package com.panandafog.mt_server.dummy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "dummy_children")
public class DummyChild {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Dummy dummy;
}
