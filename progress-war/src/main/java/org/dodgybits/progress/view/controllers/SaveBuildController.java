package org.dodgybits.progress.view.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.ArtifactDao;
import org.dodgybits.progress.dao.BuildDao;
import org.dodgybits.progress.dao.PersonDao;
import org.dodgybits.progress.dao.ProjectDao;
import org.dodgybits.progress.model.Build;
import org.dodgybits.progress.model.Service.Status;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Save a new or edited build.
 */
public class SaveBuildController extends AbstractController
{
  private static final Log cLogger = LogFactory.getLog(SaveBuildController.class);
  
  private ArtifactDao aArtifactDao;
  private ProjectDao aProjectDao;
  private PersonDao aPersonDao;
  private BuildDao aBuildDao;
    
  public void setArtifactDao(ArtifactDao pArtifactDao)
  {
    aArtifactDao = pArtifactDao;
  }

  public void setProjectDao(ProjectDao pProjectDao)
  {
    aProjectDao = pProjectDao;
  }
  
  public void setPersonDao(PersonDao pPersonDao)
  {
    aPersonDao = pPersonDao;
  }

  public void setBuildDao(BuildDao pBuildDao)
  {
    aBuildDao = pBuildDao;
  }
  
  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest pRequest,
      HttpServletResponse pResponse) throws Exception
  {
    Build lBuild = null;
    String lBuildIdStr = pRequest.getParameter("build");
    boolean lNewEntity = (lBuildIdStr == null);
    if (lNewEntity)
    {
      lBuild = populateNewBuild(pRequest);
    }
    else
    {
      lBuild = aBuildDao.findById(Long.parseLong(lBuildIdStr), false);
    }

    updateBuild(pRequest, lBuild);
    
    cLogger.debug("Saving build " + lBuild);
    aBuildDao.makePersistent(lBuild);
    
    Map<String, Object> lModel = new HashMap<String, Object>();
    List<Build> lBuilds = aBuildDao.findAll();
    lModel.put("builds", lBuilds);
    return new ModelAndView("viewBuilds", lModel);
  }

  /**
   * Create a new build and populate it with attributes that are only
   * modifiable for new builds.
   */
  private Build populateNewBuild(HttpServletRequest pRequest)
  {
    Build lBuild = new Build();
    Long lProjectId = new Long(Long.parseLong(pRequest.getParameter("project")));
    lBuild.setProject(aProjectDao.findById(lProjectId, false));
    lBuild.setCreatedDate(new Date());
    lBuild.setScheduledDate(null);
    lBuild.setPerformedDate(null);
    lBuild.setVersion(pRequest.getParameter("version"));
    Long lArtifactId = new Long(Long.parseLong(pRequest.getParameter("artifact")));
    lBuild.setArtifact(aArtifactDao.findById(lArtifactId, false));
    return lBuild;
  }
  
  /**
   * Update attributes that are modifiable for both new and edited builds.
   */
  private void updateBuild(HttpServletRequest pRequest, Build pBuild)
  {
    Long lContactId = new Long(Long.parseLong(pRequest.getParameter("contact")));
    pBuild.setContact(aPersonDao.findById(lContactId, false));
    Status lStatus = Status.valueOf(pRequest.getParameter("status"));
    pBuild.setStatus(lStatus);
    if (lStatus == Status.complete)
    {
      pBuild.setPerformedDate(new Date());
    }
    pBuild.setComments(pRequest.getParameter("comments"));
  }
  
}
