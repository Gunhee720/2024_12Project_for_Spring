package com.example.HongPack_First.Repository;

import com.example.HongPack_First.Entity.Article;
import com.example.HongPack_First.Entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //repository test니까 이렇게
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 조회")
    void findByAuthor() {
        //입력 데이터 조회
        Long articleId = 5L;

        //실제 수행
        List<Comment> comments = commentRepository.findByAuthor(articleId);
        //예상하기
        Article article = new Article(5L,"당신의 영화는?","댓글로");
        Comment a = new Comment(1L,article,"강건희","굿 윌 헌팅");
        Comment b = new Comment(2L,article,"이현룡","lalier");
        Comment c = new Comment(3L,article,"이요한","파묘");
        List<Comment> expected = Arrays.asList(a,b,c);

        //검증
        assertEquals(expected.toString(),comments.toString());
    }

    @Test
    void findByNickname() {
        String nickname = "이요한";
        List<Comment> comments = commentRepository.findByNickname(nickname);
        //예상

        Comment a = new Comment(3L,new Article(5L,"당신의 영화는?","댓글로"),"이요한","파묘");
        Comment b = new Comment(5L,new Article(6L,"당신의 좋아하는 음식은?","댓글로2"),"이요한","모든거");
        Comment c = new Comment(8L,new Article(7L,"당신의 취미는?","댓글로4"),"이요한","게임");
        List<Comment> expected = Arrays.asList(a,b,c);
        //검증
        assertEquals(expected.toString(),comments.toString());




    }
}