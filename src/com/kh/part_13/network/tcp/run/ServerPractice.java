package com.kh.part_13.network.tcp.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerPractice {

	public static void main(String[] args) {
		
		// 1. 스캐너 대기
		Scanner sc = new Scanner(System.in);
		
		//2. port 번호 지정
		int port = 3000;
		
		// 변수 선언 및 null 초기화
		ServerSocket serverSocket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 3. 서버소켓 만들기
			serverSocket = new ServerSocket(port);
			
			// 대기상태
			System.out.println("클라이언트의 요청을 기다리는 중입니다..");
			
			// 4. 연결 요청 오면 Socket 객체 생성
			Socket socket = serverSocket.accept();
			
			// 5. 입출력 기반 스트림
			// 6. 보조 스트림 추가
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			// 무한반복문
			while (true) {
				
				// 클라이언트의 요청을 받기
				String messeage = br.readLine();
				System.out.println("클라이언트로부터 온 메시지 : "+messeage);
				
				// 답장하기
				System.out.print("클라이언트에게 보낼 메시지 : ");
				String answer = sc.nextLine();
				pw.println(answer);
				
				pw.flush();
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원 반납 및 통로 끊기
				pw.close();
				br.close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
