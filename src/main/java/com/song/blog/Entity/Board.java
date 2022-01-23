package com.song.blog.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity // 이걸 붙여줘야 DB가 해당 객체를 인식할 수 있음 (해당 클래스로 테이블을 만듬)
@Getter
public class Board {

    @Id
//    @GeneratedValue // 자동생성 1, 2,3 .....
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 이렇게 해주면 DB가 알아서 id를 자동 생성! 1,2,3,4... 같긴한데 알아서 다음번호 다음번호 해줌
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


}
