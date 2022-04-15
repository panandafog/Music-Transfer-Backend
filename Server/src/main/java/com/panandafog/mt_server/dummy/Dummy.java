package com.panandafog.mt_server.dummy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dummies")
@NoArgsConstructor
@AllArgsConstructor
public class Dummy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name", nullable = false, unique = false)
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "dummy", cascade = CascadeType.ALL, orphanRemoval = true)
    @Getter
    @Setter
    private List<DummyChild> children;
}
