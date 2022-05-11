import java.awt.Font;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JCheckBox;

public class CustomCheckBox extends JCheckBox {




	public CustomCheckBox(String text) {
		super(text);
		setFont(new Font(Window.fontName, Font.PLAIN, 14));
	}

}
