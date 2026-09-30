package com.tutorial.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/tasks/*"})
public class AuthFilter extends HttpFilter {

@Override
protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    
    HttpSession session = request.getSession(false);
    boolean loggedIn = (session != null && session.getAttribute("currentUser") != null);

    if (loggedIn) {
        // Utente autenticato: continua l'esecuzione
        chain.doFilter(request, response);
    } else {
        // Utente non autenticato: reindirizza al login
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
}