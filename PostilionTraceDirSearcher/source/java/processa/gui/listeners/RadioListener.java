package processa.gui.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JRadioButton;
import processa.filesearcher.model.DataRequest;
import processa.filesearcher.model.SearchAction;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class RadioListener implements ItemListener {

	private final DataRequest dataRequest;

	/**
	 * @param dataRequest
	 */
	public RadioListener(DataRequest dataRequest) {
		this.dataRequest = dataRequest;
	}

	@Override
	public void itemStateChanged(ItemEvent itemEvent) {
		JRadioButton item = (JRadioButton) itemEvent.getItem();
		if (item.isSelected()) {
			int mnemonic = item.getMnemonic();
			SearchAction[] values = SearchAction.values();
			this.dataRequest.setAction(values[mnemonic]);
			System.out.println(this.dataRequest.getAction());
		}
	}

}
