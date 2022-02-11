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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.busenamli.mealbook.MyStorage;
import com.busenamli.mealbook.R;
import com.busenamli.mealbook.adapter.MealDetailAdapter;
import com.busenamli.mealbook.contract.ISelectedMealContract;
import com.busenamli.mealbook.model.IngredientsModel;
import com.busenamli.mealbook.model.SelectedMealModel;
import com.busenamli.mealbook.model.StrSelectedMealModel;
import com.busenamli.mealbook.presenter.SelectedMealPresenter;
import com.squareup.picasso.Picasso;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MealDetailFragment extends Fragment implements ISelectedMealContract.View {

    ImageView mealImage;
    TextView mealName, mealCategory, mealArea, mealInstructions;
    ListView ingredientList;
    ProgressBar progressBar;
    private SelectedMealPresenter selectedMealPresenter;
    private MyStorage myStorage;
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
            mealId = MealDetailFragmentArgs.fromBundle(getArguments()).getIdMeal();
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
        ingredientList = view.findViewById(R.id.ingredient_listview);
        ingredientList.setDivider(null);

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

        StrSelectedMealModel strModel = strSelectedMealModel.get(0);

        mealName.setText(strModel.strMeal);
        Picasso.get().load(strModel.strMealThumb).into(mealImage);
        mealArea.setText(strModel.strArea);
        mealCategory.setText(strModel.strCategory);
        mealInstructions.setText(strModel.strInstructions);

        ArrayList<IngredientsModel> ingredientsModels = new ArrayList<>();

        ingredientsModels.add(new IngredientsModel(strModel.strIngredient1, strModel.strMeasure1));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient2, strModel.strMeasure2));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient3, strModel.strMeasure3));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient4, strModel.strMeasure4));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient5, strModel.strMeasure5));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient6, strModel.strMeasure6));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient7, strModel.strMeasure7));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient8, strModel.strMeasure8));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient9, strModel.strMeasure9));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient10, strModel.strMeasure10));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient11, strModel.strMeasure11));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient12, strModel.strMeasure12));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient13, strModel.strMeasure13));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient14, strModel.strMeasure14));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient15, strModel.strMeasure15));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient16, strModel.strMeasure16));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient17, strModel.strMeasure17));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient18, strModel.strMeasure18));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient19, strModel.strMeasure19));
        ingredientsModels.add(new IngredientsModel(strModel.strIngredient20, strModel.strMeasure20));

        System.out.println(ingredientsModels.get(1).getIngredients());
        System.out.println(ingredientsModels.get(1).getMeasure());

        MealDetailAdapter mealDetailAdapter = new MealDetailAdapter(MealDetailFragment.this.getActivity(),ingredientsModels);
        ingredientList.setAdapter(mealDetailAdapter);

    }
}