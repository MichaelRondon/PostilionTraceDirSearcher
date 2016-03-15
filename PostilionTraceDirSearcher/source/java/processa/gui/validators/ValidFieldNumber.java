package processa.gui.validators;

import processa.filesearcher.exceptions.SearcherException;
import processa.filesearcher.model.Input;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class ValidFieldNumber extends AValidator<Integer> {

	@Override
	protected Integer validate(Input input) throws SearcherException {
		String text = input.getjTextArea().getText().trim();
		int validInteger = this.validPositiveValue(text);
		if (validInteger > 128) {
			throw new SearcherException(String.format(
					"Datos inválidos. La entrada \"%s\" no es permitida. El campo \"%s\" debe ser menor o igual a 128",
					text, input.getLabelText()));
		}
		return validInteger;
	}

}
