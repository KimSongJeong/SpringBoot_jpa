package com.song.blog.Entity;

import com.song.blog.DTO.CommentDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 Board에 연관된다!
    @JoinColumn(name="board_id") // "boardId" 컬럼에 board의 대표값을 저장! // 이름이 Board_id로 컬럼이 생성됨
    private Board board;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDTO dto, Board board) {
        // 예외 발생
        if(dto.getId() != null) // 잘못된 아이디라면
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다."); // throw 예외처리
        if (dto.getBoardId() != board.getId()) // 가져온 아이디와 json아이디가 다를때!
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                board,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDTO dto) {

        // 1. 예외 발생
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        }


        // 2. 객체를 갱신
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }
        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}
