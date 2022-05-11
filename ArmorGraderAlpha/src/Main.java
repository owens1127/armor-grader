import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		DoublePlug.generateDPs();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
