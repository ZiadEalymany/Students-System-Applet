package task.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "department")
	private String department;
//	private String[] department = { "Computers", "Electric", "Architecture", "Civil" };

	@Column(name = "phone_number")
	private String phoneNumber;

	public Student() {

	}

	public Student(String id, String name, String department, String phoneNumber) {
		setId(id);
		setName(name);
		setDepartment(department);
		setPhoneNumber(phoneNumber);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", department=" + department + ", phoneNumber=" + phoneNumber
				+ "]";
	}
}
