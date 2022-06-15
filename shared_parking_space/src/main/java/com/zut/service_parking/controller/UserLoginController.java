package com.zut.service_parking.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zut.service_parking.entity.User;
import com.zut.service_parking.service.UserService;
import com.zut.service_parking.utils.HttpUtils;
import com.zut.service_parking.utils.RandomUtil;
import com.zut.service_parking.utils.commonutils.R;
import com.zut.service_parking.utils.commonutils.util.JwtUtils;
import com.zut.service_parking.utils.commonutils.util.MD5;
import com.zut.service_parking.utils.config.exceptionHandler.ZutException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/service_parking/user")
@CrossOrigin //解决跨域问题
public class UserLoginController {

    @Autowired//注入Service 默认按照类型
    //@Qualifier：根据名称进行注入
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //login登录功能
//    @Cacheable(value = "user",key ="'findByProductNo'+#productNo")
    @PostMapping(value = "login/{productNo}/{pwd}")
    public R login(
            @PathVariable String productNo,
            @PathVariable String pwd
    ){
        //创建对象
        Page<User> page = new Page<>(1,1);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        if(!StringUtils.isEmpty(productNo)){
            //构造条件
            QueryWrapper<User> eq = userQueryWrapper.eq("product_no", productNo);
        }
        //调用方法使用条件查询
        IPage<User> page1 = userService.page(page, userQueryWrapper);
//        System.out.println(page1.getRecords());
        Long total = page1.getTotal();//记录数
        if(total!=0){
            String pwd1 ="";
            String id = "";
//            String name1 = "";
            Integer status = 0;
            List<User> records = page1.getRecords();//集合
            for (User record : records) {
                pwd1 = record.getPwd();
                id = record.getId();
//                name1 = record.getName();
                status = record.getStatus();
            }
            if(status==1) {
                if (pwd1.equals(MD5.encodeByMD5(pwd))) {
                    //使用JWT生成token字符串
                    String token = JwtUtils.getJwtToken(id, productNo);
//                    System.out.println("token://///"+token);
                    return R.ok().data("token", token);
                } else {
                    return R.error().message("密码错误，请重新输入!");
                }
            }else {
                return R.error().message("手机号被封号！");
            }
        } else {
            return R.error().message("手机号未被注册！");
        }
    }

    //info 登录后展示个人信息
    @GetMapping("info/{productNo}")
    public R info(
            @PathVariable String productNo
    ){
        //创建对象
        Page<User> page = new Page<>(1,1);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        if(!StringUtils.isEmpty(productNo)){
            //构造条件
            userQueryWrapper.eq("product_no", productNo);
        }
        //调用方法使用条件查询
//        String productNo1 = null,name1 = null,sex1 = null,cardId1 = null,cardId11 = null,cardId21 = null,cardId31 = null;
        String[] roles = new String[8];
        IPage<User> page1 = userService.page(page, userQueryWrapper);
        List<User> records = page1.getRecords();
        for (User record : records) {
            roles[0] = record.getProductNo();
            roles[1] = record.getName();
            roles[2] = record.getSex();
            roles[3] = record.getCardId();
            roles[4] = record.getCarId1();
            roles[5] = record.getCarId2();
            roles[6] = record.getCarId3();
            roles[7] = String.valueOf(record.getMoney());
        }
        return R.ok()
                .data("name",""+roles[1])
                .data("roles",""+""+roles[7])
                .data("avatar",
                        "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202005%2F03%2F20200503193405_QavAd.thumb.1000_0.jpeg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1652617370&t=1b19092cb50b1165a85d24668d7a2e1b");
    }

    //getLoginInfo 获取token的登录信息
    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("getInfoByToken")
    public R getInfoByToken(HttpServletRequest request){
        try {
            String id = JwtUtils.getIdByJwtToken(request);
            String productNo = JwtUtils.getProductNoByJwtToken(request);
//            System.out.println(id);
//            System.out.println(productNo);
            User user = userService.getById(id);
            return R.ok().data("user", user).data("id",id).data("productNo",productNo);
        }catch (Exception e){
            e.printStackTrace();
            throw new ZutException(20001,"error");
        }
    }

    @PostMapping("logout")
    public R logout(){
        return R.ok();
    }

    //1.返回用户注册验证码 登录 Map<String, String>  <productNo,code> <手机号,验证码>
    @CachePut(value = "code", key ="#productNo")
    @ApiOperation("返回用户注册验证码 登录")
    @PostMapping("register/sms/send/{productNo}")
    public R registerCode(
            @PathVariable String productNo
    ){
        if(isProductNo(productNo)){
            String value = RandomUtil.getSixBitRandom();
            String host = "https://jmsms.market.alicloudapi.com";
            String path = "/sms/send";
            String method = "POST";
            String appcode = "a62a617c3f904c40a90c4142b0ed63ae";
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + appcode);
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("mobile", productNo);
            querys.put("templateId", "M72CB42894");
            querys.put("value", value);
            Map<String, String> bodys = new HashMap<String, String>();
            try {
                /**
                 * 重要提示如下:
                 * HttpUtils请从
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
                 * 下载
                 *
                 * 相应的依赖请参照
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
                 */
                HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
                System.out.println(response.toString());
                //获取response的body
                System.out.println(EntityUtils.toString(response.getEntity()));
                // .data("prductNo",productNo)
                return R.ok().data("code",value).message("验证码已发送，5分钟内有效！");
            } catch (Exception e) {
                e.printStackTrace();
                return R.error();
            }
        }else {
            return R.error().message("手机号已被注册！");
        }

    }

    //1.返回用户注册验证码 找回密码 Map<String, String>  <productNo,code> <手机号,验证码>
    @CachePut(value = "codeFindPWD", key ="#productNo")
    @ApiOperation("返回用户注册验证码 找回密码")
    @PostMapping("findPWD/sms/send/{productNo}")
    public R findPWD(
            @PathVariable String productNo
    ){
        if(!isProductNo(productNo)){
            String value = RandomUtil.getSixBitRandom();
            String host = "https://jmsms.market.alicloudapi.com";
            String path = "/sms/send";
            String method = "POST";
            String appcode = "a62a617c3f904c40a90c4142b0ed63ae";
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + appcode);
            Map<String, String> querys = new HashMap<String, String>();
            querys.put("mobile", productNo);
            querys.put("templateId", "M72CB42894");
            querys.put("value", value);
            Map<String, String> bodys = new HashMap<String, String>();
            try {
                /**
                 * 重要提示如下:
                 * HttpUtils请从
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
                 * 下载
                 *
                 * 相应的依赖请参照
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
                 */
                HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
                System.out.println(response.toString());
                //获取response的body
                System.out.println(EntityUtils.toString(response.getEntity()));
                // .data("prductNo",productNo)
                return R.ok().data("code",value).message("验证码已发送，5分钟内有效！");
            } catch (Exception e) {
                e.printStackTrace();
                return R.error();
            }
        }else {
            return R.error().message("未注册手机号，请在注册页面注册！");
        }

    }

    //2.模仿测试短信接口返回用户注册验证码 登录 Map<String, String>  <productNo,code> <手机号,验证码>
    @CachePut(value = "code", key ="#productNo")
    @ApiOperation("模仿测试:返回用户注册验证码 登录")
    @PostMapping("register/sms/sendtest/{productNo}")
    public R registerCodeTest(
            @PathVariable String productNo
    ){
        //判断手机号是否已注册
        if(isProductNo(productNo)){
            String value = RandomUtil.getSixBitRandom();
            return R.ok().data("code",value).message("验证码已发送，5分钟内有效！");
        }else {
            return R.error().message("手机号已被注册！");
        }

    }

    //2.模仿测试短信接口返回用户注册验证码 找回密码 Map<String, String>  <productNo,code> <手机号,验证码>
    @CachePut(value = "codeFindPWD", key ="#productNo")
    @ApiOperation("模仿测试:返回用户注册验证码 找回密码")
    @PostMapping("findPWDInfo/sms/sendtest/{productNo}")
    public R findPWDInfo(
            @PathVariable String productNo
    ){
        //判断手机号是否已注册
        if(!isProductNo(productNo)){
            String value = RandomUtil.getSixBitRandom();
            return R.ok().data("code",value).message("验证码已发送，5分钟内有效！");
        }else {
            return R.error().message("未注册手机号，请在注册页面注册！");
        }

    }

    //3.注册并插入账号密码到数据库 注册
    @CachePut(value = "user",key ="'findByProductNo'+#productNo")
    @ApiOperation("注册并插入账号密码到数据库 注册")
    @PostMapping("register/sms/register/{name}/{productNo}/{pwd}/{code}")
    public R register(
            @PathVariable String name,
            @PathVariable String productNo,
            @PathVariable String pwd,
            @PathVariable String code
    ){
        String redis = stringRedisTemplate.opsForValue().get("code::" + productNo);
        //截取redis指定缓存数据中的六位验证码
        String code1 = redis.substring(redis.length()-11,redis.length()-5);
//        System.out.println(redis);
//        System.out.println(code1);
        if(isProductNo(productNo)) {
            if (code1.equals(code)) {//验证码相等
                User user = new User();
                user.setName(name);
                user.setProductNo(productNo);
                user.setPwd(MD5.encodeByMD5(pwd));
                boolean save = userService.save(user);
                if (save) {
                    return R.ok().data("name", user.getName())
                            .data("productNo", productNo)
                            .data("pwd", user.getPwd())
                            .data("code", code);
                } else {
                    return R.error();
                }
            } else {//验证码不同
                return R.error().message("验证码错误！");
            }
        }else {
            return R.error().message("已注册成功，请勿多次点击！");
        }
    }


    //3.找回密码 并插入信息到数据库
    @CacheEvict(value = "user",key ="'findByProductNo'+#productNo",allEntries = true)
    @ApiOperation("找回密码 并插入信息到数据库")
    @PostMapping("register/sms/findPWD/{productNo}/{pwd}/{code}")
    public R findPWD(
            @PathVariable String productNo,
            @PathVariable String pwd,
            @PathVariable String code
    ){
        String redis = stringRedisTemplate.opsForValue().get("codeFindPWD::" + productNo);
        //截取redis指定缓存数据中的六位验证码
        String code1 = redis.substring(redis.length()-11,redis.length()-5);
//        System.out.println(redis);
        System.out.println(code1);
        if(!isProductNo(productNo)) {
            if (code1.equals(code)) {//验证码相等

                //创建对象 获取ID
                Page<User> page = new Page<>(1,1);
                QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
                if(!StringUtils.isEmpty(productNo)){
                    //构造条件
                    userQueryWrapper.eq("product_no",productNo);
                }
                //调用方法使用条件查询
                IPage<User> page1 = userService.page(page, userQueryWrapper);
                List<User> records = page1.getRecords();//集合
                String id="";
                for (User record : records) {
                    id = record.getId();
                }

                User user = new User();
                user.setProductNo(productNo);
                user.setPwd(MD5.encodeByMD5(pwd));

                //设置指定id 修改对象到 数据库中
                user.setId(id);
                userService.updateById(user);
                return R.ok();
            } else {//验证码不同
                return R.error().message("验证码错误！");
            }
        }else {
            return R.error().message("未注册手机号，请在注册页面注册！");
        }
    }


    //function1:判断手机号是否存在数据库 0 true 1 false
    public Boolean isProductNo(
            String productNo
    ){
        //创建对象
        Page<User> page = new Page<>(1,1);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();
        if(!StringUtils.isEmpty(productNo)){
            //构造条件
            userQueryWrapper.eq("product_no",productNo);
        }
        //调用方法使用条件查询
        IPage<User> page1 = userService.page(page, userQueryWrapper);
        Long total = page1.getTotal();//记录数
        if(total!=0){
            return false;
        }else {
            return true;
        }
    }

}
