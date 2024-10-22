package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.HanfuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 汉服信息
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("hanfu")
public class HanfuView extends HanfuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 汉服类型的值
	*/
	@ColumnInfo(comment="汉服类型的字典表值",type="varchar(200)")
	private String hanfuValue;




	public HanfuView() {

	}

	public HanfuView(HanfuEntity hanfuEntity) {
		try {
			BeanUtils.copyProperties(this, hanfuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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




	@Override
	public String toString() {
		return "HanfuView{" +
			", hanfuValue=" + hanfuValue +
			"} " + super.toString();
	}
}
