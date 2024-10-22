package com.dao;

import com.entity.HanfuCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HanfuCollectionView;

/**
 * 汉服收藏 Dao 接口
 *
 * @author 
 */
public interface HanfuCollectionDao extends BaseMapper<HanfuCollectionEntity> {

   List<HanfuCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
