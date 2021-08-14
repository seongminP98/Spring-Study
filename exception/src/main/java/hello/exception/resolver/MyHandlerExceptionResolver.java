package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try {
            if(ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                 response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                 return new ModelAndView(); //빈값으로 넘기면 WAS 까지 정상 리턴이된다. 예외는 여기서 먹고, WAS는 sendError호출되고 400으로 왔다라고 인식
            }
        } catch (IOException e) {
            log.error("resolver ex", e);
        }

        return null;
    }
}
