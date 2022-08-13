
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
                    <form action="/start" method="get">
                        <button type="submit" class="btn btn-light">Sign in</button>
                    </form>
                </div>
                </li>
            </ul>
        </div>
    </div>
</header>
<main class="container pt-5">
    <div class="d-flex justify-content-center p-3 ">
        <div style="background: rgba(10,6,6,0.33); color: rgba(255,255,255,0.63)" class="p-5 rounded-2 border" >
            <p> <a href="/start">ToDoList</a> by Aleksei Chirkun. August, 2022.</p>
            - Java SE </br>
            - Java EE </br>
            - JDBC, PostgreSQL </br>
            - Maven </br>
            - HTML, CSS, Bootstrap</p>

            Find out more on <a href="https://www.linkedin.com/in/theoldestnewborn" target="_blank"> Linkedin</a>.

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