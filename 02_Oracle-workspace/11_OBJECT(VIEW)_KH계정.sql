/*
        < VIEW 뷰 >
        SELECT (쿼리문) 을 저장해둘 수 있는 객체
        (자주 쓰는 긴 SELECT 문을 저장해두면 긴 SELECT 문을 매번 다시 기술할 필요가 없음)
        임시테이블 같은 존재 (실제 데이터가 담겨있는 것은 아니다)
*/

------ 실습 문제 ------
-- '한국'에서 근무하는 사원들의 사번, 이름, 부서명, 급여, 근무국가명, 직급명 조회

-->> 오라클 전용 구문
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
FROM EMPLOYEE E, DEPARTMENT D, LOCATION L, NATIONAL N, JOB J
WHERE DEPT_CODE = DEPT_ID(+)
    AND LOCATION_ID = LOCAL_CODE
    AND L.NATIONAL_CODE = N.NATIONAL_CODE
    AND E.JOB_CODE = J.JOB_CODE
    AND NATIONAL_NAME = '한국';

-->> ANSI 구문
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
JOIN NATIONAL USING (NATIONAL_CODE)
WHERE NATIONAL_NAME = '한국';

/*
        1. VIEW 생성 방법
        
        [ 표현법 ]
        CREATE VIEW 뷰명
        AS (서브쿼리);
*/

-- 전체 사원들의 사번, 이름, 부서명, 급여, 근무국가명, 직급명 담은 뷰 생성
CREATE VIEW VM_EMPLOYEE
AS (SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
      FROM EMPLOYEE E, DEPARTMENT D, LOCATION L, NATIONAL N, JOB J
      WHERE DEPT_CODE = DEPT_ID(+)
          AND LOCATION_ID = LOCAL_CODE
          AND L.NATIONAL_CODE = N.NATIONAL_CODE
          AND E.JOB_CODE = J.JOB_CODE);
-- ORA-01031: insufficient privileges 오류 발생
-->> KH 계정에는 현재 VIEW 생성 권한이 없어서 오류 발생

-- 여기부터 관리자 계정으로 접속해서 실행
-- KH 계정에 CREATE VIEW 권한 부여
GRANT CREATE VIEW TO KH;

-- 다시 KH 계정으로 접속 후 작업
-- 다시 VIEW 접속 구문 실행 --> 잘 생성됨
SELECT * FROM VM_EMPLOYEE;

--> 아래의 쿼리문과 비슷한 맥락
SELECT *
FROM (SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
           FROM EMPLOYEE E, DEPARTMENT D, LOCATION L, NATIONAL N, JOB J
           WHERE DEPT_CODE = DEPT_ID(+)
               AND LOCATION_ID = LOCAL_CODE
               AND L.NATIONAL_CODE = N.NATIONAL_CODE
               AND E.JOB_CODE = J.JOB_CODE);
               
-- '한국'에서 근무하는 사원들의 사번, 사원명, 부서명, 급여, 근무국가명, 직급명 조회
SELECT *
FROM VM_EMPLOYEE
WHERE NATIONAL_NAME = '한국';

-- 복잡한 서브쿼리를 이용하여 그때그때 필요한 데이터들만 조회하는 것 보다
-- 한번 서브쿼리로 뷰를 생성 후 해당 뷰명으로 SELECT 문 이용하면 더 간단하게 조회 가능

-- '러시아'에서 근무하는 사원들만 조회
SELECT *
FROM VM_EMPLOYEE
WHERE NATIONAL_NAME = '러시아';

-- 러시아에서 근무하는 사원들의 사번, 이름, 직급명, "보너스" 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, BONUS
FROM VM_EMPLOYEE
WHERE NATIONAL_NAME = '러시아';
-- ORA-00904: "BONUS": invalid identifier
--> VM_EMPLOYEE 테이블에 BONUS 컬럼이 없기 때문에

-- 뷰에 보너스 컬럼이 없는 상태에서 보너스도 같이 조회하고 싶다면 뷰를 다시 생성해야 함

-- 이번에는 옵션을 붙여서 뷰를 다시 생성하기 : CREATE OR REPLACE VIEW 뷰명

/*
        [ 표현법 ]
        CREATE OR REPLACE VIEW 뷰명
        AS (서브쿼리);
        
        =>OR REPLACE 는 생략 가능
            뷰 생성 시, 기존에 중복된 뷰 이름이 있다면 새로이 뷰를 생성하는 게 아니라
                            해당 뷰를 변경 (갱신)하는 옵션
                            기존에 중복된 뷰 이름이 없다면 새로이 뷰를 생성함
*/

CREATE OR REPLACE VIEW VM_EMPLOYEE
AS (SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME, BONUS
       FROM EMPLOYEE E, DEPARTMENT D, LOCATION L, NATIONAL N, JOB J
       WHERE DEPT_CODE = DEPT_ID(+)
            AND LOCATION_ID = LOCAL_CODE
            AND L.NATIONAL_CODE = N.NATIONAL_CODE
            AND E.JOB_CODE = J.JOB_CODE);
            
SELECT * FROM VM_EMPLOYEE;
-->> BONUS 컬럼이 추가된 뷰로 갱신됨

SELECT EMP_ID, EMP_NAME, JOB_NAME, BONUS
FROM VM_EMPLOYEE
WHERE NATIONAL_NAME = '러시아';
-- 기존 뷰를 갱신 후 오류가 사라짐


-- 뷰는 논리적인 가상테이블 => 실질적으로 데이터를 저장하고 있지는 않음
-- (단순히 쿼리문이 TEXT 문구로 저장되어있음)
-- 참고 ) 해당 계정이 가지고 있는 VIEW 들에 대해 내용을 조회하고자 한다면
--          USER_VIEWS 데이터 딕셔너리를 조회하면 된다

SELECT * FROM USER_VIEWS;

/*
        * 뷰 컬럼에 별칭 부여
        서브쿼리의 SELECT절에 함수나 산술연산식이 기술되어있을 경우
        반드시 별칭을 지정해야 한다.
*/

-- 사원의 사번, 이름, 직급명, 성별, 근무년수를 조회할 수 있는 SELECT 문을 뷰로 정의
CREATE OR REPLACE VIEW VW_EMP_JOB
AS (SELECT EMP_ID, EMP_NAME, JOB_NAME
                    , DECODE(SUBSTR(EMP_NO, 8, 1), 1, '남', 2, '여', 3, '남', 4, '여') "성별"
                    , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) "근무년수"
        FROM EMPLOYEE
        JOIN JOB USING (JOB_CODE));
-- 별칭 붙이지 않으면 ORA-00998: must name this expression with a column alias 오류 발생
-- 별칭 붙이면 뷰 생성 성공

SELECT * FROM VW_EMP_JOB;

-- 별칭을 지정하는 다른 방법 (단, 모든 컬럼에 대해 별칭을 모두 다 기술해야 함)
CREATE OR REPLACE VIEW VW_EMP_JOB (사번, 사원명, 직급명, 성별, 근무년수)
AS (SELECT EMP_ID, EMP_NAME, JOB_NAME
                    , DECODE(SUBSTR(EMP_NO, 8, 1), 1, '남', 2, '여', 3, '남', 4, '여') 
                    , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) 
        FROM EMPLOYEE
        JOIN JOB USING (JOB_CODE));
        
SELECT * FROM VW_EMP_JOB;

SELECT 사원명, 근무년수
FROM VW_EMP_JOB;

SELECT 사원명, 직급명
FROM VW_EMP_JOB
WHERE 성별 = '여';

-- 근무년수가 20년 이상인 사원들의 모든 컬럼
SELECT *
FROM VW_EMP_JOB
WHERE 근무년수 >= 20;

-- 뷰를 삭제하고자 한다면
DROP VIEW VW_EMP_JOB;

SELECT * FROM VW_EMP_JOB;

--------------------------------------------------------------------------------------
/*
        * 생성된 뷰를 이용해서 DML (INSERT, UPDATE, DELETE) 사용 가능
        단, 뷰를 통해 변경하게 되면 실제 데이터가 담겨있는 실질적인 테이블 (베이스테이블) 에도 적용이 된다.
*/

-- 테스트용 뷰 생성
CREATE OR REPLACE VIEW VW_JOB
AS (SELECT * FROM JOB);

SELECT * FROM VW_JOB; -- 뷰
SELECT * FROM JOB; -- 베이스테이블

-- 뷰에 INSERT
INSERT INTO VW_JOB
VALUES ('J8', '인턴');

SELECT * FROM VW_JOB; -- 뷰
-- 뷰에도 잘 INSERT 되어있음
SELECT * FROM JOB; -- 베이스테이블
-- 베이스테이블에도 같이 INSERT 됨
-->> 뷰가 아니라 베이스테이블에 INSERT 된 것

-- 뷰에 UPDATE
-- JOB_CODE 가 J8인 JOB_NAME 을 알바로 UPDATE
UPDATE VW_JOB SET JOB_NAME = '알바' WHERE JOB_CODE = 'J8';

SELECT * FROM VW_JOB; -- 뷰
SELECT * FROM JOB; -- 베이스테이블
-->> 뷰가 아니라 베이스테이블이 UPDATE 된 것

-- 뷰에 DELETE
-- JOB_CODE 가 J8인 행 삭제
DELETE
FROM VW_JOB
WHERE JOB_CODE = 'J8';

SELECT * FROM VW_JOB; -- 뷰
SELECT * FROM JOB; -- 베이스테이블
-->> 베이스테이블인 JOB에서 DELETE 됨

--------------------------------------------------------------------------------------

-- 뷰에 DML이 적용 안 되는 경우

-- 테스트용 뷰 생성
CREATE OR REPLACE VIEW VW_JOB
AS (SELECT JOB_CODE FROM JOB);

SELECT * FROM VW_JOB;

-- 뷰에 INSERT => 불가
INSERT INTO VW_JOB (JOB_CODE, JOB_NAME) VALUES ('J8', '인턴');
-- SQL 오류: ORA-00904: "JOB_NAME": invalid identifier

-- 뷰에 UPDATE => 불가
UPDATE VW_JOB
SET JOB_NAME = '인턴'
WHERE JOB_CODE = 'J7';
-- SQL 오류: ORA-00904: "JOB_NAME": invalid identifier

-- 뷰에 DELETE => 불가
DELETE
FROM VW_JOB
WHERE JOB_NAME = '사원';
-- SQL 오류: ORA-00904: "JOB_NAME": invalid identifier

/*
        * 하지만 뷰에 DML 사용이 불가한 경우가 더 많음
        
        1) 뷰에 정의되어있지 않은 컬럼을 조작하는 경우
        2) 뷰에 정의되어있지 않은 컬럼 중 베이스테이블 상에 NOT NULL 제약조건이 걸려있는 경우
        3) 산술연산식 또는 함수를 통해 정의되어있는 경우
        4) 서브쿼리 부분에 그룹함수나 GROUP BY 절이 포함된 경우
        5) JOIN 을 이용해 여러 테이블을 매칭시켜서 뷰로 정의한 경우
        
        => 이런 이유로 뷰는 조회용으로 많이 쓴다.
*/

--------------------------------------------------------------------------------------

/*
        * VIEW 옵션
        
        [ 상세표현법 ]
        CREATE OR REPLACE FORCE/NOFORCE VIEW 뷰명
        AS (서브쿼리)
        WITH CHECK OPTION
        WITH READ ONLY;
        
        1) OR REPLACE : 해당 뷰명이 이미 존재하면 갱신/존재하지 않으면 새로이 생성
        2) FORCE/NOFORCE
            - FORCE : 서브쿼리에 기술된 테이블이 존재하지 않더라도 뷰 생성
            - NOFORCE : 서브쿼리에 기술된 테이블이 반드시 존재해야 뷰 생성 (생략 시 기본값)
        3) WITH CHECK OPTION : 서브쿼리의 조건절 (WHERE절) 에 기술된 내용에 만족하는 값으로만 DML 가능
                                                조건에 부합하지 않는 값을 수정하는 경우 오류 발생
        4) WITH READ ONLY : 뷰에 대해 조회만 가능 (INSERT, UPDATE, DELETE 수행 불가)
*/

-- 2) FORCE / NOFORCE
CREATE OR REPLACE /* NOFORCE */ VIEW VW_TEST
AS (SELECT * FROM TT);
-- ORA-00942: table or view does not exist
--> TT라는 테이블이 존재하지 않기 때문에

CREATE OR REPLACE FORCE VIEW VW_TEST
AS (SELECT * FROM TT);
-- 경고: 컴파일 오류와 함께 뷰가 생성되었습니다.
--> 실행은 되지만 경고가 뜸

SELECT * FROM VW_TEST;
-- 생성은 되지만, 원본 테이블이 없기 때문에 이용은 불가능한 상태

-- 원본테이블을 이 시점에서 생성해주면 이용이 가능함
CREATE TABLE TT (
        TCODE NUMBER,
        TNAME VARCHAR2(20),
        TCONTENT VARCHAR2(50)
);

-- 이 시점에서 다시 VIEW 조회시 이용 가능
SELECT * FROM VW_TEST;


-- 3) WITH CHECK OPTION
CREATE OR REPLACE VIEW VW_EMP
AS (SELECT * FROM EMPLOYEE WHERE SALARY >= 3000000)
WITH CHECK OPTION;

SELECT * FROM VW_EMP;
-- VW_EMP 뷰에는 현재 급여가 300만원 이상인 사원들의 정보만 보여진다.

UPDATE VW_EMP
SET SALARY = 2000000
WHERE EMP_ID = 200;
-- ORA-01402: view WITH CHECK OPTION where-clause violation
--> 서브쿼리에 기술한 조건에 부합하지 않기 때문에 값 변경 불가

UPDATE VW_EMP
SET SALARY = 4000000
WHERE EMP_ID = 200;
-- 서브쿼리에 기술한 조건에 부합하기 때문에 값 변경 가능

ROLLBACK;


-- 4) WITH READ ONLY
CREATE OR REPLACE VIEW VW_EMPBONUS
AS (SELECT EMP_ID, EMP_NAME, BONUS
       FROM EMPLOYEE
       WHERE BONUS IS NOT NULL)
WITH READ ONLY;

SELECT * FROM VW_EMPBONUS;

DELETE
FROM VW_EMPBONUS
WHERE EMP_ID = 204;
-- SQL 오류: ORA-42399: cannot perform a DML operation on a read-only view
--> READ ONLY 뷰에서는 DML 구문을 수행할 수 없다.
