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
		
		// 1. ��ĳ�� ���
		Scanner sc = new Scanner(System.in);
		
		// 2. port ��ȣ ����
		int port = 3000;
		
		// 3. ��������, ����½�Ʈ�� ���� ���� �� null �ʱ�ȭ
		ServerSocket server = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 4. ���� ����
			server = new ServerSocket(port);
			
			System.out.println("���� �����...");
			
			Socket socket = server.accept();
			
			// 5. ����� ��Ʈ�� ����
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
						
			while (true) {
				// �޽��� �����
				String readMessage = br.readLine();
				System.out.println("Ŭ���̾�Ʈ�κ��� �� �޽��� : "+readMessage);
				
				System.out.print("�������� ���� �޽��� : ");
				String writeMessage = sc.nextLine();
				
				pw.println(writeMessage);
				pw.flush();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
			// 6. �ڿ� �ݳ�
				pw.close();
				br.close();
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
