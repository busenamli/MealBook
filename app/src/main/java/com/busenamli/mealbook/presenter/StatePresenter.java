package com.busenamli.mealbook.presenter;

import com.busenamli.mealbook.Constants;
import com.busenamli.mealbook.contract.IStateContract;
import com.busenamli.mealbook.model.StateModel;
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

public class StatePresenter implements IStateContract.Presenter{

    IStateContract.View mView;

    public StatePresenter(IStateContract.View view){
        this.mView = view;
    }
    @Override
    public void start() {
        //mView.init();
    }

    @Override
    public void fetchStates() {
        Gson gson = new GsonBuilder().setLenient().create();

        IStateService iStateService = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)) //JSON formatında geleceğini belirttik
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //RxJava kullandıysak
                .build().create(IStateService.class);

        iStateService.getStateMeals()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<StateModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showProgress();
                    }

                    @Override
                    public void onNext(@NonNull StateModel stateModel) {
                        mView.hideProgress();
                        mView.loadList(stateModel);
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
