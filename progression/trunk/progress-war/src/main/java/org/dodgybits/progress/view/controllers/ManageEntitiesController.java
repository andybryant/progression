package org.dodgybits.progress.view.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dodgybits.progress.dao.ArtifactDao;
import org.dodgybits.progress.dao.BuildDao;
import org.dodgybits.progress.dao.ClientDao;
import org.dodgybits.progress.dao.DeployDao;
import org.dodgybits.progress.dao.DeployTargetDao;
import org.dodgybits.progress.dao.EnvironmentDao;
import org.dodgybits.progress.dao.LinkDao;
import org.dodgybits.progress.dao.PersonDao;
import org.dodgybits.progress.dao.ProjectDao;
import org.dodgybits.progress.dao.TargetDao;
import org.dodgybits.progress.model.Environment;
import org.dodgybits.progress.model.Link;
import org.dodgybits.progress.model.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Display a form to manage the creation and modification of
 * persistent entities.
 */
public class ManageEntitiesController extends MultiActionController
{
  private LinkDao aLinkDao;
  private EnvironmentDao aEnvironmentDao;
  private ProjectDao aProjectDao;
  private ArtifactDao aArtifactDao;
  private ClientDao aClientDao;
  private TargetDao aTargetDao;
  private PersonDao aPersonDao;
  private BuildDao aBuildDao;
  private DeployDao aDeployDao;
  private DeployTargetDao aDeployTargetDao;
  
  private Integer aNewRowCount;
  
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
  
  public void setNewRowCount(Integer pNewRowCount)
  {
    aNewRowCount = pNewRowCount;
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
    lModel.put("newRowCount", aNewRowCount);
    return new ModelAndView("manageLinks", lModel);
  }
  
  public ModelAndView environments(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("environments", aEnvironmentDao.findAll());
    lModel.put("newRowCount", aNewRowCount);
    return new ModelAndView("manageEnvironments", lModel);
  }
  
  public ModelAndView projects(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("projects", aProjectDao.findAll());
    lModel.put("clients", aClientDao.findAll());
    lModel.put("newRowCount", aNewRowCount);
    return new ModelAndView("manageProjects", lModel);
  }
  
  public ModelAndView artifacts(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("artifacts", aArtifactDao.findAll());
    lModel.put("newRowCount", aNewRowCount);
    return new ModelAndView("manageArtifacts", lModel);
  }
  
  public ModelAndView clients(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("clients", aClientDao.findAll());
    lModel.put("newRowCount", aNewRowCount);
    return new ModelAndView("manageClients", lModel);
  }
  
  public ModelAndView targets(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("targets", aTargetDao.findAll());
    lModel.put("environments", aEnvironmentDao.findAll());
    lModel.put("newRowCount", aNewRowCount);
    return new ModelAndView("manageTargets", lModel);
  }
  
  public ModelAndView people(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("people", aPersonDao.findAll());
    lModel.put("newRowCount", aNewRowCount);
    return new ModelAndView("managePeople", lModel);
  }

  public ModelAndView builds(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("builds", aBuildDao.findAll());
    lModel.put("people", aPersonDao.findAll());
    lModel.put("projects", aProjectDao.findAll());
    lModel.put("artifacts", aArtifactDao.findAll());
    lModel.put("status", Service.Status.values());
    lModel.put("newRowCount", aNewRowCount);
    return new ModelAndView("manageBuilds", lModel);
  }
  
  public ModelAndView deploys(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("deploys", aDeployDao.findAll());
    lModel.put("people", aPersonDao.findAll());
    lModel.put("status", Service.Status.values());
    return new ModelAndView("manageDeploys", lModel);
  }
  
}
