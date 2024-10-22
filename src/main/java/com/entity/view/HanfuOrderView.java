package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.HanfuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 汉服租赁
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("hanfu_order")
public class HanfuOrderView extends HanfuOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 订单类型的值
	*/
	@ColumnInfo(comment="订单类型的字典表值",type="varchar(200)")
	private String hanfuOrderValue;

	//级联表 汉服信息
		/**
		* 汉服名称
		*/

		@ColumnInfo(comment="汉服名称",type="varchar(200)")
		private String hanfuName;
		/**
		* 汉服编号
		*/

		@ColumnInfo(comment="汉服编号",type="varchar(200)")
		private String hanfuUuidNumber;
		/**
		* 汉服照片
		*/

		@ColumnInfo(comment="汉服照片",type="varchar(200)")
		private String hanfuPhoto;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 汉服类型
		*/
		@ColumnInfo(comment="汉服类型",type="int(11)")
		private Integer hanfuTypes;
			/**
			* 汉服类型的值
			*/
			@ColumnInfo(comment="汉服类型的字典表值",type="varchar(200)")
			private String hanfuValue;
		/**
		* 汉服押金
		*/
		@ColumnInfo(comment="汉服押金",type="decimal(10,2)")
		private Double hanfuOldMoney;
		/**
		* 费用
		*/
		@ColumnInfo(comment="费用",type="decimal(10,2)")
		private Double hanfuNewMoney;
		/**
		* 汉服视频
		*/

		@ColumnInfo(comment="汉服视频",type="varchar(200)")
		private String hanfuVideo;
		/**
		* 汉服热度
		*/

		@ColumnInfo(comment="汉服热度",type="int(11)")
		private Integer hanfuClicknum;
		/**
		* 汉服介绍
		*/

		@ColumnInfo(comment="汉服介绍",type="longtext")
		private String hanfuContent;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer hanfuDelete;
	//级联表 用户
		/**
		* 用户编号
		*/

		@ColumnInfo(comment="用户编号",type="varchar(200)")
		private String yonghuUuidNumber;
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户身份证号
		*/

		@ColumnInfo(comment="用户身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 用户头像
		*/

		@ColumnInfo(comment="用户头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 余额
		*/
		@ColumnInfo(comment="余额",type="decimal(10,2)")
		private Double newMoney;
		/**
		* 用户邮箱
		*/

		@ColumnInfo(comment="用户邮箱",type="varchar(200)")
		private String yonghuEmail;



	public HanfuOrderView() {

	}

	public HanfuOrderView(HanfuOrderEntity hanfuOrderEntity) {
		try {
			BeanUtils.copyProperties(this, hanfuOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 订单类型的值
	*/
	public String getHanfuOrderValue() {
		return hanfuOrderValue;
	}
	/**
	* 设置： 订单类型的值
	*/
	public void setHanfuOrderValue(String hanfuOrderValue) {
		this.hanfuOrderValue = hanfuOrderValue;
	}


	//级联表的get和set 汉服信息

		/**
		* 获取： 汉服名称
		*/
		public String getHanfuName() {
			return hanfuName;
		}
		/**
		* 设置： 汉服名称
		*/
		public void setHanfuName(String hanfuName) {
			this.hanfuName = hanfuName;
		}

		/**
		* 获取： 汉服编号
		*/
		public String getHanfuUuidNumber() {
			return hanfuUuidNumber;
		}
		/**
		* 设置： 汉服编号
		*/
		public void setHanfuUuidNumber(String hanfuUuidNumber) {
			this.hanfuUuidNumber = hanfuUuidNumber;
		}

		/**
		* 获取： 汉服照片
		*/
		public String getHanfuPhoto() {
			return hanfuPhoto;
		}
		/**
		* 设置： 汉服照片
		*/
		public void setHanfuPhoto(String hanfuPhoto) {
			this.hanfuPhoto = hanfuPhoto;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}
		/**
		* 获取： 汉服类型
		*/
		public Integer getHanfuTypes() {
			return hanfuTypes;
		}
		/**
		* 设置： 汉服类型
		*/
		public void setHanfuTypes(Integer hanfuTypes) {
			this.hanfuTypes = hanfuTypes;
		}


			/**
			* 获取： 汉服类型的值
			*/
			public String getHanfuValue() {
				return hanfuValue;
			}
			/**
			* 设置： 汉服类型的值
			*/
			public void setHanfuValue(String hanfuValue) {
				this.hanfuValue = hanfuValue;
			}

		/**
		* 获取： 汉服押金
		*/
		public Double getHanfuOldMoney() {
			return hanfuOldMoney;
		}
		/**
		* 设置： 汉服押金
		*/
		public void setHanfuOldMoney(Double hanfuOldMoney) {
			this.hanfuOldMoney = hanfuOldMoney;
		}

		/**
		* 获取： 费用
		*/
		public Double getHanfuNewMoney() {
			return hanfuNewMoney;
		}
		/**
		* 设置： 费用
		*/
		public void setHanfuNewMoney(Double hanfuNewMoney) {
			this.hanfuNewMoney = hanfuNewMoney;
		}

		/**
		* 获取： 汉服视频
		*/
		public String getHanfuVideo() {
			return hanfuVideo;
		}
		/**
		* 设置： 汉服视频
		*/
		public void setHanfuVideo(String hanfuVideo) {
			this.hanfuVideo = hanfuVideo;
		}

		/**
		* 获取： 汉服热度
		*/
		public Integer getHanfuClicknum() {
			return hanfuClicknum;
		}
		/**
		* 设置： 汉服热度
		*/
		public void setHanfuClicknum(Integer hanfuClicknum) {
			this.hanfuClicknum = hanfuClicknum;
		}

		/**
		* 获取： 汉服介绍
		*/
		public String getHanfuContent() {
			return hanfuContent;
		}
		/**
		* 设置： 汉服介绍
		*/
		public void setHanfuContent(String hanfuContent) {
			this.hanfuContent = hanfuContent;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getHanfuDelete() {
			return hanfuDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setHanfuDelete(Integer hanfuDelete) {
			this.hanfuDelete = hanfuDelete;
		}
	//级联表的get和set 用户

		/**
		* 获取： 用户编号
		*/
		public String getYonghuUuidNumber() {
			return yonghuUuidNumber;
		}
		/**
		* 设置： 用户编号
		*/
		public void setYonghuUuidNumber(String yonghuUuidNumber) {
			this.yonghuUuidNumber = yonghuUuidNumber;
		}

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
		* 获取： 用户邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 用户邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}


	@Override
	public String toString() {
		return "HanfuOrderView{" +
			", hanfuOrderValue=" + hanfuOrderValue +
			", hanfuName=" + hanfuName +
			", hanfuUuidNumber=" + hanfuUuidNumber +
			", hanfuPhoto=" + hanfuPhoto +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", hanfuOldMoney=" + hanfuOldMoney +
			", hanfuNewMoney=" + hanfuNewMoney +
			", hanfuVideo=" + hanfuVideo +
			", hanfuClicknum=" + hanfuClicknum +
			", hanfuContent=" + hanfuContent +
			", hanfuDelete=" + hanfuDelete +
			", yonghuUuidNumber=" + yonghuUuidNumber +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", newMoney=" + newMoney +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
