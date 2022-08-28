/*
    < GROUP BY �� >
    �׷��� ������ ������ ������ �� �ִ� ����
    => �ش� ���õ� ���� ���� �׷��� ���� �� ����
    
    ���� ���� ������ �ϳ��� �׷����� ��� ó���� �������� ��� (���)
*/

-- ��ü ����� �� �޿� ��
SELECT SUM(SALARY)
FROM EMPLOYEE; --> ���� ��ȸ�� ��ü ������� �ϳ��� �׷����� ��� �� ���� ���� ���


-- �� �μ��� �� �޿� ��
SELECT DEPT_CODE,  SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- ��ü ��� ��
SELECT COUNT(*)
FROM EMPLOYEE;

-- �� �μ��� ��� ��
SELECT DEPT_CODE, COUNT(*)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- �� �μ��� �� �޿� ���� �μ��ڵ� �������� ���ķ� ��ȸ
SELECT DEPT_CODE, SUM(SALARY)     -- 3
FROM EMPLOYEE                                   -- 1
GROUP BY DEPT_CODE                         -- 2
ORDER BY DEPT_CODE ASC;                -- 4

-- �� ���� �� �����ڵ�, �� �޿��� ��, ��� ��, ���ʽ��� �޴� ��� ��, ��� �޿�, �ְ� �޿�, �ּ� �޿� ��ȸ
SELECT JOB_CODE "���� �ڵ�"
            , SUM(SALARY) "�� �޿��� ��"
            , COUNT(*) "��� ��"
            , COUNT(BONUS) "���ʽ� �޴� ���"
            , ROUND(AVG(SALARY)) "��� �޿�"
            , MAX(SALARY) "�ְ� �޿�"
            , MIN(SALARY) " �ּ� �޿�"
FROM EMPLOYEE
GROUP BY JOB_CODE
ORDER BY JOB_CODE ASC;

-- �� �μ��� �μ��ڵ�, ��� ��, ���ʽ� �����, ��� �ִ� ��� ��, ��� �޿�
SELECT DEPT_CODE "�μ��ڵ�"
            , COUNT(*) "�����"
            , COUNT(BONUS) "���ʽ� ��� ��"
            , COUNT(MANAGER_ID) "��� �ִ� ���"
            , ROUND(AVG(SALARY)) "��� �޿�"
FROM EMPLOYEE
GROUP BY DEPT_CODE -- GROUP BY ������ ��Ī ��� �Ұ�
ORDER BY DEPT_CODE;

-- ���� �� ��� ��
SELECT SUBSTR(EMP_NO, 8, 1) ����, COUNT(*) �����
FROM EMPLOYEE
GROUP BY SUBSTR(EMP_NO, 8, 1);

-- DECODE �Լ� ����
SELECT DECODE(SUBSTR(EMP_NO, 8, 1), '1', '����', '2', '����') ����
            , COUNT(*) �����
FROM EMPLOYEE
GROUP BY SUBSTR(EMP_NO, 8, 1);

-- �Ի�⵵ �� �ο� ��, ��, 2015�⵵ ���� ��ȸ, 2015�⵵���� �������� ����
SELECT EXTRACT(YEAR FROM HIRE_DATE) "�Ի�⵵", COUNT(*) "�ο� ��"    -- 4
FROM EMPLOYEE                                                                                                -- 1
WHERE EXTRACT(YEAR FROM HIRE_DATE) >= 2015                                      -- 2
GROUP BY EXTRACT(YEAR FROM HIRE_DATE)                                               -- 3
ORDER BY EXTRACT(YEAR FROM HIRE_DATE);                                              -- 5

----------------------------------------------------------------------------------------------------
/*
    < HAVING �� >
    �׷쿡 ���� ������ ������ �� ���Ǵ� ����
    (�׷� �Լ��� ���Ե� ���ǽ��� ������ ��� ������ HAVING ���� �ۼ��ؾ� �Ѵ�)
*/

-- �� �μ� �� ��� �޿��� 300 ���� �̻��� �μ��鸸 ��ȸ
SELECT DEPT_CODE "�μ��ڵ�"
            , ROUND(AVG(SALARY)) "��� �޿�"
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING ROUND(AVG(SALARY)) >= 3000000;
-- WHERE������ �׷��Լ����� ����� �� ����.

-- �� ���� �� �� �޿� ���� 1000���� �̻��� ���� �ڵ�, �޿� �� ��ȸ
SELECT  JOB_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY JOB_CODE
HAVING SUM(SALARY) >= 10000000;

-- �� �μ� �� ���ʽ��� �޴� ����� �� �� ���� �μ����� ��ȸ
SELECT DEPT_CODE, COUNT(*), COUNT(BONUS)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(BONUS) = 0;

-- ���ʽ��� �޴� ��� �� : COUNT(BONUS)
-- COUNT(BONUS) > 0 : �� ���̶� ���ʽ��� �޴� ����� �ִ�.
-- COUNT(BONUS) = 0 : �� �� ���ʽ��� ���� �ʴ´�.

-- �� �μ� �� ���ʽ��� �޴� ����� �� ���̶� �ִ� �μ����� ��ȸ
SELECT DEPT_CODE, COUNT(*), COUNT(BONUS)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(BONUS) > 0;

----------------------------------------------------------------------------------------------------
/*
    < ���� ���� >

    SELECT * / ��ȸ�ϰ����ϴ��÷��� / ���ͷ� / �������� / �Լ��� AS "��Ī"
    FROM ��ȸ�ϰ����ϴ����̺�� / DUAL(�������̺��)
    WHERE ���ǽ� (���� ����, �׷��Լ��� ��� �Ұ�)
    GROUP BY �׷���ؿ��ش��ϴ��÷��� / �Լ���
    HAVING �׷��Լ��Ŀ� ���� ���ǽ�
    ORDER BY [���� ���ؿ� �ش��ϴ� �÷��� / ��Ī / �÷� ����]   [ASC / DESC NULLS]   [FIRST / NULLS LAST] (���� ����)

    FROM �� WHERE �� GROUP BY �� HAVING �� SELECT �� ORDER BY
*/

----------------------------------------------------------------------------------------------------
/*
    < ���� ������ SET OPERATOR >
    ���� ���� ���� ���� ������ �ϳ��� ���� ������ ����� ������
    
    - UNION : ������ (�� �������� ������ ������� ���� �� �ߺ��Ǵ� �κ��� �� �� �� ��) => OR�� ������ ����
    - INTERSECT : ������ (�� �������� ������ ����� �� �ߺ��� ����� �κ�) => AND �� ������ ����
    - UNION ALL :������ ����� �������� �� �� ������ ���� 
                         (�� ���� ���� ������ ������� ������ ����, ��, �����տ��� �ߺ� ���Ÿ� ���� ���� ����)
                         => �ߺ��� ����� �� �� ��ȸ�� �� ����
    - MINUS : ������ (���� ������ ��������κ��� ���� ������ ������� �� ��)
*/

-- 1. UNION : ������ - �� �������� ������ ������� ���� �� �ߺ��Ǵ� ����� �ѹ��� ��ȸ
-- �μ��ڵ尡 D5�̰ų� �Ǵ� �޿��� 300���� �ʰ��� ����� ��ȸ(���, �����, �μ��ڵ�, �޿�)

-- �μ��ڵ尡 D5�� ����鸸 ��ȸ
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'; -- 6�� ��ȸ

-- �޿��� 300���� �ʰ��� ����鸸 ��ȸ
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- 8�� ��ȸ

-- UNION ������ ����
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
UNION
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- 12�� ��ȸ

-- ���� ���� �� �׻� �� ������ SELECT ���� ������ ��ġ�ؾ� �Ѵ�.

-- ���� UNION ������ �ϳ��� SELECT������ ǥ���ϱ� - OR ������ ���
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5' OR SALARY < 3000000;
--> OR �����ڷ� �� ���� ������ ��� ��ȸ�ص� ����� ����

-- �� �μ��� �޿� �� ��ȸ (�μ��ڵ�, �μ��� �޿���)
-- GROUP BY ����
SELECT DEPT_CODE, SUM(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- UNION ������ ����
SELECT SUM(SALARY)
FROM EMPLOYEE
WHERE DEPT_CODE = 'D1'
UNION
SELECT SUM(SALARY)
FROM EMPLOYEE
WHERE DEPT_CODE = 'D2';
-- .. �� �μ����� WHERE ���� �̿��ؼ� ������ UNION ���� ��ġ�� ����� �����ϱ�� ��.

-- 2. UNION ALL : ���� ���� ������ ������� ������ ���ϴ� ������ (�ߺ� �����ϱ� ��)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
UNION ALL
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- 14���� ��ȸ�� (�ߺ� 2��)

-- 3. INTERSECT : ���� ���� ������ ����� �� �ߺ��Ǵ� ����� ��ȸ
-- �μ� �ڵ尡 D5�̸鼭 �޿��� 300���� �ʰ��� ����� (���, �����, �μ��ڵ�, �޿�)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
INTERSECT
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- �ߺ��Ǵ� 2�� ��ȸ

-- AND ������ �̿�
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5' AND SALARY > 3000000;

-- 4. MINUS : ������ - ���� ���� ������� ���� ���� ����� �� ������
-- ��𿡼� ������ ���Ŀ� ���� ����� �ٸ��Ƿ� ������ �߿��ϴ�.
-- �μ� �ڵ尡 D5�� ����� �� �޿��� 300���� �ʰ��� ������� �����ϰ� ��ȸ (���, �����, �μ��ڵ�, �޿�)
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5' -- 6�� (���� ����)
MINUS
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000; -- 6- 2�� = 4�� (���� ����(�ߺ� 2��))

-- �μ��ڵ尡 D5�� ����� �� �޿��� 300���� ������ �����
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5' AND SALARY <= 3000000;

-- ���� ���� ������ ���� ������ �ٲ۴ٸ�?
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000 -- 8�� (���� ����)
MINUS
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'; -- 6�� (���� �������� ��ġ�� 2�� ����)

-- AND ������ ����
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > 3000000 AND DEPT_CODE != 'D5';

----------------------------------------------------------------------------------------------------
---- KH ���� SELECT �⺻ ���� ���� ----
-- 1. EMPLOYEE ���̺��� �����, ��� �̸�, ���� ��ȸ
SELECT HIRE_DATE, EMP_NAME, SALARY
FROM EMPLOYEE;

-- 2. EMPLOYEE ���̺��� SAL_LEVEL�� S1�� ����� �̸�, ����, �����, ����ó ��ȸ
SELECT EMP_NAME, SALARY, HIRE_DATE, PHONE
FROM EMPLOYEE;

-- 3. EMPLOYEE ���̺��� �̸�, ����, �Ѽ��ɾ�(���ʽ�����), �Ǽ��ɾ�(�Ѽ��ɾ� - (���� * ���� 3%)) ��ȸ
SELECT EMP_NAME "�̸�"
            , SALARY * 12 "����"
            , (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "�Ѽ��ɾ�"
            , (SALARY + (SALARY * NVL(BONUS, 0))) * 12 - (SALARY * 12 * 0.03) "�Ǽ��ɾ�"
FROM EMPLOYEE;

-- 4. EMPLOYEE ���̺��� �Ǽ��ɾ��� 5õ���� �̻��� ����� �̸�, ����, �Ǽ��ɾ�, ����� ��ȸ
SELECT EMP_NAME "�̸�"
            , SALARY "����"
            , (SALARY + (SALARY * NVL(BONUS, 0)) - (SALARY * 0.03)) * 12 "�Ǽ��ɾ�"
            , HIRE_DATE "�����"
FROM EMPLOYEE;

-- 5. EMPLOYEE ���̺��� ��� ��� ������ �ֹι�ȣ�� �̿��Ͽ� ����, ����, ���� ��ȸ
SELECT EMP_NAME
            , SUBSTR(EMP_NO, 1, 2) "����"
            , SUBSTR(EMP_NO, 3, 2) "����"
            , SUBSTR(EMP_NO, 4, 2) "����"
FROM EMPLOYEE;

-- 6. EMPLOYEE ���̺��� ������, �μ��ڵ�, �������, ����(��) ��ȸ
-- ��, ��������� �ֹι�ȣ���� �����ؼ� 00�� 00�� 00�Ϸ� ��µǰ� �ϸ�
-- ���̴� �ֹι�ȣ���� ��¥ �����ͷ� ���� �� ���
SELECT EMP_NAME "������"
            , DEPT_CODE "�μ��ڵ�"
            , SUBSTR(EMP_NO, 1, 2) || '�� ' || SUBSTR(EMP_NO, 3, 2) || '�� ' || SUBSTR(EMP_NO, 5,2) || '��' "�������"
            -- , TO_DATE(SUBSTR(EMP_NO, 1, 6), 'YYMMDD')
            , EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM TO_DATE(SUBSTR(EMP_NO, 1, 2), 'RR')) AS "����"
FROM EMPLOYEE;

-- 7. EMPLOYEE ���̺��� �ٹ� ����� 20�� �̻��� ���� ���� ��ȸ
SELECT *
FROM EMPLOYEE
WHERE MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 >= 20;

-- 8. EMPLOYEE ���̺��� �μ��ڵ尡 D5, D6, D9�� ����� ��ȸ�ϵ�,
-- D5�� �ѹ���, D6�̸� ��ȹ��, D9�̸� �����η� ó�� (�μ��ڵ� ������������ ����)
SELECT EMP_NAME
            , CASE WHEN DEPT_CODE = 'D5' THEN '�ѹ���'
                        WHEN DEPT_CODE = 'D6' THEN '��ȹ��'
                        WHEN DEPT_CODE = 'D9' THEN '������'
             END AS �μ���
FROM EMPLOYEE
WHERE DEPT_CODE IN ('D5', 'D6', 'D9')
ORDER BY DEPT_CODE;

-- 9. EMPLOYEE ���̺��� ����� Ȧ���� �������� ���� ��� ��ȸ
SELECT *
FROM EMPLOYEE
WHERE MOD(EMP_ID, 2) = 1;

-- 6�� ���� ������ �ߴ� ����
SELECT TO_CHAR(TO_DATE(SUBSTR('990808-123', 1, 6)), 'YY"��" MM"��" DD"��"')
FROM DUAL;
