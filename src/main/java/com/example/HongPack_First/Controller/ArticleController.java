package com.example.HongPack_First.Controller;

import com.example.HongPack_First.Dto.ArticleForm;
import com.example.HongPack_First.Entity.Article;
import com.example.HongPack_First.Repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j //로깅을 위한 (어노테이션)
public class ArticleController {

    @Autowired //스프링 부트가 미리 만들어 놓은 객체를 가져다가 자동 연결
    private ArticleRepository articleRepository;

    @GetMapping("/article/new")
    public String ArticleForm(){
        return "articles/new";
    }

    @PostMapping("/article/create")
    public String ArticleCreate(@ModelAttribute("ArticleForm") ArticleForm form){
        log.info("form : {}",form);
        //DTO를 entity로 변환
        Article article = form.toEntity();
        log.info("article : {}",article);
        //entity를 DB로 전송
        Article saved =articleRepository.save(article);
        log.info("saved : {}",saved);
        return "redirect:/article/"+saved.getId();
    }

    @GetMapping("/article/{id}")
    public String ArticleRead(@PathVariable Long id, Model model){
        log.info("id = {}",id);
        //id로 data를 가져옴
        Article ArticleEntity_byid = articleRepository.findById(id).orElse(null);
        log.info("myid = {}", ArticleEntity_byid);
        //가져온 데이터를 model에 등록
        model.addAttribute("article", ArticleEntity_byid);
        //보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String ArticleList(Model model){
        //모든 article을 가져온다
        List<Article> ArticleList = articleRepository.findAll();
        log.info("myAritcleList = {}", ArticleList);
        //가져온 article들을 묶어서 뷰로 보냄
        model.addAttribute("articles", ArticleList);
        //뷰페이지
        return "articles/list";
    }

    @GetMapping("/articles/{id}/edit")
    public String ArticleEdit(@PathVariable Long id, Model model){
        Article ArticleEntity_byid = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", ArticleEntity_byid);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String ArticleUpdate(@ModelAttribute("Article") ArticleForm form){
        log.info("form : {}",form);
        Article myarticle = form.toEntity();
        Article oldArticle = articleRepository.findById(myarticle.getId()).orElse(null);
        if(oldArticle !=null){
            Article saved = articleRepository.save(myarticle);
            log.info("saved : {}",saved);
        }
        return "redirect:/article/"+myarticle.getId();

    }
    @GetMapping("/articles/{id}/delete")
    public String ArticleDelete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Article ArticleEntity_byid = articleRepository.findById(id).orElse(null);
        log.info("article : {}",ArticleEntity_byid);
        if(ArticleEntity_byid !=null){
            articleRepository.delete(ArticleEntity_byid);
            log.info("삭제처리완료");
            redirectAttributes.addFlashAttribute("msg","삭제가 완료되었습니다.");
        }
        return "redirect:/articles";
    }

}

