/*
    < 함수 FUNCTION >
    자바로 따지면 메소드와 같은 존재
    매개변수로 전달된 값들을 읽어서 내부적으로 계산한 결과를 리턴
    
    - 단일행 함수 : N개의 값을 읽어서 N개의 결과를 리턴 (매 행마다 함수 실행 후 매 행에 대한 결과를 모두 반환)
    - 그룹 함수 : N개의 값을 읽어서 1개의 결과를 리턴 (모든 행에 대해 하나의 그룹으로 묶어서 함수 실행 후 결과를 하나로 반환)

    - 주의할 점 : 항상 단일행 함수와 그룹 함수는 함께 사용할 수 없음
                    결과 행의 개수가 애초에 다르게 나오기 때문
*/

----------------------------------------------------------------------------------------------------
/*
    < 단일행 함수 >
    
    <문자열과 관련된 함수 >
    LENGTH / LENGTHB
    
    - LENGTH(STR) : 해당 전달된 문자열의 글자 수 반환
    - LENGTHB(STR) : 해당 전달된 문자열의 바이트 수 반환
    
    결과 값은 NUMBER 타입으로 반환
    STR : '문자열' 리터럴 / 문자열 타입의 컬럼명
    
    오라클에서의 문자의 사이즈
    숫자, 영문, 특수문자 : '!', '~', 'A', 'a', '1', ... => 한 글자당 1BYTE로 취급
    한글 : 'ㄱ', '기', '김', 'ㅣ', ... => 한 글자당 3 BYTE로 취급
*/

SELECT LENGTH('오라클!'), LENGTHB('오라클!')
FROM DUAL; -- DUAL 테이블 == 가상테이블 == DUMMY 테이블
                 -- : 산술 연산이나 가상 컬럼 등의 값을 단 한번만 출력하고 싶을 때 FROM 절에 작성하는 테이블명

SELECT EMAIL
        , LENGTH(EMAIL)
        , LENGTHB(EMAIL)
        , EMP_NAME
        , LENGTH(EMP_NAME)
        , LENGTHB(EMP_NAME)
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    INSTR
    
    - INSTR(STR) : 문자열로부터 특정 문자의 위치값을 반환
    
    INSTR(STR, '특정문자', 찾을위치의시작값, 순번)
    
    결과값은 NUMBER 타입으로 반환   
    찾을위치의시작값, 순번은 생략 가능
    
    찾을위치의 시작값
    1 : 앞에서부터 찾겠다.
    -1 : 뒤에서부터 찾겠다.
*/

SELECT INSTR('AABAACAABBAA', 'B')
FROM DUAL; -- 3 : 찾고자 하는 위치, 몇번째 해당 문자를 찾을 것인지에 대한 순번을 명시하지 않았음에도
                 --     기본적으로 앞에서부터 첫번째 글자의 위치를 찾아주고 있음

SELECT INSTR('AABAACAABBAA', 'B', 1)
FROM DUAL; -- 3 : 앞에서부터 첫번째에 위치하는 B의 위치값을 알려주겠다. (1부터 시작)

SELECT INSTR('AABAACAABBAA', 'B', -1)
FROM DUAL; -- 10 : 뒤에서부터 첫번째에 위치하는 B의 위치값을 알려주겠다. (앞에서부터 센다)

SELECT INSTR('AABAACAABBAA', 'B', 1, 2)
FROM DUAL; -- 9 : 앞에서부터 두번째에 위치하는 B의 위치값을 알려주겠다.

SELECT INSTR('AABAACAABBAA', 'B', -1, 2)
FROM DUAL; -- 9 : 뒤에서부터 두번째에 위치하는 B의 위치값을 알려주겠다. (앞에서부터 센다)

SELECT INSTR('AABAACAABBAA', 'E', 1, 1)
FROM DUAL; -- 0 : 포함되어있지 않을 때는 0 반환

-- EMAIL 에서 @ 의 위치를 찾아보자
SELECT EMAIL, INSTR(EMAIL, '@') AS "@의 위치"
FROM EMPLOYEE;

SELECT EMAIL, INSTR(EMAIL, '@') -1 AS "이메일 아이디 길이"
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    < SUBSTR >
    
    - SUBSTR(STR, POSITION, LENGTH) : 해당 문자열로부터 특정 문자열을 추출해서 반환
                                                  (자바로 치면 문자열.substring() 메소드와 유사)
                                                  
    결과값은 CHARACTER 타입으로 반환 (문자열 형태)
    LENGTH 는 생략 가능
    
    - STR : '문자열' 리터럴 / 문자열 타입의 컬럼명
    - POSITION :  문자열을 추출할 시작위치값 (음수도 제시 가능), POSITION번째 위치부터 추출
    - LENGTH : 추출할 문자의 개수 (생략 시 끝까지 추출)
    
    
*/

SELECT SUBSTR('SHOWMETHEMONEY', 7)
FROM DUAL; -- THEMONEY : 7번째 글자부터 끝까지 추출 (시작위치는 1부터)

SELECT SUBSTR('SHOWMETHEMONEY', 5, 2)
FROM DUAL; -- ME

SELECT SUBSTR('SHOWMETHEMONEY', 1, 6)
FROM DUAL; -- SHOWME

SELECT SUBSTR('SHOWMETHEMONEY', -8, 3)
FROM DUAL; -- THE : 뒤에서부터 8번째 글자에서 3개 추출
--> 시작 위치가 음수일 경우 뒤에서부터 N번째 위치로부터 문자 추출

-- 주민번호에서 성별 부분을 추출해서 남자=1, 여자=2 체크
SELECT EMP_NAME, SUBSTR(EMP_NO, 8, 1) AS 성별
FROM EMPLOYEE;

-- 남자 사원들의 사원명, 급여 조회
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
-- WHERE SUBSTR(EMP_NO, 8, 1) = 1 OR SUBSTR(EMP_NO, 8, 1) = 3;
WHERE SUBSTR(EMP_NO, 8, 1) IN ('1', '3');

-- 여자 사원들만 조회
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('2', '4')
-- WHERE SUBSTR(EMP_NO, 8, 1) NOT IN ('1', '3')
ORDER BY SALARY;

-- 이메일로부터 ID 부분만 추출
SELECT EMP_NAME, EMAIL, SUBSTR(EMAIL, 1, INSTR(EMAIL, '@')-1) AS ID
FROM EMPLOYEE;
--> 함수를 중첩하여 쓸 수 있음. = 콜백함수

----------------------------------------------------------------------------------------------------
/*
    LPAD / RPAD
    
    - LPAD/RPAD(STR, 최종문자길이(바이트), 덧붙이고자하는 문자)
    : 제시한 문자열에 임의의 문자열을 덧붙여서 최종 N길이만큼의 문자열 반환
    L : 왼, R : 오른쪽
    
    결과값은 CHARACTER 타입으로 반환
    덧붙이고자 하는 문자는 생략 가능

    - STR : '문자열' 리터럴 / 문자열 타입 컬럼명
*/

SELECT LPAD(EMAIL, 16) -- 덧붙이고자 하는 문자 생략 시, 기본값이 공백
FROM EMPLOYEE;

SELECT RPAD(EMAIL, 20, '#')
FROM EMPLOYEE;

-- 주민등록번호 850505-2222222 일 때 => 뒷 6 자리가 노출되지 않게 마스킹처리
SELECT RPAD('850805-2', 14, '*')
FROM DUAL;

-- 모든 직원의 주민번호 조회
-- 1. SUBSTR로 주민번호 앞 8번째 자리까지 추출
-- 2. RPAD를 중첩하여 주민번호 뒤 6자리에 * 붙이기
SELECT EMP_NAME, RPAD(SUBSTR(EMP_NO, 1, 8), 14, '*') AS 주민등록번호
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    LTRIM / RTRIM
    
    - LTRIM/RTRIM(STR, 제거시키고자하는문자)
    : 문자열의 왼쪽 또는 오른쪽에서 제거하고자 하는 문자를 찾아 제거한 나머지 문자열 반환
    
    결과값은 CHARACTER 타입으로 반환
    제거시키고자 하는 문자는 생략 가능
    
    - STR : '문자열' 리터럴/ 문자열 타입 컬럼명
*/

SELECT LTRIM('                  K            H')
FROM DUAL; -- 제거할 문자 생략시 기본값 공백

SELECT RTRIM('K       H          ')
FROM DUAL;

SELECT LTRIM('0001230456000', '0')
FROM DUAL;

SELECT LTRIM('123123KH123', '123')
FROM DUAL; -- KH123 출력 : 두 세트도 제거 가능

SELECT LTRIM('ACABACCKH', 'ABC')
FROM DUAL; -- KH 출력
--> 제거시키고자 하는 문자열을 통으로 지워주는 것이 아니라 문자 하나하나가 다 존재하면 다 지워주는 원리

----------------------------------------------------------------------------------------------------
/*
    TRIM
    
    - TRIM(BOTH/LEADING/TRAILING '제거시키고자하는 문자' FROM STR)
    : 문자열의 양쪽/앞/뒤에 있는 특정 문자를 제거한 나머지 문자열을 반환
    
    결과값은 CHARACTER 타입으로 반환
    BOTH/LEADING/TRAILING 생략 가능, 생략시 기본값은 BOTH
    제거하고자 하는 문자도 생략 가능
    
    - STR : '문자열' 리터럴/ 문자열 타입 컬럼명
*/

SELECT TRIM('           K         H        ') -- == [ BOTH ' ' FROM '            K    H   ' ]와 같은 의미
FROM DUAL;

SELECT TRIM('Z' FROM 'ZZZKHZZZ') -- 생략시 BOTH가 기본값
FROM DUAL;

SELECT TRIM(LEADING 'Z' FROM 'ZZZKHZZZ') -- LEADING : 앞쪽 제거 == LTRIM 함수와 동일
FROM DUAL;

SELECT TRIM(TRAILING 'Z' FROM 'ZZZKHZZZ') -- TRAILING : 뒤쪽 제거 == RTRIM 함수와 동일
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    LOWER / UPPER / INITCAP
    
    - LOWER(STR) : 해당 문자열을 소문자로 변경
    - UPPER(STR) : 해당 문자열을 대문자로 변경
    - INITCAP(STR) : 띄어쓰기 기준으로 각 단어 첫 글자만 대문자로 변경

    결과값은 CHARACTER 타입으로 반환
    
    - STR : '문자열' 리터럴 / 문자열 타입 컬럼명
*/

SELECT LOWER('Welcome To My World!')
FROM DUAL;

SELECT UPPER('Welcome To My World!')
FROM DUAL;

SELECT INITCAP('welcome to my world!')
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    CONCAT
    
    - CONCAT(STR1, STR2)
    : 전달된 두 문자열을 하나로 합쳐서 반환
    
    결과값은 CHARACTER 타입으로 반환
    
    - STR : '문자열' 리터럴 / 문자열 타입 컬럼명
*/

SELECT CONCAT('가나다', 'ABC')
FROM DUAL;
--> CONCAT 함수는 한 번에 2개의 문자만 합칠 수 있음, 매개변수 2개
-- SELECT CONCAT('가나다', '가나다', '가나다') -- 불가

-- 단 CONCAT만 사용해야 한다면 함수 중첩으로 사용할 수는 있다.
SELECT CONCAT( CONCAT('ABC', 'ABCD'), '123')
FROM DUAL;

-- || 연산자는 합치는 개수에 제한 없음
SELECT '가나다' || 'ABC'
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    REPLACE
    
    - REPLACE(STR, 찾을문자, 바꿀문자)
    : STR로부터 찾을 문자를 찾아서 바꿀 문자로 바꿔서 반환
    
    결과값은 CHARACTER 타입으로 반환
    
    - STR : '문자열' 리터럴 / 문자열 타입 컬럼명
*/

SELECT REPLACE('서울시 강남구 역삼동', '역삼동', '삼성동')
FROM DUAL;

SELECT EMP_NAME, EMAIL, REPLACE(EMAIL, 'kh', 'iei')
FROM EMPLOYEE;

-- 참고 - 중간에 있는 문자를 제거할 때 사용 가능
-- TRIM 함수는 가운데에 있는 문자는 제거 못 함
SELECT REPLACE('          K       H    ', ' ', '')
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    < 숫자와 관련된 함수 >
    
    ABS
    
    - ABS(NUMBER) : 절대값을 반환
*/

SELECT ABS(-10)
FROM DUAL;

SELECT ABS(-2.938)
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    MOD
    
    - MOD(NUMBER1, NUMBER2) : 두 수를 나눈 나머지 값 반환
*/

SELECT MOD(10, 3)
FROM DUAL;

SELECT MOD(-10, 3)
FROM DUAL;

SELECT MOD(10.9, 3)
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    ROUND
    
    - ROUND(NUMBER, 위치값) : 반올림 처리
    
    위치 : 소수점 아래 N번째 수에서 반올림
    위치값 생략 시 기본값은 0, 위치값을 음수값으로 제시 가능
*/

SELECT ROUND(123.456) -- 0번째 자리까지만 보인다, 123
FROM DUAL;

SELECT ROUND(123.456, 1) -- 1번째 자리까지 보인다, 123.5
FROM DUAL;

SELECT ROUND(123.456, 2) -- 2번째 자리까지 보인다, 123.46
FROM DUAL;

SELECT ROUND(123.456, 3) -- 3번째 자리까지 보인다 -- 반올림 처리 되지 않음
FROM DUAL;

SELECT ROUND(123.456, 4) -- 연산할 자릿수가 존재하지 않으면 그대로 반환
FROM DUAL;

SELECT ROUND(123.456, -1) -- 소수점 앞 첫째 자리에서 반올림, 120
FROM DUAL;

SELECT ROUND(123.456, -2) -- 소수점 앞 둘째 자리에서 반올림, 100
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    CEIL
    
    - CEIL(NUMBER) : 소수점 아래의 수를 무조건 올림 처리
*/

SELECT CEIL(123.156)
FROM DUAL; -- 124

SELECT CEIL(249.012)
FROM DUAL; --250

----------------------------------------------------------------------------------------------------
/*
    FLOOR
    
    - FLOOR(NUMBER) : 소수점 아래의 수를 무조건 버림 처리
*/

SELECT FLOOR(123.965)
FROM DUAL; -- 123

SELECT FLOOR(207.48)
FROM DUAL; -- 207

-- 소수점 아래를 처리해줄 수 있는 효과
-- 각 직원별로 고용일로부터 오늘까지 근무 일수 조회
-- 근무일수 = 오늘날짜 - 고용일
SELECT EMP_NAME, FLOOR(SYSDATE - HIRE_DATE) AS 근무일
FROM EMPLOYEE;

SELECT EMP_NAME, CONCAT(FLOOR(SYSDATE - HIRE_DATE), '일') AS 근무일
FROM EMPLOYEE;

SELECT EMP_NAME, 
          CONCAT(FLOOR((SYSDATE - HIRE_DATE) / 365), '년') AS 근무년도,
          CONCAT(MOD(FLOOR(SYSDATE - HIRE_DATE), 365), '일') AS 근무일수
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    TRUNC
    
    - TRUNC(NUMBER, 위치값) : 위치가 지정 가능한 버림 처리
    
    위치값은 생략 가능, 생략 시 기본값 0
*/

SELECT TRUNC(123.765)
FROM DUAL; -- 123

SELECT TRUNC(123.765, 1)
FROM DUAL; -- 123.7

SELECT TRUNC(123.765, -1)
FROM DUAL; -- 120

----------------------------------------------------------------------------------------------------
/*
    < 날짜 관련 함수 >
    
    DATE 타입을 다루는 함수들
    DATE 타입 : 년, 월, 일, 시, 분, 초를 다 포함한 자료형
*/

-- SYSDATE : 현재 이 DB가 깔려있는 컴퓨터 시스템 설정 상의 날짜
SELECT SYSDATE
FROM DUAL; -- YY/MM/DD 형식으로 출력, 날짜로써 출력

SELECT '22/08/24'
FROM DUAL; -- 같은 형식이지만 문자열로써 출력

-- MONTHS_BETWEEN(DATE1, DATE2) : 두 날짜 사이의 개월 수 반환
-- 각 직원별로 고용일로부터 오늘까지의 근무 일수와 근무 개월수를 조회
SELECT EMP_NAME
        , FLOOR(SYSDATE - HIRE_DATE) || '일' AS 근무일수
        , FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) || '개월' AS 근무개월수
FROM EMPLOYEE;
--> 두 날짜 사이의 개월수의 숫자 타입으로 반환해주되,
-- 첫번째 매개변수에 해당되는 DATE1 부분이 더 과거일 경우 결과가 음수로 나옴

-- ADD_MONTHS(DATE, NUMBER) : 특정 날짜에 해당 숫자만큼의 개월수를 더한 날짜 반환
-- 오늘 날짜로부터 5개월 후
SELECT ADD_MONTHS(SYSDATE, 5)
FROM DUAL;

-- 오늘 날짜로부터 1달 전
SELECT ADD_MONTHS(SYSDATE, -1)
FROM DUAL;

-- 전체 사원들의 직원명, 입사일, 입사 후 6개월이 흐른 후의 날짜 조회
SELECT EMP_NAME, HIRE_DATE, ADD_MONTHS(HIRE_DATE, 6)
FROM EMPLOYEE;

-- NEXT_DAY(DATE, 요일(문자/숫자)) : 특정 날짜에서 가장 가까운 해당 요일을 찾아 날짜 반환
SELECT NEXT_DAY(SYSDATE, '일요일')
FROM DUAL;

SELECT NEXT_DAY(SYSDATE, '화요일')
FROM DUAL; -- NEXT 이기 때문에 지나간 요일이 더 가까워도 미래의 요일이 반환

SELECT NEXT_DAY(SYSDATE, '일')
FROM DUAL;

SELECT NEXT_DAY(SYSDATE, 1)
FROM DUAL;
-- 1: 일요일, 2: 월요일, ... 7 : 토요일

-- SELECT NEXT_DAY(SYSDATE, 'SUN')
-- FROM DUAL;
-- 영어로 사용 불가, SUN, SUNDAY, ...
--> 현재 이 컴퓨터 시스템 상 설정된 언어 기반이기 때문에

-- 현재 이 컴퓨터의 시스템 언어 변경한 후에는 가능
-- 변경 방법
ALTER SESSION SET NLS_LANGUAGE = AMERICAN;

SELECT NEXT_DAY(SYSDATE, 'SUNDAY')
FROM DUAL;

ALTER SESSION SET NLS_LANGUAGE = KOREAN;

-- LAST_DAY(DATE) : 해당 특정 날짜 달의 마지막 날짜 반환
SELECT LAST_DAY(SYSDATE)
FROM DUAL;

-- 직원명, 입사일, 입사한 달의 마지막 날짜 조회
SELECT EMP_NAME, HIRE_DATE, LAST_DAY(HIRE_DATE)
FROM EMPLOYEE;

/*
     EXTRACT : 년도 또는 월 또는 일 정보만 추출해서 반환 (NUMBER 타입)
     
     - EXTRACT(YEAR FROM 날짜) : 특정 년도 추출
     - EXTRACT(MONTH FROM 날짜) : 특정 월 추출
     - EXTRACT(DAY FROM 날짜) : 특정 일 추출
*/

SELECT EXTRACT(YEAR FROM SYSDATE) AS 년
        , EXTRACT(MONTH FROM SYSDATE) AS 월
        , EXTRACT(DAY FROM SYSDATE) AS 일
FROM DUAL;

-- 사원명, 입사년도, 입사월, 입사일 조회
SELECT EMP_NAME 사원명
        , EXTRACT(YEAR FROM HIRE_DATE) || '년' AS 입사년도
        , EXTRACT(MONTH FROM HIRE_DATE) || '월' AS 입사월
        , EXTRACT(DAY FROM HIRE_DATE) || '일' AS 입사일
FROM EMPLOYEE
ORDER BY "입사년도", "입사월", "입사일";

----------------------------------------------------------------------------------------------------
/*
    < 형변환 함수 >
    
    NUMBER / DATE => CHARACTER
    
    - TO_CHAR(NUMBER / DATE, '포맷')
    : 숫자형/날짜형 데이터를 문자형 타입으로 변환
*/

SELECT TO_CHAR(1234)
FROM DUAL; -- 1234 => '1234'

SELECT TO_CHAR(1234, '00000') -- 빈 칸을 0으로 채워주는 포맷
FROM DUAL; -- 1234 => '01234'
--> 다섯 글자 중 오른쪽에서부터 숫자로 채워서 반환

SELECT TO_CHAR(1234, '99999') -- 빈 칸을 채우지 않는 포맷
FROM DUAL; -- 1234 => '1234' 

SELECT TO_CHAR(1234, 'L00000') -- L : LOCAL => 현재 시스템에 설정된 화폐 단위를 붙여주는 포맷
FROM DUAL; -- 1234 => '\01234'

SELECT TO_CHAR(1234, 'L99999')
FROM DUAL; -- 1234 => '\1234'

-- 만약 달러 $로 표현하고 싶다면
SELECT TO_CHAR(1234, '$99999')
FROM DUAL;

-- 금액과 같은 큰 숫자의 경우 3자리마다 , 로 구분해서 출력
SELECT TO_CHAR(12341234, 'L99,999,999,999')
FROM DUAL; -- 1234 => '\12,341,234' : 3자리마다 , 로 구분되는 포맷

-- 급여 정보를 3자리마다 , 로 구분하여 출력
SELECT EMP_NAME, TO_CHAR(SALARY, 'L999,999,999') 급여
FROM EMPLOYEE
ORDER BY 급여 DESC;

-- DATE(년월일시분초) => CHARACTER
SELECT SYSDATE
FROM DUAL; -- 날짜 형식

SELECT TO_CHAR(SYSDATE)
FROM DUAL; -- 문자 형식 '22/08/24'

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD')
FROM DUAL; -- 문자 형식 '22-08-24'

SELECT TO_CHAR(SYSDATE, 'PM HH:MI:SS') -- 포맷 지정시 AM/PM 은 오전/오후 형태로 출력해달라는 말 (!=지정)
FROM DUAL; -- 오후 03:43:37

SELECT TO_CHAR(SYSDATE, 'HH24:MI:SS') -- HH24 : 24시간 형식으로
FROM DUAL; -- 15:43:13

SELECT TO_CHAR(SYSDATE, 'MON DY, YYYY') -- MON : N월 형식, DY : '요일'을 뺀 형태
FROM DUAL; -- 8월  수, 2022

-- 년도로 쓸 수 있는 포맷
SELECT TO_CHAR(SYSDATE, 'YYYY')
        , TO_CHAR(SYSDATE, 'RRRR')
        , TO_CHAR(SYSDATE, 'YY')
        , TO_CHAR(SYSDATE, 'RR')
        , TO_CHAR(SYSDATE, 'YEAR') -- YEAR : 영어로 년도수를 출력해주는 포맷
FROM DUAL;

-- 월로 쓸 수 있는 포맷
SELECT TO_CHAR(SYSDATE, 'MM')
        , TO_CHAR(SYSDATE, 'MON') -- 월 단위
        , TO_CHAR(SYSDATE, 'MONTH') -- 월 단위
        , TO_CHAR(SYSDATE, 'RM') -- RM : 로마숫자
FROM DUAL;

-- 일로 쓸 수 있는 포맷
SELECT TO_CHAR(SYSDATE, 'D') -- 일요일 기준으로 몇 번째 일인지
        , TO_CHAR(SYSDATE, 'DD') -- 1일 기준으로 몇 번째 일인지
        , TO_CHAR(SYSDATE, 'DDD') -- 1월 1일 기준으로 몇 번째 일인지
FROM DUAL;

-- 요일로 쓸 수 있는 포맷
SELECT TO_CHAR(SYSDATE, 'DY')
        , TO_CHAR(SYSDATE, 'DAY')
FROM DUAL;

-- 2022년 08월 24일 (수) 포맷
SELECT TO_CHAR(SYSDATE, 'YYYY"년" MM"월" DD"일" (DY)')
FROM DUAL;
--> 포맷이 아닌 다른 문자와 함께 쓰고자 할 경우는 ""로 묶어준다.

-- 사원명, 입사일(위의 포맷 적용) 조회
SELECT EMP_NAME
        , TO_CHAR(HIRE_DATE, 'YYYY"년" MM"월" DD"일" (DY)')
FROM EMPLOYEE;

-- 2010년 이후에 입사한 사원들의 사원명, 입사일(위의 포맷 적용) 조회
SELECT EMP_NAME
        , TO_CHAR(HIRE_DATE, 'YYYY"년" MM"월" DD"일" (DY)') AS 입사일
FROM EMPLOYEE
WHERE EXTRACT(YEAR FROM HIRE_DATE) >= 2010;

----------------------------------------------------------------------------------------------------
/*
    NUMBER / CHARACTER => DATE
    
    - TO_DATE(NUMBER/CHARACTER, '포맷') : 숫자형/문자형 데이터를 날짜형으로 변환
*/

SELECT TO_DATE(20210101) -- NUMBER -> DATE
FROM DUAL; -- 기본 포맷은 YY/MM/DD로 변환

SELECT TO_DATE('20210101') -- CHARACTER -> DATE
FROM DUAL; -- 기본 포맷은 YY/MM/DD로 변환

-- 주의할 점 : 매개변수로 NUMBER 타입을 넘길 경우, 0으로 시작하는 년도는 반드시 문자열 타입으로 넘겨야 한다.
SELECT TO_DATE(000101) -- NUMBER TYPE에서 0으로 시작하는 수는 없음 -- 에러
FROM DUAL;

SELECT TO_DATE('000101') -- CHARACTER 타입은 가능
FROM DUAL;

SELECT TO_DATE('20100101', 'YYYYMMDD')
FROM DUAL; -- 10/01/01 포맷으로 변환됨 (값 보기 버튼 눌러보면 날짜 형식임을 알 수 있음)
--> 2010이 YYYY이고, 01이 MM, 01이 DD로 매칭시킨다의 포맷으로 봐야 한다. 형식 지정 X

SELECT TO_DATE('041030 143021', 'YYMMDD HH24MISS')
FROM DUAL;

-- 주의할 점 2 : YY와 RR의 차이점
SELECT TO_DATE('140630', 'YYMMDD')
FROM DUAL; -- 14/06/30 -- 2014년도

SELECT TO_DATE('980630', 'YYMMDD')
FROM DUAL; -- 98/06/30 -- 2098년도
--> TO_DATE 함수를 이용해서 DATE 형식으로 변환 시
-- 두 자리 년도에 대해 YY 포맷을 적용할 경우 => 무조건 현재 세기로 잡힘

SELECT TO_DATE('140630', 'RRMMDD')
FROM DUAL; -- 14/06/30 -- 2014년도

SELECT TO_DATE('980630', 'RRMMDD')
FROM DUAL; -- 98/06/30 -- 1998년도
-- 두 자리 년도에 대해 RR 포맷을 적용할 경우 50 이상의 숫자면 이전 세기, 50 미만의 숫자면 현재 세기

----------------------------------------------------------------------------------------------------
/*
    CHARACTER => NUMBER
    
    - TO_NUMBER(CHARACTER, '포맷') : 문자형 데이터를 숫자형으로 변환
*/

-- 자동형변환 되는 예시
SELECT '123' + '123'
FROM DUAL; -- 246 : NUMBER로 자동형변환 후 산술 연산까지 진행

-- 자동형변환 안 되는 예시
SELECT '10,000,000' + '550,000'
FROM DUAL;
-- 문자열 안에 ',' 문자가 포함되어있기 때문에 자동형변환이 안 됨
-- 문자열이 순수 숫자로만 이루어져 있을 경우에만 자동형변환 진행
--> 이런 경우에 사용할 수 있는 함수가 TO_NUMBER

-- 위의 예시를 TO_NUMBER 함수를 통해 변경 : 강제형변환 (포맷을 반드시 지정)
SELECT TO_NUMBER('10,000,000', '99,999,999') + TO_NUMBER('550,000', '999,999')
FROM DUAL;

-- 위의 결과를 10,550,000으로 보고 싶다면 : TO_CHAR 함수 중첩 사용
SELECT TO_CHAR(TO_NUMBER('10,000,000', '99,999,999') + TO_NUMBER('550,000', '999,999'), '999,999,999')
FROM DUAL;

-- 0123?
SELECT TO_NUMBER('0123')
FROM DUAL; -- 123

----------------------------------------------------------------------------------------------------
/*
    < NULL 처리 함수 >
*/

-- NVL(컬럼명, 해당컬럼값이NULL일경우반환할값)
-- : 해당 컬럼값이 존재할 경우 기존의 컬럼값 그대로 반환
--  만약 해당 컬럼값이 NULL일 경우 매개변수로 제시한 특정값 반환

-- 사원명, 보너스, 보너스가 없는 경우는 0으로 출력
SELECT EMP_NAME, BONUS, NVL(BONUS, 0)
FROM EMPLOYEE;

-- 보너스 포함 연봉 조회
SELECT EMP_NAME, SALARY, BONUS, (SALARY + SALARY * NVL(BONUS, 0)) * 12 "보너스 포함 연봉"
FROM EMPLOYEE;

-- 사원명, 부서코드 조회(부서 코드가 없는 경우 '없음' 조회)
SELECT EMP_NAME, NVL(DEPT_CODE, '없음')
FROM EMPLOYEE;

-- NVL2(컬럼명, 결과값1, 결과값2)
-- : 해당 컬럼값이 존재할 경우 결과값1, NULL일 경우 결과값2 반환

-- 사원명, 보너스, 보너스가 있는 경우는 0.7로 인상, 보너스가 없는 경우는 0 출력
SELECT EMP_NAME, BONUS, NVL2(BONUS, 0.7, 0)
FROM EMPLOYEE;

-- 사원명, 부서코드 조회(부서코드 있으면 '부서배치완료', 없는 경우 '부서배치미완료' 출력)
SELECT EMP_NAME, NVL2(DEPT_CODE, '부서배치완료', '부서배치미완료') 부서배치여부
FROM EMPLOYEE;

-- NULLIF(비교대상1, 비교대상2)
-- : 두 매개변수의 값이 일치할 경우 NULL 반환, 두 매개변수의 값이 일치하지 않을 경우 비교대상1 반환
SELECT NULLIF('123', '123')
FROM DUAL; -- NULL 반환

SELECT NULLIF('123', '456')
FROM DUAL; -- '123' 반환

----------------------------------------------------------------------------------------------------
/*
    < 선택 함수 >
    
    DECODE(비교대상(컬럼명, 산술연산, 함수식 등), 조건값1, 결과값1, 조건값2, 결과값2, ... , 결과값)
    
    = 자바에서의 switch 문과 유사 (동등 비교 조건문)
    switch(비교대상) {
    case 조건값1 : 결과값1; break;
    case 조건값2 : 결과값2; break;
    ...
    default : 결과값;
    }
*/

-- 사번, 사원명, 성별
SELECT EMP_ID
        , EMP_NAME
        , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남' 
                                                 , '2', '여'
                                                 , '3', '남'
                                                 , '4', '여') 성별
FROM EMPLOYEE;

-- 직원들의 급여를 인상시켜서 조회
-- 직급 코드가 'J7'인 사원은 급여를 10% 인상
-- 직급 코드가 'J6'인 사원은 급여를 15% 인상
-- 직급 코드가 'J5'인 사원은 급여를 20% 인상
-- 직급 코드가 그 이외읜 사원은 급여를 5% 인상
-- 직원명, 직급코드, 인상 전 급여, 인상 후 급여
SELECT EMP_NAME
        , JOB_CODE
        , SALARY "인상 전 급여"
        , DECODE(EMP_NAME, 'J7', SALARY+SALARY*0.1 -- SALARY * 1.1
                                   , 'J6', SALARY+SALARY*0.15
                                   , 'J5', SALARY+SALARY*0.2
                                   , SALARY+SALARY*0.05) "인상 후 급여"
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    < CASE WHEN THEN 구문 >
    
    - CASE WHEN 조건식 1 THEN 결과값1
             WHEN 조건식 2 THEN 결과값2
             ...
             ELSE 결과값
      END
    
    -- DECODE 선택함수와 비교하면 DECODE 함수는 해당 조건검사시 동등비교만 수행
       CASE WHEN THEN 구문은 특정 조건 제시시 조건식의 자유도가 더 높음. (조건식 커스터마이징 가능)
       
       - 자바에서의 if - else if 문과 비슷함
       if (조건식1) {
            실행할구문;
       } else if (조건식2) {
            실행할구문;
       } else {
            실행할 구문;
       }
*/

-- 사번, 사원명, 주민번호로 추출한 성별 (DECODE 버전)
SELECT EMP_ID
        , EMP_NAME
        , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '남'
                                                , '2', '여'
                                                , '3', '남'
                                                , '4', '여') 성별
FROM EMPLOYEE;

-- CASE WHEN THEN 버전
SELECT EMP_ID
        , EMP_NAME
        , CASE WHEN SUBSTR(EMP_NO, 8, 1) = '1' THEN '남'
                 WHEN SUBSTR(EMP_NO, 8, 1) = '2' THEN '여'
                 WHEN SUBSTR(EMP_NO, 8, 1) = '3' THEN '남'
                 WHEN SUBSTR(EMP_NO, 8, 1) = '4' THEN '여'
          END 성별
FROM EMPLOYEE;

-- 경우의 수를 하나로 묶기
SELECT EMP_ID
        , EMP_NAME
        , CASE WHEN SUBSTR(EMP_NO, 8, 1) IN ('1', '3') THEN '남'
                 -- WHEN SUBSTR(EMP_NO, 8, 1) IN ('2', '4') THEN '여'
                 ELSE '여'
          END 성별
FROM EMPLOYEE;

-- 사원명, 급여, 급여 등급(고급, 중급, 초급)
-- 고급 : SALARY 500만원 초과
-- 중급 : SALARY 350~500 만원
-- 초급 : SALARY 350만원 이하
SELECT EMP_NAME
        , SALARY
        , CASE WHEN SALARY>5000000 THEN '고급'
                 WHEN (3500000 < SALARY) AND (SALARY <= 5000000) THEN '중급'
                 WHEN SALARY <= 3500000 THEN '초급'
                 -- ELSE '초급'
         END "급여 등급"
FROM EMPLOYEE
WHERE SALARY>5000000 -- 고급 등급만 보기
ORDER BY SALARY DESC; -- 월급이 높은 순서대로 보기

----------------------------------------------------------------------------------------------------
/*
    < 그룹 함수 >
    
    그룹 함수 : N개의 값을 읽어서 1개의 결과 반환 (하나의 그룹별로 함수 실행 결과 반환)
*/

-- 1. SUM(숫자타입컬럼명) : 해당 컬럼값들의 총 합계를 구해서 반환 (NUMBER 타입 반환)
-- 전체 사원들의 총 급여 합계
SELECT SUM(SALARY)
FROM EMPLOYEE;

-- 부서 코드가 'D5' 인 사원들의 총 급여 합계
SELECT SUM(SALARY)
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5';

-- 남자 사원의 총 급여 합
SELECT SUM(SALARY)
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('1', '3');

----------------------------------------------------------------------------------------------------

-- 2. AVG(숫자타입컬럼명) : 해당 컬럼값들의 총 평균을 구해서 반환
-- 전체 사원들의 평균 급여
SELECT AVG(SALARY)
FROM EMPLOYEE;

-- 전체 사원들의 평균 급여(반올림)
SELECT ROUND(AVG(SALARY))
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------

-- 3. MIN(아무타입컬럼명) : 해당 컬럼값들 중 가장 작은 값을 매개변수의 타입으로 반환
-- 전체 사원들 중 최저급여, 가장 작은 이름값, 가장 작은 이메일값, 가장 예전에 입사한 날짜 구하기
SELECT MIN(SALARY) 최저급여
        , MIN(EMP_NAME) "가장 작은 이름"
        , MIN(EMAIL) "가장 작은 이메일"
        , MIN(HIRE_DATE) "가장 작은 입사일"
FROM EMPLOYEE;
--> 1행 4열로 나온다

SELECT *
FROM EMPLOYEE
ORDER BY HIRE_DATE ASC;
--> MIN 함수의 원리 : 해당 컬럼값 기준 오름차순 정렬했을 때 가장 위쪽에 나오는 값

----------------------------------------------------------------------------------------------------

-- 4. MAX(아무타입컬럼명) : 해당 컬럼값들 중 가장 큰 값을 매개변수의 타입으로 반환
-- 전체 사원 중 가장 큰 급여, 가장 큰 이름값, 가장 큰 이메일값, 가장 최근 입사일 조회
SELECT MAX(SALARY) 최고급여
        , MAX(EMP_NAME) "가장 큰 이름"
        , MAX(EMAIL) "가장 큰 이메일"
        , MAX(HIRE_DATE) "최근 입사일"
FROM EMPLOYEE;

SELECT *
FROM EMPLOYEE
ORDER BY HIRE_DATE DESC;
--> MAX 함수의 원리 : 해당 컬럼값 기준으로 내림차순 정렬했을 대 가장 위에 나오는 값 반환

----------------------------------------------------------------------------------------------------

-- 5. COUNT(*/컬럼명/DISTINCT 컬럼명) : 컬럼에 들어있는 값의 개수 반환 (NUMBER 타입)
-- COUNT(*) : 조회 결과에 해당되는 모든 행 개수를 다 세서 반환 : 행의 개수 반환
-- COUNT(컬럼명) : 제시한 해당 컬럼값이 NULL이 아닌 것만 세서 행의 개수 반환
-- COUNT(DISTINCT 컬럼명) : 제시한 해당 컬럼값이 중복값이 있을 경우에는 하나로 세서 반환하겠다. (NULL 미포함)

-- 전체 사원수 조회
SELECT COUNT(*)
FROM EMPLOYEE;

-- 여자 사원수 조회
SELECT COUNT(*)
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('2', '4');

-- 부서배치가 된 사원 (DEPT_CODE 값 있는) 수
SELECT COUNT(DEPT_CODE)
FROM EMPLOYEE;

-- 부서배치가 된 여자사원수
SELECT COUNT(*)
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('2','4') 
      AND DEPT_CODE IS NOT NULL;

SELECT COUNT(DEPT_CODE)
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('2', '4');

-- 사수가 있는 사원수
SELECT COUNT(MANGER_ID)
FROM EMPLOYEE;

-- 현재 사원들이 속해있는 부서의 개수
SELECT COUNT(*)
FROM DEPARTMENT; -- 전체 부서 개수 9개

SELECT COUNT(DISTINCT DEPT_CODE)
FROM EMPLOYEE; -- 사람들이 실제 있는 부서 6개
