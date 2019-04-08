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
    public boolean validateStringInput(String input){
        int digitCount = 0;
            for(int i = 0; i < input.length(); i++){
                if(Character.isDigit(input.charAt(i))){
                    digitCount++;
                }
            }
        if(digitCount != 0 || input.length() < 1){
            if(digitCount != 0){
                Util.print("------ INVALID INPUT: INPUT CONTAINS DIGIT ------");
            }
            else{
                Util.print("------ INVALID INPUT: NEED MORE THAN ONE CHARACTER ------");
            }
            return false;
        }
        return true;
    }
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
    //Checks if recently created profile already exists
    public boolean profileAlreadyExsists(String name, String last){
        for(int i = 0; i < profileArray.length; i++){
            if(profileArray[i] != null){
                if(profileArray[i].getName().equalsIgnoreCase(name)){
                    if(profileArray[i].getLast().equalsIgnoreCase(last)){
                        Util.print("------ Profile Already Exsists ------");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //Boolean Methods End.

    /* Actions for FacebookLite */
    public void createProfile(){
        if(!isMaxProfile()){
            Util.printWL("\nINPUT FIRST NAME: ");
            String name = scanInput.nextLine();
            Util.printWL("INPUT LAST NAME: ");
            String last = scanInput.nextLine();
            int age = 0;
            boolean ageFlag = true;
            boolean existanceFlag = profileAlreadyExsists(name, last);
            if(existanceFlag){
                while(ageFlag){
                    try{
                        Util.printWL("INPUT AGE: ");
                        age = Integer.parseInt(scanInput.nextLine());
                        ageFlag = false;
                    }
                    catch(NumberFormatException nfe){
                        Util.print("------ INVALID INPUT ------");
                    }
                }
                Profile profile = new Profile(name,last,age);
                boolean createFlag = true;
                while(createFlag){
                    profileIndex++;
                    if(profileArray[profileIndex]== null){
                        profileArray[profileIndex] = profile;
                        Util.print("PROFILE CREATED : " + name);
                        createFlag = false;
                    }
                }
            }
        }
        else{
            Util.print("------ MAX PROFILES SIZE EXCEEDED ------");
        }
        
    }

    public void removeProfile(){
        if(!atLeastOneProfile()){
            Util.print("Profile: " + profileArray[profileIndex].getName() + " --- DELETED");
            profileArray[profileIndex] = null;
            profileIndex--;
        }
    }
    public void resetProfiles(){
        if(!atLeastOneProfile()){
            Util.init(profileArray);
            profileIndex = -1;
            Util.print("\nALL PROFILES RESET");
        }    
    }
    /*
    Only Supports Unique First Names
    Input is the first name to swtich to an array index
    Can be switched to an integer index to support dupplicate names
    */
    public void switchProfile(){
        if(!atLeastOneProfile()){
            if(moreThanOneProfile()){
                printProfilesNames();
                Util.printWL("\n\nINPUT PROFILE DATA - FIRST NAME: ");
                String userName = scanInput.nextLine();
                Util.printWL("INPUT PROFILE DATA - LAST NAME: ");
                String userLastName = scanInput.nextLine();
                int index = -1;
                for(int i = 0; i < profileArray.length; i++){
                    if(profileArray[i] != null){
                        if(userName.equalsIgnoreCase(profileArray[i].getName())){
                            if(userLastName.equalsIgnoreCase(profileArray[i].getLast())){
                                index = i;
                            }
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
            else{
                Util.print("------ NEED MORE THAN ONE PROFILE ------");
            }
        }            
    }

    public void printProfilesNames(){
        int x = 0;
        Util.print("------ AVAILABLE PROFILES ------");
        for(int i = 0; i < profileArray.length; i++){
            if(x == 2){
                Util.print("");
                x = 0;
            }
            if(profileArray[i] != null){
                Util.printWL("Profile: " + profileArray[i].getName() + "\t");
                x++;
            }
        }
    }
    /*
    Takes in userInput and returns corresponding optionIndex or -1
    */
    public int findOptionIndex(int input){
        int index = -1;
        for(int i = 0; i < Util.getIntOptionArray().length; i++){
            if(input == Util.getIntOptionArray()[i]){
                index = i;
            }
        }
        return index; 
    }


    
    //Main
    public static void main(String[] args){
        FacebookLite facebookLite = new FacebookLite();//creates object from FacebooLite class
        Util.printOptionArray();
        boolean continueLoop = true;//Flag to check if exited
        while(continueLoop){
            //creating a refrence for current Profile
            Profile currentProfile = null;
            if (facebookLite.profileIndex > -1){
                currentProfile = facebookLite.profileArray[facebookLite.profileIndex];
            }
            int userInput = 0;
            boolean inputFlag = true;
            while(inputFlag){
                try{
                    Util.printInputPrompt();
                    userInput = Integer.parseInt(facebookLite.scanInput.nextLine());
                    inputFlag = false;
                }
                catch(NumberFormatException error){
                    Util.print("------ INVALID INPUT ------");
                }
            }
            int userIndex = facebookLite.findOptionIndex(userInput);
            switch(userIndex){
                //Exit
                case 0:
                    continueLoop = false;
                    break;
                //Create Profile
                case 1:
                    facebookLite.createProfile();
                    break;
                //Profile - Remove 
                case 2:
                    facebookLite.removeProfile();
                    break;
                //Profiles - Reset 
                case 3:
                    facebookLite.resetProfiles();
                    break;
                //Profile - Switch
                case 4:
                    facebookLite.switchProfile();
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
                    Util.print("------ OUT OF RANGE ------");
                    Util.printOptionArray();
                    break;
            }
        }
    }
}