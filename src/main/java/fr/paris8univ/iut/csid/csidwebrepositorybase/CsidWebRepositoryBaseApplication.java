package fr.paris8univ.iut.csid.csidwebrepositorybase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CsidWebRepositoryBaseApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(CsidWebRepositoryBaseApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CsidWebRepositoryBaseApplication.class);
    }
}

 /* @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://onlyweebs.csid.agilitejoviale.fr")
                        .allowedMethods("GET", "PUT", "POST", "DELETE")
                        .allowCredentials(true);
            }
        };
    }*/
