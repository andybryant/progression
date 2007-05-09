<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ attribute name="pageTitle" required="true" %>

<%-- For some reason, adding the following causes the menu to look weird
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
--%>

<html>
  <head>
    <META content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>${pageTitle}</title>
    <link href="<c:url value="/assets/styles.css"/>" type="text/css" rel="stylesheet">
    
    <script type="text/javascript">
<!-- Begin
function goto(form, baseURL) {
  var URL = baseURL + form.selector.options[form.selector.selectedIndex].value;
  window.location.href = URL;
}
// End -->
    </script>     
  </head>       
    
  <body marginwidth="0" marginheight="0" topmargin="0" leftmargin="0">
    <tag:header title="${pageTitle}"/>
    <tag:menu/>

    <table align="left" cellpadding="10" cellspacing="0"><tr><td>
      <c:if test="${! empty message}">
        <span class="message">${message}</span><br/><br/>
      </c:if>

      <jsp:doBody/> 

      <tag:footer/>
    </td></tr></table>
  </body>
</html>  
   