package parlament.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Dimension;
import javax.swing.BoxLayout;

public class ParlamentGUI extends JFrame {

	private JPanel contentPane;
	private JPanel eastPanel;
	private JPanel southPanel;
	private JScrollPane scrollTable;
	private JTable table;
	private JButton btnGetMembers;
	private JButton btnFillTable;
	private JButton btnUpdateMembers;
	private JScrollPane scrollStatus;
	private JTextArea txtStatus;
	
	/**
	 * Create the frame.
	 */
	public ParlamentGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUIKontroler.ugasiAplikaciju();
			}
		});
		setTitle("ParlamentMembers");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 647, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getEastPanel(), BorderLayout.EAST);
		contentPane.add(getSouthPanel(), BorderLayout.SOUTH);
		contentPane.add(getScrollTable(), BorderLayout.CENTER);
	}

	private JPanel getEastPanel() {
		if (eastPanel == null) {
			eastPanel = new JPanel();
			eastPanel.setLayout(new MigLayout("", "[97px]", "[23px][][]"));
			eastPanel.add(getBtnGetMembers(), "cell 0 0,grow");
			eastPanel.add(getBtnFillTable(), "cell 0 1,grow");
			eastPanel.add(getBtnUpdateMembers(), "cell 0 2,grow");
		}
		return eastPanel;
	}
	private JPanel getSouthPanel() {
		if (southPanel == null) {
			southPanel = new JPanel();
			southPanel.setPreferredSize(new Dimension(10, 90));
			southPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "STATUS", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
			southPanel.setLayout(new BorderLayout(0, 0));
			southPanel.add(getScrollPane_2(), BorderLayout.CENTER);
		}
		return southPanel;
	}
	private JScrollPane getScrollTable() {
		if (scrollTable == null) {
			scrollTable = new JScrollPane();
			scrollTable.setViewportView(getTable());
		}
		return scrollTable;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			table.setFillsViewportHeight(true);
		}
		return table;
	}
	private JButton getBtnGetMembers() {
		if (btnGetMembers == null) {
			btnGetMembers = new JButton("GET members");
		}
		return btnGetMembers;
	}
	private JButton getBtnFillTable() {
		if (btnFillTable == null) {
			btnFillTable = new JButton("Fill table");
		}
		return btnFillTable;
	}
	private JButton getBtnUpdateMembers() {
		if (btnUpdateMembers == null) {
			btnUpdateMembers = new JButton("Update members");
		}
		return btnUpdateMembers;
	}
	private JScrollPane getScrollPane_2() {
		if (scrollStatus == null) {
			scrollStatus = new JScrollPane();
			scrollStatus.setAutoscrolls(true);
			scrollStatus.setViewportView(getTxtStatus());
		}
		return scrollStatus;
	}
	private JTextArea getTxtStatus() {
		if (txtStatus == null) {
			txtStatus = new JTextArea();
			txtStatus.setLineWrap(true);
			txtStatus.setWrapStyleWord(true);
		}
		return txtStatus;
	}
}
