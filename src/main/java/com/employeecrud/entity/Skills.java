package com.employeecrud.entity;

import java.util.Objects;

public class Skills {
	
	private  int skillId;
	private String skill;
	private int employeeId;
	
	public Skills() {
		super();
	}
	public int getSkillId() {
		return skillId;
	}
	
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	
	public String getSkill() {
		return skill;
	}
	
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Skills(String skill, int employeeId) {
		super();
		this.skill = skill;
		this.employeeId = employeeId;
	}

	public Skills(String skill) {
		super();
		this.skill = skill;
	}
	public boolean contains(String[] employeeSkills) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
		if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
		Skills skillNames = (Skills) obj;
        return skill.equals(skillNames.skill);
	}

	@Override
	public int hashCode() {
		return Objects.hash(skill);
	}
	
	@Override
	public String toString() {
		return "Skills [skillId=" + skillId + ", skill=" + skill + ", employeeId=" + employeeId + "]";
	}
}
