package rc.ac.singidunum.novisad.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class CORS implements WebMvcConfigurer {
    @SuppressWarnings("null")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
     // Ovo dozvoljava zahteve sa svih domena
            .allowedOrigins("http://localhost:4200", "http://localhost:8080")	
         // Dozvoljeni HTTP metodi
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")	 
         // Dozvoljeni HTTP zaglavlja
            .allowedHeaders("*") 	
         // Dozvoljava slanje kredencijala (npr. kolačića) sa zahtevima
            .allowCredentials(true); 	
    }
    
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

