package dev.susu.susuproject.app.theme_app

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.susu.susuproject.presentation.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemeAppProvider @Inject constructor(
    @ApplicationContext context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "theme provider"
    )

    private val dataStore = context.dataStore

    private var currentTheme = MutableStateFlow(ThemeEnum.SYSTEM)

    companion object {
        private val themeModKey = stringPreferencesKey("THEME_KEY_MODE")
        private const val DARK_THEME = "darkTheme"
        private const val DAY_THEME = "dayTheme"
        private const val SYSTEM_THEME = "systemTheme"
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    fun setTheme(themeEnum: ThemeEnum) = coroutineScope.launch {
        dataStore.edit {
            it[themeModKey] = when (themeEnum) {
                ThemeEnum.DARK -> {
                    DARK_THEME
                }

                ThemeEnum.DAY -> {
                    DAY_THEME
                }

                ThemeEnum.SYSTEM -> {
                    SYSTEM_THEME
                }
            }
        }
    }

    fun getThemeFlow(): Flow<ThemeEnum> =
        dataStore.data
            .map { pref ->
                currentTheme.tryEmit(prefToTheme(pref))
                currentTheme.value
            }

    private fun prefToTheme(pref: Preferences) =
        when (pref[themeModKey]) {
            DARK_THEME -> {
                ThemeEnum.DARK
            }

            DAY_THEME -> {
                ThemeEnum.DAY
            }

            SYSTEM_THEME -> {
                ThemeEnum.SYSTEM
            }

            else -> {
                ThemeEnum.SYSTEM
            }
        }

    fun getTheme(): ThemeEnum = currentTheme.value
}

fun MainActivity.observeThemeAppProvide(flow: Flow<ThemeEnum>, block: (ThemeEnum) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.CREATED) {
            flow.collect {
                block(it)
            }
        }
    }
}