package com.chinthakad.web;

import com.chinthakad.common.session.CommonSharedContext;
import com.chinthakad.login.session.SessionComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private SessionComponent sessionComponent;

    @Autowired
    private CommonSharedContext commonSharedContext;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        System.out.println("Login Site Attempts: " + sessionComponent.getLoginAttempts());

        sessionComponent.setLoginAttempts(sessionComponent.getLoginAttempts() + 1);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (commonSharedContext.getUsername() == null) {
            commonSharedContext.setUsername(username);
        }

        model.addAttribute("loginAttempts", sessionComponent.getLoginAttempts());
        model.addAttribute("username", username);
        return "index";
    }

}
