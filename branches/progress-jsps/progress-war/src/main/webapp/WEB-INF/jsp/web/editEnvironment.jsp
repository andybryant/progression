<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Edit Environment">
  <form name="editEnvironmentForm" action="<c:url value="/web/save/environment"/>" method="GET">
  <input type="hidden" name="environment" value="${environment.id}"/>
  <tag:table>
    <tr> 
      <td colspan="2" class="title">Environment ${environment.name}</td>
    </tr>
    <tr> 
      <td class="label">Description</td>
      <td><input type="text" size="50" name="description" value="${environment.description}" /></td>
    </tr>
    <tr> 
      <td colspan="2">
        <tag:table>
          <tr>
            <td class="tableHeader">Artifact</td>
            <td class="tableHeader">Project</td>
            <td class="tableHeader">Host</td>
            <td class="tableHeader">Status</td>
            <td class="tableHeader">Version</td>
            <td class="tableHeader">Comments</td>
          </tr>
		      <c:forEach items="${deployTargets}" var="dt" varStatus="stat">
		        <tag:tableRow row="${stat.index}">
		          <td>${dt.artifact.name}&nbsp;&nbsp;</td>
		          <td>${dt.deploy.build.project.name}&nbsp;&nbsp;</td>
		          <td>${dt.target.name}&nbsp;&nbsp;</td>
		          <td><tag:pulldownEnum name="C${dt.id}_status" items="${status}" selected="${dt.status}"/></td>
		          <td>${dt.deploy.build.version}&nbsp;&nbsp;</td>
		          <td><input type="text" size="30" name="C${dt.id}_comments" value="${dt.comments}" /></td>
		        </tag:tableRow>
		      </c:forEach>
        </tag:table>
      </td>
    </tr>
    
    <tag:tableSeparator/>       
               
    <input type="submit" value="Save">&nbsp;&nbsp;
  </tag:table>
</tag:page>
