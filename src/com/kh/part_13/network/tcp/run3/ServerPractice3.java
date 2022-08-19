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
		
		// port ��ȣ ����
		int port = 5556;
		
		// ServerSocket �����
		ServerSocket server = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			
			// socket ����
			server = new ServerSocket(port);
			
			System.out.println("���� �����");
			Socket socket = server.accept();
			
			System.out.println(socket.getInetAddress().getHostAddress()+" �� ���� ��û");
			// ��Ʈ�� �����
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			System.out.println("[Server]");
			System.out.println("[���� ����]");
			
			while (true) {
				String cMessage = br.readLine();
				System.out.println("Ŭ���̾�Ʈ���� �� �޽��� : "+cMessage);
				
				System.out.print("Ŭ���̾�Ʈ���� ���� �޽��� : ");
				String message = sc.nextLine();
				
				pw.println(message);
				
				pw.flush();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ݳ�
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
