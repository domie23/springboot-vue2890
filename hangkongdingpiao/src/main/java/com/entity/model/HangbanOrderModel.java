package com.entity.model;

import com.entity.HangbanOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 航班订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HangbanOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String hangbanOrderUuidNumber;


    /**
     * 航班
     */
    private Integer hangbanId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买数量
     */
    private Integer buyNumber;


    /**
     * 出发日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date chufaTime;


    /**
     * 实付价格
     */
    private Double hangbanOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer hangbanOrderTypes;


    /**
     * 支付类型
     */
    private Integer hangbanOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getHangbanOrderUuidNumber() {
        return hangbanOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setHangbanOrderUuidNumber(String hangbanOrderUuidNumber) {
        this.hangbanOrderUuidNumber = hangbanOrderUuidNumber;
    }
    /**
	 * 获取：航班
	 */
    public Integer getHangbanId() {
        return hangbanId;
    }


    /**
	 * 设置：航班
	 */
    public void setHangbanId(Integer hangbanId) {
        this.hangbanId = hangbanId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：出发日期
	 */
    public Date getChufaTime() {
        return chufaTime;
    }


    /**
	 * 设置：出发日期
	 */
    public void setChufaTime(Date chufaTime) {
        this.chufaTime = chufaTime;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getHangbanOrderTruePrice() {
        return hangbanOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setHangbanOrderTruePrice(Double hangbanOrderTruePrice) {
        this.hangbanOrderTruePrice = hangbanOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getHangbanOrderTypes() {
        return hangbanOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setHangbanOrderTypes(Integer hangbanOrderTypes) {
        this.hangbanOrderTypes = hangbanOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getHangbanOrderPaymentTypes() {
        return hangbanOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setHangbanOrderPaymentTypes(Integer hangbanOrderPaymentTypes) {
        this.hangbanOrderPaymentTypes = hangbanOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
