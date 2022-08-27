package com.kh.part_13.network.tcp.practice1.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerProgram {

	public static void main(String[] args) {
		
		// 1. 스캐너 대기
		Scanner sc = new Scanner(System.in);
		
		// 2. port 번호 지정
		int port = 3000;
		
		// 3. 서버소켓, 입출력스트림 변수 선언 및 null 초기화
		ServerSocket server = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 4. 소켓 연결
			server = new ServerSocket(port);
			
			System.out.println("연결 대기중...");
			
			Socket socket = server.accept();
			
			// 5. 입출력 스트림 생성
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
						
			while (true) {
				// 메시지 입출력
				String readMessage = br.readLine();
				System.out.println("클라이언트로부터 온 메시지 : "+readMessage);
				
				System.out.print("서버에서 보낼 메시지 : ");
				String writeMessage = sc.nextLine();
				
				pw.println(writeMessage);
				pw.flush();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
			// 6. 자원 반납
				pw.close();
				br.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
