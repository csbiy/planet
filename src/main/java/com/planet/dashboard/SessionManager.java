package com.planet.dashboard;

import javax.servlet.http.HttpSession;

public enum SessionManager {

    LOGIN_ID("ADMIN_ID"),
    EMAIL_AUTH("EMAIL_KEY");

    private static final int SECOND = 60;
    private final String id;

    SessionManager(String id) {
        this.id = id;
    }

    public static void addSession(HttpSession session , SessionManager key , Object val){
        session.setAttribute(key.name(),val);
    }

    public static void addSession(HttpSession session ,SessionManager key ,Object val,Integer maxMinute){
        session.setAttribute(key.name(),val);
        session.setMaxInactiveInterval(SECOND * maxMinute);
    }

    public static Object getSession(HttpSession session, SessionManager key ){
         return  session.getAttribute(key.name());
    }


}
