<%@ include file="taglib.jsp" %>
<c:if test="${!empty errors.allErrors}">
<div id="errorBox" class="alert-box alert-danger">
<ul>
    <c:forEach var="error" items="${errors.allErrors}" varStatus="loopStatus">
			<%
		        java.util.List argList = new java.util.ArrayList();
		    %>
			<c:forEach var="argument" items="${error.arguments}" varStatus="loopStatus">
				<li><spring:message code="${argument}" var="argConverted" text="${argument}"/></li>
				<%
					argList.add(pageContext.getAttribute("argConverted"));
				 %>
		    </c:forEach>
			<c:choose> 
				<c:when test="${error.code != 'typeMismatch'}" > 
  					<li><spring:message code="${error.code}" arguments="<%= argList.toArray(new Object[argList.size()]) %>"/></li>
  				</c:when>
  				<c:otherwise>
  				<li><spring:message message="${error}"/></li>
  				</c:otherwise>
  	         </c:choose>			
	</c:forEach>
</ul>	
</div>

</c:if>