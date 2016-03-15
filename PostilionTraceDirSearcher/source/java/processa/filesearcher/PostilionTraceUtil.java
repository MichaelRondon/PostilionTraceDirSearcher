package processa.filesearcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class PostilionTraceUtil {

	/**
	 * 
	 */
	public static final Pattern DATE_LINE_PATTERN = Pattern
			.compile("\\[[A-Za-z]{3} [0-9]{2} [0-9]{2}h[0-9]{2}:[0-9]{2}.[0-9]{3}\\] - .*");
	/**
	 * 
	 */
	public static final Pattern MESSAGE_TYPE_PATTERN = Pattern.compile("[0-9]{3}0:");
	/**
	 * 
	 */
	public static final Pattern PROCESSING_CODE_PATTERN = Pattern
			.compile("\\[Fixed  n       6 006\\] 003 \\[[0-9]{6}\\]");

	/**
	 * @param line
	 * @param curSpecificLine
	 * @param pattern
	 * @return String
	 */
	public String getSpecificLine(String line, String curSpecificLine, Pattern pattern) {
		String resp = curSpecificLine;
		Matcher matcher = pattern.matcher(line);
		if (matcher.find()) {
			resp = line;
		}
		return resp;

	}

}
