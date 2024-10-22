package com.dao;

import com.entity.HanfuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HanfuView;

/**
 * 汉服信息 Dao 接口
 *
 * @author 
 */
public interface HanfuDao extends BaseMapper<HanfuEntity> {

   List<HanfuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
