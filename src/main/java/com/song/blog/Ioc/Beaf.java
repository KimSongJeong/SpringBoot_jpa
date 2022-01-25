package com.song.blog.Ioc;

public class Beaf extends  Ingredient{

    public Beaf(String name) {
        super(name);
    }
}


// DI 주입 전
//package com.song.blog.Ioc;
//
//public class Beaf {
//    private String name;
//
//    public Beaf(String name) {
//        this.name = name;
//    }
//
//    public String getName() {
//        return name;
//    }
//}
