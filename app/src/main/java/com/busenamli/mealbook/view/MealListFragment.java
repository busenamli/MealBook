package com.busenamli.mealbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.busenamli.mealbook.MyStorage;
import com.busenamli.mealbook.R;
import com.busenamli.mealbook.adapter.CategoryAdapter;
import com.busenamli.mealbook.adapter.SelectedCategoryAdapter;
import com.busenamli.mealbook.contract.ISelectedCategoryContract;
import com.busenamli.mealbook.model.SelectedCategoryModel;
import com.busenamli.mealbook.model.StrCategoryModel;
import com.busenamli.mealbook.model.StrSelectedCategoryModel;
import com.busenamli.mealbook.presenter.SelectedCategoryPresenter;

import java.util.List;


public class MealListFragment extends Fragment implements ISelectedCategoryContract.View {

    private RecyclerView recyclerView;
    private SelectedCategoryAdapter selectedCategoryAdapter;
    private ProgressBar progressBar;
    private SelectedCategoryPresenter selectedCategoryPresenter;
    private MyStorage myStorage;
    String strName;
    int key;

    public MealListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MealListFragment newInstance(String param1, String param2) {
        MealListFragment fragment = new MealListFragment();
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
        return inflater.inflate(R.layout.fragment_meal_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null){
            strName = MealListFragmentArgs.fromBundle(getArguments()).getStrName();
            key = MealListFragmentArgs.fromBundle(getArguments()).getKey();
        }

        getActivity().setTitle(strName);

        myStorage = new MyStorage(MealListFragment.this.getActivity());
        selectedCategoryPresenter = new SelectedCategoryPresenter(MealListFragment.this,myStorage);
        selectedCategoryPresenter.start();

        recyclerView = view.findViewById(R.id.fragmentMealList_recyclerView);
        progressBar = view.findViewById(R.id.fragmentMealList_progressbar);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        selectedCategoryPresenter.fetchStates(strName,key);
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
        Toast.makeText(MealListFragment.this.getActivity(), ""+errorMsg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loadList(SelectedCategoryModel selectedCategoryModel) {
        List<StrSelectedCategoryModel> strSelectedCategoryModels = selectedCategoryModel.getSelectedCategoryMeals();

        selectedCategoryAdapter = new SelectedCategoryAdapter(strSelectedCategoryModels,getActivity());
        recyclerView.setAdapter(selectedCategoryAdapter);

    }
}