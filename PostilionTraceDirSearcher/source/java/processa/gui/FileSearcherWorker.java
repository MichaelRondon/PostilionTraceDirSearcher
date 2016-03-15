package processa.gui;

import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import processa.filesearcher.FilesSearcher;
import processa.filesearcher.IFilesSearcher;
import processa.filesearcher.exceptions.SearcherException;
import processa.filesearcher.model.DataRequest;
import processa.gui.listeners.ProgressListener;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class FileSearcherWorker extends SwingWorker<Integer, String> {

	/**
	 * @author Michael Felipe Rondón Acosta
	 */
	public static class FileSearcherWorkerBuilder {

		private final JButton cancelJButton;
		private DataRequest dataRequest;
		private final JTextArea parcialResultJTextArea;
		private JProgressBar progressJProgressBar;
		private final JLabel resultJLabel;
		private final JButton startJButton;

		/**
		 * @param parcialResultJTextArea
		 * @param startJButton
		 * @param cancelJButton
		 * @param resultJLabel
		 */
		public FileSearcherWorkerBuilder(JTextArea parcialResultJTextArea, JButton startJButton, JButton cancelJButton,
				JLabel resultJLabel) {
			this.parcialResultJTextArea = parcialResultJTextArea;
			this.startJButton = startJButton;
			this.cancelJButton = cancelJButton;
			this.resultJLabel = resultJLabel;
		}

		/**
		 * @return cancelJButton
		 */
		public JButton getCancelJButton() {
			return this.cancelJButton;
		}

		/**
		 * @return FileSearcherWorker
		 */
		public FileSearcherWorker getFileSearcherWorker() {
			FileSearcherWorker fileSearcherWorker = new FileSearcherWorker(this);
			ProgressListener progressListener = new ProgressListener(this.progressJProgressBar);
			fileSearcherWorker.addPropertyChangeListener(progressListener);
			fileSearcherWorker.setDataRequest(this.dataRequest);
			return fileSearcherWorker;
		}

		/**
		 * @return startJButton
		 */
		public JButton getStartJButton() {
			return this.startJButton;
		}

		/**
		 * @param dataRequest
		 */
		public void setDataRequest(DataRequest dataRequest) {
			this.dataRequest = dataRequest;
		}

		/**
		 * @param progressJProgressBar
		 */
		public void setProgressJProgressBar(JProgressBar progressJProgressBar) {
			this.progressJProgressBar = progressJProgressBar;
		}

	}

	private DataRequest dataRequest;

	private final FileSearcherWorkerBuilder fileSearcherWorkerBuilder;

	private IFilesSearcher filesSearcher = new FilesSearcher();

	/**
	 * @param parcialResultJTextArea
	 * @param startJButton
	 * @param cancelJButton
	 * @param resultJLabel
	 */
	private FileSearcherWorker(FileSearcherWorkerBuilder fileSearcherWorkerBuilder) {
		this.fileSearcherWorkerBuilder = fileSearcherWorkerBuilder;
	}

	/**
	 * 
	 */
	public void cancel() {
		// Thread.currentThread().interrupt();
		this.filesSearcher.cancel();
		this.fileSearcherWorkerBuilder.startJButton.setEnabled(true);
		this.fileSearcherWorkerBuilder.cancelJButton.setEnabled(false);
	}

	/**
	 * 
	 */
	public void clearResults() {
		this.fileSearcherWorkerBuilder.parcialResultJTextArea.setText("");
		this.fileSearcherWorkerBuilder.resultJLabel.setText("");
	}

	@Override
	protected Integer doInBackground() throws SearcherException, ExecutionException {
		this.fileSearcherWorkerBuilder.startJButton.setEnabled(false);
		this.fileSearcherWorkerBuilder.cancelJButton.setEnabled(true);
		if (this.dataRequest == null) {
			throw new SearcherException("No se obtienen los datos para realizar  la busqueda.");
		}
		return this.filesSearcher.searchContent(this, this.dataRequest);
	}

	@Override
	protected void done() {
		this.setProgress(0);
		this.fileSearcherWorkerBuilder.startJButton.setEnabled(true);
		this.fileSearcherWorkerBuilder.cancelJButton.setEnabled(false);
		Integer result = 0;
		String response;
		try {
			result = this.get();
			response = String.format("Se encontraron %d coincidencias.", result);
			this.fileSearcherWorkerBuilder.resultJLabel.setText(response);
		} catch (InterruptedException e) {
			response = String.format("Error al ejecutar el proceso.%n%s%n%s", e.getMessage(), e);
			this.fileSearcherWorkerBuilder.resultJLabel.setText(response);
		} catch (ExecutionException e) {
			response = String.format("Error al ejecutar el proceso.%n%s%n%s", e.getMessage(), e);
			this.fileSearcherWorkerBuilder.resultJLabel.setText(response);
		}

	}

	/**
	 * @return dataRequest
	 */
	public DataRequest getDataRequest() {
		return this.dataRequest;
	}

	@Override
	protected void process(List<String> valsPublicados) {
		for (String string : valsPublicados) {
			this.fileSearcherWorkerBuilder.parcialResultJTextArea.append(string);
		}

	}

	/**
	 * @param list
	 */
	public void publishPublic(List<String> list) {
		this.publish(list.toArray(new String[list.size()]));
	}

	/**
	 * @param dataRequest
	 */
	public void setDataRequest(DataRequest dataRequest) {
		this.dataRequest = dataRequest;
	}

	/**
	 * @param progress
	 */
	public void setPublicProgress(int progress) {
		this.setProgress(progress);
	}
}
