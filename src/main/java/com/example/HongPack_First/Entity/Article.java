package com.example.HongPack_First.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {



    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


}
