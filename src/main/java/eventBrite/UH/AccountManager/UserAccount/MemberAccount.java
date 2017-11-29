package eventBrite.UH.AccountManager;

import eventBrite.UH.EventTools.EventTypes.Return;
import eventBrite.UH.EventTools.EventInputScanner;
import eventBrite.UH.EventTools.MailNotifier;
import eventBrite.UH.DatabaseManager.DBUserInfo;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

class MemberAccount extends UserAccount
{
	public MemberAccount(UserInfo userInfo)
	{
		this.userInfo = userInfo;
		if(!isMember)
			isMember = true;
	}

	public void createEvent() {eventHandler.create(userInfo.getId());}
	public void updateEvent() {eventHandler.update(userInfo.getId());}
	public void deleteEvent() {eventHandler.delete(userInfo.getId());}
	
	public void updateProfile() 
	{
		if(EventInputScanner.continueOrReset("Update your profile") == Return.CONTINUE)
			loadUpdateProfilePage();
		DBUserInfo.updateUser(userInfo);
	}

	private void loadUpdateProfilePage()
	{
		String value;
		int 	ind = -1;
		Return 	ret = Return.SUCCESS;
		Scanner sc 	= EventInputScanner.getScanner();

		System.out.println(userInfo.toStringUserInfo());
		System.out.println("Input the information number you want to change");
		try
		{
			ind = Integer.parseInt(sc.nextLine());
		}
		catch(Exception e)
		{
			System.out.println(e);
			loadUpdateProfilePage();
		}
		System.out.print("Input the new value of ");	

		switch (ind)
		{
			case 0:
				System.out.println("First Name");
				value = sc.nextLine();
				userInfo.setFirstname(value);
				break;
			case 1:
				System.out.println("Last Name");
				value = sc.nextLine();
				userInfo.setLastname(value);
				break;
			case 2:
				System.out.println("E-mail");
				value = sc.nextLine();
				if(!MailNotifier.checkEmailAddressFormat(value))
				{
					ret = Return.EEMAILFORMAT;
					break;
				}
				userInfo.setEmail(value);
				break;
			case 3:
				System.out.println("Password");
				value = sc.nextLine();
				try
				{
					value = AccountHandler.getInstance().getPasswordHash().generatePasswordHash(value);
				}	
				catch (NoSuchAlgorithmException | InvalidKeySpecException e)
				{	
					System.out.println(e);
					ret =  Return.EXCEPTIONRAISED;
					break;
				}
				userInfo.setPassword(value);
				break;
			default:
				ret = Return.EWRONGINPUT;
				break;
		}

		if(ret != Return.SUCCESS)
			Return.printError(ret);

		if(EventInputScanner.continueOrReset("Edit another Information") == Return.CONTINUE)
			loadUpdateProfilePage();
	}
}
