
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
 * 汉服租赁
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/hanfuOrder")
public class HanfuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(HanfuOrderController.class);

    private static final String TABLE_NAME = "hanfuOrder";

    @Autowired
    private HanfuOrderService hanfuOrderService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//交流论坛
    @Autowired
    private GonggaoService gonggaoService;//公告资讯
    @Autowired
    private HanfuService hanfuService;//汉服信息
    @Autowired
    private HanfuCollectionService hanfuCollectionService;//汉服收藏
    @Autowired
    private HanfuCommentbackService hanfuCommentbackService;//汉服评价
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = hanfuOrderService.queryPage(params);

        //字典表数据转换
        List<HanfuOrderView> list =(List<HanfuOrderView>)page.getList();
        for(HanfuOrderView c:list){
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
        HanfuOrderEntity hanfuOrder = hanfuOrderService.selectById(id);
        if(hanfuOrder !=null){
            //entity转view
            HanfuOrderView view = new HanfuOrderView();
            BeanUtils.copyProperties( hanfuOrder , view );//把实体数据重构到view中
            //级联表 汉服信息
            //级联表
            HanfuEntity hanfu = hanfuService.selectById(hanfuOrder.getHanfuId());
            if(hanfu != null){
            BeanUtils.copyProperties( hanfu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setHanfuId(hanfu.getId());
            }
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(hanfuOrder.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody HanfuOrderEntity hanfuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,hanfuOrder:{}",this.getClass().getName(),hanfuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            hanfuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        hanfuOrder.setCreateTime(new Date());
        hanfuOrder.setInsertTime(new Date());
        hanfuOrderService.insert(hanfuOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HanfuOrderEntity hanfuOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,hanfuOrder:{}",this.getClass().getName(),hanfuOrder.toString());
        HanfuOrderEntity oldHanfuOrderEntity = hanfuOrderService.selectById(hanfuOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            hanfuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            hanfuOrderService.updateById(hanfuOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<HanfuOrderEntity> oldHanfuOrderList =hanfuOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        hanfuOrderService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<HanfuOrderEntity> hanfuOrderList = new ArrayList<>();//上传的东西
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
                            HanfuOrderEntity hanfuOrderEntity = new HanfuOrderEntity();
//                            hanfuOrderEntity.setHanfuOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            hanfuOrderEntity.setHanfuId(Integer.valueOf(data.get(0)));   //汉服 要改的
//                            hanfuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            hanfuOrderEntity.setHanfuOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            hanfuOrderEntity.setHanfuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            hanfuOrderEntity.setInsertTime(date);//时间
//                            hanfuOrderEntity.setCreateTime(date);//时间
                            hanfuOrderList.add(hanfuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("hanfuOrderUuidNumber")){
                                    List<String> hanfuOrderUuidNumber = seachFields.get("hanfuOrderUuidNumber");
                                    hanfuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> hanfuOrderUuidNumber = new ArrayList<>();
                                    hanfuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("hanfuOrderUuidNumber",hanfuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<HanfuOrderEntity> hanfuOrderEntities_hanfuOrderUuidNumber = hanfuOrderService.selectList(new EntityWrapper<HanfuOrderEntity>().in("hanfu_order_uuid_number", seachFields.get("hanfuOrderUuidNumber")));
                        if(hanfuOrderEntities_hanfuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HanfuOrderEntity s:hanfuOrderEntities_hanfuOrderUuidNumber){
                                repeatFields.add(s.getHanfuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        hanfuOrderService.insertBatch(hanfuOrderList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
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

        CommonUtil.checkMap(params);
        PageUtils page = hanfuOrderService.queryPage(params);

        //字典表数据转换
        List<HanfuOrderView> list =(List<HanfuOrderView>)page.getList();
        for(HanfuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HanfuOrderEntity hanfuOrder = hanfuOrderService.selectById(id);
            if(hanfuOrder !=null){


                //entity转view
                HanfuOrderView view = new HanfuOrderView();
                BeanUtils.copyProperties( hanfuOrder , view );//把实体数据重构到view中

                //级联表
                    HanfuEntity hanfu = hanfuService.selectById(hanfuOrder.getHanfuId());
                if(hanfu != null){
                    BeanUtils.copyProperties( hanfu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setHanfuId(hanfu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(hanfuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
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
    public R add(@RequestBody HanfuOrderEntity hanfuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,hanfuOrder:{}",this.getClass().getName(),hanfuOrder.toString());
            HanfuEntity hanfuEntity = hanfuService.selectById(hanfuOrder.getHanfuId());
            if(hanfuEntity == null){
                return R.error(511,"查不到该汉服信息");
            }
            // Double hanfuNewMoney = hanfuEntity.getHanfuNewMoney();

            if(false){
            }
            else if(hanfuEntity.getHanfuNewMoney() == null){
                return R.error(511,"费用不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - hanfuEntity.getHanfuNewMoney()*1-hanfuEntity.getHanfuOldMoney();//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            hanfuOrder.setHanfuOrderTypes(101); //设置订单状态为已支付
            hanfuOrder.setHanfuOrderTruePrice(hanfuEntity.getHanfuNewMoney()*1+hanfuEntity.getHanfuOldMoney()); //设置实付价格
            hanfuOrder.setYonghuId(userId); //设置订单支付人id
            hanfuOrder.setHanfuOrderUuidNumber(String.valueOf(new Date().getTime()));
            hanfuOrder.setInsertTime(new Date());
            hanfuOrder.setCreateTime(new Date());
                hanfuOrderService.insert(hanfuOrder);//新增订单
            //更新第一注册表
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);


            return R.ok();
    }


    /**
    * 拒绝
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            HanfuOrderEntity hanfuOrder = hanfuOrderService.selectById(id);//当前表service
            Integer hanfuId = hanfuOrder.getHanfuId();
            if(hanfuId == null)
                return R.error(511,"查不到该汉服信息");
            HanfuEntity hanfuEntity = hanfuService.selectById(hanfuId);
            if(hanfuEntity == null)
                return R.error(511,"查不到该汉服信息");
            Double hanfuNewMoney = hanfuEntity.getHanfuNewMoney();
            if(hanfuNewMoney == null)
                return R.error(511,"汉服信息价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
            return R.error(511,"用户金额不能为空");
            Double zhekou = 1.0;

                //计算金额
                Double money = hanfuEntity.getHanfuNewMoney() +  hanfuEntity.getHanfuOldMoney();
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额




            hanfuOrder.setHanfuOrderTypes(102);//设置订单状态为已拒绝
            hanfuOrderService.updateAllColumnById(hanfuOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            hanfuService.updateById(hanfuEntity);//更新订单中汉服信息的信息

            return R.ok();
    }

    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer hanfuCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            HanfuOrderEntity hanfuOrder = hanfuOrderService.selectById(id);
        if(hanfuOrder == null)
            return R.error(511,"查不到该订单");
        Integer hanfuId = hanfuOrder.getHanfuId();
        if(hanfuId == null)
            return R.error(511,"查不到该汉服信息");

        HanfuCommentbackEntity hanfuCommentbackEntity = new HanfuCommentbackEntity();
            hanfuCommentbackEntity.setId(id);
            hanfuCommentbackEntity.setHanfuId(hanfuId);
            hanfuCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            hanfuCommentbackEntity.setHanfuCommentbackText(commentbackText);
            hanfuCommentbackEntity.setInsertTime(new Date());
            hanfuCommentbackEntity.setReplyText(null);
            hanfuCommentbackEntity.setUpdateTime(null);
            hanfuCommentbackEntity.setCreateTime(new Date());
            hanfuCommentbackService.insert(hanfuCommentbackEntity);

            hanfuOrder.setHanfuOrderTypes(105);//设置订单状态为已评价
            hanfuOrderService.updateById(hanfuOrder);//根据id更新
            return R.ok();
    }

    /**
     * 同意
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        HanfuOrderEntity  hanfuOrderEntity = hanfuOrderService.selectById(id);
        hanfuOrderEntity.setHanfuOrderTypes(103);//设置订单状态为已同意
        hanfuOrderService.updateById( hanfuOrderEntity);

        return R.ok();
    }


    /**
     * 归还
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        HanfuOrderEntity  hanfuOrderEntity = hanfuOrderService.selectById(id);
        hanfuOrderEntity.setHanfuOrderTypes(104);//设置订单状态为归还
        hanfuOrderService.updateById( hanfuOrderEntity);
        return R.ok();
    }


    /**
     * 退还押金
     */
    @RequestMapping("/tuihuan")
    public R tuihuan(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        HanfuOrderEntity  hanfuOrderEntity = hanfuOrderService.selectById(id);
        HanfuEntity hanfuEntity = hanfuService.selectById(hanfuOrderEntity.getHanfuId());
        YonghuEntity yonghuEntity = yonghuService.selectById(hanfuOrderEntity.getYonghuId());
        Double hanfuOldMoney = hanfuEntity.getHanfuOldMoney();
        yonghuEntity.setNewMoney(yonghuEntity.getNewMoney()+hanfuOldMoney);
        hanfuOrderEntity.setHanfuOrderTypes(106);//设置订单状态为已同意
        yonghuService.updateById(yonghuEntity);
        hanfuOrderService.updateById( hanfuOrderEntity);

        return R.ok();
    }

}

