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
		
		// ��ĳ�� �����
		Scanner sc = new Scanner(System.in);
		
		// port ��ȣ ����
		int port = 3000;
		
		// ���� ���� �� null �� �ʱ�ȭ
		ServerSocket serverSocket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// ���� ���� ����� - port ��ȣ �־
			serverSocket = new ServerSocket(port);
			
			// ��� ����
			System.out.println("Ŭ���̾�Ʈ�κ��� ��û ������Դϴ�...");
			
			// ��û �޾Ƽ� ���� �����
			Socket socket = serverSocket.accept();
			
			System.out.println("���ῡ �����߽��ϴ�.");
			
			// ��ݽ�Ʈ��, ������Ʈ�� �����
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while (true) {
				
				// Ŭ���̾�Ʈ�κ��� ��û �ޱ�
				String inMessage = br.readLine();
				System.out.println("Ŭ���̾�Ʈ�κ��� �� �޽��� : "+inMessage);
				
				// ���� ������
				System.out.print("�������� ���� �޽��� : ");
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
