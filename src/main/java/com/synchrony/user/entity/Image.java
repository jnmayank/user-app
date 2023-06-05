package com.synchrony.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "imgur_id", unique = true)
    private String imgurId;

    @Column(name = "imgur_delete_hash")
    private String imgurDeleteHash;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
