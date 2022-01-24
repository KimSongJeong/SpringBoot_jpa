package com.song.blog.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.song.blog.Entity.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class CommentDTO {

    private Long id;
    @JsonProperty("board_id") // 제이슨으로 데이터를 받아올때 board_id라는 애를 boardId로 받아올 때
    private Long boardId;
    private String nickname;
    private String body;

    public static CommentDTO createCommentDTO(Comment comment) { // static : 클래스 메소드 생성할때 사용하는 것
        return new CommentDTO(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getNickname(),
                comment.getBody()
        );

    }
}
