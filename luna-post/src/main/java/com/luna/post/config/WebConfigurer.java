package com.luna.post.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author luna
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 所有路径都被拦截
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
            .excludePathPatterns( // 添加不拦截路径
                "/login",
                "/api/logout",
                "/api/login",
                "/**/*.html", // html静态资源
                "/**/*.js", // js静态资源
                "/**/*.css", // css静态资源
                "/**/*.woff",
                "/**/*.jif",
                "/**/*.ttf");
    }
}
