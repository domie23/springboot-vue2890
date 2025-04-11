
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 航班
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/hangban")
public class HangbanController {
    private static final Logger logger = LoggerFactory.getLogger(HangbanController.class);

    @Autowired
    private HangbanService hangbanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("hangbanDeleteStart",1);params.put("hangbanDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = hangbanService.queryPage(params);

        //字典表数据转换
        List<HangbanView> list =(List<HangbanView>)page.getList();
        for(HangbanView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HangbanEntity hangban = hangbanService.selectById(id);
        if(hangban !=null){
            //entity转view
            HangbanView view = new HangbanView();
            BeanUtils.copyProperties( hangban , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody HangbanEntity hangban, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,hangban:{}",this.getClass().getName(),hangban.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");

        Wrapper<HangbanEntity> queryWrapper = new EntityWrapper<HangbanEntity>()
            .eq("hangban_uuid_number", hangban.getHangbanUuidNumber())
            .eq("hangban_name", hangban.getHangbanName())
            .eq("hangban_types", hangban.getHangbanTypes())
            .eq("zaike_number", hangban.getZaikeNumber())
            .eq("chufashijian", hangban.getChufashijian())
            .eq("chufadi_name", hangban.getChufadiName())
            .eq("mudidi_name", hangban.getMudidiName())
            .eq("hangban_clicknum", hangban.getHangbanClicknum())
            .eq("shangxia_types", hangban.getShangxiaTypes())
            .eq("hangban_delete", hangban.getHangbanDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HangbanEntity hangbanEntity = hangbanService.selectOne(queryWrapper);
        if(hangbanEntity==null){
            hangban.setHangbanClicknum(1);
            hangban.setShangxiaTypes(1);
            hangban.setHangbanDelete(1);
            hangban.setCreateTime(new Date());
            hangbanService.insert(hangban);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HangbanEntity hangban, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,hangban:{}",this.getClass().getName(),hangban.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<HangbanEntity> queryWrapper = new EntityWrapper<HangbanEntity>()
            .notIn("id",hangban.getId())
            .andNew()
            .eq("hangban_uuid_number", hangban.getHangbanUuidNumber())
            .eq("hangban_name", hangban.getHangbanName())
            .eq("hangban_types", hangban.getHangbanTypes())
            .eq("zaike_number", hangban.getZaikeNumber())
            .eq("chufashijian", hangban.getChufashijian())
            .eq("chufadi_name", hangban.getChufadiName())
            .eq("mudidi_name", hangban.getMudidiName())
            .eq("hangban_clicknum", hangban.getHangbanClicknum())
            .eq("shangxia_types", hangban.getShangxiaTypes())
            .eq("hangban_delete", hangban.getHangbanDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HangbanEntity hangbanEntity = hangbanService.selectOne(queryWrapper);
        if("".equals(hangban.getHangbanPhoto()) || "null".equals(hangban.getHangbanPhoto())){
                hangban.setHangbanPhoto(null);
        }
        if(hangbanEntity==null){
            hangbanService.updateById(hangban);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<HangbanEntity> list = new ArrayList<>();
        for(Integer id:ids){
            HangbanEntity hangbanEntity = new HangbanEntity();
            hangbanEntity.setId(id);
            hangbanEntity.setHangbanDelete(2);
            list.add(hangbanEntity);
        }
        if(list != null && list.size() >0){
            hangbanService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<HangbanEntity> hangbanList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            HangbanEntity hangbanEntity = new HangbanEntity();
//                            hangbanEntity.setHangbanUuidNumber(data.get(0));                    //航班唯一编号 要改的
//                            hangbanEntity.setHangbanName(data.get(0));                    //航班名称 要改的
//                            hangbanEntity.setHangbanPhoto("");//照片
//                            hangbanEntity.setHangbanTypes(Integer.valueOf(data.get(0)));   //航班类型 要改的
//                            hangbanEntity.setZaikeNumber(Integer.valueOf(data.get(0)));   //载客量 要改的
//                            hangbanEntity.setChufashijian(data.get(0));                    //出发时间 要改的
//                            hangbanEntity.setChufadiName(data.get(0));                    //出发地 要改的
//                            hangbanEntity.setMudidiName(data.get(0));                    //目的地 要改的
//                            hangbanEntity.setHangbanOldMoney(data.get(0));                    //航班原价 要改的
//                            hangbanEntity.setHangbanNewMoney(data.get(0));                    //现价 要改的
//                            hangbanEntity.setHangbanContent("");//照片
//                            hangbanEntity.setHangbanClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            hangbanEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            hangbanEntity.setHangbanDelete(1);//逻辑删除字段
//                            hangbanEntity.setCreateTime(date);//时间
                            hangbanList.add(hangbanEntity);


                            //把要查询是否重复的字段放入map中
                                //航班唯一编号
                                if(seachFields.containsKey("hangbanUuidNumber")){
                                    List<String> hangbanUuidNumber = seachFields.get("hangbanUuidNumber");
                                    hangbanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> hangbanUuidNumber = new ArrayList<>();
                                    hangbanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("hangbanUuidNumber",hangbanUuidNumber);
                                }
                        }

                        //查询是否重复
                         //航班唯一编号
                        List<HangbanEntity> hangbanEntities_hangbanUuidNumber = hangbanService.selectList(new EntityWrapper<HangbanEntity>().in("hangban_uuid_number", seachFields.get("hangbanUuidNumber")).eq("hangban_delete", 1));
                        if(hangbanEntities_hangbanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HangbanEntity s:hangbanEntities_hangbanUuidNumber){
                                repeatFields.add(s.getHangbanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [航班唯一编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        hangbanService.insertBatch(hangbanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = hangbanService.queryPage(params);

        //字典表数据转换
        List<HangbanView> list =(List<HangbanView>)page.getList();
        for(HangbanView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HangbanEntity hangban = hangbanService.selectById(id);
            if(hangban !=null){

                //点击数量加1
                hangban.setHangbanClicknum(hangban.getHangbanClicknum()+1);
                hangbanService.updateById(hangban);

                //entity转view
                HangbanView view = new HangbanView();
                BeanUtils.copyProperties( hangban , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody HangbanEntity hangban, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,hangban:{}",this.getClass().getName(),hangban.toString());
        Wrapper<HangbanEntity> queryWrapper = new EntityWrapper<HangbanEntity>()
            .eq("hangban_uuid_number", hangban.getHangbanUuidNumber())
            .eq("hangban_name", hangban.getHangbanName())
            .eq("hangban_types", hangban.getHangbanTypes())
            .eq("zaike_number", hangban.getZaikeNumber())
            .eq("chufashijian", hangban.getChufashijian())
            .eq("chufadi_name", hangban.getChufadiName())
            .eq("mudidi_name", hangban.getMudidiName())
            .eq("hangban_clicknum", hangban.getHangbanClicknum())
            .eq("shangxia_types", hangban.getShangxiaTypes())
            .eq("hangban_delete", hangban.getHangbanDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HangbanEntity hangbanEntity = hangbanService.selectOne(queryWrapper);
        if(hangbanEntity==null){
            hangban.setHangbanDelete(1);
            hangban.setCreateTime(new Date());
        hangbanService.insert(hangban);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
