package processa.filesearcher.threads;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import processa.filesearcher.FileSearchVisitor;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class Walker implements Callable<Integer> {

	private final FileSearchVisitor fileSearchVisitor;
	private final Path rootPath;

	/**
	 * @param rootPath
	 * @param fileSearchVisitor
	 */
	public Walker(Path rootPath, FileSearchVisitor fileSearchVisitor) {
		this.rootPath = rootPath;
		this.fileSearchVisitor = fileSearchVisitor;
	}

	@Override
	public Integer call() throws IOException {
		Files.walkFileTree(this.rootPath, this.fileSearchVisitor);
		System.out.printf("End Walker: %s", System.currentTimeMillis());
		return this.fileSearchVisitor.getSuccessCounter();
	}

}
