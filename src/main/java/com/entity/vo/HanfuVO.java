package com.entity.vo;

import com.entity.HanfuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 汉服信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("hanfu")
public class HanfuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 汉服名称
     */

    @TableField(value = "hanfu_name")
    private String hanfuName;


    /**
     * 汉服编号
     */

    @TableField(value = "hanfu_uuid_number")
    private String hanfuUuidNumber;


    /**
     * 汉服照片
     */

    @TableField(value = "hanfu_photo")
    private String hanfuPhoto;


    /**
     * 赞
     */

    @TableField(value = "zan_number")
    private Integer zanNumber;


    /**
     * 踩
     */

    @TableField(value = "cai_number")
    private Integer caiNumber;


    /**
     * 汉服类型
     */

    @TableField(value = "hanfu_types")
    private Integer hanfuTypes;


    /**
     * 汉服押金
     */

    @TableField(value = "hanfu_old_money")
    private Double hanfuOldMoney;


    /**
     * 费用
     */

    @TableField(value = "hanfu_new_money")
    private Double hanfuNewMoney;


    /**
     * 汉服视频
     */

    @TableField(value = "hanfu_video")
    private String hanfuVideo;


    /**
     * 汉服热度
     */

    @TableField(value = "hanfu_clicknum")
    private Integer hanfuClicknum;


    /**
     * 汉服介绍
     */

    @TableField(value = "hanfu_content")
    private String hanfuContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "hanfu_delete")
    private Integer hanfuDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


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
	 * 设置：汉服名称
	 */
    public String getHanfuName() {
        return hanfuName;
    }


    /**
	 * 获取：汉服名称
	 */

    public void setHanfuName(String hanfuName) {
        this.hanfuName = hanfuName;
    }
    /**
	 * 设置：汉服编号
	 */
    public String getHanfuUuidNumber() {
        return hanfuUuidNumber;
    }


    /**
	 * 获取：汉服编号
	 */

    public void setHanfuUuidNumber(String hanfuUuidNumber) {
        this.hanfuUuidNumber = hanfuUuidNumber;
    }
    /**
	 * 设置：汉服照片
	 */
    public String getHanfuPhoto() {
        return hanfuPhoto;
    }


    /**
	 * 获取：汉服照片
	 */

    public void setHanfuPhoto(String hanfuPhoto) {
        this.hanfuPhoto = hanfuPhoto;
    }
    /**
	 * 设置：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 获取：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 设置：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 获取：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 设置：汉服类型
	 */
    public Integer getHanfuTypes() {
        return hanfuTypes;
    }


    /**
	 * 获取：汉服类型
	 */

    public void setHanfuTypes(Integer hanfuTypes) {
        this.hanfuTypes = hanfuTypes;
    }
    /**
	 * 设置：汉服押金
	 */
    public Double getHanfuOldMoney() {
        return hanfuOldMoney;
    }


    /**
	 * 获取：汉服押金
	 */

    public void setHanfuOldMoney(Double hanfuOldMoney) {
        this.hanfuOldMoney = hanfuOldMoney;
    }
    /**
	 * 设置：费用
	 */
    public Double getHanfuNewMoney() {
        return hanfuNewMoney;
    }


    /**
	 * 获取：费用
	 */

    public void setHanfuNewMoney(Double hanfuNewMoney) {
        this.hanfuNewMoney = hanfuNewMoney;
    }
    /**
	 * 设置：汉服视频
	 */
    public String getHanfuVideo() {
        return hanfuVideo;
    }


    /**
	 * 获取：汉服视频
	 */

    public void setHanfuVideo(String hanfuVideo) {
        this.hanfuVideo = hanfuVideo;
    }
    /**
	 * 设置：汉服热度
	 */
    public Integer getHanfuClicknum() {
        return hanfuClicknum;
    }


    /**
	 * 获取：汉服热度
	 */

    public void setHanfuClicknum(Integer hanfuClicknum) {
        this.hanfuClicknum = hanfuClicknum;
    }
    /**
	 * 设置：汉服介绍
	 */
    public String getHanfuContent() {
        return hanfuContent;
    }


    /**
	 * 获取：汉服介绍
	 */

    public void setHanfuContent(String hanfuContent) {
        this.hanfuContent = hanfuContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getHanfuDelete() {
        return hanfuDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setHanfuDelete(Integer hanfuDelete) {
        this.hanfuDelete = hanfuDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
