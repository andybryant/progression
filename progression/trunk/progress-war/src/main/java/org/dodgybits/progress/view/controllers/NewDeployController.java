package org.dodgybits.progress.view.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.BuildDao;
import org.dodgybits.progress.dao.EnvironmentDao;
import org.dodgybits.progress.dao.PersonDao;
import org.dodgybits.progress.dao.TargetDao;
import org.dodgybits.progress.model.Build;
import org.dodgybits.progress.model.Environment;
import org.dodgybits.progress.model.Person;
import org.dodgybits.progress.model.Service;
import org.dodgybits.progress.model.Target;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Prepare a page for a new deploy.
 */
public class NewDeployController extends AbstractController
{
  private static final Log cLogger = LogFactory.getLog(NewDeployController.class);
  
  private PersonDao aPersonDao;
  private BuildDao aBuildDao;
  private EnvironmentDao aEnvironmentDao;
  private TargetDao aTargetDao;
  
  public void setPersonDao(PersonDao pPersonDao)
  {
    aPersonDao = pPersonDao;
  }
  
  public void setBuildDao(BuildDao pBuildDao)
  {
    aBuildDao = pBuildDao;
  }

  public void setEnvironmentDao(EnvironmentDao pEnvironmentDao)
  {
    aEnvironmentDao = pEnvironmentDao;
  }
  
  public void setTargetDao(TargetDao pTargetDao)
  {
    aTargetDao = pTargetDao;
  }
  
  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest pRequest,
      HttpServletResponse pResponse) throws Exception
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    cLogger.debug("Preparing new deploy page");
    Long lBuildId = Long.parseLong(pRequest.getParameter("build"));
    Build lBuild = aBuildDao.findById(lBuildId, false);
    lModel.put("build", lBuild);
    List<Person> lPeople = aPersonDao.findAll();
    lModel.put("people", lPeople);
    List<Environment> lEnvironments = aEnvironmentDao.findAll();
    lModel.put("environments", lEnvironments);
    List<Target> lTargets = aTargetDao.findAll();
    lModel.put("targets", lTargets);
    lModel.put("status", Service.Status.values());
    return new ModelAndView("newDeploy", lModel);
  }

}
