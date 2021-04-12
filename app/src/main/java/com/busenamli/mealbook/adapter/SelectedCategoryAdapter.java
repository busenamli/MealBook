package com.busenamli.mealbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.busenamli.mealbook.MyStorage;
import com.busenamli.mealbook.R;
import com.busenamli.mealbook.model.StrSelectedCategoryModel;
import com.busenamli.mealbook.view.MealListFragment;
import com.busenamli.mealbook.view.MealListFragmentArgs;
import com.busenamli.mealbook.view.MealListFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SelectedCategoryAdapter extends RecyclerView.Adapter<SelectedCategoryAdapter.SelectedCategoryHolder>{

    List<StrSelectedCategoryModel> strSelectedCategoryModelList;
    MyStorage myStorage;
    Context context;

    public SelectedCategoryAdapter(List<StrSelectedCategoryModel> strSelectedCategoryModelList, Context context) {
        this.strSelectedCategoryModelList = strSelectedCategoryModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public SelectedCategoryAdapter.SelectedCategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.selected_category_item,parent,false);
        return new SelectedCategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedCategoryAdapter.SelectedCategoryHolder holder, int position) {

        String idMeal = strSelectedCategoryModelList.get(position).idMeal;
        String nameMeal = strSelectedCategoryModelList.get(position).strMeal;
        holder.selectedCategoryText.setText(nameMeal);
        Picasso.get().load(strSelectedCategoryModelList.get(position).strMealThumb).into(holder.selectedCategoryImage);
        myStorage = new MyStorage(context);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealListFragmentDirections.ActionMealListFragmentToMealDetailFragment action = MealListFragmentDirections.actionMealListFragmentToMealDetailFragment(idMeal,nameMeal);
                action.setİdMeal(idMeal);
                action.setNameMeal(nameMeal);
                Navigation.findNavController(v).navigate(action);
            }
        });

        if (myStorage.isFav(idMeal)){
            holder.favImage.setImageResource(R.drawable.ic_fav);
        }else{
            holder.favImage.setImageResource(R.drawable.ic_not_fav);
        }

        holder.favImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myStorage.isFav(idMeal)){
                    holder.favImage.setImageResource(R.drawable.ic_not_fav);
                    myStorage.removeData(idMeal);
                }else{
                    holder.favImage.setImageResource(R.drawable.ic_fav);
                    myStorage.saveData(idMeal);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return strSelectedCategoryModelList.size();
    }

    public class SelectedCategoryHolder extends RecyclerView.ViewHolder {

        ImageView selectedCategoryImage;
        ImageView favImage;
        TextView selectedCategoryText;

        public SelectedCategoryHolder(@NonNull View itemView) {
            super(itemView);

            selectedCategoryImage = itemView.findViewById(R.id.category_item_image);
            favImage = itemView.findViewById(R.id.category_item_fav_image);
            selectedCategoryText = itemView.findViewById(R.id.category_item_text);
        }
    }
}
