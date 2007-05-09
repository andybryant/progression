<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Progress links">
  <tag:table>
    <tr>
      <td colspan="2" class="title">Environment Links</td>
      <td align="right" class="title">
        <a href="<c:url value="/web/manage?entity=links"/>">Edit</a> &nbsp;
      </td>
    </tr>
    <tr>
      <td class="tableHeader">Environment</td>
      <td class="tableHeader">Link</td>
      <td class="tableHeader">Description</td>
    </tr>      
    <c:choose>
      <c:when test="${empty envLinks}">
        <tr>
          <td colspan="3">No environment links found.</td>
        </tr>
      </c:when>
      <c:otherwise>
        <c:set var="row" value="0"/>
        <c:forEach items="${environments}" var="environment" varStatus="status">
          <c:forEach items="${envLinks[environment.id]}" var="link">
            <c:set var="row" value="${row + 1}"/>
            <tag:tableRow row="${row}">
              <td>${environment.name}&nbsp;</td>
              <td><a href="${link.url}">${link.url}</a>&nbsp;</td>
              <td>${link.name}&nbsp;</td>
            </tag:tableRow>
          </c:forEach> 
        </c:forEach> 
      </c:otherwise>
    </c:choose>
  </tag:table>
  
  <br />
  
  <tag:table>
    <tr>
      <td colspan="1" class="title">Developer Links</td>
      <td align="right" class="title">
        <a href="<c:url value="/web/manage?entity=links"/>">Edit</a> &nbsp;
      </td>
    </tr>
    <c:forEach items="${topics}" var="topic">
      <tr>
        <td colspan=2 class="tableHeader"><c:out value="${topic}"/></td>
      </tr>
      <c:forEach items="${topicLinks[topic]}" var="link" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td class="label"><c:out value="${link.name}"/></td>
          <td><a href="${link.url}">${link.url}</a></td>
        </tag:tableRow>
      </c:forEach>  
      <tr>
        <td colspan=2><br /></td>
      </tr>
    </c:forEach> 
    
    <tag:tableSeparator/> 
    
    <form action="http://www.google.com/search" name=f>
      <tr>
        <td>&nbsp;&nbsp;<b>Google:</b></td>
        <td>
          <input maxLength=256 size=30 name=q value="">&nbsp;
          <input type="submit" value="Google Search" name="btnG">&nbsp;&nbsp;<input type="submit" value="I'm Feeling Lucky" name="btnI">
        </td>
      </tr>
    </form>
  </tag:table>

</tag:page>
