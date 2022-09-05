/*
        < 시퀀스 SEQUENCE >
        자동으로 번호를 발생시켜주는 역할을 하는 객체
        정수값을 자동으로 순차적으로 생성해줌
        
        예) 회원번호, 사번, 게시글 번호 등 “채번”할 때 주로 쓰일 예정
        
        1. 시퀀스 객체 생성 구문
        CREATE SEQUENCE 시퀀스명
        START WITH 시작숫자
        INCREMENT BY 증가값
        MAXVALUE 최대값
        MINVALUE 최소값
        CYCLE/NOCYCLE
        CACHE 바이트크기 / NOCACHE

        - START WITH 시작숫자 : 처음 발생시킬 시작값 지정 (생략 가능)
        - INCREMENT BY 증가값 : 한번에 몇 씩 증가시킬 것인지 지정 (생략 가능)
        - MAXVALUE 최대값 : 최대값 지정 (생략 가능)
        - MINVALUE 최소값 : 최소값 지정 (생략 가능)
        - CYCLE / NOCYCLE : 값의 순환 여부를 지정 (생략 가능)
        - CACHE 바이트크기 / NOCHACE : 캐시메모리 사용 여부를 지정 (생략 가능)
                                                            CACHE_SIZE 기본값은 20BYTE
                                                            
        * 캐시메모리 : 발생될 값들을 미리 생성해서 저장해두는 공간
                            매번 호출할 때마다 새로이 번호를 생성하는 것보다
                            캐시 메모리 공간에 미리 생성된 값을 가져다 쓰면 속도가 훨씬 빠름
                            단, 접속이 끊기고 나서 재접속 후 기존에 생성되어있던 값들이 날라가고 없음
*/

/*
        * 접두사
        - 테이블명 : TB_
        - 뷰명 : VW_
        - 시퀀스명 : SEQ_
*/

CREATE SEQUENCE SEQ_TEST;

-- USER_SEQUENCES : 현재 이 접속한 계정이 소유하고 있는 시퀀스들에 대한 정보 조회용 데이터 딕셔너리
SELECT * FROM USER_SEQUENCES;

-- 간단한 옵션을 부여해서 사번을 채번할 용도의 시퀀스 생성
CREATE SEQUENCE SEQ_EMPNO
START WITH 300
INCREMENT BY 5
MAXVALUE 310
NOCYCLE
NOCACHE;
--> 이 시퀀스는 300, 305, 310만 채번 가능

----------------------------------------------------------------------------------------------------------------
/*
        2. 시퀀스 사용 구문
        
        시퀀스명.CURRVAL : 현재 시퀀스의 값 (마지막으로 성공적으로 발생된 NEXTVAL 값)
                                        => 마지막으로 성공적으로 발생된 NEXTVAL 값을 담아두는 일종의 변수같은 개념
        시퀀스명.NEXTVAL : 시퀀스의 값을 증가시키고 난 뒤 그 증가된 시퀀스 값
                                      기존의 시퀀스 값에서 INCREMENT BY 값만큼 증가된 값
                                      (시퀀스명.CURRVAL + INCREMENT BY 값)

        => 단, 시퀀스 생성 후 첫 NEXTVAL 은 START WITH 로 지정된 시작값으로 발생
*/

-- 위에서 만든 SEQ_EMPNO 가지고 테스트
SELECT SEQ_EMPNO.CURRVAL
FROM DUAL;
-- ORA-08002: sequence SEQ_EMPNO.CURRVAL is not yet defined in this session
-->> NEXTVAL을 한번이라도 수행하지 않는 이상 CURRVAL을 쓸 수 없음.
--     CURRVAL은 마지막으로 성공적으로 발생된 NEXTVAL 값을 저장해서 보여주는 임시값이므로

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
--> START WITH 시작값인 300이 나옴

SELECT SEQ_EMPNO.CURRVAL
FROM DUAL;
--> 이 시점에서 성공적으로 수행된 NEXTVAL 값인 300 이 나옴
--   (여러번 CURRVAL을 수행해도 값은 동일)

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
--> CURRVAL + INCREMENT BY 값인 305 가 나옴

SELECT * FROM USER_SEQUENCES;
-- 데이터 딕셔너리 조회 시 SEQ_EMPNO 시퀀스의 LAST_NUMBER 값이 310 으로 지정되어있음
-->> LAST_NUMBER : 현재 상황에서 NEXTVAL 을 한 번 실행할 경우 발성될 예정 값

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
--> LAST_NUMBER과 동일하게 310 발생 == 마지막으로 성공적으로 이뤄진 NEXTVAL
--   (== SEQ_EMPNO의 최대값)

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
-- ORA-08004: sequence SEQ_EMPNO.NEXTVAL exceeds MAXVALUE and cannot be instantiated
--> 지정한 MAXVALUE 값 (310)을 초과했기 때문에 오류 발생 == 실패한 NEXTVAL
--   (만약 사이클이 걸려있었다면 오류 발생하지 않았을 것)

-- 이 시점에서 CURRVAL 을 수행하면 마지막으로 성공한 NEXTVAL 값인 310이 나옴
SELECT SEQ_EMPNO.CURRVAL
FROM DUAL;

----------------------------------------------------------------------------------------------------------------
/*
        3. 시퀀스 변경
        
        [ 표현법 ]
        ALTER SEQUENCE 시퀀스명
        INCREMENT BY 증가값
        MAXVALUE 최대값
        MINVALUE 최대값
        CYCLE / NOCYCLE
        CACHE 바이트크기 / NOCACHE
        
        => START WITH 값은 변경불가 : 바꾸고 싶다면 시퀀스 삭제 후 재생성해야 한다.
*/

ALTER SEQUENCE SEQ_EMPNO
MAXVALUE 400
INCREMENT BY 10;

SELECT * FROM USER_SEQUENCES;
-- 시퀀스 변경 전 LAST_NUMBER = 315 => INCREMENT BY 값이 5, CURRVAL = 310이었음 (현재값)
-- 시퀀스 변경 후 LAST_NUMBER = 320
-->> 아무리 시퀀스가 변경되어도 CURRVAL 값은 바뀌지 않음

SELECT SEQ_EMPNO.CURRVAL
FROM DUAL;
-- 310

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
-- 320

--> 참고 ) SEQUENCE 를 삭제하고 싶다면
-- DROP SEQUENCE 시퀀스명;

DROP SEQUENCE SEQ_EMPNO;

-- 이 시점에서 시퀀스 조회하면 오류 발생
-- ORA-02289: sequence does not exist

----------------------------------------------------------------------------------------------------------------

-- 시퀀스 사용 예시

-- 매번 새로운 사번이 발생되는 시퀀스 생성
CREATE SEQUENCE SEQ_EID
START WITH 300;

-- 매번 사원이 추가될 때마다 실행할 INSERT문
INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL, HIRE_DATE)
VALUES (SEQ_EID.NEXTVAL, '홍길동', '111111-1111111', 'J2', 'S3', SYSDATE); --300

INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL, HIRE_DATE)
VALUES (SEQ_EID.NEXTVAL, '김영희', '222222-2222222', 'J3', 'S4', SYSDATE); --301

SELECT * FROM EMPLOYEE;

INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL, HIRE_DATE)
VALUES (SEQ_EID.NEXTVAL, '박말똥', '111111-2111111', 'J5', 'S2', SYSDATE); --302

-- 보통 시퀀스는 PRIMARY KEY 에 해당되는 식별 번호를 채번할 때 주로 사용 (INSERT 구문에서 NEXTVAL 형식으로)

-- 사원에 대해 추가요청이 들어왔을 경우 실행할 SQL 문
-- EMP_ID : 사용자로부터 직접적으로 입력 X, 시퀀스
-- EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL : 사용자로부터 직접적으로 입력 O 필수 O
-- EMAIL, PHONE, DEPT_CODE : 사용자로부터 직접적으로 입력 O 필수 X
-- DEPT_CODE, SALARY, BONUS, MANAGER_ID : 사용자로부터 직접적으로 입력 X 필수 X
-- HIRE_DATE : SYSDATE 기준
-- ENT_DATE : NULL 로 고정
-- ENT_YN : DEFAULT 'N'
INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, EMAIL
                                           , PHONE, JOB_CODE, SAL_LEVEL, HIRE_DATE)
                          VALUES (SEQ_EID.NEXTVAL, ?, ?, ?
                                        , ?, ?, ?, SYSDATE);
