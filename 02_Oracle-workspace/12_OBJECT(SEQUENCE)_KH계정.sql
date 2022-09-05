/*
        < ������ SEQUENCE >
        �ڵ����� ��ȣ�� �߻������ִ� ������ �ϴ� ��ü
        �������� �ڵ����� ���������� ��������
        
        ��) ȸ����ȣ, ���, �Խñ� ��ȣ �� ��ä������ �� �ַ� ���� ����
        
        1. ������ ��ü ���� ����
        CREATE SEQUENCE ��������
        START WITH ���ۼ���
        INCREMENT BY ������
        MAXVALUE �ִ밪
        MINVALUE �ּҰ�
        CYCLE/NOCYCLE
        CACHE ����Ʈũ�� / NOCACHE

        - START WITH ���ۼ��� : ó�� �߻���ų ���۰� ���� (���� ����)
        - INCREMENT BY ������ : �ѹ��� �� �� ������ų ������ ���� (���� ����)
        - MAXVALUE �ִ밪 : �ִ밪 ���� (���� ����)
        - MINVALUE �ּҰ� : �ּҰ� ���� (���� ����)
        - CYCLE / NOCYCLE : ���� ��ȯ ���θ� ���� (���� ����)
        - CACHE ����Ʈũ�� / NOCHACE : ĳ�ø޸� ��� ���θ� ���� (���� ����)
                                                            CACHE_SIZE �⺻���� 20BYTE
                                                            
        * ĳ�ø޸� : �߻��� ������ �̸� �����ؼ� �����صδ� ����
                            �Ź� ȣ���� ������ ������ ��ȣ�� �����ϴ� �ͺ���
                            ĳ�� �޸� ������ �̸� ������ ���� ������ ���� �ӵ��� �ξ� ����
                            ��, ������ ����� ���� ������ �� ������ �����Ǿ��ִ� ������ ���󰡰� ����
*/

/*
        * ���λ�
        - ���̺�� : TB_
        - ��� : VW_
        - �������� : SEQ_
*/

CREATE SEQUENCE SEQ_TEST;

-- USER_SEQUENCES : ���� �� ������ ������ �����ϰ� �ִ� �������鿡 ���� ���� ��ȸ�� ������ ��ųʸ�
SELECT * FROM USER_SEQUENCES;

-- ������ �ɼ��� �ο��ؼ� ����� ä���� �뵵�� ������ ����
CREATE SEQUENCE SEQ_EMPNO
START WITH 300
INCREMENT BY 5
MAXVALUE 310
NOCYCLE
NOCACHE;
--> �� �������� 300, 305, 310�� ä�� ����

----------------------------------------------------------------------------------------------------------------
/*
        2. ������ ��� ����
        
        ��������.CURRVAL : ���� �������� �� (���������� ���������� �߻��� NEXTVAL ��)
                                        => ���������� ���������� �߻��� NEXTVAL ���� ��Ƶδ� ������ �������� ����
        ��������.NEXTVAL : �������� ���� ������Ű�� �� �� �� ������ ������ ��
                                      ������ ������ ������ INCREMENT BY ����ŭ ������ ��
                                      (��������.CURRVAL + INCREMENT BY ��)

        => ��, ������ ���� �� ù NEXTVAL �� START WITH �� ������ ���۰����� �߻�
*/

-- ������ ���� SEQ_EMPNO ������ �׽�Ʈ
SELECT SEQ_EMPNO.CURRVAL
FROM DUAL;
-- ORA-08002: sequence SEQ_EMPNO.CURRVAL is not yet defined in this session
-->> NEXTVAL�� �ѹ��̶� �������� �ʴ� �̻� CURRVAL�� �� �� ����.
--     CURRVAL�� ���������� ���������� �߻��� NEXTVAL ���� �����ؼ� �����ִ� �ӽð��̹Ƿ�

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
--> START WITH ���۰��� 300�� ����

SELECT SEQ_EMPNO.CURRVAL
FROM DUAL;
--> �� �������� ���������� ����� NEXTVAL ���� 300 �� ����
--   (������ CURRVAL�� �����ص� ���� ����)

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
--> CURRVAL + INCREMENT BY ���� 305 �� ����

SELECT * FROM USER_SEQUENCES;
-- ������ ��ųʸ� ��ȸ �� SEQ_EMPNO �������� LAST_NUMBER ���� 310 ���� �����Ǿ�����
-->> LAST_NUMBER : ���� ��Ȳ���� NEXTVAL �� �� �� ������ ��� �߼��� ���� ��

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
--> LAST_NUMBER�� �����ϰ� 310 �߻� == ���������� ���������� �̷��� NEXTVAL
--   (== SEQ_EMPNO�� �ִ밪)

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
-- ORA-08004: sequence SEQ_EMPNO.NEXTVAL exceeds MAXVALUE and cannot be instantiated
--> ������ MAXVALUE �� (310)�� �ʰ��߱� ������ ���� �߻� == ������ NEXTVAL
--   (���� ����Ŭ�� �ɷ��־��ٸ� ���� �߻����� �ʾ��� ��)

-- �� �������� CURRVAL �� �����ϸ� ���������� ������ NEXTVAL ���� 310�� ����
SELECT SEQ_EMPNO.CURRVAL
FROM DUAL;

----------------------------------------------------------------------------------------------------------------
/*
        3. ������ ����
        
        [ ǥ���� ]
        ALTER SEQUENCE ��������
        INCREMENT BY ������
        MAXVALUE �ִ밪
        MINVALUE �ִ밪
        CYCLE / NOCYCLE
        CACHE ����Ʈũ�� / NOCACHE
        
        => START WITH ���� ����Ұ� : �ٲٰ� �ʹٸ� ������ ���� �� ������ؾ� �Ѵ�.
*/

ALTER SEQUENCE SEQ_EMPNO
MAXVALUE 400
INCREMENT BY 10;

SELECT * FROM USER_SEQUENCES;
-- ������ ���� �� LAST_NUMBER = 315 => INCREMENT BY ���� 5, CURRVAL = 310�̾��� (���簪)
-- ������ ���� �� LAST_NUMBER = 320
-->> �ƹ��� �������� ����Ǿ CURRVAL ���� �ٲ��� ����

SELECT SEQ_EMPNO.CURRVAL
FROM DUAL;
-- 310

SELECT SEQ_EMPNO.NEXTVAL
FROM DUAL;
-- 320

--> ���� ) SEQUENCE �� �����ϰ� �ʹٸ�
-- DROP SEQUENCE ��������;

DROP SEQUENCE SEQ_EMPNO;

-- �� �������� ������ ��ȸ�ϸ� ���� �߻�
-- ORA-02289: sequence does not exist

----------------------------------------------------------------------------------------------------------------

-- ������ ��� ����

-- �Ź� ���ο� ����� �߻��Ǵ� ������ ����
CREATE SEQUENCE SEQ_EID
START WITH 300;

-- �Ź� ����� �߰��� ������ ������ INSERT��
INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL, HIRE_DATE)
VALUES (SEQ_EID.NEXTVAL, 'ȫ�浿', '111111-1111111', 'J2', 'S3', SYSDATE); --300

INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL, HIRE_DATE)
VALUES (SEQ_EID.NEXTVAL, '�迵��', '222222-2222222', 'J3', 'S4', SYSDATE); --301

SELECT * FROM EMPLOYEE;

INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL, HIRE_DATE)
VALUES (SEQ_EID.NEXTVAL, '�ڸ���', '111111-2111111', 'J5', 'S2', SYSDATE); --302

-- ���� �������� PRIMARY KEY �� �ش�Ǵ� �ĺ� ��ȣ�� ä���� �� �ַ� ��� (INSERT �������� NEXTVAL ��������)

-- ����� ���� �߰���û�� ������ ��� ������ SQL ��
-- EMP_ID : ����ڷκ��� ���������� �Է� X, ������
-- EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL : ����ڷκ��� ���������� �Է� O �ʼ� O
-- EMAIL, PHONE, DEPT_CODE : ����ڷκ��� ���������� �Է� O �ʼ� X
-- DEPT_CODE, SALARY, BONUS, MANAGER_ID : ����ڷκ��� ���������� �Է� X �ʼ� X
-- HIRE_DATE : SYSDATE ����
-- ENT_DATE : NULL �� ����
-- ENT_YN : DEFAULT 'N'
INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, EMAIL
                                           , PHONE, JOB_CODE, SAL_LEVEL, HIRE_DATE)
                          VALUES (SEQ_EID.NEXTVAL, ?, ?, ?
                                        , ?, ?, ?, SYSDATE);
