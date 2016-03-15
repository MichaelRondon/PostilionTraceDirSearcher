package processa.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import processa.filesearcher.model.DataRequest;
import processa.filesearcher.model.Input;
import processa.filesearcher.model.SearchAction;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class ComboListener implements ActionListener {

	private final DataRequest dataRequest;

	/**
	 * @param dataRequest
	 */
	public ComboListener(DataRequest dataRequest) {
		this.dataRequest = dataRequest;
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		@SuppressWarnings("unchecked")
		JComboBox<SearchAction> comboBox = (JComboBox<SearchAction>) action.getSource();
		this.dataRequest.setAction((SearchAction) comboBox.getSelectedItem());
		System.out.println(this.dataRequest.getAction());
		this.enlableInputs((SearchAction) comboBox.getSelectedItem());

	}

	/**
	 * 
	 */
	private void disableAll() {
		Input[] values = Input.values();
		for (Input input : values) {
			input.getjTextArea().setEnabled(false);
			input.getjTextArea().setEditable(false);
		}
	}

	/**
	 * @param searchAction
	 */
	public void enlableInputs(SearchAction searchAction) {
		this.disableAll();
		Input[] inputs = searchAction.getInputs();
		for (Input input : inputs) {
			input.getjTextArea().setEnabled(true);
			input.getjTextArea().setEditable(true);
		}
	}

}
