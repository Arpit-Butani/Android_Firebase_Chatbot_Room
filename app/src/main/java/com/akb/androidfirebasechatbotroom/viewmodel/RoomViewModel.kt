package com.akb.androidfirebasechatbotroom.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akb.androidfirebasechatbotroom.Injection
import com.akb.androidfirebasechatbotroom.data.Result.Success
import com.akb.androidfirebasechatbotroom.data.Result.Error
import com.akb.androidfirebasechatbotroom.data.Room
import com.akb.androidfirebasechatbotroom.data.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {

    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> get() = _rooms
    private val roomRepository: RoomRepository

    init {
        roomRepository = RoomRepository(Injection.instance())
        loadRoom()
    }

    fun createRoom(name:String){
        viewModelScope.launch {
            roomRepository.createRoom(name)
        }
    }

    fun loadRoom(){
        viewModelScope.launch {
            when(val result = roomRepository.getRooms()){
                is Success -> _rooms.value = result.data
                is Error -> {

                }
            }
        }
    }
}