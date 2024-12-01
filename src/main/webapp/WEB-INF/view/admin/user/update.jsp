<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Update User ${newUser.id}</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp"></jsp:include>
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp"></jsp:include>
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Manage User</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Users</li>
                                </ol>
                            </div>
                            <div class="container-fluid px-4 mt-5">
                                <div class="container mt-5">
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h3 style="text-align: left;">Update User</h3>
                                            <hr />
                                            <form:form class="row g-3" method="post" action="/admin/user/update"
                                                modelAttribute="newUser" enctype="multipart/form-data">
                                                <div class="col-md-12" style="display: none;">
                                                    <label class="form-label">ID</label>
                                                    <form:input type="text" path="id" class="form-control" />
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Email address</label>
                                                    <form:input type="email" path="email" class="form-control"
                                                        disabled="true" />
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Fullname</label>
                                                    <form:input type="text" path="fullName" class="form-control" />
                                                </div>
                                                <div class="col-md-12">
                                                    <label class="form-label">Phone</label>
                                                    <form:input type="text" path="phone" class="form-control" />
                                                </div>
                                                <div class="col-12">
                                                    <label class="form-label">Address</label>
                                                    <form:input type="text" path="address" class="form-control" />
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Role</label>
                                                    <form:select class="form-select" path="role.name">
                                                        <form:option value="ADMIN">Admin</form:option>
                                                        <form:option value="USER">User</form:option>
                                                    </form:select>
                                                </div>
                                                <div class="col-md-6">
                                                    <label for="avatarFile" class="form-label">Avatar</label>
                                                    <input class="form-control" type="file" id="avatarFile"
                                                        accept=".png, .jpg, .jpeg" name="hoidanitFile" />
                                                </div>
                                                <div class="col-12">
                                                    <img style="max-height: 250px; display: block;" alt="avatar preview"
                                                        src="/images/avatar/${newUser.avatar}" id="avatarPreview" />
                                                </div>
                                                <div class="col-12">
                                                    <button type="submit" class="btn btn-primary">Update</button>
                                                </div>
                                            </form:form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp"></jsp:include>
                    </div>

                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>
            </body>

            </html>