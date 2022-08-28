/*
    < �������� SUBQUERY >
    
    �ϳ��� �ֵ� SQL �� (SELECT, INSERT, CREATE, ..) �ȿ� ���Ե� �� �ϳ��� SELECT��
    ���� SQL ���� ���� ���� ������ �ϴ� ������
*/

-- ������ ���� ���� ���� 1)
-- ���ö ����� ���� �μ��� ��� ��ȸ
-- 1) ���ö ����� �μ��ڵ� ��ȸ
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '���ö'; -- ���ö ����� �μ��ڵ�� D9 -- 1�� 1�� ��������

-- 2) �μ� �ڵ尡 D9�� ����� ��ȸ
SELECT EMP_NAME
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9'; -- ������, ������, ���ö

--> SUBQUERY�� ���� ���
-- ���� �� �ܰ谡 �ϳ��� ���������� �ذ�ȴ�
SELECT EMP_NAME
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE 
                                      FROM EMPLOYEE 
                                      WHERE EMP_NAME = '���ö');

-- ������ ���� ���� ���� 2)
-- ��ü ����� ��� �޿����� �� ���� �޿��� �ް� �ִ� ������� ���, �̸�, ���� �ڵ� ��ȸ
-- 1) ��ü ����� ��� �޿� ���ϱ�
SELECT ROUND(AVG(SALARY))
FROM EMPLOYEE; -- 3047663 -- 1�� 1�� ��������

-- 2) �޿��� 3047663�� �̻��� ����� ��ȸ
SELECT EMP_ID, EMP_NAME, JOB_CODE
FROM EMPLOYEE
WHERE SALARY >= 3047663;

--> SUBQUERY�� ���� ���
-- ���� �� �ܰ谡 �ϳ��� ���������� �ذ�ȴ�
SELECT EMP_ID, EMP_NAME, JOB_CODE
FROM EMPLOYEE
WHERE SALARY >= (SELECT ROUND(AVG(SALARY))
                                  FROM EMPLOYEE);

-------------------------------------------------------------------------------------------------------
/*
     * ���� ���� ����
     �з� ���� : ���������� ������ �����(RESULT SET)�� �� �� �� �������� ���� �з��ȴ�.
     
     - ������ (���Ͽ�) �������� : ���������� ������ ������� ������ 1���� �� (1�� 1��)
     - ������ (���Ͽ�) �������� : ���������� ������ ������� ���� �� �� ���� �� (N�� 1��)
     - (������) ���߿� �������� : ���������� ������ ������� �� �� ���� ���� �� (1�� N��)
     - ������ ���߿� �������� : ���������� ������ ������� ���� �� ���� ���� �� (N�� N��)
     
     => ���������� ������ ����� �� �� �� ���̳Ŀ� ���� ��� ������ �����ڵ� �޶���
     
     ����) ���������� FROM ���� �� ��� : �ζ��� ��
*/
-------------------------------------------------------------------------------------------------------
/*
    1. ������ ���Ͽ� �������� (SINGLE ROW SUBQUERY)
    ���� ������ ��ȸ ������� ������ 1���� ��
    
    �Ϲ� ������ ��� ���� (=, !=, <=, >, <, >=, ...)
    
*/

-- �� ������ ��� �޿����� �� ���� �޴� ������� �����, ���� �ڵ�, �޿� ��ȸ
-- 1) �� ������ ��� �޿� ��ȸ
SELECT ROUND(AVG(SALARY))
FROM EMPLOYEE; -- 3047663 -- 1�� 1�� �����

-- 2) ��� �̸����� �޴� �����, �����ڵ�, �޿� ��ȸ
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY < 3047663;

--> �������� ����ؼ� ��ġ�� (������ �������� : �Ϲ� ������ ��� ����)
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY < (SELECT ROUND(AVG(SALARY)) -- 1�� 1�� �����(������ ��������)
                                FROM EMPLOYEE);
                                

-- ��� �� ���� �޿��� �޴� ����� ���, �����, �����ڵ�, �޿�, �Ի��� ��ȸ
-- 1) ���� �޿� ��ȸ
SELECT MIN(SALARY)
FROM EMPLOYEE; -- 1380000

-- 2) �޿��� 1380000�� ��� ��ȸ
SELECT EMP_ID, EMP_NAME, JOB_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY = 1380000;

--> �������� ����ؼ� ��ġ�� 
SELECT EMP_ID, EMP_NAME, JOB_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY = (SELECT MIN(SALARY) -- 1�� 1�� �����(������ ��������)
                                FROM EMPLOYEE);

-- ���ö ����� �޿����� �� ���� �޴� ������� ���, �̸�, �μ��ڵ�, �޿� ��ȸ
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > (SELECT SALARY
                                FROM EMPLOYEE
                                WHERE EMP_NAME = '���ö');

-- ���ö ����� �޿����� �� ���� �޴� ������� ���, �̸�, �μ���, �޿� ��ȸ
-->> ����Ŭ ���� ����
SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, E.SALARY
FROM EMPLOYEE E, DEPARTMENT D
WHERE E.DEPT_CODE = D.DEPT_ID
    AND SALARY > (SELECT SALARY
                               FROM EMPLOYEE
                               WHERE EMP_NAME = '���ö');

-->> ANSI ����
SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, E.SALARY
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
WHERE SALARY > (SELECT SALARY
                                FROM EMPLOYEE
                                WHERE EMP_NAME = '���ö');
--> ���������� JOIN�� �Բ� ����� �� ����


-- ������ ����� ���� �μ��� ������� ���, �����, �޴�����ȣ, ���޸�
-->> ����Ŭ ���� ����
SELECT E.EMP_ID, E.EMP_NAME, E.PHONE, J.JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND E.DEPT_CODE = (SELECT DEPT_CODE
                                         FROM EMPLOYEE
                                         WHERE EMP_NAME = '������')
    AND EMP_NAME != '������';

-->> ANSI ����
SELECT E.EMP_ID, E.EMP_NAME, E.PHONE, J.JOB_NAME
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
WHERE E.DEPT_CODE = (SELECT DEPT_CODE
                                         FROM EMPLOYEE
                                         WHERE EMP_NAME = '������')
    AND EMP_NAME != '������';


-- �μ��� �޿� ���� ���� ū �μ� �ϳ����� ��ȸ
-- �μ��ڵ�, �μ���, �޿��� ��ȸ
-- 1) �μ��� �޿� �� ���ϱ�
SELECT SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- 2) �޿����� �ִ��� �μ�
SELECT MAX(SUM(SALARY))
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-->> ����Ŭ ���� ����
SELECT DEPT_CODE "�μ��ڵ�"
            , DEPT_TITLE "�μ���"
            , SUM(SALARY) "�޿���"
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
GROUP BY DEPT_CODE, DEPT_TITLE
HAVING SUM(SALARY) = (SELECT MAX(SUM(SALARY))
                                            FROM EMPLOYEE
                                            GROUP BY DEPT_CODE);

-->> ANSI ����
SELECT DEPT_CODE, DEPT_TITLE, SUM(SALARY)
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE DEPT_CODE = DEPT_ID
GROUP BY DEPT_CODE, DEPT_TITLE
HAVING SUM(SALARY) = (SELECT MAX(SUM(SALARY))
                                            FROM EMPLOYEE
                                            GROUP BY DEPT_CODE);

