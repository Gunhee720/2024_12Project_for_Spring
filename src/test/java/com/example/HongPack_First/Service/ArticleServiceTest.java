package com.example.HongPack_First.Service;

import com.example.HongPack_First.Dto.ArticleForm;
import com.example.HongPack_First.Entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//INSERT INTO ARTICLE(title,content) VALUES ('강건희','안녕하세요');
//INSERT INTO ARTICLE(title,content) VALUES ('이현룡','야');
//INSERT INTO ARTICLE(title,content) VALUES ('이요한','오~');
//INSERT INTO ARTICLE(title,content) VALUES ('더미','덤덤');
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleservice;

    @Test
    void index( ) {
        //애상
        Article a = new Article(1L,"강건희","안녕하세요");
        Article b = new Article(2L,"이현룡","야");
        Article c = new Article(3L,"이요한","오~");
        Article d = new Article(4L,"더미","덤덤");
        List<Article> expected=new ArrayList<Article>(Arrays.asList(a,b,c,d));
        //실제
        List<Article> articles = articleservice.index();
        //비교
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        long id = 1L;
        //예상
        Article expected = new Article(1L,"강건희","안녕하세요");
        //실제
        Article a = articleservice.show(id);
        //비교
        assertEquals(expected.toString(),a.toString());
    }
    @Test
    void show_실패_존재하지않는_id_입력() {
        long id = -1L;
        //예상
        Article expected = null;
        //실제
        Article a = articleservice.show(id);
        //비교
        assertEquals(expected,a);
    }


    @Test
    @Transactional
    void create_성공_title과_content가_제대로입력() {
        String title = "test용 title";
        String content = "test용 content";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(5L,title,content);

        Article a = articleservice.create(dto);

        assertEquals(expected.toString(),a.toString());
    }
    @Test
    @Transactional
    void create_실패_id가_포합된입력(){
        String title = "test용 title";
        String content = "test용 content";
        ArticleForm dto = new ArticleForm(4L,title,content);
        Article expected = null;
        Article a = articleservice.create(dto);
        assertEquals(expected,a);
    }
}