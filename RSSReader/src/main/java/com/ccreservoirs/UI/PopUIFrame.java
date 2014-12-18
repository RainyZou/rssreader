package com.ccreservoirs.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;

public class PopUIFrame extends JFrame {

	private JPanel contentPane;
	private JEditorPane htmlPane;

	public JEditorPane getHtmlPane() {
		return htmlPane;
	}

	public void setHtmlPane(JEditorPane htmlPane) {
		this.htmlPane = htmlPane;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUIFrame frame = new PopUIFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PopUIFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		htmlPane = new JEditorPane();
		scrollPane.setViewportView(htmlPane);
		htmlPane.setContentType("text/html");
		htmlPane.setEditable(false);
		setSize(800, 600);
	}

}
