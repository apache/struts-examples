package org.demo;

import flexjson.JSON;

import java.util.Date;

public class User extends Person {

    private String login;
    private String hashedPassword;
    private Date lastLogin;
    private String password;

    @JSON(include = false)
    public String getHashedPassword() {
        return hashedPassword;
    }

    @JSON(name = "username")
    public String getLogin() {
        return login;
    }

    @JSON(transformer = PasswordTransformer.class)
    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
