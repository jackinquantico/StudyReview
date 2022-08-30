/*
    < JOIN >
    
    두 개 이상의 테이블에서 데이터를 같이 조회하고자 할 때 사용되는 구문
    조회 결과는 하나의 결과물 (RESULT SET)로 나옴
    
    관계형 데이터베이스에서는 최소한의 데이터로 각각의 테이블에 데이터를 보관하고 있다
    (중복을 최소화하기 위해서 최대한 쪼개서 관리)
    => 즉, JOIN 구문을 이용해서 여러 테이블 간의 관계를 맺어 같이 조회해야 함
    => 단, 무작정 JOIN 구문을 작성해서 조회를 하는 게 아니라
        테이블간 연결고리에 해당하는 컬럼(외래키)를 매칭시켜서 JOIN 해줘야 함
        
    JOIN 은 크게 오라클 전용 구문과 ANSI(미국 국립 표준 협회) 구문으로 나뉜다.
    
                        오라클 전용구문                    |                   ANSI 구문
    =================================================================
                            등가조인                          |         내부 조인 (INNER JOIN)  => JOIN USING / ON
                        (EQUAL JOIN)                    |         외부 조인 (OUTER JOIN) 
   -------------------------------------------------------------------------------------------------------
                            포괄조인                         |
                     (LEFT OUTER)                      |          왼쪽 외부 조인 (LEFT OUTER JOIN)
                     (RIGHT OUTER)                    |         오른쪽 외부 조인 (RIGHT OTHER JOIN)
                                                                |          전체 외부 조인 (FULL OTHER JOIN) : 오라클에서는 불가
   -------------------------------------------------------------------------------------------------------
                    카테시안 곱
   -------------------------------------------------------------------------------------------------------
*/

-- 전체 사원들의 사번, 사원명, 부서코드, 부서명
SELECT EMP_ID, EMP_NAME, DEPT_CODE -- DEPT_CODE = DEPT_ID 컬럼
FROM EMPLOYEE;

SELECT DEPT_ID, DEPT_TITLE -- DEPT_ID = DEPT_CODE 컬럼
FROM DEPARTMENT;

-- 오라클 버전
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;

-- 전체 사원들의 사번, 사원명, 직급코드, 직급명 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE
FROM EMPLOYEE;

SELECT JOB_CODE, JOB_NAME
FROM JOB;

-- 오라클 버전
SELECT EMP_ID, EMP_NAME, E.JOB_CODE, JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE;

--> 조인을 통해 연결 고리를 잘 매칭시키면 하나의 결과물로 조회 가능

-------------------------------------------------------------------------------------------------------
/*
        1. 등가조인 (EQUAL JOIN) / 내부조인 (INNER JOIN)
        연결시키려는 컬럼의 값들이 일치하는 행들만 조인되서 조회
        ( == 일치하지 않는 값들은 조회에서 제외)
*/

-->> 오라클 전용 구문
-- FROM 절에 조회하고자 하는 테이블들을 나열 (, 로)
-- ex) FROM EMPLOYEE, JOB
-- WHERE 절에 매칭시킬 컬럼명(외래키)에 대한 조건 제시

-- 전체 사원들의 사번, 사원명, 부서코드, 부서명 같이 조회
-- 1) 연결할 컬럼명이 서로 다른 경우
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;
-- 일치하지 않는 값은 조회에서 제외되어 23명 중 21명만 조회됨
-- (DEPT_CODE 가 NULL이었던 2명의 데이터는 조회가 안 됨)
-- (DEPT_ID 가 D3, D4, D7인 부서의 부서명이 조회 안 됨 = EMPLOYEE 테이블 컬럼에 존재하지 않아서)

-- 전체 사원들의 사번, 사원명, 직급코드, 직급명 조회
-- 2) 연결할 컬럼명이 서로 같은 경우
-- SELECT EMP_ID, EMP_NAME, JOB_ID, JOB_NAME
-- FROM EMPLOYEE, JOB
-- WHERE JOB_ID = JOB_ID;
-- 에러 : AMBIGUOUSLY DEFINED : 컬럼이 모호하다
--> 어떤 테이블의 어느 컬림인지 확실히 명시해줘야 한다.

-- 해결방법 1) 테이블명 이용하는 방법 (테이블명.컬럼명)
SELECT EMP_ID, EMP_NAME, EMPLOYEE.JOB_CODE, JOB.JOB_CODE, JOB_NAME
FROM EMPLOYEE, JOB
WHERE EMPLOYEE.JOB_CODE = JOB.JOB_CODE;

-- 해결방법 2) 테이블에 별칭을 붙여서 별칭을 이용하는 방법 (각 테이블마다 별칭 부여 가능, 별칭.컬럼명)
SELECT EMP_ID, EMP_NAME, J.JOB_CODE, JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE;


-->> ANSI 구문
-- FROM 절에 기준이 될 테이블명 하나만 명시한 뒤
-- 그 뒤에 JOIN 절에서 같이 조회하고자 하는 테이블 명 기술, 또한 매칭시킬 컬럼에 대한 조건도 같이 기술
-- ( USING / ON 구문)

-- 전체 사원들의 사번, 사원명, 부서코드, 부서명 같이 조회
-- 1) 연결할 컬럼명이 서로 다른 경우 : ON 구문만 가능
-- JOIN 테이블명 ON (조건식)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE -- 기준 테이블
INNER JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
-- INNER JOIN에서 INNER를 생략하고 적어도 됨

-- 전체 사원들의 사번, 사원명, 직급코드, 직급명 조회
-- 2) 연결할 컬럼명이 서로 같은 경우 : ON 구문, USING 구문 둘 다 가능
-- JOIN 테이블명 USING(컬럼명) 또는 JOIN 테이블명 ON(조건식)

-- 2_1) ON 구문 이용 : AMBIGUOUSLY 오류가 발생할 수 있기 때문에 테이블명/별칭 명시해야 함.
SELECT EMP_ID, EMP_NAME, EMPLOYEE.JOB_CODE, JOB_NAME
FROM EMPLOYEE
JOIN JOB ON (EMPLOYEE.JOB_CODE = JOB.JOB_CODE);
-- 또는
SELECT EMP_ID, EMP_NAME, E.JOB_CODE, JOB_NAME
FROM EMPLOYEE E
JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE);

-- 2_2) USING 구문 이용 : AMBIGUOUSLY 오류가 발생 X
--                                    동일한 컬럼명 하나만 잘 적어주면 알아서 매칭시켜줌
SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE);

-- [ 참고 ] 위의 예시는 NATURAL JOIN (자연조인)으로도 가능
SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
FROM EMPLOYEE
NATURAL JOIN JOB;
-- 두 개의 테이블만 제시한 상태 + 각 테이블에 위치한 칼럼명이 유일하게 한 개 존재 (JOB_CODE)

--> ON구문과 USING 구문을 다 합친 버전
SELECT EMP_NAME, JOB_CODE, JOB_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE) 
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

-- 추가적인 조건도 제시 가능
-- 직급이 대리인 사원들의 정보 조회 (사번, 사원명, 급여, 직급명)

-->> 오라클 전용 구문
SELECT EMP_ID, EMP_NAME, SALARY, JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE   -- 연결 고리에 대한 조건
     AND J.JOB_NAME = '대리';     -- 추가적인 조건
-- 협업 시 가독성을 위해 조건은 한 줄씩 띄어쓰기, 들여쓰기 해서 쓰기

-->> ANSI 구문
SELECT EMP_ID, EMP_NAME, SALARY, JOB_NAME
FROM EMPLOYEE
-- JOIN JOB USING (JOB_CODE)                                            -- USING 구문
JOIN JOB ON(EMPLOYEE.JOB_CODE = JOB.JOB_CODE)       -- ON 구문
WHERE JOB_NAME = '대리';

-------------------------------------------------------------------------------------------------------

-- 실습 문제 --
-- 1. 부서가 '인사관리부'인 사원들의 사번, 사원명, 보너스 조회
-->> 오라클 전용 구문
SELECT EMP_ID, EMP_NAME, BONUS
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
     AND DEPT_TITLE = '인사관리부'; 

-->> ANSI 구문
SELECT EMP_ID, EMP_NAME, BONUS
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE DEPT_TITLE = '인사관리부';

-- 2. 부서가 '총무부'가 아닌 사원들의 사원명, 급여, 입사일 조회
-->> 오라클 전용 구문
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
    AND DEPT_TITLE != '총무부';

-->> ANSI 구문
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE DEPT_TITLE != '총무부';

-- 3. 보너스를 받는 사원들의 사번, 사원명, 보너스, 부서명 조회
-->> 오라클 전용 구문
SELECT EMP_ID, EMP_NAME, BONUS, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
    AND BONUS IS NOT NULL;
--> DEPT_CODE가 NULL이지만, 보너스는 NULL이 아닌 사원이 누락되는 이슈

-->> ANSI 구문
SELECT EMP_ID, EMP_NAME, BONUS, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE BONUS IS NOT NULL;

-- 4. 아래의 두 테이블을 참고하여 부서코드, 부서명, 지역코드, 지역명 (LOCAL_NAME) 조회
SELECT * FROM DEPARTMENT; -- LOCATION_ID
SELECT * FROM LOCATION; -- LOCAL_CODE

-->> 오라클 전용 구문
SELECT DEPT_ID, DEPT_TITLE, LOCAL_CODE, LOCAL_NAME
FROM DEPARTMENT, LOCATION
WHERE LOCATION_ID = LOCAL_CODE;

-->> ANSI 구문
SELECT DEPT_ID, DEPT_TITLE, LOCATION_ID, LOCAL_NAME
FROM DEPARTMENT
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);

-------------------------------------------------------------------------------------------------------
/*
    2. 포괄조인 / 왼쪽, 오른쪽, 전체 외부조인
    테이블 간에 JOIN 시 일치하지 않은 행도 포함시켜서 조회 가능
    단, 반드시 LEFT / RIGHT 의 기준 테이블을 지정해야함
    
    => 일단 일치하는 것들 조회 + 기준테이블에서 누락됐던 것들 함께 조회
*/

-- "전체" 사원들의 사원명, 급여, 부서명 조회
-- 오라클 전용 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;

-- ANSI 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
--> DEPT_CODE가 NULL인 두 명의 사원 정보가 조회되지 않음.
--> EMPLOYEE 테이블에 존재하지 않는 부서 정보도 조회되지 않음. (부서에 배정된 사람이 없는 부서) 
---->> 기본적으로 내부조인(등가조인)은 일치하는 것만 조회하기 때문에 위의 두 항목이 조회되지 않음.


-- 1) LEFT OUTER JOIN : 두 테이블 중 왼쪽에 기술된 테이블을 기준으로 JOIN
--                                   즉, 어떤 컬럼이든 왼편에 기술된 테이블의 데이터는 무조건 한번씩 조회한다 (일치하는 것을 찾지 못하더라도)

-->> ANSI 구문
-- FROM 기준테이블명 LEFT OUTER JOIN 테이블명 ON/USING구문 (OUTER 키워드는 생략 가능)
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE
LEFT OUTER JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
--> ON 구문에 기술된 조건문에 해당하는 부분을 다 조회 + 기준 테이블에서 누락된 정보도 한번씩 다 조회
-- 기준 테이블에 존재한 데이터는 컬럼값이 뭐가 되었든 간에 조회되게끔 하겠다.

-->> 오라클 전용 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID(+);
-- 기준 테이블이 아닌 반대 테이블 컬럼명에 (+) 기호를 붙여준다.


-- 2) RIGHT OUTER JOIN : 두 테이블 중 오른편에 기술된 테이블을 기준으로 JOIN
--                                      어떤 컬럼이든 오른편에 기술된 테이블의 데이터는 무조건 한번씩 조회한다 (일치하는 것을 찾지 못하더라도)

-->> ANSI 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE
RIGHT OUTER JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID); -- DEPARTMENT 기준
-- RIGHT JOIN 으로 생략 가능
-- (같은 내용을 LEFT JOIN 버전으로 바꾸면)
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM DEPARTMENT
LEFT JOIN EMPLOYEE ON (DEPT_CODE = DEPT_ID);

-->> 오라클 전용 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE (+) = DEPT_ID;

-- LEFT /RIGHT OUTER JOIN의 결과는 일단 등가조인의 결과로 공통적인 것들을 한번씩 다 조회하고 난 후
-- 기준이 되는 테이블에서 누락된 정보를 추가적으로 조회


-- 3) FULL OUTER JOIN : 전체 외부조인, 두 테이블이 가진 모든 행을 조회할 수 있도록 JOIN
--                                   (오라클 전용 구문에서는 사용 불가)

-->> ANSI 구문
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE
FULL OUTER JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
-- 순서를 바꿔써도 동일한 결과
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM DEPARTMENT
FULL OUTER JOIN EMPLOYEE ON (DEPT_CODE = DEPT_ID);
--> 일치하는 내용물 조회 + 왼쪽 테이블에서 누락된 것 조회 + 오른쪽 테이블에서 누락된 것 조회

-->> 오라클 전용 구문 사용시 오류 발생 ( only one outer-joined table )
/*
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE (+) = DEPT_ID (+);
*/

-------------------------------------------------------------------------------------------------------
/*
    3. 카테시안 곱 (CARTESIAN PRODUCT) / 교차조인 (CROSS JOIN)
    모든 테이블의 각 행들이 서로서로 맵핑된 결과가 조회됨 (곱집합) : 모든 경우의 수 출력
    두 테이블의 행들이 모두 곱해진 행들의 조합이 모두 다 출력됨 => 방대한 데이터 출력 => 과부하의 위험
    
    => 조인 조건이 잘못되었거나 누락되었을 경우 발생하기 때문에 주의해야 한다.
    
    예) 각각 N개, M개 행을 가진 테이블의 카테시안 곱 결과물은 N * M개의 행이 나옴
*/

-- 사원명, 부서명 조회
-->> 오라클 전용 구문
SELECT EMP_NAME, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT; --> 23행 * 9행 = 207개 행이 조회됨

-->> ANSI 구문
SELECT EMP_NAME, DEPT_TITLE
FROM EMPLOYEE
CROSS JOIN DEPARTMENT;

-------------------------------------------------------------------------------------------------------
/*
    4. 비등가 조인 (NON EQUAL JOIN)
    
    '=' (동등비교연산자) 를 사용하지 않는 조인문
    지정한 컬럼값이 일치하는 경우가 아닌 범위에 포함되는 경우에 매칭시켜서 조회한다는 뜻
*/

-- 사원명, 급여, 급여등급(EMPLOYEE)
SELECT EMP_NAME, SALARY, SAL_LEVEL
FROM EMPLOYEE;

-- SAL_GRADE 조회
SELECT *
FROM SAL_GRADE;

-- 사원명, 급여, 급여등급(EMPLOYEE)
-->> 오라클 전용 구문
SELECT EMP_NAME, SALARY, S.SAL_LEVEL
FROM SAL_GRADE S, EMPLOYEE
-- WHERE SALARY >= MIN_SAL AND SALARY <= MAX_SAL; -- 연결고리에 대한 조건
WHERE SALARY BETWEEN MIN_SAL AND MAX_SAL;

-->> ANSI 구문 (ON 구문만 사용 가능)
SELECT EMP_NAME, SALARY, S.SAL_LEVEL
FROM EMPLOYEE
JOIN SAL_GRADE S ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);
--                              ON (SALARY >= MIN_SAL AND SALARY <= MAX_SAL )

-------------------------------------------------------------------------------------------------------
/*
    5. 자체조인 (SELF JOIN)
    같은 테이블을 다시 한 번 조인하는 경우
    즉, 자기 자신의 테이블과 다시 조인을 맺는 경우
*/

-- 사원의 사번, 사원명, 사원 급여, 사수의 사번
SELECT EMP_ID "사원의 사번"
            , EMP_NAME "사원명"
            , SALARY "사원 급여"
            , MANAGER_ID "사수의 사번"
FROM EMPLOYEE;

-- 사원의 사번, 사원명, 사원의 부서코드, 사원의 급여
-- 사수의 사번, 사수명, 사수의 부서코드, 사수의 급여

-- 사원 정보, 사수 정보를 각각 도출해낼 테이블의 별칭 붙이기
SELECT * FROM EMPLOYEE E; -- 사원 정보 도출용 테이블 : E => 연결고리 컬럼 : MANAGER_ID
SELECT * FROM EMPLOYEE M; -- 사수 정보 도출용 테이블 : M => 연결고리 컬럼 : EMP_ID

SELECT E.EMP_ID "사원의 사번", E.EMP_NAME "사원명", E.DEPT_CODE "사원의 부서코드", E.SALARY "사원의 급여"
            , M.EMP_ID "사수의 사번", M.EMP_NAME "사수명", M.DEPT_CODE "사수의 부서코드", M.SALARY "사수의 급여"
FROM EMPLOYEE E, EMPLOYEE M
-- WHERE E.MANAGER_ID = M.EMP_ID;
-- 등가조인에 대한 조건을 제시했을 때는 사수 정보가 NULL이 아닌 사람만 가져오게 됨.
WHERE E.MANAGER_ID = M.EMP_ID(+);
-- 포괄조인에 대한 조건을 제시했을 때 (NULL인 경우) 도 포함해서 조회된다.

-->> ANSI 구문
SELECT E.EMP_ID "사원의 사번", E.EMP_NAME "사원명", E.DEPT_CODE "사원의 부서코드", E.SALARY "사원의 급여"
            , M.EMP_ID "사수의 사번", M.EMP_NAME "사수명", M.DEPT_CODE "사수의 부서코드", M.SALARY "사수의 급여"
FROM EMPLOYEE E
-- 내부조인 구문 작성시 사수 정보가 NULL이 아닌 사람만 가져오게 됨
-- JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID);
-- 왼쪽 외부조인 구문 작성시 NULL인 경우도 포함해서 조회됨
LEFT JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID);

-------------------------------------------------------------------------------------------------------
/*
    < 다중 JOIN >
    
    3 개 이상의 테이블을 조인
*/

-- 사번, 사원명, 부서명, 직급명
SELECT * FROM EMPLOYEE;      -- DEPT_CODE        JOB_CODE
SELECT * FROM DEPARTMENT; -- DEPT_CODE
SELECT * FROM JOB;                 --                            JOB_CODE

-->> 오라클 전용 구문
SELECT EMP_ID "사번"
            , EMP_NAME "사원명"
            , DEPT_TITLE "부서명"
            , JOB_NAME "직급명"
FROM EMPLOYEE E, JOB J, DEPARTMENT
-- WHERE DEPT_CODE = DEPT_ID 로 등가조인 부여할 경우 : 부서배치 안 된 (NULL인) 사원 조회 안 됨
WHERE DEPT_CODE = DEPT_ID(+)                -- EMPLOYEE 와 DEPARTMENT 포괄 조인 : NULL 포함 조회
     AND E.JOB_CODE = J.JOB_CODE;              -- EMPLOYEE 와 JOB 등가 조인
--  AND JOB_CODE = JOB_CODE 로 기술하면 AMBIGUOUSLY 에러 발생

-- ANSI 구문
SELECT EMP_ID "사번"
            , EMP_NAME "사원명"
            , DEPT_TITLE "부서명"
            , JOB_NAME "직급명"
FROM EMPLOYEE
-- JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID) 로 내부조인시 NULL 값 포함 안 됨
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)  -- LEFT OUTER JOIN : NULL 값 포함
         JOIN JOB USING (JOB_CODE);


-- 사번, 사원명, 부서명, 직급명, 근무지역명 조회 (LOCAL_NAME)
SELECT * FROM EMPLOYEE;      -- DEPT_CODE        JOB_CODE       
SELECT * FROM DEPARTMENT; -- DEPT_CODE                                  LOCATION _ID
SELECT * FROM JOB;                 --                            JOB_CODE
SELECT * FROM LOCATION;     --                                                      LOCAL_CODE

-->> 오라클 전용 구문
SELECT EMP_ID 사번
            , EMP_NAME 사원명
            , DEPT_TITLE 부서명
            , JOB_NAME 직급명
            , LOCAL_NAME 근무지역명
FROM EMPLOYEE E, DEPARTMENT, JOB J, LOCATION
WHERE DEPT_CODE = DEPT_ID(+)
    AND E.JOB_CODE = J.JOB_CODE
    AND LOCATION_ID = LOCAL_CODE;

-->> ANSI 구문
SELECT EMP_ID 사번
            , EMP_NAME 사원명
            , DEPT_TITLE 부서명
            , JOB_NAME 직급명
            , LOCAL_NAME 근무지역명
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE); -- 사실상 DEPARTMENT 와 조인하는 개념이므로,
                                                                                     -- DEPARTMENT 아래에 있어야 조인이 성립됨.
--> 다중 JOIN 시, ANSI 구문을 이용할 경우 테이블명을 기술하는 순서가 중요
--(ex) LOCATION 테이블이 DEPARTMENT 테이블보다 먼저 조인되면 EMPLOYEE 테이블과 조인하는 꼴이므로
--       EMPLOYEE 테이블에 LOCATION_ID 컬럼이 없어서 오류 발생하게 됨)

-- 사원명, 부서명, 직급명, 근무지역명, 근무국가명, 급여등급(SAL_GRADE테이블)
SELECT * FROM EMPLOYEE;      -- DEPT_CODE        JOB_CODE        SALARY
SELECT * FROM DEPARTMENT; -- DEPT_ID                                                                     LOCATION _ID
SELECT * FROM JOB;                 --                            JOB_CODE                                                                     NATIONAL_CODE
SELECT * FROM LOCATION;     --                                                                                   LOCAL_CODE
SELECT * FROM SAL_GRADE;    --                                                  MIN_SAL, MAX_SAL
SELECT * FROM NATIONAL;      --                                                                                                                  NATIONAL_CODE

-->> 오라클 전용 구문
SELECT E.EMP_NAME "사원명"
            , D.DEPT_TITLE "부서명"
            , J.JOB_NAME "직급명"
            , L.LOCAL_NAME "근무지역명"
            , N.NATIONAL_NAME "근무국가명"
            , S.SAL_LEVEL "급여등급"
FROM EMPLOYEE E, DEPARTMENT D, JOB J, LOCATION L, SAL_GRADE S, NATIONAL N
WHERE E.DEPT_CODE = D.DEPT_ID
    AND E.JOB_CODE = J.JOB_CODE
    AND D.LOCATION_ID = L.LOCAL_CODE
    AND E.SALARY BETWEEN S.MIN_SAL AND S.MAX_SAL
    AND L.NATIONAL_CODE = N.NATIONAL_CODE;
--> 주의사항 : 다중조인할 테이블이 많아지면 컬럼명이 중복될 수 있으니
--                  최대한 헷갈리지 않게 별칭을 붙이는 것을 습관화 하기

-->> ANSI 구문
SELECT E.EMP_NAME "사원명"
            , D.DEPT_TITLE "부서명"
            , J.JOB_NAME "직급명"
            , L.LOCAL_NAME "근무지역명"
            , N.NATIONAL_NAME "근무국가명"
            , S.SAL_LEVEL "급여등급"
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
JOIN JOB J USING (JOB_CODE)
JOIN LOCATION L ON (D.LOCATION_ID = L.LOCAL_CODE)
JOIN NATIONAL N ON (L.NATIONAL_CODE = N.NATIONAL_CODE)
JOIN SAL_GRADE S ON (E.SALARY BETWEEN S.MIN_SAL AND S.MAX_SAL);

-- 사원 사번, 사원명, 사원의 부서명, 사원 급여
-- 사수 사번, 사수명, 사수의 부서명, 사수 급여

-->> 오라클 전용 구문
SELECT E.EMP_ID "사원 사번", E.EMP_NAME "사원명", D1.DEPT_TITLE "사원 부서명", E.SALARY "사원 급여"
            , M.EMP_ID "사수 사번", M.EMP_NAME "사수명", D2.DEPT_TITLE "사수 부서명", M.SALARY "사수 급여"
FROM EMPLOYEE E, EMPLOYEE M, DEPARTMENT D1, DEPARTMENT D2
WHERE E.MANAGER_ID = M.EMP_ID
     AND E.DEPT_CODE = D1.DEPT_ID
     AND M.DEPT_CODE = D2.DEPT_ID;

-->> ANSI 구문
SELECT E.EMP_ID "사원 사번", E.EMP_NAME "사원명", D1.DEPT_TITLE "사원 부서명", E.SALARY "사원 급여"
            , M.EMP_ID "사수 사번", M.EMP_NAME "사수명", D2.DEPT_TITLE"사수 부서명", M.SALARY "사수 급여"
FROM EMPLOYEE E
JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID)
JOIN DEPARTMENT D1 ON (E.DEPT_CODE = D1.DEPT_ID)
JOIN DEPARTMENT D2 ON (M.DEPT_CODE = D2.DEPT_ID);