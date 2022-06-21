//all example codes

import java.math.BigInteger;
import java.util.Random;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;

public class Test extends JFrame {
	private JButton button1, button2;
	private JLabel label;

	int primeSize;
	BigInteger p, q;
	BigInteger N;
	BigInteger r;
	BigInteger E, D;

	public Test(int primeSize) {
		this.primeSize = primeSize;
		// Generate two distinct large prime numbers p and q.
		generatePrimeNumbers();
		// Generate Public and Private Keys.
		generatePublicPrivateKeys();
		this.setSize(300,300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Click event");
	    this.setLayout(null);
	    Clicklistener click = new Clicklistener();
	    button1 = new JButton ("Create Key Pair");
	    button1.setBounds(0,125,125,40);
	    button1.addActionListener(click);
	    button2 = new JButton ("Exit");
	    button2.setBounds(150,125,125,40);
	    //button2.setBounds(75,50,50,20);
	    button2.addActionListener(click);
	    
	    label = new JLabel();
	    label.setBounds(80,200,200,20);
	    
	    this.add(button1);
	    this.add(button2);
	    this.add(label);
	    //this.setVisible(true);
	}

	public void generatePrimeNumbers() {
		p = new BigInteger(primeSize, 10, new Random());

		do {
			q = new BigInteger(primeSize, 10, new Random());
		} while (q.compareTo(p) == 0);
	}

	public void generatePublicPrivateKeys() {
		// N = p * q
		N = p.multiply(q);
		// r = ( p – 1 ) * ( q – 1 )
		r = p.subtract(BigInteger.valueOf(1));
		r = r.multiply(q.subtract(BigInteger.valueOf(1))); // (p-1)(q-1)

		// Choose E, coprime to and less than r
		do {
			E = new BigInteger(2 * primeSize, new Random());
		} while ((E.compareTo(r) != -1) || (E.gcd(r).compareTo(BigInteger.valueOf(1)) != 0));

		// Compute D, the inverse of E mod r
		D = E.modInverse(r);

	}

	public BigInteger getp() {
		return (p);
	}

	public BigInteger getq() {
		return (q);
	}

	public BigInteger getr() {
		return (r);
	}

	public BigInteger getN() {
		return (N);
	}

	public BigInteger getE() {
		return (E);
	}

	public BigInteger getD() {
		return (D);
	}
	

	  private class Clicklistener implements ActionListener
	  {
		  
		  
	    public void actionPerformed(ActionEvent e)
	    {
	      if (e.getSource() == button1)
	      {
	    	  
	        //label.setText("Public Key (e,n): " + akg.publicKey);
	        label.setText("Public Key (e,n): ");
	      }
	      
	      if (e.getSource() == button2)
	      {
	    	  System.exit(0);
	        //label.setText("Button2 is clicked!");
	      }
	    }
	  }
	  
	   public static BigInteger encrypt(String message, BigInteger e, BigInteger n)
	    {
	        byte messageArr[] = message.getBytes();
	        BigInteger m = new BigInteger(messageArr);
	        BigInteger c = m.modPow(e, n);
	        return c;
	    }

	    public static void decrypt(BigInteger c, BigInteger d, BigInteger n)
	    {
	        BigInteger answer = c.modPow(d, n);
	        byte newarr[] = answer.toByteArray();
	        for (int i = 0; i < newarr.length; i++)
	            System.out.print((char)newarr[i]);
	        System.out.print("\n");
	    }

}