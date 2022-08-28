/*
    < JOIN >
    
    �� �� �̻��� ���̺��� �����͸� ���� ��ȸ�ϰ��� �� �� ���Ǵ� ����
    ��ȸ ����� �ϳ��� ����� (RESULT SET)�� ����
    
    ������ �����ͺ��̽������� �ּ����� �����ͷ� ������ ���̺� �����͸� �����ϰ� �ִ�
    (�ߺ��� �ּ�ȭ�ϱ� ���ؼ� �ִ��� �ɰ��� ����)
    => ��, JOIN ������ �̿��ؼ� ���� ���̺� ���� ���踦 �ξ� ���� ��ȸ�ؾ� ��
    => ��, ������ JOIN ������ �ۼ��ؼ� ��ȸ�� �ϴ� �� �ƴ϶�
        ���̺� ������� �ش��ϴ� �÷�(�ܷ�Ű)�� ��Ī���Ѽ� JOIN ����� ��
        
    JOIN �� ũ�� ����Ŭ ���� ������ ANSI(�̱� ���� ǥ�� ��ȸ) �������� ������.
    
                        ����Ŭ ���뱸��                    |                   ANSI ����
    =================================================================
                            �����                          |         ���� ���� (INNER JOIN)  => JOIN USING / ON
                        (EQUAL JOIN)                    |         �ܺ� ���� (OUTER JOIN) 
   -------------------------------------------------------------------------------------------------------
                            ��������                         |
                     (LEFT OUTER)                      |          ���� �ܺ� ���� (LEFT OUTER JOIN)
                     (RIGHT OUTER)                    |         ������ �ܺ� ���� (RIGHT OTHER JOIN)
                                                                |          ��ü �ܺ� ���� (FULL OTHER JOIN) : ����Ŭ������ �Ұ�
   -------------------------------------------------------------------------------------------------------
                    ī�׽þ� ��
   -------------------------------------------------------------------------------------------------------
*/

-- ��ü ������� ���, �����, �μ��ڵ�, �μ���
SELECT EMP_ID, EMP_NAME, DEPT_CODE -- DEPT_CODE = DEPT_ID �÷�
FROM EMPLOYEE;

SELECT DEPT_ID, DEPT_TITLE -- DEPT_ID = DEPT_CODE �÷�
FROM DEPARTMENT;

-- ����Ŭ ����
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;

-- ��ü ������� ���, �����, �����ڵ�, ���޸� ��ȸ
SELECT EMP_ID, EMP_NAME, JOB_CODE
FROM EMPLOYEE;

SELECT JOB_CODE, JOB_NAME
FROM JOB;

-- ����Ŭ ����
SELECT EMP_ID, EMP_NAME, E.JOB_CODE, JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE;

--> ������ ���� ���� ���� �� ��Ī��Ű�� �ϳ��� ������� ��ȸ ����

-------------------------------------------------------------------------------------------------------
/*
        1. ����� (EQUAL JOIN) / �������� (INNER JOIN)
        �����Ű���� �÷��� ������ ��ġ�ϴ� ��鸸 ���εǼ� ��ȸ
        ( == ��ġ���� �ʴ� ������ ��ȸ���� ����)
*/

-->> ����Ŭ ���� ����
-- FROM ���� ��ȸ�ϰ��� �ϴ� ���̺���� ���� (, ��)
-- ex) FROM EMPLOYEE, JOB
-- WHERE ���� ��Ī��ų �÷���(�ܷ�Ű)�� ���� ���� ����

-- ��ü ������� ���, �����, �μ��ڵ�, �μ��� ���� ��ȸ
-- 1) ������ �÷����� ���� �ٸ� ���
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;
-- ��ġ���� �ʴ� ���� ��ȸ���� ���ܵǾ� 23�� �� 21�� ��ȸ��
-- (DEPT_CODE �� NULL�̾��� 2���� �����ʹ� ��ȸ�� �� ��)
-- (DEPT_ID �� D3, D4, D7�� �μ��� �μ����� ��ȸ �� �� = EMPLOYEE ���̺� �÷��� �������� �ʾƼ�)

-- ��ü ������� ���, �����, �����ڵ�, ���޸� ��ȸ
-- 2) ������ �÷����� ���� ���� ���
-- SELECT EMP_ID, EMP_NAME, JOB_ID, JOB_NAME
-- FROM EMPLOYEE, JOB
-- WHERE JOB_ID = JOB_ID;
-- ���� : AMBIGUOUSLY DEFINED : �÷��� ��ȣ�ϴ�
--> � ���̺��� ��� �ø����� Ȯ���� �������� �Ѵ�.

-- �ذ��� 1) ���̺�� �̿��ϴ� ��� (���̺��.�÷���)
SELECT EMP_ID, EMP_NAME, EMPLOYEE.JOB_CODE, JOB.JOB_CODE, JOB_NAME
FROM EMPLOYEE, JOB
WHERE EMPLOYEE.JOB_CODE = JOB.JOB_CODE;

-- �ذ��� 2) ���̺� ��Ī�� �ٿ��� ��Ī�� �̿��ϴ� ��� (�� ���̺��� ��Ī �ο� ����, ��Ī.�÷���)
SELECT EMP_ID, EMP_NAME, J.JOB_CODE, JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE;


-->> ANSI ����
-- FROM ���� ������ �� ���̺�� �ϳ��� ����� ��
-- �� �ڿ� JOIN ������ ���� ��ȸ�ϰ��� �ϴ� ���̺� �� ���, ���� ��Ī��ų �÷��� ���� ���ǵ� ���� ���
-- ( USING / ON ����)

-- ��ü ������� ���, �����, �μ��ڵ�, �μ��� ���� ��ȸ
-- 1) ������ �÷����� ���� �ٸ� ��� : ON ������ ����
-- JOIN ���̺�� ON (���ǽ�)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE -- ���� ���̺�
INNER JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
-- INNER JOIN���� INNER�� �����ϰ� ��� ��

-- ��ü ������� ���, �����, �����ڵ�, ���޸� ��ȸ
-- 2) ������ �÷����� ���� ���� ��� : ON ����, USING ���� �� �� ����
-- JOIN ���̺�� USING(�÷���) �Ǵ� JOIN ���̺�� ON(���ǽ�)

-- 2_1) ON ���� �̿� : AMBIGUOUSLY ������ �߻��� �� �ֱ� ������ ���̺��/��Ī ����ؾ� ��.
SELECT EMP_ID, EMP_NAME, EMPLOYEE.JOB_CODE, JOB_NAME
FROM EMPLOYEE
JOIN JOB ON (EMPLOYEE.JOB_CODE = JOB.JOB_CODE);
-- �Ǵ�
SELECT EMP_ID, EMP_NAME, E.JOB_CODE, JOB_NAME
FROM EMPLOYEE E
JOIN JOB J ON (E.JOB_CODE = J.JOB_CODE);

-- 2_2) USING ���� �̿� : AMBIGUOUSLY ������ �߻� X
--                                    ������ �÷��� �ϳ��� �� �����ָ� �˾Ƽ� ��Ī������
SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE);

-- [ ���� ] ���� ���ô� NATURAL JOIN (�ڿ�����)���ε� ����
SELECT EMP_ID, EMP_NAME, JOB_CODE, JOB_NAME
FROM EMPLOYEE
NATURAL JOIN JOB;
-- �� ���� ���̺� ������ ���� + �� ���̺� ��ġ�� Į������ �����ϰ� �� �� ���� (JOB_CODE)

--> ON������ USING ������ �� ��ģ ����
SELECT EMP_NAME, JOB_CODE, JOB_NAME, DEPT_CODE, DEPT_TITLE
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE) 
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);

-- �߰����� ���ǵ� ���� ����
-- ������ �븮�� ������� ���� ��ȸ (���, �����, �޿�, ���޸�)

-->> ����Ŭ ���� ����
SELECT EMP_ID, EMP_NAME, SALARY, JOB_NAME
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE   -- ���� ���� ���� ����
     AND J.JOB_NAME = '�븮';     -- �߰����� ����
-- ���� �� �������� ���� ������ �� �پ� ����, �鿩���� �ؼ� ����

-->> ANSI ����
SELECT EMP_ID, EMP_NAME, SALARY, JOB_NAME
FROM EMPLOYEE
-- JOIN JOB USING (JOB_CODE)                                            -- USING ����
JOIN JOB ON(EMPLOYEE.JOB_CODE = JOB.JOB_CODE)       -- ON ����
WHERE JOB_NAME = '�븮';

-------------------------------------------------------------------------------------------------------

-- �ǽ� ���� --
-- 1. �μ��� '�λ������'�� ������� ���, �����, ���ʽ� ��ȸ
-->> ����Ŭ ���� ����
SELECT EMP_ID, EMP_NAME, BONUS
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
     AND DEPT_TITLE = '�λ������'; 

-->> ANSI ����
SELECT EMP_ID, EMP_NAME, BONUS
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE DEPT_TITLE = '�λ������';

-- 2. �μ��� '�ѹ���'�� �ƴ� ������� �����, �޿�, �Ի��� ��ȸ
-->> ����Ŭ ���� ����
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
    AND DEPT_TITLE != '�ѹ���';

-->> ANSI ����
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE DEPT_TITLE != '�ѹ���';

-- 3. ���ʽ��� �޴� ������� ���, �����, ���ʽ�, �μ��� ��ȸ
-->> ����Ŭ ���� ����
SELECT EMP_ID, EMP_NAME, BONUS, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
    AND BONUS IS NOT NULL;
--> DEPT_CODE�� NULL������, ���ʽ��� NULL�� �ƴ� ����� �����Ǵ� �̽�

-->> ANSI ����
SELECT EMP_ID, EMP_NAME, BONUS, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE BONUS IS NOT NULL;

-- 4. �Ʒ��� �� ���̺��� �����Ͽ� �μ��ڵ�, �μ���, �����ڵ�, ������ (LOCAL_NAME) ��ȸ
SELECT * FROM DEPARTMENT; -- LOCATION_ID
SELECT * FROM LOCATION; -- LOCAL_CODE

-->> ����Ŭ ���� ����
SELECT DEPT_ID, DEPT_TITLE, LOCAL_CODE, LOCAL_NAME
FROM DEPARTMENT, LOCATION
WHERE LOCATION_ID = LOCAL_CODE;

-->> ANSI ����
SELECT DEPT_ID, DEPT_TITLE, LOCATION_ID, LOCAL_NAME
FROM DEPARTMENT
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);

-------------------------------------------------------------------------------------------------------
/*
    2. �������� / ����, ������, ��ü �ܺ�����
    ���̺� ���� JOIN �� ��ġ���� ���� �൵ ���Խ��Ѽ� ��ȸ ����
    ��, �ݵ�� LEFT / RIGHT �� ���� ���̺��� �����ؾ���
    
    => �ϴ� ��ġ�ϴ� �͵� ��ȸ + �������̺��� �����ƴ� �͵� �Բ� ��ȸ
*/

-- "��ü" ������� �����, �޿�, �μ��� ��ȸ
-- ����Ŭ ���� ����
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID;

-- ANSI ����
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
--> DEPT_CODE�� NULL�� �� ���� ��� ������ ��ȸ���� ����.
--> EMPLOYEE ���̺� �������� �ʴ� �μ� ������ ��ȸ���� ����. (�μ��� ������ ����� ���� �μ�) 
---->> �⺻������ ��������(�����)�� ��ġ�ϴ� �͸� ��ȸ�ϱ� ������ ���� �� �׸��� ��ȸ���� ����.


-- 1) LEFT OUTER JOIN : �� ���̺� �� ���ʿ� ����� ���̺��� �������� JOIN
--                                   ��, � �÷��̵� ���� ����� ���̺��� �����ʹ� ������ �ѹ��� ��ȸ�Ѵ� (��ġ�ϴ� ���� ã�� ���ϴ���)

-->> ANSI ����
-- FROM �������̺�� LEFT OUTER JOIN ���̺�� ON/USING���� (OUTER Ű����� ���� ����)
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE
LEFT OUTER JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
--> ON ������ ����� ���ǹ��� �ش��ϴ� �κ��� �� ��ȸ + ���� ���̺��� ������ ������ �ѹ��� �� ��ȸ
-- ���� ���̺� ������ �����ʹ� �÷����� ���� �Ǿ��� ���� ��ȸ�ǰԲ� �ϰڴ�.

-->> ����Ŭ ���� ����
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID(+);
-- ���� ���̺��� �ƴ� �ݴ� ���̺� �÷��� (+) ��ȣ�� �ٿ��ش�.


-- 2) RIGHT OUTER JOIN : �� ���̺� �� ������ ����� ���̺��� �������� JOIN
--                                      � �÷��̵� ������ ����� ���̺��� �����ʹ� ������ �ѹ��� ��ȸ�Ѵ� (��ġ�ϴ� ���� ã�� ���ϴ���)

-->> ANSI ����
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE
RIGHT OUTER JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID); -- DEPARTMENT ����
-- RIGHT JOIN ���� ���� ����
-- (���� ������ LEFT JOIN �������� �ٲٸ�)
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM DEPARTMENT
LEFT JOIN EMPLOYEE ON (DEPT_CODE = DEPT_ID);

-->> ����Ŭ ���� ����
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE (+) = DEPT_ID;

-- LEFT /RIGHT OUTER JOIN�� ����� �ϴ� ������� ����� �������� �͵��� �ѹ��� �� ��ȸ�ϰ� �� ��
-- ������ �Ǵ� ���̺��� ������ ������ �߰������� ��ȸ


-- 3) FULL OUTER JOIN : ��ü �ܺ�����, �� ���̺��� ���� ��� ���� ��ȸ�� �� �ֵ��� JOIN
--                                   (����Ŭ ���� ���������� ��� �Ұ�)

-->> ANSI ����
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE
FULL OUTER JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID);
-- ������ �ٲ�ᵵ ������ ���
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM DEPARTMENT
FULL OUTER JOIN EMPLOYEE ON (DEPT_CODE = DEPT_ID);
--> ��ġ�ϴ� ���빰 ��ȸ + ���� ���̺��� ������ �� ��ȸ + ������ ���̺��� ������ �� ��ȸ

-->> ����Ŭ ���� ���� ���� ���� �߻� ( only one outer-joined table )
/*
SELECT EMP_NAME, SALARY, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE (+) = DEPT_ID (+);
*/

-------------------------------------------------------------------------------------------------------
/*
    3. ī�׽þ� �� (CARTESIAN PRODUCT) / �������� (CROSS JOIN)
    ��� ���̺��� �� ����� ���μ��� ���ε� ����� ��ȸ�� (������) : ��� ����� �� ���
    �� ���̺��� ����� ��� ������ ����� ������ ��� �� ��µ� => ����� ������ ��� => �������� ����
    
    => ���� ������ �߸��Ǿ��ų� �����Ǿ��� ��� �߻��ϱ� ������ �����ؾ� �Ѵ�.
    
    ��) ���� N��, M�� ���� ���� ���̺��� ī�׽þ� �� ������� N * M���� ���� ����
*/

-- �����, �μ��� ��ȸ
-->> ����Ŭ ���� ����
SELECT EMP_NAME, DEPT_TITLE
FROM EMPLOYEE, DEPARTMENT; --> 23�� * 9�� = 207�� ���� ��ȸ��

-->> ANSI ����
SELECT EMP_NAME, DEPT_TITLE
FROM EMPLOYEE
CROSS JOIN DEPARTMENT;

-------------------------------------------------------------------------------------------------------
/*
    4. �� ���� (NON EQUAL JOIN)
    
    '=' (����񱳿�����) �� ������� �ʴ� ���ι�
    ������ �÷����� ��ġ�ϴ� ��찡 �ƴ� ������ ���ԵǴ� ��쿡 ��Ī���Ѽ� ��ȸ�Ѵٴ� ��
*/

-- �����, �޿�, �޿����(EMPLOYEE)
SELECT EMP_NAME, SALARY, SAL_LEVEL
FROM EMPLOYEE;

-- SAL_GRADE ��ȸ
SELECT *
FROM SAL_GRADE;

-- �����, �޿�, �޿����(EMPLOYEE)
-->> ����Ŭ ���� ����
SELECT EMP_NAME, SALARY, S.SAL_LEVEL
FROM SAL_GRADE S, EMPLOYEE
-- WHERE SALARY >= MIN_SAL AND SALARY <= MAX_SAL; -- ������� ���� ����
WHERE SALARY BETWEEN MIN_SAL AND MAX_SAL;

-->> ANSI ���� (ON ������ ��� ����)
SELECT EMP_NAME, SALARY, S.SAL_LEVEL
FROM EMPLOYEE
JOIN SAL_GRADE S ON (SALARY BETWEEN MIN_SAL AND MAX_SAL);
--                              ON (SALARY >= MIN_SAL AND SALARY <= MAX_SAL )

-------------------------------------------------------------------------------------------------------
/*
    5. ��ü���� (SELF JOIN)
    ���� ���̺��� �ٽ� �� �� �����ϴ� ���
    ��, �ڱ� �ڽ��� ���̺�� �ٽ� ������ �δ� ���
*/

-- ����� ���, �����, ��� �޿�, ����� ���
SELECT EMP_ID "����� ���"
            , EMP_NAME "�����"
            , SALARY "��� �޿�"
            , MANAGER_ID "����� ���"
FROM EMPLOYEE;

-- ����� ���, �����, ����� �μ��ڵ�, ����� �޿�
-- ����� ���, �����, ����� �μ��ڵ�, ����� �޿�

-- ��� ����, ��� ������ ���� �����س� ���̺��� ��Ī ���̱�
SELECT * FROM EMPLOYEE E; -- ��� ���� ����� ���̺� : E => ����� �÷� : MANAGER_ID
SELECT * FROM EMPLOYEE M; -- ��� ���� ����� ���̺� : M => ����� �÷� : EMP_ID

SELECT E.EMP_ID "����� ���", E.EMP_NAME "�����", E.DEPT_CODE "����� �μ��ڵ�", E.SALARY "����� �޿�"
            , M.EMP_ID "����� ���", M.EMP_NAME "�����", M.DEPT_CODE "����� �μ��ڵ�", M.SALARY "����� �޿�"
FROM EMPLOYEE E, EMPLOYEE M
-- WHERE E.MANAGER_ID = M.EMP_ID;
-- ����ο� ���� ������ �������� ���� ��� ������ NULL�� �ƴ� ����� �������� ��.
WHERE E.MANAGER_ID = M.EMP_ID(+);
-- �������ο� ���� ������ �������� �� (NULL�� ���) �� �����ؼ� ��ȸ�ȴ�.

-->> ANSI ����
SELECT E.EMP_ID "����� ���", E.EMP_NAME "�����", E.DEPT_CODE "����� �μ��ڵ�", E.SALARY "����� �޿�"
            , M.EMP_ID "����� ���", M.EMP_NAME "�����", M.DEPT_CODE "����� �μ��ڵ�", M.SALARY "����� �޿�"
FROM EMPLOYEE E
-- �������� ���� �ۼ��� ��� ������ NULL�� �ƴ� ����� �������� ��
-- JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID);
-- ���� �ܺ����� ���� �ۼ��� NULL�� ��쵵 �����ؼ� ��ȸ��
LEFT JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID);

-------------------------------------------------------------------------------------------------------
/*
    < ���� JOIN >
    
    3 �� �̻��� ���̺��� ����
*/

-- ���, �����, �μ���, ���޸�
SELECT * FROM EMPLOYEE;      -- DEPT_CODE        JOB_CODE
SELECT * FROM DEPARTMENT; -- DEPT_CODE
SELECT * FROM JOB;                 --                            JOB_CODE

-->> ����Ŭ ���� ����
SELECT EMP_ID "���"
            , EMP_NAME "�����"
            , DEPT_TITLE "�μ���"
            , JOB_NAME "���޸�"
FROM EMPLOYEE E, JOB J, DEPARTMENT
-- WHERE DEPT_CODE = DEPT_ID �� ����� �ο��� ��� : �μ���ġ �� �� (NULL��) ��� ��ȸ �� ��
WHERE DEPT_CODE = DEPT_ID(+)                -- EMPLOYEE �� DEPARTMENT ���� ���� : NULL ���� ��ȸ
     AND E.JOB_CODE = J.JOB_CODE;              -- EMPLOYEE �� JOB � ����
--  AND JOB_CODE = JOB_CODE �� ����ϸ� AMBIGUOUSLY ���� �߻�

-- ANSI ����
SELECT EMP_ID "���"
            , EMP_NAME "�����"
            , DEPT_TITLE "�μ���"
            , JOB_NAME "���޸�"
FROM EMPLOYEE
-- JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID) �� �������ν� NULL �� ���� �� ��
LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)  -- LEFT OUTER JOIN : NULL �� ����
         JOIN JOB USING (JOB_CODE);


-- ���, �����, �μ���, ���޸�, �ٹ������� ��ȸ (LOCAL_NAME)
SELECT * FROM EMPLOYEE;      -- DEPT_CODE        JOB_CODE       
SELECT * FROM DEPARTMENT; -- DEPT_CODE                                  LOCATION _ID
SELECT * FROM JOB;                 --                            JOB_CODE
SELECT * FROM LOCATION;     --                                                      LOCAL_CODE

-->> ����Ŭ ���� ����
SELECT EMP_ID ���
            , EMP_NAME �����
            , DEPT_TITLE �μ���
            , JOB_NAME ���޸�
            , LOCAL_NAME �ٹ�������
FROM EMPLOYEE E, DEPARTMENT, JOB J, LOCATION
WHERE DEPT_CODE = DEPT_ID(+)
    AND E.JOB_CODE = J.JOB_CODE
    AND LOCATION_ID = LOCAL_CODE;

-->> ANSI ����
SELECT EMP_ID ���
            , EMP_NAME �����
            , DEPT_TITLE �μ���
            , JOB_NAME ���޸�
            , LOCAL_NAME �ٹ�������
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE); -- ��ǻ� DEPARTMENT �� �����ϴ� �����̹Ƿ�,
                                                                                     -- DEPARTMENT �Ʒ��� �־�� ������ ������.
--> ���� JOIN ��, ANSI ������ �̿��� ��� ���̺���� ����ϴ� ������ �߿�
--(ex) LOCATION ���̺��� DEPARTMENT ���̺��� ���� ���εǸ� EMPLOYEE ���̺�� �����ϴ� ���̹Ƿ�
--       EMPLOYEE ���̺� LOCATION_ID �÷��� ��� ���� �߻��ϰ� ��)

-- �����, �μ���, ���޸�, �ٹ�������, �ٹ�������, �޿����(SAL_GRADE���̺�)
SELECT * FROM EMPLOYEE;      -- DEPT_CODE        JOB_CODE        SALARY
SELECT * FROM DEPARTMENT; -- DEPT_ID                                                                     LOCATION _ID
SELECT * FROM JOB;                 --                            JOB_CODE                                                                     NATIONAL_CODE
SELECT * FROM LOCATION;     --                                                                                   LOCAL_CODE
SELECT * FROM SAL_GRADE;    --                                                  MIN_SAL, MAX_SAL
SELECT * FROM NATIONAL;      --                                                                                                                  NATIONAL_CODE

-->> ����Ŭ ���� ����
SELECT E.EMP_NAME "�����"
            , D.DEPT_TITLE "�μ���"
            , J.JOB_NAME "���޸�"
            , L.LOCAL_NAME "�ٹ�������"
            , N.NATIONAL_NAME "�ٹ�������"
            , S.SAL_LEVEL "�޿����"
FROM EMPLOYEE E, DEPARTMENT D, JOB J, LOCATION L, SAL_GRADE S, NATIONAL N
WHERE E.DEPT_CODE = D.DEPT_ID
    AND E.JOB_CODE = J.JOB_CODE
    AND D.LOCATION_ID = L.LOCAL_CODE
    AND E.SALARY BETWEEN S.MIN_SAL AND S.MAX_SAL
    AND L.NATIONAL_CODE = N.NATIONAL_CODE;
--> ���ǻ��� : ���������� ���̺��� �������� �÷����� �ߺ��� �� ������
--                  �ִ��� �򰥸��� �ʰ� ��Ī�� ���̴� ���� ����ȭ �ϱ�

-->> ANSI ����
SELECT E.EMP_NAME "�����"
            , D.DEPT_TITLE "�μ���"
            , J.JOB_NAME "���޸�"
            , L.LOCAL_NAME "�ٹ�������"
            , N.NATIONAL_NAME "�ٹ�������"
            , S.SAL_LEVEL "�޿����"
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
JOIN JOB J USING (JOB_CODE)
JOIN LOCATION L ON (D.LOCATION_ID = L.LOCAL_CODE)
JOIN NATIONAL N ON (L.NATIONAL_CODE = N.NATIONAL_CODE)
JOIN SAL_GRADE S ON (E.SALARY BETWEEN S.MIN_SAL AND S.MAX_SAL);

-- ��� ���, �����, ����� �μ���, ��� �޿�
-- ��� ���, �����, ����� �μ���, ��� �޿�

-->> ����Ŭ ���� ����
SELECT E.EMP_ID "��� ���", E.EMP_NAME "�����", D1.DEPT_TITLE "��� �μ���", E.SALARY "��� �޿�"
            , M.EMP_ID "��� ���", M.EMP_NAME "�����", D2.DEPT_TITLE "��� �μ���", M.SALARY "��� �޿�"
FROM EMPLOYEE E, EMPLOYEE M, DEPARTMENT D1, DEPARTMENT D2
WHERE E.MANAGER_ID = M.EMP_ID
     AND E.DEPT_CODE = D1.DEPT_ID
     AND M.DEPT_CODE = D2.DEPT_ID;

-->> ANSI ����
SELECT E.EMP_ID "��� ���", E.EMP_NAME "�����", D1.DEPT_TITLE "��� �μ���", E.SALARY "��� �޿�"
            , M.EMP_ID "��� ���", M.EMP_NAME "�����", D2.DEPT_TITLE"��� �μ���", M.SALARY "��� �޿�"
FROM EMPLOYEE E
JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID)
JOIN DEPARTMENT D1 ON (E.DEPT_CODE = D1.DEPT_ID)
JOIN DEPARTMENT D2 ON (M.DEPT_CODE = D2.DEPT_ID);