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
        <div class="d-flex flex-wrap justify-content-center mb-5 pt-5 pb-2 px-4 border-bottom">
            <div class="d-flex align-items-center p-0 mb-3 mb-md-0 me-md-auto text-light text-decoration-none ">
                <div class="display-4 lh-0 text-light p-2 rounded-4" style="background: rgba(173,181,189,0.22)">ToDoList Project</div>
            </div>

            <ul class="nav nav-pills justify-content-between">
                <div class="align-items-end d-flex m-2">
                    <form action="/about.jsp">
                        <button type="submit" class="btn btn-light">About</button>
                    </form>
                </div>
                <div class="align-items-end d-flex m-2 ">
                    <form action="/signOut" method="get">
                        <button type="submit" class="btn btn-light">Sign Out</button>
                    </form>
                </div>
                </li>
            </ul>
        </div>
    </div>
</header>
<main class="container">
    <div class="container pt-5">
        <div class="row p-lg-4 p-1 pb-3 pe-lg-0 pt-lg-5 align-items-center rounded-3 border shadow-lg">
            <div class="col-lg-12 p-1 p-lg-5 pt-lg-3">
                <h1 class="display-4 fw-bold lh-1 text-light">ToDoList</h1>
                <p class="lead text-light">Current list: '${list.listName}'</p>

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
                                value="${list.idList},${task.taskBody}"
                                name="listAndTask"
                                class="btn btn-outline-dark  col-lg-12 col-12">
                            <c:forEach items="${allTasks}" var="task">
                                <c:if test="${task.active==true}">
                                    🔥
                                </c:if>
                            </c:forEach>
                            <c:out value="${task.taskBody}"/>

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
                                    value="${list.idList},${task.taskBody}"
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
                                    value="${list.idList},${task.taskBody}"
                                    class="btn btn-outline-dark col-lg-1 col-3">Delete
                            </button>
                            <form action="/deleteTask" method="post" id="delete"></form>



                            <c:if test="${task.active==false}">
                                <button type="submit" form="set_active" name="listAndTask"
                                        value="${list.idList},${task.taskBody},true"
                                        class="btn btn-outline-dark col-lg-1 col-3">Set Active 🔥
                                </button>
                                <form action="/setTaskActive" method="post" id="set_active"></form>
                                <form action="/setTaskComplete" method="post" id="set_complete"></form>

                            </c:if>
                            <c:if test="${task.active==true}">
                                <button type="submit" form="set_active" name="listAndTask"
                                        value="${list.idList},${task.taskBody},false"
                                        class="btn btn-outline-dark col-lg-1 col-3">Set Inactive 🧯
                                </button>
                                <form action="/setTaskActive" method="post" id="set_active"></form>
                                <form action="/setTaskComplete" method="post" id="set_complete"></form>

                            </c:if>


                            <c:if test="${task.complete==false}">
                                <button type="submit" form="set_complete" name="listAndTask"
                                        value="${list.idList},${task.taskBody},true"
                                        class="btn btn-outline-dark col-lg-1 col-3">Set Complete
                                    <div class="badge bg-success rounded-pill ">
                                        ✓
                                    </div>
                                </button>
                                <form action="/setTaskComplete" method="post" id="set_complete"></form>

                            </c:if>
                            <c:if test="${task.complete==true}">
                                <button type="submit" form="set_complete" name="listAndTask"
                                        value="${list.idList},${task.taskBody},false"
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
                                name="idList"
                                value="${list.idList}"
                                class="btn btn btn-outline-dark col-lg-12 col-12">Back
                        </button>
                        <form action="/viewAllTasks" method="get" id="back"></form>
                    </div>
                </div>
                <c:if test="${update==true}">
                    <c:if test="${hasProperName==true}">
                        <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                            <div class="alert alert-success" role="alert">
                                Task was successfully updated.
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${hasProperName==false}">
                        <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                            <div class="alert alert-warning" role="alert">
                                Task '${taskWithWrongName.taskBody}' already exists or has wrong name.
                            </div>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>
</main>

<footer>

</footer>

</script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>