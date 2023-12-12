package com.example.projeto.service.authentication;


    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
    import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorAppConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    @Autowired
    public LoginInterceptorAppConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns(
                        "/css/**",
                        "/js/**",
                        "/login",
                        "/login/register"
                );
    }
}