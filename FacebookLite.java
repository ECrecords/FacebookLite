/*
Author: Elvis Chino-Islas
Date Completed: March 19, 2018 - 3:30 pm
App: FacebookLite 
Purpose: Build a system with basic java language 
*/
class FacebookLite{
    private ProfileStack profileStack;

    public FacebookLite(){
        profileStack = new ProfileStack(5);
    }

    //Main
    public static void main(String[] args){
        FacebookLite facebookLite = new FacebookLite();//creates object from FacebooLite class
        Util.printOptionArray();
        boolean continueLoop = true;//Flag to check if exited
        while(continueLoop){
            //creating a refrence for current Profile
            Profile currentProfile = null;
            if (facebookLite.profileStack.getProfileIndex() > -1){
                currentProfile = facebookLite.profileStack.getProfileArray()[facebookLite.profileStack.getProfileIndex()];
            }
            int userInput = facebookLite.profileStack.validateIntInput("\nENTER CHOICE: ");
            int userIndex = facebookLite.profileStack.findOptionIndex(userInput);
            switch(userIndex){
                //Exit
                case 0:
                    facebookLite.profileStack.write();
                    continueLoop = false;
                    break;
                //Create Profile
                case 1:
                    facebookLite.profileStack.createProfile();
                    break;
                //Profile - Remove 
                case 2:
                    facebookLite.profileStack.removeProfile();
                    break;
                //Profiles - Reset 
                case 3:
                    facebookLite.profileStack.resetProfiles();
                    break;
                //Profile - Switch
                case 4:
                    facebookLite.profileStack.switchProfile();
                    break;
                //Friend - add
                case 5:
                    if(!facebookLite.profileStack.atLeastOneProfile()){
                        if(!currentProfile.isFriendsFull()){
                            String friendname = facebookLite.profileStack.validateStrInput("INPUT FREIND'S NAME: ");
                            currentProfile.addFriend(friendname);
                        }
                        else{
                            Util.print("------ MAX FREIND SIZE EXCEEDED ------");
                        }
                    }
                    break;
                //Friend - remove
                case 6:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        currentProfile.removeFriend();
                    break;
                //Friends - reset
                case 7:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        currentProfile.removeAllFriends();
                    break;
                //Post - add
                case 8:
                    if(!facebookLite.profileStack.atLeastOneProfile()){
                        if(!currentProfile.isPostsFull()){
                            Util.printWL("INPUT POST: ");
                            String post = facebookLite.profileStack.getScanner().nextLine();
                            currentProfile.addPost(post);
                        }
                        else{
                            Util.print("------ MAX POST SIZE EXCEEDED ------");
                        }
                    }
                    break;
                //Post - remove
                case 9:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        currentProfile.removePost();
                    break;
                //Post - reset
                case 10:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        currentProfile.removeAllPosts();
                    break;
                //Toggles 
                case 11:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        currentProfile.toggleAge();
                    break;
                case 12:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        currentProfile.toggleFriends();
                    break;
                case 13:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        currentProfile.togglePosts();
                    break;
                case 14:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        currentProfile.toggleVisibility();
                    break;
                //Sets Status
                case 15:
                    if(!facebookLite.profileStack.atLeastOneProfile()){
                        Util.printWL("SET STATUS: ");
                        String status = facebookLite.profileStack.getScanner().nextLine();
                        currentProfile.setStatus(status);
                    }
                    break;
                //Displays current
                case 16:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        currentProfile.display();
                    break;
                case 17:
                    if(!facebookLite.profileStack.atLeastOneProfile())
                        facebookLite.profileStack.printAllProfiles();
                    break;
                default:
                    Util.print("------ OUT OF RANGE ------");
                    Util.printOptionArray();
                    break;
            }
        }
    }
}