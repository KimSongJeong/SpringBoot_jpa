package com.song.blog.Repository;


import com.song.blog.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * " +
            "FROM comment " +
            "WHERE board_id = :boardId",
            nativeQuery = true) // nativeQuery를 해줘야 해당 쿼리문이 동작을 함
    List<Comment>  findByBoardId(Long boardId);

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);

}
