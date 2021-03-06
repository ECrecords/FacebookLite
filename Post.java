class Post implements IDisplay{
    private Stack posts;
    private boolean isPostsVisible;

    public String[] getPostArray(){
            return posts.getArray();
    }

    public Post(){
        posts = new Stack(5);
        isPostsVisible = true;
    }
    public boolean isStackFull(){
        return posts.isFull();
    }
    public void addPost(String post){
        posts.push(post);
    }
    public void remoPost(){
        posts.pop();
    }
    public void removeAllPosts(){
        posts.reset();
    }
    public void display(){
        if(isPostsVisible){
            if(!posts.isEmpty()){
                Util.printWL("POSTS: ");
                posts.print();
            }       
        }
    }
    public void toggleVisibility(){
        isPostsVisible = !isPostsVisible;
    }
}