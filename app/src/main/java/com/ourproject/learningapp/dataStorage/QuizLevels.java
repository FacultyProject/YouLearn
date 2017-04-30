package com.ourproject.learningapp.dataStorage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Moetaz on 4/28/2017.
 */

public class QuizLevels {
    private Context context;
    private SharedPreferences sharedPreferences;

    public QuizLevels(Context context) {
        this.context = context;

        sharedPreferences = this.context.getSharedPreferences(
                "levels", Context.MODE_APPEND
        );
    }


    public void SaveItem(int Item){
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt(String.valueOf(Item),Item);
        editor.apply();
    }


    public int GetItem(String id){
        return this.sharedPreferences.getInt(id,Integer.parseInt(id));
    }
}

