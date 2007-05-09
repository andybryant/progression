<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Manage deploys">
  <form name="manageDeploysForm" action="update" method="POST">
    <input type="hidden" name="entity" value="deploys">
    <tag:table>
      <tr>
        <td colspan="10" class="title">Deploys</td>
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
        <td class="tableHeader">Delete</td>
      </tr>      

      <c:forEach items="${deploys}" var="deploy" varStatus="stat">
        <tag:tableRow row="${stat.index}">
          <td><fmt:formatDate value="${deploy.createdDate}" dateStyle="short" timeStyle="short" type="both"/>&nbsp;&nbsp;</td>
          <td>${deploy.build.version}&nbsp;&nbsp;</td>
          <td>${deploy.build.project.name}&nbsp;&nbsp;</td>
          <td>${deploy.build.artifact.name}&nbsp;&nbsp;</td>
          <td>${deploy.environment.name}&nbsp;&nbsp;</td>
          <td>
            <tag:pulldown name="C${deploy.id}_contact" items="${people}"
               valueAttribute="id" displayAttribute="name" selected="${deploy.contact.id}"/>
          </td>
          <td>
            <tag:pulldownEnum name="C${deploy.id}_status" items="${status}" selected="${deploy.status}"/>
          </td>
          <td>
            <c:forEach items="${deploy.deployTargets}" var="deployTarget" varStatus="artStat">
              ${deployTarget.target.name}
              <c:if test="${not artStat.last}">, </c:if>
            </c:forEach>
          </td>
          <td><input type="text" size="30" name="C${deploy.id}_comments" value="${deploy.comments}" /></td>
          <td><input type="checkbox" name="C${deploy.id}_delete" value="true"/></td>
        </tag:tableRow>
      </c:forEach>

      <tag:tableSeparator/>       
               
      <input type="submit" name="save" value="Save">&nbsp;&nbsp;
    </tag:table>
  </form>
</tag:page>
