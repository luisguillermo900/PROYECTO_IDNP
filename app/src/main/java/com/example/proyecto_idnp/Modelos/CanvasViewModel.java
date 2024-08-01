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
    private final MutableLiveData<Integer> selectRoom = new MutableLiveData<>();
    /* Nuevos */
    private MutableLiveData<List<RoomTestEntity>> roomLiveData;
    private MutableLiveData<RoomTestEntity> roomSeleccionada;
    private List<RoomTestEntity> listRooms;

    public CanvasViewModel() {
        roomLiveData = new MutableLiveData<>();
        roomSeleccionada = new MutableLiveData<>();
        loadObras();
    }

    public void setSelectRoom(Integer id){
        selectRoom.setValue(id);
    }
    public LiveData<Integer> getSelectRoom(){
        return selectRoom;
    }

    /* Nuevos */
    public LiveData<List<RoomTestEntity>> getRoomLiveData() {
        return roomLiveData;
    }

    public LiveData<RoomTestEntity> getRoomSeleccionada() {
        return roomSeleccionada;
    }

    public void setRoomSeleccionada(RoomTestEntity room) {
        roomSeleccionada.setValue(room);
    }

    public void setSelectRoomById(int id) {
        //selectRoom.setValue(id);
        RoomTestEntity room = getObraPorId(id);
        roomSeleccionada.setValue(room);
    }
    private RoomTestEntity getObraPorId(int id) {
        if (listRooms != null) {
            for (RoomTestEntity room : listRooms) {
                if (room.getId() == id) {
                    return room;
                }
            }
        }
        return null;
    }

    private void loadObras() {
        listRooms = new ArrayList<>();
        listRooms.add(new RoomTestEntity(
                1,
                "Sala 1",
                "Exposicion 1",
                100,
                100,
                200,
                200
        ));
        listRooms.add(new RoomTestEntity(
                1,
                "Sala 2",
                "Exposicion 2",
                100,
                100,
                200,
                200
        ));
        listRooms.add(new RoomTestEntity(
                1,
                "Sala 3",
                "Exposicion 3",
                100,
                100,
                200,
                200
        ));
        listRooms.add(new RoomTestEntity(
                1,
                "Sala 4",
                "Exposicion 4",
                100,
                100,
                200,
                200
        ));
        listRooms.add(new RoomTestEntity(
                1,
                "Sala 5",
                "Exposicion 5",
                100,
                100,
                200,
                200
        ));
        listRooms.add(new RoomTestEntity(
                1,
                "Sala 6",
                "Exposicion 6",
                100,
                100,
                200,
                200
        ));
        listRooms.add(new RoomTestEntity(
                1,
                "Sala 7",
                "Exposicion 7",
                100,
                100,
                200,
                200
        ));

        Log.d("SharedViewModel", "Lista de cuadros inicializada con " + listRooms.size() + " elementos.");

        roomLiveData.setValue(listRooms);
    }
}
