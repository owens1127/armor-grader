import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.Rectangle;

public class Window {
	
	public static String fontName = "Bahnschrift";
	public static int headerSize = 24;
	public static String statLabel = "Base Stat Total: ";
	public static Integer[] zeroThroughFive = {0, 1, 2, 3, 4, 5};
	
	public static final String APPNAME = "Newo's Destiny 2 Armor Grader";

	private JFrame frame;
	
	private boolean isMW;
    private boolean isAssumeMW;
    private int[] hiddenStatTiers;
    private int[] statTiers;
	private int[] statMods;
	private int tier;
	private ArmorPiece userArmor;

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		userArmor = new ArmorPiece(2, 2, 2, 2, 2, 2);
    	
    	hiddenStatTiers = new int[6];
    	statTiers = new int[6];
    	statMods = new int[6];
    	tier = 0;
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setForeground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 744, 446);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 0, 0, 30, 30, 30, 30, 10, 30, 50, 10};
		gridBagLayout.rowHeights = new int[] {0, 0, 30, 30, 30, 30, 30, 30, 0, 0, 0, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel title = new JLabel(APPNAME);
		title.setFont(new Font(Window.fontName, Font.BOLD, 32));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 10;
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 0;
		frame.getContentPane().add(title, gbc_title);
		
		JLabel header1 = new JLabel("Select Your Stats");
		header1.setFont(new Font(fontName, Font.PLAIN, headerSize));
		GridBagConstraints gbc_header1 = new GridBagConstraints();
		gbc_header1.gridwidth = 6;
		gbc_header1.insets = new Insets(0, 0, 5, 5);
		gbc_header1.gridx = 0;
		gbc_header1.gridy = 1;
		frame.getContentPane().add(header1, gbc_header1);
		
		JLabel header2 = new JLabel("Desired Stats");
		header2.setFont(new Font(fontName, Font.PLAIN, headerSize));
		GridBagConstraints gbc_header2 = new GridBagConstraints();
		gbc_header2.gridwidth = 3;
		gbc_header2.insets = new Insets(0, 0, 5, 5);
		gbc_header2.gridx = 6;
		gbc_header2.gridy = 1;
		frame.getContentPane().add(header2, gbc_header2);
		
		JLabel header3 = new JLabel("Mods");
		header3.setFont(new Font(fontName, Font.PLAIN, headerSize));
		GridBagConstraints gbc_header3 = new GridBagConstraints();
		gbc_header3.insets = new Insets(0, 0, 5, 0);
		gbc_header3.gridx = 9;
		gbc_header3.gridy = 1;
		frame.getContentPane().add(header3, gbc_header3);
		
		JLabel yourTotalStats = new StatDescriptor(statLabel + userArmor.statTotal());
		GridBagConstraints gbc_yourTotalStats = new GridBagConstraints();
		gbc_yourTotalStats.anchor = GridBagConstraints.EAST;
		gbc_yourTotalStats.ipadx = 5;
		gbc_yourTotalStats.gridwidth = 2;
		gbc_yourTotalStats.insets = new Insets(0, 0, 5, 5);
		gbc_yourTotalStats.gridx = 0;
		gbc_yourTotalStats.gridy = 8;
		frame.getContentPane().add(yourTotalStats, gbc_yourTotalStats);
		
    	JLabel[] statLabels = {
         		new StatLabel("MOB"),
         		new StatLabel("RES"),
         		new StatLabel("REC"),
         		new StatLabel("DIS"),
         		new StatLabel("INT"),
         		new StatLabel("STR")
    	 };
    	
    	for (int i = 0; i < statLabels.length; i++) {
    		GridBagConstraints gbc_statLabel = new GridBagConstraints();
    		gbc_statLabel.insets = new Insets(5, 10, 5, 10);
    		gbc_statLabel.gridx = 0;
    		gbc_statLabel.gridy = 2+i;
    		frame.getContentPane().add(statLabels[i], gbc_statLabel);
    	}
    	
		StatPanel[] armorDisplay = {
				new StatPanel(new GridLayout(1, ArmorPiece.maxPerStat)),
				new StatPanel(new GridLayout(1, ArmorPiece.maxPerStat)),
				new StatPanel(new GridLayout(1, ArmorPiece.maxPerStat)),
				new StatPanel(new GridLayout(1, ArmorPiece.maxPerStat)),
				new StatPanel(new GridLayout(1, ArmorPiece.maxPerStat)),
				new StatPanel(new GridLayout(1, ArmorPiece.maxPerStat))
		};
		
		for (int i = 0; i < armorDisplay.length; i++) {
	        	GridBagConstraints gbc_visualPanel = new GridBagConstraints();
	    		gbc_visualPanel.fill = GridBagConstraints.BOTH;
	    		gbc_visualPanel.gridx = 2;
	    		gbc_visualPanel.gridwidth = 4;
	    		gbc_visualPanel.gridy = 2+i;
	    		frame.getContentPane().add(armorDisplay[i], gbc_visualPanel);
	    }
    	
    	JComboBox[] statBoxes = {
        		new StatDropDownBox(ArmorPiece.availableStats),
        		new StatDropDownBox(ArmorPiece.availableStats),
        		new StatDropDownBox(ArmorPiece.availableStats),
        		new StatDropDownBox(ArmorPiece.availableStats),
        		new StatDropDownBox(ArmorPiece.availableStats),
        		new StatDropDownBox(ArmorPiece.availableStats)
        };
		
		for (int i = 0; i < statBoxes.length; i++) {
			GridBagConstraints gbc_statsDropdown1 = new GridBagConstraints();
			gbc_statsDropdown1.insets = new Insets(0, 0, 5, 5);
			gbc_statsDropdown1.fill = GridBagConstraints.HORIZONTAL;
			gbc_statsDropdown1.gridx = 1;
			gbc_statsDropdown1.gridy = 2+i;
			final int ii = i;
        	statBoxes[i].addItemListener(new ItemListener() {
    			@Override
    			public void itemStateChanged(ItemEvent e) {
    				if(e.getStateChange() == 1) {
	    				int newStat = (Integer) e.getItem();
	    				if (isMW) newStat-=2;
	    				userArmor.setStat(ii, newStat);
	    				armorDisplay[ii].edit(newStat, isMW ? 2 : 0);
	    				yourTotalStats.setText(statLabel + userArmor.statTotal());
    				}
    			}
            });
			frame.getContentPane().add(statBoxes[i], gbc_statsDropdown1);
		}
		
		for (int i = 0; i < statBoxes.length; i++) {
       
        }
		
	    
	    JButton[] plusButtons = {
	    		new StatButton("+1"),
	    		new StatButton("+1"),
	    		new StatButton("+1"),
	    		new StatButton("+1"),
	    		new StatButton("+1"),
	    		new StatButton("+1")
	    };
	    
	    for (int i = 0; i < plusButtons.length; i++) {
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.anchor = GridBagConstraints.EAST;
			gbc_button.gridx = 6;
			gbc_button.gridy = 2+i;
			frame.getContentPane().add(plusButtons[i], gbc_button);
		}
	    
		JLabel[] desiredStats = {
				new DesiredStatLabel("" + statTiers[0]),
				new DesiredStatLabel("" + statTiers[0]),
				new DesiredStatLabel("" + statTiers[0]),
				new DesiredStatLabel("" + statTiers[0]),
				new DesiredStatLabel("" + statTiers[0]),
				new DesiredStatLabel("" + statTiers[0]),
		};
		
		for (int i = 0; i < desiredStats.length; i++) {
			GridBagConstraints gbc_desiredStatLabel = new GridBagConstraints();
			gbc_desiredStatLabel.gridx = 7;
			gbc_desiredStatLabel.gridy = 2+i;
			frame.getContentPane().add(desiredStats[i], gbc_desiredStatLabel);
		}
		
	    
	    JButton[] minusButtons = {
	    		new StatButton("-1"),
	    		new StatButton("-1"),
	    		new StatButton("-1"),
	    		new StatButton("-1"),
	    		new StatButton("-1"),
	    		new StatButton("-1")
	    };
		
		for (int i = 0; i < minusButtons.length; i++) {
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.anchor = GridBagConstraints.WEST;
			gbc_button.gridx = 8;
			gbc_button.gridy = 2+i;
			frame.getContentPane().add(minusButtons[i], gbc_button);
		}
		
		JLabel baseTier = new StatDescriptor("Top - 0/13 : Bottom - 0/13");
		GridBagConstraints gbc_baseTier = new GridBagConstraints();
		gbc_baseTier.anchor = GridBagConstraints.EAST;
		gbc_baseTier.gridwidth = 4;
		gbc_baseTier.insets = new Insets(0, 0, 5, 5);
		gbc_baseTier.ipadx = 5;
		gbc_baseTier.gridx = 5;
		gbc_baseTier.gridy = 8;
		frame.getContentPane().add(baseTier, gbc_baseTier);
		
		for (int i = 0; i < desiredStats.length; i++) {
        	final int ii = i;
        	
        	plusButtons[i].addActionListener(new ActionListener() {
        		@Override
				public void actionPerformed(ActionEvent e) {
        			if (hiddenStatTiers[ii] == 12) return;
        			++hiddenStatTiers[ii];
        			desiredStats[ii].setText("" + ((++statTiers[ii])*10));
        			tier++;
        			baseTier.setText("Top - " + getHiddenStatsTop() + "/13" + " : Bottom - " + getHiddenStatsBottom() + "/13");
				}			
            });
        	minusButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (hiddenStatTiers[ii] == 0) return;
        			--hiddenStatTiers[ii];
        			desiredStats[ii].setText("" + ((--statTiers[ii])*10));
        			tier--;
        			baseTier.setText("Top - " + getHiddenStatsTop() + "/13" + " : Bottom - " + getHiddenStatsBottom() + "/13");
				}
            });
        }
		
		JLabel modCount = new StatDescriptor("Mods: 0");
		GridBagConstraints gbc_modCount = new GridBagConstraints();
		gbc_modCount.ipadx = 5;
		gbc_modCount.insets = new Insets(0, 0, 5, 0);
		gbc_modCount.gridx = 9;
		gbc_modCount.gridy = 8;
		frame.getContentPane().add(modCount, gbc_modCount);
		
		
		JComboBox[] statModSelectors = {
				new StatDropDownBox(zeroThroughFive),
				new StatDropDownBox(zeroThroughFive),
				new StatDropDownBox(zeroThroughFive),
				new StatDropDownBox(zeroThroughFive),
				new StatDropDownBox(zeroThroughFive),
				new StatDropDownBox(zeroThroughFive),
		};
		
		for (int i = 0; i < statModSelectors.length; i++) {
			GridBagConstraints gbc_statModSelector = new GridBagConstraints();
			gbc_statModSelector.insets = new Insets(0, 0, 5, 0);
			gbc_statModSelector.fill = GridBagConstraints.HORIZONTAL;
			gbc_statModSelector.gridx = 9;
			gbc_statModSelector.gridy = 2+i;
			final int ii = i;
			statModSelectors[i].addItemListener(new ItemListener() {
    			@Override
    			public void itemStateChanged(ItemEvent e) {
    				if(e.getStateChange() == 1) {
    					int change = (Integer) statModSelectors[ii].getSelectedItem() - statMods[ii];
    					System.out.println("change " + change);
    					statMods[ii] += change;
        				statTiers[ii] += change;
        				desiredStats[ii].setText("" + statTiers[ii]*10);
        				modCount.setText("Mods: " + getTotalStatMods());
    				}

    			}
            });
			frame.getContentPane().add(statModSelectors[i], gbc_statModSelector);
		}
		
		JCheckBox isMWBox = new CustomCheckBox("Masterworked");
		GridBagConstraints gbc_isMWBox = new GridBagConstraints();
		gbc_isMWBox.insets = new Insets(0, 0, 5, 5);
		gbc_isMWBox.gridx = 2;
		gbc_isMWBox.gridy = 8;
		isMWBox.addItemListener(new ItemListener() {
        	@Override
            public void itemStateChanged(ItemEvent e) {
            	isMW = !isMW;
		       	for (JComboBox box:statBoxes) {
		        	box.removeAllItems();
		        	Integer[] stats = (isMW ? ArmorPiece.availableMWStats : ArmorPiece.availableStats);
		        	for (int i = 0; i < stats.length; i++) {
		        		box.addItem(stats[i]);
		        	}
		        }
            }    
         });
		frame.getContentPane().add(isMWBox, gbc_isMWBox);

		
		
		JCheckBox assumeMWBox = new CustomCheckBox("Assume Masterwork");
		GridBagConstraints gbc_desiredCheckBox = new GridBagConstraints();
		gbc_desiredCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_desiredCheckBox.gridwidth = 2;
		gbc_desiredCheckBox.ipady = 4;
		gbc_desiredCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_desiredCheckBox.gridx = 6;
		gbc_desiredCheckBox.gridy = 9;
		assumeMWBox.addItemListener(new ItemListener() {
        	@Override
            public void itemStateChanged(ItemEvent e) {                 
            	isAssumeMW = !isAssumeMW;
            	tier += (isAssumeMW? 6 : - 6);
            	int statChange = (isAssumeMW? 1 : - 1);
            	for (int i = 0; i < statTiers.length; i++) {
            		statTiers[i] += statChange;
            		desiredStats[i].setText("" + 10*statTiers[i]);
            	}
            }    
         });
		frame.getContentPane().add(assumeMWBox, gbc_desiredCheckBox);
		
		
		
		
		JLabel grade = new JLabel("");
		grade.setFont(new Font(Window.fontName, Font.BOLD, 50));
		GridBagConstraints gbc_grade = new GridBagConstraints();
		gbc_grade.gridheight = 2;
		gbc_grade.gridwidth = 4;
		gbc_grade.insets = new Insets(0, 0, 0, 5);
		gbc_grade.gridx = 3;
		gbc_grade.gridy = 10;
		frame.getContentPane().add(grade, gbc_grade);
		
		JLabel textPane = new JLabel();
		textPane.setBackground(SystemColor.control);
		textPane.setFont(new Font(Window.fontName, Font.PLAIN, 12));
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPane.anchor = GridBagConstraints.NORTH;
		gbc_textPane.gridheight = 2;
		gbc_textPane.gridwidth = 3;
		gbc_textPane.gridx = 7;
		gbc_textPane.gridy = 10;
		frame.getContentPane().add(textPane, gbc_textPane);
		
		JButton runButton = new JButton("RUN");
		runButton.setPreferredSize(new Dimension(100, 40));
		runButton.setMinimumSize(new Dimension(100, 40));
		runButton.setMaximumSize(new Dimension(100, 40));
		runButton.setOpaque(false);
		runButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		runButton.setFont(new Font(Window.fontName, Font.PLAIN, 30));
		GridBagConstraints gbc_runButton = new GridBagConstraints();
		gbc_runButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_runButton.insets = new Insets(0, 0, 5, 5);
		gbc_runButton.gridwidth = 2;
		gbc_runButton.gridx = 1;
		gbc_runButton.gridy = 10;
		runButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					GradeAndStats g = ArmorGrader.calculateGrade(userArmor, hiddenStatTiers);
					if(!ArmorPiece.verify(userArmor)) {
						grade.setText("");
						textPane.setText("<html>"+"The armor piece you input had an impossible combination of"
												+ " stats. Please try again."+"</html>");
					} else if(getTotalStatMods() > 5) {
						grade.setText("");
						textPane.setText("<html>"+"You have selected more than 5"
												+ " stat mods. Please try again."+"</html>");
					} else if (getHiddenStatsTop() > 13 || getHiddenStatsBottom() > 13) {
						grade.setText("");
						textPane.setText("<html>"+"Unfortunately, there is no way to get"
												+ " that many stats even with perfect armor."
												+ " Either your Top or Bottom totals was too high."
												+ " Consider adding stat mods."+"</html>");
					} else if (g.getScore() == null) {
						String lg = g.getLetterGrade();
						grade.setText(lg);
						if (lg.equals("X")) {
							textPane.setText("<html>"+"A grade of 'X' means your desired"
									+ " stats are too low. No matter how bad your other armor is,"
									+ " you will always get those stats. Consider increasing your"
									+ " desired stats."+"</html>");
						} else {
							textPane.setText("<html>"+"A grade of 'F-' means it is impossible"
									+ " for this armor piece to be paired with any other"
									+ " armor pieces to achieve the desired stats. Consider adding stat mods while"
									+ " not changing the desired stats"+"</html>");
						}	
					} else {
						Score statsObj = g.getScore();
						grade.setText(g.getLetterGrade());
						String ratio;
						if (statsObj.getYourRarity() >= statsObj.getAvgRarity()) {
							ratio = "1 to " + Math.round(100*(1/statsObj.getRatio()))/100;
						} else {
							ratio = Math.round(100*(statsObj.getRatio()))/100 + " to 1";
						}
						textPane.setText("<html>"+"Raw Score: " + (Math.round(100*statsObj.getScore())/100)
								+ "<br>Rarity: 1 in " + Math.round(1/statsObj.getYourRarity())
								+ "<br>Avg Complementary Rarity: 1 in " + Math.round(1/statsObj.getAvgRarity())
								+ "<br>Rarity Ratio: " + ratio + "<html>");
					}
				}
	        });
		frame.getContentPane().add(runButton, gbc_runButton);
		
		JLabel socials = new JLabel("Made by @kneewoah on Twitter");
		socials.setFont(new Font(Window.fontName, Font.PLAIN, 12));
		GridBagConstraints gbc_socials = new GridBagConstraints();
		gbc_socials.ipadx = 4;
		gbc_socials.gridwidth = 3;
		gbc_socials.insets = new Insets(0, 0, 0, 5);
		gbc_socials.gridx = 0;
		gbc_socials.gridy = 11;
		frame.getContentPane().add(socials, gbc_socials);

		
	}
	
	protected int getHiddenStatsTop() {
		int stats = 0;
		for (int i = 0; i < 3; i++) {
			stats += hiddenStatTiers[i];
		}
		return stats;
	}

	protected int getHiddenStatsBottom() {
		int stats = 0;
		for (int i = 3; i < 6; i++) {
			stats += hiddenStatTiers[i];
		}
		return stats;
	}
	
	public int getTotalStatMods() {
		int count = 0;
		for (int x:statMods) {
			count+=x;
		}
		return count;
	}

	public JFrame getFrame() {
		return frame;
	}

}
