package com.kh.part_12.stream.byteStream.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//DAO : Data Access Object 
//�����Ͱ� �����Ǿ� �ִ� ������ ���� �����ؼ� �����͸� ������ϴ� �뵵�� Ŭ����
public class FileByteDao {

	// ���α׷� ---> �ܺθ�ü(File) 
	// ��� : ���α׷� ���� �����͸� ���Ϸ� �������� (��, ���Ͽ� ����ϰڴ�, �����ϰڴ�)
	public void fileSave() {
		
		// FileOutputStream ��ü : ���Ϸ� �����͸� 1byte ������ ����ϴ� ��Ʈ��
	
		// 1. FileOutputStream ��ü �����ϱ� : ������� �����
		// => �ش� ���ϰ� ���� ����Ǵ� ��θ� ����ڴ�.
		// => ���ϸ��� �Ű������� ������ ȣ��
		// FileNotFoundException �߻��ҹ��� ����
		// FileOutputStream fout = new FileOutputStream("a_byte.txt");
		// �ش� ������ �������� �ʴ´ٸ� �ش� ������ �����Ǹ鼭 ���� ��ΰ� �������
		// 			�����ϴ� �����̸� ���� ��θ� �ٷ� �������.
		
		// 1-1. FileOutputStream �������� ���� �� null�� �ʱ�ȭ
		FileOutputStream fout = null;
		
		try {
			// 1-2. FileOutputStream ��ü ���� �� �Ҵ�
			fout = new FileOutputStream("a_byte.txt" /* , false */ );
			// fout = new FileOutputStream("a_byte.txt", true);
			// => ������ �ش� ������ ���� ���, ��������� Ư¡�� ����
			
			// 2. ������η� �����͸� ��� : write() �޼ҵ� ���
			// => 1byte ������ ���� ���� : -128 ~ 127������ ����
			// 	  (��, ���Ͽ� ��ϵǱ⸦ �ش� ������ ������ ���ڰ� ��ϵ� : �ƽ�Ű�ڵ�)
			fout.write(97); // intŸ�� 97�� 'a' �� ��ȯ��.	 // 'a' ���
			fout.write('b'); // ���ڿ� int�� �ڵ�����ȯ�ǹǷ� ���� // 'b' ���
			// fout.write('��'); // '��'�� 2byte�̱� ������ �ѱ��� ������ @ �� �����
								 // �ѱ��� ����Ʈ��Ʈ������ ������ �� ����. ����Ʈ��Ʈ�����δ� ��������
			
			byte[] bArr = {99, 100, 101}; 
			fout.write(bArr); // �ƽ�Ű�ڵ�ǥ�� ���� �ڵ�����ȯ�Ǿ� // 'c', 'd', 'e' ���
			
			fout.write(bArr, 1, 2); // bArr�� 1��° �ε������� 2���� ��������
									// 100, 101�� �ڵ�����ȯ�Ǿ� // 'd', 'e' ���
			
			// 3. ��Ʈ���� �� ����� �ڿ��� �ڿ��� �ݳ������ �Ѵ�.
			// ��, ������θ� ���ڴ�.
			// fout.close(); // ���� �ڵ忡�� ���ܰ� �߻����� ���, �� �ڵ�� ������ �� �� ���� ����.
			
		} catch (FileNotFoundException e) { // �������� �ʴ� ��θ� �������� ��
			e.printStackTrace();
		} catch (IOException e) { // ����� ��Ȳ���� ��� �������� �߻����� ��
			e.printStackTrace();
		} finally { // � ���ܰ� �߻��ϴ���, �� �ϴ��� ���� �ݵ�� ������ ������ �ۼ��ϴ� ��
			
			// 3. ��Ʈ���� �� �̿��� �ڿ��� �ݵ�� �ڿ��� �ݳ��ؾ� �ϱ� ������
			// � ���ܰ� �߻��ϴ������� �ݵ�� �����ؾ� �ϴ� ������ �ۼ��ϴ� finally�� �ۼ�
			// close() �� IOException�� �߻���Ű�� ������ try-catch������ ������� �Ѵ�.
			try {
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	} // fileSave
	
	// ���α׷� <--- �ܺθ�ü(File)
	// �Է� : ���Ϸκ��� �����͸� �о����, �ҷ�����
	public void fileRead() {
		
		// FileInputStream : ���Ϸκ��� �����͸� 1byte ������ �Է¹޴� ��Ʈ��
		// 1_1. FileInputStream �������� ���� �� null�� �ʱ�ȭ
		FileInputStream fin = null;
		
		try {
			// 1_2. FileInutStream ��ü �����ϱ� ==������� �����
			fin = new FileInputStream("a_byte.txt");
			// �Է¹��� ������ �ݵ�� �����ϴ� ���ϸ����� ����
			
			// 2. ���Ϸκ��� �о���̱� == �Է¹ޱ� : read()
			// => ��, 1byte ������ �ϳ��� �о��
			// ��½ÿ��� ���� -> ����
			// �Է½ÿ��� ���� �� int������ �ҷ��鿩���� 
			// 				(char) ���� ����ȯ�ؼ� ���� ���·� �� �� ����
			/*
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			System.out.println(fin.read());
			// ���� ���� ������ ���ڼ��� �Ѿ�� -1 �� ���ϵ�.
			*/
			
			/* 2. �ݺ����� ����ؼ� ���� �о���̱�
			// ����� ������ ����. �ǳʶپ���. �� �� �ݺ��� 2���� ���� �Ҹ��.
			while(fin.read() != -1) { // �о���� ������� -1�� �ƴ� ���ȸ�
				System.out.println(fin.read());
			}
			*/
			
			/* �ذ��� 1. ���ѹݺ����� �Ź� ���ǰ˻��ϱ�
			while (true) {
				int value = fin.read(); // �� �� �� �о�� ���� value ������ ��Ƽ�
				
				if (value == -1) { // ���ǰ˻��ؼ�
					break;
				}
				
				System.out.println(value); // ���ǿ� ������ ���
			}
			*/
			
			// �ذ��� 2. ���ǽ� ���ο� ���� ���� ������ Ȱ���ϴ� ���
			int value = 0;
			while ((value = fin.read()) != -1) {
				System.out.println(value);
			}
				
		} catch (FileNotFoundException e) { // �ڽ� catch ���� �� ����
			e.printStackTrace();
		} catch (IOException e) { // �θ� catch ���� ���� ���߿�
			e.printStackTrace();
		} finally {
			
			// 3. ������� ���� �ڿ� �ݳ��ϱ� : finally ���� �ۼ�
			try {
				fin.close();		  // IOException�� �߻��ҹ��� ����
			} catch (IOException e) { // try-catch������ ���� ó��
				e.printStackTrace();
			}
		}
		
	} // fileRead
}
