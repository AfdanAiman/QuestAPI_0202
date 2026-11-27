package com.example.praktikum10.repositori

import com.example.praktikum10.modeldata.DataSiswa
import com.example.praktikum10.apiservice.ServiceApiSiswa
import retrofit2.Response

interface RepositoryDataSiswa {
    suspend fun getDataSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void>
}
class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
) : RepositoryDataSiswa {
    override suspend fun getDataSiswa(): List<DataSiswa> =
        serviceApiSiswa.getSiswa()
    override suspend fun postDataSiswa(dataSiswa: DataSiswa): Response<Void> =
        serviceApiSiswa.postSiswa(dataSiswa)
}