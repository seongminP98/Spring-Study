package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor  //롬복에 있음. 이걸 사용하면 final이 붙은걸 가지고 생성자를 만들어줌.
public class BasicItemController {

    private final ItemRepository itemRepository;

    /*
 @RequiredArgsConstructor 있기 때문에 생성자 없어도됨.

    //@Autowired    //생성자가 하나만 있으면 생략 가능.
    public BasicItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

     */

    @GetMapping
    public String items(Model model) {

        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {

        itemRepository.save(item);

        //model.addAttribute("item", item); //@ModelAttribute가 이것도 해줌. 생략가능.

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        //ModelAttribute의 이름은 클래스이름의 첫글자를 소문자로 바꾼게 된다. 여기선 item.
        itemRepository.save(item);

        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        //ModelAttribute 생략 가능.
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true); //얘는 return에 안들어가니 쿼리 파라미터 형식으로 들어간다.

        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
    }
}
