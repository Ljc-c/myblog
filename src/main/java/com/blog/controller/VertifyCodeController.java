package com.blog.controller;

import com.blog.entity.VerifyCode;
import com.blog.service.validcode.IVerifyCodeGen;
import com.blog.service.validcode.SimpleCharVerifyCodeGenImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class VertifyCodeController {

    //@ApiOperation(value = "验证码")    //@ApiOperation(value = “接口说明”, httpMethod = “接口请求方式”, response =
//“接口返回参数类型”, notes = “接口发布说明”；其他参数可参考源码；
    @GetMapping({"/verifyCode","/admin/verifycode"})
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();

            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
<div>
    <input id="code" placeholder="验证码" type="text" class=""
           style="width:170px">
    <!-- 验证码 显示 -->
    <img οnclick="javascript:getvCode()" id="verifyimg" style="margin-left: 20px;"/>
</div>

<script type="text/javascript">
    getvCode();

    /**
     * 获取验证码
     * 将验证码写到login.html页面的id = verifyimg 的地方
     */
/* function getvCode() {
    document.getElementById("verifyimg").src = timestamp("http://127.0.0.1:81/verifyCode");
}
    //为url添加时间戳
    function timestamp(url) {
        var getTimestamp = new Date().getTime();
        if (url.indexOf("?") > -1) {
            url = url + "&timestamp=" + getTimestamp
        } else {
            url = url + "?timestamp=" + getTimestamp
        }
        return url;
    };


</script>

 */