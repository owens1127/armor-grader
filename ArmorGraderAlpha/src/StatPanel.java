import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class StatPanel extends JPanel {
	
    public static final int statBarHeight = 25;
    public static final int statBarWidth = 12;


	public StatPanel(LayoutManager layout) {
		super(layout);
		setPreferredSize(new Dimension(32*statBarWidth, statBarHeight));
		setBorder(BorderFactory.createEmptyBorder(2,0,2,0));
		edit(2, 0);
	}
	
	public void edit(int white, int gold) {
		this.removeAll();
		for (int j = 0; j < ArmorPiece.maxPerStat; j++) {
        	JPanel tick = new JPanel();
        	
        	Color color;
        	if(j < white) color = Color.WHITE;
        	else if(j < white+gold) color = Color.YELLOW;
        	else color = Color.GRAY;
        	
        	tick.setBackground(color);
        	tick.setPreferredSize(new Dimension(statBarWidth, statBarHeight));
        	
        	this.add(tick);
        }
		
	}

}
