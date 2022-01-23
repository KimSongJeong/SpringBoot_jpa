package com.song.blog.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity // 이걸 붙여줘야 DB가 해당 객체를 인식할 수 있음
@Getter
public class Board {

    @Id
    @GeneratedValue // 자동생성 1, 2,3 .....
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


}
