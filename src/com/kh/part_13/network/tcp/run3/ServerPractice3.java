package com.kh.part_13.network.tcp.run3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerPractice3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// port 번호 연결
		int port = 5556;
		
		// ServerSocket 만들기
		ServerSocket server = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			
			// socket 연결
			server = new ServerSocket(port);
			
			System.out.println("연결 대기중");
			Socket socket = server.accept();
			
			System.out.println(socket.getInetAddress().getHostAddress()+" 의 연결 요청");
			// 스트림 만들기
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			System.out.println("[Server]");
			System.out.println("[연결 성공]");
			
			while (true) {
				String cMessage = br.readLine();
				System.out.println("클라이언트에게 온 메시지 : "+cMessage);
				
				System.out.print("클라이언트에게 보낼 메시지 : ");
				String message = sc.nextLine();
				
				pw.println(message);
				
				pw.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 반납
			try {
				pw.close();
				br.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
