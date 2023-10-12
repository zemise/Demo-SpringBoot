package io.github.zemise.security01.domain.onetomany;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "m_message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String info;

    public Message(String info) {
        this.info = info;
    }

    public Message(String info, CustomerM customer) {
        this.info = info;
        this.customer = customer;
    }

    public Message() {
    }

    // 多对一
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private CustomerM customer;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", info='" + info + '\'' +
                ", customerId=" + customer.getId() +
                ", customerName=" + customer.getName() +
                '}';
    }
}
