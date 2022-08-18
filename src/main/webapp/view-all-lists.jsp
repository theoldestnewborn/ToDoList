<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>ToDoList</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.2.0/mdb.min.css"
          rel="stylesheet"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="custom.css">
</head>

<body class="body container-fluid">
<header class="pt-5">
    <div class="container">
        <div class="d-flex flex-wrap justify-content-center mb-5 pt-5 pb-2 px-4 border-bottom">
            <div class="d-flex align-items-center p-0 mb-3 mb-md-0 me-md-auto text-light text-decoration-none ">
                <div class="display-4 lh-0 text-light p-2 rounded-3" style="background: rgba(173,181,189,0.22)">ToDoList
                    Project
                </div>
            </div>

            <ul class="nav nav-pills justify-content-between">
                <div class="align-items-end d-flex m-2">
                    <form action="<c:url value = "/about.jsp"/>">
                        <button type="submit" class="btn btn-light">About</button>
                    </form>
                </div>
                <div class="align-items-end d-flex m-2 ">
                    <form action="<c:url value = "/signOut"/>" method="get">
                        <button type="submit" class="btn btn-light">Sign out</button>
                    </form>
                </div>
                </li>
            </ul>
        </div>
    </div>
</header>
<main class="container">
    <div class="container pt-5">
        <div class="row p-lg-4 p-1 pb-0 pe-lg-0 pt-lg-5 align-items-center rounded-3 border shadow-lg">
            <div class="col-lg-12 p-2 p-lg-5 pt-lg-3 pb-lg-1">
                <h1 class="display-4 fw-bold lh-1 text-light">ToDoList</h1>
                <p class="lead text-light">Hello, ${user.login}. Your lists are:</p>
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

                            <form action="<c:url value = "/viewAllTasks"/>" id="edit" method="get">
                                <div class="d-grid gap-2
                                                mb-1 list-group list-group-item-dark
                                                align-items-center">
                                    <div class="btn-group">
                                        <button type="submit"
                                                value="${list.idList}"
                                                name="idList"
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
                                            <button type="submit" name="idList" form="delete"
                                                    value="${list.idList}"
                                                    class="btn btn-outline-dark">Delete
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <form action="<c:url value = "/deleteList"/>" method="post" id="delete"></form>


                        </c:forEach>
                    </c:if>
                </div>
            </div>

            <div class="col-lg-12 p-1 p-lg-5 pt-lg-3">
                <div class="input-group">
                    <input type="text" form="addList" class="form-control" name="listName"
                           placeholder="List Name">
                    <button class="btn btn-outline-secondary" type="submit" form="addList">Add list</button>
                    <button class="btn btn-outline-secondary" form="start" type="submit">Back</button>
                    <form action="<c:url value = "/viewAll"/>" id="addList" method="post"></form>
                    <form action="<c:url value = "/start"/>" id="start"></form>
                </div>

                <c:if test="${create == true}">
                    <c:if test="${isListNameProper==false}">
                        <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                            <div class="alert alert-success" role="alert">
                                List '${list.listName}' was successfully created.
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${isListNameProper==true}">
                        <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                            <div class="alert alert-warning" role="alert">
                                List '${list.listName}' already exists or has wrong name
                            </div>
                        </div>
                    </c:if>
                </c:if>

                <c:if test="${delete == true}">
                    <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                        <div class="alert alert-warning" role="alert">
                            List '${deletedList.listName}' was successfully deleted.
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