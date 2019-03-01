package com.zombiemaptracker.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import com.zombiemaptracker.beans.TrackConfig;
import com.zombiemaptracker.utilities.Util;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JList;
import java.awt.ScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.UIManager.*;

public class mapTrackerInterface {
	private DefaultListModel<TrackConfig> mapTrackingList = new DefaultListModel<TrackConfig>();
	public DefaultListModel<TrackConfig> getMapTrackingList() {
		return mapTrackingList;
	}

	public void setMapTrackingList(DefaultListModel<TrackConfig> mapTrackingList) {
		this.mapTrackingList = mapTrackingList;
	}

	private JFrame frmCsgoCommunityServer;
	private JTextField ipTextField;
	private JTextField portTextField;
	private JTextField filterFileLocation;
	private JTextField serverNameTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look and feel.
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mapTrackerInterface window = new mapTrackerInterface();
					window.frmCsgoCommunityServer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mapTrackerInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCsgoCommunityServer = new JFrame();
		frmCsgoCommunityServer.setTitle("CSGO Community Server Map Tracker v0.1a");
		frmCsgoCommunityServer.setResizable(false);
		frmCsgoCommunityServer.setBounds(100, 100, 515, 402);
		frmCsgoCommunityServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmCsgoCommunityServer.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
				);

		JPanel settingsTab = new JPanel();
		tabbedPane.addTab("Settings", null, settingsTab, null);

		JLabel lblServerIp = new JLabel("*Server IP:");

		ipTextField = new JTextField();
		ipTextField.setColumns(10);

		JLabel lblPort = new JLabel("*Port #:");

		portTextField = new JTextField();
		portTextField.setColumns(10);

		JLabel lblFilterFile = new JLabel("Filter File: ");

		filterFileLocation = new JTextField();
		filterFileLocation.setColumns(10);

		JButton btnBrowse = new JButton("Browse");

		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ipTextField.getText().equals("") || portTextField.getText().equals("")
						|| serverNameTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(frmCsgoCommunityServer, "Eggs are not supposed to be red. So fill in the required textfields");
				}
				TrackConfig tc = new TrackConfig(serverNameTextField.getText(), ipTextField.getText(), Integer.parseInt(portTextField.getText()), 
						filterFileLocation.getText());
				mapTrackingList.addElement(tc);
				try {
					Util.trackMap(tc);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				serverNameTextField.setText("");
				ipTextField.setText("");;
				portTextField.setText("");;
				filterFileLocation.setText("");;
			}
		});

		JLabel serverName = new JLabel("*Server: ");

		serverNameTextField = new JTextField();
		serverNameTextField.setColumns(10);

		JButton btnDefault = new JButton("Default");
		btnDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				portTextField.setText("27015");
			}
		});
		GroupLayout gl_settingsTab = new GroupLayout(settingsTab);
		gl_settingsTab.setHorizontalGroup(
			gl_settingsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_settingsTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_settingsTab.createParallelGroup(Alignment.LEADING)
						.addComponent(lblServerIp)
						.addComponent(lblPort)
						.addComponent(lblFilterFile)
						.addComponent(serverName))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_settingsTab.createParallelGroup(Alignment.LEADING, false)
						.addComponent(filterFileLocation)
						.addComponent(portTextField)
						.addComponent(ipTextField, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addComponent(serverNameTextField))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_settingsTab.createParallelGroup(Alignment.LEADING)
						.addComponent(addButton, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
						.addComponent(clearButton, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
						.addComponent(btnBrowse, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
						.addComponent(btnDefault, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_settingsTab.setVerticalGroup(
			gl_settingsTab.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_settingsTab.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_settingsTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(addButton)
						.addComponent(serverName)
						.addComponent(serverNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_settingsTab.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_settingsTab.createSequentialGroup()
							.addGroup(gl_settingsTab.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblServerIp)
								.addComponent(ipTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_settingsTab.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPort)
								.addComponent(portTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDefault)))
						.addComponent(clearButton))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_settingsTab.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilterFile)
						.addComponent(filterFileLocation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBrowse, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(180, Short.MAX_VALUE))
		);
		settingsTab.setLayout(gl_settingsTab);

		JPanel mapTracker = new JPanel();
		tabbedPane.addTab("Map Tracker", null, mapTracker, null);

		JList list = new JList(mapTrackingList);

		JButton btnResfresh = new JButton("Resfresh");
		GroupLayout gl_mapTracker = new GroupLayout(mapTracker);
		gl_mapTracker.setHorizontalGroup(
				gl_mapTracker.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_mapTracker.createSequentialGroup()
						.addContainerGap()
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnResfresh)
						.addContainerGap())
				);
		gl_mapTracker.setVerticalGroup(
				gl_mapTracker.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_mapTracker.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_mapTracker.createParallelGroup(Alignment.LEADING)
								.addComponent(list, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
								.addComponent(btnResfresh))
						.addContainerGap())
				);
		mapTracker.setLayout(gl_mapTracker);
		frmCsgoCommunityServer.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frmCsgoCommunityServer.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}
}
