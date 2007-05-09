<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Manage projects">
  <form name="manageProjectsForm" action="update" method="POST">
    <input type="hidden" name="entity" value="projects">
    <tag:table>
      <tr>
        <td colspan="4" class="title">Projects</td>
      </tr>
      <tr>
        <td class="tableHeader">Name</td>
        <td class="tableHeader">Client</td>
        <td class="tableHeader">Description</td>
        <td class="tableHeader">Delete</td>
      </tr>      

      <c:forEach items="${projects}" var="project" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size="20" name="C${project.id}_name" value="${project.name}" /></td>
          <td>
            <tag:pulldown name="C${project.id}_client" items="${clients}"
                  valueAttribute="id" displayAttribute="name" selected="${project.client.id}"/>
          </td>
          <td><input type="text" size="40" name="C${project.id}_description" value="${project.description}" /></td>
          <td><input type="checkbox" name="C${project.id}_delete" value="true"/></td>
        </tag:tableRow>
      </c:forEach>
        
      <%-- add in some extra rows for users to add new projects --%>
      <c:forEach var="newRow" begin="1" end="${newRowCount}" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size=20 name="Cnew${newRow}_name" value="" /></td>
          <td>
            <tag:pulldown name="Cnew${newRow}_client" items="${clients}"
                  valueAttribute="id" displayAttribute="name"/>
          </td>
          <td><input type="text" size="40" name="Cnew${newRow}_description" value="" /></td>
          <td></td>
        </tag:tableRow>
      </c:forEach>

      <tag:tableSeparator/>       
               
      <input type="submit" name="save" value="Save">&nbsp;&nbsp;
    </tag:table>
  </form>
</tag:page>
