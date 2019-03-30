class Stack {
    private int size;
    private int top;
    private String[] arr;

    public Stack(int size){
        this.size = size;
        top = -1;
        arr = new String[size];
        Util.init(arr);
    }
    public boolean isEmpty(){
        if(top == -1){
            return true;
        }
        return false;
    }

    public boolean isFull(){
        if(top ==(size-1)){
            return true;
        }
        return false;
    }
    public void push(String item){
        if(isFull()){
            return;
        }
        top++;
        arr[top] = item;
    }
    public String pop(){
        if(isEmpty()){
            return "";
        }
        String item = arr[top];
        top--;
        return item;
    }
    public void reset(){
        Util.init(arr);

    }
    public void print(){
        Util.print(arr);
    }
}