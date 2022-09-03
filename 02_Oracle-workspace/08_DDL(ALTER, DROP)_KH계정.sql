/*
        < DDL : DATA DEFINITION LANGUAGE >
        데이터 정의 언어
        
        객체들을 새로이 생성 (CREATE), 수정 (ALTER), 삭제 (DROP) 하는 구문
        
        1. ALTER
        객체 구조를 수정하는 구문
        
        < 테이블 수정 >
        ALTER TABLE 테이블명 수정할내용;
        
        - 수정할내용
        1) 컬럼 추가 / 수정 / 삭제
        2) 제약조건 추가 / 삭제 => 수정은 불가 (수정하고자 한다면 삭제후 다시 추가해야 한다.)
        3) 테이블명 / 컬럼명 / 제약조건명 변경
*/

-- 1) 컬럼 추가 / 수정 / 삭제
-- 1_1) 컬럼 추가 (ADD) : ADD 추가할컬럼명 데이터타입 [DEFAULT 기본값 (생략가능)]
SELECT * FROM DEPT_COPY;

-- CNAME 컬럼 추가
ALTER TABLE DEPT_COPY ADD CNAME VARCHAR2(20);
-- 새로운 컬럼이 만들어지고 기본적으로 NULL 값으로 채워짐

-- LNAME 컬럼 추가 (DEFAULT 지정)
ALTER TABLE DEPT_COPY ADD LNAME VARCHAR2(20) DEFAULT '한국';
-- 새로운 컬럼이 만들어지고 DEFAULT 지정한대로 기본값이 '한국' 으로 채워짐

-- 1_2) 컬럼 수정 (MODIFY)
-- 데이터 타입 수정 : MODIFY 수정할컬럼명 바꿀데이터타입
-- DEFAULT 값 수정 : MODIFY 수정할컬럼명 DEFAULT 바꿀기본값

-- DEPT_ID 컬럼의 데이터타입을 CHAR(3)으로 변경
ALTER TABLE DEPT_COPY MODIFY DEPT_ID CHAR(3);

-- 단, 변경하고자 하는 컬럼에 이미 담긴 값과 완전히 다른 타입으로는 변경 불가
-- 예) 문자 -> 숫자 (X) / 문자열 사이즈 축소 (X) / 문자열 사이즈 확대 (O)
-- ALTER TABLE DEPT_COPY MODIFY DEPT_ID NUMBER;
-- ORA-01439: column to be modified must be empty to change datatype

-- DEPT_TITLE 컬럼의 데이터 타입을 VARCHAR2(40)
-- LOCATION_ID 컬럼의 데이터 타입을 VARCHAR2(2)
-- LNAME 컬럼의 기본값을 '미국'으로 바꾸기
ALTER TABLE DEPT_COPY
MODIFY DEPT_TITLE VARCHAR2(40)
MODIFY LOCATION_ID VARCHAR2(2)
MODIFY LNAME DEFAULT '미국';

-- 테스트용 테이블 생성
CREATE TABLE DEPT_COPY2
AS (SELECT * FROM DEPT_COPY);

SELECT * FROM DEPT_COPY2;

-- 1_3) 컬럼 삭제 (DROP COLUMN) : DROP COLUMN 삭제할컬럼명

-- DEPT_COPY2 로부터 DEPT_ID컬럼 지우기
ALTER TABLE DEPT_COPY2 DROP COLUMN DEPT_ID;

ROLLBACK;
-- DDL 구문은 복구 불가능 : DDL 구문 실행은 애초에 COMMIT 도 포함된 개념

SELECT * FROM DEPT_COPY2;

-- 모든 컬럼 없애기
ALTER TABLE DEPT_COPY2 DROP COLUMN DEPT_TITLE;
ALTER TABLE DEPT_COPY2 DROP COLUMN LOCATION_ID;
ALTER TABLE DEPT_COPY2 DROP COLUMN CNAME;
ALTER TABLE DEPT_COPY2 DROP COLUMN LNAME;
-- ORA-12983: cannot drop all columns in a table
-- 테이블에 최소 한 개의 컬럼은 존재해야 함 (마지막 컬럼 삭제만 오류


-- 2) 제약조건 추가 / 삭제
/*
        2_1) 제약조건 추가
        
        - PRIMARY KEY : ADD PRIMARY KEY (컬럼명)
        - FOREIGN KEY : ADD FOREIGN KEY (컬럼명) REFERENCES 참조할테이블명 (참조할컬럼명);
                                  => 참조할 컬럼명은 생략 가능
        - UNIQUE : ADD UNIQUE (컬럼명);
        - CHECK : ADD CHECK (컬럼명을 포함한 조건);
        - NOT NULL : MODIFY 컬럼명 NOT NULL;
        
        제약조건명까지 부여하고자 한다면 CONSTRAINT 제약조건명 제약조건 으로 쓴다
        CONSTRAINT 제약조건명 부분은 생략가능 (SYS_C~)
        주의 사항 : 현재 계정 내에 고유한 값으로 제약조건명을 부여해야 한다.
*/

-- DEPT_COPY 테이블의
-- DEPT_ID 컬럼에 PRIMARY KEY 제약조건 추가
-- DEPT_TITLE 컬럼에 UNIQUE 제약조건 추가
-- LNAME 컬럼에 NOT NULL 제약조건 추가
ALTER TABLE DEPT_COPY
ADD CONSTRAINT DCOPY_PK PRIMARY KEY (DEPT_ID)
ADD CONSTRAINT DCOPY_UQ UNIQUE (DEPT_TITLE)
MODIFY LNAME CONSTRAINT DCPY_NN NOT NULL;

-- 주의사항 : 이미 들어있는 값에 맞춰서 제약조건을 부여해야함

/*
        2_2) 제약조건 삭제
        
        - PRIMARY KEY / FOREIGN KEY / UNIQUE / CHECK : DROP CONSTRAINT 제약조건명
        - NOT NULL : MODIFY 컬럼명 NULL
*/

-- DCOPY_PK 제약조건 지우기
ALTER TABLE DEPT_COPY DROP CONSTRAINT DCOPY_PK;

-- DCOPY_UQ, LNAME 컬럼의 NOT NULL 제약조건 지우기
ALTER TABLE DEPT_COPY
DROP CONSTRAINT DCOPY_UQ
MODIFY LNAME NULL;


-- 3. 테이블명 / 컬럼명 / 제약조건명 변경 (RENAME)

-- 3_1) 컬럼명 변경 : RENAME COLUMN 기존컬럼명 TO 바꿀컬럼명
ALTER TABLE DEPT_COPY RENAME COLUMN DEPT_TITLE TO DEPT_NAME;
-- 테이블 내 중복된 이름으로 바꿀 수 없다.

-- 3_2) 제약조건명 변경 : RENAME CONSTRAINT 기존제약조건명 TO 바꿀제약조건명
ALTER TABLE DEPT_COPY RENAME CONSTRAINT SYS_C007139 TO DCOPY_LID_NN;

-- 3_3) 테이블명 변경 : RENAME TO 바꿀테이블명
-- >> 기존테이블명은 이미 ALTER TABLE 테이블명에서 기술되기 때문에
ALTER TABLE DEPT_COPY RENAME TO DEPT_TEST;

SELECT * FROM DEPT_COPY;
-- ORA-00942: table or view does not exist
-- 기존 이름이 잘 변경되었음

SELECT * FROM DEPT_TEST;

-->> CREATE TABLE 구문을 실행해서 테이블 만들기 이전에
--     꼼꼼하게 설계를 해서 테이블이 다 만들어지고 데이터가 다 삽입된 이후에
--     되도록이면 ALTER 구문을 사용하지 않아야 한다.


--------------------------------------------------------------------------------------------------------
/*
        2. DROP
        객체를 삭제하는 구문
        
        계정 삭제시 : DROP USER 계정명;
        테이블 삭제시 : DROP TABLE 테이블명;
*/

-- DEPT_TEST 테이블 삭제
DROP TABLE DEPT_TEST;

SELECT * FROM DEPT_TEST;
-- ORA-00942: table or view does not exist

-- DEPARTMENT 테이블
DROP TABLE DEPARTMENT;
-- ORA-02449: unique/primary keys in table referenced by foreign keys
-- UNIQUE 또는 PRIMARY KEY 제약조건이 걸린 키가 다른 테이블의 외래키에서 참조되고 있기 때문에
-->> 테이블 삭제 시, 어딘가에서 참조되고 있는 부모테이블들은 삭제되지 않는다.

-- 만약 부모테이블을 삭제하고 싶다면
-- 1. 자식테이블을 먼저 삭제 후 부모테이블 삭제
DROP TABLE 자식테이블명;
DROP TABLE 부모테이블명;

-- 2. 부모테이블만 삭제하는데 맞물린 외래키 제약조건을 함께 삭제하는 방법
DROP TABLE 부모테이블명 CASCADE CONSTRAINT;

-->> DROP 구문은 실질적으로 운영시에 쓰이기보다는
--     CREATE 구문 전에 혹시 중복된 이름의 객체가 이미 있을 것에 대비해서 삭제하는데 주로 쓰임
