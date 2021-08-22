//package com.blog.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * 与package com.blog.interceptor.LoginInterceptor相连
// * 指定拦截网页url
// */
//@Configuration
//public class WebConfig extends WebMvcConfigurerAdapter {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/admin/**")            //过滤admin后所有
//                .excludePathPatterns("/admin")          //排除一些路径
//                .excludePathPatterns("/admin/login");
//
//    }
//}
