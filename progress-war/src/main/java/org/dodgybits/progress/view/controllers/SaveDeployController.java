package org.dodgybits.progress.view.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.BuildDao;
import org.dodgybits.progress.dao.DeployDao;
import org.dodgybits.progress.dao.EnvironmentDao;
import org.dodgybits.progress.dao.PersonDao;
import org.dodgybits.progress.model.Build;
import org.dodgybits.progress.model.Deploy;
import org.dodgybits.progress.model.DeployTarget;
import org.dodgybits.progress.model.Environment;
import org.dodgybits.progress.model.Target;
import org.dodgybits.progress.model.Service.Status;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Save a new or modified deploy.
 */
public class SaveDeployController extends AbstractController
{
  private static final Log cLogger = LogFactory.getLog(SaveDeployController.class);
  
  private PersonDao aPersonDao;
  private BuildDao aBuildDao;
  private DeployDao aDeployDao;
  private EnvironmentDao aEnvironmentDao;
  
  public void setPersonDao(PersonDao pPersonDao)
  {
    aPersonDao = pPersonDao;
  }

  public void setBuildDao(BuildDao pBuildDao)
  {
    aBuildDao = pBuildDao;
  }

  public void setDeployDao(DeployDao pDeployDao)
  {
    aDeployDao = pDeployDao;
  }
  
  public void setEnvironmentDao(EnvironmentDao pEnvironmentDao)
  {
    aEnvironmentDao = pEnvironmentDao;
  }
  
  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest pRequest,
      HttpServletResponse pResponse) throws Exception
  {
    Deploy lDeploy = null;
    String lDeployIdStr = pRequest.getParameter("deploy");
    boolean lNewEntity = (lDeployIdStr == null);
    if (lNewEntity)
    {
      lDeploy = populateNewDeploy(pRequest);
    }
    else
    {
      lDeploy = aDeployDao.findById(Long.parseLong(lDeployIdStr), false);
    }

    updateDeploy(pRequest, lDeploy);

    cLogger.debug("Saving deploy " + lDeploy);
    aDeployDao.makePersistent(lDeploy);
    
    Map<String, Object> lModel = new HashMap<String, Object>();
    List<Deploy> lDeploys = aDeployDao.findAll();
    lModel.put("deploys", lDeploys);
    return new ModelAndView("viewDeploys", lModel);
  }

  /**
   * Create a new build and populate it with attributes that are only
   * modifiable for new builds.
   */
  private Deploy populateNewDeploy(HttpServletRequest pRequest)
  {
    Long lBuildId = Long.parseLong(pRequest.getParameter("build"));
    Build lBuild = aBuildDao.findById(lBuildId, false);

    Deploy lDeploy = new Deploy();
    lDeploy.setBuild(lBuild);
    lDeploy.setCreatedDate(new Date());
    lDeploy.setScheduledDate(null);
    lDeploy.setPerformedDate(null);
    Long lEnvId = new Long(Long.parseLong(pRequest.getParameter("environment")));
    Environment lEnvironment = aEnvironmentDao.findById(lEnvId, false); 
    lDeploy.setEnvironment(lEnvironment);
    List<DeployTarget> lDeployTargets = new ArrayList<DeployTarget>();
    List<Target> lTargets = lEnvironment.getTargets();
    Set<String> lTargetIds = new HashSet<String>(Arrays.asList(pRequest.getParameterValues("targets")));
    String lComments = pRequest.getParameter("comments");
    for (Target lTarget : lTargets)
    {
      boolean lMatched = lTargetIds.contains(String.valueOf(lTarget.getId())); 
      cLogger.debug("Matched target " + lTarget.getId() + ": " + lMatched);
      if (lMatched)
      {
        DeployTarget lDeployTarget = new DeployTarget();
        lDeployTarget.setArtifact(lBuild.getArtifact());
        lDeployTarget.setComments(lComments);
        lDeployTarget.setDeploy(lDeploy);
        lDeployTarget.setStatus(DeployTarget.Status.running);
        lDeployTarget.setTarget(lTarget);
        lDeployTargets.add(lDeployTarget);
      }
    }
    lDeploy.setDeployTargets(lDeployTargets);
    return lDeploy;
  }
  
  /**
   * Update attributes that are modifiable for both new and edited deploys.
   */
  private void updateDeploy(HttpServletRequest pRequest, Deploy pDeploy)
  {
    Status lStatus = Status.valueOf(pRequest.getParameter("status"));
    pDeploy.setStatus(lStatus);
    if (lStatus == Status.complete)
    {
      pDeploy.setPerformedDate(new Date());
      for (DeployTarget lDeployTarget : pDeploy.getDeployTargets())
      {
        lDeployTarget.setStatus(DeployTarget.Status.running);
      }
    }
    pDeploy.setComments(pRequest.getParameter("comments"));
    Long lContactId = new Long(Long.parseLong(pRequest.getParameter("contact")));
    pDeploy.setContact(aPersonDao.findById(lContactId, false));
  }
}
