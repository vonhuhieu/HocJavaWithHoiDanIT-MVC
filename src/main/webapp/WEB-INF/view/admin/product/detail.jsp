<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Product Detail ${id}</title>
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
                                <div class="d-flex justify-content-between">
                                    <h1>Product Detail with id = ${id}</h1>
                                </div>
                                <hr />
                                <div class="card" style="width: 60%">
                                    <div class="card-header">
                                        Product information
                                    </div>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item">ID: ${product.id}</li>
                                        <li class="list-group-item">Name: ${product.name}</li>
                                        <li class="list-group-item">Price: ${product.price}</li>
                                        <li class="list-group-item">Factory: ${product.factory}</li>
                                        <li class="list-group-item">Detail description: ${product.detailDesc}</li>
                                        <li class="list-group-item">Short description: ${product.shortDesc}</li>
                                        <li class="list-group-item">Quantity: ${product.quantity}</li>
                                        <li class="list-group-item">Sold: ${product.sold}</li>
                                        <li class="list-group-item">Target: ${product.target}</li>
                                    </ul>
                                    <img src="/images/product/${product.image}" />
                                </div>
                                <div>
                                    <a href="/admin/product" class="btn btn-success mt-3">Back</a>
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