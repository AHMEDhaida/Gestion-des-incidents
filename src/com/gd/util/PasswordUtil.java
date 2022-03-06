package com.gd.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public boolean comparePassword(String hashed, String unhashed){
        return BCrypt.checkpw(unhashed, hashed);
    }

    public String getHashedPassword(String string){
        return BCrypt.hashpw(string, BCrypt.gensalt());
    }



}
