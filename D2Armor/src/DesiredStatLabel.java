import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class DesiredStatLabel extends JLabel {

	public DesiredStatLabel(String text) {
		super(text);
		setFont(new Font(Window.fontName, Font.PLAIN, 20));
	}

}
