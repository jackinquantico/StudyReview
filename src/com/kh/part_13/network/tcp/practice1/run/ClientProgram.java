package com.kh.part_13.network.tcp.practice1.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientProgram {

	public static void main(String[] args) {
		
		// 1. 스캐너 대기
		Scanner sc = new Scanner(System.in);
		
		// 2. 포트 번호, ip 번호 지정
		String serverIP = "127.0.0.1";
		int port = 3000;
		
		// 3. 소켓, 입출력 스트림 변수 선언 및 null 초기화
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 4. 소켓 연결 (ip번호, 포트번호)
			socket = new Socket(serverIP, port);
			
			if (socket != null) {
				System.out.println("서버와 연결 성공");
				
				// 5. 입출력 스트림 생성
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				while (true) {
					// 메시지 입출력
					System.out.print("클라이언트에서 보낼 메시지 : ");
					String writeMessage = sc.nextLine();
					
					pw.println(writeMessage);
					pw.flush();
					
					String readMessage = br.readLine();
					System.out.println("서버로부터 온 메시지 : "+readMessage);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 6. 자원 반납
				pw.close();
				br.close();
				socket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}

}
