package com.song.blog.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    @Test
    public void 자바_객체를_JSON으로_변환() throws JsonProcessingException {
        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
        Burger burger = new Burger("맥도날드 슈비버거", 5500, ingredients);


        // 수행
        // 오브젝트매퍼가 새로운 json을 만들길 원함
        String json = objectMapper.writeValueAsString(burger);

        // 예상
        String expected = "{\"name\":\"맥도날드 슈비버거\",\"price\":5500,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";

        // 검증
        assertEquals(expected, json);
        JsonNode jsonNode = objectMapper.readTree(json); // 제이슨 결과값 이쁘게보기
        System.out.println(jsonNode.toPrettyString());

    }

    @Test
    public void JSON_을_자바_객체로_변환() throws JsonProcessingException {

        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        /*
        * { 이런 형식의 json을 만들어 보겠다
        *  "name" : "맥도널드 슈비버거"
        *  "price" : 5000,
        *   "ingredients" : ["통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스"]
        }
        */
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", "맥도날드 슈비버거");
        objectNode.put("price", 5500);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("통새우 패티");
        arrayNode.add("순쇠고기 패티");
        arrayNode.add("토마토");
        arrayNode.add("스파이시 어니언 소스");
        objectNode.set("ingredients", arrayNode);
        String json = objectNode.toString();

        // 수행
        Burger burger = objectMapper.readValue(json, Burger.class); // readVlaue(json, json으로 만들 클래스 타입)

        // 예상
        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
        Burger expected = new Burger("맥도날드 슈비버거", 5500, ingredients);

        // 검증
        assertEquals(expected.toString(), burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json); // 제이슨 결과값 이쁘게보기
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }

//    @Test
//    public void JSON_을_자바_객체로_변환() throws JsonProcessingException {
//
//        // 준비
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "{\"name\":\"맥도날드 슈비버거\",\"price\":5500,\"ingredients\":[\"통새우 패티\",\"순쇠고기 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";
//
//        // 수행
//        Burger burger = objectMapper.readValue(json, Burger.class); // readVlaue(json, json으로 만들 클래스 타입)
//
//        // 예상
//        List<String> ingredients = Arrays.asList("통새우 패티", "순쇠고기 패티", "토마토", "스파이시 어니언 소스");
//        Burger expected = new Burger("맥도날드 슈비버거", 5500, ingredients);
//
//        // 검증
//        assertEquals(expected.toString(), burger.toString());
//        JsonNode jsonNode = objectMapper.readTree(json); // 제이슨 결과값 이쁘게보기
//        System.out.println(jsonNode.toPrettyString());
//        System.out.println(burger.toString());
//    }
}