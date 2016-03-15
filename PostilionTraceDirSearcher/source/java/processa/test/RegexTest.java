// $codepro.audit.disable
package processa.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class RegexTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("                               \\[000000054433\\]$");
		Matcher matcher = pattern.matcher("                                    [000000054433]");
		System.out.println(matcher.find());
		System.out.println(matcher.group());
		try {
			pattern = Pattern.compile("},{");
			matcher = pattern.matcher("                                    [000000054433]");
		} catch (java.util.regex.PatternSyntaxException e) {
			System.out.println("java.util.regex.PatternSyntaxException");
		}
		System.out.println("*");
		pattern = Pattern.compile("lkjfasdhf32941239084 fsdfh 33 _ -*$%n\n\r");
		pattern = Pattern.compile("[\n\r\f]*");
		matcher = pattern.matcher(" ");
		System.out.println(matcher.find());

		// int value = Integer.parseInt("2147483648");
		// System.out.println(value);

	}

}
