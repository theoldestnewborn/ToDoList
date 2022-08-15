<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>ToDoLost</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ToDoList</title>

    <link  href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.2.0/mdb.min.css"
          rel="stylesheet"/>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="custom.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body class="body container-fluid">
<header class="pt-5" >
    <div class="container">
        <div class="d-flex flex-wrap justify-content-center mb-5 pt-5 pb-2 px-4 border-bottom">
            <div class="d-flex align-items-center p-0 mb-3 mb-md-0 me-md-auto text-light text-decoration-none ">
                <div class="display-4 lh-0 text-light p-2 rounded-4" style="background: rgba(173,181,189,0.22)">ToDoList
                    Project
                </div>
            </div>

            <ul class="nav nav-pills justify-content-between">
                <div class="align-items-end d-flex m-2">
                    <form action="/to-do-list/about.jsp">
                        <button type="submit" class="btn btn-light">About</button>
                    </form>
                </div>
                <div class="align-items-end d-flex m-2 ">
                    <form action="/to-do-list/start.jsp" method="get">
                        <button type="submit" class="btn btn-light">Sign in</button>
                    </form>
                </div>
                </li>
            </ul>
        </div>
    </div>
</header>
<main class="container">

    <div class="d-flex justify-content-center p-3 ">
        <div style="background: rgba(10,6,6,0.33)" class="p-5 rounded-2 border ">
            <!-- Pills navs -->
            <ul class="nav nav-pills nav-justified mb-3 " role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active " id="tab-login" data-mdb-toggle="pill" href="#pills-login" role="tab"
                       aria-controls="pills-login" aria-selected="true">Login</a>
                </li>
                <li class="nav-item" role="presentation">
                    <a class="nav-link p-3" id="tab-register" data-mdb-toggle="pill" href="#pills-register" role="tab"
                       aria-controls="pills-register" aria-selected="false">Sign up</a>
                </li>
            </ul>

            <!-- Pills content -->
            <div class="tab-content">
                <div class="tab-pane fade show active" id="pills-login" role="tabpanel" aria-labelledby="tab-login">
                    <form action="<c:url value = "/login"/>" method="get">

                        <!-- Login input -->
                        <div class="form-outline mb-4">
                            <input type="text"
                                   style="color: rgba(255,255,255,0.8); background: rgba(0,0,0,0.07)"
                                   id="login" name="login"
                                   class="form-control"/>
                            <label class="form-label" style="color: rgba(255,255,255,0.42)"
                                   for="login">Login</label>
                        </div>

                        <!-- Password input -->
                        <div class="form-outline mb-4">
                            <input type="password"
                                   style="color: rgba(255,255,255,0.8); background: rgba(0,0,0,0.07)"
                                   id="loginPassword"
                                   name="password" class="form-control"/>
                            <label class="form-label" style="color: rgba(255,255,255,0.42)"
                                   for="loginPassword">Password</label>
                        </div>

                        <!-- Submit button -->
                        <button type="submit" class="btn btn-primary btn-block mb-4">Sign in</button>

                    </form>
                </div>

                <div class="tab-pane fade" id="pills-register" role="tabpanel" aria-labelledby="tab-register">
                    <form action="<c:url value = "/register"/>" method="post">
                        <div class="text-center mb-3">

                            <!-- Username input -->
                            <div class="form-outline mb-4">
                                <input type="text"
                                       style="color: rgba(255,255,255,0.8); background: rgba(0,0,0,0.07)"
                                       id="registerLogin" name="login"
                                       class="form-control"/>
                                <label class="form-label"
                                       style="color: rgba(255,255,255,0.8); background: rgba(0,0,0,0.07)"
                                       for="registerLogin">Login</label>
                            </div>

                            <!-- Email input -->
                            <div class="form-outline mb-4">
                                <input type="email"
                                       style="color: rgba(255,255,255,0.8); background: rgba(0,0,0,0.07)"
                                       id="registerEmail" name="email"
                                       class="form-control"/>
                                <label class="form-label" style="color: rgba(255,255,255,0.42)" for="registerEmail">Email</label>
                            </div>

                            <!-- Password input -->
                            <div class="form-outline mb-4">
                                <input type="password"
                                       style="color: rgba(255,255,255,0.8); background: rgba(0,0,0,0.07)"
                                       id="registerPassword"
                                       name="password" class="form-control"/>
                                <label class="form-label" style="color: rgba(255,255,255,0.42)" for="registerPassword">Password</label>
                            </div>

                            <!-- Repeat Password input -->
                            <div class="form-outline mb-4">
                                <input type="password"
                                       style="color: rgba(255,255,255,0.8); background: rgba(0,0,0,0.07)"
                                       id="registerRepeatPassword"
                                       name="repeatPassword"
                                       class="form-control"/>
                                <label class="form-label" style="color: rgba(255,255,255,0.42)"
                                       for="registerRepeatPassword">Repeat password</label>
                            </div>

                            <!-- Submit button -->
                            <button type="submit" class="btn btn-primary btn-block mb-3">Sign up</button>
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

</main>
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/4.2.0/mdb.min.js">
</script>

<script src="js/bootstrap.min.js"></script>
</body>
</html>