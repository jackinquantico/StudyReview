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

