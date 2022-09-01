/*
        * DDL (DATA DEFINITION LANGUAGE)
        ������ ���� ���
        
        ����Ŭ���� �����ϴ� ��ü(OBJECT) ��
        ������ ����� (CREATE), ������ �����ϰ� (ALTER), ���� ��ü�� �����ϴ� (DROP) �ϴ� ��ɹ�
        
        ����Ŭ������ ��ü (OBJECT) : ������ ���̽��� �̷�� ������
                                                   ���̺� (TABLE), �� (VIEW), ������ (SEQUENCE), �ε��� (INDEX),
                                                   ��Ű�� (PACKAGE), Ʈ���� (TRIGGER), ���ν��� (PROCEDURE),
                                                   �Լ� (FUNCTION), ���Ǿ� (SYNONYM), ����� (USER)
                                                   
        => ��, �����ͺ��̽��� ���� ��ü�� �����ϴ� ����
             �ַ� DB ������, �����ڰ� �����
*/

-------------------------------------------------------------------------------------------------------------
/*
        < CREATE TABLE >
        
        ���̺��̶�
        �� (ROW), �� (COLUMN) �� �����Ǵ� ���� �⺻���� ������ ���̽� ��ü
        ��� �����ʹ� ���̺��� ���ؼ� ����� (��, �����͸� �����ϰ��� �Ѵٸ� ���̺��� ������ ��)
        
        [ ǥ���� ]
        CREATE TABLE ���̺�� (
                �÷��� �ڷ���,
                �÷��� �ڷ���,
                ...
                �÷��� �ڷ���        
        );
        
        < �ڷ��� >
        - ���� : CHAR(����Ʈ�� ũ��) / VARCHAR2(ũ��) : ũ��� BYTE �� (����, ������, Ư������ => �� ���ڴ� 1BYTE,
                                                                                                           �ѱ� => �� ���ڴ� 3BYTE)
                  CHAR(����Ʈ ��) : �ִ� 2000 BYTE ���� ���� ����
                                              ���� ���� (�ƹ��� ���� ���� ���͵� �������� ä���� ó���� �Ҵ��� ũ�� ����)
                                              �ַ� ���� ���� ���ڼ��� ������ ���� ���
                                              �� ) ���� : �� / ��, M / F
                                                    �ֹε�Ϲ�ȣ : 123456-1234567 (�� 14�ڸ�)
                                                    �޴��� ��ȣ : 01012345678 (�� 11�ڸ�)
                  VARCHAR2(����Ʈ ��) : �ִ� 4000 BYTE ���� ���� ����
                                                      ���� ���� (���� ���� ������ �� ��� ���� ���缭 ũ�Ⱑ �پ���)
                                                      �ַ� ���� ���̰� �������� ���� ���
                                                      �� ) �� �ּ�, ���̵�, ��й�ȣ, �̸�, �Խñ�(����), ��۳���, ...
              => VAR �� '����'�� �ǹ��ϰ� 2�� �������� '2��'�� �ǹ���
                 
        - ���� : NUMBER : ���� / �Ǽ� ������� �� �ڷ������� �ٷ��.
        
        - ��¥ : DATE : ����Ͻú��� �� ��� �� �����ϴ� ����
*/

-- ȸ������ �����͸� ���� �� �ִ� ���̺� ����
-- ���̺�� : MEMBER
-- ���� �����͵� : ���̵�, ��й�ȣ, �̸�, ȸ��������
CREATE TABLE MEMBER ( -- ���̺���̳� �÷��� ���� �ۼ��� ��� ��ҹ��� ���� X => ��Ÿǥ��� �Ұ�, ����ٷ� ����
        MEMBER_ID VARCHAR2(20),  
        MEMBER_PWD VARCHAR2(20),
        MEMBER_NAME VARCHAR2(20),
        MEMBER_DATE DATE
);

SELECT * FROM MEMBER;

-------------------------------------------------------------------------------------------------------------
/*
        �÷��� �ּ� �ޱ� (�÷��� ���� ����)
        
        [ ǥ���� ]
        COMMENT ON COLUMN ���̺��.�÷��� IS '�ּ�����';
*/

COMMENT ON COLUMN MEMBER.MEMBER_ID IS 'ȸ�����̵�';
COMMENT ON COLUMN MEMBER.MEMBER_PWD IS 'ȸ����й�ȣ';
COMMENT ON COLUMN MEMBER.MEMBER_NAME IS 'ȸ���̸�';
COMMENT ON COLUMN MEMBER.MEMBER_DATE IS 'ȸ��������';

-------------------------------------------------------------------------------------------------------------
-- ���� �� ������ �����ϴ� ���̺��, �÷����� Ȯ���� �� �ִ� ��ɾ�

-- ������ ��ųʸ� : ����Ŭ�� �پ��� ��ü���� ������ �����ϰ� �ִ� �ý��� ���̺�
--                          �б� �����̸� �������� ������ �Ұ����ϰ�, �����ͺ��̽��� ��� ���� ����

-- USER_TABLES ���� �� ������ ������ �ִ� ���̺���� �������� ������ Ȯ���� �� �ִ� ������ ��ųʸ�
SELECT * FROM USER_TABLES;

-- USER_TAB_COLUMNS : ���� �� ������ ������ �ִ� ���̺���� ��� �÷��� ������ ��ȸ�� �� �ִ� ������ ��ųʸ�
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'MEMBER';
--                                                             ���̺� �̸��� MEMBER �� ��� �÷� ���� ��ȸ

SELECT * FROM MEMBER;

-- ���� : ������ �߰��ϴ� ���� (DML �� INSERT ���� : �� �� ������ �߰�, ���� ������ ���缭 �ۼ�)
-- INSERT INTO ���̺�� VALUES (��, ��, .. );
INSERT INTO MEMBER VALUES ('user01', 'pass01', 'ȫ�浿', '2021-02-01');
INSERT INTO MEMBER VALUES ('user02', 'pass02', '�踻��', '21/02/02');
INSERT INTO MEMBER VALUES ('user03', 'pass03', '�ڰ���', SYSDATE);

-- ���ݺ��� ����ؾ� �� ��
INSERT INTO MEMBER VALUES(NULL, NULL, NULL, SYSDATE);
--> ���̵�, ��й�ȣ, �̸��� NULL ���� �����ϸ� �ȵǴµ� INSERT�� �Ǵ� ����
INSERT INTO MEMBER VALUES('user03', 'pass03', '��浿', SYSDATE);
--> �ߺ��� ���̵�� �����ؼ��� �� �Ǵµ� INSERT�� �Ǵ� ����

-- ���� NULL ���̳� �ߺ��� ���̵��� ��ȿ���� ���� �����̴�.
-- ��ȿ�� ������ ���� �����ϱ� ���� "��������" �� �ɾ���� �Ѵ�.

-------------------------------------------------------------------------------------------------------------
/*
        < �������� CONSTRAINTS >
        
        - ���ϴ� �����Ͱ��� �����ϱ� ���� (�����ϱ� ����) Ư�� �÷����� �����ϴ� ����
        => �������� �ߺ��� �����Ͽ� �������� ���Ἲ�� ������ ����
        - ���������� �ο��� �÷��� ���� �����Ϳ� ������ �ִ��� �ڵ����� �˻��� ����
        
        - ���� : NOT NULL (NULL�� X), UNIQUE (�ߺ� X), CHECK (���� ���� ���� ����),
                    PRIMARY KEY (�ĺ�Ű), FOREIGN KEY (�ܷ�Ű)
                    
        - �÷��� ���� ������ �ο��ϴ� ��� : �÷����� / ���̺���
*/

/*
        1. NOT NULL ���� ����
        �ش� �÷��� �ݵ�� ���� �����ؾ� �� ��� ���
        (��, NULL ���� ���� ���ͼ��� �� �Ǵ� �÷��� �ο�)
        ���� INSERT �Ǵ� ���� UPDATE �ÿ��� NULL�� ������� ����
        
        ��, NOT NULL ���� ������ �ο��� ���� �÷����� ��Ĺۿ� ����� �� ����
*/

-- NOT NULL ���� ���Ǹ� ������ ���̺� �����
-- �÷����� ��� : ���̺� ������ (�÷��� �ڷ��� �������Ǹ�) => ���������� �ο��ϰ��� �ϴ� �÷� �ڿ� ��ٷ� ���

-- ȸ������ ������ ���� �� �ִ� ���̺� VER.2
-- ���̺�� : MEM_NOTNULL
-- �÷��� : ȸ����ȣ, ȸ�����̵�, ȸ����й�ȣ, ȸ���̸�, ����, ����ó, �̸���
CREATE TABLE MEM_NOTNULL (
        MEM_NO NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30)
);

SELECT * FROM MEM_NOTNULL;

-- ������ ����
INSERT INTO MEM_NOTNULL VALUES (1, 'user01', 'pass01', 'ȫ�浿', '��', '010-1111-2222', 'aaa@naver.com');

-- NOT NULL ���ǿ� ����Ǵ� �� �ֱ�
INSERT INTO MEM_NOTNULL VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL);
-- ORA-01400: cannot insert NULL into ("DDL"."MEM_NOTNULL"."MEM_ID")
-- DDL ������ MEM_NOTNULL ���̺��� MEM_ID �÷����� NULL ���� ���� �� ����
-- NOT NULL ���� ���ǿ� ����Ǿ� ���� �߻�

INSERT INTO MEM_NOTNULL VALUES (2, 'user02', 'pass02', '�踻��', NULL, NULL, NULL);

INSERT INTO MEM_NOTNULL VALUES (3, 'user01', 'pass03', '����', '��', NULL, NULL);

-- NOT NULL ���� ������ �ο��Ǿ����� ���� �÷��� ���� ���� NULL�̵� ��� ����
-- NOT NULL ���� ������ �ο��� �÷����� �ݵ�� NULL ���� �ƴ� ���� �����ؾ� �Ѵ�.

-------------------------------------------------------------------------------------------------------------
/*
        2. UNIQUE ���� ����
        �÷��� �ߺ����� �����ϴ� ���� ����
        ���� �Ǵ� ���� �� ���� �ش� �÷��� �� �ߺ����� ���� ��� �߰� �Ǵ� ������ ���� �ʰ� ����
        
        �÷����� / ���̺��� ��� �� �� ����
*/

-- ȸ�� ���̺� VER.3
-- ���̺�� : MEM_UNIQUE

-- �÷����� ������� UNIQUE ���� ���� �ɱ�
CREATE TABLE MEM_UNIQUE (
        MEM_NO NUMBER NOT NULL, 
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,        -- ����� �����ؼ� ���� ���� ���� �� ����
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30)        
);

-- ���̺� �����ϴ� ��ɾ�
DROP TABLE MEM_UNIQUE;

-- ���̺� ���� ������� UNIQUE �������� �ɱ�
CREATE TABLE MEM_UNIQUE (
        MEM_NO NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        UNIQUE (MEM_ID)
);

SELECT * FROM MEM_UNIQUE;

-- ������ �߰�
INSERT INTO MEM_UNIQUE VALUES (1, 'user01', 'pass01', 'ȫ�浿', '��', '010-1111-2222', 'aaa@naver.com');
INSERT INTO MEM_UNIQUE VALUES (2, 'user02', 'pass02', '�踻��', NULL, NULL, NULL);

-- UNIQUE �÷��� �ߺ��� �� ���Խ� ���� �߻�
INSERT INTO MEM_UNIQUE VALUES (3, 'user02', 'pass03', '�ڰ���', NULL, NULL, NULL);
-- ORA-00001: unique constraint (DDL.SYS_C007040) violated
-- UNIQUE ���� ���ǿ� ����Ǿ� INSERT ����, ���� ���� �߻�
-- ���� �������� ���� ������ ����Ǿ����� �˷��ֳ�, ��Ȯ�� � �÷��� ���������� �˷����� ����
-->> ������.�������Ǹ����� ��Ʈ�� ��

-------------------------------------------------------------------------------------------------------------
/*
        * �������� �ο� �� �������Ǹ� �����ϴ� ǥ����
        
        > �÷����� ���
        CREATE TABLE ���̺�� (
                �÷��� �ڷ��� CONSTRAINT �������Ǹ� ��������,
                �÷��� �ڷ���,
                ...
        );
        
        > ���̺��� ���
        CREATE TABLE ���̺�� (
                �÷��� �ڷ���,
                ..
                �÷��� �ڷ���,
                CONSTRAINT �������Ǹ� �������� (�÷���),
                �������� (�÷���),
                ..
        );

        �� �� �� ��� ��� CONSTRAINT �������Ǹ� �κ��� ���� �����ϴ�
        ��, ������ SYS_C~ ó�� ������ �������Ǹ��� �ٰ� �Ǿ� ������ �ľ��ϱ� �����.
*/

-- ȸ�����̺� VER.4
-- ���̺� : MEM_CON_NM
CREATE TABLE MEM_CON_NM (
        MEM_NUM NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) CONSTRAINT MEM_NAME_NN NOT NULL, -- �÷����� ���
        GENDER CHAR(3),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        CONSTRAINT MEM_ID_UQ UNIQUE (MEM_ID) -- ���̺��� ���
);

-- ������ ����
INSERT INTO MEM_CON_NM VALUES (1, 'user01', 'pass01', 'ȫ�浿', NULL, NULL, NULL);

-- UNIQUE�� �������Ǹ� ���� �� �ߺ��� ����
INSERT INTO MEM_CON_NM VALUES (2, 'user01', 'pass02', '�踻��', NULL, NULL, NULL);
-- ORA-00001: unique constraint (DDL.MEM_ID_UQ) violated
-->> �������Ǹ��� �����Ѵ�� �����־� ���� �߻��� � �÷��� ������ �ִ��� �ľ��ϱ� ����

SELECT * FROM MEM_CON_NM;
-- �ι�° INSERT ���� ���������� �����ؼ� ���Ե��� ����


INSERT INTO MEM_CON_NM VALUES (2, 'user02', 'pass02', '�ڸ���', '��', NULL, NULL);
-- GENDER �÷��� ��/���θ� �Է¹ް� ������ '��' ��� ������ ���Ե�

-------------------------------------------------------------------------------------------------------------
/*
        3. CHECK ���� ����
        �÷��� ��ϵ� �� �ִ� ���� ���� ������ �����ص� �� �ִ�.
        
        [ ǥ���� ]
        CHECK (���ǽ�)
*/

-- ȸ�����̺� VER.5
-- ���̺�� : MEM_CHECK
CREATE TABLE MEM_CHECK(
        MEM_NUM NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL, 
        GENDER CHAR(3) CHECK (GENDER IN ('��', '��')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE NOT NULL,
        CONSTRAINT MEM_ID_UQ2 UNIQUE (MEM_ID) -- ���̺��� ���
        -- ORA-02264: name already used by an existing constraint
        -->> �������Ǹ� �� ���� ���� ���� �ٸ� ���̺��̴��� �ߺ��Ǹ� �� �� 
);

-- ������ ����
INSERT INTO MEM_CHECK VALUES (1, 'user01', 'pass01', 'ȫ�浿', '��', '010-1111-2222', 'aaa@naver.com', SYSDATE);

-- GENDER �÷��� ��/���� �ƴ� �� �ֱ�
INSERT INTO MEM_CHECK VALUES (2, 'user02', 'pass02', '�踻��', '��', NULL, NULL, SYSDATE);
-- ORA-02290: check constraint (DDL.SYS_C007057) violated
-->> CHECK ���������� �������� ��� ��Ÿ���� ����

-- GENDER �÷��� NULL �ֱ�
INSERT INTO MEM_CHECK VALUES(3, 'user03', 'pass03', '�ڸ���', NULL, NULL, NULL, SYSDATE);
-- NULL ���� CHECK �������ǿ� INSERT�� �� �ִ�
-- NULL ���� �� ������ �ϰ� �ʹٸ� NOT NULL �������ǵ� �Բ� �ο��ϸ� �ȴ�.

-- ȸ���������� �׻� SYSDATE ������ INSERT �ϰ� �ʹ�

-------------------------------------------------------------------------------------------------------------
/*
        * Ư�� �÷��� ���� ���� ���� �⺻���� ���� ���� (���� ������ �ƴ�)
        => DEFAULT ����
*/

DROP TABLE MEM_CHECK;

-- MEM_CHECK ���̺� +
-- ȸ�������Ͽ� ���� DEFAULT ���� �߰��� ���̺�
CREATE TABLE MEM_CHECK (
        MEM_NO NUMBER NOT NULL,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3) CHECK (GENDER IN ('��', '��')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE NOT NULL -- ��������� �ٸ� ��¥�� �־ ������, �ۼ����� ������ SYSDATE�� ���� �ȴ�
        -- DEFAULT�� ���������� �ƴϱ� ������ �������� �ۼ� ���� ���� �ۼ��ؾ� �Ѵ�.
);

SELECT * FROM MEM_CHECK;

-- ������ ����
/*
        INSERT INTO ���̺��
        VALUES (��, ��, ��, ...);  => ��, ��� �÷��� ���� �� ���� ������ Ÿ���� ���� ���� �� ����ؾ� �Ѵ�.
*/
INSERT INTO MEM_CHECK VALUES (1, 'user01', 'pass01', 'ȫ�浿', '��', NULL, NULL, DEFAULT); 
-- DEFAULT ������ �÷��� �κп� DEFAULT Ű����� �� INSERT�ϸ� �⺻������ �����ص� SYSDATE ���Ե�

INSERT INTO MEM_CHECK VALUES (2, 'user02', 'pass02', '�踻��', '��', NULL, NULL, '22/08/22');
-- DEFAULT �� ���������� �ƴϱ� ������ �ٸ� ���� �־ ���� ���� INSERT �ȴ�.

INSERT INTO MEM_CHECK VALUES (3, 'user03', 'pass03', '�ڸ���', '��', NULL, NULL);
-- SQL ����: ORA-00947: not enough values
-->> INSERT �� �� ���� ������ �����ϰ� �������� �� (���� �����Ǿ��� ��) �߻��ϴ� ����

/*
        INSERT INTO ���̺�� (�÷���, �÷���, �÷���, ..)        -- �� NOT NULL ���������� �ɸ� �÷��� ������ �����ؾ� �Ѵ�.
                            VALUES (��,       ��,       ��, ..      )
*/

INSERT INTO MEM_CHECK (MEM_NO, MEM_ID, MEM_PWD, MEM_NAME)
VALUES (3, 'user03', 'pass03', '�ڸ���');
-- ���� INSERT �ϰڴٰ� ��������� ������ �� �� �÷����� �⺻������ NULL ���� ��
-- ��, DEFAULT ���� �ο��Ǿ��ִٸ� NULL ���� �ƴ� DEFAULT ������ ���� �ȴ�.

SELECT * FROM MEM_CHECK;

-------------------------------------------------------------------------------------------------------------
/*
        4. PRIMARY KEY (�⺻Ű, ��Ű)
        ���̺��� �� ����� ������ �����ϰ� �ĺ��� �� �ִ� �÷��� �ο��ϴ� ��������
        => �� ����� �������� �� �ִ� �ĺ����� ����
        �� ) ȸ����ȣ, �ֹι�ȣ, ���, �й�, �����ȣ, ...
        
        �ĺ����� Ư¡ �� �ߺ����� �ʰ�, �����ؾ߸� �ϰ�, ���� ���� ���� ���� �÷��� �ο�
        (NOT NULL + UNIQUE == PRIMARY KEY)
        
        ��, �� ���̺� �� �� ���� �÷����� ���� ����
*/

-- ȸ�� ���̺� VER.5
-- ���̺�� : MEM_PRIMARYKEY1
CREATE TABLE MEM_PRIMARYKEY1 (
        MEM_NO NUMBER CONSTRAINT MEM_PK PRIMARY KEY,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3) CHECK (GENDER IN ('��', '��')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE
        -- ���̺��� ���
        -- CONSTRAINT MEM_PK PRIMARY KEY (MEM_NO)
);

SELECT * FROM MEM_PRIMARYKEY1;

-- ������ ����
INSERT INTO MEM_PRIMARYKEY1
VALUES (1, 'user01', 'pass01', 'ȫ�浿', '��', NULL, NULL, DEFAULT);

-- PRIMARY KEY �ߺ��� ����
INSERT INTO MEM_PRIMARYKEY1
VALUES (1, 'user02', 'pass02', '�踻��', '��', NULL, NULL, DEFAULT);
-- ORA-00001: unique constraint (DDL.MEM_PK) violated 
-- �⺻Ű �÷��� �ߺ� ����

-- PRIMARY KEY�� NULL ����
INSERT INTO MEM_PRIMARYKEY1
VALUES (NULL, 'user02', 'pass02', '�踻��', NULL, NULL, NULL, DEFAULT);
-- ORA-01400: cannot insert NULL into ("DDL"."MEM_PRIMARYKEY1"."MEM_NO")
-- �⺻Ű �÷��� NULL ������ ���� ����

INSERT INTO MEM_PRIMARYKEY1
VALUES (2, 'user02', 'pass02', '�踻��', NULL, NULL, NULL, DEFAULT);


-- �� ���̺� PRIMARY KEY ���� ������ 2�� ����
CREATE TABLE MEM_PRIMARYKEY2 (
        MEM_NO NUMBER PRIMARY KEY,
        MEM_ID VARCHAR2(20) PRIMARY KEY,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3) CHECK (GENDER IN ('��', '��')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE
);
-- ORA-02260: table can have only one primary key
-- PRIMARY KEY �� �� ���̺� �� �� �̻��� �� �� ����.


-- ���� ��Ȳ : ��, �� �÷��� �� ���� ��� PRIMARY KEY �ϳ��� ���� ����
--                = ����Ű

-- ����Ű ���� ����
CREATE TABLE MEM_PRIMARYKEY2 (
        MEM_NO NUMBER,
        MEM_ID VARCHAR2(20),
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GENDER CHAR(3) CHECK (GENDER IN ('��', '��')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        PRIMARY KEY (MEM_NO, MEM_ID) -- �÷��� ��� PRIMARY KEY �ϳ��� ���� => ����Ű
);

-- ������ ����
INSERT INTO MEM_PRIMARYKEY2 VALUES (1, 'user01', 'pass01', 'ȫ�浿', NULL, NULL, NULL);

-- MEM_NO �� �ߺ����Ѽ� ����
INSERT INTO MEM_PRIMARYKEY2 VALUES (1, 'user02', 'pass02', '�踻��', NULL, NULL, NULL);
-- MEM_ID �� �ߺ����Ѽ� ����
INSERT INTO MEM_PRIMARYKEY2 VALUES (2, 'user02', 'pass02', '�ڸ���', NULL, NULL, NULL);
-->> �� �÷� �� �ϳ��� ��ġ�� ��� �ߺ����� �������� �ʰ� INSERT�� ��

-- ����Ű�� Ư¡ : ���� �÷��鿡 ���� ��� ���� �� ��ġ�ؾ� �ߺ����� �Ǻ�
INSERT INTO MEM_PRIMARYKEY2 VALUES (2, 'user02', 'pass03', '����', NULL, NULL, NULL);
-- ORA-00001: unique constraint (DDL.SYS_C007075) violated
-- UNIQUE ���� ���� ���� ����

-- ����Ű�� Ư¡ : ���� �÷��鿡 ���� NULL ���� �ϳ��� ���ԵǾ��ִٸ� INSERT �Ұ�
INSERT INTO MEM_PRIMARYKEY2 VALUES (3, NULL, 'pass03', '�̼���', NULL, NULL, NULL);
-- ORA-01400: cannot insert NULL into ("DDL"."MEM_PRIMARYKEY2"."MEM_ID")

SELECT * FROM MEM_PRIMARYKEY2;



-------------------------------------------------------------------------------------------------------------
/*
        5. FOREIGN KEY (�ܷ�Ű)
        �ش� �÷��� �ٸ� ���̺� �����ϴ� ���� ���;� �ϴ� �÷��� �ο��ϴ� ��������
        => �ٸ� ���̺��� �����Ѵٰ� ǥ����
            ��, ������ �ٸ� ���̺��� �����ϰ� �ִ� ���� ���� �� �ִ�.
        => FOREIGN KEY ������������ �ٸ� ���̺��� ���踦 ������ �� ���� (JOIN)
        
        [ ǥ���� ]
        - �÷����� ���
        �÷��� �ڷ��� CONSTRAINT �������Ǹ� REFERENCES ���������̺��(�������÷���)
        
        - ���̺��� ���
        CONSTRAINT �������Ǹ� FOREIGN KEY (�÷���) REFERENCES ���������̺��(�������÷���)
        
        �� ��� ��� CONSTRAINT �������Ǹ� �κ��� ���� ���� (��, SYS.C~ �� �̸��� ����)
                          ������ �÷��� ���� ������ (��, �⺻������ �ش� ������ ���̺��� PRIMARY KEY �÷��� ������ �÷������� ����)
*/

-- �ܷ�Ű ������ ���� ���̺�

-- �θ� ���̺�
-- ȸ�� ��޿� ���� �����͸� ������ ���̺�
-- ���̺�� : MEM_GRADE
-- ������ �����͵� : ��� �ڵ�, ��޸�
CREATE TABLE MEM_GRADE( 
        GRADE_CODE CHAR(2) PRIMARY KEY,
        GRADE_NAME VARCHAR2(20) NOT NULL
);

SELECT * FROM MEM_GRADE;

INSERT INTO MEM_GRADE VALUES ('G1', '�Ϲ� ȸ��');
INSERT INTO MEM_GRADE VALUES ('G2', '��� ȸ��');
INSERT INTO MEM_GRADE VALUES ('G3', 'Ư�� ȸ��');

-- �ڽ����̺�
-- ȸ�����̺� VER.6
CREATE TABLE MEM (
        MEM_NO NUMBER PRIMARY KEY,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GRADE_ID CHAR(2) REFERENCES MEM_GRADE(GRADE_CODE), -- �÷����� ������� �ܷ�Ű ����
        GENDER CHAR(3) CHECK (GENDER IN ('��', '��')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE
        -- ���̺��� ���
        -- FOREIGN KEY (GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE)
);

-- ������ ����
INSERT INTO MEM VALUES (1, 'user01', 'pass01', 'ȫ�浿', 'G1', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (2, 'user02', 'pass02', '�踻��', 'G2', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (3, 'user03', 'pass03', '�ڸ���', 'G3', NULL, NULL, NULL, DEFAULT);

-- �ܷ�Ű�� �θ����̺� ���� �� ����
INSERT INTO MEM VALUES (4, 'user04', 'pass04', '����', 'G4', NULL, NULL, NULL, DEFAULT);
-- ORA-02291: integrity constraint (DDL.SYS_C007084) violated - parent key not found
-->> PARENT KEY NOT FOUND
-->> �θ� ���̺�(MEM_GRADE)�� GRADE_CODE �÷��� �ش� ��(G4)�� ��� ���� ������ �߻�

-- �ܷ�Ű�� NULL �� ����
INSERT INTO MEM VALUES (4, 'user04', 'pass04', '����', NULL, NULL, NULL, NULL, DEFAULT);
-- GRADE_CODE �÷��� NULL ���� �� ��
-->> �ܷ�Ű ���� ������ �ɷ��ִ� �÷����� �⺻������ NULL���� �� �� �ִ�.

-- �׽�Ʈ�� ���� ��� ���� �߰�
INSERT INTO MEM_GRADE VALUES ('G4', '���̾� ȸ��');

SELECT * FROM MEM_GRADE;

-- ���� ���� ) �θ����̺� (MEM_GRADE) ���� �����Ͱ��� �����ȴٸ�?
-- MEM_GRADE ���̺�κ��� GRADE_CODE �÷����� G1�� ������ ����
-- DELETE FROM ���̺�� WHERE �����ϰ����ϴ��࿡��������
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 'G1';
-- ORA-02292: integrity constraint (DDL.SYS_C007084) violated - child record found
-->> CHILD RECORD FOUND
-->> �ڽ� ���̺� (MEM) ���� �̹� G1 ���� ����ϰ� �־ ������ ���� ���� ���� �߻�

--> �ڽ� ���̺��� ����ϰ� �ִ� ���� ���� ��� ������ ���� �ʴ� '���� ���� �ɼ�'�� �⺻������ �ɷ�����
--> ���� �ܷ�Ű �������� �ο� �� ���� ���� �ɼ��� �ο����� �ʾ������� �ұ��ϰ� �ɼ��� �ɷ�����

-- MEM_GRADE ���̺�κ��� GRADE_CODE ���� G4�� ������ �����
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 'G4';
-- �ڽ� ���̺��� ����ϴ� ���� ��� DELETE�� ���������� ��.

SELECT * FROM MEM_GRADE;

-------------------------------------------------------------------------------------------------------------
/*
        * �ڽ� ���̺� ���� �� (�ܷ�Ű �������� �ο� ��)
        �θ����̺��� �����Ͱ� �����Ǿ��� ��� �ڽ����̺��� �ش�Ǵ� �����͸� ��� ó���� ����
        �ɼ����� �����ص� �� �ִ�.
        
        * FOREIGN KEY ���� �ɼ�
        ���� �ɼ� ������ �������� ������ �⺻������ ���� ���� �ɼ��� �ɸ�
        1) ON DELETE RESTRICTED : ���� ���� �ɼ� (���� �� �⺻��)
        2) ON DELETE SET NULL : �θ����� ���� �� �ش� �����͸� ����ϴ� �ڽĵ����͸� NULL �� �����ϴ� �ɼ�
        3) ON DELETE CASCADE : �θ����� ���� �� �ش� �����͸� ����ϴ� �ڽĵ����͸� ���� �����ϴ� �ɼ�
*/

-- ������ MEM ���̺� ����
DROP TABLE MEM;

-- �ڽ����̺�
-- ȸ�� ���̺� VER.7
-- ����� �ܷ�Ű�� �����ϵ�, ���� �ɼ��� ON DELETE SET NULL ��
CREATE TABLE MEM (
        MEM_NO NUMBER PRIMARY KEY,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GRADE_ID CHAR(2),
        GENDER CHAR(3) CHECK (GENDER IN ('��', '��')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE,
        FOREIGN KEY (GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE SET NULL
);

-- ������ ����
INSERT INTO MEM VALUES (1, 'user01', 'pass01', 'ȫ�浿', 'G1', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (2, 'user02', 'pass02', '�踻��', 'G2', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (3, 'user03', 'pass03', '�ڸ���', 'G1', NULL, NULL, NULL, DEFAULT);

-- �θ� ���̺� (MEM_GRADE)�κ��� GRADE_CODE �÷����� G1�� ������ ����
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 'G1';
-- ���� �߻� ���� ������ �� ��

SELECT * FROM MEM;
-- �ڽ����̺�(MEM)���� GRADE_ID �÷����� G1�̾��� �κ��� ��� NULL�� �ٲ�

-- ������ MEM ���̺� ����
DROP TABLE MEM;


-- ȸ�� ���̺� VER.8
-- ����� �ܷ�Ű�� �����ϵ�, ���� �ɼ��� ON DELETE CASCADE ��
CREATE TABLE MEM (
        MEM_NO NUMBER PRIMARY KEY,
        MEM_ID VARCHAR2(20) NOT NULL UNIQUE,
        MEM_PWD VARCHAR2(20) NOT NULL,
        MEM_NAME VARCHAR2(20) NOT NULL,
        GRADE_ID CHAR(2),
        GENDER CHAR(3) CHECK (GENDER IN ('��', '��')),
        PHONE CHAR(13),
        EMAIL VARCHAR2(30),
        MEM_DATE DATE DEFAULT SYSDATE,
        FOREIGN KEY (GRADE_ID) REFERENCES MEM_GRADE(GRADE_CODE) ON DELETE CASCADE
);

INSERT INTO MEM VALUES (1, 'user01', 'pass01', 'ȫ�浿', 'G3', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (2, 'user02', 'pass02', '�踻��', 'G2', NULL, NULL, NULL, DEFAULT);
INSERT INTO MEM VALUES (3, 'user03', 'pass03', '�ڸ���', 'G3', NULL, NULL, NULL, DEFAULT);

SELECT * FROM MEM;

-- �θ� ���̺� (MEM_GRADE)�κ��� GRADE_CODE �÷����� G3�� ������ ����
DELETE FROM MEM_GRADE WHERE GRADE_CODE = 'G3';
-- ���� ���� �� ������

SELECT * FROM MEM_GRADE;

SELECT * FROM MEM;
-- �ڽ����̺� (MEM)�� GRADE_ID �÷����� G3�� ��� ����� �Բ� ������ ���� Ȯ��

-------------------------------------------------------------------------------------------------------------
-- ȸ�����̺��� MEM �κ��� ��ü ȸ���� ȸ����ȣ, ���̵�, ��й�ȣ, �̸�, ��� ���̵� ��ȸ
SELECT MEM_NO, MEM_ID, MEM_PWD, MEM_NAME, GRADE_ID
FROM MEM;

-- ȸ�����̺�κ��� ��ü ȸ���� ȸ����ȣ, ���̵�, ��й�ȣ, �̸�, ��޸� ��ȸ (JOIN)
-->> ����Ŭ ���� ����
SELECT MEM_NO, MEM_ID, MEM_PWD, MEM_NAME, GRADE_NAME
FROM MEM, MEM_GRADE
WHERE GRADE_ID = GRADE_CODE(+);

-->> ANSI ����
SELECT MEM_NO, MEM_ID, MEM_PWD, MEM_NAME, GRADE_NAME
FROM MEM
LEFT JOIN MEM_GRADE ON (GRADE_ID = GRADE_CODE);

/*
        ���� �ܷ�Ű ���������� �ɷ����� �ʾƵ� JOIN ������
        �ٸ�, �� �÷��� ������ �ǹ��� �����͸� ����ִٸ� ���� ��Ī�ؼ� JOIN ����
*/

-- ���±��� �۾��� ���� Ȯ����Ű��
COMMIT;

-------------------------------------------------------------------------------------------------------------
/*
        --- ���⼭���ʹ� KH �������� ���� ---
        
        * SUBQUERY �� �̿��� ���̺� ���� (���̺��� �����ϴ� ����)
        
        �������� : ���� SQL�� (SELECT, CREATE, INSERT, ... )�� �������� �ϴ� SELECT ��
        
        [ ǥ���� ]
        CREATE TABLE ���̺��
        AS (��������);
        
        => �ش� ���������� ������ ����� ������ ���̺��� �����ϰڴ�.
*/

-- EMPLOYEE ���̺��� ������ ���ο� ���̺� ���� (EMPLOYEE_COPY)
CREATE TABLE EMPLOYEE_COPY
AS (SELECT * 
       FROM EMPLOYEE);
-->> �÷���, ��ȸ ����� �����͵�, �������� �߿��� NOT NULL �� ������
-->> ������ �������ǵ��� ������ �� ��


-- EMPLOYEE ���̺� �ִ� �÷� ������ �������� ���� (���빰 ����) (EMPLOYEE_COPY2)
CREATE TABLE EMPLOYEE_COPY2
AS (SELECT *
       FROM EMPLOYEE
       WHERE 1=0); -- �׻� ������ ���ǹ��� �������� ��

SELECT * FROM EMPLOYEE_COPY2;


-- ��ü ����� �� �޿��� 300���� �̻��� ������� ���, �̸�, �μ��ڵ�, �޿� �÷� ���� (EMPLOYEE_COPY3)
-- 1) �������� �κ� ����
SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= 3000000;

-- 2) CREATE �������� ���̺� ����
CREATE TABLE EMPLOYEE_COPY3
AS (SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
       FROM EMPLOYEE
       WHERE SALARY >= 3000000);

SELECT * FROM EMPLOYEE_COPY3;


-- ��ü ����� ���, �����, �޿�, ���� ��ȸ ����� ���̺�� ���� (EMPLOYEE_COPY4)
-- 1) �������� �κ� ����
SELECT EMP_ID, EMP_NAME, SALARY, SALARY * 12
FROM EMPLOYEE;

-- 2) CREATE �������� ���̺� ����
CREATE TABLE EMPLOYEE_COPY4
AS (SELECT EMP_ID, EMP_NAME, SALARY, SALARY * 12
       FROM EMPLOYEE);
-- ORA-00998: must name this expression with a column alias
-->> ���������� SELECT ���� �������, �Ǵ� �Լ����� ����� ��� �ݵ�� ��Ī �ο��� �ؾ� �Ѵ�.

CREATE TABLE EMPLOYEE_COPY4
AS (SELECT EMP_ID, EMP_NAME, SALARY, SALARY * 12 "����"
       FROM EMPLOYEE);

SELECT * FROM EMPLOYEE_COPY4;

-------------------------------------------------------------------------------------------------------------
/*
        * ���̺��� �� ������ �� �ڴʰ� �������� �߰� (ALTER)
        ALTER TABLE ���̺�� + �������Ǻ� ����
        
        - PRIMARY KEY : ADD PRIMARY KEY (�÷���);
        - FOREIGN KEY : ADD FOREIGN KEY (�÷���) REFERENCES ���������̺�� (�������÷���);
                                    => ������ �÷��� ���� ���� (PRIMARY KEY�� ����)
        - UNIQUE : ADD UNIQUE (�÷���);
        - CHECK : ADD CHECK (�÷����� ������ �÷��� ���� ����);
        - NOT NULL : MODIFY �÷��� NOT NULL;
*/

-- EMPLOYEE_COPY ���̺� ���� PRIMARY KEY ���������� EMP_ID �÷��� �߰�
ALTER TABLE EMPLOYEE_COPY ADD PRIMARY KEY (EMP_ID);

-- EMPLOYEE ���̺� DEPT_CODE �÷��� �ܷ�Ű �������� �߰� (DEPARTMENT ���̺��� DEPT_ID ����)
ALTER TABLE EMPLOYEE ADD FOREIGN KEY (DEPT_CODE) REFERENCES DEPARTMENT (DEPT_ID);

-- EMPLOYEE ���̺� JOB_CODE �÷��� �ܷ�Ű ���� ���� �߰� (JOB ���̺��� JOB_CODE ����)
ALTER TABLE EMPLOYEE ADD FOREIGN KEY (JOB_CODE) REFERENCES JOB (JOB_CODE);
