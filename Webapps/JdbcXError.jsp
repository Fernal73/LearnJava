<!DOCtype html>
<%-- JdbcXError.jsp --%> 
<%@page contentType="text/html; charset=UTF-8" isErrorPage="true" %> 
<html> 
 <head> 
    <meta name="viewport" content="initial-scale=1.0"/>
 <title>Error Page</title> 
 <style>body{text-align:center;}</style> 
 </head> 
 <body> 
 <br/><br/><br/> 
 <h3>Data Retrieval Error<br/><br/> 
 <p> 
 <%= exception.toString() %></p></h3> 
 <br/><br/><br/>
 <form method="GET" action="JdbcBeanX.jsp"> 
 <input type="Submit" value="Try again"> 
 </form> 
 </body> 
</html>
