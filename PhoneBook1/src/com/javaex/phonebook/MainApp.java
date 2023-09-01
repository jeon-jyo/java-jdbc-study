package com.javaex.phonebook;

import java.util.List;
import java.util.Scanner;

public class MainApp {
	
	public static void showMenu() {
		System.out.println("1.리스트  2.등록  3.삭제  4.수정  5.검색  6.종료");
		System.out.println("--------------------------------------------------");
		System.out.print(">메뉴번호 : ");
	}

	public static void showUpdateMenu() {
		System.out.println("1.이름  2.휴대전화  3.회사전화 4.종료");
		System.out.println("--------------------------------------------------");
		System.out.print(">메뉴번호 : ");
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		PersonDao personDao = new PersonDao();
		int count = -1;
		
		System.out.println("**************************************************");
		System.out.println("* \t\t전화번호 관리 프로그램\t\t *");
		System.out.println("**************************************************");
		
		while(true) {
			
			showMenu();
			int num = sc.nextInt();
			System.out.println();
			
			if(num == 6) {
				System.out.println("**************************************************");
				System.out.println("*  \t\t감사합니다\t\t\t *");
				System.out.println("**************************************************");
				break;
			} else {
				if(num > 7 || num <= 0) {
					System.out.println("[다시 입력해 주세요.]");
					System.out.println();
					continue;
				} else if (num == 1) {

					System.out.println("<1.리스트>");
					List<PersonVo> personList = personDao.personSelect();
					for(int i = 0; i < personList.size(); i++) {
						System.out.println("id : " + personList.get(i).getId()
								+ ", name : " + personList.get(i).getName()
								+ ", hp : " + personList.get(i).getHp()
								+ ", company : " + personList.get(i).getCompany()
								);
					}
					
				} else if(num == 2) {
					
					System.out.println("<2.등록>");
					System.out.print("이름 : ");
					String name = sc.next();
					System.out.print("휴대전화 : ");
					String hp = sc.next();
					System.out.print("회사전화 : ");
					String company = sc.next();
					
					count = personDao.personInsert(name, hp, company);
					System.out.println("[등록되었습니다.]");
					
				} else if(num == 3) {
					
					System.out.println("<3.삭제>");
					System.out.print("번호 : ");
					int no = sc.nextInt();
					
					count = personDao.personDelete(no);
					System.out.println("[삭제되었습니다.]");
					
				} else if(num == 4) {
					
					System.out.println("<4.수정>");
					System.out.print("번호 : ");
					int no = sc.nextInt();
					
					PersonVo personVo = personDao.personSelectOne(no);
					System.out.println("id : " + personVo.getId()
							+ ", name : " + personVo.getName()
							+ ", hp : " + personVo.getHp()
							+ ", company : " + personVo.getCompany()
							);
					System.out.println();
					
					while(true) {
						showUpdateMenu();
						int columnNo = sc.nextInt();
						System.out.println();
						
						if(columnNo == 4) {
							System.out.println("[수정이 종료되었습니다.]");
							break;
						} else {
							if(columnNo > 4 || no <= 0) {
								System.out.println("[다시 입력해 주세요.]");
								System.out.println();
								continue;
							} else {
								System.out.println("수정 정보 : ");
								String updateStr = sc.next();
								
								count = personDao.personUpdate(updateStr, columnNo, no);
								System.out.println("[수정되었습니다.]");
								
								PersonVo person = personDao.personSelectOne(no);
								System.out.println("id : " + person.getId()
										+ ", name : " + person.getName()
										+ ", hp : " + person.getHp()
										+ ", company : " + person.getCompany()
										);
							}
							System.out.println();
						}
					}
					
				} else if(num == 5) {
					
					System.out.println("<5.검색>");
					System.out.print("이름 : ");
					String str = sc.next();
					
					List<PersonVo> personList = personDao.personSearch(str);
					for(int i = 0; i < personList.size(); i++) {
						System.out.println("id : " + personList.get(i).getId()
								+ ", name : " + personList.get(i).getName()
								+ ", hp : " + personList.get(i).getHp()
								+ ", company : " + personList.get(i).getCompany()
								);
					}
					
				}
				System.out.println();
			}
		}
		sc.close();
	}
	
}
