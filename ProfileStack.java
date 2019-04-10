import java.util.Scanner;

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
            if(digitCount != 0 || userInput.length() < 1){
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
                        Util.print("PROFILE CREATED : " + name);
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
                                Util.print("SWITCHED TO --- PROFILE: " + profileArray[index].getName());
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
}