package com.kh.part_14.arrayList.run;

import java.util.ArrayList;
import java.util.List;

import com.kh.part_14.arrayList.model.vo.Music;

public class ListRun {
	
	public static void main(String[] args) {
		
		// ������ �迭�� �� ��� : �迭 �� �غ�(�迭 ���� �� �Ҵ�)
		// �÷����� �� ��� : �÷����� �� �غ� (�ش� �÷��� ��ü ����)
		
		// ArrayList Ŭ������ ��ü ����
		// ArrayList list = new ArrayList(); // �⺻ �����ڷ� ������ ���, ���������� ũ��� 10¥���� �迭�� �������.
		
		ArrayList list = new ArrayList(3); // �Ű����� �����ڷ� ������ ���, �Ű������� �ѱ� ����ŭ�� ũ���� �迭�� �������
		
		System.out.println(list); // [] ��� : �ȿ� �ƹ��͵� ������� ���� ����
		
		// E ---> Element : ���׸�(E == Object) : �������� ���� ��� ��ü�� �� �޾��� �� �ִ� ����
		// 1. add(E e) : �ش� ����Ʈ�� ���� ���޵� e�� �߰������ִ� �޼ҵ�
		list.add(new Music("������Ÿ��", "����")); // arr[0] = new Music(); �� ���� ��
		list.add(new Music("�׽���!", "���ƾ�")); // arr[0+1] = new Music(); (������ �濡 ���� ��)
		list.add(new Music("���� �޴�..","�谡��")); // arr[2] = new Music();
		list.add("��"); // arr[3] = "��"; // ������ ������ �迭�� ũ�⸦ ��� : �迭������ �Ұ����ߴ� ��
		
		System.out.println(list);
		// ������ �����Ǹ鼭 �� �߰� (�� index�� ����ִ� �Ͱ� ����)
		// ũ�⿡ ������ ����
		// �پ��� Ÿ���� �� �߰� ����
		
		// 2. add(int index, E e) : index ��ġ�� ���޵� e�� �߰������ִ� �޼ҵ�
		// 							�ش� �ε��� ���������� �ڷ� �� ĭ�� �о��ִ� ���� ���� �˾Ƽ� ����
		list.add(1, new Music("���������", "�ںұ�"));
		
		System.out.println(list);
		
		// 3. set(int index, E e) : �ش� index ��ġ�� ���� ���޵� e�� ��������ִ� �޼ҵ�
		list.set(0, new Music("���ϸ�����", "������")); // arr[0] = new Music(���ο� ����); ���� ����� �� ��
		
		System.out.println(list);
		
		// 4. remove(int index) : �ش� index ��ġ�� ���� ���������ִ� �޼ҵ�
		//						    ���� �� ������ ������ ������ �� ĭ�� �����ִ� ���ҵ� ���� ����
		list.remove(1);
		
		System.out.println(list);
		
		// 5. size() : �ش� list�� ũ��(����ִ� ���� ����)�� ��ȯ�ϴ� �޼ҵ�
		System.out.println("list �� ��� ������ �� : "+list.size());
		System.out.println("list�� ������ �ε��� : "+(list.size()-1));
		
		// 6. get(int index) : ��ȯ�� E : �ش� index ��ġ�� �����͸� ��ȯ�ϴ� �޼ҵ�
		// Object object = list.get(0);
		// Music m = (Music)list.get(0);
		// System.out.println(m); // toString�� �������̵��Ǿ� �ֱ� ������
		System.out.println(list.get(0)); // �������ε��� ���� Music�� toString�� ȣ���.
		System.out.println(((Music)(list.get(0))).getTitle());
		// (Music)list.get(0) ���� �ϸ� list�� MusicŸ������ ����ȯ��. -> get() ��� �Ұ�
		
		System.out.println("----------------------------------------------------------");
		
		// 0�� ~ ������ �ε��������� �����͸� ���
		// for��
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("----------------------------------------------------------");
		
		// ���� for �� : for each �� => �迭 �Ӹ� �ƴ϶� �÷��ǿ��� Ȱ�� ����
		// for (���� �޾��� ���� ���� : �迭��Ǵ��÷��Ǹ�)
		for (Object o : list) { // list�� ������ ����ִ��� ��Ȯ�� �𸣱� ������
								// Object �� �޾���			
			System.out.println(o); // ���������� �������̵��� toString�� ��µ� ��. : �������ε�
		}
		
		System.out.println("----------------------------------------------------------");
		
		// 7. subList(int beginIndex, int endIndex) : �ش� ����Ʈ�� beginIndex���� endIndex���� ������ ����
		//											    �κ������� ������ ���ο� ����Ʈ�� ��ȯ���� (��, endIndex���� ������)
		List sub = list.subList(0, 2); // 0~1�� �ε��� ���� : 0 <= ������ �ε��� ���� < 2
		// => List<Object> sub
		// �θ� Ÿ������ �޾���
		
		System.out.println(sub);		
		
		System.out.println("----------------------------------------------------------");
		
		// 8. addAll(Collection c) : �ش� ����Ʈ�� �ٸ� �÷����� �����͵��� ��°�� �߰������ִ� �޼ҵ� : �÷��ǳ��� �����ִ� ����
		list.addAll(sub);
		
		System.out.println(list);
		
		System.out.println("----------------------------------------------------------");
		
		// 9. isEmpty() : ��ȯ�� boolean : �ش� ����Ʈ�� ���������(size() == 0) true, ������� �ʴٸ� false ��ȯ���ִ� �޼ҵ�
		System.out.println("�ش� ����Ʈ�� ����ֽ��ϱ�? : "+list.isEmpty());
		
		// 10. clear() : �ش� ����Ʈ�� �����͸� �� ���� ����ִ� �޼ҵ�
		list.clear();
		System.out.println(list);
		System.out.println("�ش� ����Ʈ�� ����ֽ��ϱ�? : "+list.isEmpty());
		
	}
}
