package com.mmju.hhk.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.mmju.hhk.ep3.XorPanel;
import com.mmju.hhk.ep4.gui.FreeqPanel;

public class AppMain implements ActionListener {

	private JFrame frmHkbyjava;
	private CardLayout cardLayout;
	private JPanel cards;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {
			
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain window = new AppMain();
					window.frmHkbyjava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHkbyjava = new JFrame("Happy HK");
		frmHkbyjava.setTitle("Happy Hacking");
		frmHkbyjava.setBounds(100, 100, 1024, 768);
		frmHkbyjava.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		cards = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmHkbyjava.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cards, GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(cards, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		cardLayout = new CardLayout(0, 0); 
		cards.setLayout(cardLayout);
		
		JPanel xor_panel = new XorPanel();
		cards.add(xor_panel, "Cool Crypter");
		
		JPanel freeq_panel = new FreeqPanel();
		cards.add(freeq_panel, "Freeq Analyser");
		
		JButton btnCool = new JButton("Cool Crypter");
		btnCool.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnCool.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCool.setBackground(Color.CYAN);
		btnCool.addActionListener(this);
		
		JButton btnFreeq = new JButton("Freeq Analyser");
		btnFreeq.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnFreeq.setVerticalAlignment(SwingConstants.BOTTOM);
		btnFreeq.setBackground(Color.CYAN);
		btnFreeq.addActionListener(this);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCool, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
						.addComponent(btnFreeq, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnCool, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFreeq, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(506, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frmHkbyjava.getContentPane().setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();
		cardLayout.show(this.cards, jb.getText());
	}
}
