package org.dodgybits.progress.dao.hibernate;

import java.util.List;

import org.dodgybits.progress.dao.PersonDao;
import org.dodgybits.progress.model.Person;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class PersonHibernateDao extends GenericHibernateDao<Person, Long> implements
    PersonDao
{
  @SuppressWarnings("unchecked")
  @Override
  public List<Person> findAll()
  {
    return getHibernateTemplate().find("from Person p "
        + "order by p.displayOrder");
  }
}
