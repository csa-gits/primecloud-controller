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
package jp.primecloud.auto.entity.crud;

import java.io.Serializable;

/**
 * <p>
 * IMAGE_AZUREに対応したエンティティのベースクラスです。
 * </p>
 *
 */
public abstract class BaseImageAzure implements Serializable {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;

    /** IMAGE_NO [BIGINT(19,0)] */
    private Long imageNo;

    /** IMAGE_NAME [VARCHAR(100,0)] */
    private String imageName;

    /** INSTANCE_TYPES [VARCHAR(500,0)] */
    private String instanceTypes;

    /**
     * imageNoを取得します。
     *
     * @return imageNo
     */
    public Long getImageNo() {
        return imageNo;
    }

    /**
     * imageNoを設定します。
     *
     * @param imageNo imageNo
     */
    public void setImageNo(Long imageNo) {
        this.imageNo = imageNo;
    }

    /**
     * imageNameを取得します。
     *
     * @return imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * imageNameを設定します。
     *
     * @param imageName imageName
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * instanceTypesを取得します。
     *
     * @return instanceTypes
     */
    public String getInstanceTypes() {
        return instanceTypes;
    }

    /**
     * instanceTypesを設定します。
     *
     * @param instanceTypes instanceTypes
     */
    public void setInstanceTypes(String instanceTypes) {
        this.instanceTypes = instanceTypes;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = 1;

        final int prime = 31;
        result = prime * result + ((imageNo == null) ? 0 : imageNo.hashCode());
        result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
        result = prime * result + ((instanceTypes == null) ? 0 : instanceTypes.hashCode());

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        final BaseImageAzure other = (BaseImageAzure) obj;
        if (imageNo == null) {
            if (other.imageNo != null) { return false; }
        } else if (!imageNo.equals(other.imageNo)) {
            return false;
        }
        if (imageName == null) {
            if (other.imageName != null) { return false; }
        } else if (!imageName.equals(other.imageName)) {
            return false;
        }
        if (instanceTypes == null) {
            if (other.instanceTypes != null) { return false; }
        } else if (!instanceTypes.equals(other.instanceTypes)) {
            return false;
        }

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ImageAzure").append(" [");
        sb.append("imageNo=").append(imageNo).append(", ");
        sb.append("imageName=").append(imageName).append(", ");
        sb.append("instanceTypes=").append(instanceTypes);
        sb.append("]");
        return sb.toString();
    }

}
