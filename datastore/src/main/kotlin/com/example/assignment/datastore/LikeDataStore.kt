package com.example.assignment.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LikeDataStore @Inject constructor(@ApplicationContext private val context: Context) {

    private val dataStore = context.dataStore

    fun observeLikedIds(): Flow<Set<Long>> =
        dataStore.data
            .map { prefs ->
                prefs[LIKED_IDS]
                    ?.mapNotNull { it.toLongOrNull() }
                    ?.toSet()
                    ?: emptySet()
            }

    suspend fun toggle(productId: Long): Result<Unit> = runCatching {
        dataStore.edit { prefs ->
            val current = prefs[LIKED_IDS] ?: emptySet()
            val key = productId.toString()
            prefs[LIKED_IDS] =
                if (key in current) current - key else current + key
        }
    }

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "like_preferences")
        private val LIKED_IDS = stringSetPreferencesKey("liked_ids")
    }
}