/*
        < DML : DATA MANIPULATION LANGUAGE >
        ������ ���� ���
        
        ���̺� ���ο� �����͸� ���� (INSERT) �ϰų�, ������ �����͸� ���� (UPDATE) �ϰų�,
        ���� (DELETE) �ϴ� ����
        
        + ������ ���� ���̺�κ��� �����͸� ��ȸ (SELECT) �ϴ� ������ �з��� �� �ִ�.
*/

/*
        1. INSERT : ���̺� ���ο� ���� �߰��ϴ� ����
        
        [ ǥ���� ]
        1) INSERT INTO ���̺�� VALUES (��, ��, ... );
        => �ش� ���̺� ��� �÷��� ���� �߰��ϰ��� �ϴ� ���� ���� ���� �����ؼ�
             �� �� ������ INSERT �ϰ��� �� �� ���
             ������ �� : ���� ������ �� �׻� �÷��� ������ ���Ѽ� VALUES ��ȣ �ȿ� ���� �����ؾ� ��.
             - ���� ������ �����ϰ� �����ϸ� : NOT ENOUGH VALUE ���� �߻�
             - ���� ������ �� ���� �����ϸ� : TOO MANY VALUES ���� �߻�
*/

INSERT INTO EMPLOYEE 
VALUES (900
              , '�ڸ���'
              , '990115-2222222'
              , 'park_ms@kh.or.kr'
              , '01044442222'
              , 'D1'
              , 'J7'
              , 'S3'
              , 4000000
              , 0.2
              , 200
              , SYSDATE
              , NULL
              , DEFAULT );
              
SELECT * FROM EMPLOYEE;

SELECT * FROM EMPLOYEE WHERE EMP_ID = 900;

-------------------------------------------------------------------------------------------------------------------
/*
        2) INSERT INTO ���̺�� (�÷���, �÷���, ..) VALUES (��, ��, ...);
        
        => �ش� ���̺� Ư�� �÷����� �����ؼ� �� �÷��� �߰��� ���� �����ϰ��� �� �� ���
             �׷��� �� �� ������ �߰��Ǳ� ������ ���õ��� ���� �÷��� ���ؼ��� �⺻������ NULL ���� ��
             (��, DEFAULT ������ �Ǿ��ִ� �÷����� DEFAULT ���� ��)
             ������ �� : NOT NULL ���������� �ɷ��ִ� �÷��� �ݵ�� �����ؼ� ���� ���� ��������� �Ѵ�.
                             ��, NOT NULL ���������� �ɷ��ִ��� DEFAULT ������ �Բ� �ɷ��ִٸ�
                             �����ؼ� ���� �������� �ʾƵ� �ȴ�.
*/

INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, EMP_NO, DEPT_CODE, JOB_CODE, SAL_LEVEL, HIRE_DATE)
VALUES (901, '�踻��', '980418-1111111', 'D1', 'J2', 'S1', SYSDATE);

SELECT * FROM EMPLOYEE;

-------------------------------------------------------------------------------------------------------------------
/*
        3) INSERT INTO ���̺�� (��������);
        
        => VALUES�� ���� �����ϴ� �� ��ſ� ���������� ��ȸ�� ��������� ���� INSERT �ϴ� ����
             ��, ���� ���� �� ���� INSERT�� �� �ִ�.
*/

-- �׽�Ʈ�� ���ο� ���̺� ����
CREATE TABLE EMP_01 (
        EMP_ID NUMBER,
        EMP_NAME VARCHAR2(30),
        DEPT_TITLE VARCHAR2(20)
);

-- EMPLOYEE ���̺�κ��� "��ü" ������� ���, �̸�, �μ����� ��ȸ�� �����
-- EMP_01 ���̺� ��°�� �߰�
-- 1) EMPLOYEE ���̺��� ���, �̸�, �μ��� ��ȸ
SELECT EMP_ID, EMP_NAME, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID(+);
-- ������������ EMPLOYEE ���̺��� ��� ��� (25���� ��) ��ȸ

-- 2) ���������� ���� EMP_01 ���̺� ��ȸ�� ������� ��°�� �߰�
INSERT INTO EMP_01 
(SELECT EMP_ID, EMP_NAME, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID(+));
-- 25�� �� ��(��) ���ԵǾ����ϴ�.

SELECT * FROM EMP_01;


-------------------------------------------------------------------------------------------------------------------
/*
        2. INSERT ALL
        �� �� �̻��� ���̺� ���� INSERT �� �� ���
        �� �� ���Ǵ� ���������� ������ ��쿩�� �Ѵ�.
*/

-- �׽�Ʈ�� ���ο� ���̺� ����
-- 1��° ���̺� : �޿��� 300���� �̻��� ������� ���, �����, ���޸� ���� ����
CREATE TABLE EMP_JOB (
        EMP_ID NUMBER,
        EMP_NAME VARCHAR2(30),
        JOB_NAME VARCHAR2(20)
);

-- 2��° ���̺� : �޿��� 300���� �̻��� ������� ���, �����, �μ��� ���� ����
CREATE TABLE EMP_DEPT (
        EMP_ID NUMBER,
        EMP_NAME VARCHAR2(30),
        DEPT_TITLE VARCHAR2(20)
);

SELECT * FROM EMP_JOB;
SELECT * FROM EMP_DEPT;

-- ���������� ���� ��������
-- EMP_JOB : EMP_ID, EMP_NAME, JOB_NAME
-- EMP_DEPT : EMP_ID, EMP_NAME, DEPT_TITLE

-- �޿��� 300���� �̻��� ������� ���, �̸�, ���޸�, �μ��� ��ȸ
SELECT EMP_ID, EMP_NAME, JOB_NAME, DEPT_TITLE
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY >= 3000000; -- �� 9�� ��ȸ

/*
        [ ǥ���� ]
        1) INSERT ALL INTO ���̺��1 VALUES (�÷���, �÷���, ..)
                               INTO ���̺��2 VALUES (�÷���, �÷���, ..)
                               (��������);
*/

-- EMP_JOB : �޿��� 300���� �̻��� ������� EMP_ID, EMP_NAME, JOB_NAME
-- EMP_DEPT : �޿��� 300���� �̻��� ������� EMP_ID, EMP_NAME, DEPT_TITLE
INSERT ALL
INTO EMP_JOB VALUES (EMP_ID, EMP_NAME, JOB_NAME)
INTO EMP_DEPT VALUES (EMP_ID, EMP_NAME, DEPT_TITLE)
(SELECT EMP_ID, EMP_NAME, JOB_NAME, DEPT_TITLE
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY >= 3000000);
-- 18�� �� ��(��) ���ԵǾ����ϴ�.

SELECT * FROM EMP_JOB;
SELECT * FROM EMP_DEPT;

-------------------------------------------------------------------------------------------------------------------
-- INSERT ALL �� ���ǿ� ���󼭵� �ٸ��� ���̺� INSERT ����

-- �׽�Ʈ�� ���� ���̺� ���� ����
-- 1��° ���̺� : 2010�⵵ ������ �Ի��� ����鿡 ���� ���, �����, �Ի���, �޿� (EMP_OLD)
CREATE TABLE EMP_OLD AS (SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
                                                  FROM EMPLOYEE
                                                  WHERE 1 = 0);

-- 2��° ���̺� : 2010�⵵ ���Ŀ� �Ի��� ����鿡 ���� ���, �����, �Ի���, �޿� (EMP_NEW)
CREATE TABLE EMP_NEW AS (SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
                                                  FROM EMPLOYEE
                                                  WHERE 1 = 0);

-- �������� ���� ���ϱ�
SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
FROM EMPLOYEE
-- WHERE HIRE_DATE < '2010/01/01'; -- EMP_OLD ���̺� INSERT�ϰ� ���� ��� : 9�� ��ȸ
WHERE HIRE_DATE >= '2010/01/01'; -- EMP_NEW ���̺� INSERT �ϰ� ���� ��� : 16�� ��ȸ

/*
        2) INSERT ALL
            WHEN ����1 THEN INTO ���̺�� 1 VALUES (�÷���, �÷���, ..)
            WHEN ���� 2 THEN INTO ���̺��2 VALUES (�÷���, �÷���, ..)            
            (��������);
*/

SELECT * FROM EMP_OLD;
SELECT * FROM EMP_NEW;

INSERT ALL
WHEN HIRE_DATE < '2010/01/01' THEN INTO EMP_OLD (EMP_ID, EMP_NAME, HIRE_DATE, SALARY) -- 9��
WHEN HIRE_DATE >= '2010/01/01' THEN INTO EMP_NEW (EMP_ID, EMP_NAME, HIRE_DATE, SALARY) --16��
(SELECT EMP_ID, EMP_NAME, HIRE_DATE, SALARY
FROM EMPLOYEE);
-- 25�� �� ��(��) ���ԵǾ����ϴ�.


-------------------------------------------------------------------------------------------------------------------
/*
        3. UPDATE 
        ���̺� ��ϵ� ������ �����͸� �����ϴ� ����
        
        [ ǥ���� ]
        UPDATE ���̺��
        SET �÷��� = ������, �÷��� = ������        => SET������ ���� ���� �÷����� , �� ���ÿ� �����ؼ� ���� ����
                                                                     => = �� ���Կ����� (����񱳿����� �ƴ�)
        WHERE �������࿡��������;                    => WHERE ���� ���� ����
                                                                    => �� ������ ��ü ��� ���� �����Ͱ� �� ����ȴ�.
*/

-- ���纻 ���̺� ���� �� �۾�
CREATE TABLE DEPT_COPY AS (SELECT * FROM DEPARTMENT);

SELECT * FROM DEPT_COPY;

-- DEPT_COPY ���̺��� D9 �μ��� �μ����� �ѹ��ο��� ������ȹ������ ����
UPDATE DEPT_COPY
SET DEPT_TITLE = '������ȹ��';
-- ���� WHERE���� ������ ��� ��� ���� DEPT_TITLE ���� ������ȹ������ �����

-- ������ ���� ������ �ǵ����� ���� �� ����ϴ� ��ɾ�
ROLLBACK;

-- D9 �μ��� �μ��� ������ȹ������ ����
UPDATE DEPT_COPY
SET DEPT_TITLE = '������ȹ��'
WHERE DEPT_ID = 'D9';

SELECT * FROM DEPT_COPY;


-- ���纻 ���̺� ���� �� �۾�
CREATE TABLE EMP_SALARY AS (SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY, BONUS
                                                        FROM EMPLOYEE);

SELECT * FROM EMP_SALARY;

-- EMP_SALARY ���̺��� ���ö ����� �޿��� 1000�������� ����
UPDATE EMP_SALARY
SET SALARY = 10000000
WHERE EMP_NAME = '���ö';

SELECT * FROM EMP_SALARY;

-- EMP_SALARY ���̺��� ������ ����� �޿��� 700����, ���ʽ��� 0.2�� ����
UPDATE EMP_SALARY
SET SALARY = 7000000, BONUS = 0.2
WHERE EMP_NAME = '������';

SELECT * FROM EMP_SALARY;

-- ��ü ����� �޿��� ���� �޿��� 20���� �λ��� �ݾ����� ���� (�����޿� * 1.2)
UPDATE EMP_SALARY
SET SALARY = SALARY * 1.2;

SELECT * FROM EMP_SALARY;

-------------------------------------------------------------------------------------------------------------------
/*
        * UPDATE �ÿ� ���������� ���
        ���������� ������ ��������� �����Ѵٴ� �ǹ�
        
        => SET ���� ��������
        
        UPDATE ���̺��
        SET �÷��� = (��������)
        WHERE ����;                   => ���� ����, �� ��� �����Ͱ� �ϰ������� �� �����
*/

-- EMP_SALARY ���̺� �踻�� ����� �μ��ڵ带 ������ �μ��ڵ�� ����
UPDATE EMP_SALARY
SET DEPT_CODE = (SELECT DEPT_CODE
                                FROM EMP_SALARY
                                WHERE EMP_NAME = '������')
WHERE EMP_NAME = '�踻��';

SELECT * FROM EMP_SALARY;

-- ���� ����� �޿��� ���ʽ��� ����� ����� �޿��� ���ʽ� ������ ����
UPDATE EMP_SALARY
SET (SALARY, BONUS) = (SELECT SALARY, BONUS
                                         FROM EMP_SALARY
                                         WHERE EMP_NAME = '�����')
WHERE EMP_NAME = '����';

SELECT * FROM EMP_SALARY;

-------------------------------------------------------------------------------------------------------------------
-- UPDATE �ÿ��� ������ ���� �־ �ش� �÷��� ���� �������ǿ� ����Ǹ� �ȵ�

SELECT * FROM EMPLOYEE;

-- ���߱� ������ ����� 200���� ����
UPDATE EMPLOYEE
SET EMP_ID = 200
WHERE EMP_NAME = '������';
-- ORA-00001: unique constraint (KH.EMPLOYEE_PK) violated
-- UNIQUE �������� ����

-- ����� 200���� ����� �̸��� NULL�� ����
UPDATE EMPLOYEE
SET EMP_NAME = NULL
WHERE EMP_ID = 200;
-- ORA-01407: cannot update ("KH"."EMPLOYEE"."EMP_NAME") to NULL
-- NOT NULL ���� ���� ����

-- ���� �۾��� ���� Ȯ��
COMMIT;


-------------------------------------------------------------------------------------------------------------------
/*
        4. DELETE
        ���̺� ��ϵ� �����͸� �����ϴ� ����
        
        [ ǥ���� ]
        DELETE
        FROM ���̺��
        WHERE �������࿡��������;        => WHERE �� ���� ����
                                                        => ��, ������ �ش� ���̺��� ��ü �� ��� ����
*/

SELECT * FROM EMPLOYEE;

DELETE
FROM EMPLOYEE;

ROLLBACK;

-- �踻�˰� �ڸ��� ����� �����͸� �����
DELETE
FROM EMPLOYEE
WHERE EMP_NAME IN ('�踻��', '�ڸ���');

COMMIT;

-- DEPARTMENT ���̺�κ��� DEPT_ID �� D1�� �μ� ����
DELETE
FROM DEPARTMENT
WHERE DEPT_ID = 'D1';
-- ORA-02292: integrity constraint (KH.SYS_C007115) violated - child record found
-->> EMPLOYEE ���̺� D1�� �����ϴ� �ڽĵ����Ͱ� �ֱ� ������ ���� �߻�

-- DEPARTMENT ���̺�κ��� DEPT_ID �� D3�� �μ� ����
DELETE
FROM DEPARTMENT
WHERE DEPT_ID = 'D3';
-- ������ �� �̷���� = �ڽĵ����Ͱ� ���� ����

SELECT * FROM DEPARTMENT;

ROLLBACK;

-------------------------------------------------------------------------------------------------------------------
-- DROP TABLE ���̺��; : ���̺� ��ü�� �����ϴ� ����
-- DELETE FROM ���̺��; : ���̺��� ���빰�� ��ü ���� (��, ���̺��� ������ �״�� ����)

/*
        * TRUNCATE : ���̺��� ��ü ���� ������ �� ����ϴ� ���� (����)
        
        [ ǥ���� ]
        TRUNCATE TABLE ���̺�� ;                |               DELETE FROM ���̺��;
        ---------------------------------------------------------------------------------
        ������ ���� ���ð� �Ұ�                          |  WHERE ���� ���ٿ� Ư�� ���� ���ð� ����
        ROLLBACK���� ���� �Ұ���                   |  ROLLBACK���� ���� ����
        ���� �ӵ��� ����                                    |  ����ӵ��� ����
*/

SELECT * FROM EMP_SALARY;

DELETE
FROM EMP_SALARY;
-- 25�� �� ��(��) �����Ǿ����ϴ�.

ROLLBACK;

TRUNCATE TABLE EMP_SALARY;
-- Table EMP_SALARY��(��) �߷Ƚ��ϴ�.

ROLLBACK;

