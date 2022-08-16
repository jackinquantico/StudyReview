package com.kh.part_12.stream.charStream.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//DAO : Data Access Object
public class FileCharDao {

	// ���α׷� ---> �ܺθ�ü (File)
	// ���
	public void fileSave() {
		
		// FileWriter : ���Ϸ� �����͸� 2byte ������ ����ϴ� ��Ʈ��
		
		// 1_1. ��Ʈ�� �������� ���� �� null �� �ʱ�ȭ
		FileWriter fw = null;
		
		try {
			// 1_2. ��Ʈ�� ��ü ���� == ������� : ���ϸ��� ������ �Ű������� ����
			fw = new FileWriter("b_char.txt");
			// ����� ��� ���� ���ϸ��� �����ϴ��� ���� ���� �� ���� ��ΰ� �������.
			
			// 2. ��� == ������ �������� : write() �޼ҵ� ���
			fw.write("��! IO ��մ�..��"); // ���������� ���� �� ���� ��� �̵�
			fw.write('A');
			fw.write(97);
			fw.write("\n");
			
			char[] cArr = {'k','i','w','i'};
			fw.write(cArr);
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			
			// 3. ������� ���� �ڿ� �ݳ�
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	// ���α׷� <--- �ܺθ�ü (File)
	// �Է�
	public void fileRead() {
		
		// FireReader : ���Ϸκ��� �����͸� 2byte ������ �Է¹޴� ��Ʈ��
		
		// 1_1. ��Ʈ�� �������� ���� �� null �� �ʱ�ȭ
		FileReader fr = null;
		
		try {
			// 1_2. ��Ʈ�� ��ü ���� == ������� : ���ϸ��� ������ �Ű������� ����
			fr = new FileReader("b_char.txt");
			// �Է� ���������� �ݵ�� �����ϴ� ���� ��η� �����ؾ� �Ѵ�.
			
			// 2. �Է� == ������ �б� : read() �޼ҵ� ���
			// ���������� ���� �ϳ��� ��� �����ϴ� ��
			//System.out.println(fr.read());
			// ���� ��� ��Ʈ���� ���������� ������ ���� ������ �� -1�� ��ȯ��
			
			// �ݺ��� Ȱ��
			int value = 0;
			while ((value = fr.read()) != -1) {
				System.out.print((char)value);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 3. ������� ���� �ڿ� �ݳ� : close() �޼ҵ� ���
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
