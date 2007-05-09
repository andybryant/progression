package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.LinkDao;
import org.dodgybits.progress.model.Link;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class LinkHibernateDao extends GenericHibernateDao<Link, Long> implements LinkDao
{

  @SuppressWarnings("unchecked")
  public List<String> findTopics()
  {
    return getHibernateTemplate().find("select distinct topic "
        + "from Link link "
        + "where link.topic is not null");
  }
  
  @SuppressWarnings("unchecked")
  public List<Link> findByTopic(String pTopic)
  {
    return getHibernateTemplate().find("from Link l "
        + "where topic = ? "
        + "order by l.displayOrder", pTopic);
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public List<Link> findAll()
  {
    return getHibernateTemplate().find("from Link l "
        + "order by l.displayOrder");
  }
  
}
