package com.kh.part_12.stream.byteStream.run;

import com.kh.part_12.stream.byteStream.model.dao.FileByteDao;

//����Ʈ ��� ��Ʈ��
public class ByteRun {
	
	/*
	 * * ����Ʈ ��� ��Ʈ��
	 * ����Ʈ ��Ʈ�� : 1byte �����θ� ����� �� �� �ִ� ���� ��� (XXXInputStream / XXXOutputStream)
	 * ��� ��Ʈ�� : �ܺ� ��ü�� ���������� ����Ǵ� ���� ���
	 * 
	 * => �ܺ� ��ü�� �����ϰ� �� �ܺθ�ü�� ���������� ����Ǵ� 1byte ������ ��θ� �����.
	 * XXXInputStream : XXX��ü�κ��� �����͸� �Է¹޴� ���
	 * 					(�ܺ� ��ü�κ��� �����͸� ������ ���ڴ�. �о���̰ڴ�.)
	 * XXXOutputStream : XXX��ü�κ��� �����͸� ����ϴ� ���
	 * 					 (�ܺθ�ü�� �����͸� �������ڴ�, ���ڴ�)
	 */

	public static void main(String[] args) {
		
		FileByteDao fb = new FileByteDao();
		
		// fb.fileSave();
		// ���� ��θ� ���� �������� �ʾұ� ������ ��� ��ο� ����
		// ���� �۾� ���� 12_IO ������Ʈ ���� ���� ������.
		
		fb.fileRead();
	}

}
