package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.Environment;
import org.dodgybits.progress.model.Link;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class LinkDaoTest extends AbstractTransactionalDataSourceSpringContextTests
{
  private LinkDao aLinkDao;
  private EnvironmentDao aEnvironmentDao;
  
  public String[] getConfigLocations()
  {
    return new String[] {"classpath:progressContext.xml"};
  }

  public void setLinkDao(LinkDao pLinkDao)
  {
    aLinkDao = pLinkDao;
  }
  
  public void setEnvironmentDao(EnvironmentDao pEnvironmentDao)
  {
    aEnvironmentDao = pEnvironmentDao;
  }
  
  public void testGetLink() throws Exception
  {
    Link lLink = aLinkDao.findById(new Long(7), false);
    System.out.println(lLink);
    assertEquals("Java", lLink.getTopic());
  }
  
  public void testGetTopics() throws Exception
  {
    List<String> lTopics = aLinkDao.findTopics();
    System.out.println(lTopics);    
    assertNotNull(lTopics);
    assertTrue(lTopics.contains("Java"));
  }
  
  public void testGetTopicLinks() throws Exception
  {
    List<Link> lTopicLinks = aLinkDao.findByTopic("Java");
    System.out.println(lTopicLinks);    
    assertTrue(lTopicLinks.size() > 0);
  }
  
  public void testAddEnvLink() throws Exception
  {
    Link lNewLink = new Link();
    lNewLink.setName("testing123");
    lNewLink.setUrl("http://www.123.com");
    Environment lEnv = aEnvironmentDao.findById(new Long(1), false);
    lNewLink.setEnvironment(lEnv);
    lEnv.getLinks().add(lNewLink);
    aLinkDao.makePersistent(lNewLink);
    assertNotNull(lNewLink.getId());
  }

  public void testAddDevLink() throws Exception
  {
    Link lNewLink = new Link();
    lNewLink.setName("testing123");
    lNewLink.setUrl("http://www.123.com");
    lNewLink.setTopic("topico");
    aLinkDao.makePersistent(lNewLink);
    assertNotNull(lNewLink.getId());
  }

  
}
