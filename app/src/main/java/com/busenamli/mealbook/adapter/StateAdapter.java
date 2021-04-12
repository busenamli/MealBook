package com.busenamli.mealbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.busenamli.mealbook.R;
import com.busenamli.mealbook.model.StrAreaModel;
import com.busenamli.mealbook.view.HomeFragmentDirections;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateHolder>{

    List<StrAreaModel> strAreaModelList;
    Context context;
    final int key = 0;

    public StateAdapter(List<StrAreaModel> strAreaModelList, Context context) {
        this.strAreaModelList = strAreaModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public StateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.state_item,parent,false);
        return new StateHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateHolder holder, int position) {

        //final NavController navController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
        String strArea = strAreaModelList.get(position).strArea;
        holder.stateName.setText(strArea);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentDirections.ActionHomeFragmentToMealListFragment action = HomeFragmentDirections.actionHomeFragmentToMealListFragment(strArea, key);
                action.setKey(key);
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

    @Override
    public int getItemCount() {
        return strAreaModelList.size();
    }

    public class StateHolder extends RecyclerView.ViewHolder{

        TextView stateName;

        public StateHolder(@NonNull View itemView) {
            super(itemView);

            stateName = itemView.findViewById(R.id.stateName);
        }
    }
}
