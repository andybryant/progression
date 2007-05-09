package org.dodgybits.progress.view.gwt.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.dodgybits.progress.dao.DeployTargetDao;
import org.dodgybits.progress.dao.EnvironmentDao;
import org.dodgybits.progress.dao.LinkDao;
import org.dodgybits.progress.model.DeployTarget;
import org.dodgybits.progress.model.Environment;
import org.dodgybits.progress.model.Link;
import org.dodgybits.progress.model.Target;
import org.dodgybits.progress.view.gwt.client.EntityService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class EntityServiceImpl extends RemoteServiceServlet implements EntityService
{
  private static final long serialVersionUID = 1L;
  protected EnvironmentDao aEnvironmentDao;
  protected DeployTargetDao aDeployTargetDao;
  protected LinkDao aLinkDao;
  
  public void setEnvironmentDao(EnvironmentDao pEnvironmentDao)
  {
    aEnvironmentDao = pEnvironmentDao;
  }
  
  public void setDeployTargetDao(DeployTargetDao pDeployTargetDao)
  {
    aDeployTargetDao = pDeployTargetDao;
  }
  
  public void setLinkDao(LinkDao pLinkDao)
  {
    aLinkDao = pLinkDao;
  }
  
  public Map getEnvironmentTargetMap()
  {
    Map<String,List<String>> lEnvMap = new HashMap<String,List<String>>();
    
    List<Environment> lEnvironments = aEnvironmentDao.findAll();
    for (Environment lEnv : lEnvironments)
    {
      List<String> lTargetList = new ArrayList<String>();
      for (Target lTarget : lEnv.getTargets())
      {
        lTargetList.add(lTarget.getName());
      }
      lEnvMap.put(lEnv.getName(), lTargetList);
    }
    
    return lEnvMap;
  }
  
  /**
   * Return properties about all the current deployments for all environments.
   */
  public Map getCurrentDeployTargets(String[] pProperties)
  {
    Map<String, List<String[]>> lResponse = new HashMap<String, List<String[]>>();
    List<Environment> lEnvironments = aEnvironmentDao.findAll();
    for (Environment e : lEnvironments)
    {
      List<DeployTarget> lDeployTargets = aDeployTargetDao.findCurrentDeploysByEnvironment(e.getId());
      if (!lDeployTargets.isEmpty())
      {
        List<String[]> lProperties = new ArrayList<String[]>();
        for (DeployTarget dt : lDeployTargets)
        {
          lProperties.add(getProperties(dt, pProperties));
        }
        lResponse.put(e.getName(), lProperties);
      }
    }
    return lResponse;
  }
  
  public Map getEnvironmentLinks(String[] pProperties)
  {
    Map<String, List<String[]>> lResponse = new HashMap<String, List<String[]>>();
    List<Environment> lEnvironments = aEnvironmentDao.findAll();
    for (Environment e : lEnvironments)
    {
      List<Link> lLinks = e.getLinks();
      if (!lLinks.isEmpty())
      {
        List<String[]> lProperties = new ArrayList<String[]>();
        for (Link lLink : lLinks)
        {
          lProperties.add(getProperties(lLink, pProperties));
        }
        lResponse.put(e.getName(), lProperties);
      }
    }
    return lResponse;
  }
  
  public Map getDeveloperLinks(String[] pProperties)
  {
    Map<String, List<String[]>> lResponse = new HashMap<String, List<String[]>>();
    List<String> lTopics = aLinkDao.findTopics();
    for (String lTopic : lTopics)
    {
      List<Link> lLinks = aLinkDao.findByTopic(lTopic);
      if (!lLinks.isEmpty())
      {
        List<String[]> lProperties = new ArrayList<String[]>();
        for (Link lLink : lLinks)
        {
          lProperties.add(getProperties(lLink, pProperties));
        }
        lResponse.put(lTopic, lProperties);
      }
    }
    return lResponse;
  }  
  
  public static String[] getProperties(Object pBean, String[] pProperties)
  {
    String[] lResults = new String[pProperties.length];
    for (int i = 0; i < pProperties.length; i++)
    {
      try
      {
        lResults[i] = BeanUtils.getProperty(pBean, pProperties[i]);
      }
      catch (Exception e)
      {
        lResults[i] = "Failed: " + e.getMessage();
      }
    }
    return lResults;
  }


}
