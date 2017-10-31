package com.example.root.demobind1;

/**
 * Created by root on 19/10/17.
 */

public class SceneTracker {
    private static int level=0;
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



}

