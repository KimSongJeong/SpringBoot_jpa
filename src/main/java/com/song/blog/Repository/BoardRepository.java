package com.song.blog.Repository;

import com.song.blog.Entity.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BoardRepository extends CrudRepository<Board, Long> { // 레파지토리를 그냥 만들면 힘드니 기존에 만들어져 있는걸 상속받아오자!
                                                                        // 두가지의 엔티티를 넣어주어야 함, (관리대상, 관리대상의 대표 타입)  Id는 Long타입이니


    // 우리가 사용해야하는 findAll을 crud레파지토리에서 가져와야 함
    // 마우스 오른쪽 - Generate... - findAll
    // 어레이리스트 타입으로 받아오기위해 앞에 기재
    @Override
    ArrayList<Board> findAll();
}
