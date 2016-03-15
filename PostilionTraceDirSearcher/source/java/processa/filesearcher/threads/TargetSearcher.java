package processa.filesearcher.threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import processa.filesearcher.PostilionTraceUtil;
import processa.filesearcher.model.Target;
import processa.gui.FileSearcherWorker;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class TargetSearcher implements Callable<Integer> {

	private FileSearcherWorker fileSearcherWorker;
	private PostilionTraceUtil postilionTraceUtil = new PostilionTraceUtil();
	private final BlockingQueue<Target> queue;
	private final int threashold;

	private final Future<?> walkerSubmit;

	/**
	 * @param queue
	 * @param walkerSubmit
	 * @param threashold
	 */
	public TargetSearcher(BlockingQueue<Target> queue, Future<?> walkerSubmit, int threashold) {
		this.queue = queue;
		this.walkerSubmit = walkerSubmit;
		this.threashold = threashold;
	}

	@Override
	public Integer call() throws IOException {
		Integer counter = 0;
		Target take;
		try {
			while ((!this.queue.isEmpty()) || ((!this.walkerSubmit.isCancelled()) && (!this.walkerSubmit.isDone()))) {
				take = this.queue.poll(500, TimeUnit.MILLISECONDS);
				if (take != null) {
					LinkedList<String> searcInFile = this.searcInFile(take);
					counter++;
					// System.out.println(searcInFile);
					if (this.fileSearcherWorker != null) {
						if (this.threashold != 0) {
							int progress = (counter * 100) / this.threashold;
							this.fileSearcherWorker.setPublicProgress(progress);
						}
						this.fileSearcherWorker.publishPublic(searcInFile);
					}

				}
			}
		} catch (InterruptedException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.printf("End Target: %s", System.currentTimeMillis());
		return counter;
	}

	/**
	 * @param target
	 * @return StringBuilder
	 * @throws IOException
	 */
	protected LinkedList<String> searcInFile(Target target) throws IOException {
		String refLine = target.getRefLine();
		Path filePath = target.getPath();
		BufferedReader bufferedReader = null;
		LinkedList<String> resp = new LinkedList<String>();
		resp.add(String.format(
				"%n******En el archivo: %2$s%n******Linea encontrada: %1$s%n******Transacción registrada:%n",
				target.getFindLine(), target.getPath()));
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath.toFile()));
			String line = null;
			boolean find = false;
			String referLine = null;
			boolean breakFlag = false;
			while (((line = bufferedReader.readLine()) != null)) {
				referLine = this.postilionTraceUtil.getSpecificLine(line, referLine,
						PostilionTraceUtil.DATE_LINE_PATTERN);
				find = (refLine.equals(referLine));
				if (find) {
					resp.add(String.format("%s%n", line));
					breakFlag = true;
				} else if (breakFlag) {
					break;
				}
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}
		return resp;
	}

	/**
	 * @param fileSearcherWorker
	 */
	public void setFileSearcherWorker(FileSearcherWorker fileSearcherWorker) {
		this.fileSearcherWorker = fileSearcherWorker;
	}

}
