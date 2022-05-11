import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class StatButton extends JButton {


	public StatButton(String text) {
		super(text);
		setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		setPreferredSize(new Dimension(40, 40));
		setMargin(new Insets(5, 8, 2, 8));
		setFont(new Font(Window.fontName, Font.PLAIN, 16));
	}

}
