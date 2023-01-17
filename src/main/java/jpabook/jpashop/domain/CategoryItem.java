package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;

import javax.persistence.*;

@Entity
public class CategoryItem {

    @Id @GeneratedValue
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
