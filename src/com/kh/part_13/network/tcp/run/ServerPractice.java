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
		
		// 1. ��ĳ�� ���
		Scanner sc = new Scanner(System.in);
		
		//2. port ��ȣ ����
		int port = 3000;
		
		// ���� ���� �� null �ʱ�ȭ
		ServerSocket serverSocket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 3. �������� �����
			serverSocket = new ServerSocket(port);
			
			// ������
			System.out.println("Ŭ���̾�Ʈ�� ��û�� ��ٸ��� ���Դϴ�..");
			
			// 4. ���� ��û ���� Socket ��ü ����
			Socket socket = serverSocket.accept();
			
			// 5. ����� ��� ��Ʈ��
			// 6. ���� ��Ʈ�� �߰�
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			// ���ѹݺ���
			while (true) {
				
				// Ŭ���̾�Ʈ�� ��û�� �ޱ�
				String messeage = br.readLine();
				System.out.println("Ŭ���̾�Ʈ�κ��� �� �޽��� : "+messeage);
				
				// �����ϱ�
				System.out.print("Ŭ���̾�Ʈ���� ���� �޽��� : ");
				String answer = sc.nextLine();
				pw.println(answer);
				
				pw.flush();
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// �ڿ� �ݳ� �� ��� ����
				pw.close();
				br.close();
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
