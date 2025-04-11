package com.dao;

import com.entity.HangbanOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.HangbanOrderView;

/**
 * 航班订单 Dao 接口
 *
 * @author 
 */
public interface HangbanOrderDao extends BaseMapper<HangbanOrderEntity> {

   List<HangbanOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
