package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.ArtifactDao;
import org.dodgybits.progress.model.Artifact;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ArtifactHibernateDao extends GenericHibernateDao<Artifact, Long> implements ArtifactDao
{
  @SuppressWarnings("unchecked")
  @Override
  public List<Artifact> findAll()
  {
    return getHibernateTemplate().find("from Artifact a "
        + "order by a.name");
  }
}
