package com.busenamli.mealbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.busenamli.mealbook.R;
import com.busenamli.mealbook.model.IngredientsModel;

import java.util.ArrayList;

public class MealDetailAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<IngredientsModel> ingredientsList;
    private Context context;

    public MealDetailAdapter(Context context, ArrayList<IngredientsModel> ingredientsList) {
        this.ingredientsList = ingredientsList;
        this.context = context;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return ingredientsList.size();
    }

    @Override
    public Object getItem(int position) {
        return ingredientsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.listview_row,null);
        TextView ingredient = view.findViewById(R.id.ingredient_row);
        TextView measure = view.findViewById(R.id.measure_row);

        IngredientsModel model = ingredientsList.get(position);
        if (!model.getIngredients().equals(null) && !model.getIngredients().equals("")){
            ingredient.setText(model.getIngredients() + " :  ");
            measure.setText(model.getMeasure());
        }
        else{
            ingredient.setText(" - ");
        }
        return view;
    }
}
