package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 汉服信息
 *
 * @author 
 * @email
 */
@TableName("hanfu")
public class HanfuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public HanfuEntity() {

	}

	public HanfuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 汉服名称
     */
    @ColumnInfo(comment="汉服名称",type="varchar(200)")
    @TableField(value = "hanfu_name")

    private String hanfuName;


    /**
     * 汉服编号
     */
    @ColumnInfo(comment="汉服编号",type="varchar(200)")
    @TableField(value = "hanfu_uuid_number")

    private String hanfuUuidNumber;


    /**
     * 汉服照片
     */
    @ColumnInfo(comment="汉服照片",type="varchar(200)")
    @TableField(value = "hanfu_photo")

    private String hanfuPhoto;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 汉服类型
     */
    @ColumnInfo(comment="汉服类型",type="int(11)")
    @TableField(value = "hanfu_types")

    private Integer hanfuTypes;


    /**
     * 汉服押金
     */
    @ColumnInfo(comment="汉服押金",type="decimal(10,2)")
    @TableField(value = "hanfu_old_money")

    private Double hanfuOldMoney;


    /**
     * 费用
     */
    @ColumnInfo(comment="费用",type="decimal(10,2)")
    @TableField(value = "hanfu_new_money")

    private Double hanfuNewMoney;


    /**
     * 汉服视频
     */
    @ColumnInfo(comment="汉服视频",type="varchar(200)")
    @TableField(value = "hanfu_video")

    private String hanfuVideo;


    /**
     * 汉服热度
     */
    @ColumnInfo(comment="汉服热度",type="int(11)")
    @TableField(value = "hanfu_clicknum")

    private Integer hanfuClicknum;


    /**
     * 汉服介绍
     */
    @ColumnInfo(comment="汉服介绍",type="longtext")
    @TableField(value = "hanfu_content")

    private String hanfuContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "hanfu_delete")

    private Integer hanfuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：汉服名称
	 */
    public String getHanfuName() {
        return hanfuName;
    }
    /**
	 * 设置：汉服名称
	 */

    public void setHanfuName(String hanfuName) {
        this.hanfuName = hanfuName;
    }
    /**
	 * 获取：汉服编号
	 */
    public String getHanfuUuidNumber() {
        return hanfuUuidNumber;
    }
    /**
	 * 设置：汉服编号
	 */

    public void setHanfuUuidNumber(String hanfuUuidNumber) {
        this.hanfuUuidNumber = hanfuUuidNumber;
    }
    /**
	 * 获取：汉服照片
	 */
    public String getHanfuPhoto() {
        return hanfuPhoto;
    }
    /**
	 * 设置：汉服照片
	 */

    public void setHanfuPhoto(String hanfuPhoto) {
        this.hanfuPhoto = hanfuPhoto;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：汉服类型
	 */
    public Integer getHanfuTypes() {
        return hanfuTypes;
    }
    /**
	 * 设置：汉服类型
	 */

    public void setHanfuTypes(Integer hanfuTypes) {
        this.hanfuTypes = hanfuTypes;
    }
    /**
	 * 获取：汉服押金
	 */
    public Double getHanfuOldMoney() {
        return hanfuOldMoney;
    }
    /**
	 * 设置：汉服押金
	 */

    public void setHanfuOldMoney(Double hanfuOldMoney) {
        this.hanfuOldMoney = hanfuOldMoney;
    }
    /**
	 * 获取：费用
	 */
    public Double getHanfuNewMoney() {
        return hanfuNewMoney;
    }
    /**
	 * 设置：费用
	 */

    public void setHanfuNewMoney(Double hanfuNewMoney) {
        this.hanfuNewMoney = hanfuNewMoney;
    }
    /**
	 * 获取：汉服视频
	 */
    public String getHanfuVideo() {
        return hanfuVideo;
    }
    /**
	 * 设置：汉服视频
	 */

    public void setHanfuVideo(String hanfuVideo) {
        this.hanfuVideo = hanfuVideo;
    }
    /**
	 * 获取：汉服热度
	 */
    public Integer getHanfuClicknum() {
        return hanfuClicknum;
    }
    /**
	 * 设置：汉服热度
	 */

    public void setHanfuClicknum(Integer hanfuClicknum) {
        this.hanfuClicknum = hanfuClicknum;
    }
    /**
	 * 获取：汉服介绍
	 */
    public String getHanfuContent() {
        return hanfuContent;
    }
    /**
	 * 设置：汉服介绍
	 */

    public void setHanfuContent(String hanfuContent) {
        this.hanfuContent = hanfuContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getHanfuDelete() {
        return hanfuDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setHanfuDelete(Integer hanfuDelete) {
        this.hanfuDelete = hanfuDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Hanfu{" +
            ", id=" + id +
            ", hanfuName=" + hanfuName +
            ", hanfuUuidNumber=" + hanfuUuidNumber +
            ", hanfuPhoto=" + hanfuPhoto +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", hanfuTypes=" + hanfuTypes +
            ", hanfuOldMoney=" + hanfuOldMoney +
            ", hanfuNewMoney=" + hanfuNewMoney +
            ", hanfuVideo=" + hanfuVideo +
            ", hanfuClicknum=" + hanfuClicknum +
            ", hanfuContent=" + hanfuContent +
            ", hanfuDelete=" + hanfuDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
