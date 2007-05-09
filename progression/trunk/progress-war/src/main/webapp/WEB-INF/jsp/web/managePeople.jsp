<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Manage people">
  <form name="manageClientsForm" action="update" method="POST">
    <input type="hidden" name="entity" value="people">
    <tag:table>
      <tr>
        <td colspan="5" class="title">People</td>
      </tr>
      <tr>
        <td class="tableHeader">Name</td>
        <td class="tableHeader">Email address</td>
        <td class="tableHeader">Location</td>
        <td class="tableHeader">Order</td>
        <td class="tableHeader">Delete</td>
      </tr>      

      <c:forEach items="${people}" var="person" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size="20" name="C${person.id}_name" value="${person.name}" /></td>
          <td><input type="text" size="40" name="C${person.id}_emailAddress" value="${person.emailAddress}" /></td>
          <td><input type="text" size="40" name="C${person.id}_location" value="${person.location}" /></td>
          <td><input type="text" size="5" name="C${person.id}_order" value="${(status.index + 1) * 10}" /></td>
          <td><input type="checkbox" name="C${person.id}_delete" value="true"/></td>
        </tag:tableRow>
      </c:forEach>
        
      <%-- add in some extra rows for users to add new people --%>
      <c:forEach var="newRow" begin="1" end="${newRowCount}" varStatus="status">
        <tag:tableRow row="${status.index}">
          <td><input type="text" size="20" name="Cnew${newRow}_name" value="" /></td>
          <td><input type="text" size="40" name="Cnew${newRow}_emailAddress" value="" /></td>
          <td><input type="text" size="40" name="Cnew${newRow}_location" value="" /></td>
          <td><input type="text" size=5 name="Cnew${newRow}_order" value="" /></td>
          <td></td>
        </tag:tableRow>
      </c:forEach>

      <tag:tableSeparator/>       
               
      <input type="submit" name="save" value="Save">&nbsp;&nbsp;
    </tag:table>
  </form>
</tag:page>
