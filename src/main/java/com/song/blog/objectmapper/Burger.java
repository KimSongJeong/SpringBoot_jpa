package com.song.blog.objectmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

// 객체를JSON으로 JSON을 객체로 연습

@AllArgsConstructor
@ToString
@Getter // 제이슨으로 변환하기위해 getter가 필요
@NoArgsConstructor // readValue가 생성될때 디폴트 생성자가 추가되기때문에 안붙이면 에러발생
public class Burger {
    private String name;
    private int price;
    private List<String> ingredients;

}
