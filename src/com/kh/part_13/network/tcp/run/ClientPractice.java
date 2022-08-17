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
		
		// ��ĳ�� ����
		Scanner sc = new Scanner(System.in);
		
		// ��Ʈ ��ȣ, ip �ּ� ����
		String addressIP = "127.0.0.1";
		int port = 3000;
		
		// ���� ���� �� null �ʱ�ȭ
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// ���� ������ �� �Ű������� ip �ּҿ� port �� �ֱ�
			socket = new Socket(addressIP, port);
			
			if (socket != null ) { // �� ���� �Ǿ��� ����
				
				System.out.println("���� ����!");
				
				// ��ݽ�Ʈ���� ������Ʈ�� ����
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				System.out.println("��ȭ�� �غ� �Ǿ����ϴ�.");
				
				while (true) {
					
					System.out.print("������ ���� �޽��� : ");
					String sendMessage = sc.nextLine();
					pw.println(sendMessage);
					
					pw.flush();
					
					String readMessage = br.readLine();
					System.out.println("�������� �� �޽��� : "+readMessage);
					
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
