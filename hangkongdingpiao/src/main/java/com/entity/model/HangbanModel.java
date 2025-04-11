package com.entity.model;

import com.entity.HangbanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 航班
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HangbanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 航班唯一编号
     */
    private String hangbanUuidNumber;


    /**
     * 航班名称
     */
    private String hangbanName;


    /**
     * 航班照片
     */
    private String hangbanPhoto;


    /**
     * 航班类型
     */
    private Integer hangbanTypes;


    /**
     * 载客量
     */
    private Integer zaikeNumber;


    /**
     * 出发时间
     */
    private String chufashijian;


    /**
     * 出发地
     */
    private String chufadiName;


    /**
     * 目的地
     */
    private String mudidiName;


    /**
     * 航班原价
     */
    private Double hangbanOldMoney;


    /**
     * 现价
     */
    private Double hangbanNewMoney;


    /**
     * 航班介绍
     */
    private String hangbanContent;


    /**
     * 点击次数
     */
    private Integer hangbanClicknum;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer hangbanDelete;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：航班唯一编号
	 */
    public String getHangbanUuidNumber() {
        return hangbanUuidNumber;
    }


    /**
	 * 设置：航班唯一编号
	 */
    public void setHangbanUuidNumber(String hangbanUuidNumber) {
        this.hangbanUuidNumber = hangbanUuidNumber;
    }
    /**
	 * 获取：航班名称
	 */
    public String getHangbanName() {
        return hangbanName;
    }


    /**
	 * 设置：航班名称
	 */
    public void setHangbanName(String hangbanName) {
        this.hangbanName = hangbanName;
    }
    /**
	 * 获取：航班照片
	 */
    public String getHangbanPhoto() {
        return hangbanPhoto;
    }


    /**
	 * 设置：航班照片
	 */
    public void setHangbanPhoto(String hangbanPhoto) {
        this.hangbanPhoto = hangbanPhoto;
    }
    /**
	 * 获取：航班类型
	 */
    public Integer getHangbanTypes() {
        return hangbanTypes;
    }


    /**
	 * 设置：航班类型
	 */
    public void setHangbanTypes(Integer hangbanTypes) {
        this.hangbanTypes = hangbanTypes;
    }
    /**
	 * 获取：载客量
	 */
    public Integer getZaikeNumber() {
        return zaikeNumber;
    }


    /**
	 * 设置：载客量
	 */
    public void setZaikeNumber(Integer zaikeNumber) {
        this.zaikeNumber = zaikeNumber;
    }
    /**
	 * 获取：出发时间
	 */
    public String getChufashijian() {
        return chufashijian;
    }


    /**
	 * 设置：出发时间
	 */
    public void setChufashijian(String chufashijian) {
        this.chufashijian = chufashijian;
    }
    /**
	 * 获取：出发地
	 */
    public String getChufadiName() {
        return chufadiName;
    }


    /**
	 * 设置：出发地
	 */
    public void setChufadiName(String chufadiName) {
        this.chufadiName = chufadiName;
    }
    /**
	 * 获取：目的地
	 */
    public String getMudidiName() {
        return mudidiName;
    }


    /**
	 * 设置：目的地
	 */
    public void setMudidiName(String mudidiName) {
        this.mudidiName = mudidiName;
    }
    /**
	 * 获取：航班原价
	 */
    public Double getHangbanOldMoney() {
        return hangbanOldMoney;
    }


    /**
	 * 设置：航班原价
	 */
    public void setHangbanOldMoney(Double hangbanOldMoney) {
        this.hangbanOldMoney = hangbanOldMoney;
    }
    /**
	 * 获取：现价
	 */
    public Double getHangbanNewMoney() {
        return hangbanNewMoney;
    }


    /**
	 * 设置：现价
	 */
    public void setHangbanNewMoney(Double hangbanNewMoney) {
        this.hangbanNewMoney = hangbanNewMoney;
    }
    /**
	 * 获取：航班介绍
	 */
    public String getHangbanContent() {
        return hangbanContent;
    }


    /**
	 * 设置：航班介绍
	 */
    public void setHangbanContent(String hangbanContent) {
        this.hangbanContent = hangbanContent;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getHangbanClicknum() {
        return hangbanClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setHangbanClicknum(Integer hangbanClicknum) {
        this.hangbanClicknum = hangbanClicknum;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getHangbanDelete() {
        return hangbanDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setHangbanDelete(Integer hangbanDelete) {
        this.hangbanDelete = hangbanDelete;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
