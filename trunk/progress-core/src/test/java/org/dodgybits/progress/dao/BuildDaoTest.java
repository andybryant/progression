package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.Build;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class BuildDaoTest extends AbstractTransactionalDataSourceSpringContextTests
{
  private BuildDao aBuildDao;
  
  public String[] getConfigLocations()
  {
    return new String[] {"classpath:progressContext.xml"};
  }

  public void setBuildDao(BuildDao pBuildDao)
  {
    aBuildDao = pBuildDao;
  }
  
  public void testGetBuild() throws Exception
  {
    Build lBuild = aBuildDao.findById(new Long(8), false);
    System.out.println(lBuild);
    //assertEquals(new Long(1), lBuild.getArtifacts().get(0).getId());
  }
  
  public void testFindPendingBuilds() throws Exception
  {
    List<Build> lBuilds = aBuildDao.findPendingBuilds();
    assertNotNull(lBuilds);
  }
}
