package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data //핵심도메인 상황에 쓰기 위험하다. 지금은 공부하는 거니 그냥 사용.
//@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;      //null이 들어갈 수 있게 하기 위해서 Integer사용.
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
