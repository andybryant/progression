<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Targets">
  <tag:table>
    <tr>
      <td colspan="4" class="title">Targets</td>
      <td align="right" class="title">
        <c:url var="editUrl" value="/web/manage">
          <c:param name="entity" value="targets"/>
        </c:url>
        <a href="${editUrl}">Edit</a>&nbsp;
      </td> 
    </tr>
    <tr>
      <td class="tableHeader">Name</td>
      <td class="tableHeader">Environment</td>
      <td class="tableHeader">Server name</td>
      <td class="tableHeader">Container</td>
      <td class="tableHeader">Description</td>
    </tr>      

    <c:forEach items="${targets}" var="target" varStatus="status">
      <tag:tableRow row="${status.index}">
        <td>${target.name}&nbsp;&nbsp;</td>
        <td>${target.environment.name}&nbsp;&nbsp;</td>
        <td>${target.serverName}&nbsp;&nbsp;</td>
        <td>${target.container}&nbsp;&nbsp;</td>
        <td>${target.description}&nbsp;&nbsp;</td>
      </tag:tableRow>
    </c:forEach>
  </tag:table>
</tag:page>
