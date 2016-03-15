package processa.filesearcher;

import java.nio.file.Path;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Pattern;
import processa.filesearcher.exceptions.SearcherException;
import processa.filesearcher.model.DataRequest;
import processa.filesearcher.model.Target;
import processa.filesearcher.threads.TargetSearcher;
import processa.filesearcher.threads.Walker;
import processa.gui.FileSearcherWorker;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class FilesSearcher implements IFilesSearcher {

	private static ExecutorService newFixedThreadPool;

	private static BlockingQueue<Target> queue;
	private FileSearcherWorker fileSearcherWorker;

	/**
	 * 
	 */
	@Override
	public void cancel() {
		if ((queue != null)) {
			queue.clear();
		}
		if ((newFixedThreadPool != null) && !newFixedThreadPool.isTerminated()) {
			newFixedThreadPool.shutdownNow();
		}
	}

	/**
	 * @param fileSearcherWorker
	 * @param dataRequest
	 * @return resp
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Override
	public Integer searchContent(FileSearcherWorker fileSearcherWorker, DataRequest dataRequest)
			throws SearcherException, ExecutionException {
		this.setFileSearcherWorker(fileSearcherWorker);
		int resp;
		switch (dataRequest.getAction()) {
		case FIELD_CONTENT:
			resp = this.searchFieldContent(dataRequest.getPath(), dataRequest.getFieldContent(),
					dataRequest.getThreshold());
			break;
		case FIELD_NUMBER_CONTENT:
			resp = this.searchFieldContent(dataRequest.getPath(), dataRequest.getFieldNumber(),
					dataRequest.getFieldContent(), dataRequest.getThreshold());
			break;
		case FIELD_NUMBER:
			resp = this.searchIfFieldExists(dataRequest.getPath(), dataRequest.getFieldNumber(),
					dataRequest.getThreshold());
			break;
		case MESSAGETYPE:
			resp = this.searchMessageType(dataRequest.getPath(), dataRequest.getMessageType(),
					dataRequest.getThreshold());

			break;
		case PRIV_FIELD_NUMBER:
			resp = this.searchIfPrivFieldExists(dataRequest.getPath(), dataRequest.getFieldNumber(),
					dataRequest.getThreshold());
			break;
		case PRIV_FIELD_NUMBER_CONTENT:
			resp = this.searchPrivFieldContent(dataRequest.getPath(), dataRequest.getFieldNumber(),
					dataRequest.getFieldContent(), dataRequest.getThreshold());
			break;
		case STRUCTURED_CONTENT:
			resp = this.searchStructuredDataContent(dataRequest.getPath(), dataRequest.getStructuredDataContent(),
					dataRequest.getThreshold());
			break;
		case STRUCTURED_KEY:
			resp = this.searchStructuredDataKey(dataRequest.getPath(), dataRequest.getStructuredDataKey(),
					dataRequest.getThreshold());

			break;
		default:
			resp = 0;
			break;
		}
		return resp;
	}

	/**
	 * @param rootPath
	 * @param searchPattern
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	private Integer searchContent(Path rootPath, Pattern searchPattern, int threshold)
			throws SearcherException, ExecutionException {
		int respTarget = 0;
		queue = new ArrayBlockingQueue<Target>(threshold);
		FileSearchVisitor fileSearchVisitor = new FileSearchVisitor(searchPattern, threshold, queue);
		newFixedThreadPool = Executors.newFixedThreadPool(2);
		Walker walker = new Walker(rootPath, fileSearchVisitor);
		Future<Integer> walkerSubmit = newFixedThreadPool.submit(walker);
		TargetSearcher targetSearcher = new TargetSearcher(queue, walkerSubmit, threshold);
		targetSearcher.setFileSearcherWorker(this.fileSearcherWorker);
		Future<Integer> targetSubmit = newFixedThreadPool.submit(targetSearcher);
		newFixedThreadPool.shutdown();
		try {
			respTarget = targetSubmit.get();
			walkerSubmit.get();
		} catch (InterruptedException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		// if (respTarget != respWalker) {
		// throw new SearcherException(
		// "Los datos encontrados y reportados no son los mismos");
		// }
		return respTarget;
	}

	/**
	 * @param rootPath
	 * @param fieldNumber
	 * @param fieldContent
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Override
	public Integer searchFieldContent(Path rootPath, int fieldNumber, String fieldContent, int threshold)
			throws SearcherException, ExecutionException {
		String format = String.format("\\[.*\\] %2$03d \\[%1$s\\]", fieldContent, fieldNumber);
		return this.searchContent(rootPath, Pattern.compile(format), threshold);
	}

	/**
	 * @param rootPath
	 * @param fieldContent
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Override
	public Integer searchFieldContent(Path rootPath, String fieldContent, int threshold)
			throws SearcherException, ExecutionException {
		String format = String.format("\\[.*\\] .* \\[%1$s\\]", fieldContent);
		return this.searchContent(rootPath, Pattern.compile(format), threshold);
	}

	/**
	 * @param rootPath
	 * @param fieldNumber
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Override
	public Integer searchIfFieldExists(Path rootPath, int fieldNumber, int threshold)
			throws SearcherException, ExecutionException {
		String format = String.format("\\[.*\\] %1$03d \\[.*\\]", fieldNumber);
		return this.searchContent(rootPath, Pattern.compile(format), threshold);
	}

	/**
	 * @param rootPath
	 * @param fieldNumber
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Override
	public Integer searchIfPrivFieldExists(Path rootPath, int fieldNumber, int threshold)
			throws SearcherException, ExecutionException {
		String format;
		if (String.format("%1$03d", fieldNumber).equals("022")) {
			format = String.format("\\[.*\\] 127\\.022\\..*", fieldNumber);
		} else {
			format = String.format("\\[.*\\] 127\\.%1$03d \\[.*\\]", fieldNumber);
		}
		return this.searchContent(rootPath, Pattern.compile(format), threshold);
	}

	/**
	 * @param rootPath
	 * @param messageType
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Override
	public Integer searchMessageType(Path rootPath, int messageType, int threshold)
			throws SearcherException, ExecutionException {
		String format = String.format("%1$04d:", messageType);
		return this.searchContent(rootPath, Pattern.compile(format), threshold);
	}

	/**
	 * @param rootPath
	 * @param fieldNumber
	 * @param fieldContent
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Override
	public Integer searchPrivFieldContent(Path rootPath, int fieldNumber, String fieldContent, int threshold)
			throws SearcherException, ExecutionException {
		String format = String.format("\\[.*\\] 127\\.%2$03d \\[%1$s\\]", fieldContent, fieldNumber);
		// System.out.println(format);
		return this.searchContent(rootPath, Pattern.compile(format), threshold);
	}

	/**
	 * @param rootPath
	 * @param structuredDataContent
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Override
	public Integer searchStructuredDataContent(Path rootPath, String structuredDataContent, int threshold)
			throws SearcherException, ExecutionException {
		String format = String.format("                               \\[%1$s\\]", structuredDataContent);
		return this.searchContent(rootPath, Pattern.compile(format), threshold);
	}

	/**
	 * @param rootPath
	 * @param structuredDataKey
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Override
	public Integer searchStructuredDataKey(Path rootPath, String structuredDataKey, int threshold)
			throws SearcherException, ExecutionException {
		String format = String.format("\\[.*\\] 127\\.022.%1$s", structuredDataKey);
		return this.searchContent(rootPath, Pattern.compile(format), threshold);
	}

	/**
	 * @param fileSearcherWorker
	 */
	@Override
	public void setFileSearcherWorker(FileSearcherWorker fileSearcherWorker) {
		this.fileSearcherWorker = fileSearcherWorker;
	}

}
