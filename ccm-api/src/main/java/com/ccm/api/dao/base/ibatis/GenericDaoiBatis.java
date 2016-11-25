package com.ccm.api.dao.base.ibatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.ccm.api.SecurityHolder;
import com.ccm.api.ValidationChecker;
import com.ccm.api.dao.base.GenericDao;
import com.ccm.api.model.base.BaseObject;
import com.ccm.api.model.constant.OXIConstant;
import com.ccm.api.util.DateUtil;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * <pre>
 *      &lt;bean id="fooDao" class="com.ccm.api.base.dao.ibatis.GenericDaoiBatis"&gt;
 *          &lt;constructor-arg value="com.ccm.api.base.model.Foo"/&gt;
 *          &lt;property name="sqlMapClient" ref="sqlMapClient"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * 
 * @author Bobby Diaz, Bryan Noll
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public class GenericDaoiBatis<T, PK extends Serializable> implements GenericDao<T, PK> {
	/**
	 * Log variable for all child classes. Uses LogFactory.getLog(getClass())
	 * from Commons Logging
	 */
	protected final Log log = LogFactory.getLog(getClass());
	private Class<T> persistentClass;
	private SqlMapClientTemplate sqlMapClientTemplate = new SqlMapClientTemplate();

	/**
	 * Constructor that takes in a class to see which type of entity to persist.
	 * Use this constructor when subclassing or using dependency injection.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 */
	public GenericDaoiBatis(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * Constructor that takes in a class to see which type of entity to persist
	 * Use this constructor when manually creating a new instance.
	 * 
	 * @param persistentClass
	 *            the class type you'd like to persist
	 * @param sqlMapClient
	 *            the configured SqlMapClient
	 */
	public GenericDaoiBatis(final Class<T> persistentClass, SqlMapClient sqlMapClient) {
		this.persistentClass = persistentClass;
		this.sqlMapClientTemplate.setSqlMapClient(sqlMapClient);
	}

	/**
	 * Set the iBATIS Database Layer SqlMapClient to work with. Either this or a
	 * "sqlMapClientTemplate" is required.
	 * 
	 * @param sqlMapClient
	 *            the configured SqlMapClient
	 */
	@Autowired
	@Required
	public final void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClientTemplate.setSqlMapClient(sqlMapClient);
	}

	/**
	 * Return the SqlMapClientTemplate for this DAO, pre-initialized with the
	 * SqlMapClient or set explicitly.
	 * 
	 * @return an initialized SqlMapClientTemplate
	 */
	public final SqlMapClientTemplate getSqlMapClientTemplate() {
		return this.sqlMapClientTemplate;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getSqlMapClientTemplate().queryForList(IBatisDaoUtils.getSelectQuery(ClassUtils.getShortName(this.persistentClass)), null);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> getAllDistinct() {
		Collection result = new LinkedHashSet(getAll());
		return new ArrayList(result);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		T object = (T) getSqlMapClientTemplate().queryForObject(IBatisDaoUtils.getFindQuery(ClassUtils.getShortName(this.persistentClass)), id);
		if (object == null) {
			log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
			throw new ObjectRetrievalFailureException(ClassUtils.getShortName(this.persistentClass), id);
		}
		return object;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public boolean exists(PK id) {
		T object = (T) getSqlMapClientTemplate().queryForObject(IBatisDaoUtils.getFindQuery(ClassUtils.getShortName(this.persistentClass)), id);
		return object != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	public T save(final T object) {
		String className = ClassUtils.getShortName(object.getClass());
		Object primaryKey = IBatisDaoUtils.getPrimaryKeyValue(object);
		Class primaryKeyClass = IBatisDaoUtils.getPrimaryKeyFieldType(object);
		String keyId = null;

		// check for null id
		if (primaryKey != null) {
			keyId = primaryKey.toString();
		}

		// check for new record
		if (!StringUtils.hasText(keyId)) {
			IBatisDaoUtils.prepareObjectForSaveOrUpdate(object);
			primaryKey = UUID.randomUUID().toString().replace("-", "");
			IBatisDaoUtils.setPrimaryKey(object, primaryKeyClass, primaryKey);
			createBaseObject(object);
			getSqlMapClientTemplate().insert(IBatisDaoUtils.getInsertQuery(className), object);
		} else {
			updateBaseObject(object);
			IBatisDaoUtils.prepareObjectForSaveOrUpdate(object);
			getSqlMapClientTemplate().update(IBatisDaoUtils.getUpdateQuery(className), object);
		}

		// check for null id
		if (IBatisDaoUtils.getPrimaryKeyValue(object) == null) {
			throw new ObjectRetrievalFailureException(className, object);
		} else {
			return object;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(PK id) {
		getSqlMapClientTemplate().update(IBatisDaoUtils.getDeleteQuery(ClassUtils.getShortName(this.persistentClass)), id);
	}

	@Override
	public void softDelete(String primaryKey) {
		ValidationChecker.notEmpty(primaryKey);
		updateDelFlag(primaryKey, true);
	}

	@Override
	public void unDelete(String primaryKey) {
		ValidationChecker.notEmpty(primaryKey);
		updateDelFlag(primaryKey, false);
	}

	public void createBaseObject(Object object) {
		if (object instanceof BaseObject) {
			BaseObject baseObject = ((BaseObject) object);

			if (baseObject.getCreatedTime() == null) {
				baseObject.setCreatedTime(DateUtil.currentDateTime());
			}
			if (!StringUtils.hasText(baseObject.getCreatedBy())) {
				baseObject.setCreatedBy(SecurityHolder.getUserId());
				if (!StringUtils.hasText(baseObject.getCreatedBy())) {
					baseObject.setCreatedBy(OXIConstant.creatorCode);
				}
			}
		}
	}

	public void updateBaseObject(Object object) {
		if (object instanceof BaseObject) {
			BaseObject baseObject = ((BaseObject) object);
			if (!StringUtils.hasText(baseObject.getUpdatedBy())) {
				baseObject.setUpdatedBy(SecurityHolder.getUserId());
				if (!StringUtils.hasText(baseObject.getUpdatedBy())) {
					baseObject.setUpdatedBy(OXIConstant.creatorCode);
				}
			}
			if (baseObject.getLastModifyTime() == null) {
				baseObject.setLastModifyTime(DateUtil.currentDateTime());
			}
		}
	}

	private void updateDelFlag(String primaryKey, Boolean delFlag) {
		ValidationChecker.notNull(primaryKey, delFlag);
		String className = ClassUtils.getShortName(this.persistentClass);
		Map<String, Object> param = new HashMap<String, Object>();
		String fieldName = IBatisDaoUtils.getPrimaryKeyFieldNameFromClass(this.persistentClass);
		param.put(fieldName, primaryKey);
		param.put("updatedBy", SecurityHolder.getUserId());
		param.put("lastModifyTime", DateUtil.currentDateTime());
		param.put("delFlag", delFlag);
		getSqlMapClientTemplate().update(IBatisDaoUtils.getUpdateQuery(className) + "DelFlag", param);
	}

}
