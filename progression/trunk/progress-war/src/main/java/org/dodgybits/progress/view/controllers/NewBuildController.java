package org.dodgybits.progress.view.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.ArtifactDao;
import org.dodgybits.progress.dao.PersonDao;
import org.dodgybits.progress.dao.ProjectDao;
import org.dodgybits.progress.model.Artifact;
import org.dodgybits.progress.model.Person;
import org.dodgybits.progress.model.Project;
import org.dodgybits.progress.model.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Prepare a page for a new build.
 */
public class NewBuildController extends AbstractController
{
  private static final Log cLogger = LogFactory.getLog(NewBuildController.class);
  
  private ProjectDao aProjectDao;
  private ArtifactDao aArtifactDao;
  private PersonDao aPersonDao;
    
  public void setProjectDao(ProjectDao pProjectDao)
  {
    aProjectDao = pProjectDao;
  }
  
  public void setArtifactDao(ArtifactDao pArtifactDao)
  {
    aArtifactDao = pArtifactDao;
  }
    
  public void setPersonDao(PersonDao pPersonDao)
  {
    aPersonDao = pPersonDao;
  }

  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest pRequest,
      HttpServletResponse pResponse) throws Exception
  {
    cLogger.debug("Preparing new build page");
    Map<String, Object> lModel = new HashMap<String, Object>();
    List<Artifact> lArtifacts = aArtifactDao.findAll();
    lModel.put("artifacts", lArtifacts);
    List<Project> lProjects = aProjectDao.findAll();
    lModel.put("projects", lProjects);
    List<Person> lPeople = aPersonDao.findAll();
    lModel.put("people", lPeople);
    lModel.put("status", Service.Status.values());
    return new ModelAndView("newBuild", lModel);
  }

}
