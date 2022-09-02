package com.kh.acmicpc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class N5575 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for (int i=0; i<3; i++) {
			String str = br.readLine();
			
			StringTokenizer st = new StringTokenizer(str, " ");
			
			int ch = Integer.parseInt(st.nextToken());
			int cm = Integer.parseInt(st.nextToken());
			int cs = Integer.parseInt(st.nextToken());
			int th = Integer.parseInt(st.nextToken());
			int tm = Integer.parseInt(st.nextToken());
			int ts = Integer.parseInt(st.nextToken());
			
			int r = (th*3600 + tm*60 + ts) - (ch*3600 + cm*60 + cs);
			
			int rh = r/3600;
			r %= 3600;
			int rm = r/60;
			r %= 60;
			int rs = r;
			
			System.out.println(rh +" "+ rm +" "+ rs);
		}
		
		bw.flush();
		bw.close();

	}

}
