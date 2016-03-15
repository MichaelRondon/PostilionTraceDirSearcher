package processa.gui.listeners;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import processa.filesearcher.model.DataRequest;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class MyFileChooserListener implements ActionListener {

	private final DataRequest dataRequest;
	private JLabel directoryNameJLabel = new JLabel("Directorio:     --      ");
	private JFileChooser fileChooser = new JFileChooser();
	private JButton fileChooserJButton = new JButton("Buscar");
	private JPanel filePanel;

	/**
	 * @param dataRequest
	 */
	public MyFileChooserListener(DataRequest dataRequest) {
		this.dataRequest = dataRequest;
		this.filePanel = new JPanel(new BorderLayout());

		// JPanel auxPanel = new JPanel();
		// auxPanel.add(fileChooserJButton);

		this.filePanel.add(this.directoryNameJLabel, BorderLayout.CENTER);
		this.filePanel.add(this.fileChooserJButton, BorderLayout.EAST);
		this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		this.fileChooserJButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int result = this.fileChooser.showOpenDialog(this.filePanel);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = this.fileChooser.getSelectedFile();
			this.dataRequest.setPath(selectedFile.toPath());
			this.directoryNameJLabel.setText(String.format("Directorio: %s", this.dataRequest.getPath()));
			System.out.printf("Selected file: %s", selectedFile.getAbsolutePath());
		}
	}

	/**
	 * @return filePanel
	 */
	public JPanel getFilePanel() {
		return this.filePanel;
	}

}
