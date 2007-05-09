package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.Client;
import org.dodgybits.progress.model.Project;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class ClientDaoTest extends
AbstractTransactionalDataSourceSpringContextTests
{
  private ClientDao aClientDao;
  
  public String[] getConfigLocations()
  {
    return new String[] {"classpath:progressContext.xml"};
  }

  public void setClientDao(ClientDao pClientDao)
  {
    aClientDao = pClientDao;
  }
  
  public void testGetClient() throws Exception
  {
    Client lClient = aClientDao.findById(new Long(1), false);
    System.out.println(lClient);
    assertEquals("Optus", lClient.getName());
    List<Project> lProjects = lClient.getProjects();
    assertNotNull(lProjects);
  }
  
  public void testAddClient() throws Exception
  {
    int lClientCount = aClientDao.findAll().size();
    Client lClient = new Client();
    lClient.setName("testing");
    lClient = aClientDao.makePersistent(lClient);
    setComplete(); // change behavior from rollback to commit
    endTransaction();

    startNewTransaction();
    aClientDao.makeTransient(lClient);
    setComplete(); // change behavior from rollback to commit
    endTransaction();
    
    assertEquals(lClientCount, aClientDao.findAll().size());
  }
  
}
