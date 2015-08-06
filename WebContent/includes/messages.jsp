<%@ include file="taglib.jsp" %>
<c:if test="${!empty messages}">
	<div id="message" class="alert-block alert-success">
	    <ul>
		  <c:forEach items="${messages}" var="message">
	        <li><spring:message code="${message}" text="${message}"/></li>
	 	  </c:forEach>    
	    </ul>
	</div>
</c:if>	