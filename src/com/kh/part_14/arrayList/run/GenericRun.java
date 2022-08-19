package com.kh.part_14.arrayList.run;

import java.util.ArrayList;
import java.util.List;

import com.kh.part_14.arrayList.model.vo.Music;

public class GenericRun {

public static void main(String[] args) {
		
		// �÷��Ǹ�<�ڷ���> ��ü�� = new �÷��Ǹ�<�ڷ���>();
		// => new ������ �ִ� ���׸� ���� �κп��� �ڷ��� ���� ���� (jdk 8 �������� ����)
		
		ArrayList<Music> list = new ArrayList<>(3);
		
		System.out.println(list);
		
		// E --> Element : ���׸� (E == Music)
		// 1. add(E e)
		list.add(new Music("������Ÿ��", "����"));
		list.add(new Music("�׽���!", "���ƾ�"));
		// list.add("��");
		// The method add(Music) in the type ArrayList<Music> is not applicable for the arguments (String)
		
		System.out.println(list);
		// ������ �����Ǹ鼭 �� �߰� (�� index�� ����ִ� ��)
		// ũ�⿡ ������ ����
		// �پ��� Ÿ���� ���� �߰��� �� ���� => ���׸� ������ �߱� ����
		
		// 2. add(int index, E e)
		// list.add(1, "��"); // Music Ÿ�Ը� ���� �� ����
		list.add(1, new Music("���������", "�ںұ�"));
		
		System.out.println(list);
		
		// 3. set(int index, E e)
		list.set(0, new Music("���ϸ�����", "������"));
		
		System.out.println(list);
		
		// 4. remove(int index) // ���׸� ������ ������ �޼ҵ�
		list.remove(1);
		
		System.out.println(list);
		
		// 5. size() // ���׸� ������ ������ �޼ҵ�
		System.out.println("list�� ��� ������ �� : "+list.size());
		System.out.println("list�� ������ �ε��� : "+(list.size()-1));
		
		// 6. get(int index) : E
		// ���׸� ���� �� : Object ��ȯ -> Music Ÿ������ ��������ȯ
		// ���׸� ���� �� : Music ��ȯ
		// Object obj = list.get(0); // get�� ��ȯ�� Music���� �����Ǿ�����
		// 	  �θ� <------- �ڽ� : �������� ���� �ڵ�����ȯ, upcasting
		Music m = list.get(0); // ����ȯ���� �ʾƵ� ��
		System.out.println(m);
		System.out.println(list.get(0));
		
		System.out.println(list.get(0).getTitle());
		//					Music ��ü�� �ٷ� ���� ����
		
		System.out.println("----------------------------------------------------------");
		
		// for�� : 0~������ �ε��������� �����͸� ���
		// �Ϲ� for��
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("----------------------------------------------------------");
		
		// ���� for��
		for (Music mc : list) {
			System.out.println(mc);
		}
		
		System.out.println("----------------------------------------------------------");
		
		// 7. subList(int beginIndex, int endIndex) : List
		List<Music> sub = list.subList(0, 1);
		// List sub�� �޾��� ���� �ִ�. Object�� ������ ���̱� ������
		
		System.out.println(sub);
		
		System.out.println("----------------------------------------------------------");
		
		// 8. addAll(Collection c)
		list.addAll(sub);
		// Music Ÿ�Ը� �� �� �ִ� list�̱� ������ �Ű������δ� Music, �Ǵ� Music�� ��ӹ��� �ڽ� Ÿ�Ը� ���� �� ����.
		
		System.out.println(list);
		
		System.out.println("----------------------------------------------------------");
		
		// 9. isEmpty()
		System.out.println("�ش� ����Ʈ�� ����ֽ��ϱ�? : "+list.isEmpty());
		
		System.out.println("----------------------------------------------------------");
		
		// 10. clear()
		list.clear();
		System.out.println(list);
		System.out.println("�ش� ����Ʈ�� ����ֽ��ϱ�? : "+list.isEmpty());
		
	}

}
