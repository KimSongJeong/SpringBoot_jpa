package com.song.blog.DTO;

import com.song.blog.Entity.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class CommentDTO {

    private Long id;
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
