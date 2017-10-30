package eventBrite.UH.UserLogin;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;

import java.io.IOException;
import java.nio.file.Files;

public class UserLogin {

    private Scanner sc;

    public UserLogin(Scanner sc)
    {
        this.sc=sc;
    }
    public int Login()
    {
        String userName;
        String passwordInput;
        String password;
        try {
            System.out.println("Input your User Name"); //USER
            userName=sc.nextLine();

            /*CHECK IF USER EXISTS*/
            Path userBase = Paths.get("User_db.json");
            Charset charset = Charset.forName("ISO-8859-1");
            try {
                List<String> lines = Files.readAllLines(userBase, charset);
                for (String line : lines) {
                    System.out.println(line);
                }
            }catch (IOException e) {
                System.out.println(e);
            }

            /*When the string in front of "uName": == userName make the password = corresponding "pWord":*/

            System.out.println("Input your Password"); //PASSWORD
            passwordInput=sc.nextLine();


            /*CHECK IF USER AND PASSWORD MATCH*/
            /*if (passwordInput.equals(password))
            {
                System.out.println("Thanks for Logging in");
            }
            else
            {
                System.out.println("User and Password do not match!");
            }*/




        }catch (Exception e)
        {
            System.out.println(e.toString());
            return 0;
        }



        return 0;
    }
}