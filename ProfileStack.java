import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class ProfileStack{
    private int size;
    private Scanner scanInput;
    private Profile[] profileArray;
    private int profileIndex;

    public ProfileStack(int size){
        this.size = size;
        scanInput = new Scanner(System.in);
        profileIndex = -1;
        profileArray = new Profile[size];
    }
    
    /* Getters */
    public Scanner getScanner(){
        return scanInput;
    }
    public Profile[] getProfileArray(){
        return profileArray;
    }
    public int getProfileIndex(){
        return profileIndex;
    }

    /* Input Invalidation */
    public String validateStrInput(String text){
        String userInput = "";
        while(true){
            Util.printWL(text);
            userInput = scanInput.nextLine();
            int digitCount = 0;
            for(int i = 0; i < userInput.length(); i++){
                if(Character.isDigit(userInput.charAt(i))){
                    digitCount++;
                }
            }
            if(digitCount != 0 || userInput.length() < 2){
                if(digitCount != 0){
                    Util.print("------ INVALID INPUT: INPUT CONTAINS DIGIT ------");
                }
                else{
                    Util.print("------ INVALID INPUT: NEED MORE THAN ONE CHARACTER ------");
                }
            }
            else{
                break;
            }
        } 
        return userInput;
    }

    public int validateIntInput(String text){
        int intInput = 0;
        while(true){
            try{
                Util.printWL(text);
                intInput = Integer.parseInt(scanInput.nextLine());
                break;
            }
            catch(NumberFormatException nfe){
                Util.print("------ INVALID INPUT ------");
            }
        }
        return intInput;
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
    //Checks if recently created profile already exists
    public boolean profileAlreadyExsists(String name, String last){
        for(int i = 0; i < profileArray.length; i++){
            if(profileArray[i] != null){
                if(profileArray[i].getName().equalsIgnoreCase(name)){
                    if(profileArray[i].getLast().equalsIgnoreCase(last)){
                        Util.print("------ PROFILE ALREADY EXSISTS ------");
                        return false;
                    }
                }
            }
        }
        return true;
    }
    //Boolean Methods End.

    /* IO read & write methods */
    public void read(){
        try{
            Scanner readInput = new Scanner(new File("profile.txt"));
            Util.print("------ \'profile.txt\' FOUND, LOADING SAVED PROFILES ------");
            while(readInput.hasNextLine()){
                String profileLine = readInput.nextLine();

                String[] splitTwo = profileLine.split("Profile");
                int readIndex = Integer.parseInt(splitTwo[1]); //Index found

                String userLine = readInput.nextLine();

                String[] userSplit = userLine.split(",");
                String[] idSplit = userSplit[0].split("ID:");
                String readID = idSplit[1]; //ID found

                String[] nameSplit = userSplit[1].split("Name:");
                String readName = nameSplit[1];

                String[] lastSplit = userSplit[2].split("Last:");
                String readLast = lastSplit[1];

                String[] ageSplit = userSplit[3].split("Age:");
                int readAge = Integer.parseInt(ageSplit[1]);

                String[] statusSplit = userSplit[4].split("Status:");
                String readStatus = statusSplit[1];
                
                Profile profile = new Profile(readName,readLast,readAge);
                profile.setID(readID);
                profile.setStatus(readStatus);
                
                String friendsLine = readInput.nextLine();

                String[] friendsSplit = friendsLine.split("-Friends:");
                if(friendsSplit.length !=0){
                    String[] friendsArray = friendsSplit[1].split(",");
                    for(int i = 0; i < friendsArray.length; i++){
                        if(friendsArray[i] != null){
                            profile.addFriend(friendsArray[i]);
                        }
                    }
                }
                    
                String postsLine = readInput.nextLine();
                String[] postsSplit = postsLine.split("-Posts:");
                if(postsSplit.length != 0){
                    String[] postsArray = postsSplit[1].split(",");
                    for(int i = 0; i < postsArray.length; i++){
                        if(postsArray[i] != null){
                            profile.addPost(postsArray[i]);
                        }
                    }
                }
                    

                profileIndex++;
                profileArray[profileIndex] = profile;
                Util.print("Profile: " + profileArray[profileIndex].getName() + " " + profileArray[profileIndex].getLast() + 
                    " (" + profileArray[profileIndex].getID() + ")" + " --- LOADED");

            }
            Util.print(" ");
        }
        catch(IOException e){
            System.out.println("------ NO FILE FOUND ------\n");
        }
    }

    public void write(){
        try{
            PrintStream writer = new PrintStream(new File("profile.txt"));
            for(int i = 0; i < profileArray.length; i++){
                if(profileArray[i] != null){
                    writer.print("---Profile" + i +"\n");
                    writer.print("--ID:" + profileArray[i].getID() + ",Name:" + profileArray[i].getName() +",Last:" + 
                        profileArray[i].getLast() + ",Age:" + profileArray[i].getAge() + ",Status:" + profileArray[i].getStatus() + "\n-Friends:");
                    for(int j = 0; j < profileArray[i].getFriends().length; j++){
                        if(profileArray[i].getFriends()[j] != null){
                            if(j < 4){
                                writer.print(profileArray[i].getFriends()[j] + ",");
                            }
                            else{
                                writer.print(profileArray[i].getFriends()[j]);
                            }
                        }
                        
                    }
                    writer.print("\n-Posts:");
                    for(int j = 0; j < profileArray[i].getPosts().length; j++){
                        if(profileArray[i].getPosts()[j] != null){
                            if(j < 4){
                                writer.print(profileArray[i].getPosts()[j] + ",");
                            }
                            else{
                                writer.print(profileArray[i].getPosts()[j]);
                                
                            }
                        }    
                    }
                    writer.println();
                }
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    /* Actions for FacebookLite */
    public void createProfile(){
        if(!isMaxProfile()){
            String name = validateStrInput("\nINPUT FIRST NAME: ");
            String last = validateStrInput("INPUT LAST NAME: ");
            boolean existanceFlag = profileAlreadyExsists(name, last);
            if(existanceFlag){
                int age = validateIntInput("ENTER AGE: ");
                Profile profile = new Profile(name,last,age);
                while(true){
                    profileIndex++;
                    if(profileArray[profileIndex]== null){
                        profileArray[profileIndex] = profile;
                        Util.print("PROFILE CREATED : " + profileArray[profileIndex].getName() + " (" + profileArray[profileIndex].getID() + ")");
                        break;
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
    
    public void switchProfile(){
        if(!atLeastOneProfile()){
            if(moreThanOneProfile()){
                printProfilesNames();
                Util.printWL("\nINPUT PROFILE ID : ");
                String identifier = scanInput.nextLine();
                int index = -1;
                for(int i = 0; i < profileArray.length; i++){
                    if(profileArray[i] != null){
                        if(identifier.equalsIgnoreCase(profileArray[i].getID())){
                            index = i;
                            Util.print("SWITCHED TO --- PROFILE: " + profileArray[index].getName() + " (" + profileArray[index].getID() + ")");
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
    
    public void printAllProfiles(){
        for(int i = 0; i < profileArray.length; i++){
            if(profileArray[i] != null){
                profileArray[i].display();
            }       
        }
    }/* End of Actions */

    public void printProfilesNames(){
        int x = 0;
        Util.print("\nCURRENT PROFILE: " + profileArray[profileIndex].getName() + " (" + profileArray[profileIndex].getID() + ")\n");
        Util.print("------ AVAILABLE PROFILES ------");
        for(int i = 0; i < profileArray.length; i++){
            if(x == 2){
                Util.print("");
                x = 0;
            }
            if(profileArray[i] != null){
                Util.printWL("Profile: " + profileArray[i].getName() + " (" + profileArray[i].getID() + ")" + "\t");
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
}