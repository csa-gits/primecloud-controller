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
import jp.primecloud.auto.dao.crud.BaseAwsSnapshotDao;
import jp.primecloud.auto.entity.crud.AwsSnapshot;

/**
 * <p>
 * {@link BaseAwsSnapshotDao}の実装クラスです。
 * </p>
 *
 */
public abstract class BaseAwsSnapshotDaoImpl extends SqlMapClientDaoSupport implements BaseAwsSnapshotDao {

    protected String namespace = "AwsSnapshot";

    /**
     * {@inheritDoc}
     */
    @Override
    public AwsSnapshot read(
            Long snapshotNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("snapshotNo", snapshotNo);
        return (AwsSnapshot) getSqlMapClientTemplate().queryForObject(getSqlMapId("read"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AwsSnapshot> readAll() {
        return (List<AwsSnapshot>) getSqlMapClientTemplate().queryForList(getSqlMapId("readAll"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AwsSnapshot> readByFarmNo(
            Long farmNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("farmNo", farmNo);
        return (List<AwsSnapshot>) getSqlMapClientTemplate().queryForList(getSqlMapId("readByFarmNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AwsSnapshot> readByPlatformNo(
            Long platformNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("platformNo", platformNo);
        return (List<AwsSnapshot>) getSqlMapClientTemplate().queryForList(getSqlMapId("readByPlatformNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AwsSnapshot> readByVolumeNo(
            Long volumeNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("volumeNo", volumeNo);
        return (List<AwsSnapshot>) getSqlMapClientTemplate().queryForList(getSqlMapId("readByVolumeNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AwsSnapshot> readInSnapshotNos(
            Collection<Long> snapshotNos
        ) {
        if (snapshotNos.isEmpty()) {
            return new ArrayList<AwsSnapshot>();
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (snapshotNos instanceof List) {
            paramMap.put("snapshotNos", snapshotNos);
        } else {
            paramMap.put("snapshotNos", new ArrayList<Long>(snapshotNos));
        }
        paramMap.put("orderBys", new String[0]);
        return (List<AwsSnapshot>) getSqlMapClientTemplate().queryForList(getSqlMapId("readInSnapshotNos"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(AwsSnapshot entity) {
        String id = "create";
        if (entity.getSnapshotNo() == null) {
            id = "createAuto";
        }
        getSqlMapClientTemplate().insert(getSqlMapId(id), entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(AwsSnapshot entity) {
        getSqlMapClientTemplate().insert(getSqlMapId("update"), entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(AwsSnapshot entity) {
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
    public void deleteBySnapshotNo(
            Long snapshotNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("snapshotNo", snapshotNo);
        getSqlMapClientTemplate().delete(getSqlMapId("deleteBySnapshotNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByFarmNo(
            Long farmNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("farmNo", farmNo);
        getSqlMapClientTemplate().delete(getSqlMapId("deleteByFarmNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByPlatformNo(
            Long platformNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("platformNo", platformNo);
        getSqlMapClientTemplate().delete(getSqlMapId("deleteByPlatformNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteByVolumeNo(
            Long volumeNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("volumeNo", volumeNo);
        getSqlMapClientTemplate().delete(getSqlMapId("deleteByVolumeNo"), paramMap);
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
    public long countBySnapshotNo(
            Long snapshotNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("snapshotNo", snapshotNo);
        return (Long) getSqlMapClientTemplate().queryForObject(getSqlMapId("countBySnapshotNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countByFarmNo(
            Long farmNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("farmNo", farmNo);
        return (Long) getSqlMapClientTemplate().queryForObject(getSqlMapId("countByFarmNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countByPlatformNo(
            Long platformNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("platformNo", platformNo);
        return (Long) getSqlMapClientTemplate().queryForObject(getSqlMapId("countByPlatformNo"), paramMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long countByVolumeNo(
            Long volumeNo
        ) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("volumeNo", volumeNo);
        return (Long) getSqlMapClientTemplate().queryForObject(getSqlMapId("countByVolumeNo"), paramMap);
    }

    protected String getSqlMapId(String id) {
        if (namespace == null || namespace.length() == 0) {
            return id;
        }
        return namespace + "." + id;
    }

}
