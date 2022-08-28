/*
    < GROUP BY 절 >
    그룹을 묶어줄 기준을 제시할 수 있는 구문
    => 해당 제시된 기준 별로 그룹을 묶을 수 있음
    
    여러 개의 값들을 하나의 그룹으로 묶어서 처리할 목적으로 사용 (통계)
*/

-- 전체 사원의 총 급여 합
SELECT SUM(SALARY)
FROM EMPLOYEE; --> 현재 조회된 전체 사원들을 하나의 그룹으로 묶어서 총 합을 구한 결과


-- 각 부서별 총 급여 합
SELECT DEPT_CODE,  SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- 전체 사원 수
SELECT COUNT(*)
FROM EMPLOYEE;

-- 각 부서별 사원 수
SELECT DEPT_CODE, COUNT(*)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- 각 부서별 총 급여 합을 부서코드 오름차순 정렬로 조회
SELECT DEPT_CODE, SUM(SALARY)     -- 3
FROM EMPLOYEE                                   -- 1
GROUP BY DEPT_CODE                         -- 2
ORDER BY DEPT_CODE ASC;                -- 4

-- 각 직급 별 직급코드, 총 급여의 합, 사원 수, 보너스를 받는 사원 수, 평균 급여, 최고 급여, 최소 급여 조회
SELECT JOB_CODE "직급 코드"
            , SUM(SALARY) "총 급여의 합"
            , COUNT(*) "사원 수"
            , COUNT(BONUS) "보너스 받는 사람"
            , ROUND(AVG(SALARY)) "평균 급여"
            , MAX(SALARY) "최고 급여"
            , MIN(SALARY) " 최소 급여"
FROM EMPLOYEE
GROUP BY JOB_CODE
ORDER BY JOB_CODE ASC;

-- 각 부서별 부서코드, 사원 수, 보너스 사원수, 사수 있는 사원 수, 평균 급여
SELECT DEPT_CODE "부서코드"
            , COUNT(*) "사원수"
            , COUNT(BONUS) "보너스 사원 수"
            , COUNT(MANAGER_ID) "사수 있는 사원"
            , ROUND(AVG(SALARY)) "평균 급여"
FROM EMPLOYEE
GROUP BY DEPT_CODE -- GROUP BY 절에는 별칭 사용 불가
ORDER BY DEPT_CODE;

-- 성별 별 사원 수
SELECT SUBSTR(EMP_NO, 8, 1) 성별, COUNT(*) 사원수
FROM EMPLOYEE
GROUP BY SUBSTR(EMP_NO, 8, 1);

-- DECODE 함수 적용
SELECT DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남자', '2', '여자') 성별
            , COUNT(*) 사원수
FROM EMPLOYEE
GROUP BY SUBSTR(EMP_NO, 8, 1);

-- 입사년도 별 인원 수, 단, 2015년도 부터 조회, 2015년도부터 오름차순 정렬
SELECT EXTRACT(YEAR FROM HIRE_DATE) "입사년도", COUNT(*) "인원 수"    -- 4
FROM EMPLOYEE                                                                                                -- 1
WHERE EXTRACT(YEAR FROM HIRE_DATE) >= 2015                                      -- 2
GROUP BY EXTRACT(YEAR FROM HIRE_DATE)                                               -- 3
ORDER BY EXTRACT(YEAR FROM HIRE_DATE);                                              -- 5

----------------------------------------------------------------------------------------------------
/*
    < HAVING 절 >
    그룹에 대한 조건을 제시할 때 사용되는 구문
    (그룹 함수가 포함된 조건식을 제시할 경우 무조건 HAVING 절에 작성해야 한다)
*/

-- 각 부서 별 평균 급여가 300 만원 이상인 부서들만 조회
SELECT DEPT_CODE "부서코드"
            , ROUND(AVG(SALARY)) "평균 급여"
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING ROUND(AVG(SALARY)) >= 3000000;
-- WHERE절에는 그룹함수식을 사용할 수 없음.

-- 각 직급 별 총 급여 합이 1000만원 이상인 직급 코드, 급여 합 조회
SELECT  JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY JOB_CODE
HAVING SUM(SALARY) >= 10000000;

-- 각 부서 별 보너스를 받는 사원이 한 명도 없는 부서만을 조회
SELECT DEPT_CODE, COUNT(*), COUNT(BONUS)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(BONUS) = 0;

-- 보너스를 받는 사원 수 : COUNT(BONUS)
-- COUNT(BONUS) > 0 : 한 명이라도 보너스를 받는 사원이 있다.
-- COUNT(BONUS) = 0 : 한 명도 보너스를 받지 않는다.

-- 각 부서 별 보너스를 받는 사원이 한 명이라도 있는 부서만을 조회
SELECT DEPT_CODE, COUNT(*), COUNT(BONUS)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(BONUS) > 0;

----------------------------------------------------------------------------------------------------
/*
    < 실행 순서 >

    SELECT * / 조회하고자하는컬럼명 / 리터럴 / 산술연산식 / 함수식 AS "별칭"
    FROM 조회하고자하는테이블명 / DUAL(가상테이블명)
    WHERE 조건식 (생략 가능, 그룹함수는 사용 불가)
    GROUP BY 그룹기준에해당하는컬럼명 / 함수식
    HAVING 그룹함수식에 대한 조건식
    ORDER BY [정렬 기준에 해당하는 컬럼명 / 별칭 / 컬럼 순번]   [ASC / DESC NULLS]   [FIRST / NULLS LAST] (생략 가능)

    FROM → WHERE → GROUP BY → HAVING → SELECT → ORDER BY
*/

----------------------------------------------------------------------------------------------------
/*
    < 집합 연산자 SET OPERATOR >
    여러 개의 쿼리 문을 가지고 하나의 쿼리 문으로 만드는 연산자
    
    - UNION : 합집합 (두 쿼리문을 수행한 결과값을 더한 후 중복되는 부분은 한 번 뺀 것) => OR의 개념이 강함
    - INTERSECT : 교집합 (두 쿼리문을 수행한 결과값 중 중복된 결과값 부분) => AND 의 개념이 강함
    - UNION ALL :합집합 결과에 교집합이 한 번 더해진 개념 
                         (두 쿼리 문을 수행한 결과값을 무조건 더함, 즉, 합집합에서 중복 제거를 하지 않은 상태)
                         => 중복된 결과가 두 번 조회될 수 있음
    - MINUS : 차집합 (선행 쿼리문 결과값으로부터 후행 쿼리문 결과값을 뺀 것)
*/

-- 1. UNION : 합집합 - 두 쿼리문을 수행한 결과값을 더한 후 중복되는 결과는 한번만 조회
-- 부서코드가 D5이거나 또는 급여가 300만원 초과인 사원들 조회(사원, 사원명, 부서코드, 급여)

-- 부서코드가 D5인 사원들만 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'; -- 6명 조회

-- 급여가 300만원 초과인 사원들만 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- 8명 조회

-- UNION 연산자 사용시
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
UNION
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- 12명 조회

-- 집합 연산 시 항상 두 쿼리의 SELECT 절이 완전히 일치해야 한다.

-- 위의 UNION 구문을 하나의 SELECT문으로 표현하기 - OR 연산자 사용
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5' OR SALARY < 3000000;
--> OR 연산자로 두 개의 조건을 엮어서 조회해도 결과는 동일

-- 각 부서별 급여 합 조회 (부서코드, 부서별 급여합)
-- GROUP BY 버전
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- UNION 연산자 사용시
SELECT SUM(SALARY)
FROM EMPLOYEE
WHERE DEPT_CODE = 'D1'
UNION
SELECT SUM(SALARY)
FROM EMPLOYEE
WHERE DEPT_CODE = 'D2';
-- .. 각 부서별로 WHERE 절을 이용해서 일일이 UNION 으로 합치는 방법도 가능하기는 함.

-- 2. UNION ALL : 여러 개의 쿼리문 결과값을 무조건 더하는 연산자 (중복 제거하기 전)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
UNION ALL
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- 14명이 조회됨 (중복 2명)

-- 3. INTERSECT : 여러 개의 쿼리문 결과값 중 중복되는 결과만 조회
-- 부서 코드가 D5이면서 급여도 300만원 초과인 사원들 (사번, 사원명, 부서코드, 급여)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
INTERSECT
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- 중복되는 2명 조회

-- AND 연산자 이용
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5' AND SALARY > 3000000;

-- 4. MINUS : 차집합 - 선행 쿼리 결과에서 후행 쿼리 결과를 뺀 나머지
-- 어디에서 무엇을 빼냐에 따라 결과가 다르므로 순서가 중요하다.
-- 부서 코드가 D5인 사원들 중 급여도 300만원 초과인 사원들을 제외하고 조회 (사번, 사원명, 부서코드, 급여)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5' -- 6명 (선행 쿼리)
MINUS
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- 6- 2명 = 4명 (후행 쿼리(중복 2명))

-- 부서코드가 D5인 사원들 중 급여가 300만원 이하인 사원들
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5' AND SALARY <= 3000000;

-- 만약 선행 쿼리와 후행 쿼리를 바꾼다면?
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000 -- 8명 (선행 쿼리)
MINUS
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'; -- 6명 (후행 쿼리에서 겹치는 2명 제외)

-- AND 연산자 버전
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000 AND DEPT_CODE != 'D5';

----------------------------------------------------------------------------------------------------
---- KH 계정 SELECT 기본 연습 문제 ----
-- 1. EMPLOYEE 테이블의 고용일, 사원 이름, 월급 조회
SELECT HIRE_DATE, EMP_NAME, SALARY
FROM EMPLOYEE;

-- 2. EMPLOYEE 테이블에서 SAL_LEVEL이 S1인 사원의 이름, 월급, 고용일, 연락처 조회
SELECT EMP_NAME, SALARY, HIRE_DATE, PHONE
FROM EMPLOYEE;

-- 3. EMPLOYEE 테이블에서 이름, 연봉, 총수령액(보너스포함), 실수령액(총수령액 - (연봉 * 세금 3%)) 조회
SELECT EMP_NAME "이름"
            , SALARY * 12 "연봉"
            , (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "총수령액"
            , (SALARY + (SALARY * NVL(BONUS, 0))) * 12 - (SALARY * 12 * 0.03) "실수령액"
FROM EMPLOYEE;

-- 4. EMPLOYEE 테이블에서 실수령액이 5천만워 이상인 사원의 이름, 월급, 실수령액, 고용일 조회
SELECT EMP_NAME "이름"
            , SALARY "월급"
            , (SALARY + (SALARY * NVL(BONUS, 0)) - (SALARY * 0.03)) * 12 "실수령액"
            , HIRE_DATE "고용일"
FROM EMPLOYEE;

-- 5. EMPLOYEE 테이블에서 사원 명과 직원의 주민번호를 이용하여 생년, 생월, 생일 조회
SELECT EMP_NAME
            , SUBSTR(EMP_NO, 1, 2) "생년"
            , SUBSTR(EMP_NO, 3, 2) "생월"
            , SUBSTR(EMP_NO, 4, 2) "생일"
FROM EMPLOYEE;

-- 6. EMPLOYEE 테이블에서 직원명, 부서코드, 생년월일, 나이(만) 조회
-- 단, 생년월일은 주민번호에서 추출해서 00년 00월 00일로 출력되게 하며
-- 나이는 주민번호에서 날짜 데이터로 변경 후 계산
SELECT EMP_NAME "직원명"
            , DEPT_CODE "부서코드"
            , SUBSTR(EMP_NO, 1, 2) || '년 ' || SUBSTR(EMP_NO, 3, 2) || '월 ' || SUBSTR(EMP_NO, 5,2) || '일' "생년월일"
            -- , TO_DATE(SUBSTR(EMP_NO, 1, 6), 'YYMMDD')
            , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO, 1, 2), 'RR')) AS "나이"
FROM EMPLOYEE;

-- 7. EMPLOYEE 테이블에서 근무 년수가 20년 이상인 직원 정보 조회
SELECT *
FROM EMPLOYEE
WHERE MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 >= 20;

-- 8. EMPLOYEE 테이블에서 부서코드가 D5, D6, D9인 사원만 조회하되,
-- D5면 총무부, D6이면 기획부, D9이면 영업부로 처리 (부서코드 오름차순으로 정렬)
SELECT EMP_NAME
            , CASE WHEN DEPT_CODE = 'D5' THEN '총무부'
                        WHEN DEPT_CODE = 'D6' THEN '기획부'
                        WHEN DEPT_CODE = 'D9' THEN '영업부'
             END AS 부서명
FROM EMPLOYEE
WHERE DEPT_CODE IN ('D5', 'D6', 'D9')
ORDER BY DEPT_CODE;

-- 9. EMPLOYEE 테이블에서 사번이 홀수인 직원들의 정보 모두 조회
SELECT *
FROM EMPLOYEE
WHERE MOD(EMP_ID, 2) = 1;

-- 6번 원래 쓰려고 했던 공식
SELECT TO_CHAR(TO_DATE(SUBSTR('990808-123', 1, 6)), 'YY"년" MM"월" DD"일"')
FROM DUAL;
