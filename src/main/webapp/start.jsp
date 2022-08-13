<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="color" type="java.lang.String"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>ToDoLost</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ToDoList</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
    />
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
    />
    <!-- MDB -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.2.0/mdb.min.css"
            rel="stylesheet"
    />
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
                    <form action="/start.jsp" method="get">
                        <button type="submit" class="btn btn-light btn-outline-dark">Login</button>
                    </form>
                </div>
                </li>
            </ul>
        </header>
    </div>
</header>
<main class="container">

    <div class="d-flex justify-content-center p-3 ">
        <div style="background: rgba(10,6,6,0.33)" class="p-5 rounded-2 border ">
            <!-- Pills navs -->
            <ul class="nav nav-pills nav-justified mb-3 " role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" id="tab-login" data-mdb-toggle="pill" href="#pills-login" role="tab"
                       aria-controls="pills-login" aria-selected="true">Login</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link" id="tab-register" data-mdb-toggle="pill" href="#pills-register" role="tab"
                       aria-controls="pills-register" aria-selected="false">Register</a>
                </li>
            </ul>

            <!-- Pills content -->
            <div class="tab-content">
                <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                    <form action="<c:url value = "/login"/>" method="get">

                        <!-- Login input -->
                        <div class="form-outline mb-4">
                            <input type="text" id="login" name="login" class="form-control"/>
                            <label class="form-label" for="login">Login</label>
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-4">
                            <input type="password" id="loginPassword" name="password" class="form-control"/>
                            <label class="form-label" for="loginPassword">Password</label>
                        </div>

                        <!-- Submit button -->
                        <button type="submit" class="btn btn-primary btn-block mb-4">Sign up</button>

                    </form>
                </div>
                <div class="tab-pane fade" id="pills-register" role="tabpanel" aria-labelledby="tab-register">
                    <form action="<c:url value = "/register"/>" method="post">
                        <div class="text-center mb-3">

                            <!-- Username input -->
                            <div class="form-outline mb-4">
                                <input type="text" id="registerLogin" name="login" class="form-control"/>
                                <label class="form-label" for="registerLogin">Login</label>
                            </div>

                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <input type="email" id="registerEmail" name="email" class="form-control"/>
                                <label class="form-label" for="registerEmail">Email</label>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-4">
                                <input type="password" id="registerPassword" name="password" class="form-control"/>
                                <label class="form-label" for="registerPassword">Password</label>
                            </div>

                            <!-- Repeat Password input -->
                            <div class="form-outline mb-4">
                                <input type="password" id="registerRepeatPassword" name="repeatPassword"
                                       class="form-control"/>
                                <label class="form-label" for="registerRepeatPassword">Repeat password</label>
                            </div>

                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-block mb-3">Sign in</button>
                        </div>
                    </form>
                </div>
            </div>

            <c:if test="${register==true}">
                <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                    <div class="alert alert-success" role="alert">
                            ${message}
                    </div>
                </div>
            </c:if>
            <c:if test="${register==false}">
                <div class="card bg-dark text-white mt-2" style="border-radius: 1rem;">
                    <div class="alert alert-warning" role="alert">
                            ${message}
                    </div>
                </div>
            </c:if>


        </div>
    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
    <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.2.0/mdb.min.js"
    ></script>
</main>
</body>
</html>