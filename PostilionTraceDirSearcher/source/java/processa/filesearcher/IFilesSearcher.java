package processa.filesearcher;

import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import processa.filesearcher.exceptions.SearcherException;
import processa.filesearcher.model.DataRequest;
import processa.gui.FileSearcherWorker;

/**
 * @author Michael Felipe Rondón Acosta
 */
public interface IFilesSearcher {

	/**
	 * 
	 */
	public void cancel();

	/**
	 * @param fileSearcherWorker
	 * @param dataRequest
	 * @return resp
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	public Integer searchContent(FileSearcherWorker fileSearcherWorker, DataRequest dataRequest)
			throws SearcherException, ExecutionException;

	/**
	 * @param rootPath
	 * @param fieldNumber
	 * @param fieldContent
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	public Integer searchFieldContent(Path rootPath, int fieldNumber, String fieldContent, int threshold)
			throws SearcherException, ExecutionException;

	/**
	 * @param rootPath
	 * @param fieldContent
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	public Integer searchFieldContent(Path rootPath, String fieldContent, int threshold)
			throws SearcherException, ExecutionException;

	/**
	 * @param rootPath
	 * @param fieldNumber
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	public Integer searchIfFieldExists(Path rootPath, int fieldNumber, int threshold)
			throws SearcherException, ExecutionException;

	/**
	 * @param rootPath
	 * @param fieldNumber
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	public Integer searchIfPrivFieldExists(Path rootPath, int fieldNumber, int threshold)
			throws SearcherException, ExecutionException;

	/**
	 * @param rootPath
	 * @param messageType
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	public Integer searchMessageType(Path rootPath, int messageType, int threshold)
			throws SearcherException, ExecutionException;

	/**
	 * @param rootPath
	 * @param fieldNumber
	 * @param fieldContent
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	public Integer searchPrivFieldContent(Path rootPath, int fieldNumber, String fieldContent, int threshold)
			throws SearcherException, ExecutionException;

	/**
	 * @param rootPath
	 * @param structuredDataContent
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	public Integer searchStructuredDataContent(Path rootPath, String structuredDataContent, int threshold)
			throws SearcherException, ExecutionException;

	/**
	 * @param rootPath
	 * @param structuredDataKey
	 * @param threshold
	 * @return Cantidad de lineas encontradas
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	public Integer searchStructuredDataKey(Path rootPath, String structuredDataKey, int threshold)
			throws SearcherException, ExecutionException;

	/**
	 * @param fileSearcherWorker
	 */
	public void setFileSearcherWorker(FileSearcherWorker fileSearcherWorker);
}
