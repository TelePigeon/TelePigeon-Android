package com.dongguk.telepigeon.data.local.datasourceimpl

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.dongguk.telepigeon.data.local.BuildConfig
import com.dongguk.telepigeon.data.local.datasource.TelePigeonLocalDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TelePigeonLocalDataSourceImpl @Inject constructor(
    @ApplicationContext context: Context
) : TelePigeonLocalDataSource{
    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val pref: SharedPreferences =
        if (BuildConfig.DEBUG) {
            context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        } else {
            EncryptedSharedPreferences.create(
                context,
                FILE_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }

    override var isLogin: Boolean
        get() = pref.getBoolean(AUTO_LOGIN, false)
        set(value) = pref.edit { putBoolean(AUTO_LOGIN, value) }

    override var accessToken: String
        get() = pref.getString(ACCESS_TOKEN, "") ?: ""
        set(value) = pref.edit { putString(ACCESS_TOKEN, value) }

    override var refreshToken: String
        get() = pref.getString(REFRESH_TOKEN, "") ?: ""
        set(value) = pref.edit { putString(REFRESH_TOKEN, value) }

    override var roomId: Int
        get() = pref.getInt(ROOM_ID, -1)
        set(value) = pref.edit { putInt(ROOM_ID, value) }

    override fun clear() {
        pref.edit {
            clear()
        }
    }

    companion object {
        const val FILE_NAME = "AuthSharedPreferences"
        const val AUTO_LOGIN = "IsLogin"
        const val ACCESS_TOKEN = "AccessToken"
        const val REFRESH_TOKEN = "RefreshToken"
        const val ROOM_ID = "RoomId"
    }
}