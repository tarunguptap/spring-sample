<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<%@ include file="/includes/jQuery.jsp"%>
	<%@ include file="/includes/bootstrap.jsp"%>
	<%@ include file="/includes/taglib.jsp"%>
  <title><spring:message code="label.login" /></title>
</head>

<body>
<div class="container-fluid">		
 <div class="row-fluid">  
	    <div class="span6">
			<div id="textSubHeader">
				<h1>
					<spring:message code="label.login" />
				</h1>
			</div>
		    <c:if test="${not empty error}">
		    		<div id="errorBox" class="alert-box alert-danger">
						${error}
					</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="alert-block alert-success">${msg}</div>
			</c:if>
	      	<form method="post" action="<c:url value='/j_spring_security_check' />" class="form-inline well">
		        <fieldset>
		          <spring:bind path="command.username">
		          <div id="usernameControl" class="control-group <c:if test="${status.error}">error</c:if>">
		            <label class="control-label" for="username"><spring:message code="label.username"/></label>
		            <div class="controls">
		              <input id="username" name="j_username" type="text" value="${status.value}"/>
		              <span class="help-inline"></span>
		            </div>
		          </div>
		          </spring:bind>
		          <spring:bind path="command.password">
		          <div id="passwordControl" class="control-group <c:if test="${status.error}">error</c:if>">
		            <label class="control-label" for="password"><spring:message code="label.password"/></label>
		            <div class="controls">
		              <input id="password" name="j_password" type="password"/>
		              <span class="help-inline"></span>
		            </div>
		          </div>
		          </spring:bind>
		          <div>
		            <input class="btn btn-primary" type="submit" value="<spring:message code='label.login'/>"/>
		          </div>
		        </fieldset>
	       </form>
	    </div>
	 </div>
</div>

<script language="javascript" type="text/javascript">
	<!--
		document.getElementById('username').focus();
	//-->
	</script>

</body>
</html>