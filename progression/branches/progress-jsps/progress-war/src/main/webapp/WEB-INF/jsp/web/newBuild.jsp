<%@ include file="common/includes.jspf" %> 
<tag:page pageTitle="New build">
  <form name="newBuildForm" action="<c:url value="/web/save/build"/>" method="GET">
  <tag:table>
    <tr>
      <td colspan="2" class="title">New build</td>
    </tr>
    <tr>
      <td class="label">Tag</td>
      <td><input type="text" size="20" name="version" value="" /></td>
    </tr>
    <tr>
      <td class="label">Project</td>
      <td>
        <tag:pulldown name="project" items="${projects}"
               valueAttribute="id" displayAttribute="name"/>
      </td>
    </tr>
    <tr>
      <td class="label">Contact</td>
      <td>
        <tag:pulldown name="contact" items="${people}"
               valueAttribute="id" displayAttribute="name"/>
      </td>
    </tr>
    <tr>
      <td class="label">Artifact</td>
      <td>
        <tag:pulldown name="artifact" items="${artifacts}"
               valueAttribute="id" displayAttribute="name"/>
      </td>
    </tr>
    <tr>
      <td class="label">Status</td>
      <td>
        <tag:pulldownEnum name="status" items="${status}" selected="complete"/>
      </td>
    </tr>
    <tr>
      <td class="label">Comments</td>
      <td><input type="text" size="50" name="comments" value="" /></td>
    </tr>
    
    <tag:tableSeparator/>       
               
    <input type="submit" value="Save">&nbsp;&nbsp;
  </tag:table>
  </form>  
</tag:page>
