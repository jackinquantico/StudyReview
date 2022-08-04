package com.kh.acmicpc;

import java.util.Scanner;

public class N11021 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        for(int i=0; i<t; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            
            System.out.println("Case #"+(i+1)+": "+(a+b));
        }
	}

}
