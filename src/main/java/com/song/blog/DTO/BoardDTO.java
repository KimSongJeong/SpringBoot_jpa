package com.song.blog.DTO;

import com.song.blog.Entity.Board;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class BoardDTO {
    private Long id; // id 필드 추가
    private String title;
    private String content;


    public Board toEntity() { // 크리에이트폼 데이터를 리턴
        return new Board(id, title, content);
    }
}
