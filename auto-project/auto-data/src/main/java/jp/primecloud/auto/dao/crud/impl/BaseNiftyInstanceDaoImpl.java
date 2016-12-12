/*
 * Copyright 2014 by SCSK Corporation.
 * 
 * This file is part of PrimeCloud Controller(TM).
 * 
 * PrimeCloud Controller(TM) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * PrimeCloud Controller(TM) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PrimeCloud Controller(TM). If not, see <http://www.gnu.org/licenses/>.
 */
package jp.primecloud.auto.dao.crud.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import jp.primecloud.auto.dao.crud.BaseNiftyInstanceDao;
import jp.primecloud.auto.entity.crud.NiftyInstance;

/**
 * <p>
 * {@link BaseNiftyInstanceDao}の実装クラスです。
 * </p>
 *
 */
@SuppressWarnings("deprecation")
public abstract class BaseNiftyInstanceDaoImpl extends SqlMapClientDaoSupport implements BaseNiftyInstanceDao {

    protected String namespace = "NiftyInstance";

    /**
     * {@inheritDoc}
     */
    @Override
    public NiftyInstance read(
            Long instanceNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("instanceNo", instanceNo);
        return (NiftyInstance) getSqlMapClientTemplate().queryForObject(getSqlMapId("read"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<NiftyInstance> readAll() {
        return (List<NiftyInstance>) getSqlMapClientTemplate().queryForList(getSqlMapId("readAll"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<NiftyInstance> readByKeyPairNo(
            Long keyPairNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("keyPairNo", keyPairNo);
        return (List<NiftyInstance>) getSqlMapClientTemplate().queryForList(getSqlMapId("readByKeyPairNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<NiftyInstance> readInInstanceNos(
            Collection<Long> instanceNos
        ) {
        if (instanceNos.isEmpty()) {
            return new ArrayList<NiftyInstance>();
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (instanceNos instanceof List) {
            paramMap.put("instanceNos", instanceNos);
        } else {
            paramMap.put("instanceNos", new ArrayList<Long>(instanceNos));
        }
        paramMap.put("orderBys", new String[0]);
        return (List<NiftyInstance>) getSqlMapClientTemplate().queryForList(getSqlMapId("readInInstanceNos"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(NiftyInstance entity) {
        String id = "create";
        getSqlMapClientTemplate().insert(getSqlMapId(id), entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(NiftyInstance entity) {
        getSqlMapClientTemplate().insert(getSqlMapId("update"), entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(NiftyInstance entity) {
        getSqlMapClientTemplate().insert(getSqlMapId("delete"), entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        getSqlMapClientTemplate().delete(getSqlMapId("deleteAll"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByInstanceNo(
            Long instanceNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("instanceNo", instanceNo);
        getSqlMapClientTemplate().delete(getSqlMapId("deleteByInstanceNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByKeyPairNo(
            Long keyPairNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("keyPairNo", keyPairNo);
        getSqlMapClientTemplate().delete(getSqlMapId("deleteByKeyPairNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countAll() {
        return (Long) getSqlMapClientTemplate().queryForObject(getSqlMapId("countAll"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countByInstanceNo(
            Long instanceNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("instanceNo", instanceNo);
        return (Long) getSqlMapClientTemplate().queryForObject(getSqlMapId("countByInstanceNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countByKeyPairNo(
            Long keyPairNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("keyPairNo", keyPairNo);
        return (Long) getSqlMapClientTemplate().queryForObject(getSqlMapId("countByKeyPairNo"), paramMap);
    }

    protected String getSqlMapId(String id) {
        if (namespace == null || namespace.length() == 0) {
            return id;
        }
        return namespace + "." + id;
    }

}
