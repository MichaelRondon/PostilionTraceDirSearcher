package processa.gui.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import processa.filesearcher.exceptions.SearcherException;
import processa.filesearcher.model.Input;

/**
 * @author Michael Felipe Rondón Acosta
 * @param <I>
 */
public abstract class AValidator<I> {

	/**
	 * @return AValidator
	 */
	public static AValidator<String> getBasicImpl() {
		return new AValidator<String>() {

			@Override
			protected String validate(Input input) throws SearcherException {
				return input.getjTextArea().getText();
			}
		};
	}

	/**
	 * @param input
	 * @return I
	 * @throws SearcherException
	 */
	public I executeVatidation(Input input) throws SearcherException {
		String text = input.getjTextArea().getText();
		if ((text == null) || text.isEmpty()) {
			throw new SearcherException(
					String.format("Campo Obligatorio. Ingrese el campo \"%s\"", input.getLabelText()));
		}
		try {
			Pattern.compile(text);
		} catch (java.util.regex.PatternSyntaxException e) {
			throw new SearcherException(String.format("Datos inválidos. La entrada \"%s\" no es permitida.", text));
		}
		Pattern pattern = Pattern.compile("[\n\r\f]");
		Matcher matcher = pattern.matcher(text);
		if (matcher.find()) {
			throw new SearcherException(
					String.format("Datos inválidos. La entrada \"%s\" no debe contener saltos de lìnea.", text));
		}
		return this.validate(input);
	}

	/**
	 * @param input
	 * @return I
	 * @throws SearcherException
	 */
	protected abstract I validate(Input input) throws SearcherException;

	/**
	 * @param text
	 * @return parseInt
	 * @throws SearcherException
	 */
	protected int validInteger(String text) throws SearcherException {
		int parseInt;
		try {
			parseInt = Integer.parseInt(text);
		} catch (NumberFormatException e) {
			throw new SearcherException(String.format(
					"Datos inválidos. La entrada \"%s\" no es permitida. Debe ingresar un campo entero menor a 2.147.483.648",
					text));
		}
		return parseInt;
	}

	/**
	 * @param text
	 * @return value
	 * @throws SearcherException
	 */
	protected int validPositiveValue(String text) throws SearcherException {
		int value = this.validInteger(text);
		if (value <= 0) {
			throw new SearcherException(String.format(
					"Datos inválidos. La entrada \"%s\" no es permitida. Debe ingresar un valor mayor a cero", text));
		}
		return value;
	}
}
