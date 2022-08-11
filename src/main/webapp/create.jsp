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
<main class="container pt-5">
    <div class="container pt-5">
        <div class="row p-4 pb-0 pe-lg-0 pt-lg-5 align-items-center rounded-3 border shadow-lg">
            <div class="col-lg-7 p-3 p-lg-5 pt-lg-3">
                <h1 class="display-4 fw-bold lh-1 text-light">ToDoList</h1>
                <p class="lead text-light">Welcome to ToDoList by Aleksei Chirkun</p>

                <form action=<c:url value="/create"/> method="post">
                    <div class="form-floating mb-1">
                        <textarea class="form-control" name="listName"
                        placeholder="listName" id="listName"></textarea>
                        <label for="listName">List Name</label>
                    </div>

                    <div class="d-grid gap-2 d-md-flex py-2 justify-content-md-start mb-4 mb-lg-3">
                        <button type="submit" class="btn btn-outline-secondary">
                            Add list
                        </button>
                    </div>
                </form>

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