package com.song.blog.Api;

import com.song.blog.DTO.CommentDTO;
import com.song.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    private CommentService commentService;


    // 댓글 목록 조회
    @GetMapping("/api/boards/{boardId}/comments")
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long boardId) {

        // 서비스에게 위임
        List<CommentDTO> dtos = commentService.comments(boardId);

        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }


    // 댓글 생성

    // 댓글 수정

    // 댓글 삭제
}
