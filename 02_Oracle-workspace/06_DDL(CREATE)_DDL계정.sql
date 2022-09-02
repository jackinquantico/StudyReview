/*
        * DDL (DATA DEFINITION LANGUAGE)
        데이터 정의 언어
        
        오라클에서 제공하는 객체(OBJECT) 를
        새로이 만들고 (CREATE), 구조를 변경하고 (ALTER), 구조 자체를 삭제하는 (DROP) 하는 명령문
        
        오라클에서의 객체 (OBJECT) : 데이터 베이스를 이루는 구조물
                                                   테이블 (TABLE), 뷰 (VIEW), 시퀀스 (SEQUENCE), 인덱스 (INDEX),
                                                   패키지 (PACKAGE), 트리거 (TRIGGER), 프로시저 (PROCEDURE),
                                                   함수 (FUNCTION), 동의어 (SYNONYM), 사용자 (USER)
                                                   
        => 즉, 데이터베이스의 구조 자체를 정의하는 언어로
             주로 DB 관리자, 설계자가 사용함
*/

-------------------------------------------------------------------------------------------------------------
/*
        < CREATE TABLE >
        
        테이블이란
        행 (ROW), 열 (COLUMN) 로 구성되는 가장 기본적인 데이터 베이스 객체
        모든 데이터는 테이블을 통해서 저장됨 (즉, 데이터를 보관하고자 한다면 테이블을 만들어야 함)
        
        [ 표현법 ]
        CREATE TABLE 테이블명 (
                컬럼명 자료형,
                컬럼명 자료형,
                ...
                컬럼명 자료형        
        );
        
        < 자료형 >
        - 문자 : CHAR(바이트수 크기) / VARCHAR2(크기) : 크기는 BYTE 수 (숫자, 영문자, 특수문자 => 한 글자당 1BYTE,
                                                                                                           한글 => 한 글자당 3BYTE)
                  CHAR(바이트 수) : 최대 2000 BYTE 까지 지정 가능
                                              고정 길이 (아무리 적은 값이 들어와도 공백으로 채워서 처음에 할당한 크기 유지)
                                              주로 들어올 값의 글자수가 정해져 있을 경우
                                              예 ) 성별 : 남 / 여, M / F
                                                    주민등록번호 : 123456-1234567 (총 14자리)
                                                    휴대폰 번호 : 01012345678 (총 11자리)
                  VARCHAR2(바이트 수) : 최대 4000 BYTE 까지 지정 가능
                                                      가변 길이 (적은 값이 들어오면 그 담긴 값에 맞춰서 크기가 줄어든다)
                                                      주로 값의 길이가 정해지지 않은 경우
                                                      예 ) 집 주소, 아이디, 비밀번호, 이름, 게시글(내용), 댓글내용, ...
              => VAR 는 '가변'을 의미하고 2는 사이즈의 '2배'를 의미함
                 
        - 숫자 : NUMBER : 정수 / 실수 상관없이 한 자료형으로 다룬다.
        
        - 날짜 : DATE : 년월일시분초 를 모두 다 포함하는 개념
*/

-- 회원들의 데이터를 담을 수 있는 테이블 생성
-- 테이블명 : MEMBER
-- 담을 데이터들 : 아이디, 비밀번호, 이름, 회원가입일
CREATE TABLE MEMBER ( -- 테이블명이나 컬럼명 등을 작성할 경우 대소문자 구분 X => 낙타표기법 불가, 언더바로 구분
        MEMBER_ID VARCHAR2(20),  
        MEMBER_PWD VARCHAR2(20),
        MEMBER_NAME VARCHAR2(20),
        MEMBER_DATE DATE
);

SELECT * FROM MEMBER;

-------------------------------------------------------------------------------------------------------------
/*
        컬럼에 주석 달기 (컬럼에 대한 설명)
        
        [ 표현법 ]
        COMMENT ON COLUMN 테이블명.컬럼명 IS '주석내용';
*/

COMMENT ON COLUMN MEMBER.MEMBER_ID IS '회원아이디';
COMMENT ON COLUMN MEMBER.MEMBER_PWD IS '회원비밀번호';
COMMENT ON COLUMN MEMBER.MEMBER_NAME IS '회원이름';
COMMENT ON COLUMN MEMBER.MEMBER_DATE IS '회원가입일';

-------------------------------------------------------------------------------------------------------------
-- 현재 이 계정에 존재하는 테이블명, 컬럼명을 확인할 수 있는 명령어

-- 데이터 딕셔너리 : 오라클의 다양한 객체들의 정보를 저장하고 있는 시스템 테이블
--                          읽기 전용이며 데이터의 수정이 불가능하고, 데이터베이스의 운영을 위해 존재

-- USER_TABLES 현재 이 계정이 가지고 있는 테이블들의 전반적인 구조를 확인할 수 있는 데이터 딕셔너리
SELECT * FROM USER_TABLES;

-- USER_TAB_COLUMNS : 현재 이 계정이 가지고 있는 테이블들의 모든 컬럼의 정보를 조회할 수 있는 데이터 딕셔너리
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'MEMBER';
--                                                             테이블 이름이 MEMBER 인 모든 컬럼 정보 조회

SELECT * FROM MEMBER;

-- 참고 : 데이터 추가하는 구문 (DML 중 INSERT 구문 : 한 행 단위로 추가, 값의 순서를 맞춰서 작성)
-- INSERT INTO 테이블명 VALUES (값, 값, .. );
INSERT INTO MEMBER VALUES ('user01', 'pass01', '홍길동', '2021-02-01');
INSERT INTO MEMBER VALUES ('user02', 'pass02', '김말똥', '21/02/02');
INSERT INTO MEMBER VALUES ('user03', 'pass03', '박개똥', SYSDATE);

-- 지금부터 고려해야 할 것
INSERT INTO MEMBER VALUES(NULL, NULL, NULL, SYSDATE);
--> 아이디, 비밀번호, 이름에 NULL 값이 존재하면 안되는데 INSERT가 되는 형태
INSERT INTO MEMBER VALUES('user03', 'pass03', '고길동', SYSDATE);
--> 중복된 아이디는 존재해서는 안 되는데 INSERT가 되는 형태

-- 위의 NULL 값이나 중복된 아이디값은 유효하지 않은 값들이다.
-- 유효한 데이터 값을 유지하기 위해 "제약조건" 을 걸어줘야 한다.

-------------------------------------------------------------------------------------------------------------
/*
        < 제약조건 CONSTRAINTS >
        
        - 원하는 데이터값만 유지하기 위해 (보관하기 위해) 특정 컬럼마다 설정하는 제약
        => 데이터의 중복을 제거하여 데이터의 무결성을 보장할 목적
        - 제약조건이 부여된 컬럼에 들어올 데이터에 문제가 있는지 자동으로 검사할 목적
        
        - 종류 : NOT NULL (NULL값 X), UNIQUE (중복 X), CHECK (들어올 값에 조건 지정),
                    PRIMARY KEY (식별키), FOREIGN KEY (외래키)
                    
        - 컬럼에 제약 조건을 부여하는 방식 : 컬럼레벨 / 테이블레벨
*/

/*
        1. NOT NULL 제약 조건
        해당 컬럼에 반드시 값이 존재해야 할 경우 사용
        (즉, NULL 값이 절대 들어와서는 안 되는 컬럼에 부여)
        삽입 INSERT 또는 변경 UPDATE 시에도 NULL값 허용하지 않음
        
        단, NOT NULL 제약 조건을 부여할 때는 컬럼레벨 방식밖에 사용할 수 없음
*/

-- NOT NULL 제약 조건만 설정한 테이블 만들기
-- 컬럼레벨 방식 : 테이블 생성시 (컬럼명 자료형 제약조건명) => 제약조건을 부여하고자 하는 컬럼 뒤에 곧바로 기술

-- 회원들의 정보를 담을 수 있는 테이블 VER.2
-- 테이블명 : MEM_NOTNULL
-- 컬럼명 : 회원번호, 회원아이디, 회원비밀번호, 회원이름, 성별, 연락처, 이메일
CREATE TABLE MEM_NOTNULL (
        MEM_NO NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30)
);

SELECT * FROM MEM_NOTNULL;

-- 데이터 삽입
INSERT INTO MEM_NOTNULL VALUES (1, 'user01', 'pass01', '홍길동', '남', '010-1111-2222', 'aaa@naver.com');

-- NOT NULL 조건에 위배되는 값 넣기
INSERT INTO MEM_NOTNULL VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL);
-- ORA-01400: cannot insert NULL into ("DDL"."MEM_NOTNULL"."MEM_ID")
-- DDL 계정의 MEM_NOTNULL 테이블의 MEM_ID 컬럼에는 NULL 값을 넣을 수 없다
-- NOT NULL 제약 조건에 위배되어 오류 발생

INSERT INTO MEM_NOTNULL VALUES (2, 'user02', 'pass02', '김말똥', NULL, NULL, NULL);

INSERT INTO MEM_NOTNULL VALUES (3, 'user01', 'pass03', '고영희', '여', NULL, NULL);

-- NOT NULL 제약 조건이 부여되어있지 않은 컬럼은 값이 들어가든 NULL이든 상관 없음
-- NOT NULL 제약 조건이 부여된 컬럼에는 반드시 NULL 값이 아닌 값이 존재해야 한다.

-------------------------------------------------------------------------------------------------------------
/*
        2. UNIQUE 제약 조건
        컬럼에 중복값을 제한하는 제약 조건
        삽입 또는 수정 시 기존 해당 컬럼값 중 중복값이 있을 경우 추가 또는 수정이 되지 않게 제약
        
        컬럼레벨 / 테이블레벨 방식 둘 다 가능
*/

-- 회원 테이블 VER.3
-- 테이블명 : MEM_UNIQUE

-- 컬럼레벨 방식으로 UNIQUE 제약 조건 걸기
CREATE TABLE MEM_UNIQUE (
        MEM_NO NUMBER NOT NULL, 
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,        -- 띄어쓰기로 구분해서 제약 조건 여러 개 나열
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30)        
);

-- 테이블 삭제하는 명령어
DROP TABLE MEM_UNIQUE;

-- 테이블 레벨 방식으로 UNIQUE 제약조건 걸기
CREATE TABLE MEM_UNIQUE (
        MEM_NO NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        UNIQUE (MEM_ID)
);

SELECT * FROM MEM_UNIQUE;

-- 데이터 추가
INSERT INTO MEM_UNIQUE VALUES (1, 'user01', 'pass01', '홍길동', '남', '010-1111-2222', 'aaa@naver.com');
INSERT INTO MEM_UNIQUE VALUES (2, 'user02', 'pass02', '김말똥', NULL, NULL, NULL);

-- UNIQUE 컬럼에 중복된 값 삽입시 오류 발생
INSERT INTO MEM_UNIQUE VALUES (3, 'user02', 'pass03', '박개똥', NULL, NULL, NULL);
-- ORA-00001: unique constraint (DDL.SYS_C007040) violated
-- UNIQUE 제약 조건에 위배되어 INSERT 실패, 위의 오류 발생
-- 오류 구문으로 제약 조건이 위배되었음은 알려주나, 정확히 어떤 컬럼의 문제인지는 알려주지 않음
-->> 계정명.제약조건명으로 힌트를 줌

-------------------------------------------------------------------------------------------------------------
/*
        * 제약조건 부여 시 제약조건명 지정하는 표현식
        
        > 컬럼레벨 방식
        CREATE TABLE 테이블명 (
                컬럼명 자료형 CONSTRAINT 제약조건명 제약조건,
                컬럼명 자료형,
                ...
        );
        
        > 테이블레벨 방식
        CREATE TABLE 테이블명 (
                컬럼명 자료형,
                ..
                컬럼명 자료형,
                CONSTRAINT 제약조건명 제약조건 (컬럼명),
                제약조건 (컬럼명),
                ..
        );

        이 때 두 방식 모두 CONSTRAINT 제약조건명 부분은 생략 가능하다
        단, 생략시 SYS_C~ 처럼 임의의 제약조건명이 붙게 되어 오류를 파악하기 힘들다.
*/

-- 회원테이블 VER.4
-- 테이블 : MEM_CON_NM
CREATE TABLE MEM_CON_NM (
        MEM_NUM NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) CONSTRAINT MEM_NAME_NN NOT NULL, -- 컬럼레벨 방식
        GENDER CHAR(3),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        CONSTRAINT MEM_ID_UQ UNIQUE (MEM_ID) -- 테이블레벨 방식
);

-- 데이터 삽입
INSERT INTO MEM_CON_NM VALUES (1, 'user01', 'pass01', '홍길동', NULL, NULL, NULL);

-- UNIQUE의 제약조건명 지정 후 중복값 삽입
INSERT INTO MEM_CON_NM VALUES (2, 'user01', 'pass02', '김말똥', NULL, NULL, NULL);
-- ORA-00001: unique constraint (DDL.MEM_ID_UQ) violated
-->> 제약조건명이 지정한대로 나와있어 오류 발생시 어떤 컬럼에 문제가 있는지 파악하기 쉬움

SELECT * FROM MEM_CON_NM;
-- 두번째 INSERT 문은 제약조건을 위배해서 삽입되지 않음


INSERT INTO MEM_CON_NM VALUES (2, 'user02', 'pass02', '박말순', '가', NULL, NULL);
-- GENDER 컬럼은 남/여로만 입력받고 싶은데 '가' 라는 성별이 삽입됨

-------------------------------------------------------------------------------------------------------------
/*
        3. CHECK 제약 조건
        컬럼에 기록될 수 있는 값에 대한 조건을 설정해둘 수 있다.
        
        [ 표현법 ]
        CHECK (조건식)
*/

-- 회원테이블 VER.5
-- 테이블명 : MEM_CHECK
CREATE TABLE MEM_CHECK(
        MEM_NUM NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL, 
        GENDER CHAR(3) CHECK (GENDER IN ('남', '여')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE NOT NULL,
        CONSTRAINT MEM_ID_UQ2 UNIQUE (MEM_ID) -- 테이블레벨 방식
        -- ORA-02264: name already used by an existing constraint
        -->> 제약조건명도 한 계정 내의 서로 다른 테이블이더라도 중복되면 안 됨 
);

-- 데이터 삽입
INSERT INTO MEM_CHECK VALUES (1, 'user01', 'pass01', '홍길동', '남', '010-1111-2222', 'aaa@naver.com', SYSDATE);

-- GENDER 컬럼에 남/여가 아닌 값 넣기
INSERT INTO MEM_CHECK VALUES (2, 'user02', 'pass02', '김말똥', '가', NULL, NULL, SYSDATE);
-- ORA-02290: check constraint (DDL.SYS_C007057) violated
-->> CHECK 제약조건을 위배했을 경우 나타나는 오류

-- GENDER 컬럼에 NULL 넣기
INSERT INTO MEM_CHECK VALUES(3, 'user03', 'pass03', '박말순', NULL, NULL, NULL, SYSDATE);
-- NULL 값도 CHECK 제약조건에 INSERT할 수 있다
-- NULL 값을 못 들어오게 하고 싶다면 NOT NULL 제약조건도 함께 부여하면 된다.

-- 회원가입일은 항상 SYSDATE 값으로 INSERT 하고 싶다

-------------------------------------------------------------------------------------------------------------
/*
        * 특정 컬럼에 들어올 값에 대해 기본값을 설정 가능 (제약 조건은 아님)
        => DEFAULT 설정
*/

DROP TABLE MEM_CHECK;

-- MEM_CHECK 테이블 +
-- 회원가입일에 대한 DEFAULT 설정 추가한 테이블
CREATE TABLE MEM_CHECK (
        MEM_NO NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3) CHECK (GENDER IN ('남', '여')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE NOT NULL -- 명시적으로 다른 날짜를 넣어도 되지만, 작성하지 않으면 SYSDATE로 들어가게 된다
        -- DEFAULT는 제약조건이 아니기 때문에 제약조건 작성 전에 먼저 작성해야 한다.
);

SELECT * FROM MEM_CHECK;

-- 데이터 삽입
/*
        INSERT INTO 테이블명
        VALUES (값, 값, 값, ...);  => 단, 모든 컬럼에 대해 들어갈 값을 순서와 타입을 맞춰 전부 다 명시해야 한다.
*/
INSERT INTO MEM_CHECK VALUES (1, 'user01', 'pass01', '홍길동', '남', NULL, NULL, DEFAULT); 
-- DEFAULT 설정한 컬럼값 부분에 DEFAULT 키워드로 값 INSERT하면 기본값으로 설정해둔 SYSDATE 삽입됨

INSERT INTO MEM_CHECK VALUES (2, 'user02', 'pass02', '김말똥', '여', NULL, NULL, '22/08/22');
-- DEFAULT 는 제약조건이 아니기 때문에 다른 값을 넣어도 문제 없이 INSERT 된다.

INSERT INTO MEM_CHECK VALUES (3, 'user03', 'pass03', '박말순', '남', NULL, NULL);
-- SQL 오류: ORA-00947: not enough values
-->> INSERT 할 때 값의 갯수를 부족하게 제시했을 때 (값이 누락되었을 때) 발생하는 오류

/*
        INSERT INTO 테이블명 (컬럼명, 컬럼명, 컬럼명, ..)        -- 단 NOT NULL 제약조건이 걸린 컬럼은 무조건 제시해야 한다.
                            VALUES (값,       값,       값, ..      )
*/

INSERT INTO MEM_CHECK (MEM_NO, MEM_ID, MEM_PWD, MEM_NAME)
VALUES (3, 'user03', 'pass03', '박말순');
-- 값을 INSERT 하겠다고 명시적으로 지정이 안 된 컬럼에는 기본적으로 NULL 값이 들어감
-- 단, DEFAULT 값이 부여되어있다면 NULL 값이 아닌 DEFAULT 값으로 들어가게 된다.

SELECT * FROM MEM_CHECK;

-------------------------------------------------------------------------------------------------------------
/*
        4. PRIMARY KEY (기본키, 주키)
        테이블에서 각 행들의 정보를 유일하게 식별할 수 있는 컬럼에 부여하는 제약조건
        => 각 행들을 구분지을 수 있는 식별자의 역할
        예 ) 회원번호, 주민번호, 사번, 학번, 예약번호, ...
        
        식별자의 특징 상 중복되지 않고, 존재해야만 하고, 값이 변할 일이 없는 컬럼에 부여
        (NOT NULL + UNIQUE == PRIMARY KEY)
        
        단, 한 테이블 당 한 개의 컬럼에만 설정 가능
*/

-- 회원 테이블 VER.5
-- 테이블명 : MEM_PRIMARYKEY1
CREATE TABLE MEM_PRIMARYKEY1 (
        MEM_NO NUMBER CONSTRAINT MEM_PK PRIMARY KEY,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3) CHECK (GENDER IN ('남', '여')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE
        -- 테이블레벨 방식
        -- CONSTRAINT MEM_PK PRIMARY KEY (MEM_NO)
);

SELECT * FROM MEM_PRIMARYKEY1;

-- 데이터 삽입
INSERT INTO MEM_PRIMARYKEY1
VALUES (1, 'user01', 'pass01', '홍길동', '남', NULL, NULL, DEFAULT);

-- PRIMARY KEY 중복값 삽입
INSERT INTO MEM_PRIMARYKEY1
VALUES (1, 'user02', 'pass02', '김말똥', '여', NULL, NULL, DEFAULT);
-- ORA-00001: unique constraint (DDL.MEM_PK) violated 
-- 기본키 컬럼에 중복 오류

-- PRIMARY KEY에 NULL 삽입
INSERT INTO MEM_PRIMARYKEY1
VALUES (NULL, 'user02', 'pass02', '김말똥', NULL, NULL, NULL, DEFAULT);
-- ORA-01400: cannot insert NULL into ("DDL"."MEM_PRIMARYKEY1"."MEM_NO")
-- 기본키 컬럼에 NULL 값으로 인한 오류

INSERT INTO MEM_PRIMARYKEY1
VALUES (2, 'user02', 'pass02', '김말똥', NULL, NULL, NULL, DEFAULT);


-- 한 테이블에 PRIMARY KEY 제약 조건을 2개 지정
CREATE TABLE MEM_PRIMARYKEY2 (
        MEM_NO NUMBER PRIMARY KEY,
        MEM_ID VARCHAR2(20) PRIMARY KEY,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3) CHECK (GENDER IN ('남', '여')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE
);
-- ORA-02260: table can have only one primary key
-- PRIMARY KEY 가 한 테이블에 두 개 이상이 될 수 없다.


-- 예외 상황 : 단, 두 컬럼을 한 개로 묶어서 PRIMARY KEY 하나로 설정 가능
--                = 복합키

-- 복합키 생성 예시
CREATE TABLE MEM_PRIMARYKEY2 (
        MEM_NO NUMBER,
        MEM_ID VARCHAR2(20),
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3) CHECK (GENDER IN ('남', '여')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        PRIMARY KEY (MEM_NO, MEM_ID) -- 컬럼을 묶어서 PRIMARY KEY 하나로 설정 => 복합키
);

-- 데이터 삽입
INSERT INTO MEM_PRIMARYKEY2 VALUES (1, 'user01', 'pass01', '홍길동', NULL, NULL, NULL);

-- MEM_NO 를 중복시켜서 삽입
INSERT INTO MEM_PRIMARYKEY2 VALUES (1, 'user02', 'pass02', '김말똥', NULL, NULL, NULL);
-- MEM_ID 를 중복시켜서 삽입
INSERT INTO MEM_PRIMARYKEY2 VALUES (2, 'user02', 'pass02', '박말순', NULL, NULL, NULL);
-->> 두 컬럼 중 하나만 일치할 경우 중복으로 간주하지 않고 INSERT가 됨

-- 복합키의 특징 : 묶은 컬럼들에 대해 모든 값이 다 일치해야 중복으로 판별
INSERT INTO MEM_PRIMARYKEY2 VALUES (2, 'user02', 'pass03', '고영희', NULL, NULL, NULL);
-- ORA-00001: unique constraint (DDL.SYS_C007075) violated
-- UNIQUE 제약 조건 위배 오류

-- 복합키의 특징 : 묶은 컬럼들에 대해 NULL 값이 하나라도 포함되어있다면 INSERT 불가
INSERT INTO MEM_PRIMARYKEY2 VALUES (3, NULL, 'pass03', '이순신', NULL, NULL, NULL);
-- ORA-01400: cannot insert NULL into ("DDL"."MEM_PRIMARYKEY2"."MEM_ID")

SELECT * FROM MEM_PRIMARYKEY2;



-------------------------------------------------------------------------------------------------------------
/*
        5. FOREIGN KEY (외래키)
        해당 컬럼에 다른 테이블에 존재하는 값만 들어와야 하는 컬럼에 부여하는 제약조건
        => 다른 테이블을 참조한다고 표현함
            즉, 참조된 다른 테이블이 제공하고 있는 값만 들어올 수 있다.
        => FOREIGN KEY 제약조건으로 다른 테이블간의 관계를 형성할 수 있음 (JOIN)
        
        [ 표현법 ]
        - 컬럼레벨 방식
        컬럼명 자료형 CONSTRAINT 제약조건명 REFERENCES 참조할테이블명(참조할컬럼명)
        
        - 테이블레벨 방식
        CONSTRAINT 제약조건명 FOREIGN KEY (컬럼명) REFERENCES 참조할테이블명(참조할컬럼명)
        
        두 방식 모두 CONSTRAINT 제약조건명 부분은 생략 가능 (단, SYS.C~ 로 이름이 잡힘)
                          참조할 컬럼명도 생략 가능함 (단, 기본적으로 해당 참조할 테이블의 PRIMARY KEY 컬럼이 참조할 컬럼명으로 잡힘)
*/

-- 외래키 연습을 위한 테이블

-- 부모 테이블
-- 회원 등급에 대한 데이터를 보관할 테이블
-- 테이블명 : MEM_GRADE
-- 보관할 데이터들 : 등급 코드, 등급명
CREATE TABLE MEM_GRADE( 
        GRADE_CODE CHAR(2) PRIMARY KEY,
        GRADE_NAME VARCHAR2(20) NOT NULL
);

SELECT * FROM MEM_GRADE;

INSERT INTO MEM_GRADE VALUES ('G1', '일반 회원');
INSERT INTO MEM_GRADE VALUES ('G2', '우수 회원');
INSERT INTO MEM_GRADE VALUES ('G3', '특별 회원');

-- 자식테이블
-- 회원테이블 VER.6
CREATE TABLE MEM (
        MEM_NO NUMBER PRIMARY KEY,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GRADE_ID CHAR(2) REFERENCES MEM_GRADE(GRADE_CODE), -- 컬럼레벨 방식으로 외래키 지정
        GENDER CHAR(3) CHECK (GENDER IN ('남', '여')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE
        -- 테이블레벨 방식
        -- FOREIGN KEY (GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE)
);

-- 데이터 삽입
INSERT INTO MEM VALUES (1, 'user01', 'pass01', '홍길동', 'G1', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (2, 'user02', 'pass02', '김말똥', 'G2', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (3, 'user03', 'pass03', '박말순', 'G3', NULL, NULL, NULL, DEFAULT);

-- 외래키로 부모테이블에 없는 값 삽입
INSERT INTO MEM VALUES (4, 'user04', 'pass04', '고영희', 'G4', NULL, NULL, NULL, DEFAULT);
-- ORA-02291: integrity constraint (DDL.SYS_C007084) violated - parent key not found
-->> PARENT KEY NOT FOUND
-->> 부모 테이블(MEM_GRADE)의 GRADE_CODE 컬럼에 해당 값(G4)가 없어서 위의 오류가 발생

-- 외래키로 NULL 값 삽입
INSERT INTO MEM VALUES (4, 'user04', 'pass04', '고영희', NULL, NULL, NULL, NULL, DEFAULT);
-- GRADE_CODE 컬럼에 NULL 값이 잘 들어감
-->> 외래키 제약 조건이 걸려있는 컬럼에도 기본적으로 NULL값이 들어갈 수 있다.

-- 테스트를 위해 등급 정보 추가
INSERT INTO MEM_GRADE VALUES ('G4', '다이아 회원');

SELECT * FROM MEM_GRADE;

-- 주의 사항 ) 부모테이블 (MEM_GRADE) 에서 데이터값이 삭제된다면?
-- MEM_GRADE 테이블로부터 GRADE_CODE 컬럼값이 G1인 데이터 삭제
-- DELETE FROM 테이블명 WHERE 삭제하고자하는행에대한조건
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 'G1';
-- ORA-02292: integrity constraint (DDL.SYS_C007084) violated - child record found
-->> CHILD RECORD FOUND
-->> 자식 테이블 (MEM) 에서 이미 G1 값을 사용하고 있어서 삭제할 수가 없어 오류 발생

--> 자식 테이블에서 사용하고 있는 값이 있을 경우 삭제가 되지 않는 '삭제 제한 옵션'이 기본적으로 걸려있음
--> 현재 외래키 제약조건 부여 시 삭제 관련 옵션을 부여하지 않았음에도 불구하고 옵션이 걸려있음

-- MEM_GRADE 테이블로부터 GRADE_CODE 값이 G4인 데이터 지우기
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 'G4';
-- 자식 테이블에서 사용하는 값이 없어서 DELETE가 정상적으로 됨.

SELECT * FROM MEM_GRADE;

-------------------------------------------------------------------------------------------------------------
/*
        * 자식 테이블 생성 시 (외래키 제약조건 부여 시)
        부모테이블의 데이터가 삭제되었을 경우 자식테이블에서 해당되는 데이터를 어떻게 처리할 건지
        옵션으로 지정해둘 수 있다.
        
        * FOREIGN KEY 삭제 옵션
        삭제 옵션 별도로 제시하지 않으면 기본적으로 삭제 제한 옵션이 걸림
        1) ON DELETE RESTRICTED : 삭제 제한 옵션 (생략 시 기본값)
        2) ON DELETE SET NULL : 부모데이터 삭제 시 해당 데이터를 사용하는 자식데이터를 NULL 로 변경하는 옵션
        3) ON DELETE CASCADE : 부모데이터 삭제 시 해당 데이터를 사용하는 자식데이터를 같이 삭제하는 옵션
*/

-- 기존의 MEM 테이블 삭제
DROP TABLE MEM;

-- 자식테이블
-- 회원 테이블 VER.7
-- 등급을 외래키로 지정하되, 삭제 옵션을 ON DELETE SET NULL 로
CREATE TABLE MEM (
        MEM_NO NUMBER PRIMARY KEY,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GRADE_ID CHAR(2),
        GENDER CHAR(3) CHECK (GENDER IN ('남', '여')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE,
        FOREIGN KEY (GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE SET NULL
);

-- 데이터 삽입
INSERT INTO MEM VALUES (1, 'user01', 'pass01', '홍길동', 'G1', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (2, 'user02', 'pass02', '김말똥', 'G2', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (3, 'user03', 'pass03', '박말순', 'G1', NULL, NULL, NULL, DEFAULT);

-- 부모 테이블 (MEM_GRADE)로부터 GRADE_CODE 컬럼값이 G1인 데이터 삭제
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 'G1';
-- 오류 발생 없이 삭제가 잘 됨

SELECT * FROM MEM;
-- 자식테이블(MEM)에서 GRADE_ID 컬럼값이 G1이었던 부분이 모두 NULL로 바뀜

-- 기존의 MEM 테이블 삭제
DROP TABLE MEM;


-- 회원 테이블 VER.8
-- 등급을 외래키로 지정하되, 삭제 옵션을 ON DELETE CASCADE 로
CREATE TABLE MEM (
        MEM_NO NUMBER PRIMARY KEY,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GRADE_ID CHAR(2),
        GENDER CHAR(3) CHECK (GENDER IN ('남', '여')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE,
        FOREIGN KEY (GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE CASCADE
);

INSERT INTO MEM VALUES (1, 'user01', 'pass01', '홍길동', 'G3', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (2, 'user02', 'pass02', '김말똥', 'G2', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (3, 'user03', 'pass03', '박말순', 'G3', NULL, NULL, NULL, DEFAULT);

SELECT * FROM MEM;

-- 부모 테이블 (MEM_GRADE)로부터 GRADE_CODE 컬럼값이 G3인 데이터 삭제
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 'G3';
-- 문제 없이 잘 삭제됨

SELECT * FROM MEM_GRADE;

SELECT * FROM MEM;
-- 자식테이블 (MEM)의 GRADE_ID 컬럼값이 G3인 모든 행들이 함께 삭제된 것을 확인

-------------------------------------------------------------------------------------------------------------
-- 회원테이블인 MEM 로부터 전체 회원의 회원번호, 아이디, 비밀번호, 이름, 등급 아이디 조회
SELECT MEM_NO, MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID
FROM MEM;

-- 회원테이블로부터 전체 회원의 회원번호, 아이디, 비밀번호, 이름, 등급명 조회 (JOIN)
-->> 오라클 전용 구문
SELECT MEM_NO, MEM_ID, MEM_PWD, MEM_NAME, GRADE_NAME
FROM MEM, MEM_GRADE
WHERE GRADE_ID = GRADE_CODE(+);

-->> ANSI 구문
SELECT MEM_NO, MEM_ID, MEM_PWD, MEM_NAME, GRADE_NAME
FROM MEM
LEFT JOIN MEM_GRADE ON (GRADE_ID = GRADE_CODE);

/*
        굳이 외래키 제약조건이 걸려있지 않아도 JOIN 가능함
        다만, 두 컬럼에 동일한 의미의 데이터만 담겨있다면 직접 매칭해서 JOIN 가능
*/

-- 여태까지 작업한 내용 확정시키기
COMMIT;

-------------------------------------------------------------------------------------------------------------
/*
        --- 여기서부터는 KH 계정에서 실행 ---
        
        * SUBQUERY 를 이용한 테이블 생성 (테이블을 복사하는 개념)
        
        서브쿼리 : 메인 SQL문 (SELECT, CREATE, INSERT, ... )을 보조역할 하는 SELECT 문
        
        [ 표현법 ]
        CREATE TABLE 테이블명
        AS (서브쿼리);
        
        => 해당 서브쿼리를 수행한 결과로 새로이 테이블을 생성하겠다.
*/

-- EMPLOYEE 테이블을 복제한 새로운 테이블 생성 (EMPLOYEE_COPY)
CREATE TABLE EMPLOYEE_COPY
AS (SELECT * 
       FROM EMPLOYEE);
-->> 컬럼들, 조회 결과의 데이터들, 제약조건 중에서 NOT NULL 만 복제됨
-->> 나머지 제약조건들은 복제가 안 됨


-- EMPLOYEE 테이블에 있는 컬럼 구조만 가져오고 싶음 (내용물 빼고) (EMPLOYEE_COPY2)
CREATE TABLE EMPLOYEE_COPY2
AS (SELECT *
       FROM EMPLOYEE
       WHERE 1=0); -- 항상 거짓인 조건문을 가져오면 됨

SELECT * FROM EMPLOYEE_COPY2;


-- 전체 사원들 중 급여가 300만원 이상인 사원들의 사번, 이름, 부서코드, 급여 컬럼 복제 (EMPLOYEE_COPY3)
-- 1) 서브쿼리 부분 먼저
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= 3000000;

-- 2) CREATE 구문으로 테이블 복제
CREATE TABLE EMPLOYEE_COPY3
AS (SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
       FROM EMPLOYEE
       WHERE SALARY >= 3000000);

SELECT * FROM EMPLOYEE_COPY3;


-- 전체 사원의 사번, 사원명, 급여, 연봉 조회 결과를 테이블로 생성 (EMPLOYEE_COPY4)
-- 1) 서브쿼리 부분 먼저
SELECT EMP_ID, EMP_NAME, SALARY, SALARY * 12
FROM EMPLOYEE;

-- 2) CREATE 구문으로 테이블 복제
CREATE TABLE EMPLOYEE_COPY4
AS (SELECT EMP_ID, EMP_NAME, SALARY, SALARY * 12
       FROM EMPLOYEE);
-- ORA-00998: must name this expression with a column alias
-->> 서브쿼리의 SELECT 절에 산술연산, 또는 함수식이 기술된 경우 반드시 별칭 부여를 해야 한다.

CREATE TABLE EMPLOYEE_COPY4
AS (SELECT EMP_ID, EMP_NAME, SALARY, SALARY * 12 "연봉"
       FROM EMPLOYEE);

SELECT * FROM EMPLOYEE_COPY4;

-------------------------------------------------------------------------------------------------------------
/*
        * 테이블이 다 생성된 후 뒤늦게 제약조건 추가 (ALTER)
        ALTER TABLE 테이블명 + 제약조건별 구문
        
        - PRIMARY KEY : ADD PRIMARY KEY (컬럼명);
        - FOREIGN KEY : ADD FOREIGN KEY (컬럼명) REFERENCES 참조할테이블명 (참조할컬럼명);
                                    => 참조할 컬럼명 생략 가능 (PRIMARY KEY로 잡힘)
        - UNIQUE : ADD UNIQUE (컬럼명);
        - CHECK : ADD CHECK (컬럼명을 포함한 컬럼에 대한 조건);
        - NOT NULL : MODIFY 컬럼명 NOT NULL;
*/

-- EMPLOYEE_COPY 테이블에 없는 PRIMARY KEY 제약조건을 EMP_ID 컬럼에 추가
ALTER TABLE EMPLOYEE_COPY ADD PRIMARY KEY (EMP_ID);

-- EMPLOYEE 테이블에 DEPT_CODE 컬럼에 외래키 제약조건 추가 (DEPARTMENT 테이블의 DEPT_ID 참조)
ALTER TABLE EMPLOYEE ADD FOREIGN KEY (DEPT_CODE) REFERENCES DEPARTMENT (DEPT_ID);

-- EMPLOYEE 테이블에 JOB_CODE 컬럼에 외래키 제약 조건 추가 (JOB 테이블의 JOB_CODE 참조)
ALTER TABLE EMPLOYEE ADD FOREIGN KEY (JOB_CODE) REFERENCES JOB (JOB_CODE);
