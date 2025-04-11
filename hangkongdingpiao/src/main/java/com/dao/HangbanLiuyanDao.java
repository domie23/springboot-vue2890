package com.dao;

import com.entity.HangbanLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HangbanLiuyanView;

/**
 * 航班留言 Dao 接口
 *
 * @author 
 */
public interface HangbanLiuyanDao extends BaseMapper<HangbanLiuyanEntity> {

   List<HangbanLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
