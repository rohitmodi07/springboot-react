package net.javapractical.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private long employeeId;

    @Column(name = "employee_mail_id")
    private String employeeEmailId;

    @Column(name = "level")
    private String level;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "date_of_joining")
    private String dateOfJoining;
    
    @Column(name = "date_of_resignation")
    private String dateOfResignation;
    
    @Column(name = "last_working_day")
    private String lastWorkingDay;
    
    @Column(name = "resignation_reason")
    private String resReason;
    
    @Column(name = "manager_email_id")
    private String managerEmailId;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeEmailId() {
		return employeeEmailId;
	}

	public void setEmployeeEmailId(String employeeEmailId) {
		this.employeeEmailId = employeeEmailId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getDateOfResignation() {
		return dateOfResignation;
	}

	public void setDateOfResignation(String dateOfResignation) {
		this.dateOfResignation = dateOfResignation;
	}

	public String getLastWorkingDay() {
		return lastWorkingDay;
	}

	public void setLastWorkingDay(String lastWorkingDay) {
		this.lastWorkingDay = lastWorkingDay;
	}

	public String getResReason() {
		return resReason;
	}

	public void setResReason(String resReason) {
		this.resReason = resReason;
	}
    
	public String getManagerEmailId() {
		return managerEmailId;
	}

	public void setManagerEmailId(String managerEmailId) {
		this.managerEmailId = managerEmailId;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeEmailId=" + employeeEmailId + ", level=" + level
				+ ", designation=" + designation + ", dateOfJoining=" + dateOfJoining + ", dateOfResignation="
				+ dateOfResignation + ", lastWorkingDay=" + lastWorkingDay + ", resReason=" + resReason
				+ ", managerEmailId=" + managerEmailId + "]";
	}
	
}
