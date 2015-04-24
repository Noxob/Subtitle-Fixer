import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Color;


public class Program extends JFrame {

	private JPanel contentPane;
	private JButton button1;
	private JButton button2;
	private JTextArea textArea1;
	private boolean selected = false;
	private final JFileChooser fc = new JFileChooser();
	private File returnVal;
	private int mode = 1;
	private JRadioButton rdbtnDelay;
	private JRadioButton rdbtnPostpone;
	private JLabel lblChooseTheSubtitle;
	private JButton btnFixIt;
	BufferedReader br;
	private JTextField textField;
	private JLabel lblHowManySeconds;
	private int action = 0;
	private JFrame warning;
	private FileWriter saveFile;
	private String output = "";
	private JLabel label;
	private JLabel label_1;
	private JLabel lblOutputFileWill;
	private JLabel lblContainsYourSubtitle;
	private JLabel label_2;
	private JLabel lblNewLabel;
	private JLabel lblSubtitleIsFixed;
	private JLabel lblChooseASubtitle;
	private JLabel lblTheFileYou;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Program() {
		
		this.setTitle("Subtitle Fixer");
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 300);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 1, 2, 1));
//		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{125, 0, 128, 59, 0, 0};
		gbl_contentPane.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		ListenForButton lForButton = new ListenForButton();
		
		JLabel lblWelcomeToSubtitle = new JLabel("Welcome To Subtitle Fixer!");
		GridBagConstraints gbc_lblWelcomeToSubtitle = new GridBagConstraints();
		gbc_lblWelcomeToSubtitle.anchor = GridBagConstraints.WEST;
		gbc_lblWelcomeToSubtitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblWelcomeToSubtitle.gridx = 1;
		gbc_lblWelcomeToSubtitle.gridy = 0;
		contentPane.add(lblWelcomeToSubtitle, gbc_lblWelcomeToSubtitle);
		
		rdbtnDelay = new JRadioButton("Delay Subtitles");
		GridBagConstraints gbc_rdbtnDelay = new GridBagConstraints();
		gbc_rdbtnDelay.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnDelay.gridx = 0;
		gbc_rdbtnDelay.gridy = 2;
		contentPane.add(rdbtnDelay, gbc_rdbtnDelay);
		
		rdbtnPostpone = new JRadioButton("Pull back Subtitles");
		GridBagConstraints gbc_rdbtnPostpone = new GridBagConstraints();
		gbc_rdbtnPostpone.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnPostpone.gridx = 1;
		gbc_rdbtnPostpone.gridy = 2;
		contentPane.add(rdbtnPostpone, gbc_rdbtnPostpone);
		rdbtnPostpone.addActionListener(lForButton);
		
		lblHowManySeconds = new JLabel("How many seconds you want to delay/pull-back :");
		GridBagConstraints gbc_lblHowManySeconds = new GridBagConstraints();
		gbc_lblHowManySeconds.insets = new Insets(0, 0, 5, 5);
		gbc_lblHowManySeconds.anchor = GridBagConstraints.EAST;
		gbc_lblHowManySeconds.gridx = 0;
		gbc_lblHowManySeconds.gridy = 5;
		contentPane.add(lblHowManySeconds, gbc_lblHowManySeconds);
		
		textField = new JTextField();
		textField.setText("0");
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 5;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		label = new JLabel(" ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 7;
		contentPane.add(label, gbc_label);
		
		label_1 = new JLabel(" ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 8;
		contentPane.add(label_1, gbc_label_1);
		
		lblChooseTheSubtitle = new JLabel("Choose the subtitle file you want to fix:");
		GridBagConstraints gbc_lblChooseTheSubtitle = new GridBagConstraints();
		gbc_lblChooseTheSubtitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseTheSubtitle.gridx = 0;
		gbc_lblChooseTheSubtitle.gridy = 9;
		contentPane.add(lblChooseTheSubtitle, gbc_lblChooseTheSubtitle);
		
		button1 = new JButton("Browse");
		
		GridBagConstraints gbc_button1 = new GridBagConstraints();
		gbc_button1.insets = new Insets(0, 0, 5, 5);
		gbc_button1.anchor = GridBagConstraints.NORTHWEST;
		gbc_button1.gridx = 1;
		gbc_button1.gridy = 9;
		contentPane.add(button1, gbc_button1);
		button1.addActionListener(lForButton);
		
		label_2 = new JLabel(" ");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 10;
		contentPane.add(label_2, gbc_label_2);
		
		lblTheFileYou = new JLabel("The file you wanted to fix may have been moved");
		lblTheFileYou.setVisible(false);
		lblTheFileYou.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_lblTheFileYou = new GridBagConstraints();
		gbc_lblTheFileYou.insets = new Insets(0, 0, 5, 5);
		gbc_lblTheFileYou.gridx = 1;
		gbc_lblTheFileYou.gridy = 10;
		contentPane.add(lblTheFileYou, gbc_lblTheFileYou);
		
		lblNewLabel = new JLabel(" ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 11;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		lblSubtitleIsFixed = new JLabel("Subtitle is fixed!");
		lblSubtitleIsFixed.setVisible(false);
		
		lblChooseASubtitle = new JLabel("Choose a subtitle file first!");
		lblChooseASubtitle.setVisible(false);
		lblChooseASubtitle.setForeground(new Color(255, 0, 0));
		GridBagConstraints gbc_lblChooseASubtitle = new GridBagConstraints();
		gbc_lblChooseASubtitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseASubtitle.gridx = 1;
		gbc_lblChooseASubtitle.gridy = 11;
		contentPane.add(lblChooseASubtitle, gbc_lblChooseASubtitle);
		
		lblSubtitleIsFixed.setForeground(new Color(124, 252, 0));
		GridBagConstraints gbc_lblSubtitleIsFixed = new GridBagConstraints();
		gbc_lblSubtitleIsFixed.insets = new Insets(0, 0, 5, 5);
		gbc_lblSubtitleIsFixed.gridx = 1;
		gbc_lblSubtitleIsFixed.gridy = 12;
		contentPane.add(lblSubtitleIsFixed, gbc_lblSubtitleIsFixed);
		
		btnFixIt = new JButton("Fix It!");
		GridBagConstraints gbc_btnFixIt = new GridBagConstraints();
		gbc_btnFixIt.insets = new Insets(0, 0, 5, 5);
		gbc_btnFixIt.gridx = 1;
		gbc_btnFixIt.gridy = 13;
		contentPane.add(btnFixIt, gbc_btnFixIt);
		btnFixIt.addActionListener(lForButton);
		
		lblOutputFileWill = new JLabel("Output file will be in the directory that");
		GridBagConstraints gbc_lblOutputFileWill = new GridBagConstraints();
		gbc_lblOutputFileWill.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutputFileWill.gridx = 1;
		gbc_lblOutputFileWill.gridy = 14;
		contentPane.add(lblOutputFileWill, gbc_lblOutputFileWill);
		
		lblContainsYourSubtitle = new JLabel("contains your subtitle file.");
		GridBagConstraints gbc_lblContainsYourSubtitle = new GridBagConstraints();
		gbc_lblContainsYourSubtitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblContainsYourSubtitle.gridx = 1;
		gbc_lblContainsYourSubtitle.gridy = 15;
		contentPane.add(lblContainsYourSubtitle, gbc_lblContainsYourSubtitle);
		
		File returnVal = fc.getSelectedFile();
		
		rdbtnDelay.addActionListener(lForButton);
		
		
		
		
		
	}
	
	

	
	
	
	//implement listeners
	
	private class ListenForButton implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == button1)
			{
				fc.showOpenDialog(rootPane);
				returnVal = fc.getSelectedFile();
				if(returnVal != null)
				{
					lblChooseASubtitle.setVisible(false);
					selected = true;
					try {
						saveFile = new FileWriter(returnVal.getParentFile().toString() + "\\output.srt");
					} catch (IOException e1) {
						e1.printStackTrace();
					}

				}
				
			}
			if(e.getSource() == rdbtnDelay)
			{
				mode = 1;
				rdbtnPostpone.setSelected(false);
			}
			if(e.getSource() == rdbtnPostpone)
			{
				mode = 2;
				rdbtnDelay.setSelected(false);
			}
			
			if(e.getSource() == btnFixIt)
			{
				action = Integer.parseInt(textField.getText());
				if(selected)
				{
					
					 try {
						br = new BufferedReader(new FileReader(returnVal));
					} catch (FileNotFoundException e1) {
						
						e1.printStackTrace();
					}
					
					 if(mode == 1)
					 {
						 try {
							modeOne(action);
						} catch (IOException e1) {
							lblTheFileYou.setVisible(true);
							e1.printStackTrace();
						}
					 }
					 else if(mode == 2)
					 {
						 try {
								modeTwo(action);
							} catch (IOException e1) {
								lblTheFileYou.setVisible(true);
								e1.printStackTrace();
							}
					 }
					 
					 
				}
				else{
					lblChooseASubtitle.setVisible(true);
				}
				
			}
			
				
			
			
		}
		
	}
	
	
	public void modeOne(int delay) throws IOException
	{
		for (String str = br.readLine(); str != null; str = br.readLine()) {
			
			if(str.contains("-->")){
				output = output + delayDeneme(delay,str) + "\n";
			}
			else
			{
				output = output + str + "\n";
			}
		}
		
		br.close();
		saveFile();
	}
	
	public void modeTwo(int delay) throws IOException
	{
		for (String str = br.readLine(); str != null; str = br.readLine()) {
			
			if(str.contains("-->")){
				output = output + pullDeneme(delay,str) + "\n";
			}
			else
			{
				output = output + str + "\n";
			}
		}
		
		br.close();
		
		saveFile();
	}
	
	
	public static String delayDeneme(int x, String y)
	{
		int hr1,hr2,min1,min2,sec1,sec2,mil1,mil2,mdif1,hdif1,mdif2,hdif2;
		char[] temp = y.toCharArray();
		String h1,h2,m1,m2,s1,s2,res,ml1,ml2;
		
		h1= Character.toString(temp[0]) + Character.toString(temp[1]);
		hr1 = Integer.parseInt(h1);
		h2 = h1= Character.toString(temp[17]) + Character.toString(temp[18]);
		hr2 = Integer.parseInt(h2);
		m1 = h1= Character.toString(temp[3]) + Character.toString(temp[4]);
		min1 = Integer.parseInt(m1);
		m2 = h1= Character.toString(temp[20]) + Character.toString(temp[21]);
		min2 = Integer.parseInt(m2);
		s1 = h1= Character.toString(temp[6]) + Character.toString(temp[7]);
		sec1 = Integer.parseInt(s1);
		s2 = h1= Character.toString(temp[23]) + Character.toString(temp[24]);
		sec2 = Integer.parseInt(s2);
		ml1 = Character.toString(temp[9]) + Character.toString(temp[10])+ Character.toString(temp[11]);
		mil1 = Integer.parseInt(ml1);
		ml2 = Character.toString(temp[26]) + Character.toString(temp[27])+ Character.toString(temp[28]);
		mil2 = Integer.parseInt(ml2);
		
		// 1. KISIM ÝÇÝN KOD //////////////////////////////////////
		////////////////////////////////////////////////////////////
		if(x + sec1 > 60)//saniye sýnýrýný geçiyosa
		{
			mdif1 = (sec1 + x) / 60;
			sec1 = (sec1 + x) - (mdif1 * 60);
			if((min1 + mdif1)>60)
			{
				hdif1 = (min1 + mdif1)/60;
				min1 = (min1 + mdif1) - (hdif1*60);
				hr1 += hdif1;
			}
			else
			{
				min1 += mdif1;
			}
		}
		else
		{
			sec1 += x; // saniye 60 ý geçmiyorsa
		}
		
		// 2. KISIM ÝÇÝN KOD //////////////////////////////////////
		////////////////////////////////////////////////////////////
		if(x + sec2 > 60)//saniye sýnýrýný geçiyosa
		{
			mdif2 = (sec2 + x) / 60;
			sec2 = (sec2 + x) - (mdif2 * 60);
			if((min2 + mdif2)>60)
			{
				hdif2 = (min2 + mdif2)/60;
				min2 = (min2 + mdif2) - (hdif2*60);
				hr2 += hdif2;
			}
			else
			{
				min2 += mdif2;
			}
		}
		else
		{
			sec2 += x; // saniye 60 ý geçmiyorsa
		}
		
		res = Integer.toString(hr1) + ":" + min1 + ":" + sec1 + "," + mil1 + " --> " + hr2 + ":" + min2 +  ":" + sec2 +  "," + mil2;
		
		return res;
	}
	
	public static String pullDeneme(int x, String y)
	{
		int hr1,hr2,min1,min2,sec1,sec2,mil1,mil2,mdif1,hdif1,mdif2,hdif2;
		char[] temp = y.toCharArray();
		String h1,h2,m1,m2,s1,s2,res,ml1,ml2;
		
		h1= Character.toString(temp[0]) + Character.toString(temp[1]);
		hr1 = Integer.parseInt(h1);
		h2 = h1= Character.toString(temp[17]) + Character.toString(temp[18]);
		hr2 = Integer.parseInt(h2);
		m1 = h1= Character.toString(temp[3]) + Character.toString(temp[4]);
		min1 = Integer.parseInt(m1);
		m2 = h1= Character.toString(temp[20]) + Character.toString(temp[21]);
		min2 = Integer.parseInt(m2);
		s1 = h1= Character.toString(temp[6]) + Character.toString(temp[7]);
		sec1 = Integer.parseInt(s1);
		s2 = h1= Character.toString(temp[23]) + Character.toString(temp[24]);
		sec2 = Integer.parseInt(s2);
		ml1 = Character.toString(temp[9]) + Character.toString(temp[10])+ Character.toString(temp[11]);
		mil1 = Integer.parseInt(ml1);
		ml2 = Character.toString(temp[26]) + Character.toString(temp[27])+ Character.toString(temp[28]);
		mil2 = Integer.parseInt(ml2);
		
		// 1. KISIM ÝÇÝN KOD //////////////////////////////////////
		////////////////////////////////////////////////////////////
		if(sec1 - x < 0)//saniye sýnýrýný geçiyosa
		{
			mdif1 = ((sec1 - x) / 60)-1;
			sec1 = (sec1 - x) - (mdif1 * 60);
			if((min1 + mdif1)<0)
			{
				hdif1 = ((min1 + mdif1)/60)-1;
				min1 = (min1 + mdif1) + (hdif1*60);
				hr1 += hdif1;
			}
			else
			{
				min1 += mdif1;
			}
		}
		else
		{
			sec1 -= x; // saniye 60 ý geçmiyorsa
		}
		
		// 2. KISIM ÝÇÝN KOD //////////////////////////////////////
		////////////////////////////////////////////////////////////
		if(sec2 - x < 0)//saniye sýnýrýný geçiyosa
		{
			mdif2 = ((sec2 - x) / 60)-1;
			sec2 = (sec2 - x) - (mdif2 * 60);
			if((min2 + mdif2)<0)
			{
				hdif2 = ((min2 + mdif2)/60)-1;
				min2 = (min2 + mdif2) + (hdif2*60);
				hr2 += hdif2;
			}
			else
			{
				min2 += mdif2;
			}
		}
		else
		{
			sec2 -= x; // saniye 60 ý geçmiyorsa
		}
		
		res = Integer.toString(hr1) + ":" + min1 + ":" + sec1 + "," + mil1 + " --> " + hr2 + ":" + min2 +  ":" + sec2 +  "," + mil2;
		
		return res;
	}
	
	
	public void saveFile() throws IOException
	{
		saveFile.write(output);
		
		saveFile.close();
		
		lblSubtitleIsFixed.setVisible(true);
	}

	
	

}
