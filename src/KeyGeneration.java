

//RSA public-key cryptosystem

import java.util.Random;
import java.math.BigInteger;

//*****************************************************************************************************************************
public class KeyGeneration {

	/*
	 * Part 1 Key generation parameters: d,e,n (d,n)-->private key (for Bob)
	 * (e,n)-->public key (for Internet) size of numbers should be at least 1024 bit
	 * (around 309 digits).
	 */


	BigInteger e;
	BigInteger n;
	BigInteger d;
	BigInteger p;
	BigInteger q;

	BigInteger[] privateKey = new BigInteger[2];
	BigInteger[] publicKey = new BigInteger[2];

	AllKeys pairs;
	int numberOfpair = 0;
	int tmp = 0;
	boolean p_control;
	boolean q_control;
	
	
//*****************************************************************************************************************************
	public long modPow(long a, long b, long c) {
		long res = 1;
		for (int i = 0; i < b; i++) {
			res *= a;
			res %= c;
		}
		return res % c;
	}
//*****************************************************************************************************************************
	public boolean isPrime(long n, int iteration) {

		if (n == 0 || n == 1)
			return false;

		if (n == 2)
			return true;

		if (n % 2 == 0)
			return false;

		Random rand = new Random();
		for (int i = 0; i < iteration; i++) {
			long r = Math.abs(rand.nextLong());
			long a = r % (n - 1) + 1;
			if (modPow(a, n - 1, n) != 1)
				return false;
		}
		return true;
	}
//*****************************************************************************************************************************
	public boolean Control(BigInteger p) {

		BigInteger min;
		BigInteger lim;
		BigInteger result;
		int len;
		min = new BigInteger("1");
		lim = p.subtract(min);
		len = p.bitLength();
		Random rnd = new Random();

		for (int i = 0; i < 20; i++) {

			BigInteger a = new BigInteger(len, rnd);

			if (a.compareTo(min) < 0) {
				a = a.add(min);
			}

			if (a.compareTo(lim) >= 0) {
				a = a.mod(lim).add(min);
			}

			result = a.modPow(p.subtract(min), p);

			if (result.compareTo(min) != 0) {
				return false;
			}
		}
		return true;

	}
//*****************************************************************************************************************************
	public AllKeys CreateKeys() {

		Random tmp;
		BigInteger flg;

		tmp = new Random();

		flg = new BigInteger("1");

		while (q_control == false && p_control == false) {
			// the size of the numbers should be at least 1024 bit (around 309 digits).

			q = new BigInteger(1024, 1, tmp);
			q_control = Control(q);

			p = new BigInteger(1024, 1, tmp);
			p_control = Control(p);

		}

		n = q.multiply(p);
		BigInteger bi;
		privateKey[1] = n;
		publicKey[1] = n;

		bi = (q.subtract(flg).multiply(p.subtract(flg)));

		while (true) {

			e = new BigInteger(bi.bitLength(), tmp);
			if (bi.gcd(e).compareTo(flg) == 0 && 0 > e.compareTo(bi)) {
				break;
			}

		}

		d = e.modInverse(bi);

		privateKey[0] = d;
		publicKey[0] = e;

		String info;
		info = "Key pair generated. Key Pair ID = " + this.numberOfpair;
		//info = "Key Pair--->" + this.numberOfpair;
		this.numberOfpair = numberOfpair + 1;
		
		pairs = new AllKeys(privateKey, publicKey, info);
		return pairs;
	}
//*****************************************************************************************************************************
}