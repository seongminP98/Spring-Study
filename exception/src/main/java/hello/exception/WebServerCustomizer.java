package hello.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");//404에러면 /error-page/400 컨트롤러 호출해라.
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500"); //500에러

        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500");
        //RuntimeException 의 자식타입의 예외도 포함.

        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);

    }
}
