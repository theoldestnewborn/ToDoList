<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>ToDoList</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="custom.css">
</head>

<body class="body container-fluid">
<header>

</header>
<main class="container pt-5">
    <div class="container pt-5">
        <div class="row p-lg-4 p-1 pb-3 pe-lg-0 pt-lg-5 align-items-center rounded-3 border shadow-lg">
            <div class="col-lg-12 p-1 p-lg-5 pt-lg-3">
                <h1 class="display-4 fw-bold lh-1 text-light">ToDoList</h1>
                <p class="lead text-light">Current list: '${listName}'</p>

                <p class="lead text-light">Here you can edit your task</p>

                <div class="d-grid gap-2
                                    mb-1 list-group list-group-item-dark
                                    align-items-center"
                     data-bs-toggle="collapse"
                     href="#collapse"
                     role="button"
                     aria-expanded="false"
                     aria-controls="collapse">
                    <div class="btn-group">
                        <button type="submit"
                                value="${task.listName},${task.task_body}"
                                name="listAndTask"
                                class="btn btn-outline-dark  col-lg-12 col-12">
                            <c:out value="${task.task_body}"/>

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

                        </button>
                    </div>
                </div>

                <div class="collapse" id="collapse">
                    <form action="/editTask" method="post" id="editTask">
                        <div class="mb-2 mt-2">
                        <textarea class="form-control" form="editTask" name="newTaskBody"
                                  id="FormTextarea"
                                  rows="2" placeholder="Type here"></textarea>
                        </div>
                        <div class="d-grid gap-2
                            mb-1 list-group list-group-item-dark
                            align-items-center">
                            <button type="submit"
                                    value="${task.listName},${task.task_body}"
                                    name="listAndTask"
                                    form="editTask"
                                    class="btn btn-outline-dark"> Update
                            </button>
                        </div>
                    </form>
                </div>

                <div class="mb-3">
                    <div class="d-grid gap-2
                            mb-1 list-group list-group-item-dark
                            align-items-center">
                        <div class="btn-group">
                            <div class="btn btn-outline-dark col-lg-1 col-3"
                                 data-bs-toggle="collapse"
                                 href="#collapse"
                                 role="button"
                                 aria-expanded="false"
                                 aria-controls="collapse">Update Task
                            </div>
                            <form action="/viewAllTasks" method="post" id="edit_body"></form>

                            <button type="submit" form="delete" name="listAndTask"
                                    value="${task.listName},${task.task_body}"
                                    class="btn btn-outline-dark col-lg-1 col-3">Delete
                            </button>
                            <form action="/deleteTask" method="post" id="delete"></form>



                            <c:if test="${task.active==false}">
                                <button type="submit" form="set_active" name="listAndTask"
                                        value="${task.listName},${task.task_body},true"
                                        class="btn btn-outline-dark col-lg-1 col-3">Set Active 🔥
                                </button>
                                <form action="/setTaskActive" method="post" id="set_active"></form>
                                <form action="/setTaskComplete" method="post" id="set_complete"></form>

                            </c:if>
                            <c:if test="${task.active==true}">
                                <button type="submit" form="set_active" name="listAndTask"
                                        value="${task.listName},${task.task_body},false"
                                        class="btn btn-outline-dark col-lg-1 col-3">Set Inactive 🧯
                                </button>
                                <form action="/setTaskActive" method="post" id="set_active"></form>
                                <form action="/setTaskComplete" method="post" id="set_complete"></form>

                            </c:if>








                            <c:if test="${task.complete==false}">
                                <button type="submit" form="set_complete" name="listAndTask"
                                        value="${task.listName},${task.task_body},true"
                                        class="btn btn-outline-dark col-lg-1 col-3">Set Complete
                                    <div class="badge bg-success rounded-pill ">
                                        ✓
                                    </div>
                                </button>
                                <form action="/setTaskComplete" method="post" id="set_complete"></form>

                            </c:if>
                            <c:if test="${task.complete==true}">
                                <button type="submit" form="set_complete" name="listAndTask"
                                        value="${task.listName},${task.task_body},false"
                                        class="btn btn-outline-dark col-lg-1 col-3">Set Incomplete
                                    <div class="badge bg-warning rounded-pill ">
                                        ❌
                                    </div>
                                </button>
                                <form action="/setTaskComplete" method="post" id="set_complete"></form>

                            </c:if>
                            <form action="/viewAll" method="post">

                            </form>

                        </div>
                    </div>
                    <div class="d-grid
                             list-group list-group-item-dark
                            align-items-center">
                        <button type="submit" form="back"
                                name="listName"
                                value="${task.listName}"
                                class="btn btn btn-outline-dark col-lg-12 col-12">Back
                        </button>
                        <form action="/viewAllTasks" method="get" id="back"></form>
                    </div>
                </div>

                <div class="col-10 justify-content-between d-flex align-items-center">
                    <c:forEach items="${allTasks}" var="task">
                        <c:if test="${task.active==true}">
                            <div class="lead text-light bg-warning p-2" style="word-break: break-all">
                                <c:out value="Active task: '${task.task_body}' of '${listName}' list"/>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>
</main>

<footer>

</footer>
<script src="js/bootstrap.min.js"></script>
</body>
</html>