package com.entity.view;

import com.entity.HangbanLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 航班留言
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("hangban_liuyan")
public class HangbanLiuyanView extends HangbanLiuyanEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 hangban
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
				* 航班类型的值
				*/
				private String hangbanValue;
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
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer hangbanDelete;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 常去城市
			*/
			private String changquchenggshiName;
			/**
			* 常选最低价格
			*/
			private Double zuidijieshou;
			/**
			* 常选最高价格
			*/
			private Double zuigaojiage;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;
			/**
			* 总积分
			*/
			private Double yonghuSumJifen;
			/**
			* 现积分
			*/
			private Double yonghuNewJifen;
			/**
			* 会员等级
			*/
			private Integer huiyuandengjiTypes;
				/**
				* 会员等级的值
				*/
				private String huiyuandengjiValue;

	public HangbanLiuyanView() {

	}

	public HangbanLiuyanView(HangbanLiuyanEntity hangbanLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, hangbanLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}













				//级联表的get和set hangban
					/**
					* 获取： 航班唯一编号
					*/
					public String getHangbanUuidNumber() {
						return hangbanUuidNumber;
					}
					/**
					* 设置： 航班唯一编号
					*/
					public void setHangbanUuidNumber(String hangbanUuidNumber) {
						this.hangbanUuidNumber = hangbanUuidNumber;
					}
					/**
					* 获取： 航班名称
					*/
					public String getHangbanName() {
						return hangbanName;
					}
					/**
					* 设置： 航班名称
					*/
					public void setHangbanName(String hangbanName) {
						this.hangbanName = hangbanName;
					}
					/**
					* 获取： 航班照片
					*/
					public String getHangbanPhoto() {
						return hangbanPhoto;
					}
					/**
					* 设置： 航班照片
					*/
					public void setHangbanPhoto(String hangbanPhoto) {
						this.hangbanPhoto = hangbanPhoto;
					}
					/**
					* 获取： 航班类型
					*/
					public Integer getHangbanTypes() {
						return hangbanTypes;
					}
					/**
					* 设置： 航班类型
					*/
					public void setHangbanTypes(Integer hangbanTypes) {
						this.hangbanTypes = hangbanTypes;
					}


						/**
						* 获取： 航班类型的值
						*/
						public String getHangbanValue() {
							return hangbanValue;
						}
						/**
						* 设置： 航班类型的值
						*/
						public void setHangbanValue(String hangbanValue) {
							this.hangbanValue = hangbanValue;
						}
					/**
					* 获取： 载客量
					*/
					public Integer getZaikeNumber() {
						return zaikeNumber;
					}
					/**
					* 设置： 载客量
					*/
					public void setZaikeNumber(Integer zaikeNumber) {
						this.zaikeNumber = zaikeNumber;
					}
					/**
					* 获取： 出发时间
					*/
					public String getChufashijian() {
						return chufashijian;
					}
					/**
					* 设置： 出发时间
					*/
					public void setChufashijian(String chufashijian) {
						this.chufashijian = chufashijian;
					}
					/**
					* 获取： 出发地
					*/
					public String getChufadiName() {
						return chufadiName;
					}
					/**
					* 设置： 出发地
					*/
					public void setChufadiName(String chufadiName) {
						this.chufadiName = chufadiName;
					}
					/**
					* 获取： 目的地
					*/
					public String getMudidiName() {
						return mudidiName;
					}
					/**
					* 设置： 目的地
					*/
					public void setMudidiName(String mudidiName) {
						this.mudidiName = mudidiName;
					}
					/**
					* 获取： 航班原价
					*/
					public Double getHangbanOldMoney() {
						return hangbanOldMoney;
					}
					/**
					* 设置： 航班原价
					*/
					public void setHangbanOldMoney(Double hangbanOldMoney) {
						this.hangbanOldMoney = hangbanOldMoney;
					}
					/**
					* 获取： 现价
					*/
					public Double getHangbanNewMoney() {
						return hangbanNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setHangbanNewMoney(Double hangbanNewMoney) {
						this.hangbanNewMoney = hangbanNewMoney;
					}
					/**
					* 获取： 航班介绍
					*/
					public String getHangbanContent() {
						return hangbanContent;
					}
					/**
					* 设置： 航班介绍
					*/
					public void setHangbanContent(String hangbanContent) {
						this.hangbanContent = hangbanContent;
					}
					/**
					* 获取： 点击次数
					*/
					public Integer getHangbanClicknum() {
						return hangbanClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setHangbanClicknum(Integer hangbanClicknum) {
						this.hangbanClicknum = hangbanClicknum;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getHangbanDelete() {
						return hangbanDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setHangbanDelete(Integer hangbanDelete) {
						this.hangbanDelete = hangbanDelete;
					}













				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 常去城市
					*/
					public String getChangquchenggshiName() {
						return changquchenggshiName;
					}
					/**
					* 设置： 常去城市
					*/
					public void setChangquchenggshiName(String changquchenggshiName) {
						this.changquchenggshiName = changquchenggshiName;
					}
					/**
					* 获取： 常选最低价格
					*/
					public Double getZuidijieshou() {
						return zuidijieshou;
					}
					/**
					* 设置： 常选最低价格
					*/
					public void setZuidijieshou(Double zuidijieshou) {
						this.zuidijieshou = zuidijieshou;
					}
					/**
					* 获取： 常选最高价格
					*/
					public Double getZuigaojiage() {
						return zuigaojiage;
					}
					/**
					* 设置： 常选最高价格
					*/
					public void setZuigaojiage(Double zuigaojiage) {
						this.zuigaojiage = zuigaojiage;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}
					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}
					/**
					* 获取： 总积分
					*/
					public Double getYonghuSumJifen() {
						return yonghuSumJifen;
					}
					/**
					* 设置： 总积分
					*/
					public void setYonghuSumJifen(Double yonghuSumJifen) {
						this.yonghuSumJifen = yonghuSumJifen;
					}
					/**
					* 获取： 现积分
					*/
					public Double getYonghuNewJifen() {
						return yonghuNewJifen;
					}
					/**
					* 设置： 现积分
					*/
					public void setYonghuNewJifen(Double yonghuNewJifen) {
						this.yonghuNewJifen = yonghuNewJifen;
					}
					/**
					* 获取： 会员等级
					*/
					public Integer getHuiyuandengjiTypes() {
						return huiyuandengjiTypes;
					}
					/**
					* 设置： 会员等级
					*/
					public void setHuiyuandengjiTypes(Integer huiyuandengjiTypes) {
						this.huiyuandengjiTypes = huiyuandengjiTypes;
					}


						/**
						* 获取： 会员等级的值
						*/
						public String getHuiyuandengjiValue() {
							return huiyuandengjiValue;
						}
						/**
						* 设置： 会员等级的值
						*/
						public void setHuiyuandengjiValue(String huiyuandengjiValue) {
							this.huiyuandengjiValue = huiyuandengjiValue;
						}



}
