package processa.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import processa.filesearcher.exceptions.SearcherException;
import processa.filesearcher.model.DataRequest;
import processa.gui.FileSearcherWorker;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class FrameListener implements ActionListener {

	private final JPanel parent;
	private final FileSearcherWorker.FileSearcherWorkerBuilder workerBuilder;

	/**
	 * @param fileSearcherWorker
	 * @param parent
	 */
	public FrameListener(FileSearcherWorker.FileSearcherWorkerBuilder fileSearcherWorker, JPanel parent) {
		this.workerBuilder = fileSearcherWorker;
		this.parent = parent;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		FileSearcherWorker fileSearcherWorker = this.workerBuilder.getFileSearcherWorker();
		if (source == this.workerBuilder.getStartJButton()) {
			try {
				this.workerBuilder.getStartJButton().setEnabled(false);
				this.workerBuilder.getCancelJButton().setEnabled(true);
				this.processAction(fileSearcherWorker);
			} catch (SearcherException e) {
				this.displayError(e);
				this.workerBuilder.getStartJButton().setEnabled(true);
				this.workerBuilder.getCancelJButton().setEnabled(false);
			}
		} else if (source == this.workerBuilder.getCancelJButton()) {
			fileSearcherWorker.cancel();
		}
	}

	/**
	 * @param exception
	 */
	public void displayError(Exception exception) {
		JOptionPane.showMessageDialog(this.parent, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void processAction(FileSearcherWorker fileSearcherWorker) throws SearcherException {
		DataRequest dataRequest = fileSearcherWorker.getDataRequest();
		if ((dataRequest != null) && (dataRequest.getAction() != null)) {
			dataRequest.validate();
			fileSearcherWorker.clearResults();
			fileSearcherWorker.execute();
		} else {
			this.displayError(new SearcherException("Error procesando la acción a ejecutar"));
		}
	}

}
