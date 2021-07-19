package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter  //Lombok 라이브러리 추가해서 가능. 자동으로 getter, setter 사용할 수 있게 됨.
public class HelloData {

    private String username;
    private int age;


}
