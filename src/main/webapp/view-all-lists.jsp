<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>ToDoList</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="custom.css">
</head>

<body class="body container-fluid">
<header>

</header>
<main class="pt-5">
    <div class="container pt-5">
        <div class="row p-lg-4 p-1 pb-0 pe-lg-0 pt-lg-5 align-items-center rounded-3 border shadow-lg">
            <div class="col-lg-12 p-2 p-lg-5 pt-lg-3 pb-lg-1">
                <h1 class="display-4 fw-bold lh-1 text-light">ToDoList</h1>
                <p class="lead text-light">Current lists</p>
                <div class="list-group">
                    <c:if test="${allLists.isEmpty()==true}">
                        <li class="list-group-item list-group-item-dark
                                            d-flex
                                            justify-content-between
                                            align-items-center">
                            <div style="word-break: break-all">
                                No Lists
                            </div>
                        </li>
                    </c:if>
                    <c:if test="${allLists.isEmpty()==false}">
                        <c:forEach var="list" items="${allLists}">
                            <div>
                                <form action="/viewAllTasks" method="get" id="edit">
                                    <div class="d-grid gap-2
                                                mb-1 list-group list-group-item-dark
                                                align-items-center">
                                        <div class="btn-group">
                                            <button type="submit"
                                                    value="${list.listName}"
                                                    name="listName"
                                                    form="edit"
                                                    class="btn btn-outline-dark justify-content-between
                                                           d-flex col-lg-11 col-9">
                                                <c:out value="${list.listName}"/>
                                                <div>
                                                    <div class="badge bg-info rounded-pill ">
                                                        <c:out value="${list.tasksQuantity}"/>
                                                    </div>
                                                </div>
                                            </button>

                                            <div>
                                                <button type="submit" name="listName" form="delete"
                                                        value="${list.listName}"
                                                        class="btn btn-outline-dark">Delete
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <form action="/deleteList" method="post" id="delete"></form>
                            </div>

                        </c:forEach>
                    </c:if>
                </div>
            </div>


            <div class="col-lg-12 p-1 p-lg-5 pt-lg-3 justify-content-between">
                <div class="input-group">
                    <input type="text" form="addList" class="form-control" name="listName"
                           placeholder="List Name">
                    <form action="/create" method="post" id="addList">
                        <button class="btn btn-outline-secondary" type="submit">Add list</button>
                    </form>
                    <form action="start.jsp">
                        <button class="btn btn-outline-secondary" type="submit">Back</button>
                    </form>
                </div>
            </div>

        </div>

    </div>
</main>

<footer>
    <div>

    </div>
</footer>
<script src="js/bootstrap.min.js"></script>
</body>
</html>