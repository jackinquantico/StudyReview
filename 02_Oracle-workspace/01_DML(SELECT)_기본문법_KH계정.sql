/*
    < SELECT >
    DQL �Ǵ� DML�� �з��� �� �ִ� ��ɾ�ν�
    �����͸� ��ȸ�ϰų� �˻��� �� ����ϴ� ��ɾ�
    
    - Result Set : SELECT ������ ���� ��ȸ�� �����͵��� �����
                   ��, ��ȸ�� ����� ����

    [ ǥ���� ]
    SELECT ��ȸ�ϰ����ϴ��÷���, ...   // SELECT��
    FROM ���̺��;                   // FROM��
*/

-- EMPLOYEE ���̺��� ��ü ������� ���, �̸�, �޿� �÷� ��ȸ
SELECT EMP_ID, EMP_NAME, SALARY
FROM EMPLOYEE;

-- ��ɾ�, Ű����, ���̺��, �÷��� ���� ��ҹ��� ���о��� �ۼ� ����
-- �ڹ�ó�� ��Ÿǥ����� �Ұ��ϱ� ������ �����_ ����ؼ� �ܾ� ����
-- �ҹ��ڷ� �ᵵ ������. ��, �빮�ڸ� ����

-- EMPLOYEE ���̺��� ��ü ������� ��� �÷��� �� ��ȸ
SELECT EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE, DEPT_CODE, JOB_CODE, SAL_LEVEL
        , SALARY, BONUS, MANAGER_ID, HIRE_DATE, ENT_DATE, ENT_YN
FROM EMPLOYEE;

-- �ش� ���̺��� ��ü �÷��� ��ȸ�ϰ� �ʹٸ� SELECT ���� SELECT * �� ǥ��
SELECT *
FROM EMPLOYEE;

-- JOB ���̺��� ��� �÷� ��ȸ
SELECT *
FROM JOB;

-- JOB ���̺��� ���޸� �÷� ��ȸ
SELECT JOB_NAME
FROM JOB;

----- �ǽ� ���� -----
-- 1. DEPARTMENT ���̺��� ��� �÷� ��ȸ
SELECT *
FROM DEPARTMENT;

SELECT DEPT_ID, DEPT_TITLE, LOCATION_ID
FROM DEPARTMENT;

-- 2. EMPLOYEE ���̺��� ������, �̸���, ��ȭ��ȣ, �Ի��� �÷��� ��ȸ
SELECT EMP_NAME, EMAIL, PHONE, HIRE_DATE
FROM EMPLOYEE;

-- 3. EMPLOYEE ���̺��� �Ի���, ������, �޿� �÷��� ��ȸ
SELECT HIRE_DATE, EMP_NAME, SALARY
FROM EMPLOYEE;

---------------------------------------------------------------------------------------
/*
    < �÷����� ���� ������� >
    ��ȸ�ϰ��� �ϴ� �÷����� �����ϴ� SELECT ���� �������(+,-,/,*) �� ����ؼ�
    ���� ����� ��ȸ�� �� �ִ�.
*/

-- EMPLOYEE ���̺�κ��� ������, ����, ����(== ���� * 12) ��ȸ
SELECT EMP_NAME, SALARY, SALARY * 12
FROM EMPLOYEE;

-- EMPLOYEE ���̺�κ��� ������, ����, ���ʽ�, ���ʽ��� ���Ե� ����(== (���� + (���ʽ� * ����) ) * 12) ��ȸ
SELECT EMP_NAME, SALARY, BONUS, (SALARY + (BONUS * SALARY)) * 12
FROM EMPLOYEE;
--> ��������ϴ� ������ NULL ���� �����Ѵٸ� �� ������� ����� NULL �� ���´�.

-- DATE Ÿ�Գ����� ���� ���� (DATE -> ��/��/��/��/��/��)
-- EMPLOYEE ���̺�κ��� ������, �Ի���, �ٹ��ϼ�(== ���ó�¥ - �Ի���) ��ȸ
-- ORACLE DB���� ���� ��¥ : SYSDATE
SELECT EMP_NAME, HIRE_DATE, SYSDATE - HIRE_DATE
FROM EMPLOYEE;
-- ������� �� �� ������ ��µ����� �Ҽ����� �پ ��µ�.
-- ������ == ��¥ ���, �Ҽ��� == �ú��� ���
-- DATE Ÿ�� �ȿ� ���ԵǾ��ִ� ��/��/�ʿ� ���� ������� �����ϱ� ����

---------------------------------------------------------------------------------------

/*
    < �÷��� ��Ī �����ϱ� >
    RESULT SET �� �������� �÷� ���� ���� �����̰ų� �˾ƺ��� ���� ��쿡
    ��Ī�� �����ؼ� ������ �� �ִ�.
    
    [ ǥ���� ]
    �÷��� AS ��Ī
    �÷��� AS "��Ī"
    �÷��� ��Ī
    �÷��� "��Ī"
    
    ��, AS �� ���̵� �� ���̵�
    ��Ī ���ο� Ư�����ڳ� ���Ⱑ ���Ե� ��쿡�� �� ��Ī�� �ݵ�� "" �� ��� ǥ��
*/

-- EMPLOYEE ���̺�κ��� �̸�, �޿�(��), ���ʽ�, �� �ҵ��� ��ȸ
SELECT EMP_NAME AS �̸�, SALARY AS "�޿�(��)", BONUS ���ʽ�, (SALARY + (SALARY * BONUS)) * 12 "�� �ҵ�"
FROM EMPLOYEE;

---------------------------------------------------------------------------------------
/*
    < ���ͷ� > == ��
    ���Ƿ� ������ ���ͷ��� SELECT ���� ����ϸ�
    ���� �� ���̺� �����ϴ� ������ó�� ��ȸ ����
    
    SELECT ���� ������ ���ͷ� ���� ��ȸ ����� RESULT SET �� ��� �࿡ �ݺ������� ��µ�
    ���ÿ� �÷������ε� ���õ� -> ��Ī�� ���̱⵵ ����
*/

-- EMPLOYEE ���̺�κ��� ���, �����, �޿�, ���� ��ȸ�ϱ�
SELECT EMP_ID, EMP_NAME, SALARY, '��' ����
FROM EMPLOYEE;
--> ����Ŭ���� ���ڿ� Ÿ���� ���� ' '(Ȭ����ǥ)�� ���μ� ǥ��

---------------------------------------------------------------------------------------
/*
    < DISTINCT >
    ��ȸ�ϰ��� �ϴ� �÷��� �տ� ����ϸ�
    �ش� �÷� ���� �ߺ��� ������ �� �� ������ ������ִ� ȿ��
    
    ��, �� SELECT ���� DISTINCT ������ �ѹ��� ��� �����ϴ�
    
    [ ǥ���� ]
    SELECT DISTINCT �÷���
*/

-- �μ��ڵ� ��ȸ
SELECT DEPT_ID
FROM DEPARTMENT;

-- EMPLOYEE ���̺�κ��� �μ� �ڵ� ��ȸ
SELECT DISTINCT DEPT_CODE
FROM EMPLOYEE;

-- EMPLOYEE ���̺�κ��� ���� �ڵ� ��ȸ
SELECT DISTINCT JOB_CODE
FROM EMPLOYEE;

-- �÷��� ���� ���� ���
SELECT DISTINCT DEPT_CODE, JOB_CODE
FROM EMPLOYEE;
--> DEPT_CODE, JOB_CODE ���� �ϳ��� ���� �� �ߺ� �Ǻ��� �Ѵ�.

---------------------------------------------------------------------------------------
/*
    < WHERE �� >
    ��ȸ�ϰ��� �ϴ� ���̺� Ư�� ������ �����ؼ� 
    �� ���ǿ� �����ϴ� �����͸��� ��ȸ�ϰ��� �� �� ����ϴ� ����
    
    [ ǥ���� ]
    SELECT ��ȸ�ϰ����ϴ��÷���, ...
    FROM ���̺��
    WHERE ���ǽ�;
    
    ������� : FROM �� -> WHERE �� -> SELECT ��
    
    - ���ǽĿ� �پ��� �����ڵ� ��� ����
    
    < �� ������ >
    <, >, <=, >= : ��Һ� ������
    = : ��ġ�� ������
    !=, ^=, <> : ����ġ�� ������
*/

-- EMPLOYEE ���̺�κ��� �޿��� 400���� �̻��� ����鸸 ��ȸ (��� �÷��� ���ؼ� ��ȸ)
SELECT *
FROM EMPLOYEE
WHERE SALARY >= 4000000;

-- EMPLOYEE ���̺�κ��� �μ��ڵ尡 D9�� ������� �����, �μ��ڵ�, �޿� ��ȸ
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9';

-- EMPLOYEE ���̺�κ��� �μ��ڵ尡 D9�� �ƴ� ������� �����, �μ��ڵ�, �޿� ��ȸ
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
-- WHERE DEPT_CODE != 'D9';
-- WHERE DEPT_CODE ^= 'D9';
WHERE DEPT_CODE <> 'D9';
--> NULL ���� ���� �����ϰ� ��ȸ��

-- EMPLOYEE ���̺�κ��� ���� �������� ������� ���, �̸�, �Ի��� ��ȸ
-- ENT_YN �÷��� �ǹ��ϴ� �� : N = ����, Y = ���
SELECT EMP_ID, EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE ENT_YN = 'N';

------ �ǽ� ���� ------
-- 1. EMPLOYEE ���̺�κ��� �޿��� 300 ���� �̻��� ������� �̸�, �޿�, �Ի��� ��ȸ
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY >= 3000000;

-- 2. EMPLOYEE ���̺�κ��� �����ڵ尡 J2 �� ������� �̸�, �޿�, ���ʽ� ��ȸ
SELECT EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE JOB_CODE = 'J2';

-- 3. EMPLOYEE ���̺�κ��� ������ 5000���� �̻��� ������� �̸�, �޿�, ����, �Ի��� ��ȸ
SELECT EMP_NAME AS �̸�, SALARY AS �޿�, SALARY*12 AS ����, HIRE_DATE AS �Ի���
FROM EMPLOYEE
WHERE (SALARY * 12) >= 50000000;
-- SELECT������ �ο��� ��Ī�� WHERE������ ����� �� ����
-- ( ) �켱���� ������ - ������

---------------------------------------------------------------------------------------
/*
    < �� ������ >
    ���� ���� ������ ���� �� ���
    
    AND : ~�̸鼭, �׸��� (�ڹٿ����� &&)
    OR : ~�̰ų�, �Ǵ� (�ڹٿ����� ||)
*/

-- EMPLOYEE ���̺�κ��� �μ��ڵ尡 D9�̸鼭 �޿��� 500���� �̻��� ������� �̸�, �μ��ڵ�, �޿� ��ȸ
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE (DEPT_CODE = 'D9') AND (SALARY >= 5000000);

-- EMPLOYEE ���̺�κ��� �μ��ڵ尡 D6 �̰ų� �޿��� 300���� �̻��� ������� �̸�, �μ��ڵ�, �޿� ��ȸ
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE (DEPT_CODE = 'D6') OR (SALARY >= 3000000);

-- EMPLOYEE ���̺�κ��� �޿��� 350���� �̻��̰� 600���� ������ ������� �̸�, ���, �޿�, ���� �ڵ� ��ȸ
SELECT EMP_NAME, EMP_ID, SALARY, JOB_CODE
FROM EMPLOYEE
WHERE (3500000 <= SALARY) AND (SALARY <= 6000000);

---------------------------------------------------------------------------------------
/*
    < BETWEEN AND >
    ���� ���Ѽ��� ���Ѽ��� �����Ͽ� ������ ���� ������ ������ �� ���
    
    [ ǥ���� ]
    �񱳴���÷��� BETWEEN ���Ѽ� AND ���Ѽ�
*/

-- EMPLOYEE ���̺�κ��� �޿��� 350���� �̻��̰� 600���� ������ ������� �̸�, ���, �޿�, ���� �ڵ� ��ȸ
SELECT EMP_NAME, EMP_ID, SALARY, JOB_CODE
FROM EMPLOYEE
WHERE SALARY BETWEEN 3500000 AND 6000000;

-- EMPLOYEE ���̺�κ��� �޿��� 350���� �̸��̰� 600���� �ʰ��� ������� �̸�, ���, �޿�, �����ڵ� ��ȸ
SELECT EMP_NAME, EMP_ID, SALARY, JOB_CODE
FROM EMPLOYEE
WHERE NOT SALARY BETWEEN 3500000 AND 6000000;
-- WHERE SALARY NOT BETWEEN 3500000 AND 6000000;
--> ����Ŭ������ NOT�� �ڹ��� �������������� ! �� ������ �ǹ��̴�.

-- EMPLOYEE ���̺�κ��� �Ի����� '90/01/01' ~ '03/01/01' �� ������� ��� �÷��� ��ȸ
SELECT *
FROM EMPLOYEE
WHERE HIRE_DATE BETWEEN '90/01/01' AND '03/01/01';
-- WHERE '90/01/01' <= HIRE_DATE AND HIRE_DATE <= '03/01/01'; 
--> BETWEEN AND �����ڴ� DATE ���Ŀ����� ��� �����ϴ�.

-- EMPLOYEE ���̺�κ��� �Ի����� '90/01/01' ~ '03/01/01'�� �ƴ� ������� ��� �÷��� ��ȸ
SELECT *
FROM EMPLOYEE
WHERE NOT HIRE_DATE BETWEEN '90/01/01' AND '03/01/01';
-- WHERE HIRE_DATE NOT BETWEEN '90-01/01' AND '03/01/01';

---------------------------------------------------------------------------------------
/*
    < LIKE 'Ư�� ����' >
    ���ϰ��� �ϴ� �÷����� ������ Ư�������� ������ ��� ��ȸ
    
    [ ǥ���� ]
    �񱳴���÷��� LIKE 'Ư������'
    
    - Ư�� ���Ͽ� ���ϵ�ī���� '%', '_' �� ����ؼ� ������ �� ����
    - '%' : 0 ���� �̻�
            �񱳴���÷��� LIKE '����%' => �ش� �÷� �� �߿� '����'�� ���۵Ǵ� ���� ��ȸ
            �񱳴���÷��� LIKE '%����' => �ش� �÷� �� �߿� '����'�� ������ ���� ��ȸ
            �񱳴���÷��� LIKE '%����%' => �ش� �÷� �� �߿� '����'�� ���Ե� ���� ��ȸ (�˻� ��� ���� �� ���� ����)
    - '_' : 1 ����
           �񱳴���÷��� LIKE '_����' => �ش� �÷��� �߿� '����' �տ� ������ 1���ڰ� ���� ��� ��ȸ
           �񱳴���÷��� LIKE '����_' => �ش� �÷��� �߿� '����' �ڿ� ������ 1���ڰ� ���� ��� ��ȸ
           ex) �񱳴���÷��� LIKE '_ _����' => �ش� �÷��� �߿� '����' �տ� ������ 2 ���ڰ� ���� ��� ��ȸ
*/

-- EMPLOYEE ���̺�κ��� ���� ������ ������� �̸�, �޿�, �Ի��� ��ȸ
SELECT EMP_NAME, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '��%';

-- EMPLOYEE ���̺�κ��� �̸� �߿� '��'�� ���Ե� ������� �̸�, �ֹι�ȣ, �μ��ڵ� ��ȸ
SELECT EMP_NAME, EMP_NO, DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%��%';

-- EMPLOYEE ���̺�κ��� ��ȭ��ȣ 4��° �ڸ��� 9�� �����ϴ� ������� ���, �����, ��ȭ��ȣ, �̸��� ��ȸ
SELECT EMP_ID, EMP_NAME, PHONE, EMAIL
FROM EMPLOYEE
WHERE PHONE LIKE '___9%';
-- '___9' => �� 4�����ε� 9�� ������ ��

-- EMPLOYEE ���̺�κ��� �̸� ��� ���ڰ� '��'�� ������� ��� �÷��� ��ȸ
SELECT *
FROM EMPLOYEE
WHERE EMP_NAME LIKE '_��_';

-- �� �̿��� ���
SELECT *
FROM EMPLOYEE
WHERE NOT EMP_NAME LIKE '_��_';
-- WHERE EMP_NAME NOT LIKE '_��_';

----- �ǽ� ���� -----
-- 1. �̸��� '��'���� ������ ������� �̸�, �Ի��� ��ȸ
SELECT EMP_NAME, HIRE_DATE
FROM EMPLOYEE
WHERE EMP_NAME LIKE '%��';

-- 2. ��ȭ��ȣ ó�� 3���ڰ� 010�� �ƴ� ������� �̸�, ��ȭ��ȣ ��ȸ
SELECT EMP_NAME, PHONE
FROM EMPLOYEE
WHERE NOT PHONE LIKE '010%';

-- 3. DEPARTMENT ���̺�κ��� �ؿܿ����� ���õ� �μ����� ��� �÷� ��ȸ
SELECT *
FROM DEPARTMENT
WHERE DEPT_TITLE LIKE '%�ؿܿ���%';

---------------------------------------------------------------------------------------
/*
    < IS NULL ������ >
    �ش� �÷����� NULL ���� �Ǻ����ִ� ������
    
    [ ǥ���� ]
    �񱳴���÷��� IS NULL : �ش� �÷����� NULL�� ��ġ�� ��쿡�� ��ȸ
    �񱳴���÷��� IS NOT NULL : �ش� �÷����� NULL�� �ƴ� ��쿡�� ��ȸ
*/

-- ���ʽ��� ���� �ʴ� ����� ��ȸ (BONUS �÷����� NULL �� ���)�� ���, �̸�, �޿�, ���ʽ� ��ȸ
SELECT EMP_ID, EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE BONUS IS NULL;

-- ���ʽ��� �޴� ����� ��ȸ
SELECT EMP_ID, EMP_NAME, SALARY, BONUS
FROM EMPLOYEE
WHERE BONUS IS NOT NULL;

-- EMPLOYEE ���̺�κ��� ����� ���� ������� �����, ����� ���, �μ��ڵ� ��ȸ
SELECT EMP_NAME, MANAGER_ID, DEPT_CODE
FROM EMPLOYEE
WHERE MANAGER_ID IS NULL;

-- ����� ���� �μ���ġ�� ���� ���� ������� ��� �÷� ��ȸ
SELECT *
FROM EMPLOYEE
WHERE (MANAGER_ID IS NULL) AND (DEPT_CODE IS NULL);

-- �μ���ġ�� ���� �ʾ����� ���ʽ��� �޴� ����� �����, ���ʽ�, �μ��ڵ� ��ȸ
SELECT EMP_NAME, BONUS, DEPT_CODE
FROM EMPLOYEE
WHERE (DEPT_CODE IS NULL) AND (BONUS IS NOT NULL);

---------------------------------------------------------------------------------------
/*
    < IN >
    �񱳴�� �÷����� ���� ������ ��ϵ� �߿��� �ϳ��� ��ġ�ϴ� ���� ������ ��ȸ
    
    [ ǥ���� ]
    �񱳴���÷��� IN (��, ��, ��, ...)
*/

-- �μ��ڵ尡 D6�̰ų� D8�̰ų� D5�� ������� �̸�, �μ��ڵ�, �޿� ��ȸ
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
-- WHERE (DEPT_CODE = 'D6') OR (DEPT_CODE = 'D8') OR (DEPT_CODE = 'D5');
WHERE DEPT_CODE IN ('D6', 'D8', 'D5');
--> ����� ������ ������ OR �������� �پ����� ��� IN �����ڸ� �̿��Ͽ� �����ϰ� ǥ�� ����

-- �� �̿��� �����
SELECT EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
-- WHERE NOT DEPT_CODE IN ('D6', 'D8', 'D5');
WHERE DEPT_CODE NOT IN ('D6', 'D8', 'D5');

---------------------------------------------------------------------------------------
/*
    < ���� ������ >
    ���� �÷������� ��ġ �ϳ��� �÷��� ��ó�� ��������ִ� ������
    �ַ� SELECT ������ ���̸�, �÷��� ���ͷ� ���� ��������ִ� �͵� ����
*/

SELECT EMP_ID || EMP_NAME || SALARY AS "�����"
FROM EMPLOYEE;

-- XX�� XXX���� ������ XXXXXXX���Դϴ�.
SELECT EMP_ID || '�� ' || EMP_NAME || '���� ������ ' || SALARY || '�� �Դϴ�.' AS "���� ����"
FROM EMPLOYEE;

---------------------------------------------------------------------------------------
/*
    < ������ �켱 ���� >
    0. �켱���� ������ : ( )
    1. ��� ������ : +, -, /, *
    2. ���� ������ : ||
    3. �� ������
    4. IS NULL , LIKE , IN
    5. BETWEEN AND
    6. NOT (������������)
    7. AND (��������)
    8. OR (��������)
*/

-- AND, OR ������ ����
-- DEPT_CODE �� D9�̰ų� D1�̰�, SALARY�� 300���� ������ ����� ��ü �÷� ��ȸ
SELECT *
FROM EMPLOYEE
-- WHERE DEPT_CODE = 'D9' OR DEPT_CODE = 'D1' AND SALARY <= 3000000;
--                                       (                         1                             ) �� ���� �����
WHERE (DEPT_CODE = 'D9' OR DEPT_CODE = 'D1') AND SALARY <= 3000000;

---------------------------------------------------------------------------------------
/*
    < ORDER BY �� >
    SELECT �� ���� �������� �����ϴ� ����
    + ���� ���� ���� ���� �������� �����
    �� ��ȸ�� ���빰�� ��� �������� ���� �������ִ� ����
    
    [ ǥ���� ]
    SELECT ��ȸ�ϰ����ϴ��÷�1, 2, ...
    FROM ���̺��
    WHERE ���ǽ� (���� ����)
    ORDER BY [���ı������λ�����ϴ��÷��� or  ��Ī or �÷�����] [ASC(��������)/DESC(��������)] [NULLS FIRST/NULLS LAST(��������)]
    
    - ASC : �������� ���� = ���� �Ϳ��� ū �� ������ ���� (������ �⺻��)
    - DESC : �������� ���� = ū �Ϳ��� ���� �� ������ ����
    
    - NULLS FIRST : �����ϰ��� �ϴ� �÷����� NULL�� ���� ��� �ش� NULL�� �տ� ��ġ�� �÷��� ���� (DESC �� ��� �⺻��)
    - NULLS LAST : �����ϰ��� �ϴ� �÷����� NULL�� ���� ��� �ش� NULL�� �÷��� ���� �Ŀ� ��ġ (ASC �� ��� �⺻��)
*/

SELECT *
FROM EMPLOYEE
ORDER BY EMP_NAME ASC;

SELECT *
FROM EMPLOYEE
-- ORDER BY BONUS; -- ASC �Ǵ� DESC ���� �� �⺻���� ASC
-- ORDER BY BONUS ASC; -- ASC�� �⺻���� NULLS LAST
-- ORDER BY BONUS ASC NULLS FIRST; -- NULLS FIRST ���� ����
-- ORDER BY BONUS DESC; -- DESC�� �⺻���� NULSS FIRST
-- ORDER BY BONUS DESC NULLS LAST; -- NULSS LAST ���뵵 ����
ORDER BY BONUS DESC, SALARY ASC;
--> ù��°�� ������ ���ı����� �÷����� ��ġ�� ��� �ι�° ���� ������ ������ �ٽ� ����

-- ��ü ������� �̸�, �޿�, ���� ��ȸ�ϵ�, �� ������ ���� ������� ��ȸ
SELECT EMP_NAME, SALARY, SALARY * 12 AS "����"
FROM EMPLOYEE
-- ORDER BY "����" DESC; -- ��Ī ��� ����
-- ORDER BY SALARY*12 DESC;
ORDER BY 3 DESC; -- �÷� ���� ��� �����ϴ�. ��, 1���� ����.

