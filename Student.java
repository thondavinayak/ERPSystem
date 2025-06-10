package model;

public class Student {
	String name;
	int age;
	String sex;
	String lastdegree;
	
	public Student(String n, int a, String s, String d) {
		name = n;
		age = a;
		sex = s;
		lastdegree = d;
	}
	
	public String toString() {
		String s = " Student name : ";
		s += name;
		s += " , age : ";
		s += age;
		s += " , sex : ";
		s += sex;
		s += " , lastdegree ";
		s += lastdegree;
		return s;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLastDegree() {
        return lastdegree;
    }

    public void setLastDegree(String lastDegree) {
        this.lastdegree = lastDegree;
    }
	
}
