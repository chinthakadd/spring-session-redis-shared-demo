package com.chinthakad.info.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class SessionComponent implements Serializable {

    private static final long serialVersionUID = 1000L;

    private int siteHits;

    public int getSiteHits() {
        return siteHits;
    }

    public void setSiteHits(int siteHits) {
        this.siteHits = siteHits;
    }
}
