<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Accedi</h2>

<c:if test="${not empty errorMessage}">
    <div class="error"><c:out value="${errorMessage}" /></div>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="POST">
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>
    </div>
    <button type="submit">Entra</button>
</form>