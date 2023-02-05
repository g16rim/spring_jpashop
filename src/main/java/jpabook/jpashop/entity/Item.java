package jpabook.jpashop.entity;

import jpabook.jpashop.constant.ItemSellStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;                        // 상품 id

    @Column(nullable = false, length = 20)
    private String name;                    // 상품명

    @Column(nullable = false)
    private int price;                      // 상품 가격

    @Column(nullable = false)
    private int stockQuantity;              // 상품 재고 수량

    @Lob
    @Column(nullable = false)
    private String detail;                  // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus status;          // 상품 판매 상태

    private LocalDateTime regTime;          // 상품 등록 시간

    private LocalDateTime updateTime;       // 상품 수정 시간

//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<Category>();

    //==비즈니스 로직==// setter가 아니라 이렇게 이용
    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
