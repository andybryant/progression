<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Clients">
  <tag:table>
    <tr>
      <td colspan="1" class="title">Clients</td>
      <td align="right" class="title">
        <c:url var="editUrl" value="/web/manage">
          <c:param name="entity" value="clients"/>
        </c:url>
        <a href="${editUrl}">Edit</a>&nbsp;
      </td>
    </tr>
    <tr>
      <td colspan="2" class="tableHeader">Name</td>
    </tr>      

    <c:forEach items="${clients}" var="client" varStatus="status">
      <tag:tableRow row="${status.index}">
        <td colspan="2">${client.name}&nbsp;&nbsp;</td>
      </tag:tableRow>
    </c:forEach>
  </tag:table>
</tag:page>
