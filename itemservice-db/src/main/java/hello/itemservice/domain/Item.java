package hello.itemservice.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity // JPA 객체로 인정. 테이블과 매핑
public class Item {

    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB 에서 값을 넣어주는 전략 (ex : auto_increment)
    private Long id;

    // @Column 생략하면 필드 이름을 테이블 컬럼명으로 사용
    @Column(name = "item_name", length = 10) // 스프링부트에서 사용시 카멜 케이스, 스네이크 케이스 자동 변환. (그래서 이거도 생략가능)
    private String itemName;
    private Integer price;
    private Integer quantity;

    // JPA 는 public 또는 protected 의 기본생성자가 필수이다.
    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
