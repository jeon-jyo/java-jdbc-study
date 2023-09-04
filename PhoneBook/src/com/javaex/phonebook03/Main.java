package com.javaex.phonebook03;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("**************************************************");
		System.out.println("* \t\t전화번호 관리 프로그램\t\t *");
		System.out.println("**************************************************");
		
		PersonService personService = new PersonService();
		Scanner sc = new Scanner(System.in);
		String num = null;
		
		do {
			personService.showMenu();
			num = sc.nextLine();
			
			switch (num) {
			case "1" :
				personService.personSelect();	// 리스트
				break;
			case "2" :
				personService.personInsert();	// 등록
				break;
			case "3" :
				personService.personDelete();	// 삭제
				break;
			case "4" :
				personService.personUpdate();	// 수정
				break;
			case "5" :
				personService.personSearch();	// 검색
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
