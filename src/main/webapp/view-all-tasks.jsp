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
<header class="pt-5">
    <div class="container">
        <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <div class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-light text-decoration-none ">
                <span class="fs-1">ToDoList Project</span>
            </div>

            <ul class="nav nav-pills justify-content-between">
                <div class="align-items-end d-flex mx-2">
                    <form action="/about.jsp">
                        <button type="submit" class="btn btn-light btn-outline-dark">About</button>
                    </form>
                </div>
                <div class="align-items-end d-flex mx-2 ">
                    <form action="/logout" method="get">
                        <button type="submit" class="btn btn-light btn-outline-dark">LogOut</button>
                    </form>
                </div>
                </li>
            </ul>
        </header>
    </div>
</header>
<main class="container">
    <div class="container pt-5">
        <div class="row p-lg-4 p-1 pb-0 pe-lg-0 pt-lg-5 align-items-center rounded-3 border shadow-lg">
            <div class="col-lg-12 p-2 p-lg-5 pt-lg-3 pb-lg-1">
                <h1 class="display-4 fw-bold lh-1 text-light">ToDoList</h1>
                <p class="lead text-light">Current list: '${list.listName}'</p>

                <div class="list-group">
                    <c:if test="${allTasks.isEmpty()==true}">
                        <li class="list-group-item list-group-item-dark
                                            d-flex
                                            justify-content-between
                                            align-items-center">
                            <div style="word-break: break-all">
                                No Tasks
                            </div>
                        </li>
                    </c:if>
                    <c:if test="${allTasks.isEmpty()==false}">
                        <c:forEach var="task" items="${allTasks}">
                            <div>
                                <form action="/editTask" method="get" id="edit">
                                    <div class="d-grid gap-2
                                                mb-1 list-group list-group-item-dark
                                                align-items-center">
                                        <div class="btn-group">
                                            <button type="submit"
                                                    value="${list.idList},${task.taskBody}"
                                                    name="listAndTask"
                                                    form="edit"
                                                    class="btn btn-outline-dark justify-content-between
                                                           d-flex col-lg-11 col-9">
                                                <c:out value="${task.taskBody}"/>
                                                <c:if test="${task.active==true}">
                                                    🔥
                                                </c:if>
                                                <div>
                                                    <c:if test="${task.complete==true}">
                                                        <div class="badge bg-success rounded-pill ">
                                                            ✓
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${task.complete==false}">
                                                        <div class="badge bg-warning rounded-pill ">
                                                            ❌
                                                        </div>
                                                    </c:if>

                                                </div>
                                            </button>

                                            <div>
                                                <button type="submit" name="listAndTask" form="delete"
                                                        value="${list.idList},${task.taskBody}"
                                                class="btn btn-outline-dark">Delete
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <form action="/deleteTask" method="post" id="delete"></form>
                            </div>

                        </c:forEach>
                    </c:if>
                </div>
            </div>


            <div class="col-lg-12 p-2 p-lg-5 pt-lg-3 pb-lg-1">
                <div class="input-group mb-3">
                    <input type="text" form="addTask" class="form-control" name="taskBody"
                           placeholder="Task Body">
                    <form action="/viewAllTasks" method="post" id="addTask">
                        <button type="submit" class="btn btn-outline-secondary"
                                value="${list.idList}" name="idList">Add task
                        </button>
                    </form>
                    <form action="/viewAll" method="get">
                        <button class="btn btn-outline-secondary" value="${list.idList}" type="submit">Back</button>
                    </form>
                </div>

                <c:if test="${create==true}">
                    <c:if test="${hasProperName==true}">
                        <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                            <div class="alert alert-success" role="alert">
                                Task '${task.taskBody}' was successfully created.
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${hasProperName==false}">
                        <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                            <div class="alert alert-warning" role="alert">
                                Task '${task.taskBody}' already exists or has wrong name.
                            </div>
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${delete==true}">
                    <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                        <div class="alert alert-warning" role="alert">
                            Task '${task.taskBody}' was successfully deleted.
                        </div>
                    </div>
                </c:if>

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