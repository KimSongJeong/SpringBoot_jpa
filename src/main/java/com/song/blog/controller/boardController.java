package com.song.blog.controller;

import com.song.blog.DTO.createForm;
import com.song.blog.Entity.Board;
import com.song.blog.Repository.boardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class boardController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!!
    private boardRepository boardRepository;

    @GetMapping("/new")
    public String create() {
        return "board/new";
    }

    @PostMapping("/create")
    public String newcreate(createForm form) {


        // 1. DTO를 변환!Entity!
        Board board = form.toEntity();
//        log.info(form.toString());

        // 2. Repository에게 Entity를 DB안에 저장하게 함!!
        Board saved = boardRepository.save(board);
//        log.info(saved.toString());


        return "redirect:/board/"+saved.getId();
    }

    @GetMapping("board/{id}") // PathVariable은 url주소에있는 패스로부터 입력이 된다는 것을 뜻함
    public String show(@PathVariable Long id, Model model) {
//        log.info("id : " + id);
        // 1. id로 데이터를 가져옴
        // 데이터를 가지고오는 주체 : 레파지토리
        Board boardEntity = boardRepository.findById(id).orElse(null);
        // id값을 가져올수도 있지만, 없으면 안가져옴, id값이 없다면 null을 반환하라는 것

        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("board",boardEntity);

        // 3. 보여줄 페이지를 설정
        return "board/show";
    }

    @GetMapping("board")
    public String index(Model model) {
        // 1. 모든 board를 가져온다.
        List<Board> boardEntityList = boardRepository.findAll();

        // 2. 가져온 BOARD를 묶음으로 뷰로 전달
        model.addAttribute(boardEntityList);

        // 3. 뷰 페이지를 설정
        return "board/index";
    }

    @GetMapping("board/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 1. 수정할 데이터 가져오기
        Board boardEntity = boardRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록
        model.addAttribute("boardList", boardEntity);

        // 3. 뷰 페이지
        return "board/edit";
    }

    @PostMapping("board/update")
    public String update(createForm form) {
        log.info(form.toString());

        // 1. DTO를 엔티티로 변환
        Board board = form.toEntity();
        log.info(board.toString());

        // 2. 엔티티를 DB로 저장
        // 2-1. 기존 데이터 가져오기
        Board target = boardRepository.findById(board.getId()).orElse(null);
        // 2-2. 기존 데이터가 있다면 값을 갱신
        if (target != null) {
            boardRepository.save(board);
        }


        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/board/" + board.getId();
    }

    @GetMapping("/board/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다.");

        // 1. 삭제 대상을 가져온다
        Board target = boardRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2. 대상을 삭제한다
        if(id != null) {
            boardRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
            // addFlashAttribute는 1회성으로 사용되고 삭제되는 것, 그래서 addattribute를 사용하지 않고 사용
            // 사용하기 위해선 RedirectAttributes 매개변수로 적어주어야 함
        }

        // 3. 결과 페이지로 리다이렉트 한다
        return "redirect:/board";
    }


}
