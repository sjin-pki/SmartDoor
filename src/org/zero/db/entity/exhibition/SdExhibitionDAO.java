package org.zero.db.entity.exhibition;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zero.db.entity.user.BaseHibernateDAO;

/**
 	* A data access object (DAO) providing persistence and search support for SdExhibition entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see org.zero.db.entity.exhibition.SdExhibition
  * @author MyEclipse Persistence Tools 
 */

public class SdExhibitionDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(SdExhibitionDAO.class);
		//property constants
	public static final String SD_EX_SUBJECT = "sdExSubject";
	public static final String SD_EX_HTML = "sdExHtml";



    
    public void save(SdExhibition transientInstance) {
        log.debug("saving SdExhibition instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(SdExhibition persistentInstance) {
        log.debug("deleting SdExhibition instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public SdExhibition findById( java.lang.Integer id) {
        log.debug("getting SdExhibition instance with id: " + id);
        try {
            SdExhibition instance = (SdExhibition) getSession()
                    .get("org.zero.db.entity.exhibition.SdExhibition", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(SdExhibition instance) {
        log.debug("finding SdExhibition instance by example");
        try {
            List results = getSession()
                    .createCriteria("org.zero.db.entity.exhibition.SdExhibition")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding SdExhibition instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SdExhibition as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findBySdExSubject(Object sdExSubject
	) {
		return findByProperty(SD_EX_SUBJECT, sdExSubject
		);
	}
	
	public List findBySdExHtml(Object sdExHtml
	) {
		return findByProperty(SD_EX_HTML, sdExHtml
		);
	}
	

	public List findAll() {
		log.debug("finding all SdExhibition instances");
		try {
			String queryString = "from SdExhibition";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public SdExhibition merge(SdExhibition detachedInstance) {
        log.debug("merging SdExhibition instance");
        try {
            SdExhibition result = (SdExhibition) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SdExhibition instance) {
        log.debug("attaching dirty SdExhibition instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(SdExhibition instance) {
        log.debug("attaching clean SdExhibition instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}