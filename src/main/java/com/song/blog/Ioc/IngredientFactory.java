package com.song.blog.Ioc;


import org.springframework.stereotype.Component;

@Component // 해당 클래스를 캑체를 만들고, 이를 Ioc 컨테이너에 등록하는 것
// Ioc 삽입 후에 만든 어노테이션
public class IngredientFactory {// 재료 공간

    public Ingredient get(String menu) {
        switch (menu) {
            case"돈가스":
                return new Pork("한돈 등심");
            case"스테이크":
                return new Beaf("한우 꽃등심");
            case "크리스피 치킨":
                return new chicken("국내산 10호 닭");
            default:
                return null;
        }
    }
}
