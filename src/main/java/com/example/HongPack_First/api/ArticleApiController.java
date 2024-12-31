package com.example.HongPack_First.api;

import com.example.HongPack_First.Dto.ArticleForm;
import com.example.HongPack_First.Entity.Article;
import com.example.HongPack_First.Repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController//RestApi용 컨트롤러 , 데이터(Json)을 반환
public class ArticleApiController {

    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> articles() {
        List<Article> ArticleList = articleRepository.findAll();
        return ArticleList;
    }
    @GetMapping("/api/article/{id}")
    public Article article(@PathVariable Long id) {
        Article article =articleRepository.findById(id).orElse(null);
        return article;
    }

    //Post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm article_dto) {
        Article article = article_dto.toEntity();
        Article SavedArticle = articleRepository.save(article);
        return SavedArticle;
    }

    //Patch
    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm article_dto) {
        Article newarticle = article_dto.toEntity();
        Article old  = articleRepository.findById(id).orElse(null);
        if(old != null){
            old.fet(newarticle);
            Article saved=articleRepository.save(old);
            log.info("null이 아니므로 수정 진행 후 완료 + 수정내용 : {}",saved);
            return ResponseEntity.status(HttpStatus.OK).body(saved);
        }
        log.info("잘못된 요청 id:{} , article:{}",id,article_dto);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target != null) {
            articleRepository.delete(target);
            return ResponseEntity.status(HttpStatus.OK).body(target);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
