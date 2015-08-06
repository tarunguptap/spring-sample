<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<%@ include file="/includes/jQuery.jsp"%>
<%@ include file="/includes/bootstrap.jsp"%>
<%@ include file="/includes/taglib.jsp"%>

<c:set var="loggedInUser" value="${loggedInUser}"/>
<html>
  <head>
    
  	<meta name="textHeader" content="<spring:message code='label.add.user.form'/>" />
    <script type="text/javascript">
	    var addUserHandler;
		var messages = {
			emptySpecialCharacterFieldError:'<spring:message code="label.empty.special.character.error" javaScriptEscape="true"/>',
			loading:"<spring:message code='label.processing' javaScriptEscape='true'/>"
		};
		function addUserFunction(messageBundle) {
			
			this.clearErrors = function() {
				$('#errorControl').html('').hide();
				$('#bindingError').html('').hide();
			},
			
			this.showLoading = function(element){
				$(element).button('loading');
				},
			
			this.hideLoading = function(element){
				$('.btn').button('reset');
				},
				
			this.validateuserAdminForm = function() {
					addUserHandler.clearErrors();
					var flag = true;
					$(userAdminForm).each(function() { 
						$('input[type=text],select:not([readonly="true"])', this).each(function() {
							var value = $(this).val();
							if(value == null || value.trim() == '') {
								$(this).val(value.trim());
								addUserHandler.hideLoading();
								$('#errorControl').addClass("alert alert-error").html(messages.emptySpecialCharacterFieldError).show();
								flag=false;
							} 
						});
					});
					if(flag) {
						addUserHandler.hideLoading();
						$("#action").val("saveUser");
						$("#userAdminForm").submit();	 
					}
				};
	}
    $(function() {
    	addUserHandler = new addUserFunction(messages);
    	$('input[type=text], select:not([readonly="true"])').bind("keypress", function(e) {
    		if (e.keyCode == 13)
    		$('#submitUserButton').click();
    	});
    });
 
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
	  <div class="row-fluid">
		<div class="span6" >
	    	<h3 class="alert-heading"><spring:message code='label.add.user.form' /></h3>
	    </div>
	    <div class="span6 pull-right" >
			<a href="<c:url value="j_spring_security_logout" />" > Logout</a>
		</div>
		<div class="span6 pull-right" >
			<a href="<c:url value="welcome" />" > Add Modsub</a>
		</div>
	  </div>
  
    	<div class="row-fluid">
	    	<div class="span6 well">
	    		<form:form name="userAdminForm" id="userAdminForm" method="post" action="?" commandName="user">
	    			<div class="row-fluid">
	    				<div class="span12">
	    					<input type="hidden" name="action" id="action" value=""/>
	    						<div class="control-group">
	    							<label class="control-label" for="username">Enter the User Name</label>
	    							<div class="controls">
	    								<form:input path="username" id="username"/>
	    							</div>
	    						</div>
	    						<div class="control-group">
	    							<label class="control-label" for="password">Enter the Password</label>
	    							<div class="controls">
	    								<form:input path="password" id="password"/>
	    							</div>
	    						</div>
	    						<div class="control-group">
	    							<label class="control-label" for="role">Select the Role</label>
	    							<form:select path="roles" id="roles">
	    								<spring:message code="label.select" var="select" />
                            			<form:option value="" label="${select}"></form:option>
										<c:forEach items="${loggedInUser.roles}" var="role">
											<form:option value="${role.id}" label="${role.description}"/>
										</c:forEach>
	    							</form:select>
	    						</div>
	    						<div class="control-group">
		    						<input type="button" class="btn btn-primary" name="submitUserButton" value="Submit" onclick="addUserHandler.showLoading(this);addUserHandler.validateuserAdminForm();"/>
	    						</div>
	  	 					</div>
	    				</div>
	    			</form:form>
	    		</div>
    		</div>
    </div>
  </body>
</html>
