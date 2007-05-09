<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Manage links">
  <form name="manageLinksForm" action="update" method="POST">
    <input type="hidden" name="entity" value="links">
    <tag:table>
      <tr>
        <td colspan="5" class="title">Environment Links</td>
      </tr>
      <tr>
        <td class="tableHeader">Environment</td>
        <td class="tableHeader">Link</td>
        <td class="tableHeader">Description</td>
        <td class="tableHeader">Order</td>
        <td class="tableHeader">Delete</td>
      </tr>    
      
      <c:set var="row" value="0"/>
      <c:forEach items="${environments}" var="environment" varStatus="status">
        <c:forEach items="${envLinks[environment.id]}" var="link">
          <c:set var="row" value="${row + 1}"/>
          <tag:tableRow row="${row}">
          <td>
            <tag:pulldown name="C${link.id}_environment" items="${environments}"
                  valueAttribute="id" displayAttribute="name" selected="${environment.id}"/>&nbsp;
          </td>
            <td><input type="text" size="50" name="C${link.id}_url" value="${link.url}" />&nbsp;</td>
            <td><input type="text" size="30" name="C${link.id}_name" value="${link.name}" />&nbsp;</td>
            <td><input type="text" size=5 name="C${link.id}_order" value="${row * 10}" /></td>
            <td><input type="checkbox" name="C${link.id}_delete" value="true"/>&nbsp;</td>
          </tag:tableRow>
        </c:forEach> 
      </c:forEach> 

      <%-- add in some extra rows for users to add new clients --%>
      <c:forEach var="newRow" begin="1" end="${newRowCount}" varStatus="status">
        <c:set var="row" value="${row + 1}"/>
        <tag:tableRow row="${row}">
          <td>
            <tag:pulldown name="Cnew${newRow}_environment" items="${environments}"
                  valueAttribute="id" displayAttribute="name"/>&nbsp;
          </td>
          <td><input type="text" size="50" name="Cnew${newRow}_url" value="" />&nbsp;</td>
          <td><input type="text" size="30" name="Cnew${newRow}_name" value="" />&nbsp;</td>
          <td><input type="text" size=5 name="Cnew${newRow}_order" value="" /></td>
          <td></td>
        </tag:tableRow>
      </c:forEach>
        
      <tag:tableSeparator/>       

      <tr>
        <td colspan="4" class="title">Developer Links</td>
      </tr>
      <tr>
        <td class="tableHeader">Topic</td>
        <td class="tableHeader">Link</td>
        <td class="tableHeader">Description</td>
        <td class="tableHeader">Order</td>
        <td class="tableHeader">Delete</td>
      </tr>    
      
      <c:set var="row" value="0"/>
      <c:forEach items="${topics}" var="topic">     
        <c:forEach items="${topicLinks[topic]}" var="link" varStatus="status">
          <c:set var="row" value="${row + 1}"/>
          <tag:tableRow row="${status.index}">
            <td><input type="text" size="20" name="C${link.id}_topic" value="${link.topic}" />&nbsp;</td>
            <td><input type="text" size="50" name="C${link.id}_url" value="${link.url}" />&nbsp;</td>
            <td><input type="text" size="30" name="C${link.id}_name" value="${link.name}" />&nbsp;</td>
            <td><input type="text" size=5 name="C${link.id}_order" value="${row * 10}" /></td>
            <td><input type="checkbox" name="C${link.id}_delete" value="true"/></td>
          </tag:tableRow>
        </c:forEach>  
      </c:forEach>  
              
      <%-- add in some extra rows for users to add new clients --%>
      <c:forEach var="newRow" begin="${newRowCount + 1}" end="${newRowCount * 2}" varStatus="status">
        <c:set var="row" value="${row + 1}"/>
        <tag:tableRow row="${row}">
          <td><input type="text" size="20" name="Cnew${newRow}_topic" value="" />&nbsp;</td>
          <td><input type="text" size="50" name="Cnew${newRow}_url" value="" />&nbsp;</td>
          <td><input type="text" size="30" name="Cnew${newRow}_name" value="" />&nbsp;</td>
          <td><input type="text" size=5 name="Cnew${newRow}_order" value="" /></td>
          <td></td>
        </tag:tableRow>
      </c:forEach>

      <tag:tableSeparator/>       
               
      <input type="submit" name="save" value="Save">&nbsp;&nbsp;
    </tag:table>
  </form>
</tag:page>
