package org.dodgybits.progress.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dodgybits.progress.dao.GenericDao;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements the generic CRUD data access operations using Hibernate APIs.
 * <p>
 * To write a DAO, subclass and parameterize this class with your persistent class.
 * Of course, assuming that you have a traditional 1:1 appraoch for Entity:DAO design.
 *
 * @author Christian Bauer
 */
@Transactional
public abstract class GenericHibernateDao<T, ID extends Serializable> extends HibernateDaoSupport
  implements GenericDao<T, ID>  {
  private static final Log cLogger = LogFactory.getLog(GenericHibernateDao.class);

    private Class<T> aPersistentClass;

    public GenericHibernateDao() {
        aPersistentClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
     }


    public Class<T> getPersistentClass() {
        return aPersistentClass;
    }

    @SuppressWarnings("unchecked")
    public T findById(ID pId, boolean pLock) {
        T lEntity;
        if (pLock)
            lEntity = (T) getHibernateTemplate().load(getPersistentClass(), pId, LockMode.UPGRADE);
        else
            lEntity = (T) getHibernateTemplate().load(getPersistentClass(), pId);

        return lEntity;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return findByCriteria();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByExample(T pExampleInstance, String... pExcludeProperty) {
        Criteria lCrit = getSession().createCriteria(getPersistentClass());
        Example lExample =  Example.create(pExampleInstance);
        for (String exclude : pExcludeProperty) {
            lExample.excludeProperty(exclude);
        }
        lCrit.add(lExample);
        return lCrit.list();
    }

    @SuppressWarnings("unchecked")
    public T makePersistent(T pEntity) {
      getHibernateTemplate().saveOrUpdate(pEntity);
        return pEntity;
    }

    public List<T> makePersistent(List<T> pEntities) {
      for(T lEntity : pEntities)
      {
        getHibernateTemplate().saveOrUpdate(lEntity);
      }
      return pEntities;
    }

    public void makeTransient(T pEntity) {
      getHibernateTemplate().delete(pEntity);
    }

    public void makeTransient(List<T> pEntities) {
      for(T lEntity : pEntities)
      {
        cLogger.debug("Deleting " + lEntity);
        makeTransient(lEntity);
        cLogger.debug("Done");
      }
    }
    
    public void flush() {
      getHibernateTemplate().flush();
    }

    public void clear() {
      getHibernateTemplate().clear();
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected List<T> findByCriteria(Criterion... pCriterion) {
        Criteria lCrit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : pCriterion) {
            lCrit.add(c);
        }
        return lCrit.list();
   }

}
