package processa.filesearcher.model;

import java.nio.file.Path;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class Target {

	private final String findLine;
	private final Path path;
	private final String refLine;

	/**
	 * @param path
	 * @param refLine
	 * @param findLine
	 */
	public Target(Path path, String refLine, String findLine) {
		this.path = path;
		this.refLine = refLine;
		this.findLine = findLine;
	}

	/**
	 * @return findLine
	 */
	public String getFindLine() {
		return this.findLine;
	}

	/**
	 * @return path
	 */
	public Path getPath() {
		return this.path;
	}

	/**
	 * @return refLine
	 */
	public String getRefLine() {
		return this.refLine;
	}

}
