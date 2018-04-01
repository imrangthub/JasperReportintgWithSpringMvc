<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/_partial/_header.jsp" />
<div class="container">
	<!-- Example row of columns -->
	<div class="row">
	
	<h1>Current Book is:</h1>
       <a class="btn btn-primary btn-lg" href="/JasperReportintgWithSpringMvc/book/" role="button">Go to Book</a>
       <br><br>
       
       <table style="width:100%">
              <tr>
                  <td>Book ID: </td><td>${singleObj.id}</td>
                  <td>Book Name: </td><td>${singleObj.name}</td>
                  <td>Book Type: </td><td>${singleObj.type}</td>
              </tr>
       </table>
       


	</div>
	<jsp:include page="/WEB-INF/views/_partial/_footerMenu.jsp" />
</div>
<!-- /container -->
<jsp:include page="/WEB-INF/views/_partial/_footer.jsp" />