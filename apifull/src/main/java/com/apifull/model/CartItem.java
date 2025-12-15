package com.apifull.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CART_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "cart_item_seq_gen"
    )
    @SequenceGenerator(
        name = "cart_item_seq_gen",
        sequenceName = "CART_ITEM_SEQ",
        allocationSize = 1
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;
}