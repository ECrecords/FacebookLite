class Profile implements IDisplay{
    private Friend friends;
    private User user;
    private Post posts;
    private boolean isProfileVisibile;
    
    public Profile(String name, String last, int age){
        user = new User(name, last, age);
        friends = new Friend();
        posts = new Post();
        isProfileVisibile = true;
    }

    public String getName(){
        return user.getName();
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