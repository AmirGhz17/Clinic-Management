package com.example;

import javafx.stage.Stage;

public interface GlobalFunctions {
    public void randomGenerator(Hospital hospital);
    public void menu(Hospital hospital,Stage window);
    default boolean checker(String testi , int maxSize, int minSize, Boolean notNumChecker ){
        if(testi.length()<minSize || testi.length()>maxSize){
            return false;
        }
        if(notNumChecker){
            for(int i=0;i<testi.length();i++){
                if(!Character.isLetter(testi.charAt(i)) && testi.charAt(i) != ' '){
                    return false;
                }
            }
        }
        return true;
    }
    static boolean checker2(String testi , int maxSize, int minSize, Boolean notNumChecker ){
        if(testi.length()<minSize || testi.length()>maxSize){
            return false;
        }
        if(notNumChecker){
            for(int i=0;i<testi.length();i++){
                if(!Character.isLetter(testi.charAt(i)) && testi.charAt(i) != ' '){
                    return false;
                }
            }
        }
        return true;
    }
 
    //public void signUp(Hospital hospital);
    //public void signIn();
}
