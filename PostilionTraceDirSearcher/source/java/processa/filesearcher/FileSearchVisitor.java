package processa.filesearcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import processa.filesearcher.model.Target;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class FileSearchVisitor implements FileVisitor<Path> {

	private PostilionTraceUtil postilionTraceUtil = new PostilionTraceUtil();

	private BlockingQueue<Target> queue;

	private final Pattern searchPattern;

	private int successCounter;

	private final int threshold;

	/**
	 * @param searchPattern
	 * @param threshold
	 * @param queue
	 */
	public FileSearchVisitor(Pattern searchPattern, int threshold, BlockingQueue<Target> queue) {
		this.searchPattern = searchPattern;
		this.threshold = threshold;
		this.queue = queue;
	}

	/**
	 * @return successCounter
	 */
	public int getSuccessCounter() {
		return this.successCounter;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path arg0, IOException arg1) throws IOException {
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path arg0, BasicFileAttributes arg1) throws IOException {
		FileVisitResult resp = FileVisitResult.TERMINATE;
		if (this.successCounter < this.threshold) {
			resp = FileVisitResult.CONTINUE;
		}
		return resp;
	}

	private void searcInFile(Path filePath) throws IOException {
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(filePath.toFile()));
			String line = null;
			boolean find = false;
			String referLine = null;
			while (((line = bufferedReader.readLine()) != null) && (this.successCounter < this.threshold)) {
				Matcher matcher = this.searchPattern.matcher(line);
				find = matcher.find();
				referLine = this.postilionTraceUtil.getSpecificLine(line, referLine,
						PostilionTraceUtil.DATE_LINE_PATTERN);
				if (find) {
					Target target = new Target(filePath, referLine, line);
					this.queue.put(target);
					// System.out.printf("%2$-20s %1$s%n", line, "Text:");
					this.successCounter++;
					// System.out.printf("%2$-20s %1$s%n%n",
					// this.successCounter,
					// "Counter:");
				}
			}
		} catch (InterruptedException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}

	}

	@Override
	public FileVisitResult visitFile(Path arg0, BasicFileAttributes arg1) throws IOException {
		if (this.successCounter < this.threshold) {
			this.searcInFile(arg0);
		}
		FileVisitResult resp = FileVisitResult.TERMINATE;
		if (this.successCounter < this.threshold) {
			resp = FileVisitResult.CONTINUE;
		}
		return resp;
	}

	@Override
	public FileVisitResult visitFileFailed(Path arg0, IOException arg1) throws IOException {
		// System.out.println(arg1);
		// arg1.printStackTrace();
		return FileVisitResult.CONTINUE;
	}

}
