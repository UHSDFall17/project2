package eventBrite.UH.AccountManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordHashTest 
{
	private PasswordHash passwdHash;

	@Before
	public void setUp()
	{
		passwdHash = new PasswordHash();
	}

	@After
	public void tearDown()
	{
	}

	@Test
	public void testDefaultHashLength()
	{
		assertEquals(54, passwdHash.getHashLength());
	}

	@Test
	public void testHashLength()
	{
		PasswordHash passwdHash = new PasswordHash(100, 512);
		assertEquals(165, passwdHash.getHashLength());
	}

	@Test
	public void testHashLength_NewIterationNb()
	{
		passwdHash.setIterationNb(10);
		assertEquals(52, passwdHash.getHashLength());
	}

	@Test
	public void testHashLength_NewHashBytesNb()
	{
		passwdHash.setHashBytesNb(256);
		assertEquals(102, passwdHash.getHashLength());
	}

	@Test
	public void testPasswordValidation_RightPassword()
	{
		boolean actualValue;
		try
		{
			String generatedPasswordHash = passwdHash.generatePasswordHash("password1234!");
			actualValue = passwdHash.validatePassword("password1234!", generatedPasswordHash);
		}	
		catch (NoSuchAlgorithmException | InvalidKeySpecException e)
		{	
			System.out.println(e);
			actualValue = false;
		}
		assertEquals(true, actualValue);
	}

	@Test
	public void testPasswordValidation_WrongPassword()
	{
		boolean actualValue;
		try
		{
			String generatedPasswordHash = passwdHash.generatePasswordHash("password1234!");
			actualValue = passwdHash.validatePassword("WrongPassword", generatedPasswordHash);
		}	
		catch (NoSuchAlgorithmException | InvalidKeySpecException e)
		{	
			System.out.println(e);
			actualValue = true;
		}
		assertEquals(false, actualValue);
	}
}