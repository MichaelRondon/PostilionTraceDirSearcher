package processa.gui.validators;

import processa.filesearcher.exceptions.SearcherException;
import processa.filesearcher.model.Input;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class ValidThreashold extends AValidator<Integer> {

	@Override
	protected Integer validate(Input input) throws SearcherException {
		String text = input.getjTextArea().getText().trim();
		int validInteger = this.validPositiveValue(text);
		if (validInteger > 9999) {
			throw new SearcherException(String.format(
					"Datos inválidos. La entrada \"%s\" no es permitida. El campo \"%s\" debe ser mayor o igual a 9999",
					text, input.getLabelText()));
		}
		return validInteger;
	}

}
