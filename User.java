class User extends Person implements IDisplay{
    private String status;
    private boolean isAgeVisible;

    public User(String name, String last, int age){
        super(name, last, age);
        status = "What are you doing today?";
        isAgeVisible = true;

    }

    public String getStatus(){
        return status;
    }
    public void display(){
        Util.printWL("NAME: ");
        Util.print(getName());
        Util.printWL("LAST: ");
        Util.print(getLast());
        if(isAgeVisible){
            Util.printWL("AGE: ");
            Util.print(getAge());
        }
        Util.printWL("STATUS: ");
        Util.print(status);

    }
    public void toggleVisibility(){
        isAgeVisible = !isAgeVisible;
    }
    public void setStatus(String status){
        this.status = status;
    }
}