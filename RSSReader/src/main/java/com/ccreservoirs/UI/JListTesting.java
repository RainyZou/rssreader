package com.ccreservoirs.UI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccreservoirs.renderer.MyListCellRenderer;

public class JListTesting extends JFrame {

	private static final Log log = LogFactory.getLog(JListTesting.class);

	public JListTesting() {

		initComponent();
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);

		String[] list = new String[] {
				"this is testingthis is testingthis is testingthis is testingthis is testingthis is testingthis is testingthis is testingthis is testingthis is testingthis is testingthis is testingthis is testing",
				"result for testing" };

		JList jlist = new JList(list);

		MyListCellRenderer render = new MyListCellRenderer();
		jlist.setCellRenderer(render);
		jlist.setOpaque(true);
		jlist.setSelectionForeground(Color.red);

		jlist.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					return;
				}
				log.info("effect");

			}
		});

		JScrollPane jp = new JScrollPane();
		
		jp.setViewportView(jlist);
		jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		add(jp);

		setVisible(true);
	}

	public static void main(String[] args) {

		new JListTesting();

	}

}
