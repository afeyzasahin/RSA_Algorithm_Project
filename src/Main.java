

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.math.BigInteger;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Main extends JFrame {

	public static void main(String[] args) {

		/*
		 * JFrame fsa=new JFrame(); Image icon = Toolkit.getDefaultToolkit().getImage(
		 * "C:\\Users\\ayfer\\OneDrive\\Desktop\\cryptographyy.jpg");
		 * fsa.setIconImage(icon); fsa.setLayout(null); fsa.setSize(200,200);
		 * fsa.setVisible(true);
		 */

		JLabel labelT = new JLabel("CMPE 325 Assignment 4 RSA Public-Key Cryptosystem");
		JLabel labelM = new JLabel(
				"First, create a key by clicking the \"Create Key Pair\" button. Choose your key and start the process.");
		JFrame f = new JFrame("RSA public-key cryptosystem");
		labelM.setMaximumSize(new Dimension(150,75));
		
		JLabel labelE = new JLabel("Encrypted Text");
		JLabel labelD = new JLabel("Decrypted Text");

      
        
		f.setSize(800, 800);
		f.getContentPane().setBackground(Color.white);
		labelM.setFont(new Font("Verdana", Font.BOLD, 12));
		labelT.setFont(new Font("Verdana", Font.BOLD, 16));

		KeyGeneration creatKey = new KeyGeneration();
		RSA_Algorithm RSAalgo = new RSA_Algorithm();

		JPanel KeyPairPanel = new JPanel();
		JPanel EncryptPanel = new JPanel();
		JPanel DecryptPanel = new JPanel();

		// setBounds(int x-coordinate, int y-coordinate, int width, int height)

		labelM.setBounds(25, 60, 900, 30);
		labelT.setBounds(120, 0, 900, 30);
		labelE.setBounds(50, 300, 900, 30);
		labelD.setBounds(400, 300, 900, 30);
		KeyPairPanel.setBounds(5, 120, 750, 145);
		EncryptPanel.setBounds(50, 350, 300, 400);
		DecryptPanel.setBounds(400, 350, 300, 400);

		labelM.setForeground(Color.blue);
		labelT.setForeground(Color.orange);
		labelE.setForeground(Color.magenta);
		labelD.setForeground(Color.pink);
		KeyPairPanel.setBackground(Color.cyan);
		EncryptPanel.setBackground(Color.magenta);
		DecryptPanel.setBackground(Color.pink);

		Vector<AllKeys> keypairs = new Vector<AllKeys>();
		JList<AllKeys> listOfKeys = new JList<AllKeys>(keypairs);
		listOfKeys.setFont(new Font("Verdana", Font.BOLD, 12));
		JScrollPane scrollKeys = new JScrollPane(listOfKeys);
		JButton KeyGeneration = new JButton("Create Key Pair");
		KeyGeneration.setForeground(Color.blue);

		KeyPairPanel.add(KeyGeneration);
		KeyPairPanel.add(scrollKeys);
		JTextArea Karea = new JTextArea(30, 30);
		Karea.setLineWrap(true);

		JButton Encryption = new JButton("Encrypt");
		Encryption.setForeground(Color.magenta);
		EncryptPanel.add(Encryption);
		JTextArea Earea = new JTextArea(24, 20);
		Earea.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(Earea);
		EncryptPanel.add(scroll);

		JButton Decryption = new JButton("Decrypt");
		Decryption.setForeground(Color.pink);
		DecryptPanel.add(Decryption);
		JTextArea Darea = new JTextArea(24, 20);
		Darea.setLineWrap(true);
		JScrollPane Dscroll = new JScrollPane(Darea);
		DecryptPanel.add(Dscroll);

		f.add(labelM);
		f.add(labelE);
		f.add(labelD);
		f.add(labelT);
		f.add(KeyPairPanel);
		f.add(EncryptPanel);
		f.add(DecryptPanel);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(null);
		f.setVisible(true);



//----------------------------------------------------------------------------------------------------------------
		
		Decryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Earea.updateUI();
				Earea.setText(null);
				
				
				String CipherTxt;
				String PlainText;
				AllKeys keys;
				
				keys = listOfKeys.getSelectedValue();
				CipherTxt= Darea.getText();
				PlainText = RSAalgo.Decryption(CipherTxt, keys);
				Earea.setText(PlainText);
				
				

			}

		});
		
//------------------------------------------------------------------------------------------------------------------		
		Encryption.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String CipherText;
				String PlainTxt;
				AllKeys keys;
				
				Darea.setText(null);
				Darea.updateUI();
				
				PlainTxt = Earea.getText();	
				
				keys = listOfKeys.getSelectedValue();
				CipherText = RSAalgo.Encryption(PlainTxt, keys);
				Darea.setText(CipherText);

			}
		});

//------------------------------------------------------------------------------------------------------------------
		
		KeyGeneration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AllKeys allPairs;
				KeyPairPanel.updateUI();
				scrollKeys.updateUI();
				listOfKeys.updateUI();

				allPairs = creatKey.CreateKeys();
				keypairs.add(allPairs);

			}
		});
//------------------------------------------------------------------------------------------------------------------

	}

}