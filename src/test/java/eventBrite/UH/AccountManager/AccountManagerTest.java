package eventBrite.UH.AccountManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.EventTools.EventTypes.AccountType;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventApp.TestUtility;
import eventBrite.UH.DatabaseManager.DBUserInfo;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AccountManagerTest {

	private static TestUtility.FunctionToTest loginFunc = 
					(ArrayList<Object> list) -> AccountHandler.getInstance().login();
	private static TestUtility.FunctionToTest signUpFunc = 
					(ArrayList<Object> list) -> AccountHandler.getInstance().create((AccountType)(list.get(0)));				
	private static ArrayList<Object> paramList;

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {

	}

// Login
	@Test
	public void testLoginNotExistingAccount() throws Exception 
	{
		String expectedRet = Return.EACCOUNTNOTFOUND.toString();
		TestUtility.testFunctionOutputError("y\njohn@gmail.com\nazert1234\nN", expectedRet, loginFunc, null);
	}

	@Test
	public void testLoginWrongEmail() throws Exception 
	{
		String expectedRet = Return.EEMAILFORMAT.toString();
		TestUtility.testFunctionOutputError("Y\njohnsgmail.com\nazert1234\nN", expectedRet, loginFunc, null);
	}

	@Test
	public void testLoginWrongPassword() throws Exception
	{
		String expectedRet = Return.EWRONGPASSWD.toString();
		TestUtility.testFunctionOutputError("y\nnour@cs.uh.edu\nreal1234\nn", expectedRet, loginFunc, null);
	}

	@Test
	public void testLoginWrongHashFunction() throws Exception
	{
		String expectedRet = Return.EXCEPTIONRAISED.toString();
		UserInfo userInfo = new UserInfo("FN", "LN", "FN@FN.com", "q");
		DBUserInfo.insertUserIntoDB(userInfo);

		TestUtility.testFunctionOutputError("y\nFN@FN.com\nq\nn", expectedRet, loginFunc, null);
	}	

// SignUp Test
	@Test
	public void testCreateMember() throws Exception
	{
		paramList = new ArrayList<Object>(Arrays.asList(AccountType.MEMBER));
		UserAccount userAccount = (UserAccount)(TestUtility.getFunctionReturn(
			"y\nfn\nln\nemailX@x.com\nabc\nabc", signUpFunc, paramList));
		assertTrue(userAccount.isMember());

	}

	@Test
	public void testCreateExistingMember() throws Exception
	{
		String expectedRet = Return.EACCOUNTEXIST.toString();
		paramList = new ArrayList<Object>(Arrays.asList(AccountType.MEMBER));
		TestUtility.testFunctionOutputError("y\nfn\nln\nfekiraafat@gmail.com\nabc\nabc\nn",
						expectedRet, signUpFunc, paramList);

	}

	@Test
	public void testCreateMemberWrongEmailFormat() throws Exception
	{
		String expectedRet = Return.EEMAILFORMAT.toString();
		paramList = new ArrayList<Object>(Arrays.asList(AccountType.MEMBER));
		TestUtility.testFunctionOutputError("y\nfn\nln\nemail.x.com\nabc\nabc\nn",
						expectedRet, signUpFunc, paramList);
	}

	@Test
	public void testCreateMemberWrongPasswordConfirmation() throws Exception
	{
		String expectedRet = Return.EPASSWDMATCH.toString();
		paramList = new ArrayList<Object>(Arrays.asList(AccountType.MEMBER));
		TestUtility.testFunctionOutputError("y\nfn\nln\nemailX1@x.com\nabc\nabc2\nn",
						expectedRet, signUpFunc, paramList);
	}

	@Test
	public void testCreateMemberHashFunctionException() throws Exception
	{
		String expectedRet = Return.EXCEPTIONRAISED.toString();
		SignUp userSignUp 	= new SignUp(new PasswordHash(0, 1024));

		TestUtility.FunctionToTest userSignUpfunc = 
					(ArrayList<Object> list) -> userSignUp.signUp();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionOutputError("y\nfn\nln\nemail2@x.com\n\n\nn",
						expectedRet, userSignUpfunc, paramList);
	}

// UserAccount Creation
	@Test
	public void testCreateGuest() throws Exception
	{
		paramList = new ArrayList<Object>(Arrays.asList(AccountType.GUEST));
		UserAccount userAccount = (UserAccount)(TestUtility.getFunctionReturn("", signUpFunc, paramList));
		assertFalse(userAccount.isMember());
	}

	@Test
	public void testCreateOtherThanMemberGuest() throws Exception
	{
		paramList = new ArrayList<Object>(Arrays.asList(AccountType.ADMIN));
		UserAccount userAccount = (UserAccount)(TestUtility.getFunctionReturn("", signUpFunc, paramList));
		assertNull(userAccount);
	}

	@Test
	public void testCreateAdmin() throws Exception
	{
		assertNull(AccountFactory.getInstance().create(AccountType.ADMIN, null));
	}

// Profile Update
	@Test
	public void testGuestUpdate() throws Exception
	{
		String expectedRet = Return.EINSUFFPRIV.toString();
		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
				AccountFactory.getInstance().create(AccountType.GUEST, null).updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionOutputError("", expectedRet, updateFunc, paramList);
	}

	@Test
	public void testMemberUpdateWrongInput() throws Exception
	{
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionReturn("y\nX\nn", Return.RESET, updateFunc, paramList);
	}

	@Test
	public void testMemberUpdateWrongInput2() throws Exception
	{
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionReturn("X\ny\nX\nn", Return.RESET, updateFunc, paramList);
	}

	@Test
	public void testMemberUpdateNewFirstName() throws Exception
	{
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionReturn("y\n0\nRaafat\nn", Return.SUCCESS, updateFunc, paramList);
	}

	@Test
	public void testMemberUpdateNewLastName() throws Exception
	{
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionReturn("y\n1\nFeki\nn", Return.SUCCESS, updateFunc, paramList);
	}

	@Test
	public void testMemberUpdateNewEmail() throws Exception
	{
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionReturn("y\n2\nfekiraafat@gmail.com\nn", Return.SUCCESS, 
									updateFunc, paramList);
	}

	@Test
	public void testMemberUpdateNewEmailWrongFormat() throws Exception
	{
		String expectedRet = Return.EEMAILFORMAT.toString();		
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionOutputError("y\n2\nfekiraafat.gmail.com\nn", 
			expectedRet, updateFunc, paramList);
	}

	@Test
	public void testMemberUpdateNewPassword() throws Exception
	{
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionReturn("y\n3\nqwerty1234\nn", Return.SUCCESS, 
							updateFunc, paramList);
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionReturn("y\n3\npassword\nn", Return.SUCCESS, 
							updateFunc, paramList);		
	}

	@Test
	public void testMemberUpdateNewPasswordWrongHashFunction() throws Exception
	{
		String expectedRet = Return.EXCEPTIONRAISED.toString();		
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		AccountHandler.getInstance().setPasswordHash(new PasswordHash(0, 1024));
		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionOutputError("y\n3\nqwerty1234\nn", 
			expectedRet, updateFunc, paramList);
		AccountHandler.getInstance().setPasswordHash(new PasswordHash());
	}

	@Test
	public void testMemberUpdateNoAvailableOption() throws Exception
	{
		String expectedRet = Return.ENOOPTION.toString();		
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionOutputError("y\n6\nn", 
			expectedRet, updateFunc, paramList);
	}

	@Test
	public void testMemberUpdateTwoInfo() throws Exception
	{
		UserInfo userInfo = DBUserInfo.getUserInfoByUserEmail("fekiraafat@gmail.com");
		MemberAccount memberAccount = new MemberAccount(userInfo);

		TestUtility.FunctionToTest updateFunc = (ArrayList<Object> list) -> 
													memberAccount.updateProfile();
		paramList = new ArrayList<Object>();
		TestUtility.testFunctionReturn("y\n0\nRaafat\ny\n1\nFeki\nn", Return.SUCCESS, 
									updateFunc, paramList);
	}

// UserInfo

	@Test
	public void testGetByNameWrongAttribute() throws Exception
	{
		UserInfo userInfo = new UserInfo("FN", "LN", "fakeEmail@gmail.com", "password");
		assertEquals(null, userInfo.getByName(""));
	}
}
