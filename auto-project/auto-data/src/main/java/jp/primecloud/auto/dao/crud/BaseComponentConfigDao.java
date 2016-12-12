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
package jp.primecloud.auto.dao.crud;

import java.util.Collection;
import java.util.List;
import jp.primecloud.auto.entity.crud.ComponentConfig;

/**
 * <p>
 * COMPONENT_CONFIGに対応したDAOのベースインタフェースです。
 * </p>
 *
 */
public interface BaseComponentConfigDao {

    /**
     * 主キーに該当するレコードを検索します。
     *
     * @param configNo configNo
     * @return 主キーに該当するレコードのエンティティ。レコードがない場合はnull。
     */
    public ComponentConfig read(
            Long configNo
        );

    /**
     * 全てのレコードを検索します。
     *
     * @return 全てのレコードのエンティティのリスト。レコードがない場合は空リスト。
     */
    public List<ComponentConfig> readAll();

    /**
     * 一意キーに該当するレコードを検索します。
     *
     * @param componentNo componentNo
     * @param configName configName
     * @return 一意キーに該当するレコードのエンティティ。レコードがない場合はnull。
     */
    public ComponentConfig readByComponentNoAndConfigName(
            Long componentNo,
            String configName
        );

    /**
     * 与えられたキーに該当するレコードを検索します。
     *
     * @param componentNo componentNo
     * @return 与えられたキーに該当するレコードのエンティティのリスト。レコードがない場合は空リスト。
     */
    public List<ComponentConfig> readByComponentNo(
            Long componentNo
        );

    /**
     * 主キーのコレクションに該当するレコードを検索します。
     *
     * @param configNos configNoのコレクション
     * @return 主キーのコレクションに該当するレコードのエンティティのリスト。レコードがない場合は空リスト。
     */
    public List<ComponentConfig> readInConfigNos(
            Collection<Long> configNos
        );

    /**
     * 与えられたエンティティの内容でレコードを挿入します。
     *
     * @param entity エンティティ
     */
    public void create(ComponentConfig entity);

    /**
     * 与えられたエンティティの内容でレコードを更新します。
     *
     * @param entity エンティティ
     */
    public void update(ComponentConfig entity);

    /**
     * 与えられたエンティティのレコードを削除します。
     *
     * @param entity エンティティ
     */
    public void delete(ComponentConfig entity);

    /**
     * 全てのレコードを削除します。
     */
    public void deleteAll();

    /**
     * 主キーに該当するレコードを削除します。
     *
     * @param configNo configNo
     */
    public void deleteByConfigNo(
            Long configNo
        );

    /**
     * 一意キーに該当するレコードを削除します。
     *
     * @param componentNo componentNo
     * @param configName configName
     */
    public void deleteByComponentNoAndConfigName(
            Long componentNo,
            String configName
        );

    /**
     * 与えられたキーに該当するレコードを削除します。
     *
     * @param componentNo componentNo
     */
    public void deleteByComponentNo(
            Long componentNo
        );

    /**
     * 全てのレコードの件数を取得します。
     *
     * @return 全てのレコードの件数。
     */
    public long countAll();

    /**
     * 主キーに該当するレコードの件数を取得します。
     *
     * @param configNo configNo
     * @return 主キーに該当するレコードの件数。
     */
    public long countByConfigNo(
            Long configNo
        );

    /**
     * 一意キーに該当するレコードの件数を取得します。
     *
     * @param componentNo componentNo
     * @param configName configName
     * @return 一意キーに該当するレコードの件数。
     */
    public long countByComponentNoAndConfigName(
            Long componentNo,
            String configName
        );

    /**
     * 与えられたキーに該当するレコードの件数を取得します。
     *
     * @param componentNo componentNo
     * @return 与えられたキーに該当するレコードの件数。
     */
    public long countByComponentNo(
            Long componentNo
        );

}
