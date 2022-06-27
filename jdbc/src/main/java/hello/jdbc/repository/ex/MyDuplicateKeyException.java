package hello.jdbc.repository.ex;

public class MyDuplicateKeyException extends MyDbException { // 기존 사용한 MyDbEx를 상속받아 의미있는 계층 형성.
    public MyDuplicateKeyException() {
    }

    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
