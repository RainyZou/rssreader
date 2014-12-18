
package com.ccreservoirs.RSSReader;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ccreservoirs.RSSReader.entity.RSSFeed;
import com.ccreservoirs.RSSReader.entity.RSSItem;
import com.ccreservoirs.renderer.MyListCellRenderer;
import com.ccreservoirs.util.FeedUtil;

public class UIPanel extends JPanel {
	private Log log = LogFactory.getLog(UIPanel.class);
	DefaultListModel<RSSFeed> modelFeed;
	DefaultListModel<RSSItem> modelItem;
	private JList itemList;
	private JEditorPane descConent;
	private JList rssList;
	private JScrollPane htmlPane;

	/**
	 * Create the panel.
	 */
	public UIPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 200, 200, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblRssRecord = new JLabel("Rss Record");
		GridBagConstraints gbc_lblRssRecord = new GridBagConstraints();
		gbc_lblRssRecord.insets = new Insets(0, 0, 5, 5);
		gbc_lblRssRecord.gridx = 0;
		gbc_lblRssRecord.gridy = 0;
		panel.add(lblRssRecord, gbc_lblRssRecord);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RssDialog jd = new RssDialog(new JFrame(), true);
				jd.setVisible(true);
				RSSFeed feed = jd.getFeed();
				modelFeed.addElement(feed);

			}
		});
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 1;
		gbc_btnAdd.gridy = 0;
		panel.add(btnAdd, gbc_btnAdd);
		modelFeed = new DefaultListModel<RSSFeed>();

		JScrollPane scrollPane_3 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_3 = new GridBagConstraints();
		gbc_scrollPane_3.gridwidth = 2;
		gbc_scrollPane_3.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_3.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_3.gridx = 0;
		gbc_scrollPane_3.gridy = 1;
		panel.add(scrollPane_3, gbc_scrollPane_3);

		rssList = new JList();
		rssList.setModel(modelFeed);

		rssList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					return;
				}
				final RSSFeed tempFeed = (RSSFeed) rssList.getSelectedValue();
				log.info(tempFeed);
				new SwingWorker<List<RSSItem>, Void>() {

					@Override
					protected List<RSSItem> doInBackground() throws Exception {
						return FeedUtil.getItems(tempFeed.getLink());
					}

					protected void done() {
						// 没有必要用invokeLater！因为done()本身是在EDT中执行的
						SwingUtilities.invokeLater(new Runnable() {

							public void run() {
								try {
									List<RSSItem> items = get();
									modelItem.clear();
									for (RSSItem rssItem : items) {
										modelItem.addElement(rssItem);
									}
								} catch (Exception e) {
									log.error(e);
								}
							};
						});
					}
				}.execute();

			}
		});
		scrollPane_3.setViewportView(rssList);

		JButton button = new JButton("星形记录");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridwidth = 2;
		gbc_button.gridx = 0;
		gbc_button.gridy = 2;
		panel.add(button, gbc_button);

		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 0;
		add(scrollPane_1, gbc_scrollPane_1);

		itemList = new JList();
		modelItem = new DefaultListModel<RSSItem>();
		itemList.setModel(modelItem);
		itemList.setCellRenderer(new MyListCellRenderer());
		itemList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					return;
				}
				final RSSItem item = (RSSItem) itemList.getSelectedValue();

				new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						// TODO Auto-generated method stub
						return null;
					}

					protected void done() {
						// 没有必要用invokeLater！因为done()本身是在EDT中执行的
						SwingUtilities.invokeLater(new Runnable() {

							public void run() {
								descConent
										.setText(item.getDescription() == null ? ""
												: item.getDescription());
								descConent.setCaretPosition(0);
							};
						});
					}

				}.execute();

			}
		});

		scrollPane_1.setViewportView(itemList);

		htmlPane = new JScrollPane();

		GridBagConstraints gbc_htmlPane = new GridBagConstraints();
		gbc_htmlPane.fill = GridBagConstraints.BOTH;
		gbc_htmlPane.gridx = 2;
		gbc_htmlPane.gridy = 0;
		add(htmlPane, gbc_htmlPane);

		descConent = new JEditorPane();
		descConent.setContentType("text/html");
		descConent.setEditable(false);

		htmlPane.setViewportView(descConent);

	}
}
