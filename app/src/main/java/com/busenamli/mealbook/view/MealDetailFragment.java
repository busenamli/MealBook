package com.busenamli.mealbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.busenamli.mealbook.MyStorage;
import com.busenamli.mealbook.R;
import com.busenamli.mealbook.contract.ISelectedMealContract;
import com.busenamli.mealbook.model.SelectedMealModel;
import com.busenamli.mealbook.model.StrSelectedMealModel;
import com.busenamli.mealbook.presenter.SelectedMealPresenter;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.util.List;

public class MealDetailFragment extends Fragment implements ISelectedMealContract.View {

    ImageView mealImage;
    TextView mealName, mealCategory, mealArea, mealInstructions;
    ProgressBar progressBar;
    private SelectedMealPresenter selectedMealPresenter;
    private MyStorage myStorage;
    int favourite = 1;
    String mealId, nameMeal;

    public MealDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MealDetailFragment newInstance(String param1, String param2) {
        MealDetailFragment fragment = new MealDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null){
            mealId = MealDetailFragmentArgs.fromBundle(getArguments()).getİdMeal();
            nameMeal = MealDetailFragmentArgs.fromBundle(getArguments()).getNameMeal();
        }

        getActivity().setTitle(nameMeal);

        myStorage = new MyStorage(MealDetailFragment.this.getActivity());
        selectedMealPresenter = new SelectedMealPresenter(MealDetailFragment.this,myStorage);
        selectedMealPresenter.start();

        setHasOptionsMenu(true);

        mealImage = view.findViewById(R.id.meal_imageview);
        mealName = view.findViewById(R.id.meal_name_text);
        mealCategory = view.findViewById(R.id.meal_category_text);
        mealArea = view.findViewById(R.id.meal_area_text);
        mealInstructions = view.findViewById(R.id.meal_instructions_text);
        progressBar = view.findViewById(R.id.mealDetailFragment_progressBar);

        selectedMealPresenter.fetchStates(mealId);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.fav,menu);
        if (myStorage.isFav(mealId)){
            menu.findItem(R.id.fav_item).setIcon(R.drawable.ic_fav);
        }else{
            menu.findItem(R.id.fav_item).setIcon(R.drawable.ic_not_fav);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (myStorage.isFav(mealId)){
            menu.findItem(R.id.fav_item).setIcon(R.drawable.ic_fav);
        }else{
            menu.findItem(R.id.fav_item).setIcon(R.drawable.ic_not_fav);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fav_item:
                if (myStorage.isFav(mealId)){
                    item.setIcon(R.drawable.ic_not_fav);
                    myStorage.removeData(mealId);
                    System.out.println("favorilerden çıkarıldı");
                }else{
                    item.setIcon(R.drawable.ic_fav);
                    myStorage.saveData(mealId);
                    System.out.println("favorilere eklendi");
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if(progressBar!=null && progressBar.isShown()){
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(MealDetailFragment.this.getActivity(), ""+errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadList(SelectedMealModel selectedMealModel) {
        List<StrSelectedMealModel> strSelectedMealModel = selectedMealModel.getSelectedMeal();

        mealName.setText(strSelectedMealModel.get(0).strMeal);
        Picasso.get().load(strSelectedMealModel.get(0).strMealThumb).into(mealImage);
        mealArea.setText(strSelectedMealModel.get(0).strArea);
        mealCategory.setText(strSelectedMealModel.get(0).strCategory);
        mealInstructions.setText(strSelectedMealModel.get(0).strInstructions);

    }
}