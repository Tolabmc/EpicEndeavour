package Login;

import java.util.HashMap;

public class IDandPasswords {
    HashMap<String,String> logininfo = new HashMap<String,String>();

    IDandPasswords(){
        logininfo.put("David","pass");
        logininfo.put("Tola","password");
        logininfo.put("example","abc123");
    }

protected HashMap getLoginInfo(){
        return logininfo;
    }
}
