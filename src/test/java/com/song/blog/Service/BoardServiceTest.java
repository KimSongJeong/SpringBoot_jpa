package com.song.blog.Service;

import com.song.blog.DTO.BoardDTO;
import com.song.blog.Entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스는 스프링부트와 연동되어 테스팅 된다!
class BoardServiceTest {

    @Autowired BoardService boardService;


    @Test // 해당메소드가 테스트라는 것을 알려주는 어노테이션
    void index() {
        // 예상 시나리오
        Board a = new Board(1L, "가가가가","1111");
        Board b = new Board(2L, "나나나나","2222");
        Board c = new Board(3L, "다다다다","3333");
        List<Board> expected = new ArrayList<Board>(Arrays.asList(a, b, c));

        // 실제 결과
        List<Board> boards = boardService.index();

        // 비교
        assertEquals(expected.toString(), boards.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 예상
        Long id = 1L;
        Board expected = new Board(id, "가가가가", "1111");

        // 실제
        Board board = boardService.show(id);

        // 비교
        assertEquals(expected.toString(), board.toString());
    }
    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 예상
        Long id = -1L;
        Board expected = null;

        // 실제
        Board board = boardService.show(id);

        // 비교, null인 값은 toString을 쓸수없음
        assertEquals(expected, board);
    }

    @Test
    @Transactional // 이 테스트가 끝나면 롤백될 수 있도록
    void create_성공__title과_content만_있는_dto입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        BoardDTO dto = new BoardDTO(null, title, content);
        Board expected = new Board(4L, title, content);


        // 실제
        Board board = boardService.create(dto);

        // 비교, null인 값은 toString을 쓸수없음
        assertEquals(expected.toString(), board.toString());
    }

    @Test
    @Transactional // 이 테스트가 끝나면 롤백될 수 있도록
    void create_실패__id가_포함된_dto_입력() {
        // 예상
        String title = "라라라라";
        String content = "4444";
        BoardDTO dto = new BoardDTO(4L, title, content);
        Board expected = null;


        // 실제
        Board board = boardService.create(dto);

        // 비교, null인 값은 toString을 쓸수없음
        assertEquals(expected, board);
    }

    @Test
    @Transactional
    void update_성공__존재하는id와title_content가있는dto입력() {
        // 예상
        Long id = 1L;
        String title = "라라라라";
        String content = "4444";
        BoardDTO dto = new BoardDTO(id, title, content);
        Board expected = null;


        // 실제
        Board board = boardService.update(id, dto);

        // 비교, null인 값은 toString을 쓸수없음
        assertEquals(expected.toString(), board.toString());
    }

    @Test
    @Transactional
    void update_성공__존재하는id와title_만있는dto입력() {
    }

    @Test
    @Transactional
    void update_실패__존재하지않는id의dto입력() {
    }

    @Test
    @Transactional
    void update_실패__id만있는dto입력() {
    }

    @Test
    @Transactional
    void delete_성공__존재하는_id입력() {
    }

    @Test
    @Transactional
    void delete_실패__존재하지않는_id입력() {
    }

}