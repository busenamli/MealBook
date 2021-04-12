package com.busenamli.mealbook.presenter;

import com.busenamli.mealbook.Constants;
import com.busenamli.mealbook.MyStorage;
import com.busenamli.mealbook.contract.ISelectedMealContract;
import com.busenamli.mealbook.model.SelectedMealModel;
import com.busenamli.mealbook.service.ISelectedMealService;
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

public class SelectedMealPresenter implements ISelectedMealContract.Presenter {

    ISelectedMealContract.View mView;
    MyStorage myStorage;

    public SelectedMealPresenter(ISelectedMealContract.View mView, MyStorage myStorage) {
        this.mView = mView;
        this.myStorage = myStorage;
    }

    @Override
    public void start() {
        //mView.init();
    }

    @Override
    public void fetchStates(String mealId) {
        Gson gson = new GsonBuilder().setLenient().create();

        ISelectedMealService iSelectedMealService = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)) //JSON formatında geleceğini belirttik
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava kullandıysak
                .build().create(ISelectedMealService.class);

        iSelectedMealService.getMeal(mealId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<SelectedMealModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showProgress();
                    }

                    @Override
                    public void onNext(@NonNull SelectedMealModel selectedMealModel) {
                        mView.hideProgress();
                        mView.loadList(selectedMealModel);
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
