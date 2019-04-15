class Profile implements IDisplay{
    private Friend friends;
    private User user;
    private Post posts;
    private boolean isProfileVisibile;
    private String identifier;
    
    public Profile(String name, String last, int age){
        user = new User(name, last, age);
        friends = new Friend();
        posts = new Post();
        isProfileVisibile = true;
        generateID(name, last);
    }

    public void generateID(String name, String last){
        this.identifier = Character.toString(name.charAt(0)) + Character.toString(last.charAt(0)) + "-" + (int)(Math.random() * 999 + 1);
    }

    public String getID(){
        return identifier;
    }

    public String getName(){
        return user.getName();
    }
    public String getLast(){
        return user.getLast();
    }
    //Checks if stack is full before accepting input
    public boolean isFriendsFull(){
        return friends.isStackFull();
    }
    //Checks if stack is full before accepting input
    public boolean isPostsFull(){
        return posts.isStackFull();
    }

    public void display(){
        if(isProfileVisibile){
            Util.print("ID: " + getID());
            user.display();
            friends.display();
            Util.print("");
            posts.display();
        }
    }

    public void toggleVisibility(){
        isProfileVisibile = !isProfileVisibile;
    }
    //Actions
    public void setStatus(String status){
        user.setStatus(status);
    }
    public void addFriend(String friendName){
        friends.addFriend(friendName);
    }
    public void removeFriend(){
        friends.remoFriend();
    }
    public void removeAllFriends(){
        friends.removeAllFriends();
    }

    public void addPost(String post){
        posts.addPost(post);
    }
    public void removePost(){
        posts.remoPost();
    }
    public void removeAllPosts(){
        posts.removeAllPosts();
    }
    
    public void toggleAge(){
        user.toggleVisibility();
    }
    public void toggleFriends(){
        friends.toggleVisibility();
    }
    public void togglePosts(){
        posts.toggleVisibility();
    }
}