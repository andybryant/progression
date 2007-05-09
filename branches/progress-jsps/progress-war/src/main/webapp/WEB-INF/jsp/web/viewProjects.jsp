<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Projects">
  <tag:table>
    <tr>
      <td colspan="2" class="title">Projects</td>
      <td align="right" class="title">
        <c:url var="editUrl" value="/web/manage">
          <c:param name="entity" value="projects"/>
        </c:url>
        <a href="${editUrl}">Edit</a>&nbsp;
      </td>
    </tr>
    <tr>
      <td class="tableHeader">Name</td>
      <td class="tableHeader">Client</td>
      <td class="tableHeader">Description</td>
    </tr>      

    <c:forEach items="${projects}" var="project" varStatus="status">
      <tag:tableRow row="${status.index}">
        <td>${project.name}&nbsp;&nbsp;</td>
        <td>${project.client.name}&nbsp;&nbsp;</td>
        <td>${project.description}&nbsp;&nbsp;</td>
      </tag:tableRow>
    </c:forEach>
  </tag:table>
</tag:page>