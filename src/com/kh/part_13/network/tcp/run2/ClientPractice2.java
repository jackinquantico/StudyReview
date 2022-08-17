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
		
		// ��ĳ�� ����
		Scanner sc = new Scanner(System.in);
		
		// port, ip �ּ� ����
		String ipAddress = "127.0.0.1";
		int port = 3000;
		
		// ���� ���� �� null �� �ʱ�ȭ
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// ���� ����
			socket = new Socket(ipAddress, port);
			
			// ���� ���� Ȯ��
			if (socket != null) {
				// ��ݽ�Ʈ��, ������Ʈ�� ����
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				System.out.println("���ῡ �����߽��ϴ�.");
				
				// ����¹ޱ�
				while (true) {
					
					System.out.print("Ŭ���̾�Ʈ���� ������ �޽��� : ");
					String input = sc.nextLine();
					pw.println(input);
					
					pw.flush();
					
					String message = br.readLine();
					System.out.println("�������� ������ �޽��� : "+message);
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ݳ�
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
