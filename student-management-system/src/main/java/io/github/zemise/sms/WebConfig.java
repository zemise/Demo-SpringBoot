package io.github.zemise.sms;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // addResourceHandler中的是访问路径，可以修改为其他的字符串
        // addResourceLocations中的是实际路径

        registry.addResourceHandler("/uploads/**")
//                .addResourceLocations("file:" + ImageTools.getImg());
                // tofix
                .addResourceLocations("file:");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
