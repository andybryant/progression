package org.dodgybits.progress.view.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.DeployTargetDao;
import org.dodgybits.progress.dao.EnvironmentDao;
import org.dodgybits.progress.model.DeployTarget;
import org.dodgybits.progress.model.Environment;
import org.dodgybits.progress.model.DeployTarget.Status;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Save a modified environment (including deploy targets).
 */
public class SaveEnvironmentController extends AbstractController
{
  private static final Log cLogger = LogFactory.getLog(SaveEnvironmentController.class);
  
  private EnvironmentDao aEnvironmentDao;
  private DeployTargetDao aDeployTargetDao;

  public void setEnvironmentDao(EnvironmentDao pEnvironmentDao)
  {
    aEnvironmentDao = pEnvironmentDao;
  }
  
  public void setDeployTargetDao(DeployTargetDao pDeployTargetDao)
  {
    aDeployTargetDao = pDeployTargetDao;
  }
  
  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest pRequest,
      HttpServletResponse pResponse) throws Exception
  {
    Environment lEnvironment = null;
    String lEnvironmentIdStr = pRequest.getParameter("environment");
    lEnvironment = aEnvironmentDao.findById(Long.parseLong(lEnvironmentIdStr), false);
    
    // update environment and target deploys
    lEnvironment.setDescription(pRequest.getParameter("description"));

    List<DeployTarget> lUpdatedDeployTargets = new ArrayList<DeployTarget>();
    
    // read in current DeployTargets and see if any properties have changed or were marked for deletion
    List<DeployTarget> lCurrentDeployTargets = aDeployTargetDao.findCurrentDeploysByEnvironment(lEnvironment.getId());

    for (DeployTarget lDeployTarget : lCurrentDeployTargets)
    {
      String lPrefix = "C" + lDeployTarget.getId() + "_";

      boolean lIsDirty = false;
      String lComments = pRequest.getParameter(lPrefix + "comments");
      lIsDirty = lIsDirty || !StringUtils.equals(lComments, lDeployTarget.getComments());
      Status lStatus = Status.valueOf(pRequest.getParameter(lPrefix + "status"));
      lIsDirty = lIsDirty || (lStatus != lDeployTarget.getStatus());
      if (lIsDirty)
      {
        cLogger.debug("Deploy target has been modified: " + lDeployTarget.getId());
        lDeployTarget.setComments(lComments);
        lDeployTarget.setStatus(lStatus);
        lUpdatedDeployTargets.add(lDeployTarget);
      }
    }
    // save changes
    aDeployTargetDao.makePersistent(lUpdatedDeployTargets);
    
    Map<String, Object> lModel = new HashMap<String, Object>();
    List<Environment> lEnvironments = aEnvironmentDao.findAll();
    Map<Long,List<DeployTarget>> lDeployTargetsMap = new HashMap<Long,List<DeployTarget>>();
    for (Environment e : lEnvironments)
    {
      lDeployTargetsMap.put(e.getId(), aDeployTargetDao.findCurrentDeploysByEnvironment(e.getId()));
    }
    lModel.put("deployTargets", lDeployTargetsMap);
    lModel.put("environments", lEnvironments);
    return new ModelAndView("viewEnvironments", lModel);
  }

}
