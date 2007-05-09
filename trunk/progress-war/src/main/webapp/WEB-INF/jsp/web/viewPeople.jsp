<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="People">
  <tag:table>
    <tr>
      <td colspan="2" class="title">People</td>
      <td align="right" class="title">
        <c:url var="editUrl" value="/web/manage">
          <c:param name="entity" value="people"/>
        </c:url>
        <a href="${editUrl}">Edit</a>&nbsp;
      </td>
    </tr>
    <tr>
      <td class="tableHeader">Name</td>
      <td class="tableHeader">Email Address</td>
      <td class="tableHeader">Location</td>
    </tr>      

    <c:forEach items="${people}" var="person" varStatus="status">
      <tag:tableRow row="${status.index}">
        <td>${person.name}&nbsp;&nbsp;</td>
        <td>${person.emailAddress}&nbsp;&nbsp;</td>
        <td>${person.location}&nbsp;&nbsp;</td>
      </tag:tableRow>
    </c:forEach>
  </tag:table>
</tag:page>