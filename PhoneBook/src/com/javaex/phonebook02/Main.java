package com.javaex.phonebook02;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("**************************************************");
		System.out.println("* \t\t전화번호 관리 프로그램\t\t *");
		System.out.println("**************************************************");
		
		PersonView personView = new PersonView();
		Scanner sc = new Scanner(System.in);
		String num = null;
		
		do {
			personView.showMenu();
			num = sc.nextLine();
			
			switch (num) {
			case "1" :
				personView.personSelect();	// 리스트
				break;
			case "2" :
				personView.personInsert();	// 등록
				break;
			case "3" :
				personView.personDelete();	// 삭제
				break;
			case "4" :
				personView.personUpdate();	// 수정
				break;
			case "5" :
				personView.personSearch();	// 검색
				break;
			case "6" :
				System.out.println();
				System.out.println("**************************************************");
				System.out.println("*  \t\t감사합니다\t\t\t *");
				System.out.println("**************************************************");
				break;
			default :
				System.out.println();
				System.out.println("[다시 입력해 주세요.]");
				System.out.println();
				break;
			}
		} while (!num.equals("6"));

	}
}
