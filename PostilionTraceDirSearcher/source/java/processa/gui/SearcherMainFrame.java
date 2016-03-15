package processa.gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import processa.filesearcher.model.DataRequest;

/**
 * @author Michael Felipe Rondón Acosta
 */
public class SearcherMainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SearcherMainFrame aplicacion = new SearcherMainFrame();
		aplicacion.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// private final JTextField primoMayor = new JTextField();

	private DataRequest dataRequest = new DataRequest();

	/**
	 * 
	 */
	public SearcherMainFrame() {
		super("Postilion Trace Searcher V.1.0");

		if (!this.existsAndSetLookAndFeel("Nimbus")) {
			this.existsAndSetLookAndFeel("Windows");
		}

		this.setLayout(new BorderLayout());

		JPanel criteriaPanel = new CriteriaPanel(this.dataRequest);
		JPanel resultPanel = new ResultPanel(this.dataRequest);
		this.add(criteriaPanel, BorderLayout.NORTH);
		this.add(resultPanel, BorderLayout.CENTER);

		this.setSize(800, 600);
		this.setVisible(true);
	}

	/**
	 * 
	 */
	private boolean existsAndSetLookAndFeel(String lookAndFeelName) {
		boolean resp = false;
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (lookAndFeelName.equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					resp = true;
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		return resp;
	}

}
