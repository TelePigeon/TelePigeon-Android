package com.dongguk.telepigeon.domain.repository

interface CommonRepository {
    suspend fun getKeywords(): Result<List<String>>
    suspend fun getGenders(): Result<List<String>>
    suspend fun getAgeRanges(): Result<List<String>>
    suspend fun getRelations(): Result<List<String>>
}