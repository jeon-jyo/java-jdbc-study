package com.javaex.phonebook03;

import java.util.List;
import java.util.Scanner;

public class PersonService {

	public void showMenu() {
		System.out.println("1.리스트  2.등록  3.삭제  4.수정  5.검색  6.종료");
		System.out.println("--------------------------------------------------");
		System.out.print(">메뉴번호 : ");
	}

	public void showUpdateMenu() {
		System.out.println("1.이름  2.휴대전화  3.회사전화 4.종료");
		System.out.println("--------------------------------------------------");
		System.out.print(">메뉴번호 : ");
	}
	
	// 리스트
	public void personSelect() {
		System.out.println();
		System.out.println("<1.리스트>");
		
		PersonDao personDao = new PersonDao();

		List<PersonVo> personList = personDao.personSelect("");
		for(int i = 0; i < personList.size(); i++) {
			System.out.println("id : " + personList.get(i).getId()
					+ ", name : " + personList.get(i).getName()
					+ ", hp : " + personList.get(i).getHp()
					+ ", company : " + personList.get(i).getCompany()
			);
		}
		System.out.println();
	}
	
	// 등록
	public void personInsert() {
		System.out.println();
		System.out.println("<2.등록>");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 : ");
		String name = sc.nextLine();
		System.out.print("휴대전화 : ");
		String hp = sc.nextLine();
		System.out.print("회사전화 : ");
		String company = sc.nextLine();

		PersonVo personVo = new PersonVo(name, hp, company);
		PersonDao personDao = new PersonDao();

		int count = personDao.personInsert(personVo);
		if(count == 1) {
			System.out.println("[등록되었습니다.]");
		} else {
			System.out.println("[다시 시도해 주세요.]");
		}
		System.out.println();
	}
	
	// 삭제
	public void personDelete() {
		System.out.println();
		System.out.println("<3.삭제>");
		
		PersonDao personDao = new PersonDao();
		
		Scanner sc = new Scanner(System.in);
		System.out.print("번호 : ");
		String no = sc.nextLine();
		
		int num = Integer.parseInt(no);
		int count = personDao.personDelete(num);
		if(count == 1) {
			System.out.println("[삭제되었습니다.]");
		} else {
			System.out.println("[다시 시도해 주세요.]");
		}
		System.out.println();
	}
	
	// 수정
	public void personUpdate() {
		System.out.println();
		System.out.println("<4.수정>");
		
		PersonDao personDao = new PersonDao();

		Scanner sc = new Scanner(System.in);
		System.out.print("번호 : ");
		String no = sc.nextLine();

		int num = Integer.parseInt(no);
		PersonVo personVo = personDao.personSelectOne(num);
		System.out.println("id : " + personVo.getId()
				+ ", name : " + personVo.getName()
				+ ", hp : " + personVo.getHp()
				+ ", company : " + personVo.getCompany()
		);
		System.out.println();

		String columnNo = null;
		do {
			showUpdateMenu();
			columnNo = sc.nextLine();

			switch (columnNo) {
				case "1", "2", "3" : {
					System.out.print("수정 정보 : ");
					String updateStr = sc.nextLine();
					int columnNum = Integer.parseInt(columnNo);
					update(updateStr, columnNum, num);
					break;
				}
				case "4" : {
					System.out.println();
					System.out.println("[수정이 종료되었습니다.]");
					break;
				}
				default :
					System.out.println();
					System.out.println("[다시 입력해 주세요.]");
					System.out.println();
					break;
			}

		} while (!columnNo.equals("4"));
		System.out.println();
	}
	
	// 수정2
	public void update(String updateStr , int columnNum, int no) {
		
		PersonDao personDao = new PersonDao();
		
		int count = personDao.personUpdate(updateStr, columnNum, no);
		if(count == 1) {
			System.out.println("[수정되었습니다.]");
		} else {
			System.out.println("[다시 시도해 주세요.]");
		}
		
		PersonVo personVo = personDao.personSelectOne(no);
		System.out.println("id : " + personVo.getId()
				+ ", name : " + personVo.getName()
				+ ", hp : " + personVo.getHp()
				+ ", company : " + personVo.getCompany()
		);
		System.out.println();
	}
	
	// 검색
	public void personSearch() {
		System.out.println();
		System.out.println("<5.검색>");
		
		Scanner sc = new Scanner(System.in);
		System.out.print("이름 : ");
		String str = sc.nextLine();
		
		PersonDao personDao = new PersonDao();
		
		List<PersonVo> personList = personDao.personSelect(str);
		if (personList.size() > 0) {
			System.out.println("검색 결과 : " + personList.size() + "건");

			for(int i = 0; i < personList.size(); i++) {
				System.out.println("id : " + personList.get(i).getId()
						+ ", name : " + personList.get(i).getName()
						+ ", hp : " + personList.get(i).getHp()
						+ ", company : " + personList.get(i).getCompany()
				);
			}
		} else {
			System.out.println("검색 결과가 없습니다.");
		}
		System.out.println();
	}
	
}
