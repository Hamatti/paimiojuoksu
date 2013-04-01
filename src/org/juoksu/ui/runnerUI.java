package org.juoksu.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;

import org.juoksu.run.MainClass;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class runnerUI {

	private JFrame frame;
	private JTextField txtNumero;
	private JTextField txtNimi;
	private JTextField txtSeura;
	private JTabbedPane tabbedPane;
	private File file;
	
	private MainClass mainclass;
	private JTextField add_nro_field;
	private JTextField add_time_field;
	private JLabel added_result_label;
	private JLabel added_runner_label; 
	private JPanel panel, panel_1, panel_2;
	private JLabel file_menu_label;
	private final ButtonGroup file_menu = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					runnerUI window = new runnerUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public runnerUI() {
		initialize();
	}
	
	public File newCompetition() {
		JFileChooser fc = new JFileChooser();
		int return_val = fc.showSaveDialog(this.frame);
		if (return_val == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            if (!file.isFile()) {
            	try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            return file;
            
		}
		mainclass = new MainClass();
		mainclass.setUp("39. Paimio-juoksu");
		return null;
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 886, 595);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("Tiedosto");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("Uusi kilpailu");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newCompetition();
				if (file != null) {
					tabbedPane.addTab("Lis‰‰ juoksija", null, panel, null);
					tabbedPane.addTab("Lis‰‰ tulos", null, panel_1, null);
					if(tabbedPane.getTabCount() == 3) {
						tabbedPane.setSelectedIndex(1);
						tabbedPane.remove(0);
					}
					file_menu_label.setText(file.getName() + "   ");
				}
				
				
			}

			
		});
		
		
		
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Avaa");
		mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		file_menu.add(mntmOpen);
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainclass = new MainClass();
				mainclass.setUp("39. Paimio-juoksu");
				JFileChooser fc = new JFileChooser();
				int chosen = fc.showOpenDialog(frame);
				if(chosen == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					mainclass.readFromFile(file);
					tabbedPane.addTab("Lis‰‰ juoksija", null, panel, null);
					tabbedPane.addTab("Lis‰‰ tulos", null, panel_1, null);
					if(tabbedPane.getTabCount() == 3) {
						tabbedPane.setSelectedIndex(1);
						tabbedPane.remove(0);
					}
					file_menu_label.setText(file.getName() + "   ");
				}
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Tallenna");
		file_menu.add(mntmSave);
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainclass.saveToFile(file);
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
				
			}
		});
		mnFile.add(mntmSave);
		
		JMenuItem mntmExit = new JMenuItem("Lopeta");
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		file_menu.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(frame, "Haluatko varmasti lopettaa?", "Lopetus", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					System.exit(1);
				}
			}
		});
		
		mnFile.add(mntmExit);
		
		JMenu mnTuo = new JMenu("Tuo");
		menuBar.add(mnTuo);
		
		JMenuItem mntmTuoSarjatTiedostosta = new JMenuItem("Tuo sarjat tiedostosta");
		mntmTuoSarjatTiedostosta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int chosen = fc.showOpenDialog(frame);
				
				if (chosen == JFileChooser.APPROVE_OPTION) {
					String filename = fc.getSelectedFile().getAbsolutePath();
					mainclass.readSeriesFromFile(filename);	
				}
				
				
			}
		});
		mnTuo.add(mntmTuoSarjatTiedostosta);
		
		JMenuItem mntmTuoJuoksijatTiedostosta = new JMenuItem("Tuo juoksijat tiedostosta");
		mntmTuoJuoksijatTiedostosta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int chosen = fc.showOpenDialog(frame);
				
				if (chosen == JFileChooser.APPROVE_OPTION) {
					String filename = fc.getSelectedFile().getAbsolutePath();
					mainclass.readRunnersFromFile(filename);	
				}
			}
		});
		mnTuo.add(mntmTuoJuoksijatTiedostosta);
		
		JMenu mnView = new JMenu("Generoi");
		menuBar.add(mnView);
		
		JMenuItem mntmHtml = new JMenuItem("HTML");
		mntmHtml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mainclass != null) mainclass.writeHTML();
			}
		});
		mnView.add(mntmHtml);
		
		JMenu mnAbout = new JMenu("Apua");
		menuBar.add(mnAbout);
		
		JMenuItem mntmAbout = new JMenuItem("Tietoa ohjelmasta");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Paimio-juoksutulospalvelu v0.2\nOhjelmiston on kehitt‰nyt Paimion kaupungin tarpeisiin Juha-Matti Santala / jumasan@utu.fi.\n");
			}
		});
		mnAbout.add(mntmAbout);
		
		file_menu_label = new JLabel("");
		file_menu_label.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(file_menu_label);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "name_788822633591401");
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Alku", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblAloitaLuomallaUusi = new JLabel("Aloita luomalla uusi kilpailu tai avaamalla vanha Tiedosto-valikosta");
		lblAloitaLuomallaUusi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAloitaLuomallaUusi.setBounds(37, 40, 758, 253);
		panel_2.add(lblAloitaLuomallaUusi);
		
		panel = new JPanel();
		
		panel.setLayout(null);
		
		JLabel lblNumero = new JLabel("NRO #");
		lblNumero.setBounds(198, 60, 52, 14);
		panel.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(199, 76, 52, 20);
		txtNumero.setToolTipText("numero");
		panel.add(txtNumero);
		txtNumero.setColumns(10);
		
		txtNimi = new JTextField();
		txtNimi.setBounds(261, 76, 86, 20);
		panel.add(txtNimi);
		txtNimi.setColumns(10);
		
		txtSeura = new JTextField();
		txtSeura.setBounds(368, 76, 86, 20);
		panel.add(txtSeura);
		txtSeura.setColumns(10);
		
		JButton btnLisJuoksija = new JButton("Lis\u00E4\u00E4 juoksija");
		btnLisJuoksija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				added_runner_label.setText(null);
				if (txtNumero.getText().isEmpty() || txtNimi.getText().isEmpty() || txtSeura.getText().isEmpty()) {
					added_runner_label.setText("T‰yt‰ kaikki tiedot");
				}
				else {
					mainclass.addRunner(txtNumero.getText()+","+txtNimi.getText()+","+txtSeura.getText());
					added_runner_label.setText("Juoksija lis‰tty. " + txtNumero.getText() + " " + txtNimi.getText() + " " + txtSeura.getText());
				}
				
				txtNumero.setText(null);
				txtNimi.setText(null);
				txtSeura.setText(null);
			}
		});
		btnLisJuoksija.setBounds(464, 75, 194, 23);
		panel.add(btnLisJuoksija);
		
		JLabel lblNimi = new JLabel("Nimi");
		lblNimi.setBounds(260, 60, 87, 14);
		panel.add(lblNimi);
		
		JLabel lblSeura = new JLabel("Seura");
		lblSeura.setBounds(368, 60, 86, 14);
		panel.add(lblSeura);
		
		JLabel lblLisJuoksija = new JLabel("LIS\u00C4\u00C4 JUOKSIJA");
		lblLisJuoksija.setHorizontalAlignment(SwingConstants.CENTER);
		lblLisJuoksija.setFont(new Font("Shonar Bangla", Font.BOLD, 24));
		lblLisJuoksija.setBounds(30, 11, 239, 41);
		panel.add(lblLisJuoksija);
		
		added_runner_label = new JLabel("");
		added_runner_label.setFont(new Font("Arial", Font.BOLD, 15));
		added_runner_label.setBounds(103, 167, 555, 76);
		panel.add(added_runner_label);
		
			
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Lis\u00E4\u00E4 tulos");
		lblNewLabel.setFont(new Font("Shonar Bangla", Font.PLAIN, 55));
		lblNewLabel.setBounds(10, 11, 300, 87);
		panel_1.add(lblNewLabel);
		
		add_nro_field = new JTextField();
		add_nro_field.setBounds(72, 143, 86, 20);
		panel_1.add(add_nro_field);
		add_nro_field.setColumns(10);
		
		add_time_field = new JTextField();
		add_time_field.setBounds(193, 143, 86, 20);
		panel_1.add(add_time_field);
		add_time_field.setColumns(10);
		
		added_result_label = new JLabel("");
		added_result_label.setFont(new Font("Shonar Bangla", Font.PLAIN, 13));
		added_result_label.setBounds(72, 205, 207, 61);
		panel_1.add(added_result_label);
		
		JButton addTimeBtn = new JButton("LIS\u00C4\u00C4");
		addTimeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				added_result_label.setText(null);
				added_result_label.setFont(new Font("arial", Font.BOLD, 15));
				try {
					if(add_nro_field.getText().equals("") || add_time_field.getText().equals("")) {
						added_result_label.setText("T‰yt‰ numero ja aika!");
						return;
					}
					mainclass.addTime(Integer.parseInt(add_nro_field.getText()), add_time_field.getText());
					
					added_result_label.setText("Tulos lis‰tty: " + add_nro_field.getText() + ": " + add_time_field.getText());
				}
				catch(NullPointerException exception) {
					added_result_label.setText(exception.getMessage());
				}
				
				add_nro_field.setText(null);
				add_time_field.setText(null);
			}
		});
		addTimeBtn.setBounds(321, 108, 95, 55);
		panel_1.add(addTimeBtn);
		
		JLabel add_number_label = new JLabel("NRO");
		add_number_label.setFont(new Font("Shonar Bangla", Font.PLAIN, 13));
		add_number_label.setHorizontalAlignment(SwingConstants.CENTER);
		add_number_label.setBounds(72, 109, 86, 34);
		panel_1.add(add_number_label);
		
		JLabel add_time_label = new JLabel("Aika (h.m,s)");
		add_time_label.setFont(new Font("Shonar Bangla", Font.PLAIN, 13));
		add_time_label.setHorizontalAlignment(SwingConstants.CENTER);
		add_time_label.setBounds(193, 109, 86, 34);
		panel_1.add(add_time_label);
		
		
	}
}
