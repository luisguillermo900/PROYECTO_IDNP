package com.example.proyecto_idnp.Modelos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CanvasViewModel extends ViewModel {
    private final MutableLiveData<Integer> selectRoom = new MutableLiveData<>();

    public void setSelectRoom(Integer id){
        selectRoom.setValue(id);
    }

    public LiveData<Integer> getSelectRoom(){
        return selectRoom;
    }
}
