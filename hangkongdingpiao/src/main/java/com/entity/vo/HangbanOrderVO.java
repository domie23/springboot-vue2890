package com.entity.vo;

import com.entity.HangbanOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 航班订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("hangban_order")
public class HangbanOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "hangban_order_uuid_number")
    private String hangbanOrderUuidNumber;


    /**
     * 航班
     */

    @TableField(value = "hangban_id")
    private Integer hangbanId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 购买数量
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 出发日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "chufa_time")
    private Date chufaTime;


    /**
     * 实付价格
     */

    @TableField(value = "hangban_order_true_price")
    private Double hangbanOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "hangban_order_types")
    private Integer hangbanOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "hangban_order_payment_types")
    private Integer hangbanOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getHangbanOrderUuidNumber() {
        return hangbanOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setHangbanOrderUuidNumber(String hangbanOrderUuidNumber) {
        this.hangbanOrderUuidNumber = hangbanOrderUuidNumber;
    }
    /**
	 * 设置：航班
	 */
    public Integer getHangbanId() {
        return hangbanId;
    }


    /**
	 * 获取：航班
	 */

    public void setHangbanId(Integer hangbanId) {
        this.hangbanId = hangbanId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：购买数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：出发日期
	 */
    public Date getChufaTime() {
        return chufaTime;
    }


    /**
	 * 获取：出发日期
	 */

    public void setChufaTime(Date chufaTime) {
        this.chufaTime = chufaTime;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getHangbanOrderTruePrice() {
        return hangbanOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setHangbanOrderTruePrice(Double hangbanOrderTruePrice) {
        this.hangbanOrderTruePrice = hangbanOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getHangbanOrderTypes() {
        return hangbanOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setHangbanOrderTypes(Integer hangbanOrderTypes) {
        this.hangbanOrderTypes = hangbanOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getHangbanOrderPaymentTypes() {
        return hangbanOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setHangbanOrderPaymentTypes(Integer hangbanOrderPaymentTypes) {
        this.hangbanOrderPaymentTypes = hangbanOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
