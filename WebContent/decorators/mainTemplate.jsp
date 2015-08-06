<%@ taglib prefix="dec" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html>
  <head>
    <%@ include file="/includes/jQuery.jsp"%>
	<%@ include file="/includes/bootstrap.jsp"%>
	<%@ include file="/includes/taglib.jsp"%>
	<title><dec:title default="SiteMesh Integration"/></title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<dec:head />
  </head>
  <body>
  
	  <div class="container-fluid">
	  	<dec:body>
	  	  <div class="navbar">
			  <div class="navbar-inner">
			    <div class="container-fluid">
			    	<a href="<c:url value="userAdmin"/>">User Admin</a>
			    </div>
			    
			    <div class="container-fluid">
			    	<a href="<c:url value="hello"/>">Hello</a>
			    </div>
			    
			     <div class="container-fluid" >
					<a href="<c:url value="j_spring_security_logout"/>" > Logout</a>
				</div>
			  </div>
		   </div>
		 </dec:body>
	  </div>
  </body>
</html>
