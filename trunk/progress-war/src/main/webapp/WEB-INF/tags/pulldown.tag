<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftn" uri="/functions"%>

<%@ attribute name="name" required="true" %>
<%@ attribute name="items" required="true" type="java.util.Collection" %>
<%@ attribute name="valueAttribute" required="true" %>
<%@ attribute name="displayAttribute" required="true" %>
<%@ attribute name="selected" required="false" %>

<select name="${name}">
  <c:if test="${empty selected}">
    <option value="">Select...</option>
  </c:if>
  <c:forEach items="${items}" var="item">
    <c:set var="itemVar" value="${ftn:getProperty(item, valueAttribute)}"/>
    <c:choose>
      <c:when test="${itemVar == selected}">
        <option value="${itemVar}" selected>
          ${ftn:getProperty(item, displayAttribute)}
        </option>
      </c:when>
      <c:otherwise>
        <option value="${itemVar}">
          ${ftn:getProperty(item, displayAttribute)}
        </option>
      </c:otherwise>
    </c:choose>
  </c:forEach>
</select>
