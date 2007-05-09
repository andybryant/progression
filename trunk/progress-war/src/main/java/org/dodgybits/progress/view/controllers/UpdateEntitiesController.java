package org.dodgybits.progress.view.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.dodgybits.progress.model.Artifact;
import org.dodgybits.progress.model.Build;
import org.dodgybits.progress.model.Client;
import org.dodgybits.progress.model.Deploy;
import org.dodgybits.progress.model.Environment;
import org.dodgybits.progress.model.Link;
import org.dodgybits.progress.model.Person;
import org.dodgybits.progress.model.Project;
import org.dodgybits.progress.model.Target;
import org.dodgybits.progress.model.Service.Status;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Display a form to manage the creation and modification of
 * persistent entities.
 */
public class UpdateEntitiesController extends MultiActionController
{
  private static final Log cLogger = LogFactory.getLog(UpdateEntitiesController.class);

  protected LinkDao aLinkDao;
  private EnvironmentDao aEnvironmentDao;
  private ProjectDao aProjectDao;
  private ArtifactDao aArtifactDao;
  private ClientDao aClientDao;
  private TargetDao aTargetDao;
  private PersonDao aPersonDao;
  private BuildDao aBuildDao;
  private DeployDao aDeployDao;
  private DeployTargetDao aDeployTargetDao;
  
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
    List<Link> lNewLinks = new ArrayList<Link>();
    List<Link> lUpdatedLinks = new ArrayList<Link>();
    List<Link> lDeletedLinks = new ArrayList<Link>();
    
    // read in current Links and see if any properties have changed or were marked for deletion
    List<Link> lCurrentLinks = aLinkDao.findAll();
    for (Link lLink : lCurrentLinks)
    {
      String lPrefix = "C" + lLink.getId() + "_";
      String lDeleted = pRequest.getParameter(lPrefix + "delete");
      if (Boolean.parseBoolean(lDeleted))
      {
        lDeletedLinks.add(lLink);
        continue;
      }
      
      boolean lIsDirty = false;
      String lName = pRequest.getParameter(lPrefix + "name");
      lIsDirty = lIsDirty || !StringUtils.equals(lName, lLink.getName());
      String lUrl = pRequest.getParameter(lPrefix + "url");
      lIsDirty = lIsDirty || !StringUtils.equals(lUrl, lLink.getUrl());
      int lOrder = Integer.parseInt(pRequest.getParameter(lPrefix + "order"));
      lIsDirty = lIsDirty || lOrder != lLink.getDisplayOrder();
      String lEnvIdStr = pRequest.getParameter(lPrefix + "environment");
      if (lEnvIdStr != null && lLink.getEnvironment() != null)
      {
        // environment link
        Long lEnvId = new Long(Long.parseLong(lEnvIdStr));
        Environment lEnv = aEnvironmentDao.findById(lEnvId, false);
        lIsDirty = lIsDirty || !ObjectUtils.equals(lLink.getEnvironment(), lEnv);
        if (lIsDirty)
        {
          cLogger.debug("Environment link has been modified: " + lLink.getUrl());
          lLink.setName(lName);
          lLink.setUrl(lUrl);
          lLink.setTopic(null);
          lLink.setEnvironment(lEnv);
          lLink.setDisplayOrder(lOrder);
          lUpdatedLinks.add(lLink);
        }
      }
      else
      {
        // developer link
        String lTopic = pRequest.getParameter(lPrefix + "topic");
        lIsDirty = lIsDirty || !StringUtils.equals(lTopic, lLink.getTopic());
        if (lIsDirty)
        {
          cLogger.debug("Developer link has been modified: " + lLink.getUrl());
          lLink.setName(lName);
          lLink.setUrl(lUrl);
          lLink.setTopic(lTopic);
          lLink.setDisplayOrder(lOrder);
          lUpdatedLinks.add(lLink);
        }
      }
    }
    
    // check if any new Links were added
    int lCount = 1;
    while (true)
    {
      String lPrefix = "Cnew" + lCount + "_";
      String lName = pRequest.getParameter(lPrefix + "name");
      if (lName == null)
      {
        // run out of new Link entries
        break;
      }
      else
      {
        if (StringUtils.isNotBlank(lName))
        {
          Link lLink = new Link();
          lLink.setName(lName);
          String lUrl = pRequest.getParameter(lPrefix + "url");
          lLink.setUrl(lUrl);
          String lTopic = pRequest.getParameter(lPrefix + "topic");
          lLink.setTopic(lTopic);
          // TODO validate order (handle errors)
          int lDisplayOrder = Integer.parseInt(pRequest.getParameter(lPrefix + "order"));
          lLink.setDisplayOrder(lDisplayOrder);
          String lEnvIdStr = pRequest.getParameter(lPrefix + "environment");
          if (StringUtils.isNotBlank(lEnvIdStr))
          {
            // environment link
            Long lEnvId = new Long(Long.parseLong(lEnvIdStr));
            Environment lEnv = aEnvironmentDao.findById(lEnvId, false);
            lLink.setEnvironment(lEnv);
            lEnv.getLinks().add(lLink);
          }
          lNewLinks.add(lLink);
        }
      }
      lCount++;
    }

    // save changes
    aLinkDao.makePersistent(lNewLinks);
    aLinkDao.makePersistent(lUpdatedLinks);
    aLinkDao.makeTransient(lDeletedLinks);
      
    // forward to view page
    // TODO get rid of view cut-and-paste code below
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
    
    int lModCount = lNewLinks.size() + lUpdatedLinks.size() + lDeletedLinks.size();
    lModel.put("message", "Saved, updated or deleted " + lModCount + " Links");
    return new ModelAndView("viewLinks", lModel);  
  }
  
  public ModelAndView environments(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    List<Environment> lNewEnvironments = new ArrayList<Environment>();
    List<Environment> lUpdatedEnvironments = new ArrayList<Environment>();
    List<Environment> lDeletedEnvironments = new ArrayList<Environment>();
    
    // read in current Environments and see if any properties have changed or were marked for deletion
    List<Environment> lCurrentEnvironments = aEnvironmentDao.findAll();
    for (Environment lEnvironment : lCurrentEnvironments)
    {
      String lPrefix = "C" + lEnvironment.getId() + "_";
      String lDeleted = pRequest.getParameter(lPrefix + "delete");
      if (Boolean.parseBoolean(lDeleted))
      {
        lDeletedEnvironments.add(lEnvironment);
        continue;
      }
      
      boolean lIsDirty = false;
      String lName = pRequest.getParameter(lPrefix + "name");
      lIsDirty = lIsDirty || !StringUtils.equals(lName, lEnvironment.getName());
      String lDescription = pRequest.getParameter(lPrefix + "description");
      lIsDirty = lIsDirty || !StringUtils.equals(lDescription, lEnvironment.getDescription());
      int lOrder = Integer.parseInt(pRequest.getParameter(lPrefix + "order"));
      lIsDirty = lIsDirty || lOrder != lEnvironment.getDisplayOrder();
      if (lIsDirty)
      {
        cLogger.debug("Environment has been modified: " + lEnvironment.getName());
        lEnvironment.setName(lName);
        lEnvironment.setDescription(lDescription);
        lEnvironment.setDisplayOrder(lOrder);
        lUpdatedEnvironments.add(lEnvironment);
      }
    }
    
    // check if any new Environments were added
    int lCount = 1;
    while (true)
    {
      String lPrefix = "Cnew" + lCount + "_";
      String lName = pRequest.getParameter(lPrefix + "name");
      if (lName == null)
      {
        // run out of new Environment entries
        break;
      }
      else
      {
        if (StringUtils.isNotBlank(lName))
        {
          Environment lEnvironment = new Environment();
          lEnvironment.setName(lName);
          String lDescription = pRequest.getParameter(lPrefix + "description");
          lEnvironment.setDescription(lDescription);
          // TODO validate order (handle errors)
          int lDisplayOrder = Integer.parseInt(pRequest.getParameter(lPrefix + "order"));
          lEnvironment.setDisplayOrder(lDisplayOrder);
          lNewEnvironments.add(lEnvironment);
        }
      }
      lCount++;
    }

    // save changes
    aEnvironmentDao.makePersistent(lNewEnvironments);
    aEnvironmentDao.makePersistent(lUpdatedEnvironments);
    cLogger.debug("Deleting Environments " + lDeletedEnvironments);
    aEnvironmentDao.makeTransient(lDeletedEnvironments);
      
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("environments", aEnvironmentDao.findAll());
    int lModCount = lNewEnvironments.size() + lUpdatedEnvironments.size() + lDeletedEnvironments.size();
    lModel.put("message", "Saved, updated or deleted " + lModCount + " Environments");
    return new ModelAndView("viewEnvironments", lModel);  
  }
  
  public ModelAndView projects(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    List<Project> lNewProjects = new ArrayList<Project>();
    List<Project> lUpdatedProjects = new ArrayList<Project>();
    List<Project> lDeletedProjects = new ArrayList<Project>();
    
    // read in current Projects and see if any properties have changed or were marked for deletion
    List<Project> lCurrentProjects = aProjectDao.findAll();
    for (Project lProject : lCurrentProjects)
    {
      String lPrefix = "C" + lProject.getId() + "_";
      String lDeleted = pRequest.getParameter(lPrefix + "delete");
      if (Boolean.parseBoolean(lDeleted))
      {
        lDeletedProjects.add(lProject);
        continue;
      }
      
      boolean lIsDirty = false;
      String lName = pRequest.getParameter(lPrefix + "name");
      lIsDirty = lIsDirty || !StringUtils.equals(lName, lProject.getName());
      String lDescription = pRequest.getParameter(lPrefix + "description");
      lIsDirty = lIsDirty || !StringUtils.equals(lDescription, lProject.getDescription());
      
      Long lClientId = new Long(Long.parseLong(pRequest.getParameter(lPrefix + "client")));
      lIsDirty = lIsDirty || !ObjectUtils.equals(lClientId, lProject.getClient().getId());
      if (lIsDirty)
      {
        cLogger.debug("Project has been modified: " + lProject.getName());
        lProject.setName(lName);
        lProject.setDescription(lDescription);
        lProject.setClient(aClientDao.findById(lClientId, false));
        lUpdatedProjects.add(lProject);
      }
    }
    
    // check if any new project were added
    int lCount = 1;
    while (true)
    {
      String lPrefix = "Cnew" + lCount + "_";
      String lName = pRequest.getParameter(lPrefix + "name");
      if (lName == null)
      {
        // run out of new project entries
        break;
      }
      else
      {
        if (StringUtils.isNotBlank(lName))
        {
          Project lProject = new Project();
          lProject.setName(lName);
          String lDescription = pRequest.getParameter(lPrefix + "description");
          lProject.setDescription(lDescription);
          Long lClientId = new Long(Long.parseLong(pRequest.getParameter(lPrefix + "client")));
          lProject.setClient(aClientDao.findById(lClientId, false));
          lNewProjects.add(lProject);
        }
      }
      lCount++;
    }

    // save changes
    aProjectDao.makePersistent(lNewProjects);
    aProjectDao.makePersistent(lUpdatedProjects);
    cLogger.debug("Deleting Projects " + lDeletedProjects);
    aProjectDao.makeTransient(lDeletedProjects);
      
    Map<String, Object> lModel = new HashMap<String, Object>();
    int lModCount = lNewProjects.size() + lUpdatedProjects.size() + lDeletedProjects.size();
    lModel.put("message", "Saved, updated or deleted " + lModCount + " Projects");
    lModel.put("projects", aProjectDao.findAll());
    return new ModelAndView("viewProjects", lModel);
  }
  
  public ModelAndView artifacts(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    List<Artifact> lNewArtifacts = new ArrayList<Artifact>();
    List<Artifact> lUpdatedArtifacts = new ArrayList<Artifact>();
    List<Artifact> lDeletedArtifacts = new ArrayList<Artifact>();
    
    // read in current Artifacts and see if any properties have changed or were marked for deletion
    List<Artifact> lCurrentArtifacts = aArtifactDao.findAll();
    for (Artifact lArtifact : lCurrentArtifacts)
    {
      String lPrefix = "C" + lArtifact.getId() + "_";
      String lDeleted = pRequest.getParameter(lPrefix + "delete");
      if (Boolean.parseBoolean(lDeleted))
      {
        lDeletedArtifacts.add(lArtifact);
        continue;
      }
      
      boolean lIsDirty = false;
      String lName = pRequest.getParameter(lPrefix + "name");
      lIsDirty = lIsDirty || !StringUtils.equals(lName, lArtifact.getName());
      String lDescription = pRequest.getParameter(lPrefix + "description");
      lIsDirty = lIsDirty || !StringUtils.equals(lDescription, lArtifact.getDescription());
      if (lIsDirty)
      {
        cLogger.debug("Artifact has been modified: " + lArtifact.getName());
        lArtifact.setName(lName);
        lArtifact.setDescription(lDescription);
        lUpdatedArtifacts.add(lArtifact);
      }
    }
    
    // check if any new Artifacts were added
    int lCount = 1;
    while (true)
    {
      String lPrefix = "Cnew" + lCount + "_";
      String lName = pRequest.getParameter(lPrefix + "name");
      if (lName == null)
      {
        // run out of new Artifact entries
        break;
      }
      else
      {
        if (StringUtils.isNotBlank(lName))
        {
          Artifact lArtifact = new Artifact();
          lArtifact.setName(lName);
          String lDescription = pRequest.getParameter(lPrefix + "description");
          lArtifact.setDescription(lDescription);
          lNewArtifacts.add(lArtifact);
        }
      }
      lCount++;
    }

    // save changes
    aArtifactDao.makePersistent(lNewArtifacts);
    aArtifactDao.makePersistent(lUpdatedArtifacts);
    cLogger.debug("Deleting artifacts " + lDeletedArtifacts);
    aArtifactDao.makeTransient(lDeletedArtifacts);
      
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("artifacts", aArtifactDao.findAll());
    int lModCount = lNewArtifacts.size() + lUpdatedArtifacts.size() + lDeletedArtifacts.size();
    lModel.put("message", "Saved, updated or deleted " + lModCount + " artifacts");
    return new ModelAndView("viewArtifacts", lModel);  
  }
  
  @SuppressWarnings("unchecked")
  public ModelAndView clients(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    List<Client> lNewClients = new ArrayList<Client>();
    List<Client> lUpdatedClients = new ArrayList<Client>();
    List<Client> lDeletedClients = new ArrayList<Client>();
    
    // read in current clients and see if any properties have changed or were marked for deletion
    List<Client> lCurrentClients = aClientDao.findAll();
    for (Client lClient : lCurrentClients)
    {
      String lPrefix = "C" + lClient.getId() + "_";
      String lDeleted = pRequest.getParameter(lPrefix + "delete");
      if (Boolean.parseBoolean(lDeleted))
      {
        lDeletedClients.add(lClient);
        continue;
      }
      
      String lName = pRequest.getParameter(lPrefix + "name");
      boolean lIsDirty = false;
      lIsDirty = lIsDirty || !StringUtils.equals(lName, lClient.getName());
      if (lIsDirty)
      {
        cLogger.debug("Client has been modified: " + lClient.getName());
        lClient.setName(lName);
        lUpdatedClients.add(lClient);
      }
    }
    
    // check if any new clients were added
    int lCount = 1;
    while (true)
    {
      String lPrefix = "Cnew" + lCount + "_";
      String lName = pRequest.getParameter(lPrefix + "name");
      if (lName == null)
      {
        // run out of new client entries
        break;
      }
      else
      {
        if (StringUtils.isNotBlank(lName))
        {
          Client lClient = new Client();
          lClient.setName(lName);
          lNewClients.add(lClient);
        }
      }
      lCount++;
    }

    // save changes
    aClientDao.makePersistent(lNewClients);
    aClientDao.makePersistent(lUpdatedClients);
    cLogger.debug("Deleting clients " + lDeletedClients);
    aClientDao.makeTransient(lDeletedClients);
      
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("clients", aClientDao.findAll());
    int lModCount = lNewClients.size() + lUpdatedClients.size() + lDeletedClients.size();
    lModel.put("message", "Saved, updated or deleted " + lModCount + " clients");
    return new ModelAndView("viewClients", lModel);
  }
  
  public ModelAndView targets(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    List<Target> lNewTargets = new ArrayList<Target>();
    List<Target> lUpdatedTargets = new ArrayList<Target>();
    List<Target> lDeletedTargets = new ArrayList<Target>();
    
    // read in current targets and see if any properties have changed or were marked for deletion
    List<Target> lCurrentTargets = aTargetDao.findAll();
    for (Target lTarget : lCurrentTargets)
    {
      String lPrefix = "C" + lTarget.getId() + "_";
      String lDeleted = pRequest.getParameter(lPrefix + "delete");
      if (Boolean.parseBoolean(lDeleted))
      {
        lDeletedTargets.add(lTarget);
        continue;
      }
      
      String lName = pRequest.getParameter(lPrefix + "name");
      boolean lIsDirty = false;
      lIsDirty = lIsDirty || !StringUtils.equals(lName, lTarget.getName());
      Long lEnvironmentId = new Long(Long.parseLong(pRequest.getParameter(lPrefix + "environment")));
      lIsDirty = lIsDirty || !ObjectUtils.equals(lEnvironmentId, lTarget.getEnvironment().getId());
      String lServerName = pRequest.getParameter(lPrefix + "serverName");
      lIsDirty = lIsDirty || !StringUtils.equals(lServerName, lTarget.getServerName());
      String lContainer = pRequest.getParameter(lPrefix + "container");
      lIsDirty = lIsDirty || !StringUtils.equals(lContainer, lTarget.getContainer());
      String lDescription = pRequest.getParameter(lPrefix + "description");
      lIsDirty = lIsDirty || !StringUtils.equals(lDescription, lTarget.getDescription());
      
      if (lIsDirty)
      {
        cLogger.debug("Target has been modified: " + lTarget.getName());
        lTarget.setName(lName);
        lTarget.setEnvironment(aEnvironmentDao.findById(lEnvironmentId, false));
        lTarget.setServerName(lServerName);
        lTarget.setContainer(lContainer);
        lTarget.setDescription(lDescription);
        lUpdatedTargets.add(lTarget);
      }
    }
    
    // check if any new targets were added
    int lCount = 1;
    while (true)
    {
      String lPrefix = "Cnew" + lCount + "_";
      String lName = pRequest.getParameter(lPrefix + "name");
      if (lName == null)
      {
        // run out of new target entries
        break;
      }
      else
      {
        if (StringUtils.isNotBlank(lName))
        {
          Target lTarget = new Target();
          lTarget.setName(lName);
          Long lEnvironmentId = new Long(Long.parseLong(pRequest.getParameter(lPrefix + "environment")));
          lTarget.setEnvironment(aEnvironmentDao.findById(lEnvironmentId, false));
          String lServerName = pRequest.getParameter(lPrefix + "serverName");
          lTarget.setServerName(lServerName);     
          String lContainer = pRequest.getParameter(lPrefix + "container");
          lTarget.setContainer(lContainer);    
          String lDescription = pRequest.getParameter(lPrefix + "description");
          lTarget.setDescription(lDescription);
          lNewTargets.add(lTarget);
        }
      }
      lCount++;
    }

    // save changes
    aTargetDao.makePersistent(lNewTargets);
    aTargetDao.makePersistent(lUpdatedTargets);
    cLogger.debug("Deleting targets " + lDeletedTargets);
    aTargetDao.makeTransient(lDeletedTargets);
      
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("targets", aTargetDao.findAll());
    int lModCount = lNewTargets.size() + lUpdatedTargets.size() + lDeletedTargets.size();
    lModel.put("message", "Saved, updated or deleted " + lModCount + " targets");
    return new ModelAndView("viewTargets", lModel);
  }
  
  public ModelAndView people(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    List<Person> lNewPeople = new ArrayList<Person>();
    List<Person> lUpdatedPeople = new ArrayList<Person>();
    List<Person> lDeletedPeople = new ArrayList<Person>();
    
    // read in current People and see if any properties have changed or were marked for deletion
    List<Person> lCurrentPeople = aPersonDao.findAll();
    for (Person lPerson : lCurrentPeople)
    {
      String lPrefix = "C" + lPerson.getId() + "_";
      String lDeleted = pRequest.getParameter(lPrefix + "delete");
      if (Boolean.parseBoolean(lDeleted))
      {
        lDeletedPeople.add(lPerson);
        continue;
      }
      
      boolean lIsDirty = false;
      String lName = pRequest.getParameter(lPrefix + "name");
      lIsDirty = lIsDirty || !StringUtils.equals(lName, lPerson.getName());
      String lEmailAddress = pRequest.getParameter(lPrefix + "emailAddress");
      lIsDirty = lIsDirty || !StringUtils.equals(lEmailAddress, lPerson.getEmailAddress());
      String lLocation = pRequest.getParameter(lPrefix + "location");
      lIsDirty = lIsDirty || !StringUtils.equals(lLocation, lPerson.getLocation());
      int lOrder = Integer.parseInt(pRequest.getParameter(lPrefix + "order"));
      lIsDirty = lIsDirty || lOrder != lPerson.getDisplayOrder();
      if (lIsDirty)
      {
        cLogger.debug("Person has been modified: " + lPerson.getName());
        lPerson.setName(lName);
        lPerson.setEmailAddress(lEmailAddress);
        lPerson.setLocation(lLocation);
        lPerson.setDisplayOrder(lOrder);
        lUpdatedPeople.add(lPerson);
      }
    }
    
    // check if any new People were added
    int lCount = 1;
    while (true)
    {
      String lPrefix = "Cnew" + lCount + "_";
      String lName = pRequest.getParameter(lPrefix + "name");
      if (lName == null)
      {
        // run out of new Person entries
        break;
      }
      else
      {
        if (StringUtils.isNotBlank(lName))
        {
          Person lPerson = new Person();
          lPerson.setName(lName);
          String lEmailAddress = pRequest.getParameter(lPrefix + "emailAddress");
          lPerson.setEmailAddress(lEmailAddress);
          String lLocation = pRequest.getParameter(lPrefix + "location");
          lPerson.setLocation(lLocation);
          // TODO validate order (handle errors)
          int lDisplayOrder = Integer.parseInt(pRequest.getParameter(lPrefix + "order"));
          lPerson.setDisplayOrder(lDisplayOrder);
          lNewPeople.add(lPerson);
        }
      }
      lCount++;
    }

    // save changes
    aPersonDao.makePersistent(lNewPeople);
    aPersonDao.makePersistent(lUpdatedPeople);
    aPersonDao.makeTransient(lDeletedPeople);
      
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("people", aPersonDao.findAll());
    int lModCount = lNewPeople.size() + lUpdatedPeople.size() + lDeletedPeople.size();
    lModel.put("message", "Saved, updated or deleted " + lModCount + " People");
    return new ModelAndView("viewPeople", lModel);  
  }

  public ModelAndView builds(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    List<Build> lNewBuilds = new ArrayList<Build>();
    List<Build> lUpdatedBuilds = new ArrayList<Build>();
    List<Build> lDeletedBuilds = new ArrayList<Build>();
    
    // read in current builds and see if any properties have changed or were marked for deletion
    List<Build> lCurrentBuilds = aBuildDao.findAll();
    for (Build lBuild : lCurrentBuilds)
    {
      String lPrefix = "C" + lBuild.getId() + "_";
      String lDeleted = pRequest.getParameter(lPrefix + "delete");
      if (Boolean.parseBoolean(lDeleted))
      {
        lDeletedBuilds.add(lBuild);
        continue;
      }
      
      String lVersion = pRequest.getParameter(lPrefix + "version");
      boolean lIsDirty = false;
      lIsDirty = lIsDirty || !StringUtils.equals(lVersion, lBuild.getVersion());
      Long lContactId = new Long(Long.parseLong(pRequest.getParameter(lPrefix + "contact")));
      lIsDirty = lIsDirty || !ObjectUtils.equals(lContactId, lBuild.getContact().getId());
      Status lStatus = Status.valueOf(pRequest.getParameter(lPrefix + "status"));
      lIsDirty = lIsDirty || (lStatus != lBuild.getStatus());
      String lComments = pRequest.getParameter(lPrefix + "comments");
      lIsDirty = lIsDirty || !StringUtils.equals(lComments, lBuild.getComments());
      
      if (lIsDirty)
      {
        cLogger.debug("Build has been modified: " + lBuild.getVersion());
        lBuild.setVersion(lVersion);
        lBuild.setContact(aPersonDao.findById(lContactId, false));
        lBuild.setStatus(lStatus);
        lBuild.setComments(lComments);
        lUpdatedBuilds.add(lBuild);
      }
    }
    
    // check if any new builds were added
    int lCount = 1;
    while (true)
    {
      String lPrefix = "Cnew" + lCount + "_";
      String lVersion = pRequest.getParameter(lPrefix + "version");
      if (lVersion == null)
      {
        // run out of new target entries
        break;
      }
      else
      {
        if (StringUtils.isNotBlank(lVersion))
        {
          Build lBuild = new Build();
          lBuild.setVersion(lVersion);
          lBuild.setCreatedDate(new Date());
          lBuild.setScheduledDate(null);
          lBuild.setPerformedDate(null);
          Long lProjectId = new Long(Long.parseLong(pRequest.getParameter(lPrefix + "project")));
          lBuild.setProject(aProjectDao.findById(lProjectId, false));
          Long lContactId = new Long(Long.parseLong(pRequest.getParameter(lPrefix + "contact")));
          lBuild.setContact(aPersonDao.findById(lContactId, false));
          Status lStatus = Status.valueOf(pRequest.getParameter(lPrefix + "status"));
          lBuild.setStatus(lStatus);
          Long lArtifactId = new Long(Long.parseLong(pRequest.getParameter(lPrefix + "artifact")));
          lBuild.setArtifact(aArtifactDao.findById(lArtifactId, false));
          String lComments = pRequest.getParameter(lPrefix + "comments");
          lBuild.setComments(lComments);
          lNewBuilds.add(lBuild);
        }
      }
      lCount++;
    }

    // save changes
    aBuildDao.makePersistent(lNewBuilds);
    aBuildDao.makePersistent(lUpdatedBuilds);
    // TODO handle failure when cannot delete build because of foreign key constraint
    cLogger.debug("Deleting builds " + lDeletedBuilds);
    aBuildDao.makeTransient(lDeletedBuilds);
      
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("builds", aBuildDao.findAll());
    int lModCount = lNewBuilds.size() + lUpdatedBuilds.size() + lDeletedBuilds.size();
    lModel.put("message", "Saved, updated or deleted " + lModCount + " builds");
    return new ModelAndView("viewBuilds", lModel);
  }
  
  public ModelAndView deploys(HttpServletRequest pRequest, HttpServletResponse pResponse)
  {
    List<Deploy> lUpdatedDeploys = new ArrayList<Deploy>();
    List<Deploy> lDeletedDeploys = new ArrayList<Deploy>();
    
    // read in current deploys and see if any properties have changed or were marked for deletion
    List<Deploy> lCurrentDeploys = aDeployDao.findAll();
    for (Deploy lDeploy : lCurrentDeploys)
    {
      String lPrefix = "C" + lDeploy.getId() + "_";
      String lDeleted = pRequest.getParameter(lPrefix + "delete");
      if (Boolean.parseBoolean(lDeleted))
      {
        lDeletedDeploys.add(lDeploy);
        continue;
      }
      
      boolean lIsDirty = false;
      Long lContactId = new Long(Long.parseLong(pRequest.getParameter(lPrefix + "contact")));
      lIsDirty = lIsDirty || !ObjectUtils.equals(lContactId, lDeploy.getContact().getId());
      Status lStatus = Status.valueOf(pRequest.getParameter(lPrefix + "status"));
      lIsDirty = lIsDirty || (lStatus != lDeploy.getStatus());
      String lComments = pRequest.getParameter(lPrefix + "comments");
      lIsDirty = lIsDirty || !StringUtils.equals(lComments, lDeploy.getComments());
      
      if (lIsDirty)
      {
        cLogger.debug("Deploy has been modified: " + lDeploy.getId());
        lDeploy.setContact(aPersonDao.findById(lContactId, false));
        lDeploy.setStatus(lStatus);
        lDeploy.setComments(lComments);
        lUpdatedDeploys.add(lDeploy);
      }
    }

    // save changes
    aDeployDao.makePersistent(lUpdatedDeploys);
    // TODO handle failure when cannot delete deploy because of foreign key constraint
    cLogger.debug("Deleting deploys " + lDeletedDeploys);
    aDeployDao.makeTransient(lDeletedDeploys);
      
    Map<String, Object> lModel = new HashMap<String, Object>();
    lModel.put("deploys", aDeployDao.findAll());
    int lModCount =  lUpdatedDeploys.size() + lDeletedDeploys.size();
    lModel.put("message", "Saved or updated " + lModCount + " deploys");
    return new ModelAndView("viewDeploys", lModel);
  }
}
