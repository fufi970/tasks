package com.tutorial.controller;

import com.tutorial.dao.UserDAO;
import com.tutorial.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

private final UserDAO userDAO = new UserDAO();

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    
    request.getRequestDispatcher("/WEB-INF/views/login.jsp")
           .forward(request, response);
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    User user = userDAO.authenticate(username, password);

    if (user != null) {
        // Crea una nuova sessione o usa quella esistente
        HttpSession session = request.getSession(true);
        session.setAttribute("currentUser", user);
        
        response.sendRedirect(request.getContextPath() + "/tasks");
    } else {
        request.setAttribute("errorMessage", "Credenziali non valide.");
        request.getRequestDispatcher("/WEB-INF/views/login.jsp")
               .forward(request, response);
    }
}
}

