package com.example.HongPack_First.Dto;

import com.example.HongPack_First.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class ArticleForm {

    private String title;

    private String content;

    public Article toEntity(){return new Article(null,title,content);}
}
