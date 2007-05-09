<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="row" required="true" %>

<%-- Set up alternate row colors --%>
<c:choose>
  <c:when test="${row % 2 == 0}">
    <c:set var="rowClass" value="evenRow"/>
  </c:when>
  <c:otherwise>
    <c:set var="rowClass" value="oddRow"/>
  </c:otherwise>
</c:choose>
 
<tr class="${rowClass}">
 <jsp:doBody/> 
</tr>
