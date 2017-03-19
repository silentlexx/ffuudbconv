package com.silentlexx.ffuudbconv;


import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.io.File;

import javax.swing.JButton;

public class Gui {
	final static int max = 32;
	//final static String iconka = "resources/ffuudbconv.png";
	private JFrame frame = null;
	private JComboBox[] list = new JComboBox[max];
	private JLabel[] imya = new JLabel[max];
    private static final Insets insets = new Insets(0, 0, 0, 0);
	FieldsInfo fields_info;
	Debug D;
	private JButton jButton = null;
	private int n = 0;
	private ImageIcon icon;
	  private static void addComponent(Container container, Component component, int gridx, int gridy,
		      int gridwidth, int gridheight, int anchor, int fill) {
		    GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 1.0, 1.0,
		        anchor, fill, insets, 0, 0);
		    container.add(component, gbc);
		  }

	private JFrame getJFrame() {
		if (frame == null) {
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, (fields_info.getNumSQL()*25)+50);
			frame.setLayout(new GridBagLayout());
		    for (int i = 0; i<fields_info.getNumSQL();i++){
		    		    
			imya[i] = new JLabel();
			imya[i].setText(fields_info.getSQL(i));		    
		    addComponent(frame, imya[i], 0, i, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
	
		    list[i] = new JComboBox(fields_info.getCsvList());
		    addComponent(frame, list[i], 1, i, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		    
		    n=i;
		    }
		    n++;
		    addComponent(frame, getJButton(), 0, n, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH);
		    		    
		    	    
			frame.setTitle("CSV Converter for IMUSA by SilentLexx");
					
			//icon = getImageIcon(iconka);
			
			//frame.setIconImage(icon.getImage());
			
		}
		return frame;
	}

	private ImageIcon getImageIcon(String name) {
		File f = new File(name);  
		if (f.exists()) return new ImageIcon(name);    
		return new ImageIcon(ClassLoader.getSystemResource(name));
	   }


	Gui(FieldsInfo fi) {
		D = Main.D;
		fields_info = fi;
		getJFrame().setVisible(true);
		D.p("Starting GUI :-)");
}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("Create Output File");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					D.p("actionPerformed()");
					
					for (int i = 0; i<fields_info.getNumSQL();i++){
						D.p(i+ " Sync "+fields_info.getSQL(i)+" = "+fields_info.getCSV(list[i].getSelectedIndex())+" "+list[i].getSelectedIndex());
						fields_info.setSC(i,list[i].getSelectedIndex());
						}
					Main.doSQL();
				}
			});
		}
		return jButton;
	}

}
