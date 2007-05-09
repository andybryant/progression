package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.Environment;
import org.dodgybits.progress.model.Target;
import org.dodgybits.progress.model.Link;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class EnvironmentDaoTest extends AbstractTransactionalDataSourceSpringContextTests
{
  private EnvironmentDao aEnvironmentDao;
  
  public String[] getConfigLocations()
  {
    return new String[] {"classpath:progressContext.xml"};
  }

  public void setEnvironmentDao(EnvironmentDao pEnvironmentDao)
  {
    aEnvironmentDao = pEnvironmentDao;
  }
  
  public void testGetEnvironment() throws Exception
  {
    Environment lEnv = aEnvironmentDao.findById(new Long(1), false);
    System.out.println(lEnv);
    assertEquals("Optus UAT", lEnv.getName());
    List<Link> lLinks = lEnv.getLinks();
    System.out.println(lLinks);
    assertTrue(lLinks.size() > 0);
    List<Target> lTargets = lEnv.getTargets();
    System.out.println(lTargets);
  }
  
}
