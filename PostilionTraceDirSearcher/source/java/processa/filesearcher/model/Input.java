package processa.filesearcher.model;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import processa.filesearcher.exceptions.SearcherException;
import processa.gui.validators.AValidator;
import processa.gui.validators.ValidFieldNumber;
import processa.gui.validators.ValidMessageType;
import processa.gui.validators.ValidThreashold;

/**
 * @author Michael Felipe Rondón Acosta
 */
public enum Input {
	/**
	 * 
	 */
	FIELD_CONTENT("Contenido del campo", AValidator.getBasicImpl()),
	/**
	 * 
	 */
	FIELD_NUMBER("Número del campo", new ValidFieldNumber()),
	/**
	 * 
	 */
	MESSAGE_TYPE("Tipo de Mensaje", new ValidMessageType()),
	/**
	 * 
	 */
	SD_KEY("Llave del StructuredData", AValidator.getBasicImpl()),
	/**
	 * 
	 */
	SD_VALUE("Valor del StructuredData", AValidator.getBasicImpl()),
	/**
	 * 
	 */
	SEARCH_LIMIT("Tamaño de la busqueda", new ValidThreashold()),

	;

	private final JLabel jLabel;

	private JPanel jPanel;
	private final JTextArea jTextArea;
	private final String labelText;
	private final AValidator<?> validator;

	/**
	 * @param jTextArea
	 * @param jLabel
	 */
	private Input(String labelText, AValidator<?> validator) {
		this.labelText = labelText;
		this.jLabel = new JLabel(labelText);
		this.jTextArea = new JTextArea();
		// this.jTextArea.setPreferredSize(new Dimension(30, 5));
		this.validator = validator;
	}

	/**
	 * @return jPanel
	 */
	public JPanel getJPanel() {
		if (this.jPanel == null) {

			// this.jPanel = new JPanel();
			// Box horizontalBox = Box.createHorizontalBox();
			// horizontalBox.add(this.jLabel);
			// horizontalBox.add(this.jTextArea);
			// jPanel.add(horizontalBox);

			// this.jPanel = new JPanel(new BorderLayout());
			this.jPanel = new JPanel(new GridLayout(1, 2));
			this.jPanel.add(this.jLabel, BorderLayout.WEST);
			this.jPanel.add(this.jTextArea, BorderLayout.EAST);
		}
		return this.jPanel;
	}

	/**
	 * @return jTextArea
	 */
	public JTextArea getjTextArea() {
		return this.jTextArea;
	}

	/**
	 * @return labelText
	 */
	public String getLabelText() {
		return this.labelText;
	}

	/**
	 * @return Object
	 * @throws SearcherException
	 */
	public Object validate() throws SearcherException {
		return this.validator.executeVatidation(this);
	}

}
