package com.kh.acmicpc;

import java.math.BigInteger;
import java.util.Scanner;

public class N11382 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		BigInteger a = sc.nextBigInteger();
		BigInteger b = sc.nextBigInteger();
		BigInteger c = sc.nextBigInteger();
		
		System.out.println(a.add(b.add(c)));
	}

}
