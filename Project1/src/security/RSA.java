package security;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class RSA {

	public static void main(String[] args) {
		//c:\work\project> java -Dprime_size=500 security.RSA -gen "hello world"
		int dprime_size = 500;
		String plaintext = "hello world";
		
		BigInteger p = primeGen(dprime_size);
		BigInteger q = primeGen(dprime_size);
		System.out.println("p: " + p + "\nq: " + q );
		
		BigInteger n = p.multiply(q);
		System.out.println("n: " + n);
		
		BigInteger phiN = phiGen(p, q);
		System.out.println("Phi of n: " + phiN);

		BigInteger e = eGen(phiN, dprime_size);
		System.out.println("e: " + e);

		BigInteger d = dGen(e, phiN);
		System.out.println("d: " + d);

		//encrypt
		String cyphertext = encrypt(plaintext, e, n);
		System.out.println("Cyphertext: " + cyphertext);
		
		//decrypt
		String pt = decrypt(cyphertext, d, n);
		System.out.println("Decrypted plaintext: " + pt);
	}

	private static String encrypt(String text, BigInteger e, BigInteger n) {

		BigInteger message = new BigInteger(text.getBytes());
		BigInteger m = message.modPow(e, n);

		String alteredText = new String(m.toByteArray());
		return alteredText;
	}
	
	private static String decrypt(String text, BigInteger d, BigInteger n) {
		BigInteger message = new BigInteger(text.getBytes());
		BigInteger m = message.modPow(d, n);

		String alteredText = new String(m.toByteArray());
		return alteredText;
	}

	private static BigInteger eGen(BigInteger phiN, int size) {

		SecureRandom r = new SecureRandom();
		BigInteger e = new BigInteger(size, 100, r);

		while (phiN.gcd(e).intValue() > 1) {
			e = e.add(new BigInteger("2"));
		}
		return e;
	}
	
	private static BigInteger dGen (BigInteger e, BigInteger phiN) {		
		//Find inverse of e mod phiN
		BigInteger d = e.modInverse(phiN);
		return d;
	}

	private static BigInteger primeGen(int size) {
		BigInteger prime;
		 SecureRandom rand = new SecureRandom();
		
		prime = BigInteger.probablePrime(size, rand);
		return prime;
	}
	
	private static BigInteger phiGen(BigInteger p, BigInteger q) {
		BigInteger phi = null;

		p = p.subtract(BigInteger.ONE);
		q = q.subtract(BigInteger.ONE);
		
		phi = p.multiply(q);
		
		return phi;
	}
}
