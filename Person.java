public abstract class Person{
    private String name;
    private String last;
    private int age;

    public Person (String name, String last, int age){
        this.name = name;
        this.last = last;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public String getLast(){
        return last;
    }

    public int getAge(){
        return age;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setLast(String last){
        this.last = last;
    }
    public void setAge(int age){
        this.age = age;
    }
}