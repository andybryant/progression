<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Manage builds">
  <form name="manageBuildsForm" action="update" method="POST">
    <input type="hidden" name="entity" value="builds">
    <tag:table>
      <tr>
        <td colspan="8" class="title">Builds</td>
      </tr>
      <tr>
        <td class="tableHeader">Created</td>
        <td class="tableHeader">Tag</td>
        <td class="tableHeader">Project</td>
        <td class="tableHeader">Contact</td>
        <td class="tableHeader">Status</td>
        <td class="tableHeader">Artifact</td>
        <td class="tableHeader">Comments</td>
        <td class="tableHeader">Delete</td>
      </tr>      

      <c:forEach items="${builds}" var="build" varStatus="stat">
        <tag:tableRow row="${stat.index}">
          <td><fmt:formatDate value="${build.createdDate}" dateStyle="short" timeStyle="short" type="both"/>&nbsp;&nbsp;</td>
          <td><input type="text" size="20" name="C${build.id}_version" value="${build.version}" /></td>
          <td>${build.project.name}&nbsp;&nbsp;</td>
          <td>
            <tag:pulldown name="C${build.id}_contact" items="${people}"
               valueAttribute="id" displayAttribute="name" selected="${build.contact.id}"/>
          </td>
          <td>
            <tag:pulldownEnum name="C${build.id}_status" items="${status}" selected="${build.status}"/>
          </td>
          <td>${build.artifact.name}&nbsp;&nbsp;</td>
          <td><input type="text" size="30" name="C${build.id}_comments" value="${build.comments}" /></td>
          <td><input type="checkbox" name="C${build.id}_delete" value="true"/></td>
        </tag:tableRow>
      </c:forEach>
        
      <%-- add in some extra rows for users to add new builds --%>
      <c:forEach var="newRow" begin="1" end="${newRowCount}" varStatus="stat">
        <tag:tableRow row="${stat.index}">
          <td></td>
          <td><input type="text" size=20 name="Cnew${newRow}_version" value="" /></td>
          <td>
            <tag:pulldown name="Cnew${newRow}_project" items="${projects}"
               valueAttribute="id" displayAttribute="name"/>
          </td>
          <td>
            <tag:pulldown name="Cnew${newRow}_contact" items="${people}"
                  valueAttribute="id" displayAttribute="name"/>
          </td>
          <td>
            <tag:pulldownEnum name="Cnew${newRow}_status" items="${status}" selected="complete"/>
          </td>
          <td>
            <tag:pulldown name="Cnew${newRow}_artifact" items="${artifacts}"
               valueAttribute="id" displayAttribute="name"/>
          </td>
          <td><input type="text" size="30" name="Cnew${newRow}_comments" value="${build.comments}" /></td>
          <td></td>
        </tag:tableRow>
      </c:forEach>

      <tag:tableSeparator/>       
               
      <input type="submit" name="save" value="Save">&nbsp;&nbsp;
    </tag:table>
  </form>
</tag:page>
