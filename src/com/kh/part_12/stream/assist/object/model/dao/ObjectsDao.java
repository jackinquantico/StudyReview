package com.kh.part_12.stream.assist.object.model.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kh.part_12.stream.assist.object.model.vo.Phone;


public class ObjectsDao {
	
	// ���α׷� ---> �ܺθ�ü (����)
	// ���
	public void fileSave() {
		
		// FileOutputStream + ObjectOutputStream
		// 1 ����Ʈ¥�� ���� ���
		
		// �׽�Ʈ�� ��ü �迭 ����
		Phone[] arr = new Phone[3];
		
		// �׽�Ʈ ������ ���
		arr[0] = new Phone("������", 1300000);
		arr[1] = new Phone("������", 1500000);
		arr[2] = new Phone("�ø���", 2000000);
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("phones.txt"))) {
			
			// ObjectOutputStream ��ü���� �����ϴ� writeObject() �޼ҵ� ���
			// oos.writeObject(arr[0]);
			// oos.writeObject(arr[1]);
			// oos.writeObject(arr[2]);
			
			// �ݺ��� �̿��ؼ� ���Ϸ� ��������
			for (int i=0; i<arr.length; i++) {
				oos.writeObject(arr[i]);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	// ���α׷� <--- �ܺθ�ü (����)
	// �Է�
	public void fileRead() {
		
		// FileInputStream + ObjectInputStream
		// 1 ����Ʈ¥�� ���� ���
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("phones.txt"))) {
			
			// ObjectInputStream ��ü���� �����ϴ� readObject() �޼ҵ� ���
			// toString() �޼ҵ带 �������̵� �߱� ������ ���� ����ȯ���� �ʰ� �ٷ� ����غ��� = �������ε�
			// System.out.println(ois.readObject() /* .toString() ������ ���� */);
			// System.out.println(ois.readObject());
			// System.out.println(ois.readObject());
			// System.out.println(ois.readObject());
			// ������ ���� ������ �� EOFException ���ܸ� �߻� // End Of File �� ����
			
			// EOFException �� �߻��� �������� �ݺ� ������
			// => ���� �Ұ����� �����̱� ������ ��Ȯ�� ������ ���� �� ����.
			while (true) { // �ϴ� ���� �ݺ�
				System.out.println(ois.readObject());
			}
			// ���� �ݺ��Ǵ� ������ EOFException�� �߻��ϴ� ����		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {  // EOFException ĳġ ������� �̵�
			System.out.println("������ �� �о����ϴ�.");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("���α׷� ����");
	}
}
