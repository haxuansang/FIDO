package com.example.jake.fido.Instance;

public class UserData {
    public static UserData userData;
    public UserData getInstance(){
        if (userData==null){
            userData = new UserData();
        }
        return userData;
    }
}
