package com.planet.dashboard;

import javax.servlet.http.HttpSession;

public class SessionManager {

    public static final String SESSION_ID = "ADMIN_ID";

    public static final String AUTH_ID = "EMAIL_KEY";

    public static void addSession(HttpSession session ,String key ,Object val){
        session.setAttribute(key,val);
    }


}
