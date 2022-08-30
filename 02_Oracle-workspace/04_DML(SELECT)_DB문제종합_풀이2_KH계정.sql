-- 22/08/26 �ۼ� - 2, 3, 4, 5, 6, 7, 8, 9, 10, 11*, 12*, 13, 14, 15, 16, 17, 18*, 19, 20
-- 22/08/27 �ۼ� - 22, 24, 25*, 27, 28, 29*
-- 22/08/28 �ۼ� - 1, 11, 12, 18, 21, 23, 25, 29, 30, 31, 32, 33, 36, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48
-- �� Ǭ ���� :  26(���߿�), 34(ROWNUM), 35(���߿�), 37(UPDATE)

-- [���� 1]
--EMPLOYEE ���̺��� 12�� �����ڿ��� ���� �޼��� ������
--���: OOO�� 12�� OO�� ������ �����մϴ�! 

SELECT EMP_NAME || '�� 12�� ' || SUBSTR(EMP_NO, 5, 2) || '�� ������ �����մϴ�!'
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 3, 2) = '12';

---------------------------------------------------------------------------------------------------
--[���� 2]

--EMP ���̺��� �μ��ڵ�� DEPT ���̺��� �����Ͽ� �� �μ��� �ٹ��� ��ġ�� ��ȸ
--�����, �μ��ڵ�, �μ���, �ٹ��� ��ġ ���

-->> ����Ŭ ����
SELECT EMP_NAME, DEPT_CODE, DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE E, DEPARTMENT D, LOCATION L
WHERE E.DEPT_CODE = D.DEPT_ID
     AND D.LOCATION_ID = L.LOCAL_CODE;

-->> ANSI ����
SELECT EMP_NAME, DEPT_CODE, DEPT_TITLE, LOCAL_NAME
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE);

---------------------------------------------------------------------------------------------------
--[���� 3]

--EMPLOYEE ���̺��� ���� 200���� �̻� 300���� ������ ����� 
--���, �����, �Ի���, �μ��ڵ�, ���� ��ȸ (��, ������ BONUS ���� �� \999,999,999�� ��ȸ)

SELECT EMP_ID, EMP_NAME, HIRE_DATE, DEPT_CODE
            , (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "����"
FROM EMPLOYEE
WHERE SALARY BETWEEN 2000000 AND 3000000;

---------------------------------------------------------------------------------------------------
--[���� 4]

--EMPLOYEE ���̺��� ���� PHONE ��ȣ�� 011���� �����ϴ� �����
--�̸�, ���, PHONE, �μ��ڵ带 ��ȸ

SELECT EMP_NAME, EMP_ID, PHONE, DEPT_CODE
FROM EMPLOYEE
WHERE SUBSTR(PHONE, 1, 3) = '011';

---------------------------------------------------------------------------------------------------
--[���� 5]

--80������ ���� ������ �� ���� '��'���� ����� �ֹι�ȣ, ������ ��ȸ
--��, �ֹι�ȣ�� [888888-2******] ���·� ��ȸ �� ���������� �������� ����

SELECT EMP_NAME "������"
            , RPAD(SUBSTR(EMP_NO, 1, 8), 14, '*') "�ֹι�ȣ"
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 1, 1) = '8'
     AND SUBSTR(EMP_NAME, 1, 1) = '��'
     AND SUBSTR(EMP_NO, 8, 1) IN ('1', '3');

---------------------------------------------------------------------------------------------------
--[���� 6]

--EMPLOYEE ���̺��� �����ڵ带 �ߺ� ����, "���� ����" ��� ��Ī�� �ο��ϰ�
--"���� ����" ������������ �����ؼ� ��ȸ

SELECT DISTINCT JOB_CODE "��������"
FROM EMPLOYEE
ORDER BY JOB_CODE ASC;

---------------------------------------------------------------------------------------------------
--[���� 7]

--�μ��� �޿� �հ谡 �μ� �޿� ������ 10%���� ���� �μ��� �μ����, �μ��޿� �հ� ��ȸ
--�Ϲ� ������ �������� ���

SELECT SUM(SALARY) * 0.1
FROM EMPLOYEE;

SELECT DEPT_TITLE, SUM(SALARY)
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
GROUP BY DEPT_TITLE
HAVING SUM(SALARY) > (SELECT SUM(SALARY)
                                            FROM EMPLOYEE) * 0.1;

---------------------------------------------------------------------------------------------------
--[���� 8]

--EMPLOYEE ���̺��� �μ� �ο��� 3�� �̻��� �μ��� 
--�μ� �ڵ�, ���, �ְ� �޿�, ���� �޿�, �ο� �� ��ȸ 
--(��, �μ��ڵ�� �������� ��ȸ �� \999,999,999�� ��ȸ)

SELECT DEPT_CODE
            , TO_CHAR(ROUND(AVG(SALARY)), 'L999,999,999') "��� �޿�"
            , MAX(SALARY) "�ְ� �޿�"
            , MIN(SALARY) "���� �޿�"
            , COUNT(EMP_ID) "�ο� ��"
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(EMP_ID) >= 3
ORDER BY DEPT_CODE ASC;

---------------------------------------------------------------------------------------------------
--[���� 9]

--EMPLOYEE ���̺��� 
--���� �� '��'�� ���� �����鼭, 
--�޿��� 200���� �̻� 250���� ������ 
--������ �̸��� �޿��� ��ȸ�Ͻÿ�

SELECT EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SUBSTR(EMP_NAME, 1, 1) = '��'
     AND SALARY BETWEEN 2000000 AND 2500000;

---------------------------------------------------------------------------------------------------
--[���� 10]

--�ڽ��� �Ŵ������� �޿�(SALARY)�� ���� �޴� ��������
--�̸�(EMP_NAME),�޿�(SALARY),MANAGER_ID,�Ŵ��� �̸�(EMP_NAME)��
--�޿��� ������������ ��ȸ�Ͻÿ�.

SELECT E.EMP_NAME "�����", E.SALARY "�޿�", E.MANAGER_ID "��� ���", M.EMP_NAME "�����"
FROM EMPLOYEE E, EMPLOYEE M
WHERE E.MANAGER_ID = M.EMP_ID
     AND E.SALARY > M.SALARY
ORDER BY E.SALARY DESC;

---------------------------------------------------------------------------------------------------
--[���� 11] 

--EMPLOYEE ���̺��� �μ��� �׷��� ���Ͽ�
--�μ��� �޿� �հ�, ���� ���� �޴� �μ���, ���� ���� �޴ºμ�, �ο����� ��ȸ
--��, ��ȸ����� �ο��� ���������Ͽ� ����Ͽ���.

SELECT SUM(SALARY), MAX(SALARY), MIN(SALARY), COUNT(EMP_ID)
FROM EMPLOYEE
GROUP BY DEPT_CODE
ORDER BY COUNT(EMP_ID) ASC;

---------------------------------------------------------------------------------------------------
--[����12]

--EMPLOYEE ���̺��� ���޺�
--�׷��� ���Ͽ� �����ڵ�, �޿����, �޿��հ�, �ο� ���� ��ȸ
--��, ��ȸ ����� �޿���� ���������Ͽ� ���, �ο����� 3���� �ʰ��ϴ� ���޸� ��ȸ

SELECT JOB_CODE, ROUND(AVG(SALARY)) "�޿� ���", SUM(SALARY) "�޿� �հ�", COUNT(EMP_ID)
FROM EMPLOYEE
GROUP BY JOB_CODE
HAVING COUNT(EMP_ID) > 3
ORDER BY ROUND(AVG(SALARY)) ASC;

---------------------------------------------------------------------------------------------------
--[����13]

--2001�⿡ �Ի��� ���� ������ �ִ�.
--�ش� ������ ���� �μ�, ���� ���޿� �ش��ϴ� ������� ��ȸ�Ͻÿ�.
--���, �����, ����, �μ�, �Ի���

SELECT EMP_ID, EMP_NAME, JOB_CODE, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE (JOB_CODE, DEPT_CODE) = (SELECT JOB_CODE, DEPT_CODE
                                                          FROM EMPLOYEE
                                                          WHERE EXTRACT(YEAR FROM HIRE_DATE) = '2001'
                                                               AND SUBSTR(EMP_NO, 8, 1) IN ('2', '4'));

---------------------------------------------------------------------------------------------------
--[����14]

--EMPLOYEE ���̺��� '������'�� ���� �μ����� ���ϴ� ������� 
--�����ȣ, �����, �μ��ڵ� �����ڵ�, �޿� ��ȸ
--�����ڵ� �������� ��ȸ

SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
                                      FROM EMPLOYEE
                                      WHERE EMP_NAME = '������')
    AND EMP_NAME != '������';

---------------------------------------------------------------------------------------------------
--[����15] 

--EMPLOYEE ���̺���?�Ի�����?2000��?1��?1��?������?�����?����
-- �����?�̸�, ?�Ի���,? �μ��ڵ�, �޿���?�Ի��ϼ�����?��ȸ�Ͻÿ�
--(������ �ִ� �̸���� �÷����� ���� �ٿ��ּ���)

SELECT EMP_NAME "�����", HIRE_DATE "�Ի���", DEPT_CODE "�μ��ڵ�", SALARY "�޿�"
FROM EMPLOYEE
WHERE HIRE_DATE < '20100101'
ORDER BY HIRE_DATE ASC;

---------------------------------------------------------------------------------------------------
--[����16]

--EMPLOYEE ���̺��� �ؿܿ��� �μ�(DEPT_TITLE) �Ҽ��� �������
--�̸�(EMP_NAME), ����(JOB_TITLE), �μ���(DEPT_TITLE), �ٹ�����(NATIONAL_CODE)�� ��ȸ�Ͻÿ�
--��, ����Ŭ ���� �������� �ۼ��ϰ� ��Ī�� �ݵ�� �Է�

-->> ����Ŭ ����
SELECT EMP_NAME "�̸�"
            , JOB_NAME "����"
            , DEPT_TITLE "�μ���"
            , NATIONAL_CODE "�ٹ�����"
FROM EMPLOYEE E, JOB J, DEPARTMENT D, LOCATION L
WHERE DEPT_CODE = DEPT_ID
     AND E.JOB_CODE = J.JOB_CODE
     AND D.LOCATION_ID = L.LOCAL_CODE
     AND DEPT_TITLE LIKE '%�ؿܿ���%';
     
-->> ANSI ����
SELECT EMP_NAME "�̸�"
            , JOB_NAME "����"
            , DEPT_TITLE "�μ���"
            , NATIONAL_CODE "�ٹ�����"
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
JOIN DEPARTMENT D ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION L ON (LOCATION_ID = LOCAL_CODE)
WHERE DEPT_TITLE LIKE '%�ؿܿ���%';


---------------------------------------------------------------------------------------------------
--[����17]

--EMPLOYEE ���̺���
--'���¸�'����� �ټ� ����� ��ȸ�Ͻÿ� (����� ������)

SELECT MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 "�ټӳ��"
FROM EMPLOYEE
WHERE EMP_NAME = '���¸�';

---------------------------------------------------------------------------------------------------
--[����18] **********

--�ڽ��� ���� ������ ��� �޿����� ���� �޴� �����
--�����ȣ,���޸�, �����,�μ���, �޿����� ��ȸ

/*
SELECT EMP_ID, JOB_NAME, EMP_NAME, DEPT_TITLE, SALARY, JOB_CODE
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY > ANY (SELECT AVG(SALARY) FROM EMPLOYEE GROUP BY JOB_CODE);
*/
/*
4850000	J2
2017500	J7
3600000	J3
2624373.33333333333333333333333333333333	J6
2820000	J5
8000000	J1
2330000	J4
*/
---------------------------------------------------------------------------------------------------
--[����19]

--�μ����� �ٹ��ϴ� ����� ���� 3�� ������ ���, ����� ���� �μ����� �������� ���� ��ȸ

SELECT DEPT_CODE, COUNT(EMP_ID)
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(EMP_ID) <= 3
ORDER BY SUM(EMP_ID) ASC;

---------------------------------------------------------------------------------------------------
--[����20]

--EMPLOYEE ���̺���
--���� ���� �޿������ ��ȸ�ϰ� �޿���� ������������ �����Ͻÿ�
--(�޿������ TRUNC �Լ� ����Ͽ� �������� ���ϴ� ���� �Ͻÿ�)

SELECT TRUNC(AVG(SALARY)) "�޿� ���"
FROM EMPLOYEE
GROUP BY JOB_CODE
ORDER BY TRUNC(AVG(SALARY)) DESC;

---------------------------------------------------------------------------------------------------
--[����21] ************

--�ؿܿ���2��(DEPT_CODE: D6)�� ��� �޿����� ���� �ް�, �ؿܿ���2�ο� ������ ������ 
--�����ڰ� ���� ����� ���(EMP_ID), �̸�(EMP_NAME), ����(JOB_NAME),
--                              �μ��̸�(DEPT_TITLE), �޿�(SALARY)�� ��ȸ�Ͻÿ�.
--��,FROM ���� �������� ���, JOIN�� ����Ŭ ���� ���, ���� ���� ���

SELECT AVG(SALARY)
FROM EMPLOYEE
WHERE DEPT_CODE = 'D6';

SELECT EMP_ID, EMP_NAME, JOB_NAME, DEPT_TITLE, SALARY
FROM EMPLOYEE E, JOB J, DEPARTMENT D
WHERE E.JOB_CODE = J.JOB_CODE
     AND E.DEPT_CODE = D.DEPT_ID
     AND SALARY > (SELECT AVG(SALARY)
                                FROM EMPLOYEE
                                WHERE DEPT_CODE = 'D6')
     AND DEPT_TITLE != '�ؿܿ���2��'
     AND MANAGER_ID IS NULL;

---------------------------------------------------------------------------------------------------
--[����22] 

--EMP���� �����̸����� �׷��� ����� ������ 5000�̻��� �׷� ã��
--JOB�̸���, �޿� �հ踦 ��ȸ�Ͻÿ�

SELECT JOB_NAME, SUM(SALARY)
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND SALARY > 5000000
GROUP BY JOB_NAME;

---------------------------------------------------------------------------------------------------
--[����23]

--EMPLOYEE ���̺���
--�Ի��Ϸκ��� �ٹ������ ���� �� ���� ���� 6����
--RANK()�Լ��� �̿��Ͽ� ��ȸ�Ͻÿ�
--���, �����, �μ���, ���޸�, �Ի����� ��ȸ�Ͻÿ�.

--�ٹ������ ���� �� ���� ���� 6 ��
SELECT *
FROM (SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, HIRE_DATE
                        , RANK() OVER (ORDER BY MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 DESC) "����"
            FROM EMPLOYEE)
WHERE ROWNUM <= 6;

---------------------------------------------------------------------------------------------------
--[����24]

--EMPLOYEE ���̺��� 
--�μ��� ���� �޿��� ���� ���� ��������
--�μ���, �ִ�޿��� ��ȸ�Ͻÿ�
--��, �ִ�޿��� 400���� ������ �μ��鸸 ��ȸ�Ͻÿ�
--(�μ����� JOIN Ȱ��)

SELECT DEPT_TITLE, MAX(SALARY)
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
GROUP BY DEPT_TITLE
HAVING MAX(SALARY) <= 4000000;

---------------------------------------------------------------------------------------------------
--[����25] ***********

--EMPLOYEE ���̺��� �μ��� �ְ� �޿��� Ȯ�� ��, ��� �� �ش� �μ��� �ְ� �޿��� ��ġ�ϴ� �����
--���(EMP_ID), �̸�(EMP_NAME), �μ��̸�(DEPT_TITLE), ����(JOB_NAME)
-- , �μ��ڵ�(DEPT_CODE), �޿�(SALARY)�� ��ȸ�Ͻÿ�.
--�޿� ������������ ����, JOIN(ANSI ���� ���), WHERE ������ ���������� �μ��� �ְ� �޿� Ȯ��.

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE SALARY IN (SELECT MAX(SALARY)
                                FROM EMPLOYEE
                                GROUP BY DEPT_CODE)
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[����26]

--'������'�� ���� ��������, ���� ������ ������� 
--�����ȣ, �̸�, �μ��ڵ�, �����ڵ�, ��������(SAL_LEVEL) ��ȸ (���߿� ó��)

SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SAL_LEVEL
FROM EMPLOYEE
WHERE (SAL_LEVEL, JOB_CODE) IN (SELECT SAL_LEVEL, JOB_CODE
                                                         FROM EMPLOYEE
                                                         WHERE EMP_NAME = '������');

---------------------------------------------------------------------------------------------------
--[����27]

-- ����� �� ������ 5000000 �̻��̸� 'HIGH', 3000000 �̻��̸� 'MEDIUM', 
-- 2000000 �̻��̸� 'LOW' �� �������� 'OTL'�� ����ϰ�  
--�����, �μ��ڵ�, ������ ��ȸ�Ͻÿ�.
--��, ������ ���� ������ �����Ͻÿ�.

SELECT EMP_NAME, DEPT_CODE
            , CASE WHEN SALARY >= 5000000 THEN 'HIGH'
                        WHEN SALARY >= 3000000 THEN 'MEDIUM'
                        WHEN SALARY >= 2000000 THEN 'LOW'
                        ELSE 'OTL'
            END
FROM EMPLOYEE
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[����28]

--�������� ���� ����, ���� �μ��� �ٹ��ϴ� 
--�������� ������ ��ȸ�Ͻÿ� 

SELECT *
FROM EMPLOYEE
WHERE (JOB_CODE, DEPT_CODE) IN (SELECT JOB_CODE, DEPT_CODE
                                                            FROM EMPLOYEE
                                                            WHERE EMP_NAME = '������');

---------------------------------------------------------------------------------------------------
--[���� 29]

--EMPLOYEE���̺���
--�� �μ� �� �Ի����� ���� ������ ����� �� �� ������
--�����ȣ, �����, �μ���ȣ, �Ի����� ��ȸ�ϰ� 
--������ �ִ� ��Ī��� �÷����� �����Ͻÿ�

SELECT MIN(HIRE_DATE)
FROM EMPLOYEE;

SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE HIRE_DATE IN (SELECT MIN(HIRE_DATE)
                                      FROM EMPLOYEE
                                      GROUP BY DEPT_CODE);

---------------------------------------------------------------------------------------------------
--[���� 30]

--EMPLOYEE���̺���
--�ٹ������ 20�� �̻� 30�� �̸��� �����
--�����ȣ,�����,�Ի���,������ ���Ͻÿ�
--��,������ ���ʽ��� ������ ������ ���Ѵ�.

SELECT EMP_ID, EMP_NAME, HIRE_DATE, (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "����"
FROM EMPLOYEE
WHERE (MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) BETWEEN 20 AND 30;

---------------------------------------------------------------------------------------------------
--[���� 31]

--EMPLOYEE ���̺��� �ٹ�����(NATIONAL_CODE)�� 'KO'�� �������
--�̸�(EMP_NAME), ��������, �޿�(SALARY), �ٹ�����(NATIONAL_CODE)�� ��ȸ�Ͻÿ�
--��, ���������� DENSE_RANK() ���, ANSI(JOIN) ���� ���, �������� ����(��������) 

SELECT EMP_NAME, DENSE_RANK() OVER (ORDER BY SALARY DESC) "��������", SALARY, NATIONAL_CODE
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
WHERE NATIONAL_CODE = 'KO';

---------------------------------------------------------------------------------------------------
--[����32] 
 
--EMPLOYEE ���̺��� 'J1' ������ �ְ� ���ް� 'J7' ������ ���� ���� ��ȸ
--��, ������ JOB_CODE�� '���' ó��

SELECT CASE WHEN JOB_CODE = 'J1' THEN TO_CHAR((SELECT MAX(SALARY) FROM EMPLOYEE WHERE JOB_CODE = 'J1'))
                        WHEN JOB_CODE = 'J7' THEN TO_CHAR((SELECT MIN(SALARY) FROM EMPLOYEE WHERE JOB_CODE = 'J7'))
                        ELSE '���'
             END "���"
FROM EMPLOYEE
GROUP BY JOB_CODE;             
---------------------------------------------------------------------------------------------------
--[���� 33]

--EMPLOYEE ���̺���
--�� ������ ��ձ޿����� �޿��� ���� �������� �����, �޿��� ��ȸ�ϰ�
--�޿� ������������ �����Ͻÿ�
--(������ �������� ���)

SELECT EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SALARY < (SELECT AVG(SALARY) FROM EMPLOYEE)
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[���� 34]

--�޿� ����� 3���ȿ� ��� ������ ã�� �����ڵ�, ���޸�, �޿����� ��ȸ�� ��
-- ROWNUM�� ���κ並 �ݿ��Ͽ� ��ȸ�Ͻÿ�

SELECT ROWNUM, JOB_CODE, JOB_NAME
FROM (SELECT JOB_CODE, AVG(SALARY)
            FROM EMPLOYEE
            GROUP BY JOB_CODE
            ORDER BY AVG(SALARY) DESC) E
JOIN JOB USING (JOB_CODE)
WHERE ROWNUM <= 3;

---------------------------------------------------------------------------------------------------
--[���� 35]

--EMPLOYEE ���̺���
--�޿��� 1,380,000���� ������ ���� �����ڵ�, ���� �޿����(SAL_LEVEL)�� �ش��ϴ� 
--����� �����, �����ڵ�, �޿������ ��ȸ�Ͻÿ�
--��, �޿��� 1,380,000���� �ش������� �����Ͽ� ��ȸ�Ͻÿ�
--(���߿� �������� ���)

SELECT EMP_NAME, JOB_CODE, SAL_LEVEL
FROM EMPLOYEE
WHERE (JOB_CODE, SAL_LEVEL) = (SELECT JOB_CODE, SAL_LEVEL FROM EMPLOYEE WHERE SALARY = 1380000)
     AND SALARY != 1380000;

---------------------------------------------------------------------------------------------------
--[���� 36]

--4/4�б� ������ ���� �󿩱��� �����ϰ� �Ǿ� ���� ���޿� ���� �󿩱��� �����Ϸ� �Ѵ�.
--�޿�(SALARY)�� 400���� �ʰ� �� �޿��� 30%, 200���� �̻� 400���� �̸��̸� �޿��� 50%, 
--100�����̻� 200���� �̸��̸� �޿��� 70%�� �����Ѵ�.
--��, CASE�� ����ϰ� �޿� ������ �������� ����

SELECT EMP_NAME
            , CASE WHEN SALARY > 4000000 THEN SALARY * 0.3
                        WHEN SALARY BETWEEN 2000000 AND 4000000 THEN SALARY * 0.5
                        WHEN SALARY BETWEEN 1000000 AND 2000000 THEN SALARY * 0.7
             END "�󿩱�"
FROM EMPLOYEE
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[���� 37]

--EMPLOYEE ���̺��� ����� 200, 201, 202�� ������� ã��
--200���� ����� �ֹι�ȣ ���ڸ��� '621212'��
--201���� ����� �ֹι�ȣ ���ڸ��� '631111'��
--202���� ����� �ֹι�ȣ ���ڸ��� '861010'���� �����ϴ� UPDATE ������ �����Ͻÿ�.

SELECT EMP_NAME, EMP_NO
            , CASE WHEN EMP_ID = '200' THEN REPLACE(EMP_NO, SUBSTR(EMP_NO, 1, 6), '621212')
                        WHEN EMP_ID = '201' THEN REPLACE(EMP_NO, SUBSTR(EMP_NO, 1, 6), '631111')
                        WHEN EMP_ID = '202' THEN REPLACE(EMP_NO, SUBSTR(EMP_NO, 1, 6), '861010')
            ELSE EMP_NO
            END "UPDATE"
FROM EMPLOYEE
WHERE EMP_ID IN ('200', '201', '202');

---------------------------------------------------------------------------------------------------
--[���� 38]

--EMPLOYE ���̺��� ENT_YN�� Y�� '�����', N�̸� '�ٹ���'�� ǥ���ϰ�
--������ ����� ������ '�Ϲݻ��', ������ ����� ������ '������'�� ǥ���ϰ�
--������ ���, �����, �μ��ڵ�, �����ڵ�, �ٹ� ��Ȳ, ������ ���θ� ��ȸ�Ͻÿ�.

SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE
            , CASE WHEN ENT_YN = 'Y' THEN '�����'
                        WHEN ENT_YN = 'N' THEN '�ٹ���'
              END "�ٹ� ��Ȳ"
            , CASE WHEN MANAGER_ID IS NOT NULL THEN '�Ϲݻ��'
                        WHEN MANAGER_ID IS NULL THEN '������'
              END "������ ����"
FROM EMPLOYEE;

---------------------------------------------------------------------------------------------------
--[����39]

--EMPLOYEE ���̺��� �μ��ڵ� D5�� �����ڵ尡 J5�� �ƴϰ� J7�� �ƴ� ����� 
--�����, �μ��ڵ�, �����ڵ�, SAL_LEVEL, �ٹ����� ��ȸ

SELECT EMP_NAME, DEPT_CODE, JOB_CODE, SAL_LEVEL, ENT_YN
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5'
     AND JOB_CODE NOT IN ('J5', 'J7');

---------------------------------------------------------------------------------------------------
--[����40]

--EMPLOYEE���̺��� �޿��� ���� ���� �����
--�����ȣ,�����,�޿�,�μ���ȣ�� ���Ͻÿ�

SELECT EMP_ID, EMP_NAME, SALARY, DEPT_CODE
FROM EMPLOYEE
WHERE SALARY = (SELECT MAX(SALARY) FROM EMPLOYEE);

---------------------------------------------------------------------------------------------------
--[����41]

--�Ϻ��� �ٹ��ϸ鼭 �ؿܿ���1���� ����� ��ȸ�Ͽ�
--���,�����,�μ���,������(LOCAL_NAME),�޿��� ���Ͻÿ�
--��, ��� ������ �������� �Ͻÿ�

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, LOCAL_NAME, SALARY
FROM EMPLOYEE E, DEPARTMENT D, LOCATION L
WHERE DEPT_CODE = DEPT_ID
     AND LOCATION_ID = LOCAL_CODE
     AND NATIONAL_CODE = 'JP'
     AND DEPT_TITLE LIKE '�ؿܿ���1%'
ORDER BY EMP_ID ASC;

---------------------------------------------------------------------------------------------------
--[����42]

--�μ����� ���� ������ ���� �޴� ������� �˻��Ͻÿ�
--��� �÷��� ��ȸ�ϳ�, �μ��� ������������ �����Ͻÿ�

SELECT EMP_NAME
FROM EMPLOYEE
WHERE SALARY IN (SELECT MIN(SALARY)
                                FROM EMPLOYEE
                                GROUP BY DEPT_CODE)
ORDER BY DEPT_CODE DESC;

---------------------------------------------------------------------------------------------------
--[���� 43]

--EMPLOYEE ���̺���
--�����޿��� �޴� ������ ���� �μ��� �������� 
--�̸�, �μ��ڵ�, �޿��� ��ȸ�Ͻÿ� (�����޿� ������ ����)
--(�������� �������� ���)

SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
                                      FROM EMPLOYEE
                                      WHERE SALARY = (SELECT MIN(SALARY)
                                                                      FROM EMPLOYEE))
     AND EMP_NAME != (SELECT EMP_NAME
                                     FROM EMPLOYEE
                                     WHERE SALARY = (SELECT MIN(SALARY)
                                                                      FROM EMPLOYEE));

---------------------------------------------------------------------------------------------------
--[����44]

--EMPLOYEE ���̺��� �����ȣ�� 208�� ����� ������ �����ڵ带 ���� ����� 
--�����ȣ, �����, �����ڵ�, �޿� ��ȸ (�����ȣ�� ��������)

SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE JOB_CODE = (SELECT JOB_CODE
                                    FROM EMPLOYEE
                                    WHERE EMP_ID = '208')
ORDER BY EMP_ID ASC;

---------------------------------------------------------------------------------------------------
--[���� 45]

--���� �ð��� ���� �ð����κ��� �� �ð� ���� �ð��� ����϶�.
--�ð� ���� ����)2020-12-29 21:15:23

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') "���� �ð�"
            , TO_CHAR(SYSDATE+1/24, 'YYYY-MM-DD HH24:MI:SS') "�ѽð� ��"
FROM DUAL;

---------------------------------------------------------------------------------------------------
--[���� 46]

--1995�� 5�� 1�� ~ 2000�� 5�� 1�� ���̿� �Ի��� ������� 
--��� ������ �μ���ȣ��(��������)���� �˻��Ͻÿ�.

SELECT *
FROM EMPLOYEE
WHERE HIRE_DATE BETWEEN '19950501' AND '20000501'
ORDER BY DEPT_CODE ASC;

---------------------------------------------------------------------------------------------------
--[���� 47]

--20�� �̻� �ٹ��ڿ��� ������� ��û�� ���������Ѵ�.
--������ 3�� ����(���ʽ� ����) + 5000������ ���α�
--20���̻� �ٹ����� �����ȣ, �����, �μ���, ���޸�, �Ի���, �ٹ����, ������(���α�����)�� ��ȸ�ϰ�
--�Ի��� �������� ������������ �����Ͽ� �����Ͻʽÿ� 

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, HIRE_DATE
            , MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 "�ٹ����"
            , (SALARY + (SALARY * NVL(BONUS, 0))) * 36 + 50000000 "������"
FROM EMPLOYEE E, DEPARTMENT, JOB J
WHERE DEPT_CODE = DEPT_ID
     AND E.JOB_CODE = J.JOB_CODE
     AND MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 >= 20
ORDER BY HIRE_DATE DESC;

---------------------------------------------------------------------------------------------------
--[���� 48]

--�ٹ� ����� ���� '���� ����'�� �з��ϱ� ���� CASE���� �̿��Ͽ� �̸�(EMP_NAME),
--����(JOB_NAME), �Ի糯¥(HIRE_DATE), ��������(CASE��)�� ��ȸ�Ͻÿ�.
--�ٹ� ����� 30�� �̻��̸� '����', 20 ~ 29�� '�ذ�', 10 ~ 19�� '������', 
-- 5 ~ 9�� '���ι�', �������� '�Ƹ���' 
--JOB ���̺� JOIN(ANSI)�������� �ۼ�, �Ի糯¥������ �������� ����

SELECT EMP_NAME, JOB_NAME, HIRE_DATE
            , ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) "�ٹ����"
            , CASE WHEN ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) >= 30 THEN '����'
                        WHEN ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) BETWEEN 20 AND 29 THEN '�ذ�'
                        WHEN ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) BETWEEN 10 AND 19 THEN '������'
                        WHEN ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) BETWEEN 5 AND 9 THEN '���ι�'
                        ELSE '�Ƹ���'
             END "���� ����"
FROM EMPLOYEE
JOIN JOB USING (JOB_CODE)
ORDER BY HIRE_DATE ASC

---------------------------------------------------------------------------------------------------







