package com.song.blog.Service;


import com.song.blog.DTO.CommentDTO;
import com.song.blog.Entity.Board;
import com.song.blog.Entity.Comment;
import com.song.blog.Repository.BoardRepository;
import com.song.blog.Repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;



    public List<CommentDTO> comments(Long boardId) {

        // 반환
        return commentRepository.findByBoardId(boardId)
                .stream()
                .map(comment -> CommentDTO.createCommentDTO(comment)) // ->이거는 이걸로 변환한다는 것
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

    @Transactional // db의 변경이 일어날 수 있기때문에 중간에 문제가 있으면 롤백이 필요함
    public CommentDTO create(Long boardId, CommentDTO dto) {
        // 1. 게시글 조회 및 예외 발생
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다.")); // 만약에 없다면 예외를 시키겠다

        // 2. 댓글 엔티티 생성
        // 예외가 없다면
        Comment comment = Comment.createComment(dto, board);

        // 3. 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);

        // 4. DTO로 변경하여 반환 created를 그냥타입이 아닌, dto타입으로 반환
        return CommentDTO.createCommentDTO(created);
    }

    @Transactional
    public CommentDTO update(Long id, CommentDTO dto) {

        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 댓글이 없습니다."));

        // 2. 댓글 수정
        target.patch(dto);

        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);

        // 4. 댓글 ENTITY를 DTO로 변환 및 반환
        return CommentDTO.createCommentDTO(updated);

    }

    @Transactional
    public CommentDTO delete(Long id) {

        // 1. 댓글 조회(및 예외 발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다."));

        // 2. DB에서 댓글 삭제
        commentRepository.delete(target);

        // 3. 삭제 댓글을 DTO로 반환
        return CommentDTO.createCommentDTO(target);
    }
}
