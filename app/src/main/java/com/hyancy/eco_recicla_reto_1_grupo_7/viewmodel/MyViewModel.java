package com.hyancy.eco_recicla_reto_1_grupo_7.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hyancy.eco_recicla_reto_1_grupo_7.domain.WasteUseCase;
import com.hyancy.eco_recicla_reto_1_grupo_7.data.models.WasteModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    private WasteUseCase wasteUseCase = new WasteUseCase();
    private final MutableLiveData<ArrayList<WasteModel>> dataList = new MutableLiveData<>();

    public MyViewModel(){
        getListProducts();
    }

    public void setListData(ArrayList<WasteModel> productsList){
        dataList.setValue(productsList);
    }

    public void getListProducts(){
        setListData(wasteUseCase.getListProducts());
    }

    public LiveData<ArrayList<WasteModel>> getListProductsLiveData(){
        return dataList;
    }
}
