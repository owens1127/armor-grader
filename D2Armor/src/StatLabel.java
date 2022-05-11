import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class StatLabel extends JLabel {

	public StatLabel(String text) {
		super(text);
		setFont(new Font(Window.fontName, Font.PLAIN, 16));
	}

}
