package com.mangaproject.manga_asd.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer  {
    
@Autowired
    // private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      

        // Se comenta codigo para permitir permisos a la ruta 

        // registry.addInterceptor(tokenInterceptor)
        //         .addPathPatterns("/api/details/list"); 
    }

}
