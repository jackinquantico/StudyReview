/*
        < TCL : TRANSACTION CONTROL LANGUAGE >
        트랜잭션을 제어하는 언어
        
        * 트랜잭션 (TRANSACTION)
        - 데이터베이스의 논리적 연산 단위 (절대 쿼리문 한 개의 단위가 아님)
        - DML 문을 통한 데이터의 변경사항들을 하나의 트랜잭션으로 묶어서 처리
          COMMIT (확정) 하기 전까지의 변경사항들을 하나의 트랜잭션에 담게 됨
        - 트랜잭션의 대상이 되는 SQL : INSERT, UPDATE, DELETE (DML)
        
        * TCL 의 종류
        COMMIT, ROLLBACK, SAVEPOINT
        
        [ 표현법 ]
        - COMMIT; : 하나의 트랜잭션에 담겨있는 변경사항들을 실제 DB에 반영하겠다
                           실제 DB에 변경된 내용들을 반영시키고 나서 트랜잭션은 비워짐
        - ROLLBACK; : 하나의 트랜잭션에 담겨있는 변경사항들을 실제 DB에 반영하지 않고
                               트랜잭션을 비워주는 역할 (마지막 COMMIT 시점까지만)
        - SAVEPOINT 포인트명; : 현재 이 명령어를 실행한 시점에서 
                                             해당 포인트명에 해당되는 임시저장점을 정의해두는 것
        - ROLLBACK TO 포인트명; : 전체 변경사항들을 삭제하는 것이 아니라
                                                 해당 포인트 지점까지의 트랜잭션만 롤백
*/

-- EMP_01 테이블
SELECT * FROM EMP_01;

-- 사번이 901번인 사원 삭제
DELETE
FROM EMP_01
WHERE EMP_ID = 901;

SELECT * FROM EMP_01;

-- 사번이 900번인 사원 삭제
DELETE
FROM EMP_01
WHERE EMP_ID = 900;

SELECT * FROM EMP_01;

-- 아직 DB에는 900, 901번 사원 정보가 지워지지 않은 상태
ROLLBACK;
-- 트랜잭션이 비워진 상태

SELECT * FROM EMP_01;

--------------------------------------------

-- 사번이 200번인 사원 삭제
DELETE
FROM EMP_01
WHERE EMP_ID = 200;

-- 사번 800, 이름 홍길동, 부서는 총무부인 사원 추가
INSERT INTO EMP_01 VALUES (800, '홍길동', '총무부');

SELECT * FROM EMP_01;

COMMIT;

SELECT * FROM EMP_01;
-->> SELECT 문 실행 후 다시 ROLLBACK

ROLLBACK;

SELECT * FROM EMP_01;
-->> ROLLBACK되지 않음 => DDL문은 트랜잭션에 반영되지 않음

--------------------------------------------

-- 사번이 217, 216, 214 인 사원 삭제
DELETE
FROM EMP_01
WHERE EMP_ID IN (217, 216, 214);

-- 3개의 행이 삭제된 시점에 SAVEPOINT 지정
SAVEPOINT SP;

-- 사번 801, 이름 고영희, 인사부인 사원 추가
INSERT INTO EMP_01 VALUES (801, '고영희', '인사부');
--> SAVEPOINT 이후 시점으로 쌓인 트랜잭션

SELECT * FROM EMP_01;

-- 사번이 218인 사원 삭제
DELETE
FROM EMP_01
WHERE EMP_ID = 218;

-- SP 이전까지만 롤백
ROLLBACK TO SP;

SELECT * FROM EMP_01;
-->> 801번 추가 + 218 삭제가 롤백됨

COMMIT;
-->> 217, 216, 214 삭제한 것이 DB에 적용됨 + SAVEPOINT 없어짐

--------------------------------------------

-- 사번이 900, 901 인 사원 삭제
DELETE
FROM EMP_01
WHERE EMP_ID IN (900, 901);

-- 사번이 218인 사원 삭제
DELETE
FROM EMP_01
WHERE EMP_ID = 218;

-- 테이블 생성 (DDL)
CREATE TABLE TEST (
        TID NUMBER
);

ROLLBACK;

SELECT * FROM EMP_01;
-- 900, 901, 218 사원 삭제되어있음. (롤백 안 됨)
-->> DDL 구문은 내부적으로 COMMIT 후에 작업 진행되기 때문에

/*
        DDL 구문 (CREATE, ALTER, DROP)을 실행하는 순간
        기존에 트랜잭션에 있던 모든 변경사항들을 무조건 실제 DB에 반영 (COMMIT)한 후에
        DDL 작업이 수행되는 구조
        => 즉, DDL 수행 전 항상 변경사항이 있었다면 픽스 (COMMIT/ROLLBACK) 한 뒤에 DDL 실행해야 한다.
*/



