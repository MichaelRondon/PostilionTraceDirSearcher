package processa.filesearcher.model;

import javax.swing.JComboBox;

/**
 * @author Michael Felipe Rondón Acosta
 */
public enum SearchAction {
	/**
	 * 
	 */
	FIELD_CONTENT("Busqueda por contenido del campo.", new Input[] { Input.FIELD_CONTENT, Input.SEARCH_LIMIT }),
	/**
	 * 
	 */
	FIELD_NUMBER("Busqueda por campo.", new Input[] { Input.FIELD_NUMBER, Input.SEARCH_LIMIT }),
	/**
	 * 
	 */
	FIELD_NUMBER_CONTENT("Busqueda por campo y contenido.", new Input[] { Input.FIELD_NUMBER, Input.FIELD_CONTENT, Input.SEARCH_LIMIT }),
	/**
	 * 
	 */
	MESSAGETYPE("Busqueda por tipo de mensaje.", new Input[] { Input.MESSAGE_TYPE, Input.SEARCH_LIMIT }),
	/**
	 * 
	 */
	PRIV_FIELD_NUMBER("Busqueda por campo privado.", new Input[] { Input.FIELD_NUMBER, Input.SEARCH_LIMIT }),
	/**
	 * 
	 */
	PRIV_FIELD_NUMBER_CONTENT("Busqueda por campo privado y contenido.", new Input[] { Input.FIELD_NUMBER, Input.FIELD_CONTENT, Input.SEARCH_LIMIT }),
	/**
	 * 
	 */
	STRUCTURED_CONTENT("Busqueda por valor del StructuredData.", new Input[] { Input.SD_VALUE, Input.SEARCH_LIMIT }),
	/**
	 * 
	 */
	STRUCTURED_KEY("Busqueda por llave del StructuredData.", new Input[] { Input.SD_KEY, Input.SEARCH_LIMIT });

	/**
	 * @return JComboBox
	 */
	public static JComboBox<SearchAction> getComboBox() {
		JComboBox<SearchAction> comboBox = new JComboBox<SearchAction>(values());
		comboBox.setSelectedIndex(0);
		return comboBox;
	}
	private final Input[] inputs;

	private final String text;

	private SearchAction(String text, Input[] inputs) {
		this.text = text;
		this.inputs = inputs;
	}

	/**
	 * @return inputs
	 */
	public Input[] getInputs() {
		return this.inputs;
	}

	private String getText() {
		return this.text;
	}

	@Override
	public String toString() {
		return this.getText();
	}

}
