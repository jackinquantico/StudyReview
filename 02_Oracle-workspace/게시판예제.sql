-- BOARD 테이블 생성
DROP TABLE BOARD;

CREATE TABLE BOARD(
    BNO NUMBER PRIMARY KEY, -- 게시글 번호 -- private int bno
    TITLE VARCHAR2(50) NOT NULL, -- 게시글 제목 -- private String title
    CONTENT VARCHAR2(500) NOT NULL, -- 게시글 내용 -- private String content
    CREATE_DATE DATE DEFAULT SYSDATE, -- 게시글 작성시간 -- private Date createDate
    WRITER NUMBER, -- private String writer  -- 회원번호 / 아이디 경우 둘 다 받아올 수 있음.
    DELETE_YN CHAR(2) DEFAULT 'N', -- 삭제 여부 -- private String deleteYN -- 전체 조회 시에 DEFAULT = 'N' 인 게시글만 조회되도록
    FOREIGN KEY (WRITER) REFERENCES MEMBER(USERNO), -- MEMBER 테이블의 회원번호를 외래키로 걸어둠
    CHECK(DELETE_YN IN('Y','N'))
);

-- BOARD 테이블의 PK로 사용될 시퀀스
DROP SEQUENCE SEQ_BOARD;

CREATE SEQUENCE SEQ_BOARD;

-- 테스트 데이터 삽입
INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, '게시판 서비스를 시작합니다^^', '많은 이용 바랍니다~!', '21/01/27', 1, DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, 'JDBC 어려워요', '살려주세요', '21/09/05', 2, DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, '게시글2', '게시글2 내용은 도대체 뭘까요', '21/09/05', 2, DEFAULT);

COMMIT;


SELECT * FROM BOARD;

-- 게시글 쓰기 (추가) / 글 전체 조회 (SELECTALL) / 작성자아이디로 검색 / 게시글제목으로 검색 / 게시글 상세조회 / 게시글 수정 / 게시글 삭제(UPDATE 구문으로 Y로 바꾸기)

SELECT BNO "게시글번호", TITLE "제목", CONTENT "내용", CREATE_DATE "작성일", USERID "작성자 아이디"
FROM BOARD, MEMBER
WHERE WRITER = USERNO
ORDER BY BNO;

UPDATE BOARD
SET TITLE = 'TEST', CONTENT = 'TEST CONTENT'
WHERE BNO = 4;

UPDATE BOARD
SET DELETE_YN = 'Y'
WHERE BNO = ?;

SELECT BNO, TITLE, CONTENT, CREATE_DATE, USERID
FROM BOARD, MEMBER
WHERE WRITER = USERNO AND TITLE LIKE '%T%';

SELECT BNO, TITLE, CONTENT, CREATE_DATE, USERID
FROM BOARD, MEMBER
WHERE WRITER = USERNO
    AND WRITER = ?;

SELECT CONTENT
FROM BOARD
WHERE BNO = ?;

SELECT BNO, TITLE, CONTENT, CREATE_DATE, USERID
FROM BOARD, MEMBER
WHERE WRITER = USERNO
     AND EXTRACT(DAY FROM CREATE_DATE) = ?;
    
-- TO_CHAR, 포맷 사용해서 날짜 지정
SELECT BNO, TITLE, CONTENT, CREATE_DATE, USERID
FROM BOARD, MEMBER
WHERE WRITER = USERNO
     AND TO_CHAR(CREATE_DATE, 'YYYYMMDD') = '202109' || ?;
--  AND TO_CHAR(CREATE_dATE, 'YYYYMMDD') = '2021' || ? || ?; -- 월, 일 입력받을 때