package com.example.proyecto_idnp.Modelos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto_idnp.Entidades.ObraDeArte;
import com.example.proyecto_idnp.Entidades.RoomTestEntity;

import java.util.ArrayList;
import java.util.List;

public class CanvasViewModel extends ViewModel {
    private final MutableLiveData<Integer> selectRoom;
    public CanvasViewModel() {
        selectRoom = new MutableLiveData<>();
    }
    public void setSelectRoom(Integer id){
        selectRoom.setValue(id);
    }
    public LiveData<Integer> getSelectRoom(){
        return selectRoom;
    }
    public void setSelectRoomById(int id) {
        selectRoom.setValue(id);
    }
}
