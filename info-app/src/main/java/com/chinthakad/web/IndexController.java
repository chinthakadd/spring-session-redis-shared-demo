package com.chinthakad.web;

import com.chinthakad.common.session.CommonSharedContext;
import com.chinthakad.info.session.SessionComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class IndexController {

    @Autowired
    private SessionComponent sessionComponent;

    @Autowired
    private CommonSharedContext commonSharedContext;

    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse httpServletResponse, Model model) throws IOException {
        System.out.println("Site Hits: " + sessionComponent.getSiteHits());
        sessionComponent.setSiteHits(sessionComponent.getSiteHits() + 1);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if (commonSharedContext.getUsername() == null) {
            commonSharedContext.setUsername(username);
        }
        model.addAttribute("siteHits", sessionComponent.getSiteHits());
        model.addAttribute("username", username);
        return "index";
    }

}
