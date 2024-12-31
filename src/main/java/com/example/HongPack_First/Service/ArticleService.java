package com.example.HongPack_First.Service;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import com.example.HongPack_First.Dto.ArticleForm;
import com.example.HongPack_First.Entity.Article;
import com.example.HongPack_First.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articlerepository;

    public List<Article> index() {
        return articlerepository.findAll();
    }

    public Article show(Long id) {
        return articlerepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm article_dto) {
        Article article = article_dto.toEntity();
        if(article.getId() != null) {
            return null;
        }
        return articlerepository.save(article);
    }

    public Article update(Long id, ArticleForm articleDto) {
        Article target = articlerepository.findById(id).orElse(null);
        Article article = articleDto.toEntity();
        if(target == null || id!=target.getId()) {
            log.info("잘못된요청 id:{} , target {}",id,target);
            return null;
        }
        target.fet(article);
        Article updated = articlerepository.save(target);
        return updated;


    }

    public Article delete(Long id) {
         Article target = articlerepository.findById(id).orElse(null);
         if(target == null) {
             return null;
         }
         articlerepository.delete(target);
         return target;
    }
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){
        //dto묶음을 entity묶음으로 저장
        log.info("dtos:{}",dtos);
        List<Article> articlelist = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        log.info("articlelist:{}",articlelist);
        //entity묶음을 DB로 저장
        articlelist.stream()
                .forEach(article -> articlerepository.save(article));

        //강제예외 발생
        articlerepository.findById(-1L).orElseThrow(
                () -> new RuntimeException("결재 실패!")
        );
        //결과값반환
        return articlelist;
    }
}
