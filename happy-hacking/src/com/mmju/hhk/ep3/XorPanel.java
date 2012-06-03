package com.mmju.hhk.ep3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class XorPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -7516990498605852559L;
	private static final String LOAD_SOURCE = "LOAD_SOURCE";
	private static final String CRYPT = "CRYPT";
	private static final String CLEAR = "CLEAR";

	private JTextArea source;
	private JTextArea result;
	private JTabbedPane tabbedPane;

	/**
	 * Create the panel.
	 */
	public XorPanel() {

		JPanel panel = new JPanel();

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																tabbedPane,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																778,
																Short.MAX_VALUE)
														.addComponent(
																panel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																778,
																Short.MAX_VALUE))
										.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE,
								508, Short.MAX_VALUE).addContainerGap()));

		JButton btnNewButton = new JButton("Load Source");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand(LOAD_SOURCE);

		JButton btnLoadFormal = new JButton("Crypt");
		btnLoadFormal.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLoadFormal.setBackground(Color.LIGHT_GRAY);
		btnLoadFormal.addActionListener(this);
		btnLoadFormal.setActionCommand(CRYPT);

		JButton btnClear = new JButton("Clear");
		btnClear.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClear.setBackground(Color.LIGHT_GRAY);
		btnClear.addActionListener(this);
		btnClear.setActionCommand(CLEAR);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE,
								127, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnLoadFormal,
								GroupLayout.PREFERRED_SIZE, 127,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnClear, GroupLayout.PREFERRED_SIZE,
								127, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(385, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createParallelGroup(
																Alignment.TRAILING,
																false)
																.addComponent(
																		btnNewButton,
																		Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		btnLoadFormal,
																		Alignment.LEADING,
																		GroupLayout.DEFAULT_SIZE,
																		64,
																		Short.MAX_VALUE))
												.addComponent(
														btnClear,
														GroupLayout.PREFERRED_SIZE,
														64,
														GroupLayout.PREFERRED_SIZE))
								.addContainerGap()));
		panel.setLayout(gl_panel);

		JPanel src_panel = new JPanel();
		tabbedPane.addTab("Source", null, src_panel, null);

		
		source = new JTextArea();
		source.setLineWrap(true);
		source.setWrapStyleWord(true);
		
		JScrollPane scroll_source = new JScrollPane(source);
		
		GroupLayout gl_src_panel = new GroupLayout(src_panel);
		gl_src_panel.setHorizontalGroup(
			gl_src_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_src_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scroll_source, GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_src_panel.setVerticalGroup(
			gl_src_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_src_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scroll_source, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
					.addContainerGap())
		);
		src_panel.setLayout(gl_src_panel);

		JPanel result_panel = new JPanel();
		tabbedPane.addTab("Result", null, result_panel, null);
		
		result = new JTextArea();
		result.setLineWrap(true);
		result.setWrapStyleWord(true);

		JScrollPane scroll_result = new JScrollPane(result);
		GroupLayout gl_result_panel = new GroupLayout(result_panel);
		gl_result_panel.setHorizontalGroup(gl_result_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_result_panel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scroll_result, GroupLayout.DEFAULT_SIZE, 749,
								Short.MAX_VALUE).addContainerGap()));
		gl_result_panel.setVerticalGroup(gl_result_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_result_panel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scroll_result, GroupLayout.DEFAULT_SIZE, 456,
								Short.MAX_VALUE).addContainerGap()));
		result_panel.setLayout(gl_result_panel);
		setLayout(groupLayout);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if (CRYPT.equals(e.getActionCommand())) {
				if (null != this.source.getText()
						&& !this.source.getText().isEmpty()) {
					String key = JOptionPane.showInputDialog(this,
							"Please add the key.", "Key Input",
							JOptionPane.OK_CANCEL_OPTION);
					if (null != key && !key.isEmpty()) {
						this.result.setText(new String(crypt(this.source
								.getText().getBytes(), key)));
					} else {
						this.result.setText(this.source.getText());
					}
					this.tabbedPane.setSelectedIndex(1);
				} else {
					JOptionPane.showMessageDialog(this,
							"Please Load The Source File.");
				}
			} else if (LOAD_SOURCE.equals(e.getActionCommand())) {
				JFileChooser jf = new JFileChooser();
				jf.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));
				jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (JFileChooser.APPROVE_OPTION == jf.showOpenDialog(this)) {
					byte[] bytes = Files.readAllBytes(jf.getSelectedFile()
							.toPath());
					this.source.setText(new String(bytes));
				}
				this.tabbedPane.setSelectedIndex(0);
			} else if (CLEAR.equals(e.getActionCommand())) {
				this.source.setText("");
				this.result.setText("");
			}
		} catch (Exception ex) {

		}
	}

	private byte[] crypt(byte[] input, String key) {
		byte[] result = new byte[input.length];
		byte[] keys = key.getBytes();
		int count = 0;

		for (int i = 0; i < input.length; i++) {
			if (count >= keys.length) {
				count = 0;
			}
			byte c = input[i];
			c ^= keys[count];
			result[i] = c;
			count++;
		}
		return result;
	}
}
