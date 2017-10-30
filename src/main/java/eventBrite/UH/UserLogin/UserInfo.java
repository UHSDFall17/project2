package eventBrite.UH.UserLogin;

public class UserInfo {
    private String uName;
    private String pWord;



    public UserInfo() {
        uName = "";
        pWord = "";
    }


    public int SetLoginInfos(
            String uName,
            String pWord)
    {
        this.uName = uName;
        this.pWord = pWord;

        return 1;
    }


    public String getuName(String uName) {
        return uName;
    }

    public String getpWord(String pWord) {
        return pWord;
    }

}
