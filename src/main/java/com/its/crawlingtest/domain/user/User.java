package com.its.crawlingtest.domain.user;

import lombok.*;


import javax.persistence.*;

@Getter
@AllArgsConstructor
@Builder
@Entity
public class User {
    public User() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String nickname;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "profile_picture")
    private String picture;

//    @Column(nullable = false)
//    private String gender;
//
//    @Column(nullable = false)
//    private String age; // 연령대

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = true)
//    private Role role;

//    @Builder
//    public User(String name, String email, String picture, Role role){
//        this.name = name;
//        this.email = email;
//        this.picture = picture;
//        this.role = role;
//
//    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;


        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}

