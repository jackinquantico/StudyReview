package com.kh.acmicpc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class N20499 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		
		StringTokenizer st = new StringTokenizer(str, "/");
		
		int k = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		
		if ((k + a < d) || d == 0) {
			bw.write("hasu");
		} else {
			bw.write("gosu");
		}
		
		bw.flush();
		bw.close();

	}

}
