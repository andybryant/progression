<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Edit Build">
  <form name="editBuildForm" action="<c:url value="/web/save/build"/>" method="GET">
  <input type="hidden" name="build" value="${build.id}"/>
  <tag:table>
    <tr> 
      <td colspan="2" class="title">Build ${build.version}</td>
    </tr>
    <tr> 
      <td class="label">Created</td>
      <td><fmt:formatDate value="${build.createdDate}" dateStyle="short" timeStyle="short" type="both"/>&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Performed</td>
      <td><fmt:formatDate value="${build.performedDate}" dateStyle="short" timeStyle="short" type="both"/>&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Tag</td>
      <td>${build.version}&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Artifact</td>
      <td>${build.artifact.name}&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Project</td>
      <td>${build.project.name}&nbsp;(${build.project.client.name})&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Contact</td>
      <td>
        <tag:pulldown name="contact" items="${people}"
               valueAttribute="id" displayAttribute="name" selected="${build.contact.id}"/>
      </td>
    </tr>
    <tr> 
      <td class="label">Status</td>
      <td>
        <tag:pulldownEnum name="status" items="${status}" selected="${build.status}"/>
      </td>
    </tr>
    <tr> 
      <td class="label">Comments</td>
      <td><input type="text" size="50" name="comments" value="${build.comments}" /></td>
    </tr>
    
    <tag:tableSeparator/>       
               
    <input type="submit" value="Save">&nbsp;&nbsp;
  </tag:table>
</tag:page>
