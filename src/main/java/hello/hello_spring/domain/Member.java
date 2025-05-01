package hello.hello_spring.domain;

public class Member {

    private long id;
    private String name;
    //두개의 식별자 필요


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
