package mate.academy.onlinebookstore01.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_items")
@SQLDelete(sql = "UPDATE order_items SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Book book;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
