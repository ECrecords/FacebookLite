/*
Author: Elvis Chino-Islas
Date Completed: March 19, 2018 - 3:30 pm
App: FacebookLite 
Purpose: Build a system with basic java language 
*/
import java.util.Scanner;

class FacebookLite{
    private Scanner scanInput;
    private Profile[] profileArray;
    private int profileIndex;

    public FacebookLite(){
        scanInput = new Scanner(System.in);
        profileIndex = -1;
        profileArray = new Profile[5];
    }
    
    /* Boolean Methods */
    //Return true is more and one profile was created/if more than one profile exists in the Profile[].
    public boolean moreThanOneProfile(){
        int profileCount = 0;
        for(int i = 0; i < profileArray.length; i++){
            if(profileArray[i] != null) {
                profileCount++;
            }
        }
            if(profileCount > 1){
                return true;
            }
            else{
                return false;
            }
    }
    public boolean isMaxProfile(){
        int profileCount = 0;
        for(int i = 0; i < profileArray.length; i++){
            if(profileArray[i] != null) {
                profileCount++;
            }
        }
            if(profileCount == 5){
                return true;
            }
            else{
                return false;
            }
    }
    //Profile Stack
    public boolean atLeastOneProfile(){
        if(profileIndex == -1){
            Util.print("------ CREATE PROFILE FIRST ------");
            return true;
        }
        else{
            return false;
        }
    }
    //Boolean Methods End.

    /* Actions for FacebookLite */
    public void createProfile(String name, String last, int age){
        Profile profile = new Profile(name,last,age);
        profileIndex++;
        profileArray[profileIndex] = profile;
    }

    public Profile removeProfile(){
        if(atLeastOneProfile()){
            Util.print("----- THERE ARE NO PROFILES TO DELETE ------");
            return null; 
        }
        else{
            Profile temp = profileArray[profileIndex];
            profileIndex--;
            return temp;
            }
        }
    public void resetProfiles(){
        if(atLeastOneProfile()){
            Util.print("------ THE ARE NO PROFILES TO RESET ------");
            return;
        }
        else{
            Util.init(profileArray);
            profileIndex = -1;
        }
    }
    /*
    Only Supports Unique First Names
    Input is the first name to swtich to an array index
    Can be switched to an integer index to support dupplicate names
    */
    public void switchProfile(String userName){
            int index = -1;
            for(int i = 0; i < profileArray.length; i++){
                if(profileArray[i] != null){
                    if(userName.equalsIgnoreCase(profileArray[i].getName())){
                        index = i;
                    }
                }
            }
                if(index > -1){
                    profileIndex = index;
                }
                else{
                    Util.print("------ INVALID NAME ------");
                }
    }
    /*
    Takes in userInput and returns corresponding optionIndex or -1
    */
    public int findOptionIndex(String input){
        int index = -1;
        for(int i = 0; i < Util.getOptionArray().length; i++){
            if(input.equalsIgnoreCase(Util.getOptionArray()[i])){
                index = i;
            }
        }
        return index; 
    }


    
    //Main
    public static void main(String[] args){
        FacebookLite facebookLite = new FacebookLite();//creates object from FacebooLite class
        String[] optionArray = Util.getOptionArray();
        Util.printOptionArray();
        Util.printInputPrompt();
        boolean continueLoop = true;//Flag to check if exited
        while(continueLoop){
            //creating a refrence for current Profile
            Profile currentProfile = null;
            if (facebookLite.profileIndex > -1)
                currentProfile = facebookLite.profileArray[facebookLite.profileIndex];
            String userInput = facebookLite.scanInput.nextLine();
            int userIndex = facebookLite.findOptionIndex(userInput);
            switch(userIndex){
                //Exit
                case 0:
                    continueLoop = false;
                    break;
                //Create Profile
                case 1:
                    if(!facebookLite.isMaxProfile()){
                        Util.printWL("\nINPUT FIRST NAME: ");
                        String name = facebookLite.scanInput.nextLine();
                        Util.printWL("INPUT LAST NAME: ");
                        String last = facebookLite.scanInput.nextLine();
                        Util.printWL("INPUT AGE: ");
                        int age = Integer.parseInt(facebookLite.scanInput.nextLine());
                        facebookLite.createProfile(name, last, age);
                    }
                    else{
                        Util.print("------ MAX PROFILE EXCEEDED ------");
                    }
                    break;
                //Profile - Remove 
                case 2:
                    if(!facebookLite.atLeastOneProfile())//checking if their is more than one profile
                        facebookLite.removeProfile();
                    break;
                //Profiles - Reset 
                case 3:
                    if(!facebookLite.atLeastOneProfile())
                        facebookLite.resetProfiles();
                    break;
                //Profile - Switch
                case 4:
                    if(facebookLite.moreThanOneProfile()){
                        Util.printWL("INPUT PROFILE NAME: ");
                        String userName = facebookLite.scanInput.nextLine();
                        facebookLite.switchProfile(userName);
                    }
                    else{
                        Util.print("------ NEED MORE THAN ONE PROFILE ------");
                    }
                    break;
                //Friend - add
                case 5:
                    if(!facebookLite.atLeastOneProfile()){
                        if(!currentProfile.isFriendsFull()){
                            Util.printWL("INPUT FREIND'S NAME: ");
                            String friendname = facebookLite.scanInput.nextLine();
                            currentProfile.addFriend(friendname);
                        }
                        else{
                            Util.print("------ MAX FREIND SIZE EXCEEDED ------");
                        }
                    }
                    break;
                //Friend - remove
                case 6:
                    if(!facebookLite.atLeastOneProfile())
                        currentProfile.removeFriend();
                    break;
                //Friends - reset
                case 7:
                    if(!facebookLite.atLeastOneProfile())
                        currentProfile.removeAllFriends();
                    break;
                //Post - add
                case 8:
                    if(!facebookLite.atLeastOneProfile()){
                        if(!currentProfile.isPostsFull()){
                            Util.printWL("INPUT POST: ");
                            String post = facebookLite.scanInput.nextLine();
                            currentProfile.addPost(post);
                        }
                        else{
                            Util.print("------ MAX POST SIZE EXCEEDED ------");
                        }
                    }
                    break;
                //Post - remove
                case 9:
                    if(!facebookLite.atLeastOneProfile())
                        currentProfile.removePost();
                    break;
                //Post - reset
                case 10:
                    if(!facebookLite.atLeastOneProfile())
                        currentProfile.removeAllPosts();
                    break;
                //Toggles 
                case 11:
                    if(!facebookLite.atLeastOneProfile())
                        currentProfile.toggleAge();
                    break;
                case 12:
                    if(!facebookLite.atLeastOneProfile())
                        currentProfile.toggleFriends();
                    break;
                case 13:
                    if(!facebookLite.atLeastOneProfile())
                        currentProfile.togglePosts();
                    break;
                case 14:
                    if(!facebookLite.atLeastOneProfile())
                        currentProfile.toggleVisibility();
                    break;
                //Sets Status
                case 15:
                    if(!facebookLite.atLeastOneProfile()){
                        Util.printWL("SET STATUS: ");
                        String status = facebookLite.scanInput.nextLine();
                        currentProfile.setStatus(status);
                    }
                    break;
                //Displays current
                case 16:
                    if(!facebookLite.atLeastOneProfile())
                        currentProfile.display();
                        break;
                default:
                    Util.print("------ INVALID INPUT ------");
                    Util.printOptionArray();
                    break;
            }
            Util.printInputPrompt();
        }
    }
}