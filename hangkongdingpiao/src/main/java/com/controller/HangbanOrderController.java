
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
 * 航班订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/hangbanOrder")
public class HangbanOrderController {
    private static final Logger logger = LoggerFactory.getLogger(HangbanOrderController.class);

    @Autowired
    private HangbanOrderService hangbanOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private HangbanService hangbanService;
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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = hangbanOrderService.queryPage(params);

        //字典表数据转换
        List<HangbanOrderView> list =(List<HangbanOrderView>)page.getList();
        for(HangbanOrderView c:list){
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
        HangbanOrderEntity hangbanOrder = hangbanOrderService.selectById(id);
        if(hangbanOrder !=null){
            //entity转view
            HangbanOrderView view = new HangbanOrderView();
            BeanUtils.copyProperties( hangbanOrder , view );//把实体数据重构到view中

                //级联表
                HangbanEntity hangban = hangbanService.selectById(hangbanOrder.getHangbanId());
                if(hangban != null){
                    BeanUtils.copyProperties( hangban , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setHangbanId(hangban.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(hangbanOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody HangbanOrderEntity hangbanOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,hangbanOrder:{}",this.getClass().getName(),hangbanOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            hangbanOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        hangbanOrder.setInsertTime(new Date());
        hangbanOrder.setCreateTime(new Date());
        hangbanOrderService.insert(hangbanOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HangbanOrderEntity hangbanOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,hangbanOrder:{}",this.getClass().getName(),hangbanOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
//        else if("用户".equals(role))
//            hangbanOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<HangbanOrderEntity> queryWrapper = new EntityWrapper<HangbanOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HangbanOrderEntity hangbanOrderEntity = hangbanOrderService.selectOne(queryWrapper);
        if(hangbanOrderEntity==null){
            hangbanOrderService.updateById(hangbanOrder);//根据id更新
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
        hangbanOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<HangbanOrderEntity> hangbanOrderList = new ArrayList<>();//上传的东西
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
                            HangbanOrderEntity hangbanOrderEntity = new HangbanOrderEntity();
//                            hangbanOrderEntity.setHangbanOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            hangbanOrderEntity.setHangbanId(Integer.valueOf(data.get(0)));   //航班 要改的
//                            hangbanOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            hangbanOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            hangbanOrderEntity.setChufaTime(new Date(data.get(0)));          //出发日期 要改的
//                            hangbanOrderEntity.setHangbanOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            hangbanOrderEntity.setHangbanOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            hangbanOrderEntity.setHangbanOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            hangbanOrderEntity.setInsertTime(date);//时间
//                            hangbanOrderEntity.setCreateTime(date);//时间
                            hangbanOrderList.add(hangbanOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("hangbanOrderUuidNumber")){
                                    List<String> hangbanOrderUuidNumber = seachFields.get("hangbanOrderUuidNumber");
                                    hangbanOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> hangbanOrderUuidNumber = new ArrayList<>();
                                    hangbanOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("hangbanOrderUuidNumber",hangbanOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<HangbanOrderEntity> hangbanOrderEntities_hangbanOrderUuidNumber = hangbanOrderService.selectList(new EntityWrapper<HangbanOrderEntity>().in("hangban_order_uuid_number", seachFields.get("hangbanOrderUuidNumber")));
                        if(hangbanOrderEntities_hangbanOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HangbanOrderEntity s:hangbanOrderEntities_hangbanOrderUuidNumber){
                                repeatFields.add(s.getHangbanOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        hangbanOrderService.insertBatch(hangbanOrderList);
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
        PageUtils page = hangbanOrderService.queryPage(params);

        //字典表数据转换
        List<HangbanOrderView> list =(List<HangbanOrderView>)page.getList();
        for(HangbanOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HangbanOrderEntity hangbanOrder = hangbanOrderService.selectById(id);
            if(hangbanOrder !=null){


                //entity转view
                HangbanOrderView view = new HangbanOrderView();
                BeanUtils.copyProperties( hangbanOrder , view );//把实体数据重构到view中

                //级联表
                    HangbanEntity hangban = hangbanService.selectById(hangbanOrder.getHangbanId());
                if(hangban != null){
                    BeanUtils.copyProperties( hangban , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setHangbanId(hangban.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(hangbanOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody HangbanOrderEntity hangbanOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,hangbanOrder:{}",this.getClass().getName(),hangbanOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            HangbanEntity hangbanEntity = hangbanService.selectById(hangbanOrder.getHangbanId());
            if(hangbanEntity == null){
                return R.error(511,"查不到该物品");
            }
            // Double hangbanNewMoney = hangbanEntity.getHangbanNewMoney();

            if(false){
            }
            else if(hangbanEntity.getHangbanNewMoney() == null){
                return R.error(511,"航班价格不能为空");
            }
            else if(hangbanOrder.getChufaTime() == null){
                return R.error(511,"出发日期不能为空");
            }


            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
        BigDecimal zhekou = new BigDecimal(1.0);
        // 获取折扣
        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                .eq("dic_code", "huiyuandengji_types")
                .eq("dic_name", "会员等级类型")
                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                ;
        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
        if(dictionaryEntity != null ){
            zhekou = BigDecimal.valueOf(Double.valueOf(dictionaryEntity.getBeizhu()));
        }

            //计算所获得积分
            Double buyJifen =100.0;
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - (new BigDecimal(hangbanEntity.getHangbanNewMoney()).multiply(new BigDecimal(hangbanOrder.getBuyNumber())).multiply(zhekou)).doubleValue();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");


            //查询出当前航班的出发日期的非退款的订单
            List<HangbanOrderEntity> hangbanOrderEntities = hangbanOrderService.selectList(new EntityWrapper<HangbanOrderEntity>().eq("chufa_time", new SimpleDateFormat("yyyy-MM-dd").format(hangbanOrder.getChufaTime())).notIn("hangban_order_types", 2));
            Integer yichengzuoNumber=0;
            for(HangbanOrderEntity h:hangbanOrderEntities){
                yichengzuoNumber+=h.getBuyNumber();
            }

            Integer zongchengzuo = yichengzuoNumber+hangbanOrder.getBuyNumber();
            if(zongchengzuo>hangbanEntity.getZaikeNumber()){
                return R.error(511,"该航班最多可乘坐"+hangbanEntity.getZaikeNumber()+",已经乘坐了"+yichengzuoNumber+",您购买后乘坐数量为"+zongchengzuo+",超过载客量");
            }

            hangbanOrder.setHangbanOrderTypes(3); //设置订单状态为已支付
            hangbanOrder.setHangbanOrderTruePrice(hangbanEntity.getHangbanNewMoney()*hangbanOrder.getBuyNumber()); //设置实付价格
            hangbanOrder.setYonghuId(userId); //设置订单支付人id
            hangbanOrder.setHangbanOrderPaymentTypes(1);
            hangbanOrder.setInsertTime(new Date());
            hangbanOrder.setCreateTime(new Date());
                hangbanOrderService.insert(hangbanOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
            yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);
            yonghuService.updateById(yonghuEntity);
            return R.ok();
        }else{
            return R.error(511,"您没有权限支付订单");
        }
    }
//    /**
//     * 添加订单
//     */
//    @RequestMapping("/order")
//    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
//        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
//        String hangbanOrderUuidNumber = String.valueOf(new Date().getTime());
//
//        //获取当前登录用户的id
//        Integer userId = (Integer) request.getSession().getAttribute("userId");
//
//        Integer chufaTime = Integer.valueOf(String.valueOf(params.get("chufaTime")));//出发日期
//        Integer hangbanOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("hangbanOrderPaymentTypes")));//支付类型
//
//        String data = String.valueOf(params.get("hangbans"));
//        JSONArray jsonArray = JSON.parseArray(data);
//        List<Map> hangbans = JSON.parseObject(jsonArray.toString(), List.class);
//
//        //获取当前登录用户的个人信息
//        YonghuEntity yonghuEntity = yonghuService.selectById(userId);
//
//        //当前订单表
//        List<HangbanOrderEntity> hangbanOrderList = new ArrayList<>();
//        //商品表
//        List<HangbanEntity> hangbanList = new ArrayList<>();
//
//        BigDecimal zhekou = new BigDecimal(1.0);
//        // 获取折扣
//        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
//                .eq("dic_code", "huiyuandengji_types")
//                .eq("dic_name", "会员等级类型")
//                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
//                ;
//        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
//        if(dictionaryEntity != null ){
//            zhekou = BigDecimal.valueOf(Double.valueOf(dictionaryEntity.getBeizhu()));
//        }
//
//        //循环取出需要的数据
//        for (Map<String, Object> map : hangbans) {
//           //取值
//            Integer hangbanId = Integer.valueOf(String.valueOf(map.get("hangbanId")));//商品id
//            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
//            HangbanEntity hangbanEntity = hangbanService.selectById(hangbanId);//购买的商品
//            String id = String.valueOf(map.get("id"));
//
//            /**
//             * 额外判断
//             */
////            //判断商品的库存是否足够
////            if(hangbanEntity.getZaikeNumber() < buyNumber){
////                //商品库存不足直接返回
////                return R.error(hangbanEntity.getHangbanName()+"的库存不足");
////            }else{
////                //商品库存充足就减库存
////                hangbanEntity.setZaikeNumber(hangbanEntity.getZaikeNumber() - buyNumber);
////            }
//
//            //订单信息表增加数据
//            HangbanOrderEntity hangbanOrderEntity = new HangbanOrderEntity<>();
//
//            //赋值订单信息
//            hangbanOrderEntity.setHangbanOrderUuidNumber(hangbanOrderUuidNumber);//订单号
//            hangbanOrderEntity.setHangbanId(hangbanId);//航班
//            hangbanOrderEntity.setYonghuId(userId);//用户
//            hangbanOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
////            hangbanOrderEntity.setChufaTime(chufaTime);//出发日期 ？？？？？？
//            hangbanOrderEntity.setHangbanOrderTypes(3);//订单类型
//            hangbanOrderEntity.setHangbanOrderPaymentTypes(hangbanOrderPaymentTypes);//支付类型
//            hangbanOrderEntity.setInsertTime(new Date());//订单创建时间
//            hangbanOrderEntity.setCreateTime(new Date());//创建时间
//
//            //判断是什么支付方式 1代表余额 2代表积分
//            if(hangbanOrderPaymentTypes == 1){//余额支付
//                //计算金额
//                Double money = new BigDecimal(hangbanEntity.getHangbanNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();
//
//                if(yonghuEntity.getNewMoney() - money <0 ){
//                    return R.error("余额不足,请充值！！！");
//                }else{
//                    //计算所获得积分
//                    Double buyJifen =0.0;
//                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额
//                    yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
//                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
//                        if(yonghuEntity.getYonghuSumJifen()  < 10000)
//                            yonghuEntity.setHuiyuandengjiTypes(1);
//                        else if(yonghuEntity.getYonghuSumJifen()  < 100000)
//                            yonghuEntity.setHuiyuandengjiTypes(2);
//                        else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
//                            yonghuEntity.setHuiyuandengjiTypes(3);
//
//
//                    hangbanOrderEntity.setHangbanOrderTruePrice(money);
//
//                }
//            }
//            hangbanOrderList.add(hangbanOrderEntity);
//            hangbanList.add(hangbanEntity);
//
//        }
//        hangbanOrderService.insertBatch(hangbanOrderList);
//        hangbanService.updateBatchById(hangbanList);
//        yonghuService.updateById(yonghuEntity);
//        return R.ok();
//    }






    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

        if("用户".equals(role)){
            HangbanOrderEntity hangbanOrder = hangbanOrderService.selectById(id);
            Integer buyNumber = hangbanOrder.getBuyNumber();
            Integer hangbanOrderPaymentTypes = hangbanOrder.getHangbanOrderPaymentTypes();
            Integer hangbanId = hangbanOrder.getHangbanId();
            if(hangbanId == null)
                return R.error(511,"查不到该物品");
            HangbanEntity hangbanEntity = hangbanService.selectById(hangbanId);
            if(hangbanEntity == null)
                return R.error(511,"查不到该物品");
            Double hangbanNewMoney = hangbanEntity.getHangbanNewMoney();
            if(hangbanNewMoney == null)
                return R.error(511,"物品价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 100.0;
            // 获取折扣
            Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                    .eq("dic_code", "huiyuandengji_types")
                    .eq("dic_name", "会员等级类型")
                    .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                    ;
            DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
            if(dictionaryEntity != null ){
                zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
            }


            //判断是什么支付方式 1代表余额 2代表积分
            if(hangbanOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = hangbanEntity.getHangbanNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
                yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() - buyJifen); //设置总积分
                if(yonghuEntity.getYonghuNewJifen() - buyJifen <0 )
                    return R.error("积分已经消费,无法退款！！！");
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - buyJifen); //设置现积分

                if(yonghuEntity.getYonghuSumJifen()  < 1000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(3);

            }


            hangbanOrder.setHangbanOrderTypes(2);//设置订单状态为退款
            hangbanOrderService.updateById(hangbanOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            hangbanService.updateById(hangbanEntity);//更新订单中物品的信息
            return R.ok();
        }else{
            return R.error(511,"您没有权限退款");
        }
    }


    /**
     * 使用
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        HangbanOrderEntity  hangbanOrderEntity = new  HangbanOrderEntity();;
        hangbanOrderEntity.setId(id);
        hangbanOrderEntity.setHangbanOrderTypes(4);
        boolean b =  hangbanOrderService.updateById( hangbanOrderEntity);
        if(!b){
            return R.error("使用出错");
        }
        return R.ok();
    }












}
