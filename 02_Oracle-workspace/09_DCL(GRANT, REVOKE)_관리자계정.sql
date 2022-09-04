/*
        < DCL : Data Control Language >
        ������ ���� ���
        
        �������� �ý��۱��� �Ǵ� ��ü ���ٱ����� �ο� (GRANT)�ϰų� ȸ�� (REVOKE) �ϴ� ���
        
        * ���� �ο� (GRANT)
        - �ý��۱��� : Ư�� DB�� �����ϴ� ����, ��ü���� ������ �� �ִ� ����
        - ��ü���ٱ��� : Ư�� ��ü�鿡 �����ؼ� ������ �� �ִ� ���� (DML �� �� �ֵ���)
        
        1. �ý��۱���
        Ư�� DB�� �����ϴ� ����, ��ü���� ������ �� �ִ� ����
        
        [ ǥ���� ]
        GRANT ����1, ����2, .. TO ������;
        
        - �ý��� ������ ����
        CREATE SESSION : ������ ������ �� �ִ� ����
        CREATE TABLE : ���̺��� ������ �� �ִ� ����
        CREATE VIEW : �並 ������ �� �ִ� ����
        CREATE SEQUENCE : �������� ������ �� �ִ� ����
        CREATE USER : ������ ������ �� �ִ� ����
        ...
*/

-- 1. ������ �ο����� SAMPLE ���� ����
CREATE USER SAMPLE IDENTIFIED BY SAMPLE;
-- ������ ����⸸ �Ѵ� �ؼ� ������ ���� ����

-- 2. SAMPLE ������ �����ϱ� ���� CREATE SESSION ���Ѹ� �ο�
GRANT CREATE SESSION TO SAMPLE;

-- �� �������� SAMPLE �������� ���̺� ���� �õ� ��
-- SAMPLE ������ ���̺� ���� ������ ������ ���ٴ� ����
-- ORA-01031: insufficient privileges ���� �߻�

-- 3_1. SAMPLE ������ ���̺� ������ �� �ִ� CREATE TABLE ���� �ο�
GRANT CREATE TABLE TO SAMPLE;
-- �� �������� SAMPLE �������� ���̺� ���� �õ� ��
-- ���̺����̽��� ���� ���Ұ��� ����
-- ORA-01950: no privileges on tablespace 'SYSTEM'

-- 3_2. SAMPLE ������ ���̺����̽��� �Ҵ����ֱ�
-- (ALTER �������� ��������� ��)
ALTER USER SAMPLE QUOTA 2M ON SYSTEM;
-- QUOTA : ��, �Ҵ��ϴ�
-- 2M : 2 MEGA BYTE
-- SYSTEM : ����Ŭ���� �����ϴ� �⺻ ���̺����̽���
--> ���̺� ���� ����, DML ��� ����

-- �� �������� SAMPLE �������� �� ���� �õ� ��
-- ORA-01031: insufficient privileges ���� �߻�
--> VIEW ��ü ���� ���� (CREATE VIEW) �� ���� ������

-- 4. SAMPLE ������ �並 ������ �� �ִ� CREATE VIEW ���� �ο�
GRANT CREATE VIEW TO SAMPLE;
--> �� ���� ����


-------------------------------------------------------------------------------------------------------------
/*
        2. ��ü���ٱ��� / ��ü ����
        Ư�� ��ü���� ������ �� �ִ� ���� (=DML ���� : SELECT, INSERT, UPDATE, DELETE)
        
        [ ǥ���� ]
        GRANT �������� ON Ư����ü TO ������;
        
        ���� ����           |           Ư����ü
        ------------------------------------------
        SELECT            | TABLE, VIEW, SEQUENCE
        INSERT            | TABLE, VIEW 
        UPDATE          | TABLE, VIEW
        DELETE           | TABLE, VIEW
*/

-- �� �������� SAMPLE �������� KH ������ ���̺� �����ؼ� ��ȸ��
-- ORA-00942: table or view does not exist ���� �߻�
--> KH ������ ���̺� �����ؼ� ��ȸ�� �� �ִ� ������ ���� ������

-- 5. SAMPLE ������ KH.EMPLOYEE ���̺� ��ȸ�� �� �ִ� ���� �ο�
GRANT SELECT ON KH.EMPLOYEE TO SAMPLE;

-- �� �������� SAMPLE �������� KH ������ ���̺� �����ؼ� �� ���� ��
-- KH ������ ���̺� �����ؼ� ������ ������ �� �ִ� ������ ���� ������
-- ORA-00942: table or view does not exist ���� �߻�

-- 6. SAMPLE �������� KH.DEPARTMENT ���̺� �� ���� ���� �ο�
GRANT INSERT ON KH.DEPARTMENT TO SAMPLE;

-- �� �������� SAMPLE �������� KH.DEPARTMENT ���̺� �����ؼ� ��ȸ��
-- SELECT ON KH.DEPARTMENT ������ �ο����� �ʾұ� ������
-- ORA-01031: insufficient privileges ���� �߻�

-------------------------------------------------------------------------------------------------------------
-- �ּ����� ������ �ο��ϰ��� �� �� CONNECT, RESOURCE �� �ο��߾���
-- GRANT CONNECT, RESOURCE TO ������;

/*
        < �� ROLE >
        Ư�� ���ѵ��� �ϳ��� �������� ������� ��
        
        CONNECT : CREATE SESSION (�����ͺ��̽��� ������ �� �ִ� ����)
        RESOURCE : CREATE TABLE, CREATE SEQUENCE, .. (Ư�� ��ü���� ���� �� ������ �� �ִ� ����)
*/

-- ������ ��ųʸ��� �̿��Ͽ�
-- CONNECT, RESOURCE ��� �ѿ� � ���ѵ��� �����ִ��� Ȯ��
SELECT *
FROM ROLE_SYS_PRIVS
WHERE ROLE IN ('CONNECT', 'RESOURCE');

-------------------------------------------------------------------------------------------------------------
/*
        * ���� ȸ�� (REVOKE)
        ������ ȸ���� �� ����ϴ� ��ɾ�
        
        [ ǥ���� ]
        REVOKE ���Ѹ�, ���Ѹ�, .. FROM ������;
*/

-- 7. SAMPLE �������� ���̺��� ������ �� ������ ���� ȸ��
REVOKE CREATE TABLE FROM SAMPLE;


-------------------------------------------------------------------------------------------------------------
----- �ǽ����� -----
-- ����� ���� : MYTEST / MYTEST
-- �ο��� ���� : CONNECT, RESOURCE, CREATE VIEW
CREATE USER MYTEST IDENTIFIED BY MYTEST;
GRANT CONNECT, RESOURCE, CREATE VIEW TO MYTEST;

-- ���������� ����ڷκ��� ������ ��� ȸ��
-- ����� ���� ����
REVOKE CONNECT, RESOURCE, CREATE VIEW FROM MYTEST;
DROP USER MYTEST;

-- ���� �����Ͱ� �����ִµ� ������ �����Ϸ��� �ϸ� ���� �߻�
-- DROP USER ������ CASCADE; �� ������ ����������
-- �̷� ��Ȳ�� �߻����� �ʵ��� �ؾ��Ѵ�.