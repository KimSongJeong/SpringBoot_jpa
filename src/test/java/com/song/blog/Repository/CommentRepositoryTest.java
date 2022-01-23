package com.song.blog.Repository;

import com.song.blog.Entity.Board;
import com.song.blog.Entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트!
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회") // 따로 메소드의 이름을 바꾸지않고 이렇게 해주면 됨
    void findByBoardId() {
        // case 1: 4번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long boardId = 4L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByBoardId(boardId);

            // 예상
            Board expected = null;
            Board board = new Board(4L, "당신의 인생 영화는?", "댓글 ㄲㄱ");
            Comment a = new Comment(1L, board, "Park", "앙");
            Comment b = new Comment(2L, board, "kkk", "ㅁㄴㅇ");
            Comment c = new Comment(3L, board, "익명", "뭘봐유");
            List<Comment> ex = Arrays.asList(a, b, c);

            // 검증
            assertEquals(ex.toString(), comments.toString(), "4번 글의 모든 댓글 조회");
        }

        // case 2: 1번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long boardId = 4L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByBoardId(boardId);

            // 예상
            Board expected = null;
            Board board = new Board(1L, "가가가가", "1111");
            List<Comment> ex = Arrays.asList();

            // 검증
            assertEquals(ex.toString(), comments.toString(), "1번 글에는 댓글이 없음");
        }
        // case 3: 9번 게시글의 모든 댓ㄷ글 조회

        // case 4: 9999번 게시글의 댓글 조회

        // case 5 : -1번 게시글의 모든 댓글 조ㅓ회
    }
    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // case 1 : "Park"의 모든 댓글 조회
        {
            // 입력 데이터를 준비
            String nickname="Park";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상하기
            Comment a = new Comment(1L, new Board(4L, "당신의 인생 영화는?", "댓글 ㄲㄱ"), nickname, "");
            Comment b = new Comment(4L, new Board(5L, "안녕하세요", "댓글 ㄱ"), nickname, "뜨엥");
            Comment c = new Comment(7L, new Board(6L, "뉴비입니다 ㅠㅠ", "뭘봐유"), nickname, "허어어어어어엉");
            List<Comment> ex = Arrays.asList(a, b, c);

            // 검증
            assertEquals(ex.toString(), comments.toString());


        }
    }
}