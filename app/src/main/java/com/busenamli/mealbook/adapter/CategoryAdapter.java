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

import com.busenamli.mealbook.R;
import com.busenamli.mealbook.model.StrAreaModel;
import com.busenamli.mealbook.model.StrCategoryModel;
import com.busenamli.mealbook.view.HomeFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{

    List<StrCategoryModel> strCategoryModelList;
    Context context;
    final int key = 1;

    public CategoryAdapter(List<StrCategoryModel> strCategoryModelList, Context context) {
        this.strCategoryModelList = strCategoryModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.category_item,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryHolder holder, int position) {

        String category = strCategoryModelList.get(position).strCategory;
        holder.categoryText.setText(category);
        Picasso.get().load(strCategoryModelList.get(position).strCategoryThumb).into(holder.categoryImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentDirections.ActionHomeFragmentToMealListFragment action = HomeFragmentDirections.actionHomeFragmentToMealListFragment(category,key);
                action.setKey(key);
                action.setStrName(category);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }

    @Override
    public int getItemCount() {
        return strCategoryModelList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView categoryText;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.category_item_image);
            categoryText = itemView.findViewById(R.id.category_item_text);
        }
    }
}
