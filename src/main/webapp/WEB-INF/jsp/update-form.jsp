<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Book</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <div class="container">
        <h2>Update Book Details</h2>

        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <form:form action="/books/update/${book.id}" modelAttribute="book" method="post">
            <div>
                <label for="title">Title:</label>
                <form:input path="title" id="title" required="required" />
            </div>
            <div>
                <label for="isbn">ISBN:</label>
                <form:input path="isbn" id="isbn" required="required" />
            </div>
            <div>
                <label for="author">Author:</label>
                <form:select path="author" id="author" required="required">
                    <form:option value="" label="-- Select Author --"/>
                    <form:options items="${authors}" itemValue="id" itemLabel="name"/>
                </form:select>
            </div>
            <div>
                <input type="submit" value="Update" class="btn" />
                <a href="/books" class="btn btn-secondary">Cancel</a>
            </div>
        </form:form>
    </div>
</body>
</html>
