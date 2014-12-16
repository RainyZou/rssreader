package com.ccreservoirs.UI;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;

import com.ccreservoirs.RSSReader.UIMainFrame;

public class SystemTrayTest {

	final UIMainFrame frame = new UIMainFrame();

	public SystemTrayTest() {

		final TrayIcon trayIcon;

		if (SystemTray.isSupported()) {

			SystemTray tray = SystemTray.getSystemTray();
			try {
				Image image = ImageIO.read(SystemTrayTest.class
						.getClassLoader().getResourceAsStream("tray.jpg"));

				MouseListener mouseListener = new MouseListener() {
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							openFrame();
						}
						System.out.println("Tray Icon - Mouse click!");
					}

					public void mouseEntered(MouseEvent e) {
						System.out.println("Tray Icon - Mouse entered!");
					}

					public void mouseExited(MouseEvent e) {
						System.out.println("Tray Icon - Mouse exited!");
					}

					public void mousePressed(MouseEvent e) {
						System.out.println("Tray Icon - Mouse pressed!");
					}

					public void mouseReleased(MouseEvent e) {
						System.out.println("Tray Icon - Mouse released!");
					}

				};

				ActionListener exitListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String cmd = e.getActionCommand();
						if (cmd.equalsIgnoreCase("exit")) {
							System.exit(-1);
						} else if (cmd.equalsIgnoreCase("open")) {
							openFrame();
						}
					}
				};

				PopupMenu popup = new PopupMenu();
				MenuItem defaultItemOpen = new MenuItem("Open");
				defaultItemOpen.setActionCommand("open");
				defaultItemOpen.addActionListener(exitListener);
				MenuItem defaultItem = new MenuItem("Exit");
				defaultItem.setActionCommand("exit");
				defaultItem.addActionListener(exitListener);
				popup.add(defaultItemOpen);
				popup.add(defaultItem);

				trayIcon = new TrayIcon(image, "Tray Demo", popup);

				ActionListener actionListener = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						trayIcon.displayMessage("Action Event",
								"An Action Event Has Been Peformed!",
								TrayIcon.MessageType.INFO);
					}
				};

				trayIcon.setImageAutoSize(true);
				trayIcon.addActionListener(actionListener);
				trayIcon.addMouseListener(mouseListener);

				// Depending on which Mustang build you have, you may need to
				// uncomment
				// out the following code to check for an AWTException when you
				// add
				// an image to the system tray.

				// try {
				try {
					tray.add(trayIcon);
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// } catch (AWTException e) {
				// System.err.println("TrayIcon could not be added.");
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("System tray is currently not supported.");
		}
	}

	private void openFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		SystemTrayTest main = new SystemTrayTest();
	}

}
