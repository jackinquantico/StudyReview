package com.kh.part_13.network.tcp.run3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientPractice3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 포트 번호
		int port = 3000;
		String ipAddress = "192.168.40.22";
		
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;		
		
		try {
			// 소켓 생성
			System.out.println("[연결 대기]");
			
			socket = new Socket(ipAddress, port);
			
			if (socket != null) {
				// 스트림 생성
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				System.out.println("[Client]");
				System.out.println("[연결 성공]");
				
				while (true) {
					System.out.print("서버에게 보낼 메시지 : ");
					String message = sc.nextLine();
					pw.println(message);
					
					pw.flush();
					
					String sMessage = br.readLine();
					System.out.println("서버로부터 온 메시지 : "+sMessage);
					
				}
			}
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
