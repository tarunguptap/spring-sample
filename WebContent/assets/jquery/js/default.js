function initRequest()
	{
		if (window.ActiveXObject) 
		{ 
	   		req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	    else if(window.XMLHttpRequest)
	   	{
	    	req = new XMLHttpRequest();
	    }
	    return req;
	}
	function validateNumber(value)
	{
		if(value=="" || value==0)
		{
			return false;
		}
		if(!validateSplCharAndSpace(value))
		{
			return false;
		}
		if(isNaN(value))
		{
			return false;
		}
		if(value.charAt(0)=="0")
		{
			return false;
		}
		return true;
	}
	
	function validateNumberWithZero(value)
	{
		if(value=="")
		{
			return true;
		}
		else if(value.length!=10)
		{
			return false;
		}
		if(!validateSplCharAndSpace(value))
		{
			return false;
		}
		if(isAlphabeticOnly(value))
		{
			return false;
		}
		return true;
	}
	function isAlphabeticOnly(val) { 
		
		var iChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
		if(val=="")
		{
			return true;
		}
		else
		{
		for (var i = 0; i < val.length; i++) {
			if (iChars.indexOf(val.charAt(i)) == -1) {
	  			return false;
	  		}
	  	}
		}
	  	return true;
	}
	
	function validateSpace(value)
	{
	 	var flag = true;
		var len=value.length;
 		for (var i=0;i<len;i++)
    	{
		     var c = value.charAt(i);
			 if(c==" ")
		     {
		    	flag = false;
				break;
		   	} 
    	}
    	return flag;
	}
	function isAlphaNumeric(val) {   
		var iChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";
		
		if(val.length == 0) {
			return false;
		}

		for (var i = 0; i < val.length; i++) {
			if (iChars.indexOf(val.charAt(i)) == -1) {
	  			return false;
	  		}
	  	}
	  	return true;
	}
	function isAlphabetic(val) {   
		var iChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
		
		if(val.length == 0) {
			return false;
		}

		for (var i = 0; i < val.length; i++) {
			if (iChars.indexOf(val.charAt(i)) == -1) {
	  			return false;
	  		}
	  	}
	  	return true;
	}
	function validateName(value)
	{
		if(value=="")
		{
			return false;
		}
		var len=value.length;
 		for (var i=0;i<len;i++)
    	{
		     var c = value.charAt(i);
			 if(c=="\'" || c=="\=" || c=="\<" || c=="\>")
		     {
		    	return false;
		   	} 
    	}
		if(value.charAt(0)==" " || value.charAt(value.length-1)==" ")
		{
			return false;
		}
		return true;
	}
	function validateName1(value)
	{
		if(value=="" || value=="0")
		{
			return false;
		}
		if(value.charAt(0)==" " || value.charAt(value.length-1)==" ")
		{
			return false;
		}
		return true;
	}
	function validateId(value)
	{
		if(value=="" || value=="0")
		{
			return false;
		}
		return validateSplCharAndSpace(value);
	}
	function validateSplCharAndSpace(value)
	{
	 	var flag = true;
		var msg="";
		if(value=="")
		{
			return true;
		}
		else
		{
		
		var len=value.length;
		var i=0;
	    for (i=0;i<len;i++)
	    {
	     var c = value.charAt(i);
	      if(c=="!")
	        {
	    	flag = false;
	        break;
	    	}
	    	if(c=="@")
	        {
	    	flag = false;
	    	 break;
	    	}
	    	if(c=="$")
	        {
	    	flag = false;
	    	 break;
	    	}
	    	if(c=="&")
	        {
	    	flag = false;
	    	 break;
	    	}
	    	if(c=="#")
	        {
	    	flag = false;
	         break;
	    	}
	    	if(c=="%")
	        {
	    	flag = false;
	      	 break;
	    	}
	    	if(c=="*")
	        {
	   		flag = false;
		    break;
	    	}
	    	if(c=="^")
	        {
	    	flag = false;
	    	 break;
	    	}
	    	if(c=="(")
	        {
	    	flag = false;
	    	 break;
	    	}
	    	if(c==")")
	        {
	    	flag = false;
	      	 break;
	    	}
	    	if(c=="-")
	        {
	    	flag = false;
	      	 break;
	    	}
	    	if(c=="_")
	        {
	    	flag = false;
	      	 break;
	    	}
	    	if(c=="+")
	        {
	    	flag = false;
	      	 break;
	    	}
	    	if(c=="=")
	        {
	    	flag = false;
	      	 break;
	    	}
	    	if(c=="}")
	        {
	    	flag = false;
	     	  break;
	    	}
	    	if(c=="{")
	        {
	    	flag = false;
	        break;
	    	}
	    	if(c=="[")
	        {
	    	flag = false;
	         break;
	    	}
	    	if(c=="]")
	        {
	    	flag = false;
	      	 break;
	    	}
	    	if(c==" |")
	        {
	    	flag = false;
	     	 break;
	    	}
	    	if(c=='"')
	        {
	    	flag = false;
	        break;
	    	}
	    	if(c==";")
	        {
	    	flag = false;
	      	break;
	    	}
	    	if(c==":")
	        {
	    	flag = false;
	      	 break;
	    	}   
	    	if(c=="?")
	        {
	    	flag = false;
	        break;
	    	}
	 		if(c=="/")
	        {
	    	flag = false;
	      	 break;
	    	}
	    	if(c==".")
	        {
	    	flag = false;
	      	break;
	    	}
	    	if(c=="> ")
	        {
	    	flag = false;
	     	break;
	    	}
	    	if(c=="<")
	        {
	    	flag = false;
	     	 break;
	    	}
	    	if(c==",")
	        {
	    	flag = false;
	      	break;
	    	}
	    	if(c==" ")
		    {
			 	flag = false;
		    	break;
		    }  
	    }
		}
	     return flag;
	}
	
	function myTrim(text)
	{
	   	var newStr="";
		for(var i=0;i<text.length;i++)
		{
			  if(text.charCodeAt(i)!=32)
			  {
			     newStr+=text.charAt(i);
			  }
		  }
		  return newStr;
	}
	
	function validateSpaceChecking(strValue)
    {
    	if (strValue == "")
    	{
    		return false;
    	} 
    	var startingChar = strValue.charAt(0);
    	if (!validateSpace(startingChar))
    	{
    		return false;
        }
        var endingChar = strValue.charAt(strValue.length-1);
        if (!validateSpace(endingChar))
    	{
    		return false;
        }
        return true;
    }

	function ltrim(str)
	{
		var whitespace = new String(" \t\n\r");
		   var s = new String(str);
		   if (whitespace.indexOf(s.charAt(0)) != -1) {
		      var j=0, i = s.length;
		      while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
		         j++;
		      s = s.substring(j, i);
		   }
		   return s;
	}

	function rtrim(str)
	{
		var whitespace = new String(" \t\n\r");
		   var s = new String(str);
		   if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
		      var i = s.length - 1;       // Get length of string
		      while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
		         i--;
		      s = s.substring(0, i+1);
		   }
		   return s;
	}
   
	
function removeLeftRightSpaces ( s ) {
	return rtrim(ltrim(s));
}