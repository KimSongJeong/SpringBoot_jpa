package com.song.blog.Service;


import com.song.blog.DTO.CommentDTO;
import com.song.blog.Entity.Comment;
import com.song.blog.Repository.BoardRepository;
import com.song.blog.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;



    public List<CommentDTO> comments(Long boardId) {

        return commentRepository.findByBoardId(boardId)
                .stream()
                .map(comment -> CommentDTO.createCommentDTO(comment))
                .collect(Collectors.toList());


        // for문 방식
//        // 조회 : 댓글 목록
//        List<Comment> comments = commentRepository.findByBoardId(boardId);
//        // 변환 : 엔티티 -> DTO 형태로
//        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDTO dto = CommentDTO.createCommentDTO(c);
//            dtos.add(dto);
//        }
//
//        // 반환
//        return dtos;
    }
}
