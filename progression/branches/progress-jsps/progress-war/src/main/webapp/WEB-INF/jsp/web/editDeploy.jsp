<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Edit Deploy">
  <form name="editDeployForm" action="<c:url value="/web/save/deploy"/>" method="GET">
  <input type="hidden" name="deploy" value="${deploy.id}"/>
  <tag:table>
    <tr> 
      <td colspan="2" class="title">Deploy ${deploy.build.version}</td>
    </tr>
    <tr> 
      <td class="label">Created</td>
      <td><fmt:formatDate value="${deploy.createdDate}" pattern="MMM d, yyyy HH:mm z"/>&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Tag</td>
      <td>${deploy.build.version}&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Project</td>
      <td>${deploy.build.project.name}&nbsp;(${deploy.build.project.client.name})&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Artifact</td>
      <td>${deploy.build.artifact.name}&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Environment</td>
      <td>${deploy.environment.name}&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Targets</td>
      <td>
        <c:forEach items="${deploy.deployTargets}" var="deployTarget" varStatus="artStat">
          ${deployTarget.target.name}
          <c:if test="${not artStat.last}"> <br/></c:if>
        </c:forEach>
      </td>
    </tr>
    <tr> 
      <td class="label">Contact</td>
      <td>
        <tag:pulldown name="contact" items="${people}"
               valueAttribute="id" displayAttribute="name" selected="${deploy.contact.id}"/>
      </td>
    </tr>
    <tr> 
      <td class="label">Status</td>
      <td>
        <tag:pulldownEnum name="status" items="${status}" selected="${deploy.status}"/>
      </td>
    </tr>
    <tr> 
      <td class="label">Comments</td>
      <td><input type="text" size="50" name="comments" value="${deploy.comments}" /></td>
    </tr>
    
    <tag:tableSeparator/>       
               
    <input type="submit" value="Save">&nbsp;&nbsp;
  </tag:table>
</tag:page>
