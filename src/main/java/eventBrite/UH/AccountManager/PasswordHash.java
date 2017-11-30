package eventBrite.UH.AccountManager;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordHash
{
	private int hashIterationNumber;
	private int hashLenghtInBytes;
	private	int hashLength;

	public PasswordHash()
	{
		hashIterationNumber = 1000;
		hashLenghtInBytes	= 64;
		this.setHashLength();
	}

	public PasswordHash(int iterationNb, int hashBytes)
	{
		hashIterationNumber = iterationNb;
		hashLenghtInBytes	= hashBytes;
		this.setHashLength();
	}

	public int getHashLength() {return hashLength;}

	public void setIterationNb(int iterationNb)
	{
		hashIterationNumber = iterationNb;
		this.setHashLength();
	}

	public void setHashBytesNb(int hashBytes)
	{
		hashLenghtInBytes = hashBytes;
		this.setHashLength();
	}

	private void setHashLength()
	{
		/*
			2 for :
			32 for salt
			hashIterationNumber digit number
			hashLength hex digit number
		*/
		hashLength = 2 + 32 + 
			((int) Math.log10(hashIterationNumber) + 1) + 
			(hashLenghtInBytes/4);
	}

	// the password hash is the combination of iterationNumber + salt + hash 
	public String generatePasswordHash(String password) 
		throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		char[] chars 	= password.toCharArray();
		// Generate Sal Hash
		byte[] salt 	= getSalt();
		
		// Hash password to hashLenghtInBytes Bytes. 
		PBEKeySpec spec = new PBEKeySpec(chars, salt, hashIterationNumber, hashLenghtInBytes);
		// Use PBKDF2 With Hmac SHA1
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return hashIterationNumber + ":" + toHex(salt) + ":" + toHex(hash);
	}

	public boolean validatePassword(
		String originalPassword,
		String storedPassword) 
		throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);
		 
		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] testHash = skf.generateSecret(spec).getEncoded();
		 
		int diff = hash.length ^ testHash.length;
		for(int i = 0; i < hash.length && i < testHash.length; i++)
		{
			diff |= hash[i] ^ testHash[i];
		}
		return diff == 0;
	}

	// Create Salt: 32 Bytes 
	private static byte[] getSalt() throws NoSuchAlgorithmException
	{
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
	
	// Convert Byte Array to Hexadecimal String
	private static String toHex(byte[] array) throws NoSuchAlgorithmException
	{
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();

		// if(paddingLength > 0)
		// 	return String.format("%0"  +paddingLength + "d", 0) + hex;
		// else
			return hex;
	}

	// Convert Hexadecimal String to Byte Array
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	{
		byte[] bytes = new byte[hex.length() / 2];
		for(int i = 0; i<bytes.length ;i++)
		{
			bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
}
	