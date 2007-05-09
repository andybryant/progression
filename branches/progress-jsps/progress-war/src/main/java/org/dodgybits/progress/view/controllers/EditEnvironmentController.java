package org.dodgybits.progress.view.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.DeployTargetDao;
import org.dodgybits.progress.dao.EnvironmentDao;
import org.dodgybits.progress.model.DeployTarget;
import org.dodgybits.progress.model.Environment;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Prepare a page for editing an environment.
 */
public class EditEnvironmentController extends AbstractController
{
  private static final Log cLogger = LogFactory.getLog(EditEnvironmentController.class);
  
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
    Map<String, Object> lModel = new HashMap<String, Object>();
    cLogger.debug("Preparing edit environment page");
    Long lEnvironmentId = Long.parseLong(pRequest.getParameter("id"));
    Environment lEnvironment = aEnvironmentDao.findById(lEnvironmentId, false);
    lModel.put("environment", lEnvironment);
    lModel.put("deployTargets", aDeployTargetDao.findCurrentDeploysByEnvironment(lEnvironmentId));
    lModel.put("status", DeployTarget.Status.values());
    return new ModelAndView("editEnvironment", lModel);
  }

}
