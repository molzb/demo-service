<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Countries</title>
  </head>
  <body>
    <h1>Countries</h1>
    
      <table border="1">
        <tr>
          <th>ID</th>
          <th>Name</th>
        </tr>
        <c:forEach items="${countries}" var="c">
        <tr>
          <td>${c.id}</td>
          <td>${c.name}</td>
        </tr>
        </c:forEach>
      </table>
  </body>
  
</html>