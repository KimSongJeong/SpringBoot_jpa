package com.song.blog.Api;

import com.song.blog.DTO.CommentDTO;
import com.song.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;


    // 댓글 목록 조회
    @GetMapping("/api/boards/{boardId}/comments")
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long boardId) {

        // 1. 서비스에게 위임
        // 서비스를 호출
        List<CommentDTO> dtos = commentService.comments(boardId);

        // 2. 결과 응답(이번에는 무조건 잘될것이라 생각하고 ok만)
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 댓글 생성
    @PostMapping("/api/boards/{boardId}/comments")
    public ResponseEntity<CommentDTO> create(@PathVariable Long boardId,
                                             @RequestBody CommentDTO dto) {

        // 서비스에게 위임
        CommentDTO createDto = commentService.create(boardId, dto); // 얘가 소속된 아이디, 입력된 내용을 서비스에 보내준당

        // 결과 응답
        // 문제 발생 시, 내부에서 에러를 발생시키기 위해 OK만
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long id,
                                             @RequestBody CommentDTO dto) {
        // 서비스에게 위임
        CommentDTO updateDto = commentService.update(id, dto); // 얘가 소속된 아이디, 입력된 내용을 서비스에 보내준당

        // 결과 응답
        // 문제 발생 시, 내부에서 에러를 발생시키기 위해 OK만
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDTO> delete(@PathVariable Long id) {
        // 서비스에게 위임
        CommentDTO updateDto = commentService.delete(id); // 얘가 소속된 아이디, 입력된 내용을 서비스에 보내준당

        // 결과 응답
        // 문제 발생 시, 내부에서 에러를 발생시키기 위해 OK만
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }
}
