package com.busenamli.mealbook.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.busenamli.mealbook.adapter.CategoryAdapter;
import com.busenamli.mealbook.contract.ICategoryContract;
import com.busenamli.mealbook.contract.IStateContract;
import com.busenamli.mealbook.R;
import com.busenamli.mealbook.adapter.StateAdapter;
import com.busenamli.mealbook.model.CategoryModel;
import com.busenamli.mealbook.model.StateModel;
import com.busenamli.mealbook.model.StrCategoryModel;
import com.busenamli.mealbook.presenter.CategoryPresenter;
import com.busenamli.mealbook.presenter.StatePresenter;
import com.busenamli.mealbook.model.StrAreaModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements IStateContract.View, ICategoryContract.View {

    private RecyclerView stateRecyclerView, categoryRecyclerView;
    private StateAdapter stateAdapter;
    private CategoryAdapter categoryAdapter;
    private ProgressBar stateProgressBar, categoryProgressBar;
    private StatePresenter statePresenter;
    private CategoryPresenter categoryPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        statePresenter = new StatePresenter(HomeFragment.this);
        statePresenter.start();

        categoryPresenter = new CategoryPresenter(HomeFragment.this);
        statePresenter.start();

        stateRecyclerView = view.findViewById(R.id.fragmentHome_stateRecyclerView);
        stateProgressBar = view.findViewById(R.id.fragmentHome_progressbar_state);

        categoryRecyclerView = view.findViewById(R.id.fragmentHome_categoryRecyclerView);
        categoryProgressBar = view.findViewById(R.id.fragmentHome_progressbar_category);

        LinearLayoutManager linearLayoutManagerState = new LinearLayoutManager(getActivity());
        linearLayoutManagerState.setOrientation(LinearLayoutManager.HORIZONTAL);
        stateRecyclerView.setLayoutManager(linearLayoutManagerState);
        statePresenter.fetchStates();

        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        categoryPresenter.fetchStates();
    }


    @Override
    public void showProgress() {
        categoryProgressBar.setVisibility(View.VISIBLE);
        stateProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if(stateProgressBar!=null && stateProgressBar.isShown()){
            stateProgressBar.setVisibility(View.GONE);
        }
        if(categoryProgressBar!=null && categoryProgressBar.isShown()){
            categoryProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String errorMsg) {
        Toast.makeText(HomeFragment.this.getActivity(), ""+errorMsg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loadList(CategoryModel categoryModel) {
        List<StrCategoryModel> strCategoryModels = categoryModel.getCategories();

        categoryAdapter = new CategoryAdapter(strCategoryModels,getActivity());
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void loadList(StateModel stateModel) {
        List<StrAreaModel> strAreaModels = stateModel.getMeals();

        stateAdapter = new StateAdapter(strAreaModels,getActivity());
        stateRecyclerView.setAdapter(stateAdapter);
    }
}

