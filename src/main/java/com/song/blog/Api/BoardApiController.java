package com.song.blog.Api;

import com.song.blog.DTO.BoardDTO;
import com.song.blog.Entity.Board;
import com.song.blog.Service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Repository와 협업 해서 데이터를 CRUD하는 공간

@RestController // restapi용 컨트롤러, 데이터 제이슨을 반환!!
@Slf4j
public class BoardApiController {

    @Autowired // DI, 생성 객체를 가져와 연결!
    private BoardService boardService;


    // GET
    @GetMapping("api/index")
    public List<Board> index() {
        return boardService.index();
    }

    // GET - 게시글 하나하나
    @GetMapping("api/index/{id}")
    public Board show(@PathVariable Long id) {
        return boardService.show(id);
    }


    // POST
    @PostMapping("api/boards")
    public ResponseEntity<Board> create(@RequestBody BoardDTO dto) { // RequestBody : json 데이터를 받아올때!!! 꼭!!!
        Board created = boardService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // PATCH
    @PatchMapping("api/board/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @RequestBody BoardDTO dto) {

        Board updated = boardService.update(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // DELETE
    @DeleteMapping("api/board/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Board deleted = boardService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build(): // body는 null인데 build로 줘도 같은 기능
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    // 트랜잭션 -> 실패 -> 폴백
    @PostMapping ("/api/test")
    public ResponseEntity<List<Board>> test(@RequestBody List<BoardDTO> dtos) {

        List<Board> createList = boardService.createBoards(dtos);
        return (createList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
