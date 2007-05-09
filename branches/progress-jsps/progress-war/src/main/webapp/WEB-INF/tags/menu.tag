<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- LINKS -->
<table bgcolor="#000000" cellspacing="0" cellpadding="0" width="100%" border="0">
  <tr>
    <td class="line"><img border="0" height="1" width="1" src="<c:url value="/assets/clear.gif"/>"></td>
  </tr>
  <tr>
    <td class="links">
      <table cellspacing="0" cellpadding="0" border="0">
        <tr>
          <td><img align="left" border="0" height="10" width="5" src="<c:url value="/assets/clear.gif"/>"></td>
          <form name="viewForm">
          <td class="links" align="center" valign="middle">
            <select name="selector" onChange="javascript:goto(this.form,'<c:url value="/web/view"/>?entity=')">
              <option value="Invalid">View...</option>
              <option value="links">View links</option>
              <option value="environments">View environments</option>
              <option value="projects">View projects</option>
              <option value="targets">View targets</option>
              <option value="clients">View clients</option>
              <option value="people">View people</option>
              <option value="artifacts">View artifacts</option>
              <option value="builds">View builds</option>
              <option value="deploys">View deploys</option>
            </select>
          </td></form>
          <td class="pipe">&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <form name="manageForm">
          <td class="links" align="center" valign="middle">
            <select name="selector" onChange="javascript:goto(this.form,'<c:url value="/web/manage"/>?entity=')">
              <option value="Invalid">Manage...</option>
              <option value="links">Manage links</option>
              <option value="environments">Manage environments</option>
              <option value="projects">Manage projects</option>
              <option value="targets">Manage targets</option>
              <option value="clients">Manage clients</option>
              <option value="people">Manage people</option>
              <option value="artifacts">Manage artifacts</option>
              <option value="builds">Manage builds</option>
              <option value="deploys">Manage deploys</option>
            </select>
          </td></form>
          <td class="pipe">&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;</td>
          <td class="links" align="center">&nbsp;&nbsp;<a href="<c:url value="/web/new/build"/>" class="links">New build</a></td>
          <td class="pipe">&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td class="line"><img border="0" height="1" width="1" src="<c:url value="/assets/clear.gif"/>"></td>
  </tr>
</table>
<!-- /LINKS -->

