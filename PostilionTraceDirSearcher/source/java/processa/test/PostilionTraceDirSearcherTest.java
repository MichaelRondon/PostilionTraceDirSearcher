package processa.test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import junit.framework.Assert;
import org.junit.Test;
import processa.filesearcher.FilesSearcher;
import processa.filesearcher.IFilesSearcher;
import processa.filesearcher.exceptions.SearcherException;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class PostilionTraceDirSearcherTest {

	/**
	 * @throws SearcherException
	 * @throws ExecutionException
	 */
	@Test
	public void test() throws SearcherException, ExecutionException {
		Path path = Paths.get("TracesForTest");
		Integer searchFieldContent;
		IFilesSearcher filesSearcher = new FilesSearcher();

		// searchFieldContent = filesSearcher.searchFieldContent(path, "001500",
		// 3000);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchFieldContent(path,
		// "000002053000", 20);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchIfFieldExists(path, 48, 20);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchMessageType(path, 210, 20);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchContent(
		// path,
		// Pattern.compile("\\[Fixed n 42 042\\] 090 \\[[0-9]{42}\\]"),
		// 20);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchContent(
		// path,
		// Pattern.compile("\\[Fixed n 42 042\\] 090 \\[[0-9]{42}\\]"),
		// 20);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchContent(
		// path,
		// Pattern.compile("\\[Fixed n 6 006\\] 003 \\[[0-9]{6}\\]"),
		// 20);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchFieldContent(path,
		// "639529\\*\\*\\*\\*\\*\\*\\*\\*\\*1662", 20);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// path = Paths.get("CredibancoTraces");
		//
		// searchFieldContent = filesSearcher.searchPrivFieldContent(path, 9,
		// "000015", 20);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchIfPrivFieldExists(path, 9,
		// 20);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchStructuredDataContent(path,
		// "000000054433", 10);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchStructuredDataKey(path,
		// "SALES_TAX", 10);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";
		//
		// searchFieldContent = filesSearcher.searchIfPrivFieldExists(path, 22,
		// 3);
		// System.out.printf("Lineas: %s%n", searchFieldContent);
		// Assert.assertTrue(searchFieldContent > 0);
		// assert (searchFieldContent > 0) : "Resultado no exitoso";

		path = Paths.get(
				"D:\\Requerimientos\\Juriscoop-Credibanco-eSocket\\92243-TransacciónCompraPINPAD\\CertiTracesCristian");

		searchFieldContent = filesSearcher.searchFieldContent(path, 3, "0.....", 20);
		// searchFieldContent = filesSearcher.searchMessageType(path, 210, 20);
		System.out.printf("Lineas: %s%n", searchFieldContent);
		Assert.assertTrue(searchFieldContent > 0);
		assert (searchFieldContent > 0) : "Resultado no exitoso";

	}

}
