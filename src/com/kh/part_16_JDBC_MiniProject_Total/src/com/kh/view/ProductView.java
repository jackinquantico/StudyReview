package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductView {

	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	
	public void loginMenu() {
		
		while (true) {
			
			System.out.println(">>>>> KH 전자랜드 <<<<<");
			System.out.println("----- 로그인 메뉴 -----");
			System.out.println("1. 일반 회원 메뉴 보기");
			System.out.println("2. 관리자 로그인하기");
			System.out.println("0. 프로그램 종료하기");
			System.out.println("--------------------");
			System.out.print(">> 메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				mainMenu();
				break;
			case 2:
				adminLogin();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				break;
			}
			
			System.out.println();
		}
	}
	
	public void adminLogin() {
		
		System.out.println("\n----- 관리자 로그인 -----");
		
		while (true) {
			System.out.print("관리자 아이디 입력 : ");
			String adminId = sc.nextLine();
			System.out.print("관리자 비밀번호 입력 : ");
			String adminPwd = sc.nextLine();
			
			if (adminId.equals("admin") && adminPwd.equals("admin")) {
				adminMenu();
				break;
			} else {
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void adminMenu() {
		
		while (true) {
			
			System.out.println("\n----- 관리자 메뉴 -----");
			System.out.println("1. 상품 전체 조회 하기");
			System.out.println("2. 상품 추가 하기");
			System.out.println("3. 재고 관리하기");
			System.out.println("4. 상품 정보 수정 하기");
			System.out.println("5. 상품 삭제 하기");
			System.out.println("6. 상품명 검색 하기");
			System.out.println("0. 이전 메뉴로 돌아가기");
			System.out.println("99. 프로그램 종료하기");
			System.out.println("----------------------");
			System.out.print(">> 메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				selectAll();
				break;
			case 2:
				insertProduct();
				break;
			case 3:
				selectByStock();
				break;
			case 4:
				updateProduct();
				break;
			case 5:
				deleteProduct();
				break;
			case 6:
				selectByProductName();
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			case 99:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("해당 메뉴는 존재하지 않습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("\n----- 전자랜드 메뉴 -----");
			System.out.println("1. 상품 전체 조회 하기");
			System.out.println("2. 상품명 검색 하기");			
			System.out.println("3. 가격별 상품 검색하기");
			System.out.println("4. 랜덤 상품 추천하기");
			System.out.println("5. 종류별 상품 검색하기");			
			System.out.println("0. 이전 메뉴로 돌아가기");
			System.out.println("99. 프로그램 종료하기");
			System.out.println("----------------------");
			System.out.print(">> 메뉴 번호 입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				selectAll();
				break;
			case 2:
				selectByProductName();
				break;
			case 3:
				selectByPrice();
				break;
			case 4:
				selectByRandom();
				break;
			case 5:
				selectByKind();
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			case 99:
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			default:
				System.out.println("해당 메뉴는 존재하지 않습니다. 다시 입력해주세요.");
			}
			
			System.out.println();
		}
	}
	
	public void selectAll() {
		
		System.out.println("----- 상품 전체 조회 -----");
		
		pc.selectAll();
	}
	
	public void insertProduct() {
		
		System.out.println("----- 상품 추가 -----");
		
		System.out.print("상품 아이디 입력 : ");
		String productId = sc.nextLine();
		System.out.print("상품명 입력 : ");
		String productName = sc.nextLine();
		System.out.print("상품 가격 입력 : ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("상품 상세정보 입력 : ");
		String description = sc.nextLine();
		System.out.print("재고 입력 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		pc.insertProduct(productId,productName,price,description,stock);
	}
	
	public void selectByProductName() {
		
		System.out.println("----- 상품명 검색 -----");
		
		System.out.print("상품명 키워드 입력 : ");
		String keyword = sc.nextLine();
		
		pc.selectByProductName(keyword);
	}
	
	public void updateProduct() {
		
		System.out.println("----- 상품 정보 수정 -----");
		
		System.out.print("수정할 상품 아이디 입력 : ");
		String productId = sc.nextLine();
		System.out.print("상품 가격 수정 : ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("상품 상세 정보 수정 : ");
		String description = sc.nextLine();
		System.out.print("상품 재고 수정 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		pc.updateProduct(productId, price, description, stock);
	}
	
	public void deleteProduct() {
		
		System.out.println("----- 상품 삭제 -----");
		
		System.out.print("삭제할 상품 아이디 입력 : ");
		String productId = sc.nextLine();
		
		while (true) {
			
			System.out.print("정말로 삭제하시겠습니까? : ");
			char answer = sc.nextLine().toUpperCase().charAt(0);
			
			if (answer == 'Y') {
				break;
			} else if (answer == 'N') {
				System.out.println("이전 메뉴로 돌아갑니다.");
				return;
			} else {
				System.out.println("잘못 입력했습니다.");
			}
		}
		
		pc.deleteProduct(productId);
	}
	
	public void selectByPrice() {
		
		System.out.println("----- 가격대별 상품 조회하기 -----");
		
		System.out.print("최소 가격 : ");
		int min = sc.nextInt();
		sc.nextLine();
		
		System.out.print("최대 가격 : ");
		int max = sc.nextInt();
		sc.nextLine();
		
		pc.selectByPrice(min, max);
	}
	
	public void selectByRandom() {
		
		System.out.println("----- 랜덤 상품 추천 -----");

		pc.selectByRandom();
	}
	
	public void selectByKind() {
		
		System.out.println("----- 종류별 상품 검색 -----");
		System.out.println("노트북 / 데스크탑 / 핸드폰 / 태블릿");
		System.out.print("상품 종류를 입력하세요 : ");
		String kind = sc.nextLine();
				
		switch (kind) {
		case "노트북":
			kind = "nb";
			break;
		case "데스크탑":
			kind = "pc";
			break;
		case "핸드폰":
			kind = "hp";
			break;
		case "태블릿":
			kind = "tp";
		default:
			break;
		}
		
		pc.selectByKind(kind);
	}
	
	public void selectByStock() {
		
		System.out.println("----- 상품 재고 관리 -----");
		
		System.out.print("재고가 몇 개 이하인 상품을 조회하시겠습니까? : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
		pc.selectByStock(stock);
	}
	
	//-----------------------------
	
	public void displayNodata(String message) {
		
		System.out.println(message);
	}
	
	public void displayList(ArrayList<Product> list) {
		
		for (Product p : list) {
			System.out.println(p);
		}
	}
	
	public void displaySuccess(String message) {
		
		System.out.println("서비스 요청 성공 : "+message);
	}
	
	public void displayFail(String message) {
		
		System.out.println("서비스 요청 실패 : "+message);
	}
	
	public void displayRandom(ArrayList<Product> list) {
		
		System.out.println("오늘의 랜덤 추천 상품 : ");
		
		// System.out.println(list.size());
		
		int r = (int)(Math.random() * (list.size()-1))+1;
		//(int) Math.random() * (최댓값-최소값+1) + 최소값
      
		System.out.println(list.get(r));
	}
}
