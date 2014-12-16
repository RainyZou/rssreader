package com.ccreservoirs.renderer;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyListCellRenderer extends JPanel implements ListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7495834321255693589L;

	private static final Log log = LogFactory.getLog(MyListCellRenderer.class);
	static ImageIcon longIcon;
	static ImageIcon shortIcon;

	public MyListCellRenderer() {
		try {
			longIcon = new ImageIcon(ImageIO.read(MyListCellRenderer.class
					.getClassLoader().getResourceAsStream("tray.jpg")));
			shortIcon = new ImageIcon(ImageIO.read(MyListCellRenderer.class
					.getClassLoader().getResourceAsStream("tray.jpg")));
		} catch (Exception e) {
			log.error(e, e);
		}
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		removeAll();
		String s = value.toString();
		JLabel label1 = new JLabel(s);
		JLabel label2 = new JLabel(s);
		setLayout(new BorderLayout());
		add(label1, BorderLayout.NORTH);
		add(label2, BorderLayout.EAST);
		return this;
	}

}
