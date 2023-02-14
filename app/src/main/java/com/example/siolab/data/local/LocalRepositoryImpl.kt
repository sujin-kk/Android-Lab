package com.example.siolab.data.local

import com.example.siolab.domain.local.repository.LocalRepository
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(): LocalRepository {
    override fun getLocalData(): String {
        return "Hello~"
    }

}