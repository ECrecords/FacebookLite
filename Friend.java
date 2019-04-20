class Friend implements IDisplay{
    private Stack friends;
    private boolean isFriendsVisible;
    
    public String[] getFriendArray(){
            return friends.getArray();
    }
    
    public Friend(){
        friends = new Stack(5);
        isFriendsVisible = true;
    }
    public boolean isStackFull(){
        return friends.isFull();
    }
    public void addFriend(String friendName){
        friends.push(friendName);
    }
    public void remoFriend(){
        friends.pop();
    }
    public void removeAllFriends(){
        friends.reset();
    }

    
    public void display(){
        if(isFriendsVisible){
            if(!friends.isEmpty()){
                Util.printWL("FRIENDS: ");
                friends.print();
            }   
        }
    }
    public void toggleVisibility(){
        isFriendsVisible = !isFriendsVisible;
    }
}