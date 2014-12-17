package com.ccreservoirs.renderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.swing.DefaultLookup;

import com.ccreservoirs.RSSReader.entity.RSSItem;

public class MyListCellRenderer extends JPanel implements ListCellRenderer {

	private static final long serialVersionUID = 7495834321255693589L;
	private static final Log log = LogFactory.getLog(MyListCellRenderer.class);

	public MyListCellRenderer() {
		setPreferredSize(new Dimension(150, 30));

	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		removeAll();
		RSSItem tempItem = (RSSItem) value;
		String s = value.toString();
		JLabel label1 = new JLabel("" + tempItem.getTitle() + "");
		JLabel label2 = new JLabel(tempItem.getDate());
		setLayout(new BorderLayout());
		if (index % 2 == 0) {
			setBackground(new Color(0xF8F8FF));
		} else {
			setBackground(Color.white);
		}
		add(label1, BorderLayout.NORTH);
		add(label2, BorderLayout.EAST);
		Color bg = null;
		Color fg = null;

		JList.DropLocation dropLocation = list.getDropLocation();
		if (dropLocation != null && !dropLocation.isInsert()
				&& dropLocation.getIndex() == index) {
			bg = DefaultLookup.getColor(this, ui, "List.dropCellBackground");
			fg = DefaultLookup.getColor(this, ui, "List.dropCellForeground");
			isSelected = true;
		}

		if (isSelected) {
			setBackground(bg == null ? list.getSelectionBackground() : bg);
			setForeground(fg == null ? list.getSelectionForeground() : fg);
		}

		return this;
	}

}
