-- 22/08/26 �ۼ� - 2, 3, 4, 5, 6, 7, 8, 9, 10, 11*, 12*, 13, 14, 15, 16, 17, 18*, 19, 20
-- 22/08/27 �ۼ� - 22, 24, 25*, 27, 28, 29*
-- 22/08/28 �ۼ� - 30, 32, 33, 36, 37*, 38, 39, 40, 41, 43, 44, 45, 46*, 47, 48
-- �� Ǭ ���� : 1, 11, 12, 18, 21, 23, 25, 26, 29, 31, 34, 35, 37, 42, 43

-- [���� 1]
--EMPLOYEE ���̺��� 12�� �����ڿ��� ���� �޼��� ������
--���: OOO�� 12�� OO�� ������ �����մϴ�! 

-- ������ ��� �� ���

---------------------------------------------------------------------------------------------------
--[���� 2]

--EMP ���̺��� �μ��ڵ�� DEPT ���̺��� �����Ͽ� �� �μ��� �ٹ��� ��ġ�� ��ȸ
--�����, �μ��ڵ�, �μ���, �ٹ��� ��ġ ���

-->> ����Ŭ ���� ����
SELECT E.EMP_NAME
            , E.DEPT_CODE
            , D.DEPT_TITLE
            , L.LOCAL_NAME
FROM EMPLOYEE E, DEPARTMENT D, LOCATION L
WHERE E.DEPT_CODE = D.DEPT_ID
     AND D.LOCATION_ID = L.LOCAL_CODE;

-->> ANSI ����
SELECT E.EMP_NAME
            , E.DEPT_CODE
            , D.DEPT_TITLE
            , L.LOCAL_NAME
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
JOIN LOCATION L ON (D.LOCATION_ID = L.LOCAL_CODE);

---------------------------------------------------------------------------------------------------
--[���� 3]

--EMPLOYEE ���̺��� ���� 200���� �̻� 300���� ������ ����� 
--���, �����, �Ի���, �μ��ڵ�, ���� ��ȸ (��, ������ BONUS ���� �� \999,999,999�� ��ȸ)

SELECT EMP_ID "���"
            , EMP_NAME "�����"
            , HIRE_DATE "�Ի���"
            , DEPT_CODE "�μ��ڵ�"
            , TO_CHAR((SALARY + (SALARY * NVL(BONUS, 0))) * 12, 'L999,999,999') "����"
FROM EMPLOYEE
WHERE SALARY BETWEEN 2000000 AND 3000000;

---------------------------------------------------------------------------------------------------
--[���� 4]

--EMPLOYEE ���̺��� ���� PHONE ��ȣ�� 011���� �����ϴ� �����
--�̸�, ���, PHONE, �μ��ڵ带 ��ȸ

SELECT EMP_NAME
            , EMP_ID
            , PHONE
            , DEPT_CODE
FROM EMPLOYEE
WHERE SUBSTR(PHONE, 1, 3) = '011';

---------------------------------------------------------------------------------------------------
--[���� 5]

--80������ ���� ������ �� ���� '��'���� ����� �ֹι�ȣ, ������ ��ȸ
--��, �ֹι�ȣ�� [888888-2******] ���·� ��ȸ �� ���������� �������� ����

SELECT RPAD(SUBSTR(EMP_NO, 1, 8), 14, '*') "�ֹι�ȣ"
            , EMP_NAME "������"
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 1, 1) = '8'
     AND SUBSTR(EMP_NO, 8, 1) IN ('1', '3')
     AND SUBSTR(EMP_NAME, 1, 1) = '��'
ORDER BY EMP_NO ASC;

---------------------------------------------------------------------------------------------------
--[���� 6]

--EMPLOYEE ���̺��� �����ڵ带 �ߺ� ����, "���� ����" ��� ��Ī�� �ο��ϰ�
--"���� ����" ������������ �����ؼ� ��ȸ

SELECT DISTINCT DEPT_CODE "���� ����"
FROM EMPLOYEE
ORDER BY DEPT_CODE ASC;

---------------------------------------------------------------------------------------------------
--[���� 7]

--�μ��� �޿� �հ谡 �μ� �޿� ������ 10%���� ���� �μ��� �μ����, �μ��޿� �հ� ��ȸ
--�Ϲ� ������ �������� ���

SELECT SUM(SALARY) "�޿� �հ�"
FROM EMPLOYEE
GROUP BY DEPT_CODE;

SELECT DEPT_CODE
            , SUM(SALARY) "�μ� �޿� �հ�"
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING SUM(SALARY) > (SELECT SUM(SALARY) "�޿� �հ�"
                                           FROM EMPLOYEE) * 0.1;
                   
-->> ����Ŭ ���� ����                        
SELECT DEPT_TITLE
            , SUM(SALARY) "�μ� �޿� �հ�"
FROM EMPLOYEE, DEPARTMENT
WHERE DEPT_CODE = DEPT_ID
GROUP BY DEPT_CODE, DEPT_TITLE
HAVING SUM(SALARY) > (SELECT SUM(SALARY)
                                            FROM EMPLOYEE) * 0.1;
                                            
-->> ANSI ����
SELECT DEPT_TITLE
            , SUM(SALARY) "�μ� �޿� �հ�"
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
GROUP BY DEPT_CODE, DEPT_TITLE
HAVING SUM(SALARY) > (SELECT SUM(SALARY)
                                            FROM EMPLOYEE) * 0.1;
                                            
---------------------------------------------------------------------------------------------------
--[���� 8]

--EMPLOYEE ���̺��� �μ� �ο��� 3�� �̻��� �μ��� 
--�μ� �ڵ�, ���, �ְ� �޿�, ���� �޿�, �ο� �� ��ȸ 
--(��, �μ��ڵ�� �������� ��ȸ �� \999,999,999�� ��ȸ)

SELECT DEPT_CODE "�μ� �ڵ�"
            , TO_CHAR(ROUND(AVG(SALARY)), 'L999,999,999') "��� �޿�"
            , TO_CHAR(MAX(SALARY), 'L999,999,999') "�ְ� �޿�"
            , TO_CHAR(MIN(SALARY), 'L999,999,999') "���� �޿�"
            , COUNT(DEPT_CODE) "�ο���"
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(DEPT_CODE) >= 3
ORDER BY DEPT_CODE ASC;

---------------------------------------------------------------------------------------------------
--[���� 9]

--EMPLOYEE ���̺��� 
--���� �� '��'�� ���� �����鼭, 
--�޿��� 200���� �̻� 250���� ������ 
--������ �̸��� �޿��� ��ȸ�Ͻÿ�

SELECT EMP_NAME
            , SALARY
FROM EMPLOYEE
WHERE SUBSTR(EMP_NAME, 1, 1) = '��'
     AND SALARY BETWEEN 2000000 AND 2500000;

---------------------------------------------------------------------------------------------------
--[���� 10]

--�ڽ��� �Ŵ������� �޿�(SALARY)�� ���� �޴� ��������
--�̸�(EMP_NAME),�޿�(SALARY),MANAGER_ID,�Ŵ��� �̸�(EMP_NAME)��
--�޿��� ������������ ��ȸ�Ͻÿ�.


-->> ����Ŭ ���� ����
SELECT E.EMP_NAME
            , E.SALARY
            , E.MANAGER_ID
            , M.EMP_NAME
FROM EMPLOYEE E, EMPLOYEE M
WHERE E.MANAGER_ID = M.EMP_ID
     AND E.SALARY > M.SALARY
ORDER BY E.SALARY DESC;

-->> ANSI ����
SELECT E.EMP_NAME
            , E.SALARY
            , E.MANAGER_ID
            , M.EMP_NAME
FROM EMPLOYEE E
JOIN EMPLOYEE M ON (E.MANAGER_ID = M.EMP_ID)
WHERE E.SALARY > M.SALARY
ORDER BY E.SALARY DESC;

---------------------------------------------------------------------------------------------------
--[���� 11] ********

--EMPLOYEE ���̺��� �μ��� �׷��� ���Ͽ�
--�μ��� �޿� �հ�, ���� ���� �޴� �μ���, ���� ���� �޴ºμ�, �ο����� ��ȸ
--��, ��ȸ����� �ο��� ���������Ͽ� ����Ͽ���.

SELECT SUM(SALARY) "�μ��� �޿� �հ�"
FROM EMPLOYEE
GROUP BY DEPT_CODE
ORDER BY COUNT(DEPT_CODE) ASC; -- �ο��� ��������

SELECT MAX(SUM(SALARY)) "�ְ� �޿� �μ�"
            , MIN(SUM(SALARY)) "���� �޿� �μ�"
FROM EMPLOYEE
GROUP BY DEPT_CODE;

---------------------------------------------------------------------------------------------------
--[����12] ********

--EMPLOYEE ���̺��� ���޺�
--�׷��� ���Ͽ� �����ڵ�, �޿����, �޿��հ�, �ο� ���� ��ȸ
--��, ��ȸ ����� �޿���� ���������Ͽ� ���, �ο����� 3���� �ʰ��ϴ� ���޸� ��ȸ

/*
SELECT JOB_CODE "���� �ڵ�"
            , ROUND(AVG(SALARY)) "�޿� ���"
            , SUM(SALARY) "�޿� �հ�"
            , COUNT(JOB_CODE) "�ο� ��"
FROM EMPLOYEE
GROUP BY JOB_CODE
HAVING COUNT(JOB_CODE) > 3
ORDER BY SALARY ASC;
*/

---------------------------------------------------------------------------------------------------
--[����13]

--2001�⿡ �Ի��� ���� ������ �ִ�.
--�ش� ������ ���� �μ�, ���� ���޿� �ش��ϴ� ������� ��ȸ�Ͻÿ�.
--���, �����, ����, �μ�, �Ի���

SELECT EMP_ID
            , EMP_NAME
            , JOB_CODE
            , DEPT_CODE
            , HIRE_DATE
FROM EMPLOYEE
WHERE SUBSTR(TO_CHAR(HIRE_DATE), 1, 2) = '01'
-- WHERE EXTRACT(YEAR FROM HIRE_DATE) = 1
     AND SUBSTR(EMP_NO, 8, 1) IN ('2', '4');

---------------------------------------------------------------------------------------------------
--[����14]

--EMPLOYEE ���̺��� '������'�� ���� �μ����� ���ϴ� ������� 
--�����ȣ, �����, �μ��ڵ� �����ڵ�, �޿� ��ȸ
--�����ڵ� �������� ��ȸ

SELECT EMP_ID
            , EMP_NAME
            , DEPT_CODE
            , JOB_CODE
            , SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
                                      FROM EMPLOYEE
                                      WHERE EMP_NAME = '������')
     AND EMP_NAME != '������'
ORDER BY JOB_CODE DESC;

---------------------------------------------------------------------------------------------------
--[����15] 

--EMPLOYEE ���̺���?�Ի�����?2000��?1��?1��?������?�����?����
-- �����?�̸�, ?�Ի���,? �μ��ڵ�, �޿���?�Ի��ϼ�����?��ȸ�Ͻÿ�
--(������ �ִ� �̸���� �÷����� ���� �ٿ��ּ���)

SELECT EMP_NAME "�����"
            , HIRE_DATE "�Ի���"
            , DEPT_CODE "�μ��ڵ�"
            , SALARY "�޿�"
FROM EMPLOYEE
WHERE HIRE_DATE > '00/01/01'
ORDER BY HIRE_DATE ASC;

---------------------------------------------------------------------------------------------------
--[����16]

--EMPLOYEE ���̺��� �ؿܿ��� �μ�(DEPT_TITLE) �Ҽ��� �������
--�̸�(EMP_NAME), ����(JOB_TITLE), �μ���(DEPT_TITLE), �ٹ�����(NATIONAL_CODE)�� ��ȸ�Ͻÿ�
--��, ����Ŭ ���� �������� �ۼ��ϰ� ��Ī�� �ݵ�� �Է�

SELECT E.EMP_NAME "�̸�"
            , J.JOB_NAME "����"
            , D.DEPT_TITLE "�μ���"
            , L.NATIONAL_CODE "�ٹ�����"
FROM EMPLOYEE E, JOB J, DEPARTMENT D, LOCATION L
WHERE E.DEPT_CODE = D.DEPT_ID
     AND E.JOB_CODE = J.JOB_CODE
     AND D.LOCATION_ID = L.LOCAL_CODE
     AND DEPT_TITLE LIKE '%�ؿܿ���%';

-->> ANSI ����
SELECT E.EMP_NAME "�̸�"
            , J.JOB_NAME "����"
            , D.DEPT_TITLE "�μ���"
            , L.NATIONAL_CODE "�ٹ�����"
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
JOIN DEPARTMENT D ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION L ON (LOCATION_ID = LOCAL_CODE)
WHERE DEPT_TITLE LIKE '%�ؿܿ���%';

---------------------------------------------------------------------------------------------------
--[����17]

--EMPLOYEE ���̺���
--'���¸�'����� �ټ� ����� ��ȸ�Ͻÿ� (����� ������)

SELECT MONTHS_BETWEEN(ENT_DATE, HIRE_DATE) / 12 "�ټ� ���"
FROM EMPLOYEE
WHERE EMP_NAME = '���¸�';

---------------------------------------------------------------------------------------------------
--[����18] **********

--�ڽ��� ���� ������ ��� �޿����� ���� �޴� �����
--�����ȣ,���޸�, �����,�μ���, �޿����� ��ȸ

-->> ����Ŭ ���� ����
SELECT E.EMP_ID
            , J.JOB_NAME
            , E.EMP_NAME
            , D.DEPT_TITLE
            , E.SALARY
FROM EMPLOYEE E, JOB J, DEPARTMENT D
WHERE E.JOB_CODE = J.JOB_CODE
     AND E.DEPT_CODE = D.DEPT_ID
     AND E.SALARY > (SELECT AVG(SALARY)
                                   FROM EMPLOYEE); -- �ڽ��� ���� ������ ��� ǥ��?

-->> ANSI ����

---------------------------------------------------------------------------------------------------
--[����19]

--�μ����� �ٹ��ϴ� ����� ���� 3�� ������ ���, ����� ���� �μ����� �������� ���� ��ȸ

SELECT DEPT_CODE
            , COUNT(DEPT_CODE) "��� ��"
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING COUNT(DEPT_CODE) <= 3
ORDER BY COUNT(DEPT_CODE) ASC;

---------------------------------------------------------------------------------------------------
--[����20]

--EMPLOYEE ���̺���
--���� ���� �޿������ ��ȸ�ϰ� �޿���� ������������ �����Ͻÿ�
--(�޿������ TRUNC �Լ� ����Ͽ� �������� ���ϴ� ���� �Ͻÿ�)

SELECT JOB_CODE "����" 
            , TRUNC(AVG(SALARY)) "�޿� ���"
FROM EMPLOYEE
GROUP BY JOB_CODE
ORDER BY TRUNC(AVG(SALARY)) DESC;

---------------------------------------------------------------------------------------------------
--[����21]

--�ؿܿ���2��(DEPT_CODE: D6)�� ��� �޿����� ���� �ް�, �ؿܿ���2�ο� ������ ������ 
--�����ڰ� ���� ����� ���(EMP_ID), �̸�(EMP_NAME), ����(JOB_NAME), �μ��̸�(DEPT_TITLE), �޿�(SALARY)�� ��ȸ�Ͻÿ�.
--��,FROM ���� �������� ���, JOIN�� ����Ŭ ���� ���, ���� ���� ���

---------------------------------------------------------------------------------------------------
--[����22] 

--EMP���� �����̸����� �׷��� ����� ������ 5000�̻��� �׷� ã��
--JOB�̸���, �޿� �հ踦 ��ȸ�Ͻÿ�

-->> ����Ŭ ���� ����
SELECT J.JOB_NAME, SUM(SALARY)
FROM EMPLOYEE E, JOB J
WHERE E.JOB_CODE = J.JOB_CODE
     AND SALARY > 5000000
GROUP BY J.JOB_NAME;

-->> ANSI ����
SELECT J.JOB_NAME, SUM(SALARY)
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
WHERE SALARY > 5000000
GROUP BY JOB_NAME;

---------------------------------------------------------------------------------------------------
--[����23]

--EMPLOYEE ���̺���
--�Ի��Ϸκ��� �ٹ������ ���� �� ���� ���� 6����
--RANK()�Լ��� �̿��Ͽ� ��ȸ�Ͻÿ�
--���, �����, �μ���, ���޸�, �Ի����� ��ȸ�Ͻÿ�.
---------------------------------------------------------------------------------------------------
--[����24]

--EMPLOYEE ���̺��� 
--�μ��� ���� �޿��� ���� ���� ��������
--�μ���, �ִ�޿��� ��ȸ�Ͻÿ�
--��, �ִ�޿��� 400���� ������ �μ��鸸 ��ȸ�Ͻÿ�
--(�μ����� JOIN Ȱ��)

-->> ����Ŭ ���� ����
SELECT D.DEPT_TITLE, MAX(SALARY)
FROM EMPLOYEE E, DEPARTMENT D
WHERE E.DEPT_CODE = D.DEPT_ID
GROUP BY DEPT_TITLE
HAVING MAX(SALARY) < 4000000;

-->> ANSI ����
SELECT D.DEPT_TITLE, MAX(SALARY)
FROM EMPLOYEE E
JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
GROUP BY DEPT_TITLE
HAVING MAX(SALARY) < 4000000;

---------------------------------------------------------------------------------------------------
--[����25] ***********

--EMPLOYEE ���̺��� �μ��� �ְ� �޿��� Ȯ�� ��, ��� �� �ش� �μ��� �ְ� �޿��� ��ġ�ϴ� �����
--���(EMP_ID), �̸�(EMP_NAME), �μ��̸�(DEPT_TITLE), ����(JOB_NAME), �μ��ڵ�(DEPT_CODE), �޿�(SALARY)�� ��ȸ�Ͻÿ�.
--�޿� ������������ ����, JOIN(ANSI ���� ���), WHERE ������ ���������� �μ��� �ְ� �޿� Ȯ��.

-- �μ��� �ְ� �޿�
SELECT MAX(SALARY)
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- ����Ŭ ���� ��ȸ
SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, J.JOB_NAME, E.DEPT_CODE, E.SALARY
FROM EMPLOYEE E, JOB J, DEPARTMENT D
WHERE E.JOB_CODE = J.JOB_CODE
     AND E.DEPT_CODE = D.DEPT_ID
ORDER BY SALARY DESC;

-- ANSI ���� ��ȸ
SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, J.JOB_NAME, E.DEPT_CODE, E.SALARY
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
JOIN DEPARTMENT D ON (DEPT_CODE = DEPT_ID)
ORDER BY SALARY DESC;

--> ��ġ��?

---------------------------------------------------------------------------------------------------
--[����26]

--'������'�� ���� ��������, ���� ������ ������� 
--�����ȣ, �̸�, �μ��ڵ�, �����ڵ�, ��������(SAL_LEVEL) ��ȸ (���߿� ó��)
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
WHERE JOB_CODE = (SELECT JOB_CODE
                                    FROM EMPLOYEE
                                    WHERE EMP_NAME = '������')
     AND DEPT_CODE = (SELECT DEPT_CODE
                                      FROM EMPLOYEE
                                      WHERE EMP_NAME = '������')
     AND EMP_NAME != '������';

---------------------------------------------------------------------------------------------------
--[���� 29]

--EMPLOYEE���̺���
--�� �μ� �� �Ի����� ���� ������ ����� �� �� ������
--�����ȣ, �����, �μ���ȣ, �Ի����� ��ȸ�ϰ� 
--������ �ִ� ��Ī��� �÷����� �����Ͻÿ�

/* �� ǰ
SELECT EMP_ID "�����ȣ"
            , EMP_NAME "�����"
            , DEPT_CODE "�μ���ȣ"
            , HIRE_DATE "�Ի���"
FROM EMPLOYEE
GROUP BY DEPT_CODE
HAVING HIRE_DATE = (SELECT MIN(HIRE_DATE)
                                      FROM EMPLOYEE
                                      GROUP BY DEPT_CODE);
*/

---------------------------------------------------------------------------------------------------
--[���� 30]

--EMPLOYEE���̺���
--�ٹ������ 20�� �̻� 30�� �̸��� �����
--�����ȣ,�����,�Ի���,������ ���Ͻÿ�
--��,������ ���ʽ��� ������ ������ ���Ѵ�.

SELECT EMP_ID, EMP_NAME, HIRE_DATE, (SALARY + (SALARY * NVL(BONUS, 0))) * 12 "����"
FROM EMPLOYEE
WHERE 20 <= (MONTHS_BETWEEN (SYSDATE, HIRE_DATE) / 12)
    AND (MONTHS_BETWEEN (SYSDATE, HIRE_DATE) / 12) < 30;
    
---------------------------------------------------------------------------------------------------
--[���� 31]

--EMPLOYEE ���̺��� �ٹ�����(NATIONAL_CODE)�� 'KO'�� �������
--�̸�(EMP_NAME), ��������, �޿�(SALARY), �ٹ�����(NATIONAL_CODE)�� ��ȸ�Ͻÿ�
--��, ���������� DENSE_RANK() ���, ANSI(JOIN) ���� ���, �������� ����(��������) 

-->> ����Ŭ ���� ����

-->> ANSI ����

---------------------------------------------------------------------------------------------------
--[����32] 
 
--EMPLOYEE ���̺��� 'J1' ������ �ְ� ���ް� 'J7' ������ ���� ���� ��ȸ
--��, ������ JOB_CODE�� '���' ó��

SELECT MAX(SALARY)
FROM EMPLOYEE
WHERE JOB_CODE = 'J1';

SELECT MIN(SALARY)
FROM EMPLOYEE
WHERE JOB_CODE = 'J7';

SELECT CASE WHEN JOB_CODE = 'J1' THEN TO_CHAR(MAX(SALARY))
                        WHEN JOB_CODE = 'J7' THEN TO_CHAR(MIN(SALARY))
                        ELSE '���'
              END
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
WHERE SALARY < (SELECT AVG(SALARY)
                                FROM EMPLOYEE)
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[���� 34]

--�޿� ����� 3���ȿ� ��� ������ ã�� �����ڵ�, ���޸�, �޿����� ��ȸ�� ��
-- ROWNUM�� ���κ並 �ݿ��Ͽ� ��ȸ�Ͻÿ�
---------------------------------------------------------------------------------------------------
--[���� 35]

--EMPLOYEE ���̺���
--�޿��� 1,380,000���� ������ ���� �����ڵ�, ���� �޿����(SAL_LEVEL)�� �ش��ϴ� 
--����� �����, �����ڵ�, �޿������ ��ȸ�Ͻÿ�
--��, �޿��� 1,380,000���� �ش������� �����Ͽ� ��ȸ�Ͻÿ�
--(���߿� �������� ���)
---------------------------------------------------------------------------------------------------
--[���� 36]

--4/4�б� ������ ���� �󿩱��� �����ϰ� �Ǿ� ���� ���޿� ���� �󿩱��� �����Ϸ� �Ѵ�.
--�޿�(SALARY)�� 400���� �ʰ� �� �޿��� 30%, 200���� �̻� 400���� �̸��̸� �޿��� 50%, 
--100�����̻� 200���� �̸��̸� �޿��� 70%�� �����Ѵ�.
--��, CASE�� ����ϰ� �޿� ������ �������� ����

SELECT EMP_NAME, SALARY "���� ����"
            , CASE WHEN SALARY >= 4000000 THEN SALARY*1.3
                        WHEN SALARY BETWEEN 2000000 AND 4000000 THEN SALARY * 1.5
                        WHEN SALARY BETWEEN 1000000 AND 2000000 THEN SALARY * 1.7
             END "�󿩱� ����"
FROM EMPLOYEE
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[���� 37]

--EMPLOYEE ���̺��� ����� 200, 201, 202�� ������� ã��
--200���� ����� �ֹι�ȣ ���ڸ��� '621212'��
--201���� ����� �ֹι�ȣ ���ڸ��� '631111'��
--202���� ����� �ֹι�ȣ ���ڸ��� '861010'���� �����ϴ� UPDATE ������ �����Ͻÿ�.

-- ã�� �ͱ��� �Ϸ�
SELECT EMP_NAME, EMP_NO
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
           , CASE WHEN MANAGER_ID IS NOT NULL THEN '�Ϲ� ���'
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
     AND JOB_CODE != 'J5'
     AND JOB_CODE != 'J7';

---------------------------------------------------------------------------------------------------
--[����40]

--EMPLOYEE���̺���
--�޿��� ���� ���� �����
--�����ȣ,�����,�޿�,�μ���ȣ�� ���Ͻÿ�
--�޿� ������ �������� �Ͻÿ�

SELECT EMP_ID, EMP_NAME, SALARY, DEPT_CODE
FROM EMPLOYEE
WHERE SALARY = (SELECT MAX(SALARY)
                                FROM EMPLOYEE)
ORDER BY SALARY DESC;

---------------------------------------------------------------------------------------------------
--[����41]

--�Ϻ��� �ٹ��ϸ鼭 �ؿܿ���1���� ����� ��ȸ�Ͽ�
--���,�����,�μ���,������(LOCAL_NAME),�޿��� ���Ͻÿ�
--��, ��� ������ �������� �Ͻÿ�

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, LOCAL_NAME, SALARY
FROM EMPLOYEE, DEPARTMENT, LOCATION L, NATIONAL N
WHERE DEPT_CODE = DEPT_ID
     AND LOCATION_ID = LOCAL_CODE
     AND L.NATIONAL_CODE = N.NATIONAL_CODE
     AND DEPT_TITLE = '�ؿܿ���1��'
     AND NATIONAL_NAME = '�Ϻ�'
ORDER BY EMP_ID ASC;

-->> ANSI ����
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, LOCAL_NAME, SALARY
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
JOIN LOCATION ON (LOCATION_ID = LOCAL_CODE)
JOIN NATIONAL USING (NATIONAL_CODE)
WHERE DEPT_TITLE = '�ؿܿ���1��'
    AND NATIONAL_NAME = '�Ϻ�'
ORDER BY EMP_ID ASC;

---------------------------------------------------------------------------------------------------
--[����42]

--�μ����� ���� ������ ���� �޴� ������� �˻��Ͻÿ�
--��� �÷��� ��ȸ�ϳ�, �μ��� ������������ �����Ͻÿ�

---------------------------------------------------------------------------------------------------
--[���� 43]

--EMPLOYEE ���̺���
--�����޿��� �޴� ������ ���� �μ��� �������� 
--�̸�, �μ��ڵ�, �޿��� ��ȸ�Ͻÿ� (�����޿� ������ ����)
--(�������� �������� ���)

-- ���� �޿� ����
SELECT MIN(SALARY)
FROM EMPLOYEE;

-- ���� �޿� ������ ���� �μ�
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
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
     AND EMP_ID != '208'
ORDER BY EMP_ID ASC;

---------------------------------------------------------------------------------------------------
--[���� 45]

--���� �ð��� ���� �ð����κ��� �� �ð� ���� �ð��� ����϶�.
--�ð� ���� ����)2020-12-29 21:15:23

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')   
            , TO_CHAR(SYSDATE+1/24, 'YYYY-MM-DD HH24:MI:SS') 
FROM DUAL;

---------------------------------------------------------------------------------------------------
--[���� 46] *********

--1995�� 5�� 1�� ~ 2000�� 5�� 1�� ���̿� �Ի��� ������� 
--��� ������ �μ���ȣ��(��������)���� �˻��Ͻÿ�.

SELECT *
FROM EMPLOYEE
-- WHERE HIRE_DATE BETWEEN TO_DATE('1995-05-01', 'RRRR-MM-DD') AND TO_DATE('2000-05-01', 'RRRR-MM-DD')
ORDER BY DEPT_CODE ASC;

---------------------------------------------------------------------------------------------------
--[���� 47]

--20�� �̻� �ٹ��ڿ��� ������� ��û�� ���������Ѵ�.
--������ 3�� ����(���ʽ� ����) + 5000������ ���α�
--20���̻� �ٹ����� �����ȣ, �����, �μ���, ���޸�, �Ի���, �ٹ����, ������(���α�����)�� ��ȸ�ϰ�
--�Ի��� �������� ������������ �����Ͽ� �����Ͻʽÿ� 

SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, HIRE_DATE
            , ROUND(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) "�ٹ����"
            , 3 * (SALARY + (SALARY * NVL(BONUS, 0))) * 12 + 50000000 "������"
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

SELECT E.EMP_NAME "�̸�"
            , J.JOB_NAME "����"
            , HIRE_DATE "�Ի���"
            , CASE WHEN MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 >= 30 THEN '����'
                        WHEN MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 BETWEEN 20 AND 29 THEN '�ذ�'
                        WHEN MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 BETWEEN 10 AND 19 THEN '������'
                        WHEN MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12 BETWEEN 5 AND 9 THEN '���ι�'
                        ELSE '�Ƹ���'
              END "��������"
FROM EMPLOYEE E
JOIN JOB J USING (JOB_CODE)
ORDER BY HIRE_DATE ASC;

---------------------------------------------------------------------------------------------------







