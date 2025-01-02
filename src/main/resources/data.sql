INSERT INTO ARTICLE(title,content) VALUES ('강건희','안녕하세요');
INSERT INTO ARTICLE(title,content) VALUES ('이현룡','야');
INSERT INTO ARTICLE(title,content) VALUES ('이요한','오~');
INSERT INTO ARTICLE(title,content) VALUES ('더미','덤덤');

--데이터 더미
INSERT INTO ARTICLE(title,content) VALUES ('당신의 영화는?','댓글로');
INSERT INTO ARTICLE(title,content) VALUES ('당신의 좋아하는 음식은?','댓글로2');
INSERT INTO ARTICLE(title,content) VALUES ('당신의 취미는?','댓글로4');

--댓글 데이터

INSERT INTO COMMENT(article_id,nickname,body) values (5,'강건희','굿 윌 헌팅');
INSERT INTO COMMENT(article_id,nickname,body) values (5,'이현룡','lalier');
INSERT INTO COMMENT(article_id,nickname,body) values (5,'이요한','파묘');

INSERT INTO COMMENT(article_id,nickname,body) values (6,'이현룡','닭가슴살');
INSERT INTO COMMENT(article_id,nickname,body) values (6,'이요한','모든거');
INSERT INTO COMMENT(article_id,nickname,body) values (6,'강건희','차돌박이');

INSERT INTO COMMENT(article_id,nickname,body) values (7,'이현룡','헬스');
INSERT INTO COMMENT(article_id,nickname,body) values (7,'이요한','게임');
INSERT INTO COMMENT(article_id,nickname,body) values (7,'강건희','러닝');