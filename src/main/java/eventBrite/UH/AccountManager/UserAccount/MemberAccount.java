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
	private boolean isUpdatedProfile;

	public MemberAccount(UserInfo userInfo)
	{
		this.userInfo = userInfo;
		isUpdatedProfile = false;
		if(!isMember)
			isMember = true;
	}

	public void createEvent() {eventHandler.create(userInfo.getId());}
	public void updateEvent() {eventHandler.update(userInfo.getId());}
	public void deleteEvent() {eventHandler.delete(userInfo.getId());}
	
	public Return updateProfile() 
	{
		Return ret = Return.RESET;
		if(EventInputScanner.continueOrReset("Update your profile") == Return.CONTINUE)
			ret = loadUpdateProfilePage();

		if(ret == Return.EWRONGINPUT)
			updateProfile();

		if(isUpdatedProfile)
		{
			isUpdatedProfile = false;
			return DBUserInfo.updateUser(userInfo);
		}
		return Return.RESET;
	}

	private Return loadUpdateProfilePage()
	{
		Return ret = updateProfileFeatures();

		if(ret == Return.EWRONGINPUT)
			return Return.EWRONGINPUT;

		if(ret != Return.SUCCESS)
			Return.printError(ret);

		if(EventInputScanner.continueOrReset("Edit another Information") == Return.CONTINUE)
			ret = loadUpdateProfilePage();
		return ret;
	}

	private Return updateProfileFeatures()
	{
		String  value;
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
			return Return.EWRONGINPUT;
		}
		System.out.print("Input the new value of ");	

		switch (ind)
		{
			case 0:
				System.out.println("First Name");
				value = sc.nextLine();
				userInfo.setFirstname(value);
				if(!isUpdatedProfile) isUpdatedProfile = true;
				break;
			case 1:
				System.out.println("Last Name");
				value = sc.nextLine();
				userInfo.setLastname(value);
				if(!isUpdatedProfile) isUpdatedProfile = true;
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
				if(!isUpdatedProfile) isUpdatedProfile = true;
				break;
			case 3:
				System.out.println("Password");
				value = sc.nextLine();
				try
				{
					value = AccountHandler.getInstance().getPasswordHash().generatePasswordHash(value);
				}	
				catch (Exception e)
				{	
					System.out.println(e);
					return Return.EXCEPTIONRAISED;
				}
				userInfo.setPassword(value);
				if(!isUpdatedProfile) isUpdatedProfile = true;
				break;
			default:
				ret = Return.ENOOPTION;
				break;
		}

		return ret;
	}
}
