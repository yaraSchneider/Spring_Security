package com.example.security.security.Utils;

import com.example.security.security.model.Exeption.CookieNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieUtils {

    private final String NAME = "JWTSESSION";

    public Cookie getJWTCookie(HttpServletRequest request){
        Cookie[] cokies = request.getCookies();
        for (Cookie cookie :cokies){
            if (cookie.getName().equals(NAME)){
                return cookie;
            }
        }
        throw new CookieNotFoundException(NAME);
    }
}
