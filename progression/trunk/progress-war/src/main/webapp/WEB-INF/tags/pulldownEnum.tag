<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftn" uri="/functions"%>

<%@ attribute name="name" required="true" %>
<%@ attribute name="items" required="true" type="java.lang.Object" %>
<%@ attribute name="selected" required="false" %>

<select name="${name}">
  <c:if test="${empty selected}">
    <option value="">Select...</option>
  </c:if>
  <c:forEach items="${items}" var="item">
    <c:choose>
      <c:when test="${item == selected}">
        <option value="${item}" selected>${item}</option>
      </c:when>
      <c:otherwise>
        <option value="${item}">${item}</option>
      </c:otherwise>
    </c:choose>
  </c:forEach>
</select>
