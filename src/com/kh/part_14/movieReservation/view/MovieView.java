package com.kh.part_14.movieReservation.view;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.RowFilter;

import com.kh.part_14.movieReservation.controller.MovieController;
import com.kh.part_14.movieReservation.model.vo.Movie;

public class MovieView {

	private Scanner sc = new Scanner(System.in);
	private MovieController mc = new MovieController();
	
	public void loginMenu() {
			
		while(true) {
			
			System.out.println("==== ## ��ȭ�� ====");
			System.out.println("1. �� �α���");
			System.out.println("2. �� ȸ������");
			System.out.println("3. ������ �α���");
			System.out.println("0. ���α׷� ����");
			System.out.println("=================");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				cosLogin();
				break;
			case 2:
				cosSignup();
				break;
			case 3:
				sysLogin();
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				return;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
			
		}
	}
	
	public void cosLogin() {
		
		System.out.println("�� �α��� â�Դϴ�.");
		System.out.print("�� ���̵� �Է��ϼ��� : ");
		String cosId = sc.nextLine();
		System.out.print("�� ��й�ȣ�� �Է��ϼ��� : ");
		String cosPw = sc.nextLine();
		
		int result = mc.cosLogin(cosId, cosPw);
		
		if (result == 3) {
			System.out.println("�α��ο� �����߽��ϴ�.");
			
			while (true) {
				System.out.print("���� �޴��� �����Ͻðڽ��ϱ�?(Y/N) : ");
				String answer = sc.nextLine();
				
				if (answer.toUpperCase().charAt(0) == 'Y') {
					System.out.println("���� �޴��� �����մϴ�.\n");
					mainMenu();
				} else if (answer.toUpperCase().charAt(0) == 'N') {
					System.out.println("���α׷��� �����մϴ�.");
					return;
				} else {
					System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
				}
			}
		} else {
			if (result == 2) {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			} else if (result == 1) {
				System.out.println("���̵� ��ġ���� �ʽ��ϴ�.");
			} else {
				System.out.println("�ش� ���̵�� �������� �ʽ��ϴ�.");
			}
			
			System.out.println("�α��ο� �����߽��ϴ�.");
		}
		
	}
	
	public void cosSignup() {
		
		System.out.println("�� ȸ������ â�Դϴ�.");
		System.out.print("����� ���̵� �Է��ϼ��� : ");
		String cosId = sc.nextLine();
		System.out.print("����� ��й�ȣ�� �Է��ϼ��� : ");
		String cosPw = sc.nextLine();
		
		int result = mc.cosSignup(cosId, cosPw);
		
		if (result > 0) {
			System.out.println("ȸ�����Կ� �����߽��ϴ�.");
		} else {
			System.out.println("�̹� �����ϴ� ���̵��Դϴ�.");
			System.out.println("ȸ�����Կ� �����߽��ϴ�.");
		}
	}
	
	public void sysLogin() {
		
		System.out.println("������ �α��� â�Դϴ�.");
		
		System.out.print("������ ���̵� �Է��ϼ��� : ");
		String sysId = sc.nextLine();
		System.out.print("������ ��й�ȣ�� �Է��ϼ��� : ");
		String sysPw = sc.nextLine();
		
		int result = mc.sysLogin(sysId, sysPw);
			
		if (result > 0) {
			System.out.println("�α��ο� �����߽��ϴ�.");
			
			while (true) {
				System.out.print("������ �޴��� �̵��Ͻðڽ��ϱ�?(Y/N) : ");
				String answer = sc.nextLine();
				
				if (answer.toUpperCase().charAt(0) == 'Y') {
					System.out.println("������ �޴��� �����մϴ�.\n");
					sysMenu();
				} else if (answer.toUpperCase().charAt(0) == 'N') {
					System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
				} else {
					System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
				}
			}
			
		} else {
			System.out.println("�α��ο� �����߽��ϴ�.");
		}
		
	}
	
	public void mainMenu() {
		
		while (true) {
			
			System.out.println("==== ## ��ȭ�� ====");
			System.out.println("1. ��ȭ ��� ����");
			System.out.println("2. ��ȭ �����ϱ�");
			System.out.println("3. ��ȭ ����ϱ�");
			System.out.println("0. ���α׷� ����");
			System.out.println("=================");
			System.out.print("�޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				showMovieList();
				break;
			case 2:
				selectMovie();
				break;
			case 3:
				cancelMovie();
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
			
		}
	}
	
	
	public void showMovieList() {
		
		System.out.println("==== ��ȭ ��� ====");
		
		// showMovieList() ȣ��
		// ���ϰ����� list
		ArrayList<Movie> list = mc.showMovieList();
				
		// if ���ǽ����� �˻� �� ���
		if (list.isEmpty()) {
			System.out.println("�� ���� ��ȭ�� �����ϴ�.");
		} else {
			for (Movie m : list) {
				System.out.println(m);
			}
		}
	}
	
	
	public void selectMovie() {
		
		System.out.println("=== ��ȭ ���� ===");
		ArrayList<Movie> list = mc.showMovieList();
		for (Movie m : list) {
			System.out.println(m);
		}
		
		System.out.print("������ ��ȭ�� �������ּ��� : ");
		String title = sc.nextLine();
		
		ArrayList<Movie> searched = mc.selectMovie(title);
		
		if (searched.isEmpty()) {
			System.out.println("�ش� ��ȭ�� �������� �ʽ��ϴ�.");
		} else {
			for (Movie m : searched) {
				System.out.println(m);
			}
			selectSeat();
		}
		
	}
	
	public void selectSeat() {
		
		System.out.println("\n=== �ڸ� ���� ===");
		
		System.out.println("=============="); //14
		for (int i=0; i<mc.seatNoRow.length; i++) {
			System.out.print(mc.seatNoRow[i]+":");
			for (int j=0; j<mc.seatNoCol[i].length; j++) {
				System.out.print(" " + mc.seatNoCol[i][j]);
			}
			System.out.println();
		}
		
		System.out.print("�¼� �� �Է�(A~E) : ");
		char row = sc.nextLine().toUpperCase().charAt(0);
		System.out.print("�¼� �� �Է�(1~6) : ");
		char col = sc.nextLine().charAt(0);
		
		if (!('A' <= row && row <= 'E')) {
			System.out.println("�߸��� ���� �Է��߽��ϴ�.");
			return;
		}
		if (!('1' <= col && col <= '6')) {
			System.out.println("�߸��� ���� �Է��߽��ϴ�.");
			return;
		}
		
		boolean result = mc.selectSeat(row, col);
		
		if (result == true) {
			System.out.println("���ſ� �����߽��ϴ�.");
		} else {
			System.out.println("���ſ� �����߽��ϴ�.");
		}
		
	}
	
	public void cancelMovie() {
		
		System.out.println("=== ��ȭ ��� ===");
		ArrayList<Movie> list = mc.showMovieList();
		for (Movie m : list) {
			System.out.println(m);
		}
		
		System.out.print("����� ��ȭ�� �Է����ּ��� : ");
		String title = sc.nextLine();
		System.out.print("���� �¼� �� �Է�(A~E) : ");
		char row = sc.nextLine().toUpperCase().charAt(0);
		System.out.print("���� �¼� �� �Է�(1~6) : ");
		char col = sc.nextLine().charAt(0);
		
		boolean result = mc.cancelMovie(title, row, col);
		
		if (result == true) {
			System.out.println("��ҿ� �����߽��ϴ�.");
		} else {
			System.out.println("���� ������ �������� �ʽ��ϴ�.");
		}
	}
	
	public void sysMenu() {
		
		while (true) {
		
			System.out.println("==== ## ��ȭ�� ====");
			System.out.println("==== ������ �޴� ====");
			System.out.println("1. ��ȭ �߰��ϱ�");
			System.out.println("2. ��ȭ �����ϱ�");
			System.out.println("3. ��ȭ ��Ϻ���");
			System.out.println("0. ���α׷� ����");
			System.out.println("=================");
			System.out.print("�޴� ��ȣ �Է� : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch (menu) {
			case 1:
				insertMovie();
				break;
			case 2:
				deleteMovie();
				break;
			case 3:
				showMovieList();
				break;
			case 0:
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			
			System.out.println();
		}
	}
	
	public void insertMovie() {
		
		System.out.println("=== ��ȭ �߰� ===");
		System.out.print("�߰��� ��ȭ ���� �Է� : ");
		String title = sc.nextLine();
		System.out.print("�߰��� ��ȭ ���� �Է� : ");
		String director = sc.nextLine();
		System.out.print("�߰��� ��ȭ �帣 �Է� : "); 
		String genre = sc.nextLine();
		System.out.print("�߰��� ��ȭ �� �ð� : ");
		int runtime = sc.nextInt();
		sc.nextLine();
		
		int result = mc.insertMovie(title, director, genre, runtime);
		
		if (result > 0) {
			System.out.println("�߰��� �����߽��ϴ�.");
		} else {
			System.out.println("�߰��� �����߽��ϴ�.");
		}
	}
	
	public void deleteMovie() {
		
		System.out.println("=== ��ȭ ���� ===");
		System.out.print("������ ��ȭ ���� �Է� : ");
		String title = sc.nextLine();
		System.out.print("������ ��ȭ ���� �Է� : ");
		String director = sc.nextLine();
		
		int result = mc.deleteMovie(title, director);
		
		if (result > 0) {
			System.out.println("������ �����߽��ϴ�.");
		} else {
			System.out.println("������ ��ȭ�� �������� �ʽ��ϴ�.");
		}
	}
	
}












