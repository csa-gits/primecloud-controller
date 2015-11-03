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
package jp.primecloud.auto.api.instance;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import jp.primecloud.auto.api.ApiSupport;
import jp.primecloud.auto.api.ApiValidate;

import org.apache.commons.lang.StringUtils;

import jp.primecloud.auto.api.response.instance.EditInstanceNiftyResponse;
import jp.primecloud.auto.common.status.InstanceStatus;
import jp.primecloud.auto.entity.crud.Image;
import jp.primecloud.auto.entity.crud.ImageNifty;
import jp.primecloud.auto.entity.crud.Instance;
import jp.primecloud.auto.entity.crud.NiftyKeyPair;
import jp.primecloud.auto.entity.crud.Platform;
import jp.primecloud.auto.entity.crud.User;
import jp.primecloud.auto.exception.AutoApplicationException;


@Path("/EditInstanceNifty")
public class EditInstanceNifty extends ApiSupport {

    /**
     *
     * サーバ編集(Nifty)
     * @param instanceNo インスタンス番号
     * @param instanceType インスタンスタイプ
     * @param keyName キーペア名
     * @param comment コメント
     *
     * @return EditInstanceNiftyResponse
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EditInstanceNiftyResponse editInstanceNifty(
            @Context UriInfo uriInfo,
            @QueryParam(PARAM_NAME_INSTANCE_NO) String instanceNo,
            @QueryParam(PARAM_NAME_INSTANCE_TYPE) String instanceType,
            @QueryParam(PARAM_NAME_KEY_NAME) String keyName,
            @QueryParam(PARAM_NAME_COMMENT) String comment){

        EditInstanceNiftyResponse response = new EditInstanceNiftyResponse();

            // 入力チェック
            // InstanceNo
            ApiValidate.validateInstanceNo(instanceNo);

            //インスタンス取得
            Instance instance = getInstance(Long.parseLong(instanceNo));
            
            // 権限チェック
            User user = checkAndGetUser(instance);

            InstanceStatus status = InstanceStatus.fromStatus(instance.getStatus());
            if (InstanceStatus.STOPPED != status) {
                // インスタンスが停止済み以外
                throw new AutoApplicationException("EAPI-100014", instanceNo);
            }

            //プラットフォーム取得
            Platform platform = platformDao.read(instance.getPlatformNo());
            if (platform == null) {
                // プラットフォームが存在しない
                throw new AutoApplicationException("EAPI-100000", "Platform",
                        PARAM_NAME_PLATFORM_NO, instance.getPlatformNo());
            }
            if (!PLATFORM_TYPE_NIFTY.equals(platform.getPlatformType())) {
                //プラットフォームがNifty以外
                throw new AutoApplicationException("EAPI-100031", "Nifty", instanceNo, instance.getPlatformNo());
            }

            // イメージ取得
            Image image = imageDao.read(instance.getImageNo());
            if (image == null || image.getPlatformNo().equals(platform.getPlatformNo()) == false) {
                // イメージが存在しない
                throw new AutoApplicationException("EAPI-100000", "Image",
                        PARAM_NAME_IMAGE_NO, instance.getImageNo());
            }

            // InstanceType
            ApiValidate.validateInstanceType(instanceType, true);
            if(checkInstanceType(platform, image, instanceType) == false) {
                // インスタンスタイプがイメージのインスタンスタイプに含まれていない
                throw new AutoApplicationException("EAPI-000011", instance.getImageNo(), instanceType);
            }

            // KeyName
            ApiValidate.validateKeyName(keyName);
            Long keyPairNo = getKeyPairNo(user.getUserNo(), platform.getPlatformNo(), keyName);
            if (keyPairNo == null) {
                // キーペアがプラットフォームに存在しない
                throw new AutoApplicationException("EAPI-000012", platform.getPlatformNo(), keyName);
            }

            // Comment
            ApiValidate.validateComment(comment);

            //インスタンス(VMware)の更新
            instanceService.updateNiftyInstance(
                    Long.parseLong(instanceNo), instance.getInstanceName(), comment, instanceType, keyPairNo);

            response.setSuccess(true);

        return  response;
    }

    /**
     *
     * カンマ区切りのインスタンスタイプ(名称)の文字列からインスタンスタイプ(名称)のリストを作成する
     *
     * @param instanceTypesText カンマ区切りのインスタンスタイプ(名称)文字列
     * @return インスタンスタイプ(名称)のリスト
     */
    private static List<String> getInstanceTypes(String instanceTypesText) {
        List<String> instanceTypes = new ArrayList<String>();
        if (StringUtils.isNotEmpty(instanceTypesText)) {
            for (String instanceType: StringUtils.split(instanceTypesText, ",")) {
                instanceTypes.add(instanceType.trim());
            }
        }
        return instanceTypes;
    }

    /**
     *
     * インスタンスタイプ(名称)が対象イメージで使用可能かチェックする
     *
     * @param platform プラットフォーム
     * @param image イメージ
     * @param instanceType インスタンスタイプ
     * @return true:存在する、false:存在しない
     */
    private boolean checkInstanceType(Platform platform, Image image, String instanceType) {
        //Nifty
        ImageNifty imageNifty = imageNiftyDao.read(image.getImageNo());
        List<String> instanceTypes  = getInstanceTypes(imageNifty.getInstanceTypes());
        return instanceTypes.contains(instanceType);
    }

    /**
     *
     * キーペア名からキーペアNoを取得
     *
     * @param userNo ユーザ番号
     * @param platformNo プラットフォーム番号
     * @param keyName キーペア名
     * @return キーペアNo(存在しない場合はNull)
     */
    private Long getKeyPairNo(Long userNo, Long platformNo, String keyName) {
        Long keyPairNo = null;
        //Nifty
        List<NiftyKeyPair> niftyKeyPairs = niftyDescribeService.getKeyPairs(userNo, platformNo);
        for (NiftyKeyPair niftyKeyPair: niftyKeyPairs) {
            if(StringUtils.equals(keyName, niftyKeyPair.getKeyName())) {
                keyPairNo = niftyKeyPair.getKeyNo();
                break;
            }
        }

        return keyPairNo;
    }
}