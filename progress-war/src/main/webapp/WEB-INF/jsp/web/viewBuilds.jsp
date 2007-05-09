<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Builds">
  <tag:table>
    <tr>
      <td colspan="7" class="title">Builds</td>
      <td align="right" class="title">
        <c:url var="editUrl" value="/web/manage">
          <c:param name="entity" value="builds"/>
        </c:url>
        <a href="${editUrl}">Edit</a>&nbsp;
      </td>
    </tr>
    <tr>
      <td class="tableHeader">Created</td>
      <td class="tableHeader">Tag</td>
      <td class="tableHeader">Project</td>
      <td class="tableHeader">Contact</td>
      <td class="tableHeader">Status</td>
      <td class="tableHeader">Artifact</td>
      <td class="tableHeader">Comments</td>
      <td class="tableHeader"></td>
    </tr>      

    <c:forEach items="${builds}" var="build" varStatus="status">
      <tag:tableRow row="${status.index}">
        <td><fmt:formatDate value="${build.createdDate}" dateStyle="short" timeStyle="short" type="both"/>&nbsp;&nbsp;</td>
        <td>${build.version}</td>
        <td>${build.project.name}&nbsp;&nbsp;</td>
        <td>${build.contact.name}&nbsp;&nbsp;</td>
        <td>${build.status}&nbsp;&nbsp;</td>
        <td>${build.artifact.name}&nbsp;&nbsp;</td>
        <td>${build.comments}&nbsp;&nbsp;</td>
        <td>
          <c:url var="editLink" value="/web/edit/build">
            <c:param name="build" value="${build.id}"/>
          </c:url>
          <a href="${editLink}">Edit</a>&nbsp;&nbsp;
          <c:url var="newLink" value="/web/new/deploy">
            <c:param name="build" value="${build.id}"/>
          </c:url>
          <a href="${newLink}">Deploy</a>&nbsp;&nbsp;
        </td>
      </tag:tableRow>
    </c:forEach>
  </tag:table>
</tag:page>
