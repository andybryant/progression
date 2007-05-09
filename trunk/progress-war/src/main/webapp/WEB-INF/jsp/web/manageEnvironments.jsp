<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Manage environments">
  <form name="manageEnvironmentsForm" action="update" method="POST">
    <input type="hidden" name="entity" value="environments">
    <tag:table>
      <tr>
        <td colspan="4" class="title">Environments</td>
      </tr>
      <tr>
        <td class="tableHeader">Name</td>
        <td class="tableHeader">Description</td>
        <td class="tableHeader">Order</td>
        <td class="tableHeader">Delete</td>
      </tr>      

      <c:forEach items="${environments}" var="environment" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size=20 name="C${environment.id}_name" value="${environment.name}" /></td>
          <td><input type="text" size=50 name="C${environment.id}_description" value="${environment.description}" /></td>
          <td><input type="text" size=5 name="C${environment.id}_order" value="${(status.index + 1) * 10}" /></td>
          <td><input type="checkbox" name="C${environment.id}_delete" value="true"/></td>
        </tag:tableRow>
      </c:forEach>
        
      <%-- add in some extra rows for users to add new clients --%>
      <c:forEach var="newRow" begin="1" end="${newRowCount}" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size=20 name="Cnew${newRow}_name" value="" /></td>
          <td><input type="text" size=50 name="Cnew${newRow}_description" value="" /></td>
          <td><input type="text" size=5 name="Cnew${newRow}_order" value="" /></td>
          <td></td>
        </tag:tableRow>
      </c:forEach>

      <tag:tableSeparator/>       
               
      <input type="submit" name="save" value="Save">&nbsp;&nbsp;
    </tag:table>
  </form>
</tag:page>
