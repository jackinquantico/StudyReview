/*
        < VIEW �� >
        SELECT (������) �� �����ص� �� �ִ� ��ü
        (���� ���� �� SELECT ���� �����صθ� �� SELECT ���� �Ź� �ٽ� ����� �ʿ䰡 ����)
        �ӽ����̺� ���� ���� (���� �����Ͱ� ����ִ� ���� �ƴϴ�)
*/

------ �ǽ� ���� ------
-- '�ѱ�'���� �ٹ��ϴ� ������� ���, �̸�, �μ���, �޿�, �ٹ�������, ���޸� ��ȸ

-->> ����Ŭ ���� ����
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
FROM EMPLOYEE E, DEPARTMENT D, LOCATION L, NATIONAL N, JOB J
WHERE DEPT_CODE = DEPT_ID(+)
    AND LOCATION_ID = LOCAL_CODE
    AND L.NATIONAL_CODE = N.NATIONAL_CODE
    AND E.JOB_CODE = J.JOB_CODE
    AND NATIONAL_NAME = '�ѱ�';

-->> ANSI ����
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
JOIN NATIONAL USING (NATIONAL_CODE)
WHERE NATIONAL_NAME = '�ѱ�';

/*
        1. VIEW ���� ���
        
        [ ǥ���� ]
        CREATE VIEW ���
        AS (��������);
*/

-- ��ü ������� ���, �̸�, �μ���, �޿�, �ٹ�������, ���޸� ���� �� ����
CREATE VIEW VM_EMPLOYEE
AS (SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
      FROM EMPLOYEE E, DEPARTMENT D, LOCATION L, NATIONAL N, JOB J
      WHERE DEPT_CODE = DEPT_ID(+)
          AND LOCATION_ID = LOCAL_CODE
          AND L.NATIONAL_CODE = N.NATIONAL_CODE
          AND E.JOB_CODE = J.JOB_CODE);
-- ORA-01031: insufficient privileges ���� �߻�
-->> KH �������� ���� VIEW ���� ������ ��� ���� �߻�

-- ������� ������ �������� �����ؼ� ����
-- KH ������ CREATE VIEW ���� �ο�
GRANT CREATE VIEW TO KH;

-- �ٽ� KH �������� ���� �� �۾�
-- �ٽ� VIEW ���� ���� ���� --> �� ������
SELECT * FROM VM_EMPLOYEE;

--> �Ʒ��� �������� ����� �ƶ�
SELECT *
FROM (SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME
           FROM EMPLOYEE E, DEPARTMENT D, LOCATION L, NATIONAL N, JOB J
           WHERE DEPT_CODE = DEPT_ID(+)
               AND LOCATION_ID = LOCAL_CODE
               AND L.NATIONAL_CODE = N.NATIONAL_CODE
               AND E.JOB_CODE = J.JOB_CODE);
               
-- '�ѱ�'���� �ٹ��ϴ� ������� ���, �����, �μ���, �޿�, �ٹ�������, ���޸� ��ȸ
SELECT *
FROM VM_EMPLOYEE
WHERE NATIONAL_NAME = '�ѱ�';

-- ������ ���������� �̿��Ͽ� �׶��׶� �ʿ��� �����͵鸸 ��ȸ�ϴ� �� ����
-- �ѹ� ���������� �並 ���� �� �ش� ������� SELECT �� �̿��ϸ� �� �����ϰ� ��ȸ ����

-- '���þ�'���� �ٹ��ϴ� ����鸸 ��ȸ
SELECT *
FROM VM_EMPLOYEE
WHERE NATIONAL_NAME = '���þ�';

-- ���þƿ��� �ٹ��ϴ� ������� ���, �̸�, ���޸�, "���ʽ�" ��ȸ
SELECT EMP_ID, EMP_NAME, JOB_NAME, BONUS
FROM VM_EMPLOYEE
WHERE NATIONAL_NAME = '���þ�';
-- ORA-00904: "BONUS": invalid identifier
--> VM_EMPLOYEE ���̺� BONUS �÷��� ���� ������

-- �信 ���ʽ� �÷��� ���� ���¿��� ���ʽ��� ���� ��ȸ�ϰ� �ʹٸ� �並 �ٽ� �����ؾ� ��

-- �̹����� �ɼ��� �ٿ��� �並 �ٽ� �����ϱ� : CREATE OR REPLACE VIEW ���

/*
        [ ǥ���� ]
        CREATE OR REPLACE VIEW ���
        AS (��������);
        
        =>OR REPLACE �� ���� ����
            �� ���� ��, ������ �ߺ��� �� �̸��� �ִٸ� ������ �並 �����ϴ� �� �ƴ϶�
                            �ش� �並 ���� (����)�ϴ� �ɼ�
                            ������ �ߺ��� �� �̸��� ���ٸ� ������ �並 ������
*/

CREATE OR REPLACE VIEW VM_EMPLOYEE
AS (SELECT EMP_ID, EMP_NAME, DEPT_TITLE, SALARY, NATIONAL_NAME, JOB_NAME, BONUS
       FROM EMPLOYEE E, DEPARTMENT D, LOCATION L, NATIONAL N, JOB J
       WHERE DEPT_CODE = DEPT_ID(+)
            AND LOCATION_ID = LOCAL_CODE
            AND L.NATIONAL_CODE = N.NATIONAL_CODE
            AND E.JOB_CODE = J.JOB_CODE);
            
SELECT * FROM VM_EMPLOYEE;
-->> BONUS �÷��� �߰��� ��� ���ŵ�

SELECT EMP_ID, EMP_NAME, JOB_NAME, BONUS
FROM VM_EMPLOYEE
WHERE NATIONAL_NAME = '���þ�';
-- ���� �並 ���� �� ������ �����


-- ��� ������ �������̺� => ���������� �����͸� �����ϰ� ������ ����
-- (�ܼ��� �������� TEXT ������ ����Ǿ�����)
-- ���� ) �ش� ������ ������ �ִ� VIEW �鿡 ���� ������ ��ȸ�ϰ��� �Ѵٸ�
--          USER_VIEWS ������ ��ųʸ��� ��ȸ�ϸ� �ȴ�

SELECT * FROM USER_VIEWS;

/*
        * �� �÷��� ��Ī �ο�
        ���������� SELECT���� �Լ��� ���������� ����Ǿ����� ���
        �ݵ�� ��Ī�� �����ؾ� �Ѵ�.
*/

-- ����� ���, �̸�, ���޸�, ����, �ٹ������ ��ȸ�� �� �ִ� SELECT ���� ��� ����
CREATE OR REPLACE VIEW VW_EMP_JOB
AS (SELECT EMP_ID, EMP_NAME, JOB_NAME
                    , DECODE(SUBSTR(EMP_NO, 8, 1), 1, '��', 2, '��', 3, '��', 4, '��') "����"
                    , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) "�ٹ����"
        FROM EMPLOYEE
        JOIN JOB USING (JOB_CODE));
-- ��Ī ������ ������ ORA-00998: must name this expression with a column alias ���� �߻�
-- ��Ī ���̸� �� ���� ����

SELECT * FROM VW_EMP_JOB;

-- ��Ī�� �����ϴ� �ٸ� ��� (��, ��� �÷��� ���� ��Ī�� ��� �� ����ؾ� ��)
CREATE OR REPLACE VIEW VW_EMP_JOB (���, �����, ���޸�, ����, �ٹ����)
AS (SELECT EMP_ID, EMP_NAME, JOB_NAME
                    , DECODE(SUBSTR(EMP_NO, 8, 1), 1, '��', 2, '��', 3, '��', 4, '��') 
                    , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM HIRE_DATE) 
        FROM EMPLOYEE
        JOIN JOB USING (JOB_CODE));
        
SELECT * FROM VW_EMP_JOB;

SELECT �����, �ٹ����
FROM VW_EMP_JOB;

SELECT �����, ���޸�
FROM VW_EMP_JOB
WHERE ���� = '��';

-- �ٹ������ 20�� �̻��� ������� ��� �÷�
SELECT *
FROM VW_EMP_JOB
WHERE �ٹ���� >= 20;

-- �並 �����ϰ��� �Ѵٸ�
DROP VIEW VW_EMP_JOB;

SELECT * FROM VW_EMP_JOB;

--------------------------------------------------------------------------------------
/*
        * ������ �並 �̿��ؼ� DML (INSERT, UPDATE, DELETE) ��� ����
        ��, �並 ���� �����ϰ� �Ǹ� ���� �����Ͱ� ����ִ� �������� ���̺� (���̽����̺�) ���� ������ �ȴ�.
*/

-- �׽�Ʈ�� �� ����
CREATE OR REPLACE VIEW VW_JOB
AS (SELECT * FROM JOB);

SELECT * FROM VW_JOB; -- ��
SELECT * FROM JOB; -- ���̽����̺�

-- �信 INSERT
INSERT INTO VW_JOB
VALUES ('J8', '����');

SELECT * FROM VW_JOB; -- ��
-- �信�� �� INSERT �Ǿ�����
SELECT * FROM JOB; -- ���̽����̺�
-- ���̽����̺��� ���� INSERT ��
-->> �䰡 �ƴ϶� ���̽����̺� INSERT �� ��

-- �信 UPDATE
-- JOB_CODE �� J8�� JOB_NAME �� �˹ٷ� UPDATE
UPDATE VW_JOB SET JOB_NAME = '�˹�' WHERE JOB_CODE = 'J8';

SELECT * FROM VW_JOB; -- ��
SELECT * FROM JOB; -- ���̽����̺�
-->> �䰡 �ƴ϶� ���̽����̺��� UPDATE �� ��

-- �信 DELETE
-- JOB_CODE �� J8�� �� ����
DELETE
FROM VW_JOB
WHERE JOB_CODE = 'J8';

SELECT * FROM VW_JOB; -- ��
SELECT * FROM JOB; -- ���̽����̺�
-->> ���̽����̺��� JOB���� DELETE ��

--------------------------------------------------------------------------------------

-- �信 DML�� ���� �� �Ǵ� ���

-- �׽�Ʈ�� �� ����
CREATE OR REPLACE VIEW VW_JOB
AS (SELECT JOB_CODE FROM JOB);

SELECT * FROM VW_JOB;

-- �信 INSERT => �Ұ�
INSERT INTO VW_JOB (JOB_CODE, JOB_NAME) VALUES ('J8', '����');
-- SQL ����: ORA-00904: "JOB_NAME": invalid identifier

-- �信 UPDATE => �Ұ�
UPDATE VW_JOB
SET JOB_NAME = '����'
WHERE JOB_CODE = 'J7';
-- SQL ����: ORA-00904: "JOB_NAME": invalid identifier

-- �信 DELETE => �Ұ�
DELETE
FROM VW_JOB
WHERE JOB_NAME = '���';
-- SQL ����: ORA-00904: "JOB_NAME": invalid identifier

/*
        * ������ �信 DML ����� �Ұ��� ��찡 �� ����
        
        1) �信 ���ǵǾ����� ���� �÷��� �����ϴ� ���
        2) �信 ���ǵǾ����� ���� �÷� �� ���̽����̺� �� NOT NULL ���������� �ɷ��ִ� ���
        3) �������� �Ǵ� �Լ��� ���� ���ǵǾ��ִ� ���
        4) �������� �κп� �׷��Լ��� GROUP BY ���� ���Ե� ���
        5) JOIN �� �̿��� ���� ���̺��� ��Ī���Ѽ� ��� ������ ���
        
        => �̷� ������ ��� ��ȸ������ ���� ����.
*/

--------------------------------------------------------------------------------------

/*
        * VIEW �ɼ�
        
        [ ��ǥ���� ]
        CREATE OR REPLACE FORCE/NOFORCE VIEW ���
        AS (��������)
        WITH CHECK OPTION
        WITH READ ONLY;
        
        1) OR REPLACE : �ش� ����� �̹� �����ϸ� ����/�������� ������ ������ ����
        2) FORCE/NOFORCE
            - FORCE : ���������� ����� ���̺��� �������� �ʴ��� �� ����
            - NOFORCE : ���������� ����� ���̺��� �ݵ�� �����ؾ� �� ���� (���� �� �⺻��)
        3) WITH CHECK OPTION : ���������� ������ (WHERE��) �� ����� ���뿡 �����ϴ� �����θ� DML ����
                                                ���ǿ� �������� �ʴ� ���� �����ϴ� ��� ���� �߻�
        4) WITH READ ONLY : �信 ���� ��ȸ�� ���� (INSERT, UPDATE, DELETE ���� �Ұ�)
*/

-- 2) FORCE / NOFORCE
CREATE OR REPLACE /* NOFORCE */ VIEW VW_TEST
AS (SELECT * FROM TT);
-- ORA-00942: table or view does not exist
--> TT��� ���̺��� �������� �ʱ� ������

CREATE OR REPLACE FORCE VIEW VW_TEST
AS (SELECT * FROM TT);
-- ���: ������ ������ �Բ� �䰡 �����Ǿ����ϴ�.
--> ������ ������ ��� ��

SELECT * FROM VW_TEST;
-- ������ ������, ���� ���̺��� ���� ������ �̿��� �Ұ����� ����

-- �������̺��� �� �������� �������ָ� �̿��� ������
CREATE TABLE TT (
        TCODE NUMBER,
        TNAME VARCHAR2(20),
        TCONTENT VARCHAR2(50)
);

-- �� �������� �ٽ� VIEW ��ȸ�� �̿� ����
SELECT * FROM VW_TEST;


-- 3) WITH CHECK OPTION
CREATE OR REPLACE VIEW VW_EMP
AS (SELECT * FROM EMPLOYEE WHERE SALARY >= 3000000)
WITH CHECK OPTION;

SELECT * FROM VW_EMP;
-- VW_EMP �信�� ���� �޿��� 300���� �̻��� ������� ������ ��������.

UPDATE VW_EMP
SET SALARY = 2000000
WHERE EMP_ID = 200;
-- ORA-01402: view WITH CHECK OPTION where-clause violation
--> ���������� ����� ���ǿ� �������� �ʱ� ������ �� ���� �Ұ�

UPDATE VW_EMP
SET SALARY = 4000000
WHERE EMP_ID = 200;
-- ���������� ����� ���ǿ� �����ϱ� ������ �� ���� ����

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
-- SQL ����: ORA-42399: cannot perform a DML operation on a read-only view
--> READ ONLY �信���� DML ������ ������ �� ����.
