package com.mmju.hhk.ep4.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.mmju.hhk.ep4.freeq.FreqChar;
import com.mmju.hhk.ep4.freeq.FreqString;

public class FreeqPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -7516990498605852559L;
	private static final String SOURCE = "SOURCE";
	private static final String FORMAL = "FORMAL";
	private static final String CRYPT = "CRYPT";
	private static final String CLEAR = "CLEAR";
	private static final String crypt = "crypt";
	
	private FreqString srcFreq;
	private FreqString fomFreq;

	/**
	 * Create the panel.
	 */
	public FreeqPanel() {
		
		JPanel panel = new JPanel();
		
		sourceArea = new JTextArea();
		sourceArea.setLineWrap(true);
		sourceArea.setWrapStyleWord(true);
		formalArea = new JTextArea();
		formalArea.setLineWrap(true);
		formalArea.setWrapStyleWord(true);
		cryptTopArea = new JTextArea();
		cryptTopArea.setLineWrap(true);
		cryptTopArea.setWrapStyleWord(true);
		cryptDownArea = new JTextArea();
		cryptDownArea.setLineWrap(true);
		cryptDownArea.setWrapStyleWord(true);

		sourceTable = new JTable(this.getTableModel(false));
		formalTable = new JTable(this.getTableModel(false));
		cryptTable = new JTable(this.getTableModel(true));
		cryptTable.setAutoCreateRowSorter(true);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 508, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btnNewButton = new JButton("Load Source");
		btnNewButton.setActionCommand(SOURCE);
		btnNewButton.addActionListener(this);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JButton btnLoadFormal = new JButton("Load Formal");
		btnLoadFormal.setActionCommand(FORMAL);
		btnLoadFormal.addActionListener(this);
		btnLoadFormal.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLoadFormal.setBackground(Color.LIGHT_GRAY);
		
		JButton btnCrypt = new JButton("Crypt");
		btnCrypt.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCrypt.setBackground(Color.LIGHT_GRAY);
		btnCrypt.setActionCommand(CRYPT);
		btnCrypt.addActionListener(this);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClear.setBackground(Color.LIGHT_GRAY);
		btnClear.setActionCommand(CLEAR);
		btnClear.addActionListener(this);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLoadFormal, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCrypt, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(252, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnLoadFormal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
						.addComponent(btnCrypt, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JPanel src_panel = new JPanel();
		tabbedPane.addTab("Source", null, src_panel, null);
		
		JPanel panel_2 = new JPanel();
		
		JPanel panel_3 = new JPanel();
		GroupLayout gl_src_panel = new GroupLayout(src_panel);
		gl_src_panel.setHorizontalGroup(
			gl_src_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_src_panel.createSequentialGroup()
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
		);
		gl_src_panel.setVerticalGroup(
			gl_src_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
		);
		
		JScrollPane rScroll = new JScrollPane(this.sourceArea);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(rScroll, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(rScroll, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
		);
		panel_3.setLayout(gl_panel_3);
		
		JScrollPane lScroll = new JScrollPane(this.sourceTable);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(lScroll, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addComponent(lScroll, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
		);
		panel_2.setLayout(gl_panel_2);
		src_panel.setLayout(gl_src_panel);
		
		JPanel formal_panel = new JPanel();
		tabbedPane.addTab("Formal", null, formal_panel, null);
		
		JScrollPane fLScroll = new JScrollPane(formalTable);
		
		JScrollPane fRScroll = new JScrollPane(formalArea);
		GroupLayout gl_formal_panel = new GroupLayout(formal_panel);
		gl_formal_panel.setHorizontalGroup(
			gl_formal_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_formal_panel.createSequentialGroup()
					.addComponent(fLScroll, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(fRScroll, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE))
		);
		gl_formal_panel.setVerticalGroup(
			gl_formal_panel.createParallelGroup(Alignment.TRAILING)
				.addComponent(fRScroll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
				.addComponent(fLScroll, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
		);
		formal_panel.setLayout(gl_formal_panel);
		
		JPanel crypt_panel = new JPanel();
		tabbedPane.addTab("Crypt", null, crypt_panel, null);
		
		JScrollPane leftScroll = new JScrollPane(cryptTable);
		
		JScrollPane topScroll = new JScrollPane(cryptTopArea);
		
		JScrollPane btnScroll = new JScrollPane(cryptDownArea);
		
		JButton btnNewButton_1 = new JButton("Crypt");
		btnNewButton_1.setActionCommand(crypt);
		btnNewButton_1.addActionListener(this);
		
		GroupLayout gl_crypt_panel = new GroupLayout(crypt_panel);
		gl_crypt_panel.setHorizontalGroup(
			gl_crypt_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_crypt_panel.createSequentialGroup()
					.addGroup(gl_crypt_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_1)
						.addComponent(leftScroll, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_crypt_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(topScroll, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
						.addComponent(btnScroll, GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)))
		);
		gl_crypt_panel.setVerticalGroup(
			gl_crypt_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_crypt_panel.createSequentialGroup()
					.addComponent(topScroll, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnScroll, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_crypt_panel.createSequentialGroup()
					.addComponent(leftScroll, GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_1)
					.addGap(6))
		);
		crypt_panel.setLayout(gl_crypt_panel);
		setLayout(groupLayout);

	}
	
	private JTextArea sourceArea;
	private JTable sourceTable;
	private JTextArea formalArea;
	private JTable formalTable;
	private JTabbedPane tabbedPane;
	private JTable cryptTable;
	private JTextArea cryptTopArea;
	private JTextArea cryptDownArea;

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		try {
			if(SOURCE.equals(cmd) || FORMAL.equals(cmd)) {
				JFileChooser jf = new JFileChooser();
				jf.setMultiSelectionEnabled(false);
				jf.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));
				jf.setFileSelectionMode(JFileChooser.FILES_ONLY);

				if(JFileChooser.APPROVE_OPTION == jf.showOpenDialog(this)) {
					byte [] bytes = Files.readAllBytes(jf.getSelectedFile().toPath());
					if(SOURCE.equals(cmd)) {
						this.doClear();
						srcFreq = new FreqString(bytes);
						this.sourceArea.setText(srcFreq.toString());
						for(Object [] row : srcFreq.getRowDatas())
							((DefaultTableModel)this.sourceTable.getModel()).addRow(row);
						this.tabbedPane.setSelectedIndex(0);
					} else {
						this.formalTable.setModel(this.getTableModel(false));
						fomFreq = new FreqString(bytes);
						this.formalArea.setText(fomFreq.toString());
						for(Object [] row : fomFreq.getRowDatas())
							((DefaultTableModel)this.formalTable.getModel()).addRow(row);
						this.tabbedPane.setSelectedIndex(1);
					}
				}
			} else if (CRYPT.equals(cmd)) {
				this.doUpCryptBoard();
				this.tabbedPane.setSelectedIndex(2);
			} else if (CLEAR.equals(cmd)) {
				this.doClear();
				this.tabbedPane.setSelectedIndex(0);
			} else if (crypt.equals(cmd)) {
				this.doCrypt();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void doCrypt() {
		DefaultTableModel model = (DefaultTableModel) this.cryptTable.getModel();
		int count = model.getRowCount();
		String tmpString = this.srcFreq.toString();
		
		if(count == 0) {
			JOptionPane.showMessageDialog(this, "Load the Source Text.");
			return;
		}
		
		for(int i=0; i < count; i++) {
			String to = String.valueOf(model.getValueAt(i, 4));
				
			if(null != to && !to.isEmpty()) {
				if(to.length() > 1)
					throw new RuntimeException("Please set only one char!");
				
				String from = String.valueOf(model.getValueAt(i, 0));
				tmpString = this.srcFreq.cryptString(tmpString, from.charAt(0), to.charAt(0));
			}
		}
		
		this.cryptDownArea.setText(tmpString);
	}

	private void doUpCryptBoard() {
		if(null == this.srcFreq || this.srcFreq.toString().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please Load Source Text.");
			return;
		}
		
		if(null == this.fomFreq || this.fomFreq.toString().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please Load Formal Text.");
			return;
		}
		this.cryptTable.setModel(this.getTableModel(true));
		List<FreqChar> srcList = this.srcFreq.getCharList();
		List<FreqChar> fomList = this.fomFreq.getCharList();
		
		int count = (srcList.size() < fomList.size()) ? fomList.size(): srcList.size();
		
		for(int i=0; i<count; i++) {
			Object [] row = new Object [5];
			if(i >= srcList.size()) {
				row[0] = "";
				row[1] = "";
			} else {
				Object [] tmp = srcFreq.getRowData(srcList.get(i));
				row[0] = tmp[0];
				row[1] = tmp[2];
			}
			if(i >= fomList.size()) {
				row[2] = "";
				row[3] = "";
			} else {
				Object [] tmp = fomFreq.getRowData(fomList.get(i));
				row[2] = tmp[0];
				row[3] = tmp[2];
			}
			row [4] = "";
			((DefaultTableModel)this.cryptTable.getModel()).addRow(row);
		}
		cryptTopArea.setText(this.srcFreq.toString());
	}
	
	private void doClear() {
		this.fomFreq = null;
		this.srcFreq = null;
		this.formalArea.setText("");
		this.sourceArea.setText("");
		this.formalTable.setModel(this.getTableModel(false));
		this.sourceTable.setModel(this.getTableModel(false));
		this.cryptTopArea.setText("");
		this.cryptDownArea.setText("");
		this.cryptTable.setModel(this.getTableModel(true));
	}
	
	private TableModel getTableModel(boolean crypt) {
		Object [] columns = {"char","count","  %  "};
		Object [] columns2 = {"Src","%","Nom","%","Set"};
		if(crypt)
			return new DefaultTableModel(columns2, 0);
		return new DefaultTableModel(columns, 0);
	}
}
