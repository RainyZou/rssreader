package com.ccreservoirs.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MyFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1026416994451303162L;
	private static MyFrame mf = null;
	JButton jb1 = new JButton("max");
	JButton jb2 = new JButton("min");
	JButton jb3 = new JButton("normal");

	public static MyFrame getInstance() {
		if (mf == null)
			mf = new MyFrame();
		return mf;
	}

	public MyFrame() {
		super("改变窗体测试");
		init();
		systemTray();
	}

	private void init() {
		this.add(jb1, BorderLayout.NORTH);
		this.add(jb2, BorderLayout.SOUTH);
		this.add(jb3, BorderLayout.CENTER);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		this.setSize(300, 120);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int h = (int) d.getHeight() / 4;
		int w = (int) d.getWidth() / 4;
		this.setLocation(w + 150, h + 100);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MyFrame.getInstance().setVisible(false);
			}
		});
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals("max")) {
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		if (str.equals("min")) {
			this.setExtendedState(JFrame.ICONIFIED);
			System.out.println("Min");
		}
		if (str.equals("Normal")) {
			this.setExtendedState(JFrame.NORMAL);
		}
		if (str.equals("m2")) {
			this.setExtendedState(JFrame.NORMAL);
		}
	}

	public void systemTray() {
		try {
			if (java.awt.SystemTray.isSupported()) {// 判断当前平台是否支持系统托盘
				java.awt.SystemTray st = java.awt.SystemTray.getSystemTray();
				Image image = Toolkit.getDefaultToolkit().getImage(
						getClass().getResource("0.gif"));// 定义托盘图标的图片
				java.awt.TrayIcon ti = new java.awt.TrayIcon(image);
				ti.setToolTip("test frame");
				PopupMenu p = new PopupMenu("OK");
				MenuItem m = new MenuItem("exit");
				m.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				p.add(m);
				MenuItem m1 = new MenuItem("open");
				m1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MyFrame.getInstance().setVisible(true);
						MyFrame.getInstance().setExtendedState(JFrame.NORMAL);
					}
				});
				p.add(m1);
				MenuItem m2 = new MenuItem("min");
				m2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MyFrame.getInstance().setVisible(false);
					}
				});
				p.add(m2);
				ti.setPopupMenu(p); // 为托盘添加右键菜单
				st.add(ti);
			}
		} catch (Exception e) {

		}

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			MyFrame.getInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}