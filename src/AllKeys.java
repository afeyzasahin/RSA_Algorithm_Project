

import java.util.Random;
import java.math.BigInteger;

//******************************************************************************************************************************

public class AllKeys {

	// BigInteger[] PrivateKey;
	// BigInteger[] PublicKey;

	BigInteger PrivateKeys;
	BigInteger PublicKeys;

	BigInteger[] PRkey = new BigInteger[2];
	BigInteger[] Pukey = new BigInteger[2];

	String Info;

//******************************************************************************************************************************    
	public String toString() {
		return Info;
	}

//******************************************************************************************************************************      
	public AllKeys(BigInteger[] privateKey, BigInteger[] publicKey, String info) {

		this.Info = info;

		this.PRkey[0] = privateKey[0];
		this.PRkey[1] = privateKey[1];
		this.PrivateKeys = new BigInteger(this.PRkey[0].toString() + this.PRkey[1].toString());
		
		this.Pukey[0] = publicKey[0];
		this.Pukey[1] = publicKey[1];
		this.PublicKeys = new BigInteger(this.Pukey[0].toString() + this.Pukey[1].toString());

	}
//******************************************************************************************************************************  

}