package com.ccm.api.dao.user.ibatis;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ccm.api.dao.user.LookupDao;
import com.ccm.api.model.user.Role;

/**
 * iBatis implementation of LookupDao.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Repository
public class LookupDaoiBatis implements LookupDao {
    private Log log = LogFactory.getLog(LookupDaoiBatis.class);
    private SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate();

    @Autowired
    public final void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClientTemplate.setSqlMapClient(sqlMapClient);
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<Role> getRoles() {
        log.debug("Retrieving all role names...");

        return sqlMapClientTemplate.queryForList("getRoles", null);
    }
}
