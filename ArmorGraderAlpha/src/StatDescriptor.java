import java.awt.Color;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

public class StatDescriptor extends JLabel {

	public StatDescriptor(String text) {
		super(text);
		setForeground(new Color(0, 0, 51));
		setFont(new Font(Window.fontName, Font.ITALIC, 14));
	}

}
