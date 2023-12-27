// Author: Abdallah Mohamed (@AntiHexCode)

import java.util.ArrayList;


public class UndoRedo {

    
    private static ArrayList<String> undoStack = new ArrayList<>();
    private static ArrayList<String> redoStack = new ArrayList<>();
    private static String currentFileContent = null;
    private static boolean undoFlag = false;
    private static boolean redoFlag = false;  
    
    
    public static void setCurrentFileContent(String currentFileContent){
        
        if (undoFlag){
            redoStack.add(UndoRedo.currentFileContent);
            undoFlag = false;
        }
        else if (redoFlag){
            undoStack.add(UndoRedo.currentFileContent);
            redoFlag = false;
        }
        else{
            if (UndoRedo.currentFileContent == null)
                undoStack.add("");
            else
                undoStack.add(UndoRedo.currentFileContent);
        }
        
        UndoRedo.currentFileContent = currentFileContent;
        
    }
    
    
    public static boolean isUndoStackEmpty(){
        return undoStack.isEmpty();
    }
    public static boolean isRedoStackEmpty(){
        return redoStack.isEmpty();
    }
    
    
    public static String undo(){
        undoFlag = true;
        String undoneFileContent = undoStack.getLast();
        undoStack.removeLast();
        return undoneFileContent;
    }
    
    
    public static String redo(){
        redoFlag = true;
        String redoneFileContent = redoStack.getLast();
        redoStack.removeLast();
        return redoneFileContent;
    }
    
}
