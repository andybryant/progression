package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.Link;

public interface LinkDao extends GenericDao<Link, Long>
{
  public List<String> findTopics();
  public List<Link> findByTopic(String pTopic);
}
