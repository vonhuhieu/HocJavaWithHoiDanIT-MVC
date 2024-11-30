<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Update User ${newUser.id}</title>
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h3 style="text-align: center;">Update User</h3>
                            <hr />
                            <form:form method="post" action="/admin/user/update" modelAttribute="newUser">
                                <div class="mb-3" style="display: none;">
                                    <label class="form-label">ID</label>
                                    <form:input type="text" path="id" class="form-control" value="${newUser.id}" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Email address</label>
                                    <form:input type="email" path="email" class="form-control" value="${newUser.email}"
                                        readOnly="readOnly" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Fullname</label>
                                    <form:input type="text" path="fullName" class="form-control"
                                        value="${newUser.fullName}" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Address</label>
                                    <form:input type="text" path="address" class="form-control"
                                        value="${newUser.address}" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Phone</label>
                                    <form:input type="text" path="phone" class="form-control"
                                        value="${newUser.phone}" />
                                </div>
                                <button type="submit" class="btn btn-primary">Update</button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </body>

            </html>