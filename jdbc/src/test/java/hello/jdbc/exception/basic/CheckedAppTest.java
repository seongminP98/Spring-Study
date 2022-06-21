package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

/**
 * 2가지 문제
 * 1. 복구 불가능한 예외 (대부분의 서비스나 컨트롤러는 이런 문제를 해결하지 못한다)
 *      - 서블릿 필터, 스프링 인터셉터, 스프링의 ControllerAdvice를 사용해 공통으로 해결한다.
 * 2. 의존 관계에 대한 문제
 *      - 체크 예외이기 떄문에 컨트롤러나 서비스 입장에서는 본인이 처리할 수 없어도 어쩔 수 없이 throws를 통해 던지는 예외를 선언해야 한다.
 *          - 서비스, 컨트롤러에서 java.sql.SQLException(JDBC 기술)을 의존하기 때문에 문제가 된다.
 */
public class CheckedAppTest {

    @Test
    void checked() {
        Controller controller = new Controller();
        assertThatThrownBy(controller::request)
                .isInstanceOf(Exception.class);
    }

    static class Controller {
        Service service = new Service();

        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service {
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() throws SQLException, ConnectException {
            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient {
        public void call() throws ConnectException {
            throw new ConnectException("연결 실패");
        }
    }

    static class Repository {
        public void call() throws SQLException {
            throw new SQLException("ex");
        }
    }
}
