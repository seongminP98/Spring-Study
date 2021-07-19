package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello") // /hello로 오면 이게 실행이된다.
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { //이 서블릿이 호출되면 이 서비스 메서드가 호출된다.
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

        response.setContentType("text/plain");  //헤더정보에 들어감
        response.setCharacterEncoding("utf-8");  //헤더정보에 들어감
        response.getWriter().write("hello " + username);  //write는 HTTP 메시지 바디에 들어간다.

    }
}
