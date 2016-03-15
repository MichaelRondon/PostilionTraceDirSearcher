package processa.gui.listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JProgressBar;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class ProgressListener implements PropertyChangeListener {

	private JProgressBar progressJProgressBar;

	/**
	 * @param progressJProgressBar
	 */
	public ProgressListener(JProgressBar progressJProgressBar) {
		this.progressJProgressBar = progressJProgressBar;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("progress")) {
			int nuevoValor = (Integer) evt.getNewValue();
			this.progressJProgressBar.setValue(nuevoValor);
			// System.out.println("Nuevo valor: "+ nuevoValor);
		}

	}

}
