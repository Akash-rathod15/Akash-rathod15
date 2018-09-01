package com.scp.app.util;

import java.util.List;

public class TestDbLogic {
	
	public static void main(String[] args) {
		//DataBaseUtil.createTable();
		//DataBaseUtil.insertDummyRecords(10);
		List<Emp> listOfEmps = DataBaseUtil.fetchAllDataFromDB();
		System.out.println("Here is your list of emps coming from db-----" +listOfEmps);
		//DataBaseUtil.fetchBasedOnId(int id);
	}

}


class Emp{
	private int empId;
	private String empName;
	private String empAddress;
	
	@Override
	public String toString() {
		return "\nEmp [empId=" + empId + ", empName=" + empName + ", empAddress="
				+ empAddress + "]";
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	
}
