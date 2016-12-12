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
import jp.primecloud.auto.entity.crud.Platform;

/**
 * <p>
 * PLATFORMに対応したDAOのベースインタフェースです。
 * </p>
 *
 */
public interface BasePlatformDao {

    /**
     * 主キーに該当するレコードを検索します。
     *
     * @param platformNo platformNo
     * @return 主キーに該当するレコードのエンティティ。レコードがない場合はnull。
     */
    public Platform read(
            Long platformNo
        );

    /**
     * 全てのレコードを検索します。
     *
     * @return 全てのレコードのエンティティのリスト。レコードがない場合は空リスト。
     */
    public List<Platform> readAll();

    /**
     * 一意キーに該当するレコードを検索します。
     *
     * @param platformName platformName
     * @return 一意キーに該当するレコードのエンティティ。レコードがない場合はnull。
     */
    public Platform readByPlatformName(
            String platformName
        );

    /**
     * 主キーのコレクションに該当するレコードを検索します。
     *
     * @param platformNos platformNoのコレクション
     * @return 主キーのコレクションに該当するレコードのエンティティのリスト。レコードがない場合は空リスト。
     */
    public List<Platform> readInPlatformNos(
            Collection<Long> platformNos
        );

    /**
     * 与えられたエンティティの内容でレコードを挿入します。
     *
     * @param entity エンティティ
     */
    public void create(Platform entity);

    /**
     * 与えられたエンティティの内容でレコードを更新します。
     *
     * @param entity エンティティ
     */
    public void update(Platform entity);

    /**
     * 与えられたエンティティのレコードを削除します。
     *
     * @param entity エンティティ
     */
    public void delete(Platform entity);

    /**
     * 全てのレコードを削除します。
     */
    public void deleteAll();

    /**
     * 主キーに該当するレコードを削除します。
     *
     * @param platformNo platformNo
     */
    public void deleteByPlatformNo(
            Long platformNo
        );

    /**
     * 一意キーに該当するレコードを削除します。
     *
     * @param platformName platformName
     */
    public void deleteByPlatformName(
            String platformName
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
     * @param platformNo platformNo
     * @return 主キーに該当するレコードの件数。
     */
    public long countByPlatformNo(
            Long platformNo
        );

    /**
     * 一意キーに該当するレコードの件数を取得します。
     *
     * @param platformName platformName
     * @return 一意キーに該当するレコードの件数。
     */
    public long countByPlatformName(
            String platformName
        );

}
