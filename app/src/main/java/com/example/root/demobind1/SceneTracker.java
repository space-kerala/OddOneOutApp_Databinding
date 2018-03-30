package com.example.root.demobind1;

/**
 * Created by root on 19/10/17.
 */

public class SceneTracker {
    private static int level=0,correctedItem=0,wrongItem=0;
    private static String flag;
    private static int totalLevel=0;
    public SceneTracker( ){

    }

  public static void setLevel( int value){
        level=value;

    }

   public static int getLevel()
   {
       return level;
   }


    public static void setTotalLevel(int value) {
        totalLevel = value;
    }

    public static int getTotalLevel(){
        return totalLevel;
    }

    public static void setflag(String value) {
        flag = value;
    }

    public static String getflag() {
        return flag;
    }



    public static void setCorrectedItem( int value){
        correctedItem=value;

    }

    public static int getCorrectedItem()
    {
        return correctedItem;
    }



    public static void setWrongItem( int value){
        wrongItem = value;

    }

    public static int getWrongItem()
    {
        return wrongItem;
    }


}

