package com.dao;

import com.entity.HanfuOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HanfuOrderView;

/**
 * 汉服租赁 Dao 接口
 *
 * @author 
 */
public interface HanfuOrderDao extends BaseMapper<HanfuOrderEntity> {

   List<HanfuOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
