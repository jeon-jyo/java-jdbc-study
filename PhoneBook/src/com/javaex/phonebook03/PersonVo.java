package com.javaex.phonebook03;

public class PersonVo {

	private int id;
	private String name;
	private String hp;
	private String company;
	
	public PersonVo() {}

	public PersonVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	public PersonVo(int id, String name, String hp, String company) {
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "PersonVo [id=" + id + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
}
