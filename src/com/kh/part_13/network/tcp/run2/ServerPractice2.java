package com.kh.part_13.network.tcp.run2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerPractice2 {

	public static void main(String[] args) {
		
		// 스캐너 만들기
		Scanner sc = new Scanner(System.in);
		
		// port 번호 설정
		int port = 3000;
		
		// 변수 선언 및 null 로 초기화
		ServerSocket serverSocket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 서버 소켓 만들기 - port 번호 넣어서
			serverSocket = new ServerSocket(port);
			
			// 대기 상태
			System.out.println("클라이언트로부터 요청 대기중입니다...");
			
			// 요청 받아서 소켓 만들기
			Socket socket = serverSocket.accept();
			
			System.out.println("연결에 성공했습니다.");
			
			// 기반스트림, 보조스트림 만들기
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true) {
				
				// 클라이언트로부터 요청 받기
				String inMessage = br.readLine();
				System.out.println("클라이언트로부터 온 메시지 : "+inMessage);
				
				// 답장 보내기
				System.out.print("서버에서 보낸 메시지 : ");
				String outMessage = sc.nextLine();
				
				pw.println(outMessage);
				
				pw.flush();
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				pw.close();
				br.close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
