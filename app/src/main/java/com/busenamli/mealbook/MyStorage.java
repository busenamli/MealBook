package com.busenamli.mealbook;

import android.content.Context;
import android.content.SharedPreferences;

public class MyStorage {

    SharedPreferences sharedPreferences;
    private Context mContext;

    public MyStorage(Context context) {
        this.mContext = context;
        sharedPreferences = mContext.getSharedPreferences("com.busenamli.mealbook", Context.MODE_PRIVATE);
    }

    public void saveData(String mealId) {
        sharedPreferences.edit().putString(mealId, mealId).apply();
    }

    public void removeData(String mealId) {
        String favMealId = sharedPreferences.getString(mealId, "");

        if (!favMealId.equals("")) {
            sharedPreferences.edit().remove(mealId).apply();
        }
    }

    public boolean isFav(String mealId) {
        String favMealId = sharedPreferences.getString(mealId, "");

        if (!favMealId.equals("")) {
            return true;
        }
        return false;
    }
}
