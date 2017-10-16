package eventBrite.UH.UserLogin;

import java.util.Scanner;

public class UserLogin {

    private Scanner sc;

    public UserLogin(Scanner sc)
    {
        this.sc=sc;
    }
    public int Login()
    {
        String userName;
        String password;
        try {
            System.out.println("Input your User Name");
            userName=sc.nextLine();

            /*CHECK IF USER EXISTS*/

            System.out.println("Input your Password");
            password=sc.nextLine();

            /*CHECK IF USER AND PASSWORD MATCH*/



        }catch (Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }



        return 0;
    }
}