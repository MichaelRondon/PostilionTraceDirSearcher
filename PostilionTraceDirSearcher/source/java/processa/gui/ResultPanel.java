package processa.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import processa.filesearcher.model.DataRequest;
import processa.gui.listeners.FrameListener;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class ResultPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton cancelJButton = new JButton("Cancelar");
	private final JTextArea parcialResultJTextArea = new JTextArea();
	private final JProgressBar progressJProgressBar = new JProgressBar();
	private final JLabel resultJLabel = new JLabel();
	private final JButton startJButton = new JButton("Iniciar Busqueda");
	private FileSearcherWorker.FileSearcherWorkerBuilder workerBuilder;

	/**
	 * @param dataRequest
	 */
	public ResultPanel(DataRequest dataRequest) {
		this.workerBuilder = new FileSearcherWorker.FileSearcherWorkerBuilder(this.parcialResultJTextArea,
				this.startJButton, this.cancelJButton, this.resultJLabel);

		this.workerBuilder.setDataRequest(dataRequest);
		this.workerBuilder.setProgressJProgressBar(this.progressJProgressBar);

		this.startJButton.setEnabled(true);
		this.cancelJButton.setEnabled(false);
		this.progressJProgressBar.setValue(0);
		this.progressJProgressBar.setStringPainted(true);
		JPanel progressPanel = new JPanel(new BorderLayout(10, 10));
		progressPanel.add(new JLabel("Progreso: "), BorderLayout.WEST);
		this.progressJProgressBar.setStringPainted(true);
		progressPanel.add(this.progressJProgressBar, BorderLayout.CENTER);

		TitledBorder title = BorderFactory.createTitledBorder("Resultados");
		this.setBorder(title);
		this.setLayout(new BorderLayout(10, 10));

		JPanel resultPanel = new JPanel(new BorderLayout());
		resultPanel.add(new JLabel("Total resultados: "), BorderLayout.WEST);
		resultPanel.add(this.resultJLabel, BorderLayout.EAST);

		this.parcialResultJTextArea.setEditable(false);
		JScrollPane jScrollPanenew = new JScrollPane(this.parcialResultJTextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPanenew.setPreferredSize(new Dimension(100, 100));

		JPanel mediumPanel = new JPanel(new BorderLayout());
		mediumPanel.add(progressPanel, BorderLayout.NORTH);
		mediumPanel.add(jScrollPanenew, BorderLayout.CENTER);
		mediumPanel.add(resultPanel, BorderLayout.SOUTH);

		JPanel southPanel = new JPanel();
		southPanel.add(this.startJButton);
		southPanel.add(this.cancelJButton);
		this.add(mediumPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		FrameListener frameListener = new FrameListener(this.workerBuilder, this);
		this.startJButton.addActionListener(frameListener);
		this.cancelJButton.addActionListener(frameListener);
	}
}
