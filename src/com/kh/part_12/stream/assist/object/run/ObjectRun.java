package com.kh.part_12.stream.assist.object.run;

import com.kh.part_12.stream.assist.object.model.dao.ObjectDao;
import com.kh.part_12.stream.assist.object.model.dao.ObjectsDao;

public class ObjectRun {

	/*
	 * * ������Ʈ��
	 * ��� ��Ʈ�������� ������ ����� �߰����ִ� ���� ������ ��Ʈ��
	 * ���� �ܵ����� ��ü ���� �Ұ�
	 * 
	 * ��ü ������ ����� �� �� �ִ� ����� �����ϴ� ���� ��Ʈ��
	 * => ObjectInputStream (1byte) / ObjectOutputStream (1byte)
	 */
	
	public static void main(String[] args) {
		
		ObjectDao od = new ObjectDao();
		
		// od.fileSave();
		// od.fileRead();
		
		ObjectsDao ods = new ObjectsDao();
		
		// ods.fileSave();
		ods.fileRead();
		
		// ��ĳ�ʷ� �Է¹��� ���ϸ��� �Ű������� �Ѱܼ� �����ϴ� ���
		// ��ĳ�ʷ� �Է¹��� ���ϸ� ������ִ� ���
	}

}
