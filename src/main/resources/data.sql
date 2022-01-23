insert into board(id, title, content) values (1, '가가가가', '1111');
insert into board(id, title, content) values (2, '나나나나', '2222');
insert into board(id, title, content) values (3, '다다다다', '3333');

-- Board 더미 데이터
insert into board(id, title, content) values (4, '당신의 인생 영화는?', '댓글 ㄲㄱ');
insert into board(id, title, content) values (5, '안녕하세요', '댓글 ㄱ');
insert into board(id, title, content) values (6, '뉴비입니다 ㅠㅠ', '뭘봐유');

-- Comment 더미 데이터
-- 4번 게시글의 댓글들
insert into comment(id, board_id, nickname, body) values (1, 4, 'Park', '앙');
insert into comment(id, board_id, nickname, body) values (2, 4, 'kkk', 'ㅁㄴㅇ');
insert into comment(id, board_id, nickname, body) values (3, 4, '익명', '뭘봐유');

-- 5번 게시글의 댓글들
insert into comment(id, board_id, nickname, body) values (4, 5, 'Park', '뜨엥');
insert into comment(id, board_id, nickname, body) values (5, 5, 'kkk', '굳');
insert into comment(id, board_id, nickname, body) values (6, 5, '익명', '뭘봐유');

-- 6번 게시글의 댓글들
insert into comment(id, board_id, nickname, body) values (7, 6, 'Park', '허어어어어어엉');
insert into comment(id, board_id, nickname, body) values (8, 6, 'kkk', 'ㅁㄴㅇ');
insert into comment(id, board_id, nickname, body) values (9, 6, '익명', '쿠아아앙');