package com.song.blog.Service;

import com.song.blog.DTO.BoardDTO;
import com.song.blog.Entity.Board;
import com.song.blog.Repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언(서비스 객체를 스프링부트에 생성하는 것)
@Slf4j
public class BoardService {

    @Autowired // 레파지토리와 협업할 수 있도록 불러와주기
    private BoardRepository boardRepository;


    public List<Board> index() {
        // 데이터는 레파지토리 보조 요리사한태 가져온다!
        return boardRepository.findAll();
    }

    public Board show(Long id) {
        return boardRepository.findById(id).orElse(null);
    }


    public Board create(BoardDTO dto) {
        // DTO를 받았으면 DTO를 ENTITY로 바꿔야징
        Board board = dto.toEntity();

        // 여기서 이미 있는 아이디번호로 보내면 그 아이디에 있는 데이터에 덮어씌어지는 오류가 발생함
        // post는 생성이지 바뀌면 안됨, 이를 방지하기 위해 if문 추가
        if(board.getId() != null) {
            return null;
        }

        return boardRepository.save(board);
    }

    public Board update(Long id, BoardDTO dto) {
        // 1. 수정용 엔티티 생성 (dto 엔티티 변환)
        Board board = dto.toEntity();
        log.info("id : {}, board : {}", id, board.toString());

        // 2. 대상 엔티티를 조회
        Board target = boardRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target == null || id != board.getId()) {
            // 잘못된 요청이라는 400번 요청 응답!
            log.info("잘못된 요청! id : {}, board : {}", id, board.toString());
            return null;
        }


        // 4. 업데이트
        target.patch(board); // 새롭게 있는 것만 붙여주는 것
        Board updated = boardRepository.save(target);
        return updated;

    }

    public Board delete(Long id) {
        // 대상 찾기
        Board target = boardRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null){
            return null;
        }

        // null이 아닐 경우
        // 대상 삭제
        boardRepository.delete(target);
        return target;
    }


    @Transactional // 해당 메소드를 트랜잭션으로 묶는다! 즉, 실패하면 이전상태로 롤백!
    public List<Board> createBoards(List<BoardDTO> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Board> boardList= dtos.stream()
                        .map(dto -> dto.toEntity())
                        .collect(Collectors.toList());

        // entity묶음을 db로 저장
        boardList.stream()
                .forEach(board -> boardRepository.save(board));

        // 강제 예외 발생 시키기
        boardRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")
        );

        // 결과값 반환
        return boardList;
    }


}
