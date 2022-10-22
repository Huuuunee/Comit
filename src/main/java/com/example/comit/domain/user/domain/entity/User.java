package com.example.comit.domain.user.domain.entity;

import com.example.comit.domain.posting.domain.entity.Posting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String introduce;

    @Column
    private String refreshToken;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Posting> posting = new ArrayList<>();

}
