package hello.hellospring.controller;

public class MemberForm {
    private String name;       //createMemberForm.html에서 입력한 name이랑 값이 매칭됨. ("name")

    public String getName(){
        return name;
    }

    public void setName(String name){ //스프링이 setName을 호출해서 name을 저장함.
        this.name = name;
    }
}
