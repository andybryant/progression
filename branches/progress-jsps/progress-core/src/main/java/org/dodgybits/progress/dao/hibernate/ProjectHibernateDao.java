package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.ProjectDao;
import org.dodgybits.progress.model.Project;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class ProjectHibernateDao extends GenericHibernateDao<Project, Long> implements
    ProjectDao
{
  @SuppressWarnings("unchecked")
  @Override
  public List<Project> findAll()
  {
    return getHibernateTemplate().find("from Project p "
        + "order by p.name");
  }
}
