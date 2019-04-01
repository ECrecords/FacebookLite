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
    //Options Array 
    public static void printOptionArray(){
        String[][] matrix = new String[][]{
            {"0 - Exit","\t\t 9 - Remove Last Post"},
            {"1 - Create Profile","\t10 - Reset Posts"},
            {"2 - Remove Last Profile","11 - Toggle Age"},
            {"3 - Reset Profiles","\t12 - Toggle Friend"},
            {"4 - Switch Profile","\t13 - Toggle Post"},
            {"5 - Add Friend","\t\t14 - Toggle Profile"},
            {"6 - Remove Last Friend","\t15 - Set Status" },
            {"7 - Reset Friends","\t16 - Display Profile"},
            {"8 - Add Post"},
        };
        Util.print("------ ENTER ONE OF THE FOLLOWING CHOICES ------");
        for(int row = 0; row < matrix.length; row++){
            for(int column = 0; column < matrix[row].length; column++){
                Util.printWL(matrix[row][column] + " ");
            }
            Util.print("");
        }
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
    
    public static int[] getIntOptionArray(){
        int[] arr = new int[17];
        for(int i = 0; i < arr.length; i++){
            arr[i] = i;
        }
        return arr;
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