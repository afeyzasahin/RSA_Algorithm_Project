

import java.math.BigInteger;
public class RSA_Algorithm {
	
//*************************************************************************************************************************************
	
    public  String TEXTtoASCII(String PLAIN_TXT){
        String Plain_Txt = "";
        int ptxt;
        ptxt = PLAIN_TXT.length() ;
        for(int i = 0; i < ptxt ; i++){
        	Plain_Txt +=  (int)PLAIN_TXT.charAt(i)+100;
        }

        return Plain_Txt;
    }

 //*************************************************************************************************************************************
    
	public String ASCII_to_TEXT(String PLAIN_TXT) {

		int PlainTxtLength;
		PlainTxtLength = PLAIN_TXT.length();
		int x = 3;
		String text = "";
	
		for (int i = 0; i <= PlainTxtLength - 3; i = i+3) {
			text = text + (char) (Integer.parseInt(PLAIN_TXT.substring(i, x)) - 100);
			x = x + 3;
		}

		return text;

	}
//*************************************************************************************************************************************	
	public String[] BlocksForASCII(String ASCIIval) {
		
		String ECBlocks[];
		int ASCIIength;
		ASCIIength = ASCIIval.length();
		if (ASCIIval.length() % 2 != 0) {
			ECBlocks = new String[ASCIIength / 2 + 1];
		} 
		else {
			ECBlocks = new String[ASCIIength / 2];
		}

		for (int i = 0; i < ECBlocks.length; i++) {
			
			ECBlocks[i] = "";
		}
		int flg = 0;
		for (int i = 0; i < ASCIIength; i++) {
			if ( i % 2 == 0 && i != 0) {
				flg = flg +1;
			}
			ECBlocks[flg] = ECBlocks[flg] + ASCIIval.charAt(i);

		}

		return ECBlocks;
	}
//*************************************************************************************************************************************
	public String Decryption(String encryptedTxt, AllKeys Keys) {

		int CLength;
		int blocks;
		blocks = 0;
		String PLAIN_TXT;
		CLength = encryptedTxt.length();
		PLAIN_TXT = "";
		String[] block_cipher;
		int BLength;
		int CipherTx;
		CipherTx = 0;

		for (int i = 0; i < CLength; i++) {
			if (encryptedTxt.charAt(i) == '|') {
				blocks = blocks + 1;
			}
		}

		block_cipher = new String[blocks + 1];
		BLength = block_cipher.length;

		for (int i = 0; i < BLength; i++) {
			block_cipher[i] = "";
		}

		for (int i = 0; i < CLength; i++) {

			if (encryptedTxt.charAt(i) != '|') {
				block_cipher[CipherTx] = block_cipher[CipherTx] + encryptedTxt.charAt(i);
			} else if (encryptedTxt.charAt(i) == '|') {
				CipherTx = CipherTx + 1;
			}
		}

		String[] BlocksPlainTxt;
		BigInteger ans;

		BlocksPlainTxt = new String[block_cipher.length];

		for (int i = 0; i < BLength; i++) {

			if (i == BLength - 1 && 1 < BLength) {

				String prefix;
				String suffix;

				String PR_DN;
				int DNlength;
				// DNlength = PR_DN.length();
				String PU_EN;
				PR_DN = BlocksPlainTxt[i - 1];

				prefix = PR_DN.substring(0, PR_DN.length() - 1);
				suffix = PR_DN.substring(PR_DN.length() - 1, PR_DN.length());
				PU_EN = block_cipher[i] + suffix;
				ans = new BigInteger(PU_EN);
				BlocksPlainTxt[i] = prefix;

				BlocksPlainTxt[i - 1] = ans.modPow(Keys.PRkey[0], Keys.PRkey[1]).toString();

			}

			else {
				ans = new BigInteger(block_cipher[i]);
				BlocksPlainTxt[i] = ans.modPow(Keys.PRkey[0], Keys.PRkey[1]).toString();
			}
		}

		int PBlength;
		PBlength = BlocksPlainTxt.length;

		for (int i = 0; i < PBlength - 1; i++) {
			if (2 > BlocksPlainTxt[i].length()) {
				while (2 > BlocksPlainTxt[i].length())
					BlocksPlainTxt[i] = "0" + BlocksPlainTxt[i];
			}
		}
		if (BlocksPlainTxt[PBlength - 1] == "") {
			BlocksPlainTxt[PBlength - 1] = "0";
		}

		for (int i = 0; i < PBlength; i++) {
			PLAIN_TXT = PLAIN_TXT + BlocksPlainTxt[i];
		}

		PLAIN_TXT = ASCII_to_TEXT(PLAIN_TXT);

		return PLAIN_TXT;
	}
//*************************************************************************************************************************************
	public String Encryption(String PLAIN_TXT, AllKeys Keys) {
		
		//CIPHER_TXT stealing

		String ASCIIplaintxt;
		String[] BlocksPlainTxt;
		String[] block_cipher;
		String CIPHER_TXT = "";
		
		ASCIIplaintxt = TEXTtoASCII(PLAIN_TXT);
		BlocksPlainTxt = BlocksForASCII(ASCIIplaintxt);
		int BlockPtxtL;
		BlockPtxtL=BlocksPlainTxt.length;
		block_cipher = new String[BlockPtxtL];
		BigInteger ans;
		for (int i = 0; i < BlockPtxtL; i++) {

			if ( BlockPtxtL > 1 && i == BlockPtxtL - 1 ) {
				String prefix = block_cipher[i - 1].substring(0, block_cipher[i - 1].length() - 1);
				String suffix = block_cipher[i - 1].substring(block_cipher[i - 1].length() - 1, block_cipher[i - 1].length());
				String pr_key = BlocksPlainTxt[i] + suffix;
				ans = new BigInteger(pr_key);
				
				block_cipher[i] = prefix;
				block_cipher[i - 1] = ans.modPow(Keys.Pukey[0], Keys.Pukey[1]).toString();
				
			}

			else {
				ans = new BigInteger(BlocksPlainTxt[i]);
				block_cipher[i] = ans.modPow(Keys.Pukey[0], Keys.Pukey[1]).toString();
			}

		}
		for (int i = 0; i < BlockPtxtL; i++) {
			CIPHER_TXT = CIPHER_TXT +block_cipher[i];
			if (block_cipher.length - 1 != i) {
				CIPHER_TXT = CIPHER_TXT + '|';
			}
		}
		return CIPHER_TXT;
	}
//*************************************************************************************************************************************



}