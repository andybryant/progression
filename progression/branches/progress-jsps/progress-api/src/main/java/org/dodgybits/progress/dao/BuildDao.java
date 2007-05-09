package org.dodgybits.progress.dao;

import java.util.List;

import org.dodgybits.progress.model.Build;

public interface BuildDao extends GenericDao<Build, Long>
{
  
  /**
   * Find all scheduled builds that have not been performed.
   */
  public List<Build> findPendingBuilds();

  
}
