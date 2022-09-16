-- BOARD ���̺� ����
DROP TABLE BOARD;

CREATE TABLE BOARD(
    BNO NUMBER PRIMARY KEY, -- �Խñ� ��ȣ -- private int bno
    TITLE VARCHAR2(50) NOT NULL, -- �Խñ� ���� -- private String title
    CONTENT VARCHAR2(500) NOT NULL, -- �Խñ� ���� -- private String content
    CREATE_DATE DATE DEFAULT SYSDATE, -- �Խñ� �ۼ��ð� -- private Date createDate
    WRITER NUMBER, -- private String writer  -- ȸ����ȣ / ���̵� ��� �� �� �޾ƿ� �� ����.
    DELETE_YN CHAR(2) DEFAULT 'N', -- ���� ���� -- private String deleteYN -- ��ü ��ȸ �ÿ� DEFAULT = 'N' �� �Խñ۸� ��ȸ�ǵ���
    FOREIGN KEY (WRITER) REFERENCES MEMBER(USERNO), -- MEMBER ���̺��� ȸ����ȣ�� �ܷ�Ű�� �ɾ��
    CHECK(DELETE_YN IN('Y','N'))
);

-- BOARD ���̺��� PK�� ���� ������
DROP SEQUENCE SEQ_BOARD;

CREATE SEQUENCE SEQ_BOARD;

-- �׽�Ʈ ������ ����
INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, '�Խ��� ���񽺸� �����մϴ�^^', '���� �̿� �ٶ��ϴ�~!', '21/01/27', 1, DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, 'JDBC �������', '����ּ���', '21/09/05', 2, DEFAULT);

INSERT INTO BOARD
VALUES(SEQ_BOARD.NEXTVAL, '�Խñ�2', '�Խñ�2 ������ ����ü �����', '21/09/05', 2, DEFAULT);

COMMIT;


SELECT * FROM BOARD;

-- �Խñ� ���� (�߰�) / �� ��ü ��ȸ (SELECTALL) / �ۼ��ھ��̵�� �˻� / �Խñ��������� �˻� / �Խñ� ����ȸ / �Խñ� ���� / �Խñ� ����(UPDATE �������� Y�� �ٲٱ�)

SELECT BNO "�Խñ۹�ȣ", TITLE "����", CONTENT "����", CREATE_DATE "�ۼ���", USERID "�ۼ��� ���̵�"
FROM BOARD, MEMBER
WHERE WRITER = USERNO
ORDER BY BNO;

UPDATE BOARD
SET TITLE = 'TEST', CONTENT = 'TEST CONTENT'
WHERE BNO = 4;

UPDATE BOARD
SET DELETE_YN = 'Y'
WHERE BNO = ?;

SELECT BNO, TITLE, CONTENT, CREATE_DATE, USERID
FROM BOARD, MEMBER
WHERE WRITER = USERNO AND TITLE LIKE '%T%';

SELECT BNO, TITLE, CONTENT, CREATE_DATE, USERID
FROM BOARD, MEMBER
WHERE WRITER = USERNO
    AND WRITER = ?;

SELECT CONTENT
FROM BOARD
WHERE BNO = ?;

SELECT BNO, TITLE, CONTENT, CREATE_DATE, USERID
FROM BOARD, MEMBER
WHERE WRITER = USERNO
     AND EXTRACT(DAY FROM CREATE_DATE) = ?;
    
-- TO_CHAR, ���� ����ؼ� ��¥ ����
SELECT BNO, TITLE, CONTENT, CREATE_DATE, USERID
FROM BOARD, MEMBER
WHERE WRITER = USERNO
     AND TO_CHAR(CREATE_DATE, 'YYYYMMDD') = '202109' || ?;
--  AND TO_CHAR(CREATE_dATE, 'YYYYMMDD') = '2021' || ? || ?; -- ��, �� �Է¹��� ��