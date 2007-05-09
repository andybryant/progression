<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="Artifacts">
  <tag:table>
    <tr>
      <td colspan="1" class="title">Artifacts</td>
      <td align="right" class="title">
        <c:url var="editUrl" value="/web/manage">
          <c:param name="entity" value="artifacts"/>
        </c:url>
        <a href="${editUrl}">Edit</a>&nbsp;
      </td>
    </tr>
    <tr>
      <td class="tableHeader">Name</td>
      <td class="tableHeader">Description</td>
    </tr>      

    <c:forEach items="${artifacts}" var="artifact" varStatus="status">
      <tag:tableRow row="${status.index}">
        <td>${artifact.name}&nbsp;&nbsp;</td>
        <td>${artifact.description}&nbsp;&nbsp;</td>
      </tag:tableRow>
    </c:forEach>
  </tag:table>
</tag:page>
