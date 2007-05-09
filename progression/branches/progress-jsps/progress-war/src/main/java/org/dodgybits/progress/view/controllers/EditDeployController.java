package org.dodgybits.progress.view.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.DeployDao;
import org.dodgybits.progress.dao.PersonDao;
import org.dodgybits.progress.model.Deploy;
import org.dodgybits.progress.model.Person;
import org.dodgybits.progress.model.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Prepare a page for editing a deploy.
 */
public class EditDeployController extends AbstractController
{
  private static final Log cLogger = LogFactory.getLog(EditDeployController.class);
  
  private DeployDao aDeployDao;
  private PersonDao aPersonDao;

  public void setDeployDao(DeployDao pDeployDao)
  {
    aDeployDao = pDeployDao;
  }
    
  public void setPersonDao(PersonDao pPersonDao)
  {
    aPersonDao = pPersonDao;
  }

  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest pRequest,
      HttpServletResponse pResponse) throws Exception
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    cLogger.debug("Preparing edit deploy page");
    Long lDeployId = Long.parseLong(pRequest.getParameter("deploy"));
    Deploy lDeploy = aDeployDao.findById(lDeployId, false);
    lModel.put("deploy", lDeploy);
    List<Person> lPeople = aPersonDao.findAll();
    lModel.put("people", lPeople);
    lModel.put("status", Service.Status.values());
    return new ModelAndView("editDeploy", lModel);
  }

}
