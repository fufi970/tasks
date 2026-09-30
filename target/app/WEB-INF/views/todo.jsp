<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="header">
    <div>Ciao, <strong><c:out value="${sessionScope.currentUser.username}" /></strong>!</div>
    <a class="logout-link" href="${pageContext.request.contextPath}/logout">Logout</a>
</div>

<h1>Le mie Task</h1>

<form class="add-form" action="${pageContext.request.contextPath}/tasks" method="POST">
    <input type="text" name="taskTitle" placeholder="Nuovo impegno..." required>
    <input type="checkbox" name="completed" value="true">
    <button type="submit">Aggiungi</button>
</form>

<ul>
    <c:forEach var="item" items="${tasks}">
        <li>
            <input type="checkbox" ${item.completed ? 'checked' : ''} disabled>
            <span class="${item.completed ? 'done' : ''}" style="margin-left: 10px;">
                <c:out value="${item.title}" />
            </span>
        </li>
    </c:forEach>
</ul>