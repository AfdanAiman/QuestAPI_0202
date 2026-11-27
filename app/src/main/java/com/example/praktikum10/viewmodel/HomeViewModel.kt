package com.example.praktikum10.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikum10.modeldata.DataSiswa
import com.example.praktikum10.repositori.RepositoryDataSiswa
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface StatusUiSiswa {
    data class Success(
        val siswa: List<DataSiswa> = listOf()
    ) : StatusUiSiswa

    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}

class HomeViewModel(
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var listSiswa: StatusUiSiswa by mutableStateOf(
        StatusUiSiswa.Loading
    )
        private set

    init {
        loadSiswa()
    }

    fun loadSiswa() {
        viewModelScope.launch {
            listSiswa = StatusUiSiswa.Loading
            listSiswa = try {
                StatusUiSiswa.Success(
                    repositoryDataSiswa.getDataSiswa()
                )
            } catch (e: IOException) {
                Log.e("CEK_API", "IOException: ${e.message}", e)
                StatusUiSiswa.Error
            } catch (e: HttpException) {
                Log.e("CEK_API", "HttpException ${e.code()}: ${e.message()}", e)
                StatusUiSiswa.Error
            } catch (e: Exception) {
                Log.e("CEK_API", "Exception: ${e.message}", e)
                StatusUiSiswa.Error
            }
        }
    }
}