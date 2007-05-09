<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="View environments">
  <tag:table>
    <c:forEach items="${environments}" var="environment">
      <tr>
        <td colspan="5" class="title">
          <a name="e${environment.id}"></a>
          <c:out value="${environment.name}"/>&nbsp;
        </td>
        <td align="right" class="title">
          <c:url var="editUrl" value="/web/edit/environment">
            <c:param name="id" value="${environment.id}"/>
          </c:url>
          <a href="${editUrl}">Edit</a>&nbsp;
        </td>
      </tr>
      <tr>
        <td class="tableHeader">Artifact</td>
        <td class="tableHeader">Host</td>
        <td class="tableHeader">Status</td>
        <td class="tableHeader">Version</td>
        <td class="tableHeader">Contact</td>
        <td class="tableHeader">Comments</td>
      </tr>
      <c:forEach items="${deployTargets[environment.id]}" var="dt" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td>${dt.artifact.name}&nbsp;&nbsp;</td>
          <td>${dt.target.name}&nbsp;&nbsp;</td>
          <td>${dt.status}&nbsp;&nbsp;</td>
          <td>
            <c:url var="editDeployLink" value="/web/edit/deploy">
              <c:param name="deploy" value="${dt.deploy.id}"/>
            </c:url>
            <a href="${editDeployLink}">${dt.deploy.build.version}</a>&nbsp;&nbsp;
          </td>
          <td>${dt.deploy.contact.name}&nbsp;&nbsp;</td>
          <td>${dt.comments}&nbsp;&nbsp;</td>
        </tag:tableRow>
      </c:forEach>
    </c:forEach>
  </tag:table>
</tag:page>

