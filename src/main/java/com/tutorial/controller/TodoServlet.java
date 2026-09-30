package com.tutorial.controller;

import com.tutorial.dao.TaskDAO;
import com.tutorial.model.Task;
import com.tutorial.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TodoServlet", urlPatterns = {"/tasks"})
public class TodoServlet extends HttpServlet {

private final TaskDAO taskDAO = new TaskDAO();

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
    
    HttpSession session = request.getSession(false);
    User currentUser = (User) session.getAttribute("currentUser");

    List<Task> userTasks = taskDAO.findByUserId(currentUser.getId());
    request.setAttribute("tasks", userTasks);

    request.getRequestDispatcher("/WEB-INF/views/todo.jsp")
           .forward(request, response);
}

@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws IOException {
    
    HttpSession session = request.getSession(false);
    User currentUser = (User) session.getAttribute("currentUser");

    String title = request.getParameter("taskTitle");
    Boolean completed = Boolean.parseBoolean(request.getParameter("completed"));

    if (title != null && !title.trim().isEmpty()) {
        taskDAO.insert(title.trim(), currentUser.getId(), completed);
    }

    response.sendRedirect(request.getContextPath() + "/tasks");
}
}