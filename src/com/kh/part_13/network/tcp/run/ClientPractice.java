package com.kh.part_13.network.tcp.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientPractice {

	public static void main(String[] args) {
		
		// 스캐너 생성
		Scanner sc = new Scanner(System.in);
		
		// 포트 번호, ip 주소 생성
		String addressIP = "127.0.0.1";
		int port = 3000;
		
		// 변수 선언 및 null 초기화
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 소켓 생성할 때 매개변수로 ip 주소와 port 값 넣기
			socket = new Socket(addressIP, port);
			
			if (socket != null ) { // 잘 연결 되었을 때만
				
				System.out.println("연결 성공!");
				
				// 기반스트림과 보조스트림 생성
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				System.out.println("대화할 준비가 되었습니다.");
				
				while (true) {
					
					System.out.print("서버에 보낼 메시지 : ");
					String sendMessage = sc.nextLine();
					pw.println(sendMessage);
					
					pw.flush();
					
					String readMessage = br.readLine();
					System.out.println("서버에서 온 메시지 : "+readMessage);
					
				}
			}
		
		} catch (UnknownHostException e) {	
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
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
