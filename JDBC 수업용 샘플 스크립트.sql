-- ȸ������ ���α׷� �ۼ��� ���� ȸ�� ���̺� ����

DROP TABLE MEMBER;

CREATE TABLE MEMBER (
        USERNO NUMBER PRIMARY KEY, -- ȸ����ȣ
        USERID VARCHAR2(15) NOT NULL UNIQUE, -- ȸ�� ���̵�
        USERPWD VARCHAR2(20) NOT NULL, -- ȸ�� ��й�ȣ
        USERNAME VARCHAR2(20) NOT NULL, -- ȸ�� �̸�
        GENDER CHAR(1) CHECK (GENDER IN ('M', 'F')), -- ����
        AGE NUMBER, -- ����
        EMAIL VARCHAR2(30), -- �̸���
        PHONE CHAR(11), -- �ڵ��� ��ȣ
        ADDRESS VARCHAR2(100), -- �� �ּ�
        HOBBY VARCHAR2(50), -- ���
        ENROLLDATE DATE DEFAULT SYSDATE NOT NULL
);

-- ȸ����ȣ ä���� ���� ������ ����

DROP SEQUENCE SEQ_USERNO;

CREATE SEQUENCE SEQ_USERNO
NOCACHE;

-- �׽�Ʈ�� ������(���̵�����) �߰�
-- 2���� ȸ������ �߰�
INSERT INTO MEMBER
VALUES (SEQ_USERNO.NEXTVAL
              , 'admin'
              , '1234'
              , '������'
              , 'M'
              , 45
              , 'admin@naver.com'
              , '01012345678'
              , '����� ������'
              , '�����ڱ�'
              , '2021/01/25');
              
INSERT INTO MEMBER
VALUES (SEQ_USERNO.NEXTVAL
              , 'user01'
              , 'pass01'
              , 'ȫ���'
              , 'F'
              , 23
              , NULL
              , '01067891234'
              , NULL
              , '��ȭ����'
              , '2021/07/13');
              
-- ���� DB�� �ݿ��ϱ� ���� Ŀ��
COMMIT;


-- ��ȸ
SELECT * FROM MEMBER;







