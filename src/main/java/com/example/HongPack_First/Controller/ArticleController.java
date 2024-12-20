package com.example.HongPack_First.Controller;

import com.example.HongPack_First.Dto.ArticleForm;
import com.example.HongPack_First.Entity.Article;
import com.example.HongPack_First.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired //스프링 부트가 미리 만들어 놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/article/new")
    public String ArticleFrom(){
        return "articles/new";
    }

    @PostMapping("/article/create")
    public String ArticleCreate(@ModelAttribute("ArticleForm") ArticleForm form){
        System.out.println(form.toString());
        //DTO를 entity로 변환
        Article article = form.toEntity();
        System.out.println(article.toString());
        //entity를 DB로 전송
        Article saved =articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}

