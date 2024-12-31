package com.example.HongPack_First.api;

import com.example.HongPack_First.Dto.ArticleForm;
import com.example.HongPack_First.Entity.Article;
import com.example.HongPack_First.Repository.ArticleRepository;
import com.example.HongPack_First.Service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Patch;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController//RestApi용 컨트롤러 , 데이터(Json)을 반환
public class ArticleApiController {

    @Autowired
    private ArticleService articleservice;

    //GET
    @GetMapping("/api/articles")
    public List<Article> articles() {
        List<Article> ArticleList = articleservice.index();
        return ArticleList;
    }

    @GetMapping("/api/article/{id}")
    public Article article(@PathVariable Long id) {
        Article article = articleservice.show(id);
        return article;
    }

    //Post
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm article_dto) {
        Article create = articleservice.create(article_dto);
        return (create != null) ?
                ResponseEntity.status(HttpStatus.OK).body(create) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm article_dto) {
        Article updated = articleservice.update(id, article_dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

//    //Patch
//    @PatchMapping("api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm article_dto) {
//        Article newarticle = article_dto.toEntity();
//        Article old  = articleRepository.findById(id).orElse(null);
//        if(old != null){
//            old.fet(newarticle);
//            Article saved=articleRepository.save(old);
//            log.info("null이 아니므로 수정 진행 후 완료 + 수정내용 : {}",saved);
//            return ResponseEntity.status(HttpStatus.OK).body(saved);
//        }
//        log.info("잘못된 요청 id:{} , article:{}",id,article_dto);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//
//    }

    //    //DELETE
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        Article target = articleRepository.findById(id).orElse(null);
//        if(target != null) {
//            articleRepository.delete(target);
//            return ResponseEntity.status(HttpStatus.OK).body(target);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//    }
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleservice.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //트랜젝션이 실패? -> 롤백
    @PostMapping("/api/transactiontest")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> Dtos) {
         List<Article> ArticleList = articleservice.createArticles(Dtos);
         return (ArticleList!=null) ?
                 ResponseEntity.status(HttpStatus.OK).body(ArticleList):
                 ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
