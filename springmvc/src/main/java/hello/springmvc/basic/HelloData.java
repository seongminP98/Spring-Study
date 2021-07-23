package hello.springmvc.basic;

import lombok.Data;

@Data //롬복 사용. 이걸 사용하면 @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor를 자동으로 적용해준다.
public class HelloData {
    private String username;
    private int age;
}
