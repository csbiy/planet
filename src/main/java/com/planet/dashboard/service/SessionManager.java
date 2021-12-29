package com.planet.dashboard.service;

import javax.servlet.http.HttpSession;

public class SessionManager {

    public static final String SESSION_ID = "ADMIN_ID";

    public static void addSession(HttpSession session , Object val){
        session.setAttribute(SESSION_ID,val);
    }


}
