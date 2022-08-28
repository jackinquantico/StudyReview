/*
    < �Լ� FUNCTION >
    �ڹٷ� ������ �޼ҵ�� ���� ����
    �Ű������� ���޵� ������ �о ���������� ����� ����� ����
    
    - ������ �Լ� : N���� ���� �о N���� ����� ���� (�� �ึ�� �Լ� ���� �� �� �࿡ ���� ����� ��� ��ȯ)
    - �׷� �Լ� : N���� ���� �о 1���� ����� ���� (��� �࿡ ���� �ϳ��� �׷����� ��� �Լ� ���� �� ����� �ϳ��� ��ȯ)

    - ������ �� : �׻� ������ �Լ��� �׷� �Լ��� �Բ� ����� �� ����
                    ��� ���� ������ ���ʿ� �ٸ��� ������ ����
*/

----------------------------------------------------------------------------------------------------
/*
    < ������ �Լ� >
    
    <���ڿ��� ���õ� �Լ� >
    LENGTH / LENGTHB
    
    - LENGTH(STR) : �ش� ���޵� ���ڿ��� ���� �� ��ȯ
    - LENGTHB(STR) : �ش� ���޵� ���ڿ��� ����Ʈ �� ��ȯ
    
    ��� ���� NUMBER Ÿ������ ��ȯ
    STR : '���ڿ�' ���ͷ� / ���ڿ� Ÿ���� �÷���
    
    ����Ŭ������ ������ ������
    ����, ����, Ư������ : '!', '~', 'A', 'a', '1', ... => �� ���ڴ� 1BYTE�� ���
    �ѱ� : '��', '��', '��', '��', ... => �� ���ڴ� 3 BYTE�� ���
*/

SELECT LENGTH('����Ŭ!'), LENGTHB('����Ŭ!')
FROM DUAL; -- DUAL ���̺� == �������̺� == DUMMY ���̺�
                 -- : ��� �����̳� ���� �÷� ���� ���� �� �ѹ��� ����ϰ� ���� �� FROM ���� �ۼ��ϴ� ���̺��

SELECT EMAIL
        , LENGTH(EMAIL)
        , LENGTHB(EMAIL)
        , EMP_NAME
        , LENGTH(EMP_NAME)
        , LENGTHB(EMP_NAME)
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    INSTR
    
    - INSTR(STR) : ���ڿ��κ��� Ư�� ������ ��ġ���� ��ȯ
    
    INSTR(STR, 'Ư������', ã����ġ�ǽ��۰�, ����)
    
    ������� NUMBER Ÿ������ ��ȯ   
    ã����ġ�ǽ��۰�, ������ ���� ����
    
    ã����ġ�� ���۰�
    1 : �տ������� ã�ڴ�.
    -1 : �ڿ������� ã�ڴ�.
*/

SELECT INSTR('AABAACAABBAA', 'B')
FROM DUAL; -- 3 : ã���� �ϴ� ��ġ, ���° �ش� ���ڸ� ã�� �������� ���� ������ ������� �ʾ�������
                 --     �⺻������ �տ������� ù��° ������ ��ġ�� ã���ְ� ����

SELECT INSTR('AABAACAABBAA', 'B', 1)
FROM DUAL; -- 3 : �տ������� ù��°�� ��ġ�ϴ� B�� ��ġ���� �˷��ְڴ�. (1���� ����)

SELECT INSTR('AABAACAABBAA', 'B', -1)
FROM DUAL; -- 10 : �ڿ������� ù��°�� ��ġ�ϴ� B�� ��ġ���� �˷��ְڴ�. (�տ������� ����)

SELECT INSTR('AABAACAABBAA', 'B', 1, 2)
FROM DUAL; -- 9 : �տ������� �ι�°�� ��ġ�ϴ� B�� ��ġ���� �˷��ְڴ�.

SELECT INSTR('AABAACAABBAA', 'B', -1, 2)
FROM DUAL; -- 9 : �ڿ������� �ι�°�� ��ġ�ϴ� B�� ��ġ���� �˷��ְڴ�. (�տ������� ����)

SELECT INSTR('AABAACAABBAA', 'E', 1, 1)
FROM DUAL; -- 0 : ���ԵǾ����� ���� ���� 0 ��ȯ

-- EMAIL ���� @ �� ��ġ�� ã�ƺ���
SELECT EMAIL, INSTR(EMAIL, '@') AS "@�� ��ġ"
FROM EMPLOYEE;

SELECT EMAIL, INSTR(EMAIL, '@') -1 AS "�̸��� ���̵� ����"
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    < SUBSTR >
    
    - SUBSTR(STR, POSITION, LENGTH) : �ش� ���ڿ��κ��� Ư�� ���ڿ��� �����ؼ� ��ȯ
                                                  (�ڹٷ� ġ�� ���ڿ�.substring() �޼ҵ�� ����)
                                                  
    ������� CHARACTER Ÿ������ ��ȯ (���ڿ� ����)
    LENGTH �� ���� ����
    
    - STR : '���ڿ�' ���ͷ� / ���ڿ� Ÿ���� �÷���
    - POSITION :  ���ڿ��� ������ ������ġ�� (������ ���� ����), POSITION��° ��ġ���� ����
    - LENGTH : ������ ������ ���� (���� �� ������ ����)
    
    
*/

SELECT SUBSTR('SHOWMETHEMONEY', 7)
FROM DUAL; -- THEMONEY : 7��° ���ں��� ������ ���� (������ġ�� 1����)

SELECT SUBSTR('SHOWMETHEMONEY', 5, 2)
FROM DUAL; -- ME

SELECT SUBSTR('SHOWMETHEMONEY', 1, 6)
FROM DUAL; -- SHOWME

SELECT SUBSTR('SHOWMETHEMONEY', -8, 3)
FROM DUAL; -- THE : �ڿ������� 8��° ���ڿ��� 3�� ����
--> ���� ��ġ�� ������ ��� �ڿ������� N��° ��ġ�κ��� ���� ����

-- �ֹι�ȣ���� ���� �κ��� �����ؼ� ����=1, ����=2 üũ
SELECT EMP_NAME, SUBSTR(EMP_NO, 8, 1) AS ����
FROM EMPLOYEE;

-- ���� ������� �����, �޿� ��ȸ
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
-- WHERE SUBSTR(EMP_NO, 8, 1) = 1 OR SUBSTR(EMP_NO, 8, 1) = 3;
WHERE SUBSTR(EMP_NO, 8, 1) IN ('1', '3');

-- ���� ����鸸 ��ȸ
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('2', '4')
-- WHERE SUBSTR(EMP_NO, 8, 1) NOT IN ('1', '3')
ORDER BY SALARY;

-- �̸��Ϸκ��� ID �κи� ����
SELECT EMP_NAME, EMAIL, SUBSTR(EMAIL, 1, INSTR(EMAIL, '@')-1) AS ID
FROM EMPLOYEE;
--> �Լ��� ��ø�Ͽ� �� �� ����. = �ݹ��Լ�

----------------------------------------------------------------------------------------------------
/*
    LPAD / RPAD
    
    - LPAD/RPAD(STR, �������ڱ���(����Ʈ), �����̰����ϴ� ����)
    : ������ ���ڿ��� ������ ���ڿ��� ���ٿ��� ���� N���̸�ŭ�� ���ڿ� ��ȯ
    L : ��, R : ������
    
    ������� CHARACTER Ÿ������ ��ȯ
    �����̰��� �ϴ� ���ڴ� ���� ����

    - STR : '���ڿ�' ���ͷ� / ���ڿ� Ÿ�� �÷���
*/

SELECT LPAD(EMAIL, 16) -- �����̰��� �ϴ� ���� ���� ��, �⺻���� ����
FROM EMPLOYEE;

SELECT RPAD(EMAIL, 20, '#')
FROM EMPLOYEE;

-- �ֹε�Ϲ�ȣ 850505-2222222 �� �� => �� 6 �ڸ��� ������� �ʰ� ����ŷó��
SELECT RPAD('850805-2', 14, '*')
FROM DUAL;

-- ��� ������ �ֹι�ȣ ��ȸ
-- 1. SUBSTR�� �ֹι�ȣ �� 8��° �ڸ����� ����
-- 2. RPAD�� ��ø�Ͽ� �ֹι�ȣ �� 6�ڸ��� * ���̱�
SELECT EMP_NAME, RPAD(SUBSTR(EMP_NO, 1, 8), 14, '*') AS �ֹε�Ϲ�ȣ
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    LTRIM / RTRIM
    
    - LTRIM/RTRIM(STR, ���Ž�Ű�����ϴ¹���)
    : ���ڿ��� ���� �Ǵ� �����ʿ��� �����ϰ��� �ϴ� ���ڸ� ã�� ������ ������ ���ڿ� ��ȯ
    
    ������� CHARACTER Ÿ������ ��ȯ
    ���Ž�Ű���� �ϴ� ���ڴ� ���� ����
    
    - STR : '���ڿ�' ���ͷ�/ ���ڿ� Ÿ�� �÷���
*/

SELECT LTRIM('                  K            H')
FROM DUAL; -- ������ ���� ������ �⺻�� ����

SELECT RTRIM('K       H          ')
FROM DUAL;

SELECT LTRIM('0001230456000', '0')
FROM DUAL;

SELECT LTRIM('123123KH123', '123')
FROM DUAL; -- KH123 ��� : �� ��Ʈ�� ���� ����

SELECT LTRIM('ACABACCKH', 'ABC')
FROM DUAL; -- KH ���
--> ���Ž�Ű���� �ϴ� ���ڿ��� ������ �����ִ� ���� �ƴ϶� ���� �ϳ��ϳ��� �� �����ϸ� �� �����ִ� ����

----------------------------------------------------------------------------------------------------
/*
    TRIM
    
    - TRIM(BOTH/LEADING/TRAILING '���Ž�Ű�����ϴ� ����' FROM STR)
    : ���ڿ��� ����/��/�ڿ� �ִ� Ư�� ���ڸ� ������ ������ ���ڿ��� ��ȯ
    
    ������� CHARACTER Ÿ������ ��ȯ
    BOTH/LEADING/TRAILING ���� ����, ������ �⺻���� BOTH
    �����ϰ��� �ϴ� ���ڵ� ���� ����
    
    - STR : '���ڿ�' ���ͷ�/ ���ڿ� Ÿ�� �÷���
*/

SELECT TRIM('           K         H        ') -- == [ BOTH ' ' FROM '            K    H   ' ]�� ���� �ǹ�
FROM DUAL;

SELECT TRIM('Z' FROM 'ZZZKHZZZ') -- ������ BOTH�� �⺻��
FROM DUAL;

SELECT TRIM(LEADING 'Z' FROM 'ZZZKHZZZ') -- LEADING : ���� ���� == LTRIM �Լ��� ����
FROM DUAL;

SELECT TRIM(TRAILING 'Z' FROM 'ZZZKHZZZ') -- TRAILING : ���� ���� == RTRIM �Լ��� ����
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    LOWER / UPPER / INITCAP
    
    - LOWER(STR) : �ش� ���ڿ��� �ҹ��ڷ� ����
    - UPPER(STR) : �ش� ���ڿ��� �빮�ڷ� ����
    - INITCAP(STR) : ���� �������� �� �ܾ� ù ���ڸ� �빮�ڷ� ����

    ������� CHARACTER Ÿ������ ��ȯ
    
    - STR : '���ڿ�' ���ͷ� / ���ڿ� Ÿ�� �÷���
*/

SELECT LOWER('Welcome To My World!')
FROM DUAL;

SELECT UPPER('Welcome To My World!')
FROM DUAL;

SELECT INITCAP('welcome to my world!')
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    CONCAT
    
    - CONCAT(STR1, STR2)
    : ���޵� �� ���ڿ��� �ϳ��� ���ļ� ��ȯ
    
    ������� CHARACTER Ÿ������ ��ȯ
    
    - STR : '���ڿ�' ���ͷ� / ���ڿ� Ÿ�� �÷���
*/

SELECT CONCAT('������', 'ABC')
FROM DUAL;
--> CONCAT �Լ��� �� ���� 2���� ���ڸ� ��ĥ �� ����, �Ű����� 2��
-- SELECT CONCAT('������', '������', '������') -- �Ұ�

-- �� CONCAT�� ����ؾ� �Ѵٸ� �Լ� ��ø���� ����� ���� �ִ�.
SELECT CONCAT( CONCAT('ABC', 'ABCD'), '123')
FROM DUAL;

-- || �����ڴ� ��ġ�� ������ ���� ����
SELECT '������' || 'ABC'
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    REPLACE
    
    - REPLACE(STR, ã������, �ٲܹ���)
    : STR�κ��� ã�� ���ڸ� ã�Ƽ� �ٲ� ���ڷ� �ٲ㼭 ��ȯ
    
    ������� CHARACTER Ÿ������ ��ȯ
    
    - STR : '���ڿ�' ���ͷ� / ���ڿ� Ÿ�� �÷���
*/

SELECT REPLACE('����� ������ ���ﵿ', '���ﵿ', '�Ｚ��')
FROM DUAL;

SELECT EMP_NAME, EMAIL, REPLACE(EMAIL, 'kh', 'iei')
FROM EMPLOYEE;

-- ���� - �߰��� �ִ� ���ڸ� ������ �� ��� ����
-- TRIM �Լ��� ����� �ִ� ���ڴ� ���� �� ��
SELECT REPLACE('          K       H    ', ' ', '')
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    < ���ڿ� ���õ� �Լ� >
    
    ABS
    
    - ABS(NUMBER) : ���밪�� ��ȯ
*/

SELECT ABS(-10)
FROM DUAL;

SELECT ABS(-2.938)
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    MOD
    
    - MOD(NUMBER1, NUMBER2) : �� ���� ���� ������ �� ��ȯ
*/

SELECT MOD(10, 3)
FROM DUAL;

SELECT MOD(-10, 3)
FROM DUAL;

SELECT MOD(10.9, 3)
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    ROUND
    
    - ROUND(NUMBER, ��ġ��) : �ݿø� ó��
    
    ��ġ : �Ҽ��� �Ʒ� N��° ������ �ݿø�
    ��ġ�� ���� �� �⺻���� 0, ��ġ���� ���������� ���� ����
*/

SELECT ROUND(123.456) -- 0��° �ڸ������� ���δ�, 123
FROM DUAL;

SELECT ROUND(123.456, 1) -- 1��° �ڸ����� ���δ�, 123.5
FROM DUAL;

SELECT ROUND(123.456, 2) -- 2��° �ڸ����� ���δ�, 123.46
FROM DUAL;

SELECT ROUND(123.456, 3) -- 3��° �ڸ����� ���δ� -- �ݿø� ó�� ���� ����
FROM DUAL;

SELECT ROUND(123.456, 4) -- ������ �ڸ����� �������� ������ �״�� ��ȯ
FROM DUAL;

SELECT ROUND(123.456, -1) -- �Ҽ��� �� ù° �ڸ����� �ݿø�, 120
FROM DUAL;

SELECT ROUND(123.456, -2) -- �Ҽ��� �� ��° �ڸ����� �ݿø�, 100
FROM DUAL;

----------------------------------------------------------------------------------------------------
/*
    CEIL
    
    - CEIL(NUMBER) : �Ҽ��� �Ʒ��� ���� ������ �ø� ó��
*/

SELECT CEIL(123.156)
FROM DUAL; -- 124

SELECT CEIL(249.012)
FROM DUAL; --250

----------------------------------------------------------------------------------------------------
/*
    FLOOR
    
    - FLOOR(NUMBER) : �Ҽ��� �Ʒ��� ���� ������ ���� ó��
*/

SELECT FLOOR(123.965)
FROM DUAL; -- 123

SELECT FLOOR(207.48)
FROM DUAL; -- 207

-- �Ҽ��� �Ʒ��� ó������ �� �ִ� ȿ��
-- �� �������� ����Ϸκ��� ���ñ��� �ٹ� �ϼ� ��ȸ
-- �ٹ��ϼ� = ���ó�¥ - �����
SELECT EMP_NAME, FLOOR(SYSDATE - HIRE_DATE) AS �ٹ���
FROM EMPLOYEE;

SELECT EMP_NAME, CONCAT(FLOOR(SYSDATE - HIRE_DATE), '��') AS �ٹ���
FROM EMPLOYEE;

SELECT EMP_NAME, 
          CONCAT(FLOOR((SYSDATE - HIRE_DATE) / 365), '��') AS �ٹ��⵵,
          CONCAT(MOD(FLOOR(SYSDATE - HIRE_DATE), 365), '��') AS �ٹ��ϼ�
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    TRUNC
    
    - TRUNC(NUMBER, ��ġ��) : ��ġ�� ���� ������ ���� ó��
    
    ��ġ���� ���� ����, ���� �� �⺻�� 0
*/

SELECT TRUNC(123.765)
FROM DUAL; -- 123

SELECT TRUNC(123.765, 1)
FROM DUAL; -- 123.7

SELECT TRUNC(123.765, -1)
FROM DUAL; -- 120

----------------------------------------------------------------------------------------------------
/*
    < ��¥ ���� �Լ� >
    
    DATE Ÿ���� �ٷ�� �Լ���
    DATE Ÿ�� : ��, ��, ��, ��, ��, �ʸ� �� ������ �ڷ���
*/

-- SYSDATE : ���� �� DB�� ����ִ� ��ǻ�� �ý��� ���� ���� ��¥
SELECT SYSDATE
FROM DUAL; -- YY/MM/DD �������� ���, ��¥�ν� ���

SELECT '22/08/24'
FROM DUAL; -- ���� ���������� ���ڿ��ν� ���

-- MONTHS_BETWEEN(DATE1, DATE2) : �� ��¥ ������ ���� �� ��ȯ
-- �� �������� ����Ϸκ��� ���ñ����� �ٹ� �ϼ��� �ٹ� �������� ��ȸ
SELECT EMP_NAME
        , FLOOR(SYSDATE - HIRE_DATE) || '��' AS �ٹ��ϼ�
        , FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) || '����' AS �ٹ�������
FROM EMPLOYEE;
--> �� ��¥ ������ �������� ���� Ÿ������ ��ȯ���ֵ�,
-- ù��° �Ű������� �ش�Ǵ� DATE1 �κ��� �� ������ ��� ����� ������ ����

-- ADD_MONTHS(DATE, NUMBER) : Ư�� ��¥�� �ش� ���ڸ�ŭ�� �������� ���� ��¥ ��ȯ
-- ���� ��¥�κ��� 5���� ��
SELECT ADD_MONTHS(SYSDATE, 5)
FROM DUAL;

-- ���� ��¥�κ��� 1�� ��
SELECT ADD_MONTHS(SYSDATE, -1)
FROM DUAL;

-- ��ü ������� ������, �Ի���, �Ի� �� 6������ �帥 ���� ��¥ ��ȸ
SELECT EMP_NAME, HIRE_DATE, ADD_MONTHS(HIRE_DATE, 6)
FROM EMPLOYEE;

-- NEXT_DAY(DATE, ����(����/����)) : Ư�� ��¥���� ���� ����� �ش� ������ ã�� ��¥ ��ȯ
SELECT NEXT_DAY(SYSDATE, '�Ͽ���')
FROM DUAL;

SELECT NEXT_DAY(SYSDATE, 'ȭ����')
FROM DUAL; -- NEXT �̱� ������ ������ ������ �� ������� �̷��� ������ ��ȯ

SELECT NEXT_DAY(SYSDATE, '��')
FROM DUAL;

SELECT NEXT_DAY(SYSDATE, 1)
FROM DUAL;
-- 1: �Ͽ���, 2: ������, ... 7 : �����

-- SELECT NEXT_DAY(SYSDATE, 'SUN')
-- FROM DUAL;
-- ����� ��� �Ұ�, SUN, SUNDAY, ...
--> ���� �� ��ǻ�� �ý��� �� ������ ��� ����̱� ������

-- ���� �� ��ǻ���� �ý��� ��� ������ �Ŀ��� ����
-- ���� ���
ALTER SESSION SET NLS_LANGUAGE = AMERICAN;

SELECT NEXT_DAY(SYSDATE, 'SUNDAY')
FROM DUAL;

ALTER SESSION SET NLS_LANGUAGE = KOREAN;

-- LAST_DAY(DATE) : �ش� Ư�� ��¥ ���� ������ ��¥ ��ȯ
SELECT LAST_DAY(SYSDATE)
FROM DUAL;

-- ������, �Ի���, �Ի��� ���� ������ ��¥ ��ȸ
SELECT EMP_NAME, HIRE_DATE, LAST_DAY(HIRE_DATE)
FROM EMPLOYEE;

/*
     EXTRACT : �⵵ �Ǵ� �� �Ǵ� �� ������ �����ؼ� ��ȯ (NUMBER Ÿ��)
     
     - EXTRACT(YEAR FROM ��¥) : Ư�� �⵵ ����
     - EXTRACT(MONTH FROM ��¥) : Ư�� �� ����
     - EXTRACT(DAY FROM ��¥) : Ư�� �� ����
*/

SELECT EXTRACT(YEAR FROM SYSDATE) AS ��
        , EXTRACT(MONTH FROM SYSDATE) AS ��
        , EXTRACT(DAY FROM SYSDATE) AS ��
FROM DUAL;

-- �����, �Ի�⵵, �Ի��, �Ի��� ��ȸ
SELECT EMP_NAME �����
        , EXTRACT(YEAR FROM HIRE_DATE) || '��' AS �Ի�⵵
        , EXTRACT(MONTH FROM HIRE_DATE) || '��' AS �Ի��
        , EXTRACT(DAY FROM HIRE_DATE) || '��' AS �Ի���
FROM EMPLOYEE
ORDER BY "�Ի�⵵", "�Ի��", "�Ի���";

----------------------------------------------------------------------------------------------------
/*
    < ����ȯ �Լ� >
    
    NUMBER / DATE => CHARACTER
    
    - TO_CHAR(NUMBER / DATE, '����')
    : ������/��¥�� �����͸� ������ Ÿ������ ��ȯ
*/

SELECT TO_CHAR(1234)
FROM DUAL; -- 1234 => '1234'

SELECT TO_CHAR(1234, '00000') -- �� ĭ�� 0���� ä���ִ� ����
FROM DUAL; -- 1234 => '01234'
--> �ټ� ���� �� �����ʿ������� ���ڷ� ä���� ��ȯ

SELECT TO_CHAR(1234, '99999') -- �� ĭ�� ä���� �ʴ� ����
FROM DUAL; -- 1234 => '1234' 

SELECT TO_CHAR(1234, 'L00000') -- L : LOCAL => ���� �ý��ۿ� ������ ȭ�� ������ �ٿ��ִ� ����
FROM DUAL; -- 1234 => '\01234'

SELECT TO_CHAR(1234, 'L99999')
FROM DUAL; -- 1234 => '\1234'

-- ���� �޷� $�� ǥ���ϰ� �ʹٸ�
SELECT TO_CHAR(1234, '$99999')
FROM DUAL;

-- �ݾװ� ���� ū ������ ��� 3�ڸ����� , �� �����ؼ� ���
SELECT TO_CHAR(12341234, 'L99,999,999,999')
FROM DUAL; -- 1234 => '\12,341,234' : 3�ڸ����� , �� ���еǴ� ����

-- �޿� ������ 3�ڸ����� , �� �����Ͽ� ���
SELECT EMP_NAME, TO_CHAR(SALARY, 'L999,999,999') �޿�
FROM EMPLOYEE
ORDER BY �޿� DESC;

-- DATE(����Ͻú���) => CHARACTER
SELECT SYSDATE
FROM DUAL; -- ��¥ ����

SELECT TO_CHAR(SYSDATE)
FROM DUAL; -- ���� ���� '22/08/24'

SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD')
FROM DUAL; -- ���� ���� '22-08-24'

SELECT TO_CHAR(SYSDATE, 'PM HH:MI:SS') -- ���� ������ AM/PM �� ����/���� ���·� ����ش޶�� �� (!=����)
FROM DUAL; -- ���� 03:43:37

SELECT TO_CHAR(SYSDATE, 'HH24:MI:SS') -- HH24 : 24�ð� ��������
FROM DUAL; -- 15:43:13

SELECT TO_CHAR(SYSDATE, 'MON DY, YYYY') -- MON : N�� ����, DY : '����'�� �� ����
FROM DUAL; -- 8��  ��, 2022

-- �⵵�� �� �� �ִ� ����
SELECT TO_CHAR(SYSDATE, 'YYYY')
        , TO_CHAR(SYSDATE, 'RRRR')
        , TO_CHAR(SYSDATE, 'YY')
        , TO_CHAR(SYSDATE, 'RR')
        , TO_CHAR(SYSDATE, 'YEAR') -- YEAR : ����� �⵵���� ������ִ� ����
FROM DUAL;

-- ���� �� �� �ִ� ����
SELECT TO_CHAR(SYSDATE, 'MM')
        , TO_CHAR(SYSDATE, 'MON') -- �� ����
        , TO_CHAR(SYSDATE, 'MONTH') -- �� ����
        , TO_CHAR(SYSDATE, 'RM') -- RM : �θ�����
FROM DUAL;

-- �Ϸ� �� �� �ִ� ����
SELECT TO_CHAR(SYSDATE, 'D') -- �Ͽ��� �������� �� ��° ������
        , TO_CHAR(SYSDATE, 'DD') -- 1�� �������� �� ��° ������
        , TO_CHAR(SYSDATE, 'DDD') -- 1�� 1�� �������� �� ��° ������
FROM DUAL;

-- ���Ϸ� �� �� �ִ� ����
SELECT TO_CHAR(SYSDATE, 'DY')
        , TO_CHAR(SYSDATE, 'DAY')
FROM DUAL;

-- 2022�� 08�� 24�� (��) ����
SELECT TO_CHAR(SYSDATE, 'YYYY"��" MM"��" DD"��" (DY)')
FROM DUAL;
--> ������ �ƴ� �ٸ� ���ڿ� �Բ� ������ �� ���� ""�� �����ش�.

-- �����, �Ի���(���� ���� ����) ��ȸ
SELECT EMP_NAME
        , TO_CHAR(HIRE_DATE, 'YYYY"��" MM"��" DD"��" (DY)')
FROM EMPLOYEE;

-- 2010�� ���Ŀ� �Ի��� ������� �����, �Ի���(���� ���� ����) ��ȸ
SELECT EMP_NAME
        , TO_CHAR(HIRE_DATE, 'YYYY"��" MM"��" DD"��" (DY)') AS �Ի���
FROM EMPLOYEE
WHERE EXTRACT(YEAR FROM HIRE_DATE) >= 2010;

----------------------------------------------------------------------------------------------------
/*
    NUMBER / CHARACTER => DATE
    
    - TO_DATE(NUMBER/CHARACTER, '����') : ������/������ �����͸� ��¥������ ��ȯ
*/

SELECT TO_DATE(20210101) -- NUMBER -> DATE
FROM DUAL; -- �⺻ ������ YY/MM/DD�� ��ȯ

SELECT TO_DATE('20210101') -- CHARACTER -> DATE
FROM DUAL; -- �⺻ ������ YY/MM/DD�� ��ȯ

-- ������ �� : �Ű������� NUMBER Ÿ���� �ѱ� ���, 0���� �����ϴ� �⵵�� �ݵ�� ���ڿ� Ÿ������ �Ѱܾ� �Ѵ�.
SELECT TO_DATE(000101) -- NUMBER TYPE���� 0���� �����ϴ� ���� ���� -- ����
FROM DUAL;

SELECT TO_DATE('000101') -- CHARACTER Ÿ���� ����
FROM DUAL;

SELECT TO_DATE('20100101', 'YYYYMMDD')
FROM DUAL; -- 10/01/01 �������� ��ȯ�� (�� ���� ��ư �������� ��¥ �������� �� �� ����)
--> 2010�� YYYY�̰�, 01�� MM, 01�� DD�� ��Ī��Ų���� �������� ���� �Ѵ�. ���� ���� X

SELECT TO_DATE('041030 143021', 'YYMMDD HH24MISS')
FROM DUAL;

-- ������ �� 2 : YY�� RR�� ������
SELECT TO_DATE('140630', 'YYMMDD')
FROM DUAL; -- 14/06/30 -- 2014�⵵

SELECT TO_DATE('980630', 'YYMMDD')
FROM DUAL; -- 98/06/30 -- 2098�⵵
--> TO_DATE �Լ��� �̿��ؼ� DATE �������� ��ȯ ��
-- �� �ڸ� �⵵�� ���� YY ������ ������ ��� => ������ ���� ����� ����

SELECT TO_DATE('140630', 'RRMMDD')
FROM DUAL; -- 14/06/30 -- 2014�⵵

SELECT TO_DATE('980630', 'RRMMDD')
FROM DUAL; -- 98/06/30 -- 1998�⵵
-- �� �ڸ� �⵵�� ���� RR ������ ������ ��� 50 �̻��� ���ڸ� ���� ����, 50 �̸��� ���ڸ� ���� ����

----------------------------------------------------------------------------------------------------
/*
    CHARACTER => NUMBER
    
    - TO_NUMBER(CHARACTER, '����') : ������ �����͸� ���������� ��ȯ
*/

-- �ڵ�����ȯ �Ǵ� ����
SELECT '123' + '123'
FROM DUAL; -- 246 : NUMBER�� �ڵ�����ȯ �� ��� ������� ����

-- �ڵ�����ȯ �� �Ǵ� ����
SELECT '10,000,000' + '550,000'
FROM DUAL;
-- ���ڿ� �ȿ� ',' ���ڰ� ���ԵǾ��ֱ� ������ �ڵ�����ȯ�� �� ��
-- ���ڿ��� ���� ���ڷθ� �̷���� ���� ��쿡�� �ڵ�����ȯ ����
--> �̷� ��쿡 ����� �� �ִ� �Լ��� TO_NUMBER

-- ���� ���ø� TO_NUMBER �Լ��� ���� ���� : ��������ȯ (������ �ݵ�� ����)
SELECT TO_NUMBER('10,000,000', '99,999,999') + TO_NUMBER('550,000', '999,999')
FROM DUAL;

-- ���� ����� 10,550,000���� ���� �ʹٸ� : TO_CHAR �Լ� ��ø ���
SELECT TO_CHAR(TO_NUMBER('10,000,000', '99,999,999') + TO_NUMBER('550,000', '999,999'), '999,999,999')
FROM DUAL;

-- 0123?
SELECT TO_NUMBER('0123')
FROM DUAL; -- 123

----------------------------------------------------------------------------------------------------
/*
    < NULL ó�� �Լ� >
*/

-- NVL(�÷���, �ش��÷�����NULL�ϰ���ȯ�Ұ�)
-- : �ش� �÷����� ������ ��� ������ �÷��� �״�� ��ȯ
--  ���� �ش� �÷����� NULL�� ��� �Ű������� ������ Ư���� ��ȯ

-- �����, ���ʽ�, ���ʽ��� ���� ���� 0���� ���
SELECT EMP_NAME, BONUS, NVL(BONUS, 0)
FROM EMPLOYEE;

-- ���ʽ� ���� ���� ��ȸ
SELECT EMP_NAME, SALARY, BONUS, (SALARY + SALARY * NVL(BONUS, 0)) * 12 "���ʽ� ���� ����"
FROM EMPLOYEE;

-- �����, �μ��ڵ� ��ȸ(�μ� �ڵ尡 ���� ��� '����' ��ȸ)
SELECT EMP_NAME, NVL(DEPT_CODE, '����')
FROM EMPLOYEE;

-- NVL2(�÷���, �����1, �����2)
-- : �ش� �÷����� ������ ��� �����1, NULL�� ��� �����2 ��ȯ

-- �����, ���ʽ�, ���ʽ��� �ִ� ���� 0.7�� �λ�, ���ʽ��� ���� ���� 0 ���
SELECT EMP_NAME, BONUS, NVL2(BONUS, 0.7, 0)
FROM EMPLOYEE;

-- �����, �μ��ڵ� ��ȸ(�μ��ڵ� ������ '�μ���ġ�Ϸ�', ���� ��� '�μ���ġ�̿Ϸ�' ���)
SELECT EMP_NAME, NVL2(DEPT_CODE, '�μ���ġ�Ϸ�', '�μ���ġ�̿Ϸ�') �μ���ġ����
FROM EMPLOYEE;

-- NULLIF(�񱳴��1, �񱳴��2)
-- : �� �Ű������� ���� ��ġ�� ��� NULL ��ȯ, �� �Ű������� ���� ��ġ���� ���� ��� �񱳴��1 ��ȯ
SELECT NULLIF('123', '123')
FROM DUAL; -- NULL ��ȯ

SELECT NULLIF('123', '456')
FROM DUAL; -- '123' ��ȯ

----------------------------------------------------------------------------------------------------
/*
    < ���� �Լ� >
    
    DECODE(�񱳴��(�÷���, �������, �Լ��� ��), ���ǰ�1, �����1, ���ǰ�2, �����2, ... , �����)
    
    = �ڹٿ����� switch ���� ���� (���� �� ���ǹ�)
    switch(�񱳴��) {
    case ���ǰ�1 : �����1; break;
    case ���ǰ�2 : �����2; break;
    ...
    default : �����;
    }
*/

-- ���, �����, ����
SELECT EMP_ID
        , EMP_NAME
        , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '��' 
                                                 , '2', '��'
                                                 , '3', '��'
                                                 , '4', '��') ����
FROM EMPLOYEE;

-- �������� �޿��� �λ���Ѽ� ��ȸ
-- ���� �ڵ尡 'J7'�� ����� �޿��� 10% �λ�
-- ���� �ڵ尡 'J6'�� ����� �޿��� 15% �λ�
-- ���� �ڵ尡 'J5'�� ����� �޿��� 20% �λ�
-- ���� �ڵ尡 �� �̿��� ����� �޿��� 5% �λ�
-- ������, �����ڵ�, �λ� �� �޿�, �λ� �� �޿�
SELECT EMP_NAME
        , JOB_CODE
        , SALARY "�λ� �� �޿�"
        , DECODE(EMP_NAME, 'J7', SALARY+SALARY*0.1 -- SALARY * 1.1
                                   , 'J6', SALARY+SALARY*0.15
                                   , 'J5', SALARY+SALARY*0.2
                                   , SALARY+SALARY*0.05) "�λ� �� �޿�"
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------
/*
    < CASE WHEN THEN ���� >
    
    - CASE WHEN ���ǽ� 1 THEN �����1
             WHEN ���ǽ� 2 THEN �����2
             ...
             ELSE �����
      END
    
    -- DECODE �����Լ��� ���ϸ� DECODE �Լ��� �ش� ���ǰ˻�� ����񱳸� ����
       CASE WHEN THEN ������ Ư�� ���� ���ý� ���ǽ��� �������� �� ����. (���ǽ� Ŀ���͸���¡ ����)
       
       - �ڹٿ����� if - else if ���� �����
       if (���ǽ�1) {
            �����ұ���;
       } else if (���ǽ�2) {
            �����ұ���;
       } else {
            ������ ����;
       }
*/

-- ���, �����, �ֹι�ȣ�� ������ ���� (DECODE ����)
SELECT EMP_ID
        , EMP_NAME
        , DECODE(SUBSTR(EMP_NO, 8, 1), '1', '��'
                                                , '2', '��'
                                                , '3', '��'
                                                , '4', '��') ����
FROM EMPLOYEE;

-- CASE WHEN THEN ����
SELECT EMP_ID
        , EMP_NAME
        , CASE WHEN SUBSTR(EMP_NO, 8, 1) = '1' THEN '��'
                 WHEN SUBSTR(EMP_NO, 8, 1) = '2' THEN '��'
                 WHEN SUBSTR(EMP_NO, 8, 1) = '3' THEN '��'
                 WHEN SUBSTR(EMP_NO, 8, 1) = '4' THEN '��'
          END ����
FROM EMPLOYEE;

-- ����� ���� �ϳ��� ����
SELECT EMP_ID
        , EMP_NAME
        , CASE WHEN SUBSTR(EMP_NO, 8, 1) IN ('1', '3') THEN '��'
                 -- WHEN SUBSTR(EMP_NO, 8, 1) IN ('2', '4') THEN '��'
                 ELSE '��'
          END ����
FROM EMPLOYEE;

-- �����, �޿�, �޿� ���(���, �߱�, �ʱ�)
-- ��� : SALARY 500���� �ʰ�
-- �߱� : SALARY 350~500 ����
-- �ʱ� : SALARY 350���� ����
SELECT EMP_NAME
        , SALARY
        , CASE WHEN SALARY>5000000 THEN '���'
                 WHEN (3500000 < SALARY) AND (SALARY <= 5000000) THEN '�߱�'
                 WHEN SALARY <= 3500000 THEN '�ʱ�'
                 -- ELSE '�ʱ�'
         END "�޿� ���"
FROM EMPLOYEE
WHERE SALARY>5000000 -- ��� ��޸� ����
ORDER BY SALARY DESC; -- ������ ���� ������� ����

----------------------------------------------------------------------------------------------------
/*
    < �׷� �Լ� >
    
    �׷� �Լ� : N���� ���� �о 1���� ��� ��ȯ (�ϳ��� �׷캰�� �Լ� ���� ��� ��ȯ)
*/

-- 1. SUM(����Ÿ���÷���) : �ش� �÷������� �� �հ踦 ���ؼ� ��ȯ (NUMBER Ÿ�� ��ȯ)
-- ��ü ������� �� �޿� �հ�
SELECT SUM(SALARY)
FROM EMPLOYEE;

-- �μ� �ڵ尡 'D5' �� ������� �� �޿� �հ�
SELECT SUM(SALARY)
FROM EMPLOYEE
WHERE DEPT_CODE = 'D5';

-- ���� ����� �� �޿� ��
SELECT SUM(SALARY)
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('1', '3');

----------------------------------------------------------------------------------------------------

-- 2. AVG(����Ÿ���÷���) : �ش� �÷������� �� ����� ���ؼ� ��ȯ
-- ��ü ������� ��� �޿�
SELECT AVG(SALARY)
FROM EMPLOYEE;

-- ��ü ������� ��� �޿�(�ݿø�)
SELECT ROUND(AVG(SALARY))
FROM EMPLOYEE;

----------------------------------------------------------------------------------------------------

-- 3. MIN(�ƹ�Ÿ���÷���) : �ش� �÷����� �� ���� ���� ���� �Ű������� Ÿ������ ��ȯ
-- ��ü ����� �� �����޿�, ���� ���� �̸���, ���� ���� �̸��ϰ�, ���� ������ �Ի��� ��¥ ���ϱ�
SELECT MIN(SALARY) �����޿�
        , MIN(EMP_NAME) "���� ���� �̸�"
        , MIN(EMAIL) "���� ���� �̸���"
        , MIN(HIRE_DATE) "���� ���� �Ի���"
FROM EMPLOYEE;
--> 1�� 4���� ���´�

SELECT *
FROM EMPLOYEE
ORDER BY HIRE_DATE ASC;
--> MIN �Լ��� ���� : �ش� �÷��� ���� �������� �������� �� ���� ���ʿ� ������ ��

----------------------------------------------------------------------------------------------------

-- 4. MAX(�ƹ�Ÿ���÷���) : �ش� �÷����� �� ���� ū ���� �Ű������� Ÿ������ ��ȯ
-- ��ü ��� �� ���� ū �޿�, ���� ū �̸���, ���� ū �̸��ϰ�, ���� �ֱ� �Ի��� ��ȸ
SELECT MAX(SALARY) �ְ�޿�
        , MAX(EMP_NAME) "���� ū �̸�"
        , MAX(EMAIL) "���� ū �̸���"
        , MAX(HIRE_DATE) "�ֱ� �Ի���"
FROM EMPLOYEE;

SELECT *
FROM EMPLOYEE
ORDER BY HIRE_DATE DESC;
--> MAX �Լ��� ���� : �ش� �÷��� �������� �������� �������� �� ���� ���� ������ �� ��ȯ

----------------------------------------------------------------------------------------------------

-- 5. COUNT(*/�÷���/DISTINCT �÷���) : �÷��� ����ִ� ���� ���� ��ȯ (NUMBER Ÿ��)
-- COUNT(*) : ��ȸ ����� �ش�Ǵ� ��� �� ������ �� ���� ��ȯ : ���� ���� ��ȯ
-- COUNT(�÷���) : ������ �ش� �÷����� NULL�� �ƴ� �͸� ���� ���� ���� ��ȯ
-- COUNT(DISTINCT �÷���) : ������ �ش� �÷����� �ߺ����� ���� ��쿡�� �ϳ��� ���� ��ȯ�ϰڴ�. (NULL ������)

-- ��ü ����� ��ȸ
SELECT COUNT(*)
FROM EMPLOYEE;

-- ���� ����� ��ȸ
SELECT COUNT(*)
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('2', '4');

-- �μ���ġ�� �� ��� (DEPT_CODE �� �ִ�) ��
SELECT COUNT(DEPT_CODE)
FROM EMPLOYEE;

-- �μ���ġ�� �� ���ڻ����
SELECT COUNT(*)
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('2','4') 
      AND DEPT_CODE IS NOT NULL;

SELECT COUNT(DEPT_CODE)
FROM EMPLOYEE
WHERE SUBSTR(EMP_NO, 8, 1) IN ('2', '4');

-- ����� �ִ� �����
SELECT COUNT(MANGER_ID)
FROM EMPLOYEE;

-- ���� ������� �����ִ� �μ��� ����
SELECT COUNT(*)
FROM DEPARTMENT; -- ��ü �μ� ���� 9��

SELECT COUNT(DISTINCT DEPT_CODE)
FROM EMPLOYEE; -- ������� ���� �ִ� �μ� 6��
