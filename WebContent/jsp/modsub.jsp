<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>


<html>
  <head>
    <%@ include file="/includes/jQuery.jsp"%>
  	<%@ include file="/includes/bootstrap.jsp"%>
  	<%@ include file="/includes/taglib.jsp"%>
  	
    <title>
      Login Form
    </title>
 <script type="text/javascript" src="/assets/js/jquery-1.3.2.min.js"></script>
 
  <script type="text/javascript">
 function setAction(val)
 {
		document.loginForm.oper.value=val;
		document.loginForm.action="<%=request.getContextPath()%>/submit";
		document.loginForm.submit();
		return true;
 	}
 
 
</script>
  </head>
  
  <body>
  <div class="container-fluid">
  	<div class="row-fluid">
		<div class="span6" >
			<div id="errorControl"> </div>
				<div id="bindingError">
				<%@ include file="/includes/messages.jsp"%>
				<spring:hasBindErrors name="userAdminCommand">
					<%@ include file="/includes/errors.jsp"%>
				</spring:hasBindErrors>
			</div>
		</div>
	  </div>
    
    	<div class="span6">
    		<div class="row-fluid">
				<div class="span6" >
			    	<h3 class="alert-heading">Add Information</h3>
			    </div>
			    <div class="span6 pull-right" >
					<a href="<c:url value="j_spring_security_logout" />" > Logout</a>
				</div>
				<div class="span6 pull-right" >
					<a href="<c:url value="userAdmin" />" > Add User Detail</a>
				</div>
			  </div>
		</div>	
		<div class="row-fluid">
    		<form name=loginForm method="post" class="form-inline well" commandName="submitform">
    			<div class="row-fluid">
    				<div class="span12">
    					<input type="hidden" name="oper" id="oper"/>
    						<div class="control-group">
    							<label class="control-label" for="userid">Enter the User ID</label>
    							<div class="controls">
    								<input type="text" class="span2" name="uid" />
    							</div>
    						</div>
    						<div class="control-group">
    							<label class="control-label" for="username">Enter the User Name</label>
    							<div class="controls">
    								<input type="text" class="span2" name="uname" />
    							</div>
    						</div>
    						<div class="control-group">
	    						<input type="button" class="btn btn-primary" name="btnSubmit" value="Submit" onclick="setAction('saveUser')"/>
	   							<input type="button" class="btn" name="btnEdit" value="Edit" onclick="setAction('editUser')"/>
	  	 						<input type="button" class="btn" name="btnDelete" value="Delete" onclick="setAction('deleteUser')"/>
  	 						</div>
    					</div>
    				</div>
    			</form>
    		</div>
    	</div>
    </div>
  </body>
</html>
