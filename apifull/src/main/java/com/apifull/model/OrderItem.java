package com.apifull.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ORDER_ITEMS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "order_item_seq_gen"
    )
    @SequenceGenerator(
        name = "order_item_seq_gen",
        sequenceName = "ORDER_ITEM_SEQ",
        allocationSize = 1
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private String productName;
    private Double price;
    private Integer quantity;
}
