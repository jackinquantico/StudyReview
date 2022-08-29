/*
    < 서브쿼리 SUBQUERY >
    
    하나의 주된 SQL 문 (SELECT, INSERT, CREATE, ..) 안에 포함된 또 하나의 SELECT문
    메인 SQL 문을 위해 보조 역할을 하는 쿼리문
*/

-- 간단한 서브 쿼리 예시 1)
-- 노옹철 사원과 같은 부서인 사원 조회
-- 1) 노옹철 사원의 부서코드 조회
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '노옹철'; -- 노옹철 사원의 부서코드는 D9 -- 1행 1열 서브쿼리

-- 2) 부서 코드가 D9인 사원들 조회
SELECT EMP_NAME
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9'; -- 선동일, 송종기, 노옹철

--> SUBQUERY를 쓰는 경우
-- 위의 두 단계가 하나의 쿼리문으로 해결된다
SELECT EMP_NAME
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE 
                                      FROM EMPLOYEE 
                                      WHERE EMP_NAME = '노옹철');

-- 간단한 서브 쿼리 예시 2)
-- 전체 사원의 평균 급여보다 더 많은 급여를 받고 있는 사원들의 사번, 이름, 직급 코드 조회
-- 1) 전체 사원의 평균 급여 구하기
SELECT ROUND(AVG(SALARY))
FROM EMPLOYEE; -- 3047663 -- 1행 1열 서브쿼리

-- 2) 급여가 3047663원 이상인 사원들 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE
FROM EMPLOYEE
WHERE SALARY >= 3047663;

--> SUBQUERY를 쓰는 경우
-- 위의 두 단계가 하나의 쿼리문으로 해결된다
SELECT EMP_ID, EMP_NAME, JOB_CODE
FROM EMPLOYEE
WHERE SALARY >= (SELECT ROUND(AVG(SALARY))
                                  FROM EMPLOYEE);

-------------------------------------------------------------------------------------------------------
/*
     * 서브 쿼리 구분
     분류 기준 : 서브쿼리를 수행한 결과값(RESULT SET)이 몇 행 몇 열인지에 따라 분류된다.
     
     - 단일행 (단일열) 서브쿼리 : 서브쿼리를 수행한 결과값이 오로지 1개일 때 (1행 1열)
     - 다중행 (단일열) 서브쿼리 : 서브쿼리를 수행한 결과값이 여러 행 한 열일 때 (N행 1열)
     - (단일행) 다중열 서브쿼리 : 서브쿼리를 수행한 결과값이 한 행 여러 열일 때 (1행 N열)
     - 다중행 다중열 서브쿼리 : 서브쿼리를 수행한 결과값이 여러 행 여러 열일 때 (N행 N열)
     
     => 서브쿼리를 수행한 결과가 몇 행 몇 열이냐에 따라 사용 가능한 연산자도 달라짐
     
     참고) 서브쿼리가 FROM 절에 들어간 경우 : 인라인 뷰
*/
-------------------------------------------------------------------------------------------------------
/*
    1. 단일행 단일열 서브쿼리 (SINGLE ROW SUBQUERY)
    서브 쿼리의 조회 결과값이 오로지 1개일 때
    
    일반 연산자 사용 가능 (=, !=, <=, >, <, >=, ...)
    
*/

-- 전 직원의 평균 급여보다 더 적게 받는 사원들의 사원명, 직급 코드, 급여 조회
-- 1) 전 직원의 평균 급여 조회
SELECT ROUND(AVG(SALARY))
FROM EMPLOYEE; -- 3047663 -- 1행 1열 결과값

-- 2) 평균 미만으로 받는 사원명, 직급코드, 급여 조회
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY < 3047663;

--> 서브쿼리 사용해서 합치기 (단일행 서브쿼리 : 일반 연산자 사용 가능)
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY < (SELECT ROUND(AVG(SALARY)) -- 1행 1열 결과값(단일행 서브쿼리)
                                FROM EMPLOYEE);
                                

-- 사원 중 최저 급여를 받는 사원의 사번, 사원명, 직급코드, 급여, 입사일 조회
-- 1) 최저 급여 조회
SELECT MIN(SALARY)
FROM EMPLOYEE; -- 1380000

-- 2) 급여가 1380000인 사원 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY = 1380000;

--> 서브쿼리 사용해서 합치기 
SELECT EMP_ID, EMP_NAME, JOB_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY = (SELECT MIN(SALARY) -- 1행 1열 결과값(단일행 서브쿼리)
                                FROM EMPLOYEE);

-- 노옹철 사원의 급여보다 더 많이 받는 사원들의 사번, 이름, 부서코드, 급여 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > (SELECT SALARY
                                FROM EMPLOYEE
                                WHERE EMP_NAME = '노옹철');

-- 노옹철 사원의 급여보다 더 많이 받는 사원들의 사번, 이름, 부서명, 급여 조회
-->> 오라클 전용 구문
SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, E.SALARY
FROM EMPLOYEE E, DEPARTMENT D
WHERE E.DEPT_CODE = D.DEPT_ID
    AND SALARY > (SELECT SALARY
                               FROM EMPLOYEE
                               WHERE EMP_NAME = '노옹철');

-->> ANSI 구문
SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, E.SALARY
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
WHERE SALARY > (SELECT SALARY
                                FROM EMPLOYEE
                                WHERE EMP_NAME = '노옹철');
--> 서브쿼리와 JOIN도 함께 사용할 수 있음


-- 전지연 사원과 같은 부서인 사원들의 사번, 사원명, 휴대폰번호, 직급명
-->> 오라클 전용 구문
SELECT E.EMP_ID, E.EMP_NAME, E.PHONE, J.JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND E.DEPT_CODE = (SELECT DEPT_CODE
                                         FROM EMPLOYEE
                                         WHERE EMP_NAME = '전지연')
    AND EMP_NAME != '전지연';

-->> ANSI 구문
SELECT E.EMP_ID, E.EMP_NAME, E.PHONE, J.JOB_NAME
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
WHERE E.DEPT_CODE = (SELECT DEPT_CODE
                                         FROM EMPLOYEE
                                         WHERE EMP_NAME = '전지연')
    AND EMP_NAME != '전지연';


-- 부서별 급여 합이 가장 큰 부서 하나만을 조회
-- 부서코드, 부서명, 급여합 조회
-- 1) 부서별 급여 합 구하기
SELECT SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- 2) 급여합이 최대인 부서
SELECT MAX(SUM(SALARY))
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-->> 오라클 전용 구문
SELECT DEPT_CODE "부서코드"
            , DEPT_TITLE "부서명"
            , SUM(SALARY) "급여합"
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
GROUP BY DEPT_CODE, DEPT_TITLE
HAVING SUM(SALARY) = (SELECT MAX(SUM(SALARY))
                                            FROM EMPLOYEE
                                            GROUP BY DEPT_CODE);

-->> ANSI 구문
SELECT DEPT_CODE, DEPT_TITLE, SUM(SALARY)
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE DEPT_CODE = DEPT_ID
GROUP BY DEPT_CODE, DEPT_TITLE
HAVING SUM(SALARY) = (SELECT MAX(SUM(SALARY))
                                            FROM EMPLOYEE
                                            GROUP BY DEPT_CODE);

-------------------------------------------------------------------------------------------------------
/*
        2. 다중행 (단일열) 서브쿼리 (MULTI ROW SUBQUERY)
        서브쿼리의 조회 결과값이 여러 행일 때
        
        사용할 수 있는 연산자 종류
        - 컬럼명 IN (서브쿼리문) : 여러 개의 결과 값 중에서 한 개라도 일치하는 값이 있다면 
        - 컬럼명 NOT IN (서브쿼리문) : 여러 개의 결과 값 중에서 일치하는 게 하나도 없다면
        - 컬럼명 > ANY (서브쿼리문) : 여러 개의 결과값 중에서 하나라도 클 경우
                                                    여러 개의 결과값 중 가장 작은 값보다 클 경우
        - 컬럼명 < ANY (서브쿼리문) : 여러 개의 결과값 중에서 하나라도 작을 경우
                                                    여러 개의 결과값 중 가장 큰 값보다 작을 경우
        - 컬럼명 > ALL (서브쿼리문) : 여러 개의 결과값의 모든 값보다 클 경우
                                                  여러 개의 결과값 중에서 가장 큰 값보다 클 경우
        - 컬럼명 < ALL (서브쿼리문) : 여러 개의 결과값의 모든 값보다 작을 경우
                                                  여거 개의 결과값 중에서 가장 작은 값보다 작을 경우
*/

-- 각 부서별 최고 급여를 받는 사원의 이름, 직급코드, 급여 조회
-- 1) 각 부서별 최고 급여
SELECT MAX(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE; --2890000, 3660000, 8000000, 3760000, 3900000, 2490000, 2550000
-- 7행 1열로 총 7개의 값이 조회됨

-- 1_2) 
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY IN (2890000, 3660000, 8000000, 3760000, 3900000, 2490000, 2550000);

-- 2) 위의 급여를 받는 사원 조회
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY IN (SELECT MAX(SALARY)
                                  FROM EMPLOYEE
                                  GROUP BY DEPT_CODE);
                             
                                  
-- 선동일 또는 유재식 사원과 같은 부서인 사원들의 사원명, 부서코드, 급여 조회
-- 1) 선동일 또는 유재식 사원의 부서코드 구하기
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME IN ('선동일', '유재식');

-- 2) 같은 부서인 사원들 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE IN (SELECT DEPT_CODE
                                        FROM EMPLOYEE
                                        WHERE EMP_NAME IN ('선동일', '유재식'))
    AND EMP_NAME NOT IN ('선동일', '유재식');


-- 사원 < 대리 < 과장 < 차장 < 부장 ...
-- 대리 직급임에도 불구하고 과장 직급의 급여보다 더 많이 받는 직원들 조회 ( 사번, 이름, 직급명, 급여)
-- 1) 과장 직급의 급여
SELECT SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND J.JOB_NAME = '과장';
     
-- 2) 대리 직급이고 과장보다 더 많이 받는 직원 조회
SELECT EMP_ID, EMP_NAME, J.JOB_NAME, SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND J.JOB_NAME = '대리'
     AND SALARY > ANY (SELECT SALARY
                                         FROM EMPLOYEE E, JOB J
                                         WHERE E.JOB_CODE = J.JOB_CODE
                                              AND J.JOB_NAME = '과장');


-- 과장 직급임에도 불구하고 모든 차장 직급의 급여보다도 더 많이 받는 직원 조회
-- 사번, 이름, 직급명, 급여

-->> 오라클 구문
SELECT SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND JOB_NAME = '차장';

SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND JOB_NAME = '과장'
     AND SALARY > ALL (SELECT SALARY
                                        FROM EMPLOYEE E, JOB J
                                        WHERE E.JOB_CODE = J.JOB_CODE
                                             AND JOB_NAME = '차장');

-->> ANSI 구문
SELECT SALARY
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
WHERE JOB_NAME = '차장';

SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
WHERE JOB_NAME = '과장'
     AND SALARY > ALL (SELECT SALARY
                                        FROM EMPLOYEE E
                                        JOIN JOB J USING (JOB_CODE)
                                        WHERE JOB_NAME = '차장');


-------------------------------------------------------------------------------------------------------
/*
        3. (단일행) 다중열 서브쿼리 (MULTI COLUMN SUBQUERY)
        조회 결과 값이 한 행이지만 나열된 컬럼 수가 여러 개일 때
        
        (여러 개) = (여러 개) 이런 식으로 비교가 일어날 예정 : 뒤쪽의 (여러 개) 부분에 리터럴을 넣을 수 없다 에러 발생
*/

-- 하이유 사원과 같은 부서코드, 같은 직급 코드에 해당하는 사원들 조회
SELECT DEPT_CODE, JOB_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '하이유';

SELECT DEPT_CODE, JOB_CODE, EMP_NAME
FROM EMPLOYEE
WHERE (DEPT_CODE, JOB_CODE) = (SELECT DEPT_CODE, JOB_CODE
                                                          FROM EMPLOYEE
                                                          WHERE EMP_NAME = '하이유')
     AND EMP_NAME != '하이유';


-- 박나라 사원과 같은 직급 코드, 같은 사수사번을 가진 사원들의 사번, 이름, 직급코드, 사수사번 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, MANAGER_ID
FROM EMPLOYEE
WHERE (JOB_CODE, MANAGER_ID) = (SELECT JOB_CODE, MANAGER_ID
                                                             FROM EMPLOYEE
                                                             WHERE EMP_NAME = '박나라');


-------------------------------------------------------------------------------------------------------
/*
        4. 다중행 다중열 서브쿼리
        서브쿼리 조회 결과값이 여러 행 여러 열일 경우
*/

-- 각 직급별 최소 급여를 받는 사원들의 사번, 이름, 직급코드, 급여 조회
-->> 각 직급별 최소 급여(직급, 최소 급여)
SELECT JOB_CODE, MIN(SALARY)
FROM EMPLOYEE
GROUP BY JOB_CODE; 
-- 7행 2열, 총 14개의 값

-->> 사원들의 사번, 이름, 직급코드, 급여 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE (JOB_CODE, SALARY) IN (SELECT JOB_CODE, MIN(SALARY)
                                                     FROM EMPLOYEE
                                                     GROUP BY JOB_CODE);


-- 각 부서별 최고 급여를 받는 사원들의 사번, 이름, 부서코드, 급여 조회
SELECT DEPT_CODE, MAX(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE (NVL(DEPT_CODE, '없음'), SALARY) IN (SELECT NVL(DEPT_CODE, '없음'), MAX(SALARY)
                                                                            FROM EMPLOYEE
                                                                            GROUP BY DEPT_CODE);
-- NULL 인 값이 나온다면 동등비교가 안 돼서 결과에서 누락됨
-- NVL 함수로 처리해주면 됨 : 비교 대상과 비교할 값 모두에서

-->> NULL 값이 비교대상에 포함될 경우 동등비교가 일어나지 않기 때문에 NVL 처리가 필수
-->> NULL 값은 IS NULL 연산자로만 동등비교 가능

-------------------------------------------------------------------------------------------------------
/*
        5. 인라인 뷰 (INLINE-VIEW)
        FROM 절에 서브쿼리를 제시하는 것
        
        서브쿼리를 수행한 결과 (RESULT SET) 기준으로 조회하겠다
*/

-- 보너스 포함 연봉이 3000만원 이상인 사원들의 사번, 이름, 보너스 포함 연봉, 부서코드 조회
-->> 인라인뷰 쓰지 않는 경우
SELECT EMP_ID, EMP_NAME, (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "연봉", DEPT_CODE
FROM EMPLOYEE
WHERE (SALARY + (SALARY * NVL(BONUS, 0))) * 12 >= 30000000;

-->> 인라인뷰를 쓰는 경우
SELECT EMP_ID, EMP_NAME, (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "연봉", DEPT_CODE
FROM (SELECT * 
            FROM EMPLOYEE 
            WHERE (SALARY + (SALARY * NVL(BONUS, 0))) * 12 >= 30000000);

SELECT *
FROM (SELECT  EMP_ID, EMP_NAME, (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "연봉", DEPT_CODE
            FROM EMPLOYEE)
WHERE 연봉 >= 30000000;

-- ROWNUM : 오라클에서 제공하는 컬럼, 조회된 순서대로 1부터 순서를 부여하는 컬럼

-->> 인라인뷰를 주로 사용하는 예
-- TOP-N 분석 : 데이터베이스 상에 있는 자료 중 최상위 몇 개의 자료를 보기 위해 사용되는 자료

-- 전 직원 중 급여가 가장 높은 상위 5명
SELECT EMP_NAME, SALARY     -- 3
FROM EMPLOYEE                        -- 1
WHERE ROWNUM <= 5             -- 2
ORDER BY SALARY DESC;         -- 4
-- 이슈 : 급여순으로 내림차순 하고 번호를 붙이고 번호순대로 잘라줘야 하는데
--          번호순대로 자르고 번호를 붙이고 급여순으로 내림차순해주는 이슈 발생 (순서가 꼬임)

--> 1) ORDER BY 로 정렬한 테이블을 가지고 조회
--   2) SELECT 시에 ROWNUM 순번 부여 후
--   3) 번호순대로 잘라주는 ROWNUM <= 5 조건 적용

SELECT ROWNUM, EMP_NAME, SALARY     -- 3 : SELECT
FROM (SELECT *                                           -- 0 : RESULT SET 조회 / 1 : FROM
            FROM EMPLOYEE
            ORDER BY SALARY DESC)
 WHERE ROWNUM <= 5;                              -- 2 : WHERE

-- FROM 절 인라인뷰에 별칭을 붙일 수 있음
SELECT ROWNUM "순위", E.*
FROM (SELECT * 
            FROM EMPLOYEE
            ORDER BY SALARY DESC) E
WHERE ROWNUM <= 3;


-- 각 부서별 평균 급여가 높은 3개의 부서코드, 평균 급여 조회
SELECT ROWNUM "순위", DEPT_CODE, ROUND(평균급여) "평균급여"
FROM (SELECT DEPT_CODE, AVG(SALARY) "평균급여" -- 인라인뷰 내부에서 그룹함수 이용한 뒤 메인쿼리에서 사용하려면 반드시 별칭 붙여야 한다.
            FROM EMPLOYEE
            GROUP BY DEPT_CODE
            ORDER BY AVG(SALARY) DESC) E
WHERE ROWNUM <= 3;

-- 가장 최근에 입사한 사원 5명 조회 (사원명, 급여, 입사일)
SELECT ROWNUM, E.*
FROM (SELECT EMP_NAME, SALARY, HIRE_DATE
            FROM EMPLOYEE
            ORDER BY HIRE_DATE DESC) E
WHERE ROWNUM <= 5;

--> TOP-N 분석시 ORDER BY 절에 의한 정렬이 가장 먼저 일어나야 하기 때문에
-- ORDER BY 절이 먼저 일어날 수 있게끔 인라인뷰를 사용해야 한다.

-------------------------------------------------------------------------------------------------------
/*
        6. 순위 매기는 함수 (WINDOW 함수)
        RANK() OVER (정렬 기준)
        DENSE_RANK() OVER (정렬 기준)
        
        주의사항 : 오로지 SELECT 절에서만 작성 가능
                       오라클에서 순위를 매기는 함수를 윈도우 함수라고 부름 (SELECT 절에서만 쓸 수 있음)
                       
        두 함수의 차이점
        - RANK() OVER (정렬기준) : 만약 공동 1위가 3명이면 다음 순위는 4등 (그 다음순위 = 현재 순위 + 명수)
        - DENSE_RANK() OVER (정렬기준) : 만약 공동 1위가 3명이어도 다음 순위 2위 (그 다음순위 = 현재순위 + 1)
*/

-- 사원들의 급여가 높은 순서대로 매겨서 사원명, 급여, 순위 조회 (RANK)
SELECT EMP_NAME, SALARY
            , RANK() OVER (ORDER BY SALARY DESC) "순위"
FROM EMPLOYEE;
-- 공동 19위 2등, 그 다음 순서는 21위

-- 사원들의 급여가 높은 순서대로 매겨서 사원명, 급여, 순위 조회 (DENSE_RANK)
SELECT EMP_NAME, SALARY, DENSE_RANK() OVER (ORDER BY SALARY DESC) "순위"
FROM EMPLOYEE;
-- 공동 19위 2등, 그 다음 순서는 20위


-- 5위까지만 조회하겠다 (TOP-N 분석)
SELECT *
FROM (SELECT EMP_NAME, SALARY, RANK() OVER (ORDER BY SALARY DESC) "연봉순위"
            FROM EMPLOYEE)
WHERE ROWNUM <= 5;
