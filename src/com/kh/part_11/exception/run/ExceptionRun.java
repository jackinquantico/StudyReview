package com.kh.part_11.exception.run;

import java.io.IOException;

import com.kh.part_11.exception.controller.A_UncheckedException;
import com.kh.part_11.exception.controller.B_CheckedException;


public class ExceptionRun {
	
	/*
	 * * ����(����) ����
	 * - �ý��� ���� : ��ǻ���� ���۵����� ���� �߻��ϴ� ����
	 *				=> �ҽ��ڵ�� �ذ��� �� �� (�ɰ��� ����)
	 * - ������ ���� : �ҽ��ڵ� ���� �������� ������ �߻��ϴ� ����
	 * 				=> �ҽ��ڵ� �������� �ذ� ���� (��Ŭ�������� ���� ���ٷ� �˷��ִ� ����)
	 * - ��Ÿ�� ���� : �ҽ��ڵ� �����δ� ������ ������, ���α׷� ���� ���� �߻��ϴ� ����
	 * 				=> ������� �߸��� ���� �ְ�, �����ڰ� ���� ������ ��츦 ����� ó�� �� �ص� �߸��� ���� ����.
	 * - �� ���� : �ҽ��ڵ� ���� �������� ������ ���� �������� ���� ������ ���� ������
	 * 			    ���� § ���α׷� �ǵ��� �ݴ�� �۵��ϴ� ����
	 * 
	 * => �ý��� ������ ������ ������ ����, ��Ÿ�� ����, �� ������ ���� ���� �� �ɰ��� �͵��� ������ �۾�
	 * 	    �̷� �͵��� "����" ��� �Ѵ�. (Exception)
	 * 
	 * => �̷��� ���ܵ��� �߻����� ��쿡 ����ؼ� �̸� ó���ϴ� ����� �����صδ� ���� "����ó��"��� �Ѵ�.
	 * 
	 * ����ó���� �ϴ� ���� : ���� �߻� �� ���α׷��� ������ ���Ḧ ���� ����
	 * 
	 * ���� ó�� ���
	 * 1. try - catch�� �̿�
	 * 2. throws �̿� (���ѱ��, �����ϱ�)
	 */

	// main �޼ҵ忡�� throws�� ���� ó���� ���ѱ�� �Ǹ� JVM�� ���� ���� ó���� ��.
	// => JVM�� ��� ���ܸ� ó������ �� �𸣱� ������ try-catch���� �̿��ؼ� ����ó���� ������ ���� ���� ������.
	public static void main(String[] args) throws IOException {
		
		A_UncheckedException a = new A_UncheckedException();
		
		// a.method1();
		// a.method2();
		// a.method3();
		
		B_CheckedException b = new B_CheckedException();
		
		/*
		try {
			b.method1();
		} catch (IOException e) {
			
			System.out.println("���� �߻�");
		}
		*/
		
		b.method1();
	}

}
