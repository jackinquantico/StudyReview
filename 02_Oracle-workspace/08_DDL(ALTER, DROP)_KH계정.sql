/*
        < DDL : DATA DEFINITION LANGUAGE >
        ������ ���� ���
        
        ��ü���� ������ ���� (CREATE), ���� (ALTER), ���� (DROP) �ϴ� ����
        
        1. ALTER
        ��ü ������ �����ϴ� ����
        
        < ���̺� ���� >
        ALTER TABLE ���̺�� �����ҳ���;
        
        - �����ҳ���
        1) �÷� �߰� / ���� / ����
        2) �������� �߰� / ���� => ������ �Ұ� (�����ϰ��� �Ѵٸ� ������ �ٽ� �߰��ؾ� �Ѵ�.)
        3) ���̺�� / �÷��� / �������Ǹ� ����
*/

-- 1) �÷� �߰� / ���� / ����
-- 1_1) �÷� �߰� (ADD) : ADD �߰����÷��� ������Ÿ�� [DEFAULT �⺻�� (��������)]
SELECT * FROM DEPT_COPY;

-- CNAME �÷� �߰�
ALTER TABLE DEPT_COPY ADD CNAME VARCHAR2(20);
-- ���ο� �÷��� ��������� �⺻������ NULL ������ ä����

-- LNAME �÷� �߰� (DEFAULT ����)
ALTER TABLE DEPT_COPY ADD LNAME VARCHAR2(20) DEFAULT '�ѱ�';
-- ���ο� �÷��� ��������� DEFAULT �����Ѵ�� �⺻���� '�ѱ�' ���� ä����

-- 1_2) �÷� ���� (MODIFY)
-- ������ Ÿ�� ���� : MODIFY �������÷��� �ٲܵ�����Ÿ��
-- DEFAULT �� ���� : MODIFY �������÷��� DEFAULT �ٲܱ⺻��

-- DEPT_ID �÷��� ������Ÿ���� CHAR(3)���� ����
ALTER TABLE DEPT_COPY MODIFY DEPT_ID CHAR(3);

-- ��, �����ϰ��� �ϴ� �÷��� �̹� ��� ���� ������ �ٸ� Ÿ�����δ� ���� �Ұ�
-- ��) ���� -> ���� (X) / ���ڿ� ������ ��� (X) / ���ڿ� ������ Ȯ�� (O)
-- ALTER TABLE DEPT_COPY MODIFY DEPT_ID NUMBER;
-- ORA-01439: column to be modified must be empty to change datatype

-- DEPT_TITLE �÷��� ������ Ÿ���� VARCHAR2(40)
-- LOCATION_ID �÷��� ������ Ÿ���� VARCHAR2(2)
-- LNAME �÷��� �⺻���� '�̱�'���� �ٲٱ�
ALTER TABLE DEPT_COPY
MODIFY DEPT_TITLE VARCHAR2(40)
MODIFY LOCATION_ID VARCHAR2(2)
MODIFY LNAME DEFAULT '�̱�';

-- �׽�Ʈ�� ���̺� ����
CREATE TABLE DEPT_COPY2
AS (SELECT * FROM DEPT_COPY);

SELECT * FROM DEPT_COPY2;

-- 1_3) �÷� ���� (DROP COLUMN) : DROP COLUMN �������÷���

-- DEPT_COPY2 �κ��� DEPT_ID�÷� �����
ALTER TABLE DEPT_COPY2 DROP COLUMN DEPT_ID;

ROLLBACK;
-- DDL ������ ���� �Ұ��� : DDL ���� ������ ���ʿ� COMMIT �� ���Ե� ����

SELECT * FROM DEPT_COPY2;

-- ��� �÷� ���ֱ�
ALTER TABLE DEPT_COPY2 DROP COLUMN DEPT_TITLE;
ALTER TABLE DEPT_COPY2 DROP COLUMN LOCATION_ID;
ALTER TABLE DEPT_COPY2 DROP COLUMN CNAME;
ALTER TABLE DEPT_COPY2 DROP COLUMN LNAME;
-- ORA-12983: cannot drop all columns in a table
-- ���̺� �ּ� �� ���� �÷��� �����ؾ� �� (������ �÷� ������ ����


-- 2) �������� �߰� / ����
/*
        2_1) �������� �߰�
        
        - PRIMARY KEY : ADD PRIMARY KEY (�÷���)
        - FOREIGN KEY : ADD FOREIGN KEY (�÷���) REFERENCES ���������̺�� (�������÷���);
                                  => ������ �÷����� ���� ����
        - UNIQUE : ADD UNIQUE (�÷���);
        - CHECK : ADD CHECK (�÷����� ������ ����);
        - NOT NULL : MODIFY �÷��� NOT NULL;
        
        �������Ǹ���� �ο��ϰ��� �Ѵٸ� CONSTRAINT �������Ǹ� �������� ���� ����
        CONSTRAINT �������Ǹ� �κ��� �������� (SYS_C~)
        ���� ���� : ���� ���� ���� ������ ������ �������Ǹ��� �ο��ؾ� �Ѵ�.
*/

-- DEPT_COPY ���̺���
-- DEPT_ID �÷��� PRIMARY KEY �������� �߰�
-- DEPT_TITLE �÷��� UNIQUE �������� �߰�
-- LNAME �÷��� NOT NULL �������� �߰�
ALTER TABLE DEPT_COPY
ADD CONSTRAINT DCOPY_PK PRIMARY KEY (DEPT_ID)
ADD CONSTRAINT DCOPY_UQ UNIQUE (DEPT_TITLE)
MODIFY LNAME CONSTRAINT DCPY_NN NOT NULL;

-- ���ǻ��� : �̹� ����ִ� ���� ���缭 ���������� �ο��ؾ���

/*
        2_2) �������� ����
        
        - PRIMARY KEY / FOREIGN KEY / UNIQUE / CHECK : DROP CONSTRAINT �������Ǹ�
        - NOT NULL : MODIFY �÷��� NULL
*/

-- DCOPY_PK �������� �����
ALTER TABLE DEPT_COPY DROP CONSTRAINT DCOPY_PK;

-- DCOPY_UQ, LNAME �÷��� NOT NULL �������� �����
ALTER TABLE DEPT_COPY
DROP CONSTRAINT DCOPY_UQ
MODIFY LNAME NULL;


-- 3. ���̺�� / �÷��� / �������Ǹ� ���� (RENAME)

-- 3_1) �÷��� ���� : RENAME COLUMN �����÷��� TO �ٲ��÷���
ALTER TABLE DEPT_COPY RENAME COLUMN DEPT_TITLE TO DEPT_NAME;
-- ���̺� �� �ߺ��� �̸����� �ٲ� �� ����.

-- 3_2) �������Ǹ� ���� : RENAME CONSTRAINT �����������Ǹ� TO �ٲ��������Ǹ�
ALTER TABLE DEPT_COPY RENAME CONSTRAINT SYS_C007139 TO DCOPY_LID_NN;

-- 3_3) ���̺�� ���� : RENAME TO �ٲ����̺��
-- >> �������̺���� �̹� ALTER TABLE ���̺���� ����Ǳ� ������
ALTER TABLE DEPT_COPY RENAME TO DEPT_TEST;

SELECT * FROM DEPT_COPY;
-- ORA-00942: table or view does not exist
-- ���� �̸��� �� ����Ǿ���

SELECT * FROM DEPT_TEST;

-->> CREATE TABLE ������ �����ؼ� ���̺� ����� ������
--     �Ĳ��ϰ� ���踦 �ؼ� ���̺��� �� ��������� �����Ͱ� �� ���Ե� ���Ŀ�
--     �ǵ����̸� ALTER ������ ������� �ʾƾ� �Ѵ�.


--------------------------------------------------------------------------------------------------------
/*
        2. DROP
        ��ü�� �����ϴ� ����
        
        ���� ������ : DROP USER ������;
        ���̺� ������ : DROP TABLE ���̺��;
*/

-- DEPT_TEST ���̺� ����
DROP TABLE DEPT_TEST;

SELECT * FROM DEPT_TEST;
-- ORA-00942: table or view does not exist

-- DEPARTMENT ���̺�
DROP TABLE DEPARTMENT;
-- ORA-02449: unique/primary keys in table referenced by foreign keys
-- UNIQUE �Ǵ� PRIMARY KEY ���������� �ɸ� Ű�� �ٸ� ���̺��� �ܷ�Ű���� �����ǰ� �ֱ� ������
-->> ���̺� ���� ��, ��򰡿��� �����ǰ� �ִ� �θ����̺���� �������� �ʴ´�.

-- ���� �θ����̺��� �����ϰ� �ʹٸ�
-- 1. �ڽ����̺��� ���� ���� �� �θ����̺� ����
DROP TABLE �ڽ����̺��;
DROP TABLE �θ����̺��;

-- 2. �θ����̺� �����ϴµ� �¹��� �ܷ�Ű ���������� �Բ� �����ϴ� ���
DROP TABLE �θ����̺�� CASCADE CONSTRAINT;

-->> DROP ������ ���������� ��ÿ� ���̱⺸�ٴ�
--     CREATE ���� ���� Ȥ�� �ߺ��� �̸��� ��ü�� �̹� ���� �Ϳ� ����ؼ� �����ϴµ� �ַ� ����
