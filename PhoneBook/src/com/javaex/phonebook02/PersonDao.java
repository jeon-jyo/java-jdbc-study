package com.javaex.phonebook02;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
	
	String dataFile = "C:\\Jiho\\HiMedia\\workspace_himedia_java_2\\PhoneBook\\PhoneDB.txt";
	
	// 저장
	public int personSave(List<PersonVo> personList) {
		
		int count = -1;
		
		try (FileOutputStream fos = new FileOutputStream(dataFile);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {

			for(int i = 0; i < personList.size(); i++) {
				String str = personList.get(i).getName() + "," + personList.get(i).getHp() + "," + personList.get(i).getCompany() + "\n";
				bos.write(str.getBytes());
			}
			count = 1;
			
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
		
		return count;
	}
	
	// 리스트
	public List<PersonVo> personSelect() {
		
		List<PersonVo> personList = new ArrayList<PersonVo>();
		
		try (InputStream fis = new FileInputStream(dataFile);
				InputStreamReader isr = new InputStreamReader(fis);
				BufferedReader br = new BufferedReader(isr)) {
			
			String line = null;

			while((line = br.readLine()) != null) {
				
				String[] words = line.trim().split(",");
				String name = words[0];
				String hp = words[1];
				String company = words[2];

				PersonVo personVo = new PersonVo();
				personVo.setName(name);
				personVo.setHp(hp);
				personVo.setCompany(company);
				
				personList.add(personVo);
			}

		} catch (FileNotFoundException e) {
			System.out.println("error : " + e);
		} catch (IOException e) {
			System.out.println("error : " + e);
		}
		
		return personList;
	}
	
	// 등록
	public int personInsert(PersonVo personVo) {
		
		List<PersonVo> personList = personSelect();
		personList.add(personVo);
		
		int count = personSave(personList);
		
		return count;
	}
	
	// 삭제
	public int personDelete(int no) {
		
		List<PersonVo> personList = personSelect();
		
		for(int i = 0; i < personList.size(); i++) {
			if(i == (no-1)) {
				personList.remove(i);
			}
		}
		
		int count = personSave(personList);
		
		return count;
	}
	
	// 한 명 조회
	public PersonVo personSelectOne(int no) {
		
		List<PersonVo> personList = personSelect();
		PersonVo personVo = null;
		
		for(int i = 0; i < personList.size(); i++) {
			if(i == (no-1)) {
				personVo = new PersonVo(
						personList.get(i).getName(), personList.get(i).getHp(), personList.get(i).getCompany());
			}
		}
		
		return personVo;
	}
	
	// 수정
	public int personUpdate(String updateStr , String columnNo, int no) {
		
		List<PersonVo> personList = personSelect();
		
		int num = Integer.parseInt(columnNo);
		int index = no-1;
		
		if(num == 1) {
			personList.set(no-1, new PersonVo(
					updateStr, personList.get(index).getHp(), personList.get(index).getCompany()));
		} else if(num == 2) {
			personList.set(no-1, new PersonVo(
					personList.get(index).getName(), updateStr, personList.get(index).getCompany()));
		} else if(num == 3) {
			personList.set(no-1, new PersonVo(
					personList.get(index).getName(), personList.get(index).getHp(), updateStr));
		}
		
		int count = personSave(personList);
		
		return count;
	}
	
	// 검색
	public List<PersonVo> personSearch(String word) {
		
		List<PersonVo> personList = personSelect();
		List<PersonVo> searchList = new ArrayList<PersonVo>();

		for(int i = 0; i < personList.size(); i++) {
			if(personList.get(i).getName().contains(word)) {
				searchList.add(new PersonVo(
						personList.get(i).getName(), personList.get(i).getHp(), personList.get(i).getCompany()));
			}
		}
		
		return searchList;
	}
	
}
