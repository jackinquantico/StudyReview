/*
        < TCL : TRANSACTION CONTROL LANGUAGE >
        Ʈ������� �����ϴ� ���
        
        * Ʈ����� (TRANSACTION)
        - �����ͺ��̽��� ���� ���� ���� (���� ������ �� ���� ������ �ƴ�)
        - DML ���� ���� �������� ������׵��� �ϳ��� Ʈ��������� ��� ó��
          COMMIT (Ȯ��) �ϱ� �������� ������׵��� �ϳ��� Ʈ����ǿ� ��� ��
        - Ʈ������� ����� �Ǵ� SQL : INSERT, UPDATE, DELETE (DML)
        
        * TCL �� ����
        COMMIT, ROLLBACK, SAVEPOINT
        
        [ ǥ���� ]
        - COMMIT; : �ϳ��� Ʈ����ǿ� ����ִ� ������׵��� ���� DB�� �ݿ��ϰڴ�
                           ���� DB�� ����� ������� �ݿ���Ű�� ���� Ʈ������� �����
        - ROLLBACK; : �ϳ��� Ʈ����ǿ� ����ִ� ������׵��� ���� DB�� �ݿ����� �ʰ�
                               Ʈ������� ����ִ� ���� (������ COMMIT ����������)
        - SAVEPOINT ����Ʈ��; : ���� �� ��ɾ ������ �������� 
                                             �ش� ����Ʈ�� �ش�Ǵ� �ӽ��������� �����صδ� ��
        - ROLLBACK TO ����Ʈ��; : ��ü ������׵��� �����ϴ� ���� �ƴ϶�
                                                 �ش� ����Ʈ ���������� Ʈ����Ǹ� �ѹ�
*/

-- EMP_01 ���̺�
SELECT * FROM EMP_01;

-- ����� 901���� ��� ����
DELETE
FROM EMP_01
WHERE EMP_ID = 901;

SELECT * FROM EMP_01;

-- ����� 900���� ��� ����
DELETE
FROM EMP_01
WHERE EMP_ID = 900;

SELECT * FROM EMP_01;

-- ���� DB���� 900, 901�� ��� ������ �������� ���� ����
ROLLBACK;
-- Ʈ������� ����� ����

SELECT * FROM EMP_01;

--------------------------------------------

-- ����� 200���� ��� ����
DELETE
FROM EMP_01
WHERE EMP_ID = 200;

-- ��� 800, �̸� ȫ�浿, �μ��� �ѹ����� ��� �߰�
INSERT INTO EMP_01 VALUES (800, 'ȫ�浿', '�ѹ���');

SELECT * FROM EMP_01;

COMMIT;

SELECT * FROM EMP_01;
-->> SELECT �� ���� �� �ٽ� ROLLBACK

ROLLBACK;

SELECT * FROM EMP_01;
-->> ROLLBACK���� ���� => DDL���� Ʈ����ǿ� �ݿ����� ����

--------------------------------------------

-- ����� 217, 216, 214 �� ��� ����
DELETE
FROM EMP_01
WHERE EMP_ID IN (217, 216, 214);

-- 3���� ���� ������ ������ SAVEPOINT ����
SAVEPOINT SP;

-- ��� 801, �̸� ����, �λ���� ��� �߰�
INSERT INTO EMP_01 VALUES (801, '����', '�λ��');
--> SAVEPOINT ���� �������� ���� Ʈ�����

SELECT * FROM EMP_01;

-- ����� 218�� ��� ����
DELETE
FROM EMP_01
WHERE EMP_ID = 218;

-- SP ���������� �ѹ�
ROLLBACK TO SP;

SELECT * FROM EMP_01;
-->> 801�� �߰� + 218 ������ �ѹ��

COMMIT;
-->> 217, 216, 214 ������ ���� DB�� ����� + SAVEPOINT ������

--------------------------------------------

-- ����� 900, 901 �� ��� ����
DELETE
FROM EMP_01
WHERE EMP_ID IN (900, 901);

-- ����� 218�� ��� ����
DELETE
FROM EMP_01
WHERE EMP_ID = 218;

-- ���̺� ���� (DDL)
CREATE TABLE TEST (
        TID NUMBER
);

ROLLBACK;

SELECT * FROM EMP_01;
-- 900, 901, 218 ��� �����Ǿ�����. (�ѹ� �� ��)
-->> DDL ������ ���������� COMMIT �Ŀ� �۾� ����Ǳ� ������

/*
        DDL ���� (CREATE, ALTER, DROP)�� �����ϴ� ����
        ������ Ʈ����ǿ� �ִ� ��� ������׵��� ������ ���� DB�� �ݿ� (COMMIT)�� �Ŀ�
        DDL �۾��� ����Ǵ� ����
        => ��, DDL ���� �� �׻� ��������� �־��ٸ� �Ƚ� (COMMIT/ROLLBACK) �� �ڿ� DDL �����ؾ� �Ѵ�.
*/



