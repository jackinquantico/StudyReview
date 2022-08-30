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

-------------------------------------------------------------------------------------------------------
/*
        2. ������ (���Ͽ�) �������� (MULTI ROW SUBQUERY)
        ���������� ��ȸ ������� ���� ���� ��
        
        ����� �� �ִ� ������ ����
        - �÷��� IN (����������) : ���� ���� ��� �� �߿��� �� ���� ��ġ�ϴ� ���� �ִٸ� 
        - �÷��� NOT IN (����������) : ���� ���� ��� �� �߿��� ��ġ�ϴ� �� �ϳ��� ���ٸ�
        - �÷��� > ANY (����������) : ���� ���� ����� �߿��� �ϳ��� Ŭ ���
                                                    ���� ���� ����� �� ���� ���� ������ Ŭ ���
        - �÷��� < ANY (����������) : ���� ���� ����� �߿��� �ϳ��� ���� ���
                                                    ���� ���� ����� �� ���� ū ������ ���� ���
        - �÷��� > ALL (����������) : ���� ���� ������� ��� ������ Ŭ ���
                                                  ���� ���� ����� �߿��� ���� ū ������ Ŭ ���
        - �÷��� < ALL (����������) : ���� ���� ������� ��� ������ ���� ���
                                                  ���� ���� ����� �߿��� ���� ���� ������ ���� ���
*/

-- �� �μ��� �ְ� �޿��� �޴� ����� �̸�, �����ڵ�, �޿� ��ȸ
-- 1) �� �μ��� �ְ� �޿�
SELECT MAX(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE; --2890000, 3660000, 8000000, 3760000, 3900000, 2490000, 2550000
-- 7�� 1���� �� 7���� ���� ��ȸ��

-- 1_2) 
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY IN (2890000, 3660000, 8000000, 3760000, 3900000, 2490000, 2550000);

-- 2) ���� �޿��� �޴� ��� ��ȸ
SELECT EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY IN (SELECT MAX(SALARY)
                                  FROM EMPLOYEE
                                  GROUP BY DEPT_CODE);
                             
                                  
-- ������ �Ǵ� ����� ����� ���� �μ��� ������� �����, �μ��ڵ�, �޿� ��ȸ
-- 1) ������ �Ǵ� ����� ����� �μ��ڵ� ���ϱ�
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME IN ('������', '�����');

-- 2) ���� �μ��� ����� ��ȸ
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE IN (SELECT DEPT_CODE
                                        FROM EMPLOYEE
                                        WHERE EMP_NAME IN ('������', '�����'))
    AND EMP_NAME NOT IN ('������', '�����');


-- ��� < �븮 < ���� < ���� < ���� ...
-- �븮 �����ӿ��� �ұ��ϰ� ���� ������ �޿����� �� ���� �޴� ������ ��ȸ ( ���, �̸�, ���޸�, �޿�)
-- 1) ���� ������ �޿�
SELECT SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND J.JOB_NAME = '����';
     
-- 2) �븮 �����̰� ���庸�� �� ���� �޴� ���� ��ȸ
SELECT EMP_ID, EMP_NAME, J.JOB_NAME, SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND J.JOB_NAME = '�븮'
     AND SALARY > ANY (SELECT SALARY
                                         FROM EMPLOYEE E, JOB J
                                         WHERE E.JOB_CODE = J.JOB_CODE
                                              AND J.JOB_NAME = '����');


-- ���� �����ӿ��� �ұ��ϰ� ��� ���� ������ �޿����ٵ� �� ���� �޴� ���� ��ȸ
-- ���, �̸�, ���޸�, �޿�

-->> ����Ŭ ����
SELECT SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND JOB_NAME = '����';

SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND JOB_NAME = '����'
     AND SALARY > ALL (SELECT SALARY
                                        FROM EMPLOYEE E, JOB J
                                        WHERE E.JOB_CODE = J.JOB_CODE
                                             AND JOB_NAME = '����');

-->> ANSI ����
SELECT SALARY
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
WHERE JOB_NAME = '����';

SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
WHERE JOB_NAME = '����'
     AND SALARY > ALL (SELECT SALARY
                                        FROM EMPLOYEE E
                                        JOIN JOB J USING (JOB_CODE)
                                        WHERE JOB_NAME = '����');


-------------------------------------------------------------------------------------------------------
/*
        3. (������) ���߿� �������� (MULTI COLUMN SUBQUERY)
        ��ȸ ��� ���� �� �������� ������ �÷� ���� ���� ���� ��
        
        (���� ��) = (���� ��) �̷� ������ �񱳰� �Ͼ ���� : ������ (���� ��) �κп� ���ͷ��� ���� �� ���� ���� �߻�
*/

-- ������ ����� ���� �μ��ڵ�, ���� ���� �ڵ忡 �ش��ϴ� ����� ��ȸ
SELECT DEPT_CODE, JOB_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '������';

SELECT DEPT_CODE, JOB_CODE, EMP_NAME
FROM EMPLOYEE
WHERE (DEPT_CODE, JOB_CODE) = (SELECT DEPT_CODE, JOB_CODE
                                                          FROM EMPLOYEE
                                                          WHERE EMP_NAME = '������')
     AND EMP_NAME != '������';


-- �ڳ��� ����� ���� ���� �ڵ�, ���� �������� ���� ������� ���, �̸�, �����ڵ�, ������ ��ȸ
SELECT EMP_ID, EMP_NAME, JOB_CODE, MANAGER_ID
FROM EMPLOYEE
WHERE (JOB_CODE, MANAGER_ID) = (SELECT JOB_CODE, MANAGER_ID
                                                             FROM EMPLOYEE
                                                             WHERE EMP_NAME = '�ڳ���');


-------------------------------------------------------------------------------------------------------
/*
        4. ������ ���߿� ��������
        �������� ��ȸ ������� ���� �� ���� ���� ���
*/

-- �� ���޺� �ּ� �޿��� �޴� ������� ���, �̸�, �����ڵ�, �޿� ��ȸ
-->> �� ���޺� �ּ� �޿�(����, �ּ� �޿�)
SELECT JOB_CODE, MIN(SALARY)
FROM EMPLOYEE
GROUP BY JOB_CODE; 
-- 7�� 2��, �� 14���� ��

-->> ������� ���, �̸�, �����ڵ�, �޿� ��ȸ
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE (JOB_CODE, SALARY) IN (SELECT JOB_CODE, MIN(SALARY)
                                                     FROM EMPLOYEE
                                                     GROUP BY JOB_CODE);


-- �� �μ��� �ְ� �޿��� �޴� ������� ���, �̸�, �μ��ڵ�, �޿� ��ȸ
SELECT DEPT_CODE, MAX(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE (NVL(DEPT_CODE, '����'), SALARY) IN (SELECT NVL(DEPT_CODE, '����'), MAX(SALARY)
                                                                            FROM EMPLOYEE
                                                                            GROUP BY DEPT_CODE);
-- NULL �� ���� ���´ٸ� ����񱳰� �� �ż� ������� ������
-- NVL �Լ��� ó�����ָ� �� : �� ���� ���� �� ��ο���

-->> NULL ���� �񱳴�� ���Ե� ��� ����񱳰� �Ͼ�� �ʱ� ������ NVL ó���� �ʼ�
-->> NULL ���� IS NULL �����ڷθ� ����� ����

-------------------------------------------------------------------------------------------------------
/*
        5. �ζ��� �� (INLINE-VIEW)
        FROM ���� ���������� �����ϴ� ��
        
        ���������� ������ ��� (RESULT SET) �������� ��ȸ�ϰڴ�
*/

-- ���ʽ� ���� ������ 3000���� �̻��� ������� ���, �̸�, ���ʽ� ���� ����, �μ��ڵ� ��ȸ
-->> �ζ��κ� ���� �ʴ� ���
SELECT EMP_ID, EMP_NAME, (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "����", DEPT_CODE
FROM EMPLOYEE
WHERE (SALARY + (SALARY * NVL(BONUS, 0))) * 12 >= 30000000;

-->> �ζ��κ並 ���� ���
SELECT EMP_ID, EMP_NAME, (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "����", DEPT_CODE
FROM (SELECT * 
            FROM EMPLOYEE 
            WHERE (SALARY + (SALARY * NVL(BONUS, 0))) * 12 >= 30000000);

SELECT *
FROM (SELECT  EMP_ID, EMP_NAME, (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "����", DEPT_CODE
            FROM EMPLOYEE)
WHERE ���� >= 30000000;

-- ROWNUM : ����Ŭ���� �����ϴ� �÷�, ��ȸ�� ������� 1���� ������ �ο��ϴ� �÷�

-->> �ζ��κ並 �ַ� ����ϴ� ��
-- TOP-N �м� : �����ͺ��̽� �� �ִ� �ڷ� �� �ֻ��� �� ���� �ڷḦ ���� ���� ���Ǵ� �ڷ�

-- �� ���� �� �޿��� ���� ���� ���� 5��
SELECT EMP_NAME, SALARY     -- 3
FROM EMPLOYEE                        -- 1
WHERE ROWNUM <= 5             -- 2
ORDER BY SALARY DESC;         -- 4
-- �̽� : �޿������� �������� �ϰ� ��ȣ�� ���̰� ��ȣ����� �߶���� �ϴµ�
--          ��ȣ����� �ڸ��� ��ȣ�� ���̰� �޿������� �����������ִ� �̽� �߻� (������ ����)

--> 1) ORDER BY �� ������ ���̺��� ������ ��ȸ
--   2) SELECT �ÿ� ROWNUM ���� �ο� ��
--   3) ��ȣ����� �߶��ִ� ROWNUM <= 5 ���� ����

SELECT ROWNUM, EMP_NAME, SALARY     -- 3 : SELECT
FROM (SELECT *                                           -- 0 : RESULT SET ��ȸ / 1 : FROM
            FROM EMPLOYEE
            ORDER BY SALARY DESC)
 WHERE ROWNUM <= 5;                              -- 2 : WHERE

-- FROM �� �ζ��κ信 ��Ī�� ���� �� ����
SELECT ROWNUM "����", E.*
FROM (SELECT * 
            FROM EMPLOYEE
            ORDER BY SALARY DESC) E
WHERE ROWNUM <= 3;


-- �� �μ��� ��� �޿��� ���� 3���� �μ��ڵ�, ��� �޿� ��ȸ
SELECT ROWNUM "����", DEPT_CODE, ROUND(��ձ޿�) "��ձ޿�"
FROM (SELECT DEPT_CODE, AVG(SALARY) "��ձ޿�" -- �ζ��κ� ���ο��� �׷��Լ� �̿��� �� ������������ ����Ϸ��� �ݵ�� ��Ī �ٿ��� �Ѵ�.
            FROM EMPLOYEE
            GROUP BY DEPT_CODE
            ORDER BY AVG(SALARY) DESC) E
WHERE ROWNUM <= 3;

-- ���� �ֱٿ� �Ի��� ��� 5�� ��ȸ (�����, �޿�, �Ի���)
SELECT ROWNUM, E.*
FROM (SELECT EMP_NAME, SALARY, HIRE_DATE
            FROM EMPLOYEE
            ORDER BY HIRE_DATE DESC) E
WHERE ROWNUM <= 5;

--> TOP-N �м��� ORDER BY ���� ���� ������ ���� ���� �Ͼ�� �ϱ� ������
-- ORDER BY ���� ���� �Ͼ �� �ְԲ� �ζ��κ並 ����ؾ� �Ѵ�.

-------------------------------------------------------------------------------------------------------
/*
        6. ���� �ű�� �Լ� (WINDOW �Լ�)
        RANK() OVER (���� ����)
        DENSE_RANK() OVER (���� ����)
        
        ���ǻ��� : ������ SELECT �������� �ۼ� ����
                       ����Ŭ���� ������ �ű�� �Լ��� ������ �Լ���� �θ� (SELECT �������� �� �� ����)
                       
        �� �Լ��� ������
        - RANK() OVER (���ı���) : ���� ���� 1���� 3���̸� ���� ������ 4�� (�� �������� = ���� ���� + ���)
        - DENSE_RANK() OVER (���ı���) : ���� ���� 1���� 3���̾ ���� ���� 2�� (�� �������� = ������� + 1)
*/

-- ������� �޿��� ���� ������� �Űܼ� �����, �޿�, ���� ��ȸ (RANK)
SELECT EMP_NAME, SALARY
            , RANK() OVER (ORDER BY SALARY DESC) "����"
FROM EMPLOYEE;
-- ���� 19�� 2��, �� ���� ������ 21��

-- ������� �޿��� ���� ������� �Űܼ� �����, �޿�, ���� ��ȸ (DENSE_RANK)
SELECT EMP_NAME, SALARY, DENSE_RANK() OVER (ORDER BY SALARY DESC) "����"
FROM EMPLOYEE;
-- ���� 19�� 2��, �� ���� ������ 20��


-- 5�������� ��ȸ�ϰڴ� (TOP-N �м�)
SELECT *
FROM (SELECT EMP_NAME, SALARY, RANK() OVER (ORDER BY SALARY DESC) "��������"
            FROM EMPLOYEE)
WHERE ROWNUM <= 5;
