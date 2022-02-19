package com.Projects.MedStore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan
@Configuration
public class WebConfigurer  implements WebMvcConfigurer{

    @Value("${application.upload.folder.image}")
    private String imageUploadDir;
    
    //public static String imageUploadDir= System.getProperty("application.upload.folder.image");

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/images/**", "/css/**", "/img/**", "/js/**", "/lib/**")
        .addResourceLocations("file:"+imageUploadDir+"\\","classpath:/static/css/","classpath:/static/img/","classpath:/static/js/","classpath:/static/lib/");
    }

    
    
}
    

