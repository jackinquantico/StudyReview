package com.kh.part_12.stream.assist.object.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kh.part_12.stream.assist.object.model.vo.Phone;

//��ü ���� �����
public class ObjectDao {
	
	// ���α׷� ---> �ܺ� ��ü (����)
	// ��� : ���Ϸ� ����
	public void fileSave() {
		
		// FileOutputStream : ���Ͽ� �����͸� 1 byte ������ ����� �� �ִ� ��� ��Ʈ��
		// + 
		// ObjectOutputStream : ��ü ������ ����� �� �ִ� ���� ��Ʈ��
		
		// �׽�Ʈ�� ��ü ����
		Phone ph = new Phone("������", 1300000);
		
		// 0. ��Ʈ�� ���� ���� ���� �� null�� �ʱ�ȭ
		ObjectOutputStream oos = null;
		
		try {
			// 1. ��Ʈ�� ��ü ���� == ���� ��� �����
			oos = new ObjectOutputStream(new FileOutputStream("phone.txt"));
			
			// 2. ���Ϸ� ��� : ��ü ������ ��½�
			// ObjectOutputStream Ŭ�������� �����ϴ� writeObject() �޼ҵ� ���
			oos.writeObject(ph);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			// 3. �ڿ� �ݳ��ϱ� == ���� ��� ���� => �ʼ�
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		
		
	} // fileSave

	// ���α׷� <--- �ܺ� ��ü (����)
	// �Է� : ���� �б�
	public void fileRead() {
		
		// FileInputStream : ���Ϸκ��� �����͸� 1byte ������ �о���̱� ���� ���Ǵ� ��� ��Ʈ��
		// +
		// ObjectInputStream : ��ü ������ �Է¹ޱ� ���� ���Ǵ� ���� ��Ʈ��
		
		/*
		// 0. ��Ʈ�� ���� ���� ���� �� null�� �ʱ�ȭ
		ObjectInputStream ois = null;
		
		try {
			// 1. ��Ʈ�� ��ü ���� == ���� ��� �����
			ois = new ObjectInputStream(new FileInputStream("phone.txt"));	
			
			// 2. �Է� : ���� �о���̱�
			// Object obj = ois.readObject();
			Phone ph = (Phone)ois.readObject(); // �������� ���� �ٿ�ĳ����(��������ȯ)
			System.out.println(ph);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			
			// 3. �ڿ� �ݳ��ϱ� == ������� ���� => �ʼ�
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		*/
		
		// try with resource ���� ����
		// ������Ʈ�� ��ü ������ ��� ��Ʈ�� ��ü ���� ���ÿ� ���� -> �ݳ��� �ʿ� ����
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("phone.txt"))) {
			
			// �Է� : ���� �о���̱�
			Phone ph = (Phone)ois.readObject();
			System.out.println(ph);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	} // fileRead
}
