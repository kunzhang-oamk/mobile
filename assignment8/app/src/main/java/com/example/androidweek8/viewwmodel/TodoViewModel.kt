package com.example.androidweek8.viewwmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel:ViewModel() {
    var todos = mutableStateListOf<Todo>()
        private set
    init {
        getTodoList()
    }

    private fun getTodoList(){
        viewModelScope.launch {
            var todosApi:TodosApi? = null
            try{
                todosApi = TodosApi!!.getInstance()
                todos.clear()
                todos.addAll(todosApi.getTodos())
            } catch (e:Exception){
                Log.d("TODOVIEWMODEL",e.message.toString())
            }
        }
    }
}