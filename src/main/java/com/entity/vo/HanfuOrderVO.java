package com.entity.vo;

import com.entity.HanfuOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 汉服租赁
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("hanfu_order")
public class HanfuOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单编号
     */

    @TableField(value = "hanfu_order_uuid_number")
    private String hanfuOrderUuidNumber;


    /**
     * 汉服
     */

    @TableField(value = "hanfu_id")
    private Integer hanfuId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 实付价格
     */

    @TableField(value = "hanfu_order_true_price")
    private Double hanfuOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "hanfu_order_types")
    private Integer hanfuOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：订单编号
	 */
    public String getHanfuOrderUuidNumber() {
        return hanfuOrderUuidNumber;
    }


    /**
	 * 获取：订单编号
	 */

    public void setHanfuOrderUuidNumber(String hanfuOrderUuidNumber) {
        this.hanfuOrderUuidNumber = hanfuOrderUuidNumber;
    }
    /**
	 * 设置：汉服
	 */
    public Integer getHanfuId() {
        return hanfuId;
    }


    /**
	 * 获取：汉服
	 */

    public void setHanfuId(Integer hanfuId) {
        this.hanfuId = hanfuId;
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
	 * 设置：实付价格
	 */
    public Double getHanfuOrderTruePrice() {
        return hanfuOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setHanfuOrderTruePrice(Double hanfuOrderTruePrice) {
        this.hanfuOrderTruePrice = hanfuOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getHanfuOrderTypes() {
        return hanfuOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setHanfuOrderTypes(Integer hanfuOrderTypes) {
        this.hanfuOrderTypes = hanfuOrderTypes;
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
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
