/*
        < DML : DATA MANIPULATION LANGUAGE >
        데이터 조작 언어
        
        테이블에 새로운 데이터를 삽입 (INSERT) 하거나, 기존의 데이터를 수정 (UPDATE) 하거나,
        삭제 (DELETE) 하는 구문
        
        + 관점에 따라 테이블로부터 데이터를 조회 (SELECT) 하는 구문도 분류될 수 있다.
*/

/*
        1. INSERT : 테이블에 새로운 행을 추가하는 구문
        
        [ 표현법 ]
        1) INSERT INTO 테이블명 VALUES (값, 값, ... );
        => 해당 테이블에 모든 컬럼에 대해 추가하고자 하는 값을 내가 직접 제시해서
             한 행 단위로 INSERT 하고자 할 때 사용
             주의할 점 : 값을 제시할 때 항상 컬럼의 순번을 지켜서 VALUES 괄호 안에 값을 나열해야 함.
             - 값의 갯수가 부족하게 제시하면 : NOT ENOUGH VALUE 오류 발생
             - 값의 갯수를 더 많이 제시하면 : TOO MANY VALUES 오류 발생
*/

INSERT INTO EMPLOYEE 
VALUES (900
              , '박말순'
              , '990115-2222222'
              , 'park_ms@kh.or.kr'
              , '01044442222'
              , 'D1'
              , 'J7'
              , 'S3'
              , 4000000
              , 0.2
              , 200
              , SYSDATE
              , NULL
              , DEFAULT );
              
SELECT * FROM EMPLOYEE;

SELECT * FROM EMPLOYEE WHERE EMP_ID = 900;

-------------------------------------------------------------------------------------------------------------------
/*
        2) INSERT INTO 테이블명 (컬럼명, 컬럼명, ..) VALUES (값, 값, ...);
        
        => 해당 테이블에 특정 컬럼값만 선택해서 그 컬럼에 추가할 값만 제시하고자 할 때 사용
             그래도 한 행 단위로 추가되기 때문에 선택되지 않은 컬럼에 대해서는 기본적으로 NULL 값이 들어감
             (단, DEFAULT 설정이 되어있던 컬럼에는 DEFAULT 값이 들어감)
             주의할 점 : NOT NULL 제약조건이 걸려있는 컬럼은 반드시 선택해서 값을 직접 제시해줘야 한다.
                             단, NOT NULL 제약조건이 걸려있더라도 DEFAULT 설정이 함께 걸려있다면
                             선택해서 값을 제시하지 않아도 된다.
*/

INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, DEPT_CODE, JOB_CODE, SAL_LEVEL, HIRE_DATE)
VALUES (901, '김말똥', '980418-1111111', 'D1', 'J2', 'S1', SYSDATE);

SELECT * FROM EMPLOYEE;

-------------------------------------------------------------------------------------------------------------------
/*
        3) INSERT INTO 테이블명 (서브쿼리);
        
        => VALUES로 값을 기입하는 것 대신에 서브쿼리로 조회한 결과값들을 전부 INSERT 하는 구문
             즉, 여러 행을 한 번에 INSERT할 수 있다.
*/

-- 테스트용 새로운 테이블 생성
CREATE TABLE EMP_01 (
        EMP_ID NUMBER,
        EMP_NAME VARCHAR2(30),
        DEPT_TITLE VARCHAR2(20)
);

-- EMPLOYEE 테이블로부터 "전체" 사원들의 사번, 이름, 부서명을 조회한 결과를
-- EMP_01 테이블에 통째로 추가
-- 1) EMPLOYEE 테이블에서 사번, 이름, 부서명 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID(+);
-- 포괄조인으로 EMPLOYEE 테이블의 모든 사원 (25개의 행) 조회

-- 2) 서브쿼리를 통해 EMP_01 테이블에 조회한 결과물을 통째로 추가
INSERT INTO EMP_01 
(SELECT EMP_ID, EMP_NAME, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID(+));
-- 25개 행 이(가) 삽입되었습니다.

SELECT * FROM EMP_01;


-------------------------------------------------------------------------------------------------------------------
/*
        2. INSERT ALL
        두 개 이상의 테이블에 각각 INSERT 할 때 사용
        이 때 사용되는 서브쿼리가 동일한 경우여야 한다.
*/

-- 테스트용 새로운 테이블 생성
-- 1번째 테이블 : 급여가 300만원 이상인 사원들의 사번, 사원명, 직급명에 대해 보관
CREATE TABLE EMP_JOB (
        EMP_ID NUMBER,
        EMP_NAME VARCHAR2(30),
        JOB_NAME VARCHAR2(20)
);

-- 2번째 테이블 : 급여가 300만원 이상인 사원들의 사번, 사원명, 부서명에 대해 보관
CREATE TABLE EMP_DEPT (
        EMP_ID NUMBER,
        EMP_NAME VARCHAR2(30),
        DEPT_TITLE VARCHAR2(20)
);

SELECT * FROM EMP_JOB;
SELECT * FROM EMP_DEPT;

-- 공통적으로 쓰일 서브쿼리
-- EMP_JOB : EMP_ID, EMP_NAME, JOB_NAME
-- EMP_DEPT : EMP_ID, EMP_NAME, DEPT_TITLE

-- 급여가 300만원 이상인 사원들의 사번, 이름, 직급명, 부서명 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, DEPT_TITLE
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY >= 3000000; -- 총 9명 조회

/*
        [ 표현법 ]
        1) INSERT ALL INTO 테이블명1 VALUES (컬럼명, 컬럼명, ..)
                               INTO 테이블명2 VALUES (컬럼명, 컬럼명, ..)
                               (서브쿼리);
*/

-- EMP_JOB : 급여가 300만원 이상인 사원들의 EMP_ID, EMP_NAME, JOB_NAME
-- EMP_DEPT : 급여가 300만원 이상인 사원들의 EMP_ID, EMP_NAME, DEPT_TITLE
INSERT ALL
INTO EMP_JOB VALUES (EMP_ID, EMP_NAME, JOB_NAME)
INTO EMP_DEPT VALUES (EMP_ID, EMP_NAME, DEPT_TITLE)
(SELECT EMP_ID, EMP_NAME, JOB_NAME, DEPT_TITLE
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY >= 3000000);
-- 18개 행 이(가) 삽입되었습니다.

SELECT * FROM EMP_JOB;
SELECT * FROM EMP_DEPT;

-------------------------------------------------------------------------------------------------------------------
-- INSERT ALL 시 조건에 따라서도 다르게 테이블에 INSERT 가능

-- 테스트를 위한 테이블 먼저 생성
-- 1번째 테이블 : 2010년도 이전에 입사한 사원들에 대해 사번, 사원명, 입사일, 급여 (EMP_OLD)
CREATE TABLE EMP_OLD AS (SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
                                                  FROM EMPLOYEE
                                                  WHERE 1 = 0);

-- 2번째 테이블 : 2010년도 이후에 입사한 사원들에 대해 사번, 사원명, 입사일, 급여 (EMP_NEW)
CREATE TABLE EMP_NEW AS (SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
                                                  FROM EMPLOYEE
                                                  WHERE 1 = 0);

-- 서브쿼리 먼저 구하기
SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
FROM EMPLOYEE
-- WHERE HIRE_DATE < '2010/01/01'; -- EMP_OLD 테이블에 INSERT하고 싶을 경우 : 9명 조회
WHERE HIRE_DATE >= '2010/01/01'; -- EMP_NEW 테이블에 INSERT 하고 싶을 경우 : 16명 조회

/*
        2) INSERT ALL
            WHEN 조건1 THEN INTO 테이블명 1 VALUES (컬럼명, 컬럼명, ..)
            WHEN 조건 2 THEN INTO 테이블명2 VALUES (컬럼명, 컬럼명, ..)            
            (서브쿼리);
*/

SELECT * FROM EMP_OLD;
SELECT * FROM EMP_NEW;

INSERT ALL
WHEN HIRE_DATE < '2010/01/01' THEN INTO EMP_OLD (EMP_ID, EMP_NAME, HIRE_DATE, SALARY) -- 9명
WHEN HIRE_DATE >= '2010/01/01' THEN INTO EMP_NEW (EMP_ID, EMP_NAME, HIRE_DATE, SALARY) --16명
(SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
FROM EMPLOYEE);
-- 25개 행 이(가) 삽입되었습니다.


-------------------------------------------------------------------------------------------------------------------
/*
        3. UPDATE 
        테이블에 기록된 기존의 데이터를 수정하는 구문
        
        [ 표현법 ]
        UPDATE 테이블명
        SET 컬럼명 = 수정값, 컬럼명 = 수정값        => SET절에는 여러 개의 컬럼값을 , 로 동시에 나열해서 변경 가능
                                                                     => = 은 대입연산자 (동등비교연산자 아님)
        WHERE 수정할행에대한조건;                    => WHERE 절은 생략 가능
                                                                    => 단 생략시 전체 모든 행의 데이터가 다 변경된다.
*/

-- 복사본 테이블 만든 후 작업
CREATE TABLE DEPT_COPY AS (SELECT * FROM DEPARTMENT);

SELECT * FROM DEPT_COPY;

-- DEPT_COPY 테이블의 D9 부서의 부서명을 총무부에서 전략기획팀으로 수정
UPDATE DEPT_COPY
SET DEPT_TITLE = '전략기획팀';
-- 만약 WHERE절을 생략할 경우 모든 행의 DEPT_TITLE 값이 전략기획팀으로 변경됨

-- 데이터 수정 사항을 되돌리고 싶을 때 사용하는 명령어
ROLLBACK;

-- D9 부서의 부서명만 전략기획팀으로 수정
UPDATE DEPT_COPY
SET DEPT_TITLE = '전략기획팀'
WHERE DEPT_ID = 'D9';

SELECT * FROM DEPT_COPY;


-- 복사본 테이블 만든 후 작업
CREATE TABLE EMP_SALARY AS (SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY, BONUS
                                                        FROM EMPLOYEE);

SELECT * FROM EMP_SALARY;

-- EMP_SALARY 테이블의 노옹철 사원의 급여를 1000만원으로 변경
UPDATE EMP_SALARY
SET SALARY = 10000000
WHERE EMP_NAME = '노옹철';

SELECT * FROM EMP_SALARY;

-- EMP_SALARY 테이블의 선동일 사원의 급여를 700만원, 보너스도 0.2로 변경
UPDATE EMP_SALARY
SET SALARY = 7000000, BONUS = 0.2
WHERE EMP_NAME = '선동일';

SELECT * FROM EMP_SALARY;

-- 전체 사원의 급여를 기존 급여에 20프로 인상한 금액으로 변경 (기존급여 * 1.2)
UPDATE EMP_SALARY
SET SALARY = SALARY * 1.2;

SELECT * FROM EMP_SALARY;

-------------------------------------------------------------------------------------------------------------------
/*
        * UPDATE 시에 서브쿼리를 사용
        서브쿼리를 수행한 결과값으로 변경한다는 의미
        
        => SET 절에 서브쿼리
        
        UPDATE 테이블명
        SET 컬럼명 = (서브쿼리)
        WHERE 조건;                   => 생략 가능, 단 모든 데이터가 일괄적으로 다 변경됨
*/

-- EMP_SALARY 테이블에 김말똥 사원의 부서코드를 선동일 부서코드로 변경
UPDATE EMP_SALARY
SET DEPT_CODE = (SELECT DEPT_CODE
                                FROM EMP_SALARY
                                WHERE EMP_NAME = '선동일')
WHERE EMP_NAME = '김말똥';

SELECT * FROM EMP_SALARY;

-- 방명수 사원의 급여와 보너스를 유재식 사원의 급여와 보너스 값으로 변경
UPDATE EMP_SALARY
SET (SALARY, BONUS) = (SELECT SALARY, BONUS
                                         FROM EMP_SALARY
                                         WHERE EMP_NAME = '유재식')
WHERE EMP_NAME = '방명수';

SELECT * FROM EMP_SALARY;

-------------------------------------------------------------------------------------------------------------------
-- UPDATE 시에도 변경할 값에 있어서 해당 컬럼에 대한 제약조건에 위배되면 안됨

SELECT * FROM EMPLOYEE;

-- 송중기 직원의 사번을 200으로 변경
UPDATE EMPLOYEE
SET EMP_ID = 200
WHERE EMP_NAME = '송종기';
-- ORA-00001: unique constraint (KH.EMPLOYEE_PK) violated
-- UNIQUE 제약조건 위배

-- 사번이 200번인 사원의 이름을 NULL로 변경
UPDATE EMPLOYEE
SET EMP_NAME = NULL
WHERE EMP_ID = 200;
-- ORA-01407: cannot update ("KH"."EMPLOYEE"."EMP_NAME") to NULL
-- NOT NULL 제약 조건 위배

-- 여태 작업한 내용 확정
COMMIT;


-------------------------------------------------------------------------------------------------------------------
/*
        4. DELETE
        테이블에 기록된 데이터를 삭제하는 구문
        
        [ 표현법 ]
        DELETE
        FROM 테이블명
        WHERE 삭제할행에대한조건;        => WHERE 절 생략 가능
                                                        => 단, 생략시 해당 테이블의 전체 행 모두 삭제
*/

SELECT * FROM EMPLOYEE;

DELETE
FROM EMPLOYEE;

ROLLBACK;

-- 김말똥과 박말순 사원의 데이터를 지우기
DELETE
FROM EMPLOYEE
WHERE EMP_NAME IN ('김말똥', '박말순');

COMMIT;

-- DEPARTMENT 테이블로부터 DEPT_ID 가 D1인 부서 삭제
DELETE
FROM DEPARTMENT
WHERE DEPT_ID = 'D1';
-- ORA-02292: integrity constraint (KH.SYS_C007115) violated - child record found
-->> EMPLOYEE 테이블에 D1을 참조하는 자식데이터가 있기 때문에 오류 발생

-- DEPARTMENT 테이블로부터 DEPT_ID 가 D3인 부서 삭제
DELETE
FROM DEPARTMENT
WHERE DEPT_ID = 'D3';
-- 삭제가 잘 이루어짐 = 자식데이터가 없기 때문

SELECT * FROM DEPARTMENT;

ROLLBACK;

-------------------------------------------------------------------------------------------------------------------
-- DROP TABLE 테이블명; : 테이블 자체를 삭제하는 구문
-- DELETE FROM 테이블명; : 테이블의 내용물을 전체 삭제 (즉, 테이블의 구조는 그대로 있음)

/*
        * TRUNCATE : 테이블의 전체 행을 삭제할 때 사용하는 구문 (절삭)
        
        [ 표현법 ]
        TRUNCATE TABLE 테이블명 ;                |               DELETE FROM 테이블명;
        ---------------------------------------------------------------------------------
        별도의 조건 제시가 불가                          |  WHERE 절을 덧붙여 특정 조건 제시가 가능
        ROLLBACK으로 복구 불가능                   |  ROLLBACK으로 복구 가능
        수행 속도가 빠름                                    |  수행속도가 느림
*/

SELECT * FROM EMP_SALARY;

DELETE
FROM EMP_SALARY;
-- 25개 행 이(가) 삭제되었습니다.

ROLLBACK;

TRUNCATE TABLE EMP_SALARY;
-- Table EMP_SALARY이(가) 잘렸습니다.

ROLLBACK;

