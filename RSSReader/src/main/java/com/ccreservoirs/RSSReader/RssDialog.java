package com.ccreservoirs.RSSReader;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import com.ccreservoirs.RSSReader.entity.RSSFeed;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RssDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private RSSFeed feed;
	private JTextField urlVar;
	private JTextField nameVar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RssDialog dialog = new RssDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RssDialog() {
		super((Frame) null, true);
		initComponent();
	}

	public void initComponent() {
		setTitle("Rss Setting");
		setBounds(100, 100, 306, 141);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblRssLocation = new JLabel("*Rss Location:");
			GridBagConstraints gbc_lblRssLocation = new GridBagConstraints();
			gbc_lblRssLocation.insets = new Insets(0, 0, 5, 5);
			gbc_lblRssLocation.anchor = GridBagConstraints.EAST;
			gbc_lblRssLocation.gridx = 0;
			gbc_lblRssLocation.gridy = 0;
			contentPanel.add(lblRssLocation, gbc_lblRssLocation);
		}
		{
			urlVar = new JTextField();
			GridBagConstraints gbc_urlVar = new GridBagConstraints();
			gbc_urlVar.insets = new Insets(0, 0, 5, 0);
			gbc_urlVar.fill = GridBagConstraints.HORIZONTAL;
			gbc_urlVar.gridx = 1;
			gbc_urlVar.gridy = 0;
			contentPanel.add(urlVar, gbc_urlVar);
			urlVar.setColumns(10);
		}
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.insets = new Insets(0, 0, 0, 5);
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			nameVar = new JTextField();
			GridBagConstraints gbc_nameVar = new GridBagConstraints();
			gbc_nameVar.fill = GridBagConstraints.HORIZONTAL;
			gbc_nameVar.gridx = 1;
			gbc_nameVar.gridy = 1;
			contentPanel.add(nameVar, gbc_nameVar);
			nameVar.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String url = urlVar.getText();
						String name = nameVar.getText();
						feed = new RSSFeed();
						feed.setName(name);
						feed.setLink(url);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public RssDialog(Frame owner, boolean modal) {
		super(owner, modal);
		initComponent();
	}

	public RSSFeed getFeed() {
		return feed;
	}

	public void setFeed(RSSFeed feed) {
		this.feed = feed;
	}

}
