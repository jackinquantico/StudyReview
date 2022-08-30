/*
    < SELECT >
    DQL 또는 DML로 분류될 수 있는 명령어로써
    데이터를 조회하거나 검색할 때 사용하는 명령어
    
    - Result Set : SELECT 구문을 통해 조회된 데이터들의 결과물
                   즉, 조회된 행들의 집합

    [ 표현법 ]
    SELECT 조회하고자하는컬럼명, ...   // SELECT절
    FROM 테이블명;                   // FROM절
*/

-- EMPLOYEE 테이블의 전체 사원들의 사번, 이름, 급여 컬럼 조회
SELECT EMP_ID, EMP_NAME, SALARY
FROM EMPLOYEE;

-- 명령어, 키워드, 테이블명, 컬럼명 등은 대소문자 구분없이 작성 가능
-- 자바처럼 낙타표기법이 불가하기 때문에 언더바_ 사용해서 단어 구분
-- 소문자로 써도 무방함. 단, 대문자를 권장

-- EMPLOYEE 테이블의 전체 사원들의 모든 컬럼을 다 조회
SELECT EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE, DEPT_CODE, JOB_CODE, SAL_LEVEL
        , SALARY, BONUS, MANAGER_ID, HIRE_DATE, ENT_DATE, ENT_YN
FROM EMPLOYEE;

-- 해당 테이블의 전체 컬럼을 조회하고 싶다면 SELECT 절에 SELECT * 로 표현
SELECT *
FROM EMPLOYEE;

-- JOB 테이블의 모든 컬럼 조회
SELECT *
FROM JOB;

-- JOB 테이블의 직급명 컬럼 조회
SELECT JOB_NAME
FROM JOB;

----- 실습 문제 -----
-- 1. DEPARTMENT 테이블의 모든 컬럼 조회
SELECT *
FROM DEPARTMENT;

SELECT DEPT_ID, DEPT_TITLE, LOCATION_ID
FROM DEPARTMENT;

-- 2. EMPLOYEE 테이블의 직원명, 이메일, 전화번호, 입사일 컬럼만 조회
SELECT EMP_NAME, EMAIL, PHONE, HIRE_DATE
FROM EMPLOYEE;

-- 3. EMPLOYEE 테이블의 입사일, 직원명, 급여 컬럼만 조회
SELECT HIRE_DATE, EMP_NAME, SALARY
FROM EMPLOYEE;

---------------------------------------------------------------------------------------
/*
    < 컬럼값을 통한 산술연산 >
    조회하고자 하는 컬럼들을 나열하는 SELECT 절에 산술연산(+,-,/,*) 을 기술해서
    연산 결과를 조회할 수 있다.
*/

-- EMPLOYEE 테이블로부터 직원명, 월급, 연봉(== 월급 * 12) 조회
SELECT EMP_NAME, SALARY, SALARY * 12
FROM EMPLOYEE;

-- EMPLOYEE 테이블로부터 직원명, 월급, 보너스, 보너스가 포함된 연봉(== (월급 + (보너스 * 월급) ) * 12) 조회
SELECT EMP_NAME, SALARY, BONUS, (SALARY + (BONUS * SALARY)) * 12
FROM EMPLOYEE;
--> 산술연산하는 과정에 NULL 값이 존재한다면 그 산술연산 결과도 NULL 이 나온다.

-- DATE 타입끼리도 연산 가능 (DATE -> 년/월/일/시/분/초)
-- EMPLOYEE 테이블로부터 직원명, 입사일, 근무일수(== 오늘날짜 - 입사일) 조회
-- ORACLE DB에서 오늘 날짜 : SYSDATE
SELECT EMP_NAME, HIRE_DATE, SYSDATE - HIRE_DATE
FROM EMPLOYEE;
-- 결과값은 일 수 단위로 출력되지만 소수점이 붙어서 출력됨.
-- 정수부 == 날짜 결과, 소수부 == 시분초 결과
-- DATE 타입 안에 포함되어있는 시/분/초에 대한 연산까지 수행하기 때문

---------------------------------------------------------------------------------------

/*
    < 컬럼명에 별칭 지정하기 >
    RESULT SET 상에 보여지는 컬럼 명이 수식 형태이거나 알아보기 힘들 경우에
    별칭을 지정해서 보여줄 수 있다.
    
    [ 표현법 ]
    컬럼명 AS 별칭
    컬럼명 AS "별칭"
    컬럼명 별칭
    컬럼명 "별칭"
    
    단, AS 를 붙이든 안 붙이든
    별칭 내부에 특수문자나 띄어쓰기가 포함될 경우에는 그 별칭을 반드시 "" 로 묶어서 표기
*/

-- EMPLOYEE 테이블로부터 이름, 급여(월), 보너스, 총 소득을 조회
SELECT EMP_NAME AS 이름, SALARY AS "급여(월)", BONUS 보너스, (SALARY + (SALARY * BONUS)) * 12 "총 소득"
FROM EMPLOYEE;

---------------------------------------------------------------------------------------
/*
    < 리터럴 > == 값
    임의로 지정한 리터럴을 SELECT 절에 기술하면
    실제 그 테이블에 존재하는 데이터처럼 조회 가능
    
    SELECT 절에 제시한 리터럴 값은 조회 결과인 RESULT SET 의 모든 행에 반복적으로 출력됨
    동시에 컬럼명으로도 제시됨 -> 별칭을 붙이기도 가능
*/

-- EMPLOYEE 테이블로부터 사번, 사원명, 급여, 단위 조회하기
SELECT EMP_ID, EMP_NAME, SALARY, '원' 단위
FROM EMPLOYEE;
--> 오라클에서 문자열 타입의 값은 ' '(홑따옴표)로 감싸서 표현

---------------------------------------------------------------------------------------
/*
    < DISTINCT >
    조회하고자 하는 컬럼명 앞에 기술하면
    해당 컬럼 내에 중복된 값들을 단 한 번씩만 출력해주는 효과
    
    단, 한 SELECT 절에 DISTINCT 구문은 한번만 기술 가능하다
    
    [ 표현법 ]
    SELECT DISTINCT 컬럼명
*/

-- 부서코드 조회
SELECT DEPT_ID
FROM DEPARTMENT;

-- EMPLOYEE 테이블로부터 부서 코드 조회
SELECT DISTINCT DEPT_CODE
FROM EMPLOYEE;

-- EMPLOYEE 테이블로부터 직급 코드 조회
SELECT DISTINCT JOB_CODE
FROM EMPLOYEE;

-- 컬럼이 여러 개일 경우
SELECT DISTINCT DEPT_CODE, JOB_CODE
FROM EMPLOYEE;
--> DEPT_CODE, JOB_CODE 값을 하나로 묶은 뒤 중복 판별을 한다.

---------------------------------------------------------------------------------------
/*
    < WHERE 절 >
    조회하고자 하는 테이블에 특정 조건을 제시해서 
    그 조건에 만족하는 데이터만을 조회하고자 할 때 기술하는 구문
    
    [ 표현법 ]
    SELECT 조회하고자하는컬럼명, ...
    FROM 테이블명
    WHERE 조건식;
    
    실행순서 : FROM 절 -> WHERE 절 -> SELECT 절
    
    - 조건식에 다양한 연산자들 사용 가능
    
    < 비교 연산자 >
    <, >, <=, >= : 대소비교 연산자
    = : 일치비교 연산자
    !=, ^=, <> : 불일치비교 연산자
*/

-- EMPLOYEE 테이블로부터 급여가 400만원 이상인 사원들만 조회 (모든 컬럼에 대해서 조회)
SELECT *
FROM EMPLOYEE
WHERE SALARY >= 4000000;

-- EMPLOYEE 테이블로부터 부서코드가 D9인 사원들의 사원명, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9';

-- EMPLOYEE 테이블로부터 부서코드가 D9가 아닌 사원들의 사원명, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
-- WHERE DEPT_CODE != 'D9';
-- WHERE DEPT_CODE ^= 'D9';
WHERE DEPT_CODE <> 'D9';
--> NULL 값인 경우는 제외하고 조회됨

-- EMPLOYEE 테이블로부터 현재 재직중인 사원들의 사번, 이름, 입사일 조회
-- ENT_YN 컬럼이 의미하는 바 : N = 재직, Y = 퇴사
SELECT EMP_ID, EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE ENT_YN = 'N';

------ 실습 문제 ------
-- 1. EMPLOYEE 테이블로부터 급여가 300 만원 이상인 사원들의 이름, 급여, 입사일 조회
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY >= 3000000;

-- 2. EMPLOYEE 테이블로부터 직급코드가 J2 인 사원들의 이름, 급여, 보너스 조회
SELECT EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE JOB_CODE = 'J2';

-- 3. EMPLOYEE 테이블로부터 연봉이 5000만원 이상인 사원들의 이름, 급여, 연봉, 입사일 조회
SELECT EMP_NAME AS 이름, SALARY AS 급여, SALARY*12 AS 연봉, HIRE_DATE AS 입사일
FROM EMPLOYEE
WHERE (SALARY * 12) >= 50000000;
-- SELECT절에서 부여한 별칭을 WHERE절에서 사용할 수 없음
-- ( ) 우선순위 연산자 - 가독성

---------------------------------------------------------------------------------------
/*
    < 논리 연산자 >
    여러 개의 조건을 엮을 때 사용
    
    AND : ~이면서, 그리고 (자바에서의 &&)
    OR : ~이거나, 또는 (자바에서의 ||)
*/

-- EMPLOYEE 테이블로부터 부서코드가 D9이면서 급여가 500만원 이상인 사원들의 이름, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE (DEPT_CODE = 'D9') AND (SALARY >= 5000000);

-- EMPLOYEE 테이블로부터 부서코드가 D6 이거나 급여가 300만원 이상인 사원들의 이름, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE (DEPT_CODE = 'D6') OR (SALARY >= 3000000);

-- EMPLOYEE 테이블로부터 급여가 350만원 이상이고 600만원 이하인 사원들의 이름, 사번, 급여, 직급 코드 조회
SELECT EMP_NAME, EMP_ID, SALARY, JOB_CODE
FROM EMPLOYEE
WHERE (3500000 <= SALARY) AND (SALARY <= 6000000);

---------------------------------------------------------------------------------------
/*
    < BETWEEN AND >
    값의 상한선과 하한선을 제시하여 범위에 대한 조건을 제시할 때 사용
    
    [ 표현법 ]
    비교대상컬럼명 BETWEEN 하한선 AND 상한선
*/

-- EMPLOYEE 테이블로부터 급여가 350만원 이상이고 600만원 이하인 사원들의 이름, 사번, 급여, 직급 코드 조회
SELECT EMP_NAME, EMP_ID, SALARY, JOB_CODE
FROM EMPLOYEE
WHERE SALARY BETWEEN 3500000 AND 6000000;

-- EMPLOYEE 테이블로부터 급여가 350만원 미만이고 600만원 초과인 사원들의 이름, 사번, 급여, 직급코드 조회
SELECT EMP_NAME, EMP_ID, SALARY, JOB_CODE
FROM EMPLOYEE
WHERE NOT SALARY BETWEEN 3500000 AND 6000000;
-- WHERE SALARY NOT BETWEEN 3500000 AND 6000000;
--> 오라클에서의 NOT은 자바의 논리부정연산자인 ! 와 동일한 의미이다.

-- EMPLOYEE 테이블로부터 입사일이 '90/01/01' ~ '03/01/01' 인 사원들의 모든 컬럼을 조회
SELECT *
FROM EMPLOYEE
WHERE HIRE_DATE BETWEEN '90/01/01' AND '03/01/01';
-- WHERE '90/01/01' <= HIRE_DATE AND HIRE_DATE <= '03/01/01'; 
--> BETWEEN AND 연산자는 DATE 형식에서도 사용 가능하다.

-- EMPLOYEE 테이블로부터 입사일이 '90/01/01' ~ '03/01/01'이 아닌 사원들의 모든 컬럼을 조회
SELECT *
FROM EMPLOYEE
WHERE NOT HIRE_DATE BETWEEN '90/01/01' AND '03/01/01';
-- WHERE HIRE_DATE NOT BETWEEN '90-01/01' AND '03/01/01';

---------------------------------------------------------------------------------------
/*
    < LIKE '특정 패턴' >
    비교하고자 하는 컬럼값이 지정한 특정패턴을 만족할 경우 조회
    
    [ 표현법 ]
    비교대상컬럼명 LIKE '특정패턴'
    
    - 특정 패턴에 와일드카드인 '%', '_' 를 사용해서 제시할 수 있음
    - '%' : 0 글자 이상
            비교대상컬럼명 LIKE '문자%' => 해당 컬럼 값 중에 '문자'로 시작되는 것을 조회
            비교대상컬럼명 LIKE '%문자' => 해당 컬럼 값 중에 '문자'로 끝나는 것을 조회
            비교대상컬럼명 LIKE '%문자%' => 해당 컬럼 값 중에 '문자'가 포함된 것을 조회 (검색 기능 구현 시 많이 쓰임)
    - '_' : 1 글자
           비교대상컬럼명 LIKE '_문자' => 해당 컬럼값 중에 '문자' 앞에 무조건 1글자가 있을 경우 조회
           비교대상컬럼명 LIKE '문자_' => 해당 컬럼값 중에 '문자' 뒤에 무조건 1글자가 있을 경우 조회
           ex) 비교대상컬럼명 LIKE '_ _문자' => 해당 컬럼값 중에 '문자' 앞에 무조건 2 글자가 있을 경우 조회
*/

-- EMPLOYEE 테이블로부터 성이 전씨인 사원들의 이름, 급여, 입사일 조회
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '전%';

-- EMPLOYEE 테이블로부터 이름 중에 '하'가 포함된 사원들의 이름, 주민번호, 부서코드 조회
SELECT EMP_NAME, EMP_NO, DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%하%';

-- EMPLOYEE 테이블로부터 전화번호 4번째 자리가 9로 시작하는 사원들의 사번, 사원명, 전화번호, 이메일 조회
SELECT EMP_ID, EMP_NAME, PHONE, EMAIL
FROM EMPLOYEE
WHERE PHONE LIKE '___9%';
-- '___9' => 총 4글자인데 9로 끝나는 것

-- EMPLOYEE 테이블로부터 이름 가운데 글자가 '지'인 사원들의 모든 컬럼을 조회
SELECT *
FROM EMPLOYEE
WHERE EMP_NAME LIKE '_지_';

-- 그 이외의 사원
SELECT *
FROM EMPLOYEE
WHERE NOT EMP_NAME LIKE '_지_';
-- WHERE EMP_NAME NOT LIKE '_지_';

----- 실습 문제 -----
-- 1. 이름이 '연'으로 끝나는 사원들의 이름, 입사일 조회
SELECT EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%연';

-- 2. 전화번호 처음 3글자가 010이 아닌 사원들의 이름, 전화번호 조회
SELECT EMP_NAME, PHONE
FROM EMPLOYEE
WHERE NOT PHONE LIKE '010%';

-- 3. DEPARTMENT 테이블로부터 해외영업과 관련된 부서들의 모든 컬럼 조회
SELECT *
FROM DEPARTMENT
WHERE DEPT_TITLE LIKE '%해외영업%';

---------------------------------------------------------------------------------------
/*
    < IS NULL 연산자 >
    해당 컬럼값이 NULL 인지 판별해주는 연산자
    
    [ 표현법 ]
    비교대상컬럼명 IS NULL : 해당 컬럼값이 NULL과 일치할 경우에만 조회
    비교대상컬럼명 IS NOT NULL : 해당 컬럼값이 NULL이 아닐 경우에만 조회
*/

-- 보너스를 받지 않는 사원들 조회 (BONUS 컬럼값이 NULL 인 경우)의 사번, 이름, 급여, 보너스 조회
SELECT EMP_ID, EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE BONUS IS NULL;

-- 보너스를 받는 사원들 조회
SELECT EMP_ID, EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE BONUS IS NOT NULL;

-- EMPLOYEE 테이블로부터 사수가 없는 사원들의 사원명, 사수의 사번, 부서코드 조회
SELECT EMP_NAME, MANAGER_ID, DEPT_CODE
FROM EMPLOYEE
WHERE MANAGER_ID IS NULL;

-- 사수도 없고 부서배치도 받지 않은 사원들의 모든 컬럼 조회
SELECT *
FROM EMPLOYEE
WHERE (MANAGER_ID IS NULL) AND (DEPT_CODE IS NULL);

-- 부서배치는 받지 않았지만 보너스는 받는 사원의 사원명, 보너스, 부서코드 조회
SELECT EMP_NAME, BONUS, DEPT_CODE
FROM EMPLOYEE
WHERE (DEPT_CODE IS NULL) AND (BONUS IS NOT NULL);

---------------------------------------------------------------------------------------
/*
    < IN >
    비교대상 컬럼값에 내가 제시한 목록들 중에서 하나라도 일치하는 값이 있으면 조회
    
    [ 표현법 ]
    비교대상컬럼명 IN (값, 값, 값, ...)
*/

-- 부서코드가 D6이거나 D8이거나 D5인 사원들의 이름, 부서코드, 급여 조회
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
-- WHERE (DEPT_CODE = 'D6') OR (DEPT_CODE = 'D8') OR (DEPT_CODE = 'D5');
WHERE DEPT_CODE IN ('D6', 'D8', 'D5');
--> 동등비교 조건이 여러개 OR 연산으로 붙어있을 경우 IN 연산자를 이용하여 간략하게 표현 가능

-- 그 이외의 사원들
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
-- WHERE NOT DEPT_CODE IN ('D6', 'D8', 'D5');
WHERE DEPT_CODE NOT IN ('D6', 'D8', 'D5');

---------------------------------------------------------------------------------------
/*
    < 연결 연산자 >
    여러 컬럼값들을 마치 하나의 컬럼인 것처럼 연결시켜주는 연산자
    주로 SELECT 절에서 쓰이며, 컬럼과 리터럴 값을 연결시켜주는 것도 가능
*/

SELECT EMP_ID || EMP_NAME || SALARY AS "연결됨"
FROM EMPLOYEE;

-- XX번 XXX님의 월급은 XXXXXXX원입니다.
SELECT EMP_ID || '번 ' || EMP_NAME || '님의 월급은 ' || SALARY || '원 입니다.' AS "문장 연결"
FROM EMPLOYEE;

---------------------------------------------------------------------------------------
/*
    < 연산자 우선 순위 >
    0. 우선순위 연산자 : ( )
    1. 산술 연산자 : +, -, /, *
    2. 연결 연산자 : ||
    3. 비교 연산자
    4. IS NULL , LIKE , IN
    5. BETWEEN AND
    6. NOT (논리부정연산자)
    7. AND (논리연산자)
    8. OR (논리연산자)
*/

-- AND, OR 연산자 예시
-- DEPT_CODE 가 D9이거나 D1이고, SALARY가 300만원 이하의 사원의 전체 컬럼 조회
SELECT *
FROM EMPLOYEE
-- WHERE DEPT_CODE = 'D9' OR DEPT_CODE = 'D1' AND SALARY <= 3000000;
--                                       (                         1                             ) 이 먼저 실행됨
WHERE (DEPT_CODE = 'D9' OR DEPT_CODE = 'D1') AND SALARY <= 3000000;

---------------------------------------------------------------------------------------
/*
    < ORDER BY 절 >
    SELECT 문 가장 마지막에 기입하는 구문
    + 실행 순서 또한 가장 마지막에 실행됨
    다 조회된 내용물을 어느 기준점에 맞춰 정렬해주는 역할
    
    [ 표현법 ]
    SELECT 조회하고자하는컬럼1, 2, ...
    FROM 테이블명
    WHERE 조건식 (생략 가능)
    ORDER BY [정렬기준으로삼고자하는컬럼명 or  별칭 or 컬럼순번] [ASC(오름차순)/DESC(내림차순)] [NULLS FIRST/NULLS LAST(생략가능)]
    
    - ASC : 오름차순 정렬 = 작은 것에서 큰 것 순서로 나열 (생략시 기본값)
    - DESC : 내림차순 정렬 = 큰 것에서 작은 것 순서로 나열
    
    - NULLS FIRST : 정렬하고자 하는 컬럼값에 NULL이 있을 경우 해당 NULL을 앞에 배치후 컬럼값 나열 (DESC 일 경우 기본값)
    - NULLS LAST : 정렬하고자 하는 컬럼값에 NULL이 있을 경우 해당 NULL을 컬럼값 나열 후에 배치 (ASC 일 경우 기본값)
*/

SELECT *
FROM EMPLOYEE
ORDER BY EMP_NAME ASC;

SELECT *
FROM EMPLOYEE
-- ORDER BY BONUS; -- ASC 또는 DESC 생략 시 기본값이 ASC
-- ORDER BY BONUS ASC; -- ASC는 기본값이 NULLS LAST
-- ORDER BY BONUS ASC NULLS FIRST; -- NULLS FIRST 적용 가능
-- ORDER BY BONUS DESC; -- DESC는 기본값이 NULSS FIRST
-- ORDER BY BONUS DESC NULLS LAST; -- NULSS LAST 적용도 가능
ORDER BY BONUS DESC, SALARY ASC;
--> 첫번째로 제시한 정렬기준의 컬럼값이 일치할 경우 두번째 정렬 기준을 가지고 다시 정렬

-- 전체 사원들의 이름, 급여, 연봉 조회하되, 단 연봉이 높은 순서대로 조회
SELECT EMP_NAME, SALARY, SALARY * 12 AS "연봉"
FROM EMPLOYEE
-- ORDER BY "연봉" DESC; -- 별칭 사용 가능
-- ORDER BY SALARY*12 DESC;
ORDER BY 3 DESC; -- 컬럼 순번 사용 가능하다. 단, 1부터 센다.

