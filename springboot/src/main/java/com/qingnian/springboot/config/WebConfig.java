package com.qingnian.springboot.config;

import com.qingnian.springboot.filter.HeFilter;
import com.qingnian.springboot.interceptor.LoginInterceptor;
import com.qingnian.springboot.servlet.HeServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
任涛 -----专用
*/
@Configuration  //等价于Spring的xml文件
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //定义需要拦截的数组
        String[] pathPatterns={
//                "/**"
        };
        //定义不需要拦截的数组
        String[] excludePathPatterns={
//                "/web/interceptor1"
        };
        InterceptorRegistration interceptor = registry.addInterceptor(new LoginInterceptor());
        interceptor.addPathPatterns(pathPatterns);
        interceptor.excludePathPatterns(excludePathPatterns);
    }

    @Bean   //表示返回一个对象
    public ServletRegistrationBean getServletBean(){
        //第一个参数为对应servlet第二个参数为对应的访问路径
        return new ServletRegistrationBean(new HeServlet(),"/abc");
    }

    @Bean   //表示返回一个对象
    public FilterRegistrationBean getFilterBean(){
        //第一个参数为对应servlet第二个参数为对应的访问路径
        FilterRegistrationBean bean = new FilterRegistrationBean(new HeFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
}
