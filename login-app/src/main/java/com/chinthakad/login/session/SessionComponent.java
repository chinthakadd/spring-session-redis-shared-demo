package com.chinthakad.login.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class SessionComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    private int loginAttempts;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(int loginAttempts) {
        this.loginAttempts = loginAttempts;
    }
}
