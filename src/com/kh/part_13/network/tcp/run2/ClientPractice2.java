package com.kh.part_13.network.tcp.run2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientPractice2 {

	public static void main(String[] args) {
		
		// 스캐너 생성
		Scanner sc = new Scanner(System.in);
		
		// port, ip 주소 세팅
		String ipAddress = "127.0.0.1";
		int port = 3000;
		
		// 변수 선언 및 null 로 초기화
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 소켓 생성
			socket = new Socket(ipAddress, port);
			
			// 소켓 생성 확인
			if (socket != null) {
				// 기반스트림, 보조스트림 생성
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				System.out.println("연결에 성공했습니다.");
				
				// 입출력받기
				while (true) {
					
					System.out.print("클라이언트에서 보내는 메시지 : ");
					String input = sc.nextLine();
					pw.println(input);
					
					pw.flush();
					
					String message = br.readLine();
					System.out.println("서버에서 보내는 메시지 : "+message);
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 반납
			try {
				pw.close();
				br.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

	}

}
