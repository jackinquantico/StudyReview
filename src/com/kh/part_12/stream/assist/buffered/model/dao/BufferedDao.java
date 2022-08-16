package com.kh.part_12.stream.assist.buffered.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedDao {
	
	// BufferedWriter (��¿�) / BufferedReader (�Է¿�)

	// ���α׷� ---> �ܺ� ��ü (����)
	public void fileSave() {
		
		// FileWriter : 2����Ʈ ������ ���Ϸ� �����͸� �������� ��� ��Ʈ��
		// + 
		// BufferedWriter : ���� ������ �������ִ� ���� ��Ʈ�� (�ӵ� ���)
		
		/*
		// 1_1. ��ݽ�Ʈ�� ���� ���� ���� �� null �ʱ�ȭ
		FileWriter fw = null;
		// 2_1. ������Ʈ�� ���� ���� ���� �� null �ʱ�ȭ
		BufferedWriter bw = null;
		
		try {
			// 1_2. ��� ��Ʈ�� ��ü ���� (���� ������� �����)
			fw = new FileWriter("c_buffer.txt");
			
			// 2. ���� ��Ʈ�� ��ü ����
			bw = new BufferedWriter(fw);
			
			// 3. ��� : BufferedWriter ��ü���� �����ϴ� write() �޼ҵ� ���
			// fw.write(); // FileWriter ��ü���� �����ϴ� write() �޼ҵ带 ����ϸ� ���� ��� ����� �� ����.
			bw.write("�����ٶ󸶹ٻ�� ");
			bw.newLine(); // ���� �޼ҵ�
			bw.write("�ȳ��ϼ���\n");
			bw.write("����������.");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 4. �ڿ� �ݳ�
			// ������ �� : ��� ��Ʈ���� ���� ��Ʈ���� �ݳ��� ��
			// 			��ü�� ������ ������ �������� �ݳ�
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		*/
		
		// 1_1. ��ݽ�Ʈ�� ���� ���� ���� �� null �ʱ�ȭ
		// FileWriter fw = null;
		// 2_1. ������Ʈ�� ���� ���� ���� �� null �ʱ�ȭ
		BufferedWriter bw = null;
		
		try {			
			// �ڵ� �� �ٷ� �ٿ��� ǥ��
			// 2. ���� ��Ʈ�� ��ü ���� + ��� ��Ʈ�� ��ü ����
			bw = new BufferedWriter(new FileWriter("c_buffer.txt"));
			
			// 3. ��� : BufferedWriter ��ü���� �����ϴ� write() �޼ҵ� ���
			// fw.write(); // FileWriter ��ü���� �����ϴ� write() �޼ҵ带 ����ϸ� ���� ��� ����� �� ����.
			bw.write("�����ٶ󸶹ٻ�� ");
			bw.newLine(); // ���� �޼ҵ�
			bw.write("�ȳ��ϼ���\n");
			bw.write("����������.");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 4. �ڿ� �ݳ�
			// �ڵ带 �� �ٷ� ���̸� ��� ��Ʈ�� �ݳ� ������ ���� ����
			try {
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	} // fileSave
	
	// ���α׷� <--- �ܺ� ��ü (����)
	public void fileRead() {
		
		// FileReader : ���ϰ� ���������� �����ؼ� �� ���� 2byte ������ ������ �Է��� �� �ִ� ��� ��Ʈ��
		// +
		// BufferedReader : ���� ������ �������ִ� ���� ��Ʈ�� (�ӵ� ���)
		
		/*
		// 1_1. ���� ���� ���� �� null ������ �ʱ�ȭ
		BufferedReader br = null;
		
		try {
			// 1_2. ��ü ���� == ���� ��� �����
			br = new BufferedReader(new FileReader("c_buffer.txt"));
			
			// 2. �Է�
			// System.out.println(br.read()); // ���� �ϳ� ������ �о����
			// System.out.println(br.readLine()); // �� �� ������ �о����
			// System.out.println(br.readLine());
			// System.out.println(br.readLine());
			// ������ ���� ������ null�� ��ȯ
			
			// �ݺ��� Ȱ��
			String input = ""; // null�̵� ���ڿ��̵� ��� ����.
			while ((input = br.readLine()) != null) {
				System.out.println(input);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 3. ���� ��� ���� == �ڿ� �ݳ�
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		*/
		
		// try with resource ���� (jdk7 �������� ����)
		/* [ ǥ����  ]
		 * try (���߿� �ݳ��ؾ��ϴ� ��ü ���� ����) {
		 * 		���ܰ� �߻��ҹ��� ����;
		 * } catch (����Ŭ������ e) {
		 * 		�ش� ���� �߻��� ������ ����;
		 * }
		 * 
		 * => ��Ʈ�� ��ü ���������� try()�� �ۼ��ϰ� �Ǹ�
		 *    ��Ʈ�� ��ü ���� �� �ش� try �� ������ ����� ��
		 *    �˾Ƽ� �ڿ� �ݳ��� �ȴ�.
		 */
		
		try (BufferedReader br = new BufferedReader(new FileReader("c_buffer.txt"))) {
			
			// �ݺ��� Ȱ��
			String value = null;
			while ((value = br.readLine()) != null) {
				System.out.println(value);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	} // fileRead
}
