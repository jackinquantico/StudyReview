package com.kh.part_14.arrayList.run;

import java.util.ArrayList;
import java.util.List;

import com.kh.part_14.arrayList.model.vo.Music;

public class ListRun {
	
	public static void main(String[] args) {
		
		// 기존의 배열을 쓸 경우 : 배열 쓸 준비(배열 선언 후 할당)
		// 컬렉션을 쓸 경우 : 컬렉션을 쓸 준비 (해당 컬렉션 객체 생성)
		
		// ArrayList 클래스의 객체 생성
		// ArrayList list = new ArrayList(); // 기본 생성자로 생성할 경우, 내부적으로 크기는 10짜리인 배열이 만들어짐.
		
		ArrayList list = new ArrayList(3); // 매개변수 생성자로 생성할 경우, 매개변수로 넘긴 값만큼의 크기의 배열이 만들어짐
		
		System.out.println(list); // [] 출력 : 안에 아무것도 담겨있지 않은 상태
		
		// E ---> Element : 제네릭(E == Object) : 다형성에 의해 모든 객체를 다 받아줄 수 있는 상태
		// 1. add(E e) : 해당 리스트의 끝에 전달된 e를 추가시켜주는 메소드
		list.add(new Music("강남스타일", "싸이")); // arr[0] = new Music(); 과 같은 꼴
		list.add(new Music("테스형!", "나훈아")); // arr[0+1] = new Music(); (마지막 방에 담기는 것)
		list.add(new Music("밥이 달다..","김가현")); // arr[2] = new Music();
		list.add("끝"); // arr[3] = "끝"; // 생성시 지정한 배열의 크기를 벗어남 : 배열에서는 불가능했던 일
		
		System.out.println(list);
		// 순서가 유지되면서 값 추가 (각 index에 들어있는 것과 같음)
		// 크기에 제약이 없음
		// 다양한 타입의 값 추가 가능
		
		// 2. add(int index, E e) : index 위치에 전달된 e를 추가시켜주는 메소드
		// 							해당 인덱스 다음값들을 뒤로 한 칸씩 밀어주는 역할 또한 알아서 해줌
		list.add(1, new Music("토요일좋아", "박불금"));
		
		System.out.println(list);
		
		// 3. set(int index, E e) : 해당 index 위치의 값을 전달된 e로 변경시켜주는 메소드
		list.set(0, new Music("강북멋쟁이", "정형돈")); // arr[0] = new Music(새로운 음악); 으로 덮어쓰기 한 꼴
		
		System.out.println(list);
		
		// 4. remove(int index) : 해당 index 위치의 값을 삭제시켜주는 메소드
		//						    삭제 후 기존의 값들을 앞으로 한 칸씩 땡겨주는 역할도 같이 해줌
		list.remove(1);
		
		System.out.println(list);
		
		// 5. size() : 해당 list의 크기(담겨있는 값의 개수)를 반환하는 메소드
		System.out.println("list 에 담긴 데이터 수 : "+list.size());
		System.out.println("list의 마지막 인덱스 : "+(list.size()-1));
		
		// 6. get(int index) : 반환형 E : 해당 index 위치의 데이터를 반환하는 메소드
		// Object object = list.get(0);
		// Music m = (Music)list.get(0);
		// System.out.println(m); // toString이 오버라이딩되어 있기 때문에
		System.out.println(list.get(0)); // 동적바인딩에 의해 Music의 toString이 호출됨.
		System.out.println(((Music)(list.get(0))).getTitle());
		// (Music)list.get(0) 으로 하면 list가 Music타입으로 형변환됨. -> get() 사용 불가
		
		System.out.println("----------------------------------------------------------");
		
		// 0번 ~ 마지막 인덱스까지의 데이터를 출력
		// for문
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		System.out.println("----------------------------------------------------------");
		
		// 향상된 for 문 : for each 문 => 배열 뿐만 아니라 컬렉션에도 활용 가능
		// for (값을 받아줄 변수 선언문 : 배열명또는컬렉션명)
		for (Object o : list) { // list에 무엇이 담겨있는지 정확히 모르기 때문에
								// Object 로 받아줌			
			System.out.println(o); // 내부적으로 오버라이딩된 toString이 출력될 것. : 동적바인딩
		}
		
		System.out.println("----------------------------------------------------------");
		
		// 7. subList(int beginIndex, int endIndex) : 해당 리스트의 beginIndex에서 endIndex까지 범위의 값을
		//											    부분적으로 추출해 새로운 리스트로 반환해줌 (단, endIndex값은 불포함)
		List sub = list.subList(0, 2); // 0~1번 인덱스 추출 : 0 <= 추출한 인덱스 범위 < 2
		// => List<Object> sub
		// 부모 타입으로 받아줌
		
		System.out.println(sub);		
		
		System.out.println("----------------------------------------------------------");
		
		// 8. addAll(Collection c) : 해당 리스트에 다른 컬렉션의 데이터들을 통째로 추가시켜주는 메소드 : 컬렉션끼리 합쳐주는 역할
		list.addAll(sub);
		
		System.out.println(list);
		
		System.out.println("----------------------------------------------------------");
		
		// 9. isEmpty() : 반환형 boolean : 해당 리스트가 비어있으면(size() == 0) true, 비어있지 않다면 false 반환해주는 메소드
		System.out.println("해당 리스트가 비어있습니까? : "+list.isEmpty());
		
		// 10. clear() : 해당 리스트의 데이터를 한 번에 비워주는 메소드
		list.clear();
		System.out.println(list);
		System.out.println("해당 리스트가 비어있습니까? : "+list.isEmpty());
		
	}
}
