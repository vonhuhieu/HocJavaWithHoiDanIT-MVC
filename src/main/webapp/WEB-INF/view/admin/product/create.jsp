<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Create a product</title>
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
                                <h1 class="mt-4">Manage Products</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Products</li>
                                </ol>
                            </div>
                            <div class="container-fluid px-4 mt-5">
                                <div class="container mt-5">
                                    <div class="row">
                                        <div class="col-md-8 col-12 mx-auto">
                                            <h3 style="text-align: left;">Create a product</h3>
                                            <hr />
                                            <form:form class="row g-3" method="post" action="/admin/product/create"
                                                enctype="multipart/form-data" modelAttribute="newProduct">
                                                <div class="col-md-6">
                                                    <label class="form-label">Name</label>
                                                    <form:input type="text" path="name" class="form-control" />
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Price</label>
                                                    <form:input path="price" type="number" class="form-control" />
                                                </div>
                                                <div class="col-md-12">
                                                    <label class="form-label">Detail description</label>
                                                    <form:textarea type="text" path="detailDesc" class="form-control" />
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Short description</label>
                                                    <form:input type="text" class="form-control" path="shortDesc" />
                                                </div>
                                                <div class="col-6">
                                                    <label class="form-label">Quantity</label>
                                                    <form:input type="number" path="quantity" class="form-control" />
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Factory</label>
                                                    <form:select class="form-select" path="factory">
                                                        <form:option value="APPLE">Apple (MacBook)</form:option>
                                                        <form:option value="ACER">ACER</form:option>
                                                        <form:option value="ASUS">ASUS</form:option>
                                                    </form:select>
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="form-label">Target</label>
                                                    <form:select class="form-select" path="target">
                                                        <form:option value="GAMING">GAMING</form:option>
                                                        <form:option value="MONG-NHE">Mỏng nhẹ</form:option>
                                                        <form:option value="DOANH-NHAN">Doanh nhân</form:option>
                                                    </form:select>
                                                </div>
                                                <div class="col-md-6">
                                                    <label for="avatarFile" class="form-label">Image</label>
                                                    <input class="form-control" type="file" id="avatarFile"
                                                        accept=".png, .jpg, .jpeg" name="hoidanitFile" />
                                                </div>
                                                <div class="col-12">
                                                    <img class="col-6" style="max-height: 350px; display: none;"
                                                        alt="avatar preview" id="avatarPreview" />
                                                </div>
                                                <div class="col-12">
                                                    <button type="submit" class="btn btn-primary">Create</button>
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