package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.Deploy;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class DeployDaoTest extends
AbstractTransactionalDataSourceSpringContextTests
{
  private DeployDao aDeployDao;
  
  public String[] getConfigLocations()
  {
    return new String[] {"classpath:progressContext.xml"};
  }

  public void setDeployDao(DeployDao pDeployDao)
  {
    aDeployDao = pDeployDao;
  }
  
  public void testGetDeploy() throws Exception
  {
    Deploy lDeploy = aDeployDao.findById(new Long(9), false);
    System.out.println(lDeploy);
    assertEquals(new Long(3), lDeploy.getContact().getId());
  }
  
  public void testFindPendingDeploys() throws Exception
  {
    List<Deploy> lDeploys = aDeployDao.findPendingDeploys();
    assertEquals(0, lDeploys.size());
  }
  
  public void testFindLastDeploysByEnvironment() throws Exception
  {
    List<Deploy> lDeploys = aDeployDao.findLatestDeploysByEnvironment(new Long(1));
    System.out.println(lDeploys);
    assertEquals(1, lDeploys.size());
    assertEquals(new Long(4), lDeploys.get(0).getContact().getId());
  }
  
}
