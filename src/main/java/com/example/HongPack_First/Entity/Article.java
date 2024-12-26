package com.example.HongPack_First.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Article {



    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


}
