package com.busenamli.mealbook.presenter;

import com.busenamli.mealbook.Constants;
import com.busenamli.mealbook.contract.ICategoryContract;
import com.busenamli.mealbook.model.CategoryModel;
import com.busenamli.mealbook.model.StateModel;
import com.busenamli.mealbook.service.ICategoryService;
import com.busenamli.mealbook.service.IStateService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryPresenter implements ICategoryContract.Presenter {

    ICategoryContract.View mView;

    public CategoryPresenter(ICategoryContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {
        //mView.init();
    }

    @Override
    public void fetchStates() {

        Gson gson = new GsonBuilder().setLenient().create();

        ICategoryService iCategoryService = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)) //JSON formatında geleceğini belirttik
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava kullandıysak
                .build().create(ICategoryService.class);

        iCategoryService.getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CategoryModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showProgress();
                    }

                    @Override
                    public void onNext(@NonNull CategoryModel categoryModel) {
                        mView.hideProgress();
                        mView.loadList(categoryModel);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.showError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
