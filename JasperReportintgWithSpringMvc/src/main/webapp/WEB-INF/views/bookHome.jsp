<%@ page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/_partial/_header.jsp" />
<div class="container">
	<!-- Example row of columns -->
	<div class="row">
	
	<h1>Book home</h1>
	       <a class="btn btn-success pull-right" href="/JasperReportintgWithSpringMvc/report/all" role="button">Print Book List</a><br><br>
	     <!-- Example row of columns -->
      <div class="row">
 	       <div class="col-md-12 well">
 	          <table style="width:100%">
 	                 <tr>
 	                    <td>#</td><td>Title</td><td>Type</td><td>Action</td>
 	                 </tr>
 	                 <c:forEach items="${bookList }" var="book">
 	                     <tr>
 	                         <td>${book.id }</td><td>${book.name }</td><td>${book.type }</td>
 	                         <td>
 	                            <a href="/JasperReportintgWithSpringMvc/book/singleShow?id=${book.id}">Show</a> || 
 	                            <a href="/JasperReportintgWithSpringMvc/report/singlePrint?id=${book.id}">Print</a>
 	                         </td>
 	                     </tr>
 	                 </c:forEach>
 	                 
 	          </table>
 	       </div>	       
      </div>

	</div>
	<jsp:include page="/WEB-INF/views/_partial/_footerMenu.jsp" />
</div>
<!-- /container -->
<jsp:include page="/WEB-INF/views/_partial/_footer.jsp" />