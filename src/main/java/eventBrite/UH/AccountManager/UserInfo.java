package eventBrite.UH.AccountManager;

import eventBrite.UH.EventTools.AttributesGetter;

public class UserInfo implements AttributesGetter
{
	private int 	id;
	private String 	userName;
	private String 	firstName;
	private String 	lastName;
	private String 	email;
	private String 	password;

	public UserInfo(String firstName, String lastName, String email, String hashedPassword) 
	{
		this.id = -1;
		this.userName 	= firstName + '.' + lastName;
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.email 		= email;
		this.password 	= hashedPassword;
	}

	public int 	  getId()			{return id;}
	public String getUsername() 	{return userName;}
	public String getFirstname()	{return firstName;}
	public String getLastname()		{return lastName;}
	public String getEmail()		{return email;}	
	public String getPassword() 	{return password;}

	public void setId(int id)				{this.id = id;}
	public void setFirstname(String fName)	{this.firstName = fName;}
	public void setLastname(String lName) 	{this.lastName = lName;}
	public void setEmail(String email)		{this.email = email;}	
	public void setPassword(String  pWord) 	{this.password = pWord;}

	@Override
	public Object getByName(String Name) {

		switch (Name) {
			case "id":
				return getId();
			case "userName":
				return getUsername();
			case "firstName":
				return getFirstname();
			case "lastName":
				return getLastname();
			case "email":
				return getEmail();
			case "password":
				return getPassword();
			default:
				return null;
		}
	}
}
