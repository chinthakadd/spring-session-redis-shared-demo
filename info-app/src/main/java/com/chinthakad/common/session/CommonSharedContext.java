package com.chinthakad.common.session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CommonSharedContext implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
