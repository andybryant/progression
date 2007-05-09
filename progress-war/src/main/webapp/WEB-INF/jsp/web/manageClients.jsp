<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Manage clients">
  <form name="manageClientsForm" action="update" method="POST">
    <input type="hidden" name="entity" value="clients">
    <tag:table>
      <tr>
        <td colspan="2" class="title">Clients</td>
      </tr>
      <tr>
        <td class="tableHeader">Name</td>
        <td class="tableHeader">Delete</td>
      </tr>      

      <c:forEach items="${clients}" var="client" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size="20" name="C${client.id}_name" value="${client.name}" /></td>
          <td><input type="checkbox" name="C${client.id}_delete" value="true"/></td>
        </tag:tableRow>
      </c:forEach>
        
      <%-- add in some extra rows for users to add new clients --%>
      <c:forEach var="newRow" begin="1" end="${newRowCount}" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size="20" name="Cnew${newRow}_name" value="" /></td>
          <td></td>
        </tag:tableRow>
      </c:forEach>

      <tag:tableSeparator/>       
               
      <input type="submit" name="save" value="Save">&nbsp;&nbsp;
    </tag:table>
  </form>
</tag:page>
