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

    public Message() {
    }
}
