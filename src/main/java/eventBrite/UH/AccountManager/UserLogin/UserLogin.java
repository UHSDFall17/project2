package eventBrite.UH.AccountManager;

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
        int userFound = 0;
        int passFound = 0;
        int lineCount = 0;
        int liner = 0;
        int startIndex;
        int endIndex;
        try {
            System.out.println("Input your User Name"); //USER
            userName=sc.nextLine();

            /*CHECK IF USER EXISTS*/
            Path userBase = Paths.get("User_db.json");
            Charset charset = Charset.forName("ISO-8859-1");
            try {
                List<String> lines = Files.readAllLines(userBase, charset);
                for (String line : lines) {
                    //System.out.println(line);
                    line = line.replace("{\"uName\":\"","");
                    endIndex = line.indexOf("\"");
                    line = line.substring(0,endIndex);
                    if (userName.equals(line)){
                        //System.out.println("Correct User: " + userName);
                        userFound = 1;
                        liner = lineCount;
                    }
                    /*else{
                        System.out.println("Incorrect User: "+ line);
                        System.exit(1);
                    }*/
                    lineCount += 1;
                }
                if (userFound == 0)
                {
                    System.out.println("User Not Found: "+ userName);
                    System.exit(1);
                }
            }catch (IOException e) {
                System.out.println(e);
            }

            /*When the string in front of "uName": == userName make the password = corresponding "pWord":*/

            lineCount = 0;
            System.out.println("Input your Password"); //PASSWORD
            passwordInput=sc.nextLine();


            try {
                List<String> lines = Files.readAllLines(userBase, charset);
                for (String line : lines) {
                    //System.out.println(line);
                    startIndex = line.indexOf("pWord\":\"")+8;
                    //System.out.println("line == " + line + "\nstartIndex == " + startIndex);
                    endIndex = line.indexOf("\"}");
                    line = line.substring(startIndex,endIndex);
                    if (passwordInput.equals(line) && lineCount == liner){
                        System.out.println("Login Successful");
                        passFound = 1;
                    }
                    else{
                        //System.out.println("Expected Pass: "+ line);
                        //System.exit(1);
                    }
                    lineCount += 1;
                }
                if (passFound == 0)
                {
                    System.out.println("Password does not match: "+ passwordInput);
                    System.exit(1);
                }
            }catch (IOException e) {
                System.out.println(e);
            }

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