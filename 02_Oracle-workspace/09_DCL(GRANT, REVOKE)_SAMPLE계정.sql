-- CREATE SESSION 권한을 받아 계정에 접속 가능한 상태


-- CREATE TABLE 권한 부여 받기 전
-- 테이블 생성 시도
CREATE TABLE TEST (
        TEST_ID NUMBER
);
-- ORA-01031: insufficient privileges
--> 권한 불충분


-- CREATE TABLE 권한 부여 받은 후
CREATE TABLE TEST (
        TEST_ID NUMBER
);
-- ORA-01950: no privileges on tablespace 'SYSTEM'
--> 테이블스페이스(테이블들이 모여있는 공간)이 할당되지 않아
--   테이블스페이스가 아직 사용불가인 상태


-- TABLE SPACE 할당받은 후
CREATE TABLE TEST (
        TEST_ID NUMBER
);
-- 테이블 생성 성공

-- 테이블 생성 권한 (CREATE TABLE) 을 부여받게 되면
-- 기본적으로는 해당 계정이 소유하고 있는 테이블을 조작하는 것도 가능해짐
-->> DML 문 사용 가능
SELECT * FROM TEST;
-- RESULT SET  출력 잘 됨
INSERT INTO TEST VALUES (1);
-- INSERT 문으로 컬럼값 삽입도 가능


-- 뷰 만들어 보기
-- CREATE VIEW 권한 부여 받기 전
CREATE VIEW V_TEST
AS (SELECT * FROM TEST);
-- ORA-01031: insufficient privileges
--> VIEW 객체 생성 권한 (CREATE VIEW) 가 없기 때문에


-- CREATE VIEW 권한 부여 받은 후
CREATE VIEW V_TEST
AS (SELECT * FROM TEST);
-- 뷰 생성 성공

SELECT * FROM V_TEST;


-------------------------------------------------------------------------------------------------------------

-- SAMPLE 계정에서 KH 계정의 테이블에 접근해서 조회
SELECT * FROM KH.EMPLOYEE;
-- KH 계정의 테이블에 접근해서 조회할 수 있는 권한이 없기 때문에
-- ORA-00942: table or view does not exist


-- SAMPLE 계정에 SELECT ON KH.EMPLOYEE 권한 부여 후
SELECT * FROM KH.EMPLOYEE;
-- KH.EMPLOYEE 테이블 조회 성공


-- SAMPLE 계정에서 DEPARTMENT 테이블 접근해서 조회하면
SELECT * FROM KH.DEPARTMENT;
-- DEPARTMENT 테이블에 접근해서 조회할 수 있는 권한이 없기 때문에
-- ORA-00942: table or view does not exist 오류 발생


-- SAMPLE 계정에서 KH 계정의 테이블에 접근해서 행 삽입해보기
INSERT INTO KH.DEPARTMENT  VALUES ('D0', '회계부', 'L2');
-- KH 계정의 테이블에 접근해서 데이터 삽입할 수 있는 권한이 없기 때문에
-- ORA-00942: table or view does not exist 오류 발생


-- SAMPLE 계정에 INSERT ON KH.DEPARTMENT 권한 부여 후
INSERT INTO KH.DEPARTMENT  VALUES ('D0', '회계부', 'L2');
-- KH.DEPARTMENT 테이블에 행 INSERT 성공


SELECT * FROM KH.DEPARTMENT;
-- SELECT ON KH.DEPARTMENT 권한은 부여받지 않았기 때문에
-- ORA-01031: insufficient privileges 오류 발생


-- 이 시점에서 테이블 확인 시 INSERT 내용이 반영되지 않음
-- COMMIT을 해야 내용이 반영됨.
COMMIT;


-------------------------------------------------------------------------------------------------------------
-- SAMPLE 계정에서 테이블을 생성할 수 없도록 권한 회수 후

-- 테이블 만들기 시도
CREATE TABLE TEST2 (
        TEST_ID NUMBER,
        TEST_TITLE VARCHAR2(50)
);
-- SAMPLE 계정에서 CREATE TABLE 권한을 회수했기 때문에
-- ORA-01031: insufficient privileges


CREATE VIEW V_TEST2
AS (SELECT * FROM TEST WHERE 1=0);
-- 테이블 생성권한만 회수했고 VIEW 생성권한은 회수하지 않았기 때문에
-- VIEW 는 잘 생성이 되는 것을 확인할 수 있음.
