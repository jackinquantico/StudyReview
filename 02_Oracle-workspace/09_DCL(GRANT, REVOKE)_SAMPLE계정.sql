-- CREATE SESSION ������ �޾� ������ ���� ������ ����


-- CREATE TABLE ���� �ο� �ޱ� ��
-- ���̺� ���� �õ�
CREATE TABLE TEST (
        TEST_ID NUMBER
);
-- ORA-01031: insufficient privileges
--> ���� �����


-- CREATE TABLE ���� �ο� ���� ��
CREATE TABLE TEST (
        TEST_ID NUMBER
);
-- ORA-01950: no privileges on tablespace 'SYSTEM'
--> ���̺����̽�(���̺���� ���ִ� ����)�� �Ҵ���� �ʾ�
--   ���̺����̽��� ���� ���Ұ��� ����


-- TABLE SPACE �Ҵ���� ��
CREATE TABLE TEST (
        TEST_ID NUMBER
);
-- ���̺� ���� ����

-- ���̺� ���� ���� (CREATE TABLE) �� �ο��ް� �Ǹ�
-- �⺻�����δ� �ش� ������ �����ϰ� �ִ� ���̺��� �����ϴ� �͵� ��������
-->> DML �� ��� ����
SELECT * FROM TEST;
-- RESULT SET  ��� �� ��
INSERT INTO TEST VALUES (1);
-- INSERT ������ �÷��� ���Ե� ����


-- �� ����� ����
-- CREATE VIEW ���� �ο� �ޱ� ��
CREATE VIEW V_TEST
AS (SELECT * FROM TEST);
-- ORA-01031: insufficient privileges
--> VIEW ��ü ���� ���� (CREATE VIEW) �� ���� ������


-- CREATE VIEW ���� �ο� ���� ��
CREATE VIEW V_TEST
AS (SELECT * FROM TEST);
-- �� ���� ����

SELECT * FROM V_TEST;


-------------------------------------------------------------------------------------------------------------

-- SAMPLE �������� KH ������ ���̺� �����ؼ� ��ȸ
SELECT * FROM KH.EMPLOYEE;
-- KH ������ ���̺� �����ؼ� ��ȸ�� �� �ִ� ������ ���� ������
-- ORA-00942: table or view does not exist


-- SAMPLE ������ SELECT ON KH.EMPLOYEE ���� �ο� ��
SELECT * FROM KH.EMPLOYEE;
-- KH.EMPLOYEE ���̺� ��ȸ ����


-- SAMPLE �������� DEPARTMENT ���̺� �����ؼ� ��ȸ�ϸ�
SELECT * FROM KH.DEPARTMENT;
-- DEPARTMENT ���̺� �����ؼ� ��ȸ�� �� �ִ� ������ ���� ������
-- ORA-00942: table or view does not exist ���� �߻�


-- SAMPLE �������� KH ������ ���̺� �����ؼ� �� �����غ���
INSERT INTO KH.DEPARTMENT  VALUES ('D0', 'ȸ���', 'L2');
-- KH ������ ���̺� �����ؼ� ������ ������ �� �ִ� ������ ���� ������
-- ORA-00942: table or view does not exist ���� �߻�


-- SAMPLE ������ INSERT ON KH.DEPARTMENT ���� �ο� ��
INSERT INTO KH.DEPARTMENT  VALUES ('D0', 'ȸ���', 'L2');
-- KH.DEPARTMENT ���̺� �� INSERT ����


SELECT * FROM KH.DEPARTMENT;
-- SELECT ON KH.DEPARTMENT ������ �ο����� �ʾұ� ������
-- ORA-01031: insufficient privileges ���� �߻�


-- �� �������� ���̺� Ȯ�� �� INSERT ������ �ݿ����� ����
-- COMMIT�� �ؾ� ������ �ݿ���.
COMMIT;


-------------------------------------------------------------------------------------------------------------
-- SAMPLE �������� ���̺��� ������ �� ������ ���� ȸ�� ��

-- ���̺� ����� �õ�
CREATE TABLE TEST2 (
        TEST_ID NUMBER,
        TEST_TITLE VARCHAR2(50)
);
-- SAMPLE �������� CREATE TABLE ������ ȸ���߱� ������
-- ORA-01031: insufficient privileges


CREATE VIEW V_TEST2
AS (SELECT * FROM TEST WHERE 1=0);
-- ���̺� �������Ѹ� ȸ���߰� VIEW ���������� ȸ������ �ʾұ� ������
-- VIEW �� �� ������ �Ǵ� ���� Ȯ���� �� ����.
