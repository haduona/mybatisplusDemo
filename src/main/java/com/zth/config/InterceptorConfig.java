package com.zth.config;
import com.zth.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .excludePathPatterns("/**");
//                //拦截
//                .addPathPatterns("/**")
//                //放行
//                .excludePathPatterns("/admin/register","/user/login", "/admin/login");
    }
}
