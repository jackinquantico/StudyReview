package com.kh.part_12.stream.charStream.run;

import com.kh.part_12.stream.charStream.model.dao.FileCharDao;

public class CharRun {
	
	/*
	 * * ���� ��� ��Ʈ��
	 * ���� ��Ʈ�� : �� ���� 2byte ������ �����͸� ������� �� �ִ� ��� (XXXReader / XXXWriter)
	 * ��� ��Ʈ�� : �ܺ� ��ü�� ���������� ����Ǵ� ���� ���
	 * 
	 * => �ܺ� ��ü�� �����ϰ� �� �ܺ� ��ü�� ���������� ����Ǵ� 2byte ������ ��θ� ����ڴ�.
	 */

	public static void main(String[] args) {
		
		FileCharDao fc = new FileCharDao();
		
		// fc.fileSave();
		
		fc.fileRead();

	}
}
