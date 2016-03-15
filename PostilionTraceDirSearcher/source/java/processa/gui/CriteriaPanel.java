package processa.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import processa.filesearcher.model.DataRequest;
import processa.filesearcher.model.Input;
import processa.filesearcher.model.SearchAction;
import processa.gui.listeners.ComboListener;
import processa.gui.listeners.MyFileChooserListener;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class CriteriaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private final DataRequest dataRequest;

	/**
	 * @param dataRequest
	 */
	public CriteriaPanel(DataRequest dataRequest) {
		// this.dataRequest =dataRequest;
		TitledBorder title = BorderFactory.createTitledBorder("Criterios de Busqueda");
		this.setBorder(title);
		this.setLayout(new BorderLayout(10, 10));

		/* FileChoser: */
		MyFileChooserListener myFileChooserListener = new MyFileChooserListener(dataRequest);
		this.add(myFileChooserListener.getFilePanel(), BorderLayout.NORTH);

		/* Combo: */
		ComboListener comboListener = new ComboListener(dataRequest);
		JComboBox<SearchAction> comboBox = SearchAction.getComboBox();
		comboBox.addActionListener(comboListener);
		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

		centerPanel.add(comboBox, BorderLayout.NORTH);

		/* Inputs: */
		JPanel inputsJPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		Input[] values = Input.values();
		for (Input input : values) {
			inputsJPanel.add(input.getJPanel());
		}
		centerPanel.add(inputsJPanel, BorderLayout.CENTER);
		this.add(centerPanel, BorderLayout.CENTER);

		/* Inicialización de las opciones del frame: */
		dataRequest.setAction(SearchAction.values()[0]);
		comboListener.enlableInputs(SearchAction.values()[0]);

	}

}
