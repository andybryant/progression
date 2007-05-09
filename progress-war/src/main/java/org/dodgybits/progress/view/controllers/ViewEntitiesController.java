package org.dodgybits.progress.view.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.ArtifactDao;
import org.dodgybits.progress.dao.BuildDao;
import org.dodgybits.progress.dao.ClientDao;
import org.dodgybits.progress.dao.DeployDao;
import org.dodgybits.progress.dao.DeployTargetDao;
import org.dodgybits.progress.dao.EnvironmentDao;
import org.dodgybits.progress.dao.LinkDao;
import org.dodgybits.progress.dao.TargetDao;
import org.dodgybits.progress.dao.PersonDao;
import org.dodgybits.progress.dao.ProjectDao;
import org.dodgybits.progress.model.Environment;
import org.dodgybits.progress.model.Link;
import org.dodgybits.progress.model.DeployTarget;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ViewEntitiesController extends MultiActionController
{
  private static Log cLogger = LogFactory.getLog(ViewEntitiesController.class);
  
  protected LinkDao aLinkDao;
  protected EnvironmentDao aEnvironmentDao;
  protected ProjectDao aProjectDao;
  protected ArtifactDao aArtifactDao;
  protected ClientDao aClientDao;
  protected TargetDao aTargetDao;
  protected PersonDao aPersonDao;
  protected BuildDao aBuildDao;
  protected DeployDao aDeployDao;
  protected DeployTargetDao aDeployTargetDao;
    
  public void setLinkDao(LinkDao pLinkDao)
  {
    aLinkDao = pLinkDao;
  }
  
  public void setEnvironmentDao(EnvironmentDao pEnvironmentDao)
  {
    aEnvironmentDao = pEnvironmentDao;
  }
  
  public void setProjectDao(ProjectDao pProjectDao)
  {
    aProjectDao = pProjectDao;
  }
  
  public void setArtifactDao(ArtifactDao pArtifactDao)
  {
    aArtifactDao = pArtifactDao;
  }
  
  public void setClientDao(ClientDao pClientDao)
  {
    aClientDao = pClientDao;
  }
  
  public void setTargetDao(TargetDao pTargetDao)
  {
    aTargetDao = pTargetDao;
  }
  
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
  
  public void setDeployTargetDao(DeployTargetDao pDeployTargetDao)
  {
    aDeployTargetDao = pDeployTargetDao;
  }

  public ModelAndView links(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    List<Environment> lEnvironments = aEnvironmentDao.findAll();
    lModel.put("environments", lEnvironments);
    Map<Long,List<Link>> lEnvironmentLinks = new HashMap<Long,List<Link>>();
    for (Environment e : lEnvironments)
    {
      lEnvironmentLinks.put(e.getId(), e.getLinks());
    }
    lModel.put("envLinks", lEnvironmentLinks);
    List<String> lTopics = aLinkDao.findTopics();
    lModel.put("topics", lTopics);
    Map<String,List<Link>> lTopicLinks = new HashMap<String,List<Link>>();
    for (String lTopic : lTopics)
    {
      lTopicLinks.put(lTopic, aLinkDao.findByTopic(lTopic));
    }
    lModel.put("topicLinks", lTopicLinks);
    
    cLogger.debug(lModel);
    return new ModelAndView("viewLinks", lModel);
  }
  
  public ModelAndView environments(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    List<Environment> lEnvironments = aEnvironmentDao.findAll();
    Map<Long,List<DeployTarget>> lDeployTargets = new HashMap<Long,List<DeployTarget>>();
    for (Environment e : lEnvironments)
    {
      lDeployTargets.put(e.getId(), aDeployTargetDao.findCurrentDeploysByEnvironment(e.getId()));
    }
    lModel.put("deployTargets", lDeployTargets);
    lModel.put("environments", lEnvironments);
    return new ModelAndView("viewEnvironments", lModel);
  }
  
  public ModelAndView projects(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("projects", aProjectDao.findAll());
    return new ModelAndView("viewProjects", lModel);
  }
  
  public ModelAndView artifacts(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("artifacts", aArtifactDao.findAll());
    return new ModelAndView("viewArtifacts", lModel);
  }
  
  public ModelAndView clients(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("clients", aClientDao.findAll());
    return new ModelAndView("viewClients", lModel);
  }
  
  public ModelAndView targets(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("targets", aTargetDao.findAll());
    return new ModelAndView("viewTargets", lModel);
  }
  
  public ModelAndView people(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("people", aPersonDao.findAll());
    return new ModelAndView("viewPeople", lModel);
  }
  
  public ModelAndView builds(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("builds", aBuildDao.findAll());
    return new ModelAndView("viewBuilds", lModel);
  }

  public ModelAndView deploys(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("deploys", aDeployDao.findAll());
    return new ModelAndView("viewDeploys", lModel);
  }

}
