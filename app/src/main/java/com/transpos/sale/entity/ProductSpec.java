package com.transpos.sale.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "pos_product_spec")
public class ProductSpec extends BaseEntity {

    /// <summary>
    /// 商品ID
    /// </summary>
    @ColumnInfo
    private String productId;

    /// <summary>
    /// 规格编号
    /// </summary>
    @ColumnInfo
    private String specNo;

    /// <summary>
    /// 规格名称
    /// </summary>
    @ColumnInfo
    private String specification;

    /// <summary>
    /// 进价
    /// </summary>
    @ColumnInfo
    private double purPrice;

    /// <summary>
    /// 零售价
    /// </summary>
    @ColumnInfo
    private double salePrice;

    /// <summary>
    /// 最低售价
    /// </summary>
    @ColumnInfo
    private double minPrice;

    /// <summary>
    /// 会员价
    /// </summary>
    @ColumnInfo
    private double vipPrice;

    /// <summary>
    /// 会员价2
    /// </summary>
    @ColumnInfo
    private double vipPrice2;

    /// <summary>
    /// 会员价3
    /// </summary>
    @ColumnInfo
    private double vipPrice3;

    /// <summary>
    /// 会员价4
    /// </summary>
    @ColumnInfo
    private double vipPrice4;

    /// <summary>
    /// 会员价5
    /// </summary>
    @ColumnInfo
    private double vipPrice5;

    /// <summary>
    /// 配送价
    /// </summary>
    @ColumnInfo
    private double postPrice;

    /// <summary>
    /// 批发价
    /// </summary>
    @ColumnInfo
    private double batchPrice;

    /// <summary>
    /// 批发价2
    /// </summary>
    @ColumnInfo
    private double batchPrice2;

    /// <summary>
    /// 批发价3
    /// </summary>
    @ColumnInfo
    private double batchPrice3;

    /// <summary>
    /// 批发价4
    /// </summary>
    @ColumnInfo
    private double batchPrice4;

    /// <summary>
    /// 批发价5
    /// </summary>
    @ColumnInfo
    private double batchPrice5;

    /// <summary>
    /// 批发价6
    /// </summary>
    @ColumnInfo
    private double batchPrice6;

    /// <summary>
    /// 批发价7
    /// </summary>
    @ColumnInfo
    private double batchPrice7;

    /// <summary>
    /// 批发价8
    /// </summary>
    @ColumnInfo
    private double batchPrice8;

    /// <summary>
    /// 外送价
    /// </summary>
    @ColumnInfo
    private double otherPrice;

    /// <summary>
    /// PLUS商品标识
    /// </summary>
    @ColumnInfo
    private boolean plusFlag;

    /// <summary>
    /// PLUS价格
    /// </summary>
    @ColumnInfo
    private double plusPrice;


    /// <summary>
    /// PLUS商品优惠开始时间
    /// </summary>
    @ColumnInfo
    private String validStartDate;

    /// <summary>
    /// PLUS商品优惠结束时间
    /// </summary>
    @ColumnInfo
    private String validendDate;


    /// <summary>
    /// 进货规格
    /// </summary>
    @ColumnInfo
    private double purchaseSpec;

    /// <summary>
    /// 状态
    /// </summary>
    @ColumnInfo
    private int status;

    /// <summary>
    /// 存储类型
    /// </summary>
    @ColumnInfo
    private int storageType;

    /// <summary>
    /// 规格图存储地址
    /// </summary>
    @ColumnInfo
    private String storageAddress;

    /// <summary>
    /// 删除标识
    /// </summary>
    @ColumnInfo
    private int deleteFlag;

    /// <summary>
    /// 是否默认
    /// </summary>
    @ColumnInfo
    private int isDefault;

    /// <summary>
    /// 库存上限
    /// </summary>
    @ColumnInfo
    private double topLimit;

    /// <summary>
    /// 库存下限
    /// </summary>
    @ColumnInfo
    private double lowerLimit;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSpecNo() {
        return specNo;
    }

    public void setSpecNo(String specNo) {
        this.specNo = specNo;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public double getPurPrice() {
        return purPrice;
    }

    public void setPurPrice(double purPrice) {
        this.purPrice = purPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public double getVipPrice2() {
        return vipPrice2;
    }

    public void setVipPrice2(double vipPrice2) {
        this.vipPrice2 = vipPrice2;
    }

    public double getVipPrice3() {
        return vipPrice3;
    }

    public void setVipPrice3(double vipPrice3) {
        this.vipPrice3 = vipPrice3;
    }

    public double getVipPrice4() {
        return vipPrice4;
    }

    public void setVipPrice4(double vipPrice4) {
        this.vipPrice4 = vipPrice4;
    }

    public double getVipPrice5() {
        return vipPrice5;
    }

    public void setVipPrice5(double vipPrice5) {
        this.vipPrice5 = vipPrice5;
    }

    public double getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(double postPrice) {
        this.postPrice = postPrice;
    }

    public double getBatchPrice() {
        return batchPrice;
    }

    public void setBatchPrice(double batchPrice) {
        this.batchPrice = batchPrice;
    }

    public double getBatchPrice2() {
        return batchPrice2;
    }

    public void setBatchPrice2(double batchPrice2) {
        this.batchPrice2 = batchPrice2;
    }

    public double getBatchPrice3() {
        return batchPrice3;
    }

    public void setBatchPrice3(double batchPrice3) {
        this.batchPrice3 = batchPrice3;
    }

    public double getBatchPrice4() {
        return batchPrice4;
    }

    public void setBatchPrice4(double batchPrice4) {
        this.batchPrice4 = batchPrice4;
    }

    public double getBatchPrice5() {
        return batchPrice5;
    }

    public void setBatchPrice5(double batchPrice5) {
        this.batchPrice5 = batchPrice5;
    }

    public double getBatchPrice6() {
        return batchPrice6;
    }

    public void setBatchPrice6(double batchPrice6) {
        this.batchPrice6 = batchPrice6;
    }

    public double getBatchPrice7() {
        return batchPrice7;
    }

    public void setBatchPrice7(double batchPrice7) {
        this.batchPrice7 = batchPrice7;
    }

    public double getBatchPrice8() {
        return batchPrice8;
    }

    public void setBatchPrice8(double batchPrice8) {
        this.batchPrice8 = batchPrice8;
    }

    public double getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(double otherPrice) {
        this.otherPrice = otherPrice;
    }

    public boolean isPlusFlag() {
        return plusFlag;
    }

    public void setPlusFlag(boolean plusFlag) {
        this.plusFlag = plusFlag;
    }

    public double getPlusPrice() {
        return plusPrice;
    }

    public void setPlusPrice(double plusPrice) {
        this.plusPrice = plusPrice;
    }

    public String getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(String validStartDate) {
        this.validStartDate = validStartDate;
    }

    public String getValidendDate() {
        return validendDate;
    }

    public void setValidendDate(String validendDate) {
        this.validendDate = validendDate;
    }

    public double getPurchaseSpec() {
        return purchaseSpec;
    }

    public void setPurchaseSpec(double purchaseSpec) {
        this.purchaseSpec = purchaseSpec;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStorageType() {
        return storageType;
    }

    public void setStorageType(int storageType) {
        this.storageType = storageType;
    }

    public String getStorageAddress() {
        return storageAddress;
    }

    public void setStorageAddress(String storageAddress) {
        this.storageAddress = storageAddress;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public double getTopLimit() {
        return topLimit;
    }

    public void setTopLimit(double topLimit) {
        this.topLimit = topLimit;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }
}
