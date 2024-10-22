package com.entity.model;

import com.entity.HanfuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 汉服信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class HanfuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 汉服名称
     */
    private String hanfuName;


    /**
     * 汉服编号
     */
    private String hanfuUuidNumber;


    /**
     * 汉服照片
     */
    private String hanfuPhoto;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 汉服类型
     */
    private Integer hanfuTypes;


    /**
     * 汉服押金
     */
    private Double hanfuOldMoney;


    /**
     * 费用
     */
    private Double hanfuNewMoney;


    /**
     * 汉服视频
     */
    private String hanfuVideo;


    /**
     * 汉服热度
     */
    private Integer hanfuClicknum;


    /**
     * 汉服介绍
     */
    private String hanfuContent;


    /**
     * 逻辑删除
     */
    private Integer hanfuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


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
