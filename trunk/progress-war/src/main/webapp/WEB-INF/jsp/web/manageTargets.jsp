<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Manage targets">
  <form name="manageTargetsForm" action="update" method="POST">
    <input type="hidden" name="entity" value="targets">
    <tag:table>
      <tr>
        <td colspan="6" class="title">Targets</td>
      </tr>
      <tr>
        <td class="tableHeader">Name</td>
        <td class="tableHeader">Environment</td>
        <td class="tableHeader">Server name</td>
        <td class="tableHeader">Container</td>
        <td class="tableHeader">Description</td>
        <td class="tableHeader">Delete</td>
      </tr>      

      <c:forEach items="${targets}" var="target" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size="30" name="C${target.id}_name" value="${target.name}" /></td>
          <td>
            <tag:pulldown name="C${target.id}_environment" items="${environments}"
                  valueAttribute="id" displayAttribute="name" selected="${target.environment.id}"/>
          </td>
          <td><input type="text" size="18" name="C${target.id}_serverName" value="${target.serverName}" /></td>
          <td><input type="text" size="20" name="C${target.id}_container" value="${target.container}" /></td>
          <td><input type="text" size="30" name="C${target.id}_description" value="${target.description}" /></td>
          <td><input type="checkbox" name="C${target.id}_delete" value="true"/></td>
        </tag:tableRow>
      </c:forEach>
        
      <%-- add in some extra rows for users to add new targets --%>
      <c:forEach var="newRow" begin="1" end="${newRowCount}" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size="30" name="Cnew${newRow}_name" value="" /></td>
          <td>
            <tag:pulldown name="Cnew${newRow}_environment" items="${environments}"
                  valueAttribute="id" displayAttribute="name"/>
          </td>
          <td><input type="text" size="18" name="Cnew${newRow}_serverName" value="" /></td>
          <td><input type="text" size="20" name="Cnew${newRow}_container" value="" /></td>
          <td><input type="text" size="30" name="Cnew${newRow}_description" value="" /></td>
          <td></td>
        </tag:tableRow>
      </c:forEach>

      <tag:tableSeparator/>       
               
      <input type="submit" name="save" value="Save">&nbsp;&nbsp;
    </tag:table>
  </form>
</tag:page>
