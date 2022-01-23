package com.song.blog.Entity;

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
}
