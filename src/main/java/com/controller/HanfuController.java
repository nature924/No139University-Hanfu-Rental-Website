
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
 * 汉服信息
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/hanfu")
public class HanfuController {
    private static final Logger logger = LoggerFactory.getLogger(HanfuController.class);

    private static final String TABLE_NAME = "hanfu";

    @Autowired
    private HanfuService hanfuService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//交流论坛
    @Autowired
    private GonggaoService gonggaoService;//公告资讯
    @Autowired
    private HanfuCollectionService hanfuCollectionService;//汉服收藏
    @Autowired
    private HanfuCommentbackService hanfuCommentbackService;//汉服评价
    @Autowired
    private HanfuOrderService hanfuOrderService;//汉服租赁
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
        params.put("hanfuDeleteStart",1);params.put("hanfuDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = hanfuService.queryPage(params);

        //字典表数据转换
        List<HanfuView> list =(List<HanfuView>)page.getList();
        for(HanfuView c:list){
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
        HanfuEntity hanfu = hanfuService.selectById(id);
        if(hanfu !=null){
            //entity转view
            HanfuView view = new HanfuView();
            BeanUtils.copyProperties( hanfu , view );//把实体数据重构到view中
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
    public R save(@RequestBody HanfuEntity hanfu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,hanfu:{}",this.getClass().getName(),hanfu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<HanfuEntity> queryWrapper = new EntityWrapper<HanfuEntity>()
            .eq("hanfu_name", hanfu.getHanfuName())
            .eq("zan_number", hanfu.getZanNumber())
            .eq("cai_number", hanfu.getCaiNumber())
            .eq("hanfu_types", hanfu.getHanfuTypes())
            .eq("hanfu_video", hanfu.getHanfuVideo())
            .eq("hanfu_delete", 1)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HanfuEntity hanfuEntity = hanfuService.selectOne(queryWrapper);
        if(hanfuEntity==null){
            hanfu.setZanNumber(1);
            hanfu.setCaiNumber(1);
            hanfu.setHanfuClicknum(1);
            hanfu.setHanfuDelete(1);
            hanfu.setInsertTime(new Date());
            hanfu.setCreateTime(new Date());
            hanfuService.insert(hanfu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody HanfuEntity hanfu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,hanfu:{}",this.getClass().getName(),hanfu.toString());
        HanfuEntity oldHanfuEntity = hanfuService.selectById(hanfu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(hanfu.getHanfuPhoto()) || "null".equals(hanfu.getHanfuPhoto())){
                hanfu.setHanfuPhoto(null);
        }
        if("".equals(hanfu.getHanfuVideo()) || "null".equals(hanfu.getHanfuVideo())){
                hanfu.setHanfuVideo(null);
        }
        if("".equals(hanfu.getHanfuContent()) || "null".equals(hanfu.getHanfuContent())){
                hanfu.setHanfuContent(null);
        }

            hanfuService.updateById(hanfu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<HanfuEntity> oldHanfuList =hanfuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<HanfuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            HanfuEntity hanfuEntity = new HanfuEntity();
            hanfuEntity.setId(id);
            hanfuEntity.setHanfuDelete(2);
            list.add(hanfuEntity);
        }
        if(list != null && list.size() >0){
            hanfuService.updateBatchById(list);
        }

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
            List<HanfuEntity> hanfuList = new ArrayList<>();//上传的东西
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
                            HanfuEntity hanfuEntity = new HanfuEntity();
//                            hanfuEntity.setHanfuName(data.get(0));                    //汉服名称 要改的
//                            hanfuEntity.setHanfuUuidNumber(data.get(0));                    //汉服编号 要改的
//                            hanfuEntity.setHanfuPhoto("");//详情和图片
//                            hanfuEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            hanfuEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            hanfuEntity.setHanfuTypes(Integer.valueOf(data.get(0)));   //汉服类型 要改的
//                            hanfuEntity.setHanfuOldMoney(data.get(0));                    //汉服押金 要改的
//                            hanfuEntity.setHanfuNewMoney(data.get(0));                    //费用 要改的
//                            hanfuEntity.setHanfuVideo(data.get(0));                    //汉服视频 要改的
//                            hanfuEntity.setHanfuClicknum(Integer.valueOf(data.get(0)));   //汉服热度 要改的
//                            hanfuEntity.setHanfuContent("");//详情和图片
//                            hanfuEntity.setHanfuDelete(1);//逻辑删除字段
//                            hanfuEntity.setInsertTime(date);//时间
//                            hanfuEntity.setCreateTime(date);//时间
                            hanfuList.add(hanfuEntity);


                            //把要查询是否重复的字段放入map中
                                //汉服编号
                                if(seachFields.containsKey("hanfuUuidNumber")){
                                    List<String> hanfuUuidNumber = seachFields.get("hanfuUuidNumber");
                                    hanfuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> hanfuUuidNumber = new ArrayList<>();
                                    hanfuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("hanfuUuidNumber",hanfuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //汉服编号
                        List<HanfuEntity> hanfuEntities_hanfuUuidNumber = hanfuService.selectList(new EntityWrapper<HanfuEntity>().in("hanfu_uuid_number", seachFields.get("hanfuUuidNumber")).eq("hanfu_delete", 1));
                        if(hanfuEntities_hanfuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(HanfuEntity s:hanfuEntities_hanfuUuidNumber){
                                repeatFields.add(s.getHanfuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [汉服编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        hanfuService.insertBatch(hanfuList);
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
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<HanfuView> returnHanfuViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("yonghuId",request.getSession().getAttribute("userId"));
        params1.put("shangxiaTypes",1);
        params1.put("hanfuYesnoTypes",2);
        PageUtils pageUtils = hanfuOrderService.queryPage(params1);
        List<HanfuOrderView> orderViewsList =(List<HanfuOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(HanfuOrderView orderView:orderViewsList){
            Integer hanfuTypes = orderView.getHanfuTypes();
            if(typeMap.containsKey(hanfuTypes)){
                typeMap.put(hanfuTypes,typeMap.get(hanfuTypes)+1);
            }else{
                typeMap.put(hanfuTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("hanfuTypes",type);
            params2.put("shangxiaTypes",1);
            params2.put("hanfuYesnoTypes",2);
            PageUtils pageUtils1 = hanfuService.queryPage(params2);
            List<HanfuView> hanfuViewList =(List<HanfuView>)pageUtils1.getList();
            returnHanfuViewList.addAll(hanfuViewList);
            if(returnHanfuViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        params.put("shangxiaTypes",1);
        params.put("hanfuYesnoTypes",2);
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = hanfuService.queryPage(params);
        if(returnHanfuViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnHanfuViewList.size();//要添加的数量
            List<HanfuView> hanfuViewList =(List<HanfuView>)page.getList();
            for(HanfuView hanfuView:hanfuViewList){
                Boolean addFlag = true;
                for(HanfuView returnHanfuView:returnHanfuViewList){
                    if(returnHanfuView.getId().intValue() ==hanfuView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnHanfuViewList.add(hanfuView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnHanfuViewList = returnHanfuViewList.subList(0, limit);
        }

        for(HanfuView c:returnHanfuViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnHanfuViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = hanfuService.queryPage(params);

        //字典表数据转换
        List<HanfuView> list =(List<HanfuView>)page.getList();
        for(HanfuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        HanfuEntity hanfu = hanfuService.selectById(id);
            if(hanfu !=null){

                //点击数量加1
                hanfu.setHanfuClicknum(hanfu.getHanfuClicknum()+1);
                hanfuService.updateById(hanfu);

                //entity转view
                HanfuView view = new HanfuView();
                BeanUtils.copyProperties( hanfu , view );//把实体数据重构到view中

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
    public R add(@RequestBody HanfuEntity hanfu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,hanfu:{}",this.getClass().getName(),hanfu.toString());
        Wrapper<HanfuEntity> queryWrapper = new EntityWrapper<HanfuEntity>()
            .eq("hanfu_name", hanfu.getHanfuName())
            .eq("hanfu_uuid_number", hanfu.getHanfuUuidNumber())
            .eq("zan_number", hanfu.getZanNumber())
            .eq("cai_number", hanfu.getCaiNumber())
            .eq("hanfu_types", hanfu.getHanfuTypes())
            .eq("hanfu_video", hanfu.getHanfuVideo())
            .eq("hanfu_clicknum", hanfu.getHanfuClicknum())
            .eq("hanfu_delete", hanfu.getHanfuDelete())
//            .notIn("hanfu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        HanfuEntity hanfuEntity = hanfuService.selectOne(queryWrapper);
        if(hanfuEntity==null){
                hanfu.setZanNumber(1);
                hanfu.setCaiNumber(1);
            hanfu.setHanfuClicknum(1);
            hanfu.setHanfuDelete(1);
            hanfu.setInsertTime(new Date());
            hanfu.setCreateTime(new Date());
        hanfuService.insert(hanfu);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

