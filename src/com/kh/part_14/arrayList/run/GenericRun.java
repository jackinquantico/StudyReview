package com.kh.part_14.arrayList.run;

import java.util.ArrayList;
import java.util.List;

import com.kh.part_14.arrayList.model.vo.Music;

public class GenericRun {

public static void main(String[] args) {
		
		// 컬렉션명<자료형> 객체명 = new 컬렉션명<자료형>();
		// => new 구문에 있는 제네릭 설정 부분에는 자료형 생략 가능 (jdk 8 버전부터 가능)
		
		ArrayList<Music> list = new ArrayList<>(3);
		
		System.out.println(list);
		
		// E --> Element : 제네릭 (E == Music)
		// 1. add(E e)
		list.add(new Music("강남스타일", "싸이"));
		list.add(new Music("테스형!", "나훈아"));
		// list.add("끝");
		// The method add(Music) in the type ArrayList<Music> is not applicable for the arguments (String)
		
		System.out.println(list);
		// 순서가 유지되면서 값 추가 (각 index에 들어있는 꼴)
		// 크기에 제약이 없음
		// 다양한 타입의 값을 추가할 수 없음 => 제네릭 설정을 했기 때문
		
		// 2. add(int index, E e)
		// list.add(1, "끝"); // Music 타입만 들어올 수 있음
		list.add(1, new Music("토요일좋아", "박불금"));
		
		System.out.println(list);
		
		// 3. set(int index, E e)
		list.set(0, new Music("강북멋쟁이", "정형돈"));
		
		System.out.println(list);
		
		// 4. remove(int index) // 제네릭 설정과 무관한 메소드
		list.remove(1);
		
		System.out.println(list);
		
		// 5. size() // 제네릭 설정과 무관한 메소드
		System.out.println("list에 담긴 데이터 수 : "+list.size());
		System.out.println("list의 마지막 인덱스 : "+(list.size()-1));
		
		// 6. get(int index) : E
		// 제네릭 설정 전 : Object 반환 -> Music 타입으로 강제형변환
		// 제네릭 설정 후 : Music 반환
		// Object obj = list.get(0); // get의 반환형 Music으로 설정되어있음
		// 	  부모 <------- 자식 : 다형성에 의해 자동형변환, upcasting
		Music m = list.get(0); // 형변환하지 않아도 됨
		System.out.println(m);
		System.out.println(list.get(0));
		
		System.out.println(list.get(0).getTitle());
		//					Music 객체라 바로 접근 가능
		
		System.out.println("----------------------------------------------------------");
		
		// for문 : 0~마지막 인덱스까지의 데이터를 출력
		// 일반 for문
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("----------------------------------------------------------");
		
		// 향상된 for문
		for (Music mc : list) {
			System.out.println(mc);
		}
		
		System.out.println("----------------------------------------------------------");
		
		// 7. subList(int beginIndex, int endIndex) : List
		List<Music> sub = list.subList(0, 1);
		// List sub로 받아줄 수는 있다. Object가 생략된 꼴이기 때문에
		
		System.out.println(sub);
		
		System.out.println("----------------------------------------------------------");
		
		// 8. addAll(Collection c)
		list.addAll(sub);
		// Music 타입만 들어갈 수 있는 list이기 때문에 매개변수로는 Music, 또는 Music을 상속받은 자식 타입만 들어올 수 있음.
		
		System.out.println(list);
		
		System.out.println("----------------------------------------------------------");
		
		// 9. isEmpty()
		System.out.println("해당 리스트가 비어있습니까? : "+list.isEmpty());
		
		System.out.println("----------------------------------------------------------");
		
		// 10. clear()
		list.clear();
		System.out.println(list);
		System.out.println("해당 리스트가 비어있습니까? : "+list.isEmpty());
		
	}

}
