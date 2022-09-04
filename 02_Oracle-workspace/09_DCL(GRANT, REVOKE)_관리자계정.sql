/*
        < DCL : Data Control Language >
        데이터 제어 언어
        
        계정에게 시스템권한 또는 객체 접근권한을 부여 (GRANT)하거나 회수 (REVOKE) 하는 언어
        
        * 권한 부여 (GRANT)
        - 시스템권한 : 특정 DB에 접근하는 권한, 객체들을 생성할 수 있는 권한
        - 객체접근권한 : 특정 객체들에 접근해서 조작할 수 있는 권한 (DML 쓸 수 있도록)
        
        1. 시스템권한
        특정 DB에 접근하는 권한, 객체들을 생성할 수 있는 권한
        
        [ 표현법 ]
        GRANT 권한1, 권한2, .. TO 계정명;
        
        - 시스템 권한의 종류
        CREATE SESSION : 계정에 접속할 수 있는 권한
        CREATE TABLE : 테이블을 생성할 수 있는 권한
        CREATE VIEW : 뷰를 생성할 수 있는 권한
        CREATE SEQUENCE : 시퀀스를 생성할 수 있는 권한
        CREATE USER : 계정을 생성할 수 있는 권한
        ...
*/

-- 1. 권한을 부여해줄 SAMPLE 계정 생성
CREATE USER SAMPLE IDENTIFIED BY SAMPLE;
-- 계정을 만들기만 한다 해서 접속이 되지 않음

-- 2. SAMPLE 계정에 접속하기 위한 CREATE SESSION 권한만 부여
GRANT CREATE SESSION TO SAMPLE;

-- 이 시점에서 SAMPLE 계정으로 테이블 생성 시도 시
-- SAMPLE 계정에 테이블 생성 가능한 권한이 없다는 뜻의
-- ORA-01031: insufficient privileges 오류 발생

-- 3_1. SAMPLE 계정에 테이블 생성할 수 있는 CREATE TABLE 권한 부여
GRANT CREATE TABLE TO SAMPLE;
-- 이 시점에서 SAMPLE 계정으로 테이블 생성 시도 시
-- 테이블스페이스가 아직 사용불가인 상태
-- ORA-01950: no privileges on tablespace 'SYSTEM'

-- 3_2. SAMPLE 계정에 테이블스페이스를 할당해주기
-- (ALTER 구문으로 변경해줘야 함)
ALTER USER SAMPLE QUOTA 2M ON SYSTEM;
-- QUOTA : 몫, 할당하다
-- 2M : 2 MEGA BYTE
-- SYSTEM : 오라클에서 제공하는 기본 테이블스페이스명
--> 테이블 생성 가능, DML 사용 가능

-- 이 시점에서 SAMPLE 계정으로 뷰 생성 시도 시
-- ORA-01031: insufficient privileges 오류 발생
--> VIEW 객체 생성 권한 (CREATE VIEW) 가 없기 때문에

-- 4. SAMPLE 계정에 뷰를 생성할 수 있는 CREATE VIEW 권한 부여
GRANT CREATE VIEW TO SAMPLE;
--> 뷰 생성 가능


-------------------------------------------------------------------------------------------------------------
/*
        2. 객체접근권한 / 객체 권한
        특정 객체들을 조작할 수 있는 권한 (=DML 구문 : SELECT, INSERT, UPDATE, DELETE)
        
        [ 표현법 ]
        GRANT 권한종류 ON 특정객체 TO 계정명;
        
        권한 종류           |           특정객체
        ------------------------------------------
        SELECT            | TABLE, VIEW, SEQUENCE
        INSERT            | TABLE, VIEW 
        UPDATE          | TABLE, VIEW
        DELETE           | TABLE, VIEW
*/

-- 이 시점에서 SAMPLE 계정으로 KH 계정의 테이블에 접근해서 조회시
-- ORA-00942: table or view does not exist 오류 발생
--> KH 계정의 테이블에 접근해서 조회할 수 있는 권한이 없기 때문에

-- 5. SAMPLE 계정에 KH.EMPLOYEE 테이블 조회할 수 있는 권한 부여
GRANT SELECT ON KH.EMPLOYEE TO SAMPLE;

-- 이 시점에서 SAMPLE 계정에서 KH 계정의 테이블에 접근해서 행 삽입 시
-- KH 계정의 테이블에 접근해서 데이터 삽입할 수 있는 권한이 없기 때문에
-- ORA-00942: table or view does not exist 오류 발생

-- 6. SAMPLE 계정에서 KH.DEPARTMENT 테이블에 행 삽입 권한 부여
GRANT INSERT ON KH.DEPARTMENT TO SAMPLE;

-- 이 시점에서 SAMPLE 계정으로 KH.DEPARTMENT 테이블에 접근해서 조회시
-- SELECT ON KH.DEPARTMENT 권한은 부여받지 않았기 때문에
-- ORA-01031: insufficient privileges 오류 발생

-------------------------------------------------------------------------------------------------------------
-- 최소한의 권한을 부여하고자 할 때 CONNECT, RESOURCE 만 부여했었음
-- GRANT CONNECT, RESOURCE TO 계정명;

/*
        < 롤 ROLE >
        특정 권한들을 하나의 집합으로 묶어놓은 것
        
        CONNECT : CREATE SESSION (데이터베이스에 접속할 수 있는 권한)
        RESOURCE : CREATE TABLE, CREATE SEQUENCE, .. (특정 객체들을 생성 및 관리할 수 있는 권한)
*/

-- 데이터 딕셔너리를 이용하여
-- CONNECT, RESOURCE 라는 롤에 어떤 권한들이 묶여있는지 확인
SELECT *
FROM ROLE_SYS_PRIVS
WHERE ROLE IN ('CONNECT', 'RESOURCE');

-------------------------------------------------------------------------------------------------------------
/*
        * 권한 회수 (REVOKE)
        권한을 회수할 때 사용하는 명령어
        
        [ 표현법 ]
        REVOKE 권한명, 권한명, .. FROM 계정명;
*/

-- 7. SAMPLE 계정에서 테이블을 생성할 수 없도록 권한 회수
REVOKE CREATE TABLE FROM SAMPLE;


-------------------------------------------------------------------------------------------------------------
----- 실습문제 -----
-- 사용자 계정 : MYTEST / MYTEST
-- 부여할 권한 : CONNECT, RESOURCE, CREATE VIEW
CREATE USER MYTEST IDENTIFIED BY MYTEST;
GRANT CONNECT, RESOURCE, CREATE VIEW TO MYTEST;

-- 마지막으로 사용자로부터 권한을 모두 회수
-- 사용자 계정 삭제
REVOKE CONNECT, RESOURCE, CREATE VIEW FROM MYTEST;
DROP USER MYTEST;

-- 만약 데이터가 남아있는데 계정을 삭제하려고 하면 오류 발생
-- DROP USER 계정명 CASCADE; 로 삭제는 가능하지만
-- 이런 상황이 발생되지 않도록 해야한다.