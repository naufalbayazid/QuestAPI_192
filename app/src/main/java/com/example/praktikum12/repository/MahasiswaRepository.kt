package com.example.praktikum12.repository


import com.example.praktikum12.model.Mahasiswa
import com.example.praktikum12.service_api.MahasiswaService
import java.io.IOException

interface MahasiswaRepository{
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)
    suspend fun getMahasiswa(): List<Mahasiswa>
    suspend fun getMahasiswaByNim(nim: String): List<Mahasiswa>
    suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa)
    suspend fun deleteMahasiswa(nim: String)
}

class NetworkKontakRepository(
    private val kontakApiService: MahasiswaService
): MahasiswaRepository{
    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) {
        kontakApiService.insertMahasiswa(mahasiswa)
    }

    override suspend fun getMahasiswa(): List<Mahasiswa> {
        return kontakApiService.getMahasiswa()
    }

    override suspend fun getMahasiswaByNim(nim: String):List<Mahasiswa> {
        return kontakApiService.getMahasiswaByNim(nim)
    }

    override suspend fun updateMahasiswa(nim: String, mahasiswa: Mahasiswa) {
        kontakApiService.updateMahasiswa(nim, mahasiswa)
    }

    override suspend fun deleteMahasiswa(nim: String) {
        try {
            val response = kontakApiService.deleteMahasiswa(nim)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete kontak. HTTP Status code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

}