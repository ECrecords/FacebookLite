public class Util{
    /* Print Methods */
    public static void printInputPrompt(){
        System.out.print("\nENTER CHOICE: ");
    }
    public static void printWL(String s){
        System.out.print(s);
    }
    public static void print(String[] arr){
        for(int i =0; i < arr.length; i++){
            System.out.print(arr[i] + " | ");
        }
    }
    public static void printOptionArray(){
        String[] optionArray = getOptionArray();
        int x = 0;
        Util.print("------ ENTER ONE OF THE FOLLOWING CHOICES ------");
        for(int i = 0; i<(optionArray.length-1); i++){
            if(x == 4){
                Util.print("");
                x = 0;
            }
            System.out.print(" " + optionArray[i] + " ");
            x++;
        }
        Util.print("\n\nTo Display Profile Enter - " + optionArray[16]);
    }

    /* Overloaded Print Methods */
    public static void print(String s){
        System.out.println(s);
    }
    public static void print(int s){
        System.out.println(s);
    }

    /* Array Initializer Methods */
    public static void init(String[] arr){
        for(int i = 0; i < arr.length; i++){
            arr[i] = "";
        }
    }
    public static void init(Profile[] arr){
        for(int i = 0; i < arr.length; i++){
            arr[i] = null;
        }
    }
    
    /* Options Array */
    public static String[] getOptionArray(){
    String[] optionArray = new String[]{"Exit","CreateProfile",
        "RemoveLastProfile","DeleteAllProfiles","SwitchProfile","AddFriend",
        "RemoveLastFriend","RemoveAllFriends","AddPost","RemoveLastPost","RemoveAllPosts",
        "ToggleAge","ToggleFriend","TogglePost","ToggleProfile","SetStatus", "DisplayProfile"};
        return optionArray;
    }
    
    /* Boolean Methods */
    public static boolean isArrayInit(Profile[] arr){
        for(int i = 0; i < arr.length; i++){
            if (arr[i] == null){
                return true;
            }
        }
        return false;
    }
}