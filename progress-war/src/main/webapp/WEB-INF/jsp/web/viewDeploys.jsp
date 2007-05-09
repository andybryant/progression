<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Deploys">
  <tag:table>
    <tr>
      <td colspan="9" class="title">Deploys</td>
      <td align="right" class="title">
        <c:url var="editUrl" value="/web/manage">
          <c:param name="entity" value="deploys"/>
        </c:url>
        <a href="${editUrl}">Edit</a>&nbsp;
      </td>
    </tr>
    <tr>
      <td class="tableHeader">Created</td>
      <td class="tableHeader">Build Tag</td>
      <td class="tableHeader">Project</td>
      <td class="tableHeader">Artifact</td>
      <td class="tableHeader">Environment</td>
      <td class="tableHeader">Contact</td>
      <td class="tableHeader">Status</td>
      <td class="tableHeader">Targets</td>
      <td class="tableHeader">Comments</td>
      <td class="tableHeader"></td>
    </tr>      

    <c:forEach items="${deploys}" var="deploy" varStatus="status">
      <tag:tableRow row="${status.index}">
        <td><fmt:formatDate value="${deploy.createdDate}" pattern="MMM d, yyyy HH:mm z"/>&nbsp;&nbsp;</td>
        <td>
          <c:url var="editBuildLink" value="/web/edit/build">
            <c:param name="build" value="${deploy.build.id}"/>
          </c:url>
          <a href="${editBuildLink}">${deploy.build.version}</a>&nbsp;&nbsp;
        </td>
        <td>${deploy.build.project.name}&nbsp;&nbsp;</td>
        <td>${deploy.build.artifact.name}&nbsp;&nbsp;</td>
        <td>${deploy.environment.name}&nbsp;&nbsp;</td>
        <td>${deploy.contact.name}&nbsp;&nbsp;</td>
        <td>${deploy.status}&nbsp;&nbsp;</td>
        <td>
          <c:forEach items="${deploy.deployTargets}" var="deployTarget" varStatus="artStat">
            ${deployTarget.target.name}
            <c:if test="${not artStat.last}">, </c:if>
          </c:forEach>
        </td>
        <td>${deploy.comments}&nbsp;&nbsp;</td>
        <td>
          <c:url var="editLink" value="/web/edit/deploy">
            <c:param name="deploy" value="${deploy.id}"/>
          </c:url>
          <a href="${editLink}">Edit</a>&nbsp;&nbsp;
        </td>
      </tag:tableRow>
    </c:forEach>
  </tag:table>
</tag:page>
