package com.entity.vo;

import com.entity.HangbanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 航班
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("hangban")
public class HangbanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 航班唯一编号
     */

    @TableField(value = "hangban_uuid_number")
    private String hangbanUuidNumber;


    /**
     * 航班名称
     */

    @TableField(value = "hangban_name")
    private String hangbanName;


    /**
     * 航班照片
     */

    @TableField(value = "hangban_photo")
    private String hangbanPhoto;


    /**
     * 航班类型
     */

    @TableField(value = "hangban_types")
    private Integer hangbanTypes;


    /**
     * 载客量
     */

    @TableField(value = "zaike_number")
    private Integer zaikeNumber;


    /**
     * 出发时间
     */

    @TableField(value = "chufashijian")
    private String chufashijian;


    /**
     * 出发地
     */

    @TableField(value = "chufadi_name")
    private String chufadiName;


    /**
     * 目的地
     */

    @TableField(value = "mudidi_name")
    private String mudidiName;


    /**
     * 航班原价
     */

    @TableField(value = "hangban_old_money")
    private Double hangbanOldMoney;


    /**
     * 现价
     */

    @TableField(value = "hangban_new_money")
    private Double hangbanNewMoney;


    /**
     * 航班介绍
     */

    @TableField(value = "hangban_content")
    private String hangbanContent;


    /**
     * 点击次数
     */

    @TableField(value = "hangban_clicknum")
    private Integer hangbanClicknum;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "hangban_delete")
    private Integer hangbanDelete;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：航班唯一编号
	 */
    public String getHangbanUuidNumber() {
        return hangbanUuidNumber;
    }


    /**
	 * 获取：航班唯一编号
	 */

    public void setHangbanUuidNumber(String hangbanUuidNumber) {
        this.hangbanUuidNumber = hangbanUuidNumber;
    }
    /**
	 * 设置：航班名称
	 */
    public String getHangbanName() {
        return hangbanName;
    }


    /**
	 * 获取：航班名称
	 */

    public void setHangbanName(String hangbanName) {
        this.hangbanName = hangbanName;
    }
    /**
	 * 设置：航班照片
	 */
    public String getHangbanPhoto() {
        return hangbanPhoto;
    }


    /**
	 * 获取：航班照片
	 */

    public void setHangbanPhoto(String hangbanPhoto) {
        this.hangbanPhoto = hangbanPhoto;
    }
    /**
	 * 设置：航班类型
	 */
    public Integer getHangbanTypes() {
        return hangbanTypes;
    }


    /**
	 * 获取：航班类型
	 */

    public void setHangbanTypes(Integer hangbanTypes) {
        this.hangbanTypes = hangbanTypes;
    }
    /**
	 * 设置：载客量
	 */
    public Integer getZaikeNumber() {
        return zaikeNumber;
    }


    /**
	 * 获取：载客量
	 */

    public void setZaikeNumber(Integer zaikeNumber) {
        this.zaikeNumber = zaikeNumber;
    }
    /**
	 * 设置：出发时间
	 */
    public String getChufashijian() {
        return chufashijian;
    }


    /**
	 * 获取：出发时间
	 */

    public void setChufashijian(String chufashijian) {
        this.chufashijian = chufashijian;
    }
    /**
	 * 设置：出发地
	 */
    public String getChufadiName() {
        return chufadiName;
    }


    /**
	 * 获取：出发地
	 */

    public void setChufadiName(String chufadiName) {
        this.chufadiName = chufadiName;
    }
    /**
	 * 设置：目的地
	 */
    public String getMudidiName() {
        return mudidiName;
    }


    /**
	 * 获取：目的地
	 */

    public void setMudidiName(String mudidiName) {
        this.mudidiName = mudidiName;
    }
    /**
	 * 设置：航班原价
	 */
    public Double getHangbanOldMoney() {
        return hangbanOldMoney;
    }


    /**
	 * 获取：航班原价
	 */

    public void setHangbanOldMoney(Double hangbanOldMoney) {
        this.hangbanOldMoney = hangbanOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getHangbanNewMoney() {
        return hangbanNewMoney;
    }


    /**
	 * 获取：现价
	 */

    public void setHangbanNewMoney(Double hangbanNewMoney) {
        this.hangbanNewMoney = hangbanNewMoney;
    }
    /**
	 * 设置：航班介绍
	 */
    public String getHangbanContent() {
        return hangbanContent;
    }


    /**
	 * 获取：航班介绍
	 */

    public void setHangbanContent(String hangbanContent) {
        this.hangbanContent = hangbanContent;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getHangbanClicknum() {
        return hangbanClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setHangbanClicknum(Integer hangbanClicknum) {
        this.hangbanClicknum = hangbanClicknum;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getHangbanDelete() {
        return hangbanDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setHangbanDelete(Integer hangbanDelete) {
        this.hangbanDelete = hangbanDelete;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
