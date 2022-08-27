package com.kh.part_13.network.tcp.practice1.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientProgram {

	public static void main(String[] args) {
		
		// 1. ��ĳ�� ���
		Scanner sc = new Scanner(System.in);
		
		// 2. ��Ʈ ��ȣ, ip ��ȣ ����
		String serverIP = "127.0.0.1";
		int port = 3000;
		
		// 3. ����, ����� ��Ʈ�� ���� ���� �� null �ʱ�ȭ
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			// 4. ���� ���� (ip��ȣ, ��Ʈ��ȣ)
			socket = new Socket(serverIP, port);
			
			if (socket != null) {
				System.out.println("������ ���� ����");
				
				// 5. ����� ��Ʈ�� ����
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				while (true) {
					// �޽��� �����
					System.out.print("Ŭ���̾�Ʈ���� ���� �޽��� : ");
					String writeMessage = sc.nextLine();
					
					pw.println(writeMessage);
					pw.flush();
					
					String readMessage = br.readLine();
					System.out.println("�����κ��� �� �޽��� : "+readMessage);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 6. �ڿ� �ݳ�
				pw.close();
				br.close();
				socket.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

	}

}
