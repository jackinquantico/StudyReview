DROP TABLE TB_PUBLISHER;
DROP TABLE TB_BOOK;
DROP TABLE TB_MEMBER;
DROP TABLE TB_RENT;

-- 실습 문제 --
-- 도서관리 프로그램을 만들기 위한 테이블들 만들기 --
-- 이때, 제약조건에 이름을 부여할것
-- 각 컬럼에 주석 달기
-- 단, 상단에 DROP TABLE 테이블명; 구문 같이 적을 것

-- 1. 출판사들에 대한 데이터를 담기 위한 출판사 테이블 (TB_PUBLISHER)
-- 컬럼 : PUB_NO (출판사번호) -- 기본키 (PUBLISHER_PK)
--        PUB_NAME (출판사명) -- NOT NULL (PUBLISHER_NN)
--        PHONE (출판사전화번호) -- 제약조건 없음
CREATE TABLE TB_PUBLISHER (
        PUB_NO NUMBER CONSTRAINT PUBLISHER_PK PRIMARY KEY,
        PUB_NAME VARCHAR2(30) CONSTRAINT PUBLISHER_NN NOT NULL,
        PHONE VARCHAR2(20)
);

COMMENT ON COLUMN TB_PUBLISHER.PUB_NO IS '출판사번호';
COMMENT ON COLUMN TB_PUBLISHER.PUB_NAME IS '출판사명';
COMMENT ON COLUMN TB_PUBLISHER.PHONE IS '출판사전화번호';

-- 3개 정도의 샘플 데이터 추가하기
INSERT INTO TB_PUBLISHER VALUES (1, '가나다', '02-1111-1111');
INSERT INTO TB_PUBLISHER VALUES (2, 'ABC', '031-2222-2222');
INSERT INTO TB_PUBLISHER VALUES (3, '123', '02-3333-3333');


-- 2. 도서들에 대한 데이터를 담기 위한 도서 테이블 (TB_BOOK)
-- 컬럼 : BK_NO (도서번호) -- 기본키 (BOOK_PK)
--        BK_TITLE (도서명) -- NOT NULL (BOOK_NN_TITLE)
--        BK_AUTHOR (저자명) -- NOT NULL (BOOK_NN_AUTHOR)
--        BK_PRICE (가격)
--        BK_PUB_NO (출판사번호) -- 외래키(BOOK_FK) (TB_PUBLISHER 테이블을 참조하도록)
--                                 이 때, 참조하고 있는 부모데이터 삭제 시 자식 데이터도 삭제 되도록 한다.
CREATE TABLE TB_BOOK (
        BK_NO NUMBER CONSTRAINT BOOK_PK PRIMARY KEY,
        BK_TITLE VARCHAR2(50) CONSTRAINT BOOK_NN_TITLE NOT NULL,
        BK_AUTHOR VARCHAR2(30) CONSTRAINT BOOK_NN_AUTHOR NOT NULL,
        BK_PRICE NUMBER,
        BK_PUB_NO NUMBER CONSTRAINT BOOK_FK REFERENCES TB_PUBLISHER (PUB_NO) ON DELETE CASCADE
);

COMMENT ON COLUMN TB_BOOK.BK_NO IS '도서번호';
COMMENT ON COLUMN TB_BOOK.BK_TITLE IS '도서명';
COMMENT ON COLUMN TB_BOOK.BK_AUTHOR IS '저자명';
COMMENT ON COLUMN TB_BOOK.BK_PRICE IS '가격';
COMMENT ON COLUMN TB_BOOK.BK_PUB_NO IS '출판사번호';

-- 5개 정도의 샘플 데이터 추가하기
INSERT INTO TB_BOOK VALUES (1, '수학의정석', '김수학', 8000, '3');
INSERT INTO TB_BOOK VALUES (2, '수학익힘책', '김익힘', 12000, '3');
INSERT INTO TB_BOOK VALUES (3, 'ABC 공부', 'JAMES', 20000, '2');
INSERT INTO TB_BOOK VALUES (4, '영어 노래송', 'ALICE', 10000, '2');
INSERT INTO TB_BOOK VALUES (5, '말하기듣기쓰기', '강국어', NULL, '1');

SELECT * FROM TB_BOOK;


-- 3. 회원에 대한 데이터를 담기 위한 회원 테이블 (TB_MEMBER)
-- 컬럼명 : MEMBER_NO (회원번호) -- 기본키 (MEMBER_PK)
--         MEMBER_ID (아이디) -- 중복금지 (MEMBER_UQ)
--         MEMBER_PWD (비밀번호) -- NOT NULL (MEMBER_NN_PWD)
--         MEMBER_NAME (회원명) -- NOT NULL (MEMBER_NN_NAME)
--         GENDER (성별) -- 'M' 또는 'F' 로 입력되도록 제한 (MEMBER_CK_GEN)
--         ADDRESS (주소)
--         PHONE (연락처)
--         STATUS (탈퇴여부) -- 기본값으로 'N' 으로 지정, 그리고 'Y' 혹은 'N' 으로만 입력되도록 제약조건 (MEMBER_CK_ST)
--         ENROLL_DATE (가입일) -- 기본값으로 SYSDATE, NOT NULL 제약조건 (MEMBER_NN_EN)
CREATE TABLE TB_MEMBER (
        MEMBER_NO NUMBER CONSTRAINT MEMBER_PK PRIMARY KEY,
        MEMBER_ID VARCHAR2(20) CONSTRAINT MEMBER_UQ UNIQUE,
        MEMBER_PWD VARCHAR2(20) CONSTRAINT MEMBER_NN_PWD NOT NULL,
        MEMBER_NAME VARCHAR2(20) CONSTRAINT MEMBER_NN_NAME NOT NULL,
        GENDER CHAR(1) CONSTRAINT MEMBER_CK_GEN CHECK (GENDER IN ('M', 'F')),
        ADDRESS VARCHAR2(50),
        PHONE VARCHAR2(13),
        STATUS CHAR(1) DEFAULT 'N' CONSTRAINT MEMBER_CK_ST CHECK (STATUS IN ('Y', 'N')),
        ENROLL_DATE DATE DEFAULT SYSDATE CONSTRAINT MEMBER_NN_EN NOT NULL
);

COMMENT ON COLUMN TB_MEMBER.MEMBER_NO IS '회원번호';
COMMENT ON COLUMN TB_MEMBER.MEMBER_ID IS '아이디';
COMMENT ON COLUMN TB_MEMBER.MEMBER_PWD IS '비밀번호';
COMMENT ON COLUMN TB_MEMBER.MEMBER_NAME IS '회원명';
COMMENT ON COLUMN TB_MEMBER.GENDER IS '성별';
COMMENT ON COLUMN TB_MEMBER.ADDRESS IS '주소';
COMMENT ON COLUMN TB_MEMBER.PHONE IS '연락처';
COMMENT ON COLUMN TB_MEMBER.STATUS IS '탈퇴여부';
COMMENT ON COLUMN TB_MEMBER.ENROLL_DATE IS '가입일';

-- 5개 정도의 샘플 데이터 추가하기
INSERT INTO TB_MEMBER VALUES (1, 'user01', 'pass01', '홍길동', 'M', '서울시', '010-1111-2222', DEFAULT, DEFAULT);
INSERT INTO TB_MEMBER VALUES (2, 'user02', 'pass02', '김기현', 'F', '수원시', '010-1234-1234', DEFAULT, DEFAULT);
INSERT INTO TB_MEMBER VALUES (3, 'user03', 'pass03', '강술래', 'F', '강릉시', '010-5678-5678', DEFAULT, DEFAULT);
INSERT INTO TB_MEMBER VALUES (4, 'user04', 'pass04', '데프콘', 'M', '서울시', '010-2222-3333', DEFAULT, DEFAULT);
INSERT INTO TB_MEMBER VALUES (5, 'user05', 'pass05', '유재석', 'M', '제주시', '010-3333-4444', DEFAULT, DEFAULT);

SELECT * FROM TB_MEMBER;

-- 4. 어떤 회원이 어떤 도서를 대여했는지에 대한 대여목록 테이블 (TB_RENT)
-- 컬럼 : RENT_NO (대여번호) -- 기본키 (RENT_PK)
--        RENT_MEM_NO (대여회원번호) -- 외래키 (RENT_FK_MEM) TB_MEMBER 와 참조하도록
--                                    이 때, 부모 데이터 삭제 시 자식 데이터 값이 NULL 이 되도록 옵션 설정
--        RENT_BOOK_NO (대여도서번호) -- 외래키 (RENT_FK_BOOK) TB_BOOK 와 참조하도록
--                                     이 때, 부모 데이터 삭제 시 자식 데이터 값이 NULL 값이 되도록 옵션 설정
--        RENT_DATE (대여일) -- 기본값 SYSDATE
CREATE TABLE TB_RENT (
        RENT_NO NUMBER CONSTRAINT RENT_PK PRIMARY KEY,
        RENT_MEM_NO NUMBER CONSTRAINT RENT_FK_MEM REFERENCES TB_MEMBER ON DELETE SET NULL,
        RENT_BOOK_NO NUMBER CONSTRAINT RENT_FK_BOOK REFERENCES TB_BOOK ON DELETE SET NULL,
        RENT_DATE DATE DEFAULT SYSDATE
);

COMMENT ON COLUMN TB_RENT.RENT_NO IS '대여번호';
COMMENT ON COLUMN TB_RENT.RENT_MEM_NO IS '대여회원번호';
COMMENT ON COLUMN TB_RENT.RENT_BOOK_NO IS '대여도서번호';
COMMENT ON COLUMN TB_RENT.RENT_DATE IS '대여일';

-- 3개 정도의 샘플 데이터 추가하기
INSERT INTO TB_RENT VALUES (101, 1, 1, SYSDATE);
INSERT INTO TB_RENT VALUES (102, 2, 2, SYSDATE);
INSERT INTO TB_RENT VALUES (103, 3, 3, SYSDATE);

SELECT * FROM TB_RENT;








