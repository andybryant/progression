<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Manage artifacts">
  <form name="manageArtifactsForm" action="update" method="POST">
    <input type="hidden" name="entity" value="artifacts">
    <tag:table>
      <tr>
        <td colspan="2" class="title">Artifacts</td>
      </tr>
      <tr>
        <td class="tableHeader">Name</td>
        <td class="tableHeader">Description</td>
        <td class="tableHeader">Delete</td>
      </tr>      

      <c:forEach items="${artifacts}" var="artifact" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size="20" name="C${artifact.id}_name" value="${artifact.name}" /></td>
          <td><input type="text" size="40" name="C${artifact.id}_description" value="${artifact.description}" /></td>
          <td><input type="checkbox" name="C${artifact.id}_delete" value="true"/></td>
        </tag:tableRow>
      </c:forEach>
        
      <%-- add in some extra rows for users to add new artifacts --%>
      <c:forEach var="newRow" begin="1" end="${newRowCount}" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size="20" name="Cnew${newRow}_name" value="" /></td>
          <td><input type="text" size="40" name="Cnew${newRow}_description" value="" /></td>
          <td></td>
        </tag:tableRow>
      </c:forEach>

      <tag:tableSeparator/>       
               
      <input type="submit" name="save" value="Save">&nbsp;&nbsp;
    </tag:table>
  </form>
</tag:page>
