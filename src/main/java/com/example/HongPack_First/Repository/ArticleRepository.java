package com.example.HongPack_First.Repository;

import com.example.HongPack_First.Entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
