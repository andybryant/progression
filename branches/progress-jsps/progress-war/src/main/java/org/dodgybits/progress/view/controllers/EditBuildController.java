package org.dodgybits.progress.view.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.BuildDao;
import org.dodgybits.progress.dao.PersonDao;
import org.dodgybits.progress.model.Build;
import org.dodgybits.progress.model.Person;
import org.dodgybits.progress.model.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * Prepare a page for editing a build.
 */
public class EditBuildController extends AbstractController
{
  private static final Log cLogger = LogFactory.getLog(EditBuildController.class);
  
  private BuildDao aBuildDao;
  private PersonDao aPersonDao;

  public void setBuildDao(BuildDao pBuildDao)
  {
    aBuildDao = pBuildDao;
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
    cLogger.debug("Preparing edit build page");
    Long lBuildId = Long.parseLong(pRequest.getParameter("build"));
    Build lBuild = aBuildDao.findById(lBuildId, false);
    lModel.put("build", lBuild);
    List<Person> lPeople = aPersonDao.findAll();
    lModel.put("people", lPeople);
    lModel.put("status", Service.Status.values());
    return new ModelAndView("editBuild", lModel);
  }

}
