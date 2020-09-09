<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html lang="en">
<head>
    <!-- Access the bootstrap Css like this,
        Spring boot will handle the resource mapping automcatically -->
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
    <!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
<div>
    <div>
        <h2>Add city info</h2>
        <label for="name">City name</label>
        <input type="text" name="name" id="name"/>
        <br>
        <label for="info">City info</label>
        <input type="text" name="info" id="info"/>
        <br>
        <button class="create" onclick="save()">Save</button>
    </div> </td>
</div>

<br>
<br>
<br>
<br>
<div>
    <table>
    <tr>
        <th>City name</th>
        <th>City info</th>
        <th>DELETE</th>
    </tr>
    <c:forEach items="${cities}" var="city">
        <tr>
            <td>${city.name}</td>
            <td>${city.info}</td>
            <td><button class="danger" onclick="destroy('${city.name}')">Delete</button></td>
        </tr>
    </c:forEach>
    </table>
</div>

<script>
    function destroy(name){
        let xhr = new XMLHttpRequest();
        let url = "/manager/cities/" + name;
        xhr.open("DELETE", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                window.location.reload();
            }
        };
        xhr.send();
    }

    function save() {
        let xhr = new XMLHttpRequest();
        let url = "/manager/cities";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                window.location.reload();
            }
        };
        let name = document.getElementById("name").value;
        let info = document.getElementById("info").value;
        if (!name || !info){
            return;
        }
        let data = JSON.stringify({"name": name, "info": info});
        xhr.send(data);
    }
</script>
</body>

</html>