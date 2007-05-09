<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="New deploy">
  <form name="newDeployForm" action="<c:url value="/web/save/deploy"/>" method="GET">
  <input type="hidden" name="build" value="${build.id}"/>
  <tag:table>
    <tr>
      <td colspan="2" class="title">New deploy</td>
    </tr>
    <tr> 
      <td class="label">Tag</td>
      <td>${build.version}&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Project</td>
      <td>${build.project.name}&nbsp;(${build.project.client.name})&nbsp;&nbsp;</td>
    </tr>
    <tr> 
      <td class="label">Artifact</td>
      <td>${build.artifact.name}&nbsp;&nbsp;</td>
    </tr>
    <tr>
      <td class="label">Environment</td>
      <td>
        <tag:pulldown name="environment" items="${environments}"
               valueAttribute="id" displayAttribute="name"/>
      </td>
    </tr>
    <tr>
      <td class="label">Targets</td>
      <td>
        <select name="targets" MULTIPLE size="10">
          <c:forEach items="${targets}" var="target" varStatus="status">
            <option value="${target.id}">${target.name}</option>
          </c:forEach>
        </select>
      </td>
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
        <tag:pulldownEnum name="status" items="${status}" selected="complete"/>
      </td>
    </tr>
    <tr>
      <td class="label">Comments</td>
      <td><input type="text" size="50" name="comments" value="${build.comments}" /></td>
    </tr>
    
    <tag:tableSeparator/>       
               
    <input type="submit" value="Save">&nbsp;&nbsp;
  </tag:table>
  </form>  
</tag:page>
