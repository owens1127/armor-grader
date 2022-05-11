import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class StatDropDownBox extends JComboBox {
	
	public static Dimension size = new Dimension (22, 30);

	public StatDropDownBox(Object[] a) {
		super(a);
		setFont(new Font(Window.fontName, Font.PLAIN, 16));
		setPreferredSize(size);
		setSize(size);
		setBackground(SystemColor.controlHighlight);
	}

}
