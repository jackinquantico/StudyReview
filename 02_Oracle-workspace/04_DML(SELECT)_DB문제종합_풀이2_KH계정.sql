-- 22/08/26 작성 - 2, 3, 4, 5, 6, 7, 8, 9, 10, 11*, 12*, 13, 14, 15, 16, 17, 18*, 19, 20
-- 22/08/27 작성 - 22, 24, 25*, 27, 28, 29*
-- 22/08/28 작성 - 1, 11, 12, 18, 21, 23, 25, 29, 30, 31, 32, 33, 36, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48
-- 못 푼 문제 :  26(다중열), 34(ROWNUM), 35(다중열), 37(UPDATE)

-- [문제 1]
--EMPLOYEE 테이블에서 12월 생일자에게 축하 메세지 보내기
--결과: OOO님 12월 OO일 생일을 축하합니다! 

SELECT EMP_NAME || '님 12월 ' || SUBSTR(EMP_NO, 5, 2) || '일 생일을 축하합니다!'
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 3, 2) = '12';

---------------------------------------------------------------------------------------------------
--[문제 2]

--EMP 테이블의 부서코드와 DEPT 테이블을 조인하여 각 부서별 근무지 위치를 조회
--사원명, 부서코드, 부서명, 근무지 위치 출력

-->> 오라클 구문
SELECT EMP_NAME, DEPT_CODE, DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE E, DEPARTMENT D, LOCATION L
WHERE E.DEPT_CODE = D.DEPT_ID
     AND D.LOCATION_ID = L.LOCAL_CODE;

-->> ANSI 구문
SELECT EMP_NAME, DEPT_CODE, DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);

---------------------------------------------------------------------------------------------------
--[문제 3]

--EMPLOYEE 테이블에서 월급 200만원 이상 300만원 이하인 사원의 
--사번, 사원명, 입사일, 부서코드, 연봉 조회 (단, 연봉은 BONUS 적용 및 \999,999,999로 조회)

SELECT EMP_ID, EMP_NAME, HIRE_DATE, DEPT_CODE
            , (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "연봉"
FROM EMPLOYEE
WHERE SALARY BETWEEN 2000000 AND 3000000;

---------------------------------------------------------------------------------------------------
--[문제 4]

--EMPLOYEE 테이블을 통해 PHONE 번호가 011으로 시작하는 사원의
--이름, 사번, PHONE, 부서코드를 조회

SELECT EMP_NAME, EMP_ID, PHONE, DEPT_CODE
FROM EMPLOYEE
WHERE SUBSTR(PHONE, 1, 3) = '011';

---------------------------------------------------------------------------------------------------
--[문제 5]

--80년대생인 남자 직원들 중 성이 '김'씨인 사람의 주민번호, 직원명 조회
--단, 주민번호는 [888888-2******] 형태로 조회 및 직원명으로 오름차순 정렬

SELECT EMP_NAME "직원명"
            , RPAD(SUBSTR(EMP_NO, 1, 8), 14, '*') "주민번호"
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 1, 1) = '8'
     AND SUBSTR(EMP_NAME, 1, 1) = '김'
     AND SUBSTR(EMP_NO, 8, 1) IN ('1', '3');

---------------------------------------------------------------------------------------------------
--[문제 6]

--EMPLOYEE 테이블에서 직급코드를 중복 없이, "직급 종류" 라는 별칭을 부여하고
--"직급 종류" 오름차순으로 정렬해서 조회

SELECT DISTINCT JOB_CODE "직급종류"
FROM EMPLOYEE
ORDER BY JOB_CODE ASC;

---------------------------------------------------------------------------------------------------
--[문제 7]

--부서별 급여 합계가 부서 급여 총합의 10%보다 많은 부서의 부서명과, 부서급여 합계 조회
--일반 단일행 서브쿼리 방식

SELECT SUM(SALARY) * 0.1
FROM EMPLOYEE;

SELECT DEPT_TITLE, SUM(SALARY)
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
GROUP BY DEPT_TITLE
HAVING SUM(SALARY) > (SELECT SUM(SALARY)
                                            FROM EMPLOYEE) * 0.1;

---------------------------------------------------------------------------------------------------
--[문제 8]

--EMPLOYEE 테이블에서 부서 인원이 3명 이상인 부서의 
--부서 코드, 평균, 최고 급여, 최저 급여, 인원 수 조회 
--(단, 부서코드로 오름차순 조회 및 \999,999,999로 조회)

SELECT DEPT_CODE
            , TO_CHAR(ROUND(AVG(SALARY)), 'L999,999,999') "평균 급여"
            , MAX(SALARY) "최고 급여"
            , MIN(SALARY) "최저 급여"
            , COUNT(EMP_ID) "인원 수"
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(EMP_ID) >= 3
ORDER BY DEPT_CODE ASC;

---------------------------------------------------------------------------------------------------
--[문제 9]

--EMPLOYEE 테이블에서 
--직원 중 '이'씨 성을 가지면서, 
--급여가 200만원 이상 250만원 이하인 
--직원의 이름과 급여를 조회하시오

SELECT EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SUBSTR(EMP_NAME, 1, 1) = '이'
     AND SALARY BETWEEN 2000000 AND 2500000;

---------------------------------------------------------------------------------------------------
--[문제 10]

--자신의 매니저보다 급여(SALARY)를 많이 받는 직원들의
--이름(EMP_NAME),급여(SALARY),MANAGER_ID,매니저 이름(EMP_NAME)을
--급여의 내림차순으로 조회하시오.

SELECT E.EMP_NAME "사원명", E.SALARY "급여", E.MANAGER_ID "사수 사번", M.EMP_NAME "사수명"
FROM EMPLOYEE E, EMPLOYEE M
WHERE E.MANAGER_ID = M.EMP_ID
     AND E.SALARY > M.SALARY
ORDER BY E.SALARY DESC;

---------------------------------------------------------------------------------------------------
--[문제 11] 

--EMPLOYEE 테이블에서 부서별 그룹을 편성하여
--부서별 급여 합계, 제일 낮게 받는 부서와, 제일 높게 받는부서, 인원수를 조회
--단, 조회결과는 인원수 오름차순하여 출력하여라.

SELECT SUM(SALARY), MAX(SALARY), MIN(SALARY), COUNT(EMP_ID)
FROM EMPLOYEE
GROUP BY DEPT_CODE
ORDER BY COUNT(EMP_ID) ASC;

---------------------------------------------------------------------------------------------------
--[문제12]

--EMPLOYEE 테이블에서 직급별
--그룹을 편성하여 직급코드, 급여평균, 급여합계, 인원 수를 조회
--단, 조회 결과는 급여평균 오름차순하여 출력, 인원수는 3명을 초과하는 직급만 조회

SELECT JOB_CODE, ROUND(AVG(SALARY)) "급여 평균", SUM(SALARY) "급여 합계", COUNT(EMP_ID)
FROM EMPLOYEE
GROUP BY JOB_CODE
HAVING COUNT(EMP_ID) > 3
ORDER BY ROUND(AVG(SALARY)) ASC;

---------------------------------------------------------------------------------------------------
--[문제13]

--2001년에 입사한 여자 직원이 있다.
--해당 직원과 같은 부서, 같은 직급에 해당하는 사원들을 조회하시오.
--사번, 사원명, 직급, 부서, 입사일

SELECT EMP_ID, EMP_NAME, JOB_CODE, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE (JOB_CODE, DEPT_CODE) = (SELECT JOB_CODE, DEPT_CODE
                                                          FROM EMPLOYEE
                                                          WHERE EXTRACT(YEAR FROM HIRE_DATE) = '2001'
                                                               AND SUBSTR(EMP_NO, 8, 1) IN ('2', '4'));

---------------------------------------------------------------------------------------------------
--[문제14]

--EMPLOYEE 테이블에서 '하이유'와 같은 부서에서 일하는 사원들의 
--사원번호, 사원명, 부서코드 직급코드, 급여 조회
--직급코드 내림차순 조회

SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
                                      FROM EMPLOYEE
                                      WHERE EMP_NAME = '하이유')
    AND EMP_NAME != '하이유';

---------------------------------------------------------------------------------------------------
--[문제15] 

--EMPLOYEE 테이블에서?입사일이?2000년?1월?1일?이전인?사원에?대해
-- 사원의?이름, ?입사일,? 부서코드, 급여를?입사일순으로?조회하시오
--(문제에 있는 이름대로 컬럼명을 따로 붙여주세요)

SELECT EMP_NAME "사원명", HIRE_DATE "입사일", DEPT_CODE "부서코드", SALARY "급여"
FROM EMPLOYEE
WHERE HIRE_DATE < '20100101'
ORDER BY HIRE_DATE ASC;

---------------------------------------------------------------------------------------------------
--[문제16]

--EMPLOYEE 테이블에서 해외영업 부서(DEPT_TITLE) 소속인 사원들의
--이름(EMP_NAME), 직급(JOB_TITLE), 부서명(DEPT_TITLE), 근무국가(NATIONAL_CODE)를 조회하시오
--단, 오라클 조인 구문으로 작성하고 별칭을 반드시 입력

-->> 오라클 구문
SELECT EMP_NAME "이름"
            , JOB_NAME "직급"
            , DEPT_TITLE "부서명"
            , NATIONAL_CODE "근무국가"
FROM EMPLOYEE E, JOB J, DEPARTMENT D, LOCATION L
WHERE DEPT_CODE = DEPT_ID
     AND E.JOB_CODE = J.JOB_CODE
     AND D.LOCATION_ID = L.LOCAL_CODE
     AND DEPT_TITLE LIKE '%해외영업%';
     
-->> ANSI 구문
SELECT EMP_NAME "이름"
            , JOB_NAME "직급"
            , DEPT_TITLE "부서명"
            , NATIONAL_CODE "근무국가"
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
JOIN DEPARTMENT D ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION L ON (LOCATION_ID = LOCAL_CODE)
WHERE DEPT_TITLE LIKE '%해외영업%';


---------------------------------------------------------------------------------------------------
--[문제17]

--EMPLOYEE 테이블에서
--'이태림'사원의 근속 년수를 조회하시오 (현재는 퇴사상태)

SELECT MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 "근속년수"
FROM EMPLOYEE
WHERE EMP_NAME = '이태림';

---------------------------------------------------------------------------------------------------
--[문제18] **********

--자신이 속한 직급의 평균 급여보다 많이 받는 사원의
--사원번호,직급명, 사원명,부서명, 급여정보 조회

/*
SELECT EMP_ID, JOB_NAME, EMP_NAME, DEPT_TITLE, SALARY, JOB_CODE
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY > ANY (SELECT AVG(SALARY) FROM EMPLOYEE GROUP BY JOB_CODE);
*/
/*
4850000	J2
2017500	J7
3600000	J3
2624373.33333333333333333333333333333333	J6
2820000	J5
8000000	J1
2330000	J4
*/
---------------------------------------------------------------------------------------------------
--[문제19]

--부서별로 근무하는 사원의 수가 3명 이하인 경우, 사원이 적은 부서별로 오름차순 정렬 조회

SELECT DEPT_CODE, COUNT(EMP_ID)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(EMP_ID) <= 3
ORDER BY SUM(EMP_ID) ASC;

---------------------------------------------------------------------------------------------------
--[문제20]

--EMPLOYEE 테이블에서
--직급 별로 급여평균을 조회하고 급여평균 내림차순으로 정렬하시오
--(급여평균은 TRUNC 함수 사용하여 만원단위 이하는 버림 하시오)

SELECT TRUNC(AVG(SALARY)) "급여 평균"
FROM EMPLOYEE
GROUP BY JOB_CODE
ORDER BY TRUNC(AVG(SALARY)) DESC;

---------------------------------------------------------------------------------------------------
--[문제21] ************

--해외영업2부(DEPT_CODE: D6)의 평균 급여보다 많이 받고, 해외영업2부에 속하지 않으며 
--관리자가 없는 사원의 사번(EMP_ID), 이름(EMP_NAME), 직급(JOB_NAME),
--                              부서이름(DEPT_TITLE), 급여(SALARY)를 조회하시오.
--단,FROM 절에 서브쿼리 사용, JOIN은 오라클 구문 사용, 셀프 조인 사용

SELECT AVG(SALARY)
FROM EMPLOYEE
WHERE DEPT_CODE = 'D6';

SELECT EMP_ID, EMP_NAME, JOB_NAME, DEPT_TITLE, SALARY
FROM EMPLOYEE E, JOB J, DEPARTMENT D
WHERE E.JOB_CODE = J.JOB_CODE
     AND E.DEPT_CODE = D.DEPT_ID
     AND SALARY > (SELECT AVG(SALARY)
                                FROM EMPLOYEE
                                WHERE DEPT_CODE = 'D6')
     AND DEPT_TITLE != '해외영업2부'
     AND MANAGER_ID IS NULL;

---------------------------------------------------------------------------------------------------
--[문제22] 

--EMP에서 직급이름으로 그룹을 만들고 월급이 5000이상인 그룹 찾기
--JOB이름과, 급여 합계를 조회하시오

SELECT JOB_NAME, SUM(SALARY)
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND SALARY > 5000000
GROUP BY JOB_NAME;

---------------------------------------------------------------------------------------------------
--[문제23]

--EMPLOYEE 테이블에서
--입사일로부터 근무년수가 가장 긴 직원 상위 6명을
--RANK()함수를 이용하여 조회하시오
--사번, 사원명, 부서명, 직급명, 입사일을 조회하시오.

--근무년수가 가장 긴 직원 상위 6 명
SELECT *
FROM (SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, HIRE_DATE
                        , RANK() OVER (ORDER BY MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 DESC) "순위"
            FROM EMPLOYEE)
WHERE ROWNUM <= 6;

---------------------------------------------------------------------------------------------------
--[문제24]

--EMPLOYEE 테이블에서 
--부서명 별로 급여가 가장 높은 직원들의
--부서명, 최대급여를 조회하시오
--단, 최대급여가 400만원 이하인 부서들만 조회하시오
--(부서명은 JOIN 활용)

SELECT DEPT_TITLE, MAX(SALARY)
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
GROUP BY DEPT_TITLE
HAVING MAX(SALARY) <= 4000000;

---------------------------------------------------------------------------------------------------
--[문제25] ***********

--EMPLOYEE 테이블에서 부서별 최고 급여를 확인 후, 사원 중 해당 부서와 최고 급여가 일치하는 사원의
--사번(EMP_ID), 이름(EMP_NAME), 부서이름(DEPT_TITLE), 직급(JOB_NAME)
-- , 부서코드(DEPT_CODE), 급여(SALARY)를 조회하시오.
--급여 내림차순으로 정렬, JOIN(ANSI 구문 사용), WHERE 절에서 서브쿼리로 부서별 최고 급여 확인.

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY IN (SELECT MAX(SALARY)
                                FROM EMPLOYEE
                                GROUP BY DEPT_CODE)
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[문제26]

--'장쯔위'와 같은 연봉레벨, 같은 직급인 사원들의 
--사원번호, 이름, 부서코드, 직급코드, 연봉레벨(SAL_LEVEL) 조회 (다중열 처리)

SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SAL_LEVEL
FROM EMPLOYEE
WHERE (SAL_LEVEL, JOB_CODE) IN (SELECT SAL_LEVEL, JOB_CODE
                                                         FROM EMPLOYEE
                                                         WHERE EMP_NAME = '장쯔위');

---------------------------------------------------------------------------------------------------
--[문제27]

-- 사원들 중 월급이 5000000 이상이면 'HIGH', 3000000 이상이면 'MEDIUM', 
-- 2000000 이상이면 'LOW' 로 나머지는 'OTL'로 출력하고  
--사원명, 부서코드, 월급을 조회하시오.
--단, 월급이 많은 순으로 정렬하시오.

SELECT EMP_NAME, DEPT_CODE
            , CASE WHEN SALARY >= 5000000 THEN 'HIGH'
                        WHEN SALARY >= 3000000 THEN 'MEDIUM'
                        WHEN SALARY >= 2000000 THEN 'LOW'
                        ELSE 'OTL'
            END
FROM EMPLOYEE
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[문제28]

--전형돈과 같은 직급, 같은 부서에 근무하는 
--직원들의 정보를 조회하시오 

SELECT *
FROM EMPLOYEE
WHERE (JOB_CODE, DEPT_CODE) IN (SELECT JOB_CODE, DEPT_CODE
                                                            FROM EMPLOYEE
                                                            WHERE EMP_NAME = '전형돈');

---------------------------------------------------------------------------------------------------
--[문제 29]

--EMPLOYEE테이블에서
--각 부서 별 입사일이 가장 오래된 사원을 한 명씩 선별해
--사원번호, 사원명, 부서번호, 입사일을 조회하고 
--문제에 있는 명칭대로 컬럼명을 지정하시오

SELECT MIN(HIRE_DATE)
FROM EMPLOYEE;

SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE HIRE_DATE IN (SELECT MIN(HIRE_DATE)
                                      FROM EMPLOYEE
                                      GROUP BY DEPT_CODE);

---------------------------------------------------------------------------------------------------
--[문제 30]

--EMPLOYEE테이블에서
--근무년수가 20년 이상 30년 미만인 사원의
--사원번호,사원명,입사일,연봉을 구하시오
--단,연봉은 보너스를 포함한 총합을 구한다.

SELECT EMP_ID, EMP_NAME, HIRE_DATE, (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "연봉"
FROM EMPLOYEE
WHERE (MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) BETWEEN 20 AND 30;

---------------------------------------------------------------------------------------------------
--[문제 31]

--EMPLOYEE 테이블에서 근무국가(NATIONAL_CODE)가 'KO'인 사원들의
--이름(EMP_NAME), 연봉순위, 급여(SALARY), 근무국가(NATIONAL_CODE)를 조회하시오
--단, 연봉순위는 DENSE_RANK() 사용, ANSI(JOIN) 구문 사용, 내림차순 정렬(연봉순위) 

SELECT EMP_NAME, DENSE_RANK() OVER (ORDER BY SALARY DESC) "연봉순위", SALARY, NATIONAL_CODE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
WHERE NATIONAL_CODE = 'KO';

---------------------------------------------------------------------------------------------------
--[문제32] 
 
--EMPLOYEE 테이블에서 'J1' 직급의 최고 월급과 'J7' 직급의 최저 월급 조회
--단, 나머지 JOB_CODE는 '비밀' 처리

SELECT CASE WHEN JOB_CODE = 'J1' THEN TO_CHAR((SELECT MAX(SALARY) FROM EMPLOYEE WHERE JOB_CODE = 'J1'))
                        WHEN JOB_CODE = 'J7' THEN TO_CHAR((SELECT MIN(SALARY) FROM EMPLOYEE WHERE JOB_CODE = 'J7'))
                        ELSE '비밀'
             END "등급"
FROM EMPLOYEE
GROUP BY JOB_CODE;             
---------------------------------------------------------------------------------------------------
--[문제 33]

--EMPLOYEE 테이블에서
--전 직원의 평균급여보다 급여가 적은 직원들의 사원명, 급여를 조회하고
--급여 내림차순으로 정렬하시오
--(단일행 서브쿼리 사용)

SELECT EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SALARY < (SELECT AVG(SALARY) FROM EMPLOYEE)
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[문제 34]

--급여 평균이 3위안에 드는 직급을 찾아 직급코드, 직급명, 급여평군을 조회한 후
-- ROWNUM과 라인뷰를 반영하여 조회하시오

SELECT ROWNUM, JOB_CODE, JOB_NAME
FROM (SELECT JOB_CODE, AVG(SALARY)
            FROM EMPLOYEE
            GROUP BY JOB_CODE
            ORDER BY AVG(SALARY) DESC) E
JOIN JOB USING (JOB_CODE)
WHERE ROWNUM <= 3;

---------------------------------------------------------------------------------------------------
--[문제 35]

--EMPLOYEE 테이블에서
--급여가 1,380,000원인 직원과 같은 직급코드, 같은 급여등급(SAL_LEVEL)에 해당하는 
--사원의 사원명, 직급코드, 급여등급을 조회하시오
--단, 급여가 1,380,000원인 해당직원은 제외하여 조회하시오
--(다중열 서브쿼리 사용)

SELECT EMP_NAME, JOB_CODE, SAL_LEVEL
FROM EMPLOYEE
WHERE (JOB_CODE, SAL_LEVEL) = (SELECT JOB_CODE, SAL_LEVEL FROM EMPLOYEE WHERE SALARY = 1380000)
     AND SALARY != 1380000;

---------------------------------------------------------------------------------------------------
--[문제 36]

--4/4분기 실적에 따른 상여금을 지급하게 되어 기존 월급에 따라 상여금을 지급하려 한다.
--급여(SALARY)가 400만원 초과 시 급여의 30%, 200만원 이상 400만원 미만이면 급여의 50%, 
--100만원이상 200만원 미만이면 급여의 70%를 지급한다.
--단, CASE문 사용하고 급여 순으로 내림차순 정렬

SELECT EMP_NAME
            , CASE WHEN SALARY > 4000000 THEN SALARY * 0.3
                        WHEN SALARY BETWEEN 2000000 AND 4000000 THEN SALARY * 0.5
                        WHEN SALARY BETWEEN 1000000 AND 2000000 THEN SALARY * 0.7
             END "상여금"
FROM EMPLOYEE
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[문제 37]

--EMPLOYEE 테이블에서 사번이 200, 201, 202인 사원들을 찾아
--200번인 사원의 주민번호 앞자리를 '621212'로
--201번인 사원의 주민번호 앞자리를 '631111'로
--202번인 사원의 주민번호 앞자리를 '861010'으로 변경하는 UPDATE 구문을 구현하시오.

SELECT EMP_NAME, EMP_NO
            , CASE WHEN EMP_ID = '200' THEN REPLACE(EMP_NO, SUBSTR(EMP_NO, 1, 6), '621212')
                        WHEN EMP_ID = '201' THEN REPLACE(EMP_NO, SUBSTR(EMP_NO, 1, 6), '631111')
                        WHEN EMP_ID = '202' THEN REPLACE(EMP_NO, SUBSTR(EMP_NO, 1, 6), '861010')
            ELSE EMP_NO
            END "UPDATE"
FROM EMPLOYEE
WHERE EMP_ID IN ('200', '201', '202');

---------------------------------------------------------------------------------------------------
--[문제 38]

--EMPLOYE 테이블에서 ENT_YN이 Y면 '퇴사자', N이면 '근무자'로 표시하고
--관리자 사번이 있으면 '일반사원', 관리자 사번이 없으면 '관리자'로 표시하고
--직원의 사번, 사원명, 부서코드, 직급코드, 근무 현황, 관리자 여부를 조회하시오.

SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE
            , CASE WHEN ENT_YN = 'Y' THEN '퇴사자'
                        WHEN ENT_YN = 'N' THEN '근무자'
              END "근무 현황"
            , CASE WHEN MANAGER_ID IS NOT NULL THEN '일반사원'
                        WHEN MANAGER_ID IS NULL THEN '관리자'
              END "관리자 여부"
FROM EMPLOYEE;

---------------------------------------------------------------------------------------------------
--[문제39]

--EMPLOYEE 테이블에서 부서코드 D5의 직급코드가 J5도 아니고 J7도 아닌 사원의 
--사원명, 부서코드, 직급코드, SAL_LEVEL, 근무여부 조회

SELECT EMP_NAME, DEPT_CODE, JOB_CODE, SAL_LEVEL, ENT_YN
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
     AND JOB_CODE NOT IN ('J5', 'J7');

---------------------------------------------------------------------------------------------------
--[문제40]

--EMPLOYEE테이블에서 급여가 가장 높은 사원의
--사원번호,사원명,급여,부서번호를 구하시오

SELECT EMP_ID, EMP_NAME, SALARY, DEPT_CODE
FROM EMPLOYEE
WHERE SALARY = (SELECT MAX(SALARY) FROM EMPLOYEE);

---------------------------------------------------------------------------------------------------
--[문제41]

--일본에 근무하면서 해외영업1부인 사원을 조회하여
--사번,사원명,부서명,지역명(LOCAL_NAME),급여를 구하시오
--단, 사번 순으로 오름차순 하시오

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, LOCAL_NAME, SALARY
FROM EMPLOYEE E, DEPARTMENT D, LOCATION L
WHERE DEPT_CODE = DEPT_ID
     AND LOCATION_ID = LOCAL_CODE
     AND NATIONAL_CODE = 'JP'
     AND DEPT_TITLE LIKE '해외영업1%'
ORDER BY EMP_ID ASC;

---------------------------------------------------------------------------------------------------
--[문제42]

--부서별로 가장 월급을 적게 받는 사원명을 검색하시오
--모든 컬럼을 조회하나, 부서별 내림차순으로 정렬하시오

SELECT EMP_NAME
FROM EMPLOYEE
WHERE SALARY IN (SELECT MIN(SALARY)
                                FROM EMPLOYEE
                                GROUP BY DEPT_CODE)
ORDER BY DEPT_CODE DESC;

---------------------------------------------------------------------------------------------------
--[문제 43]

--EMPLOYEE 테이블에서
--최저급여를 받는 직원과 같은 부서의 직원들의 
--이름, 부서코드, 급여를 조회하시오 (최저급여 직원도 포함)
--(서브쿼리 이중으로 사용)

SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
                                      FROM EMPLOYEE
                                      WHERE SALARY = (SELECT MIN(SALARY)
                                                                      FROM EMPLOYEE))
     AND EMP_NAME != (SELECT EMP_NAME
                                     FROM EMPLOYEE
                                     WHERE SALARY = (SELECT MIN(SALARY)
                                                                      FROM EMPLOYEE));

---------------------------------------------------------------------------------------------------
--[문제44]

--EMPLOYEE 테이블에서 사원번호가 208인 사원과 동일한 직급코드를 가진 사원의 
--사원번호, 사원명, 직급코드, 급여 조회 (사원번호로 오름차순)

SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE JOB_CODE = (SELECT JOB_CODE
                                    FROM EMPLOYEE
                                    WHERE EMP_ID = '208')
ORDER BY EMP_ID ASC;

---------------------------------------------------------------------------------------------------
--[문제 45]

--현재 시간과 현재 시간으로부터 한 시간 후의 시간을 출력하라.
--시간 포맷 예시)2020-12-29 21:15:23

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') "현재 시간"
            , TO_CHAR(SYSDATE+1/24, 'YYYY-MM-DD HH24:MI:SS') "한시간 뒤"
FROM DUAL;

---------------------------------------------------------------------------------------------------
--[문제 46]

--1995년 5월 1일 ~ 2000년 5월 1일 사이에 입사한 사원들의 
--모든 정보를 부서번호순(오름차순)으로 검색하시오.

SELECT *
FROM EMPLOYEE
WHERE HIRE_DATE BETWEEN '19950501' AND '20000501'
ORDER BY DEPT_CODE ASC;

---------------------------------------------------------------------------------------------------
--[문제 47]

--20년 이상 근무자에게 희망퇴직 신청을 받으려고한다.
--조건은 3년 연봉(보너스 포함) + 5000만원의 위로금
--20년이상 근무자의 사원번호, 사원명, 부서명, 직급명, 입사일, 근무년수, 퇴직금(위로금포함)을 조회하고
--입사일 기준으로 내림차순으로 정렬하여 제출하십시오 

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, HIRE_DATE
            , MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 "근무년수"
            , (SALARY + (SALARY * NVL(BONUS, 0))) * 36 + 50000000 "퇴직금"
FROM EMPLOYEE E, DEPARTMENT, JOB J
WHERE DEPT_CODE = DEPT_ID
     AND E.JOB_CODE = J.JOB_CODE
     AND MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 >= 20
ORDER BY HIRE_DATE DESC;

---------------------------------------------------------------------------------------------------
--[문제 48]

--근무 년수에 따른 '수질 상태'를 분류하기 위해 CASE문을 이용하여 이름(EMP_NAME),
--직급(JOB_NAME), 입사날짜(HIRE_DATE), 수질상태(CASE문)을 조회하시오.
--근무 년수가 30년 이상이면 '석유', 20 ~ 29년 '해골물', 10 ~ 19년 '썩은물', 
-- 5 ~ 9년 '고인물', 나머지는 '아리수' 
--JOB 테이블 JOIN(ANSI)구문으로 작성, 입사날짜순으로 오름차순 정렬

SELECT EMP_NAME, JOB_NAME, HIRE_DATE
            , ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) "근무년수"
            , CASE WHEN ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) >= 30 THEN '석유'
                        WHEN ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) BETWEEN 20 AND 29 THEN '해골물'
                        WHEN ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) BETWEEN 10 AND 19 THEN '썩은물'
                        WHEN ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) BETWEEN 5 AND 9 THEN '고인물'
                        ELSE '아리수'
             END "수질 상태"
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
ORDER BY HIRE_DATE ASC

---------------------------------------------------------------------------------------------------







