package dev.susu.susuproject.presentation;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dev.susu.susuproject.app.theme_app.ThemeAppProvider;
import dev.susu.susuproject.app.theme_app.ThemeEnum;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.flow.Flow;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private ThemeAppProvider themeAppProvider;

    public Flow<ThemeEnum> themeEnumFlowable;

    @Inject
    public MainViewModel(
            ThemeAppProvider themeAppProvider
    ) {
        this.themeAppProvider = themeAppProvider;
        this.themeEnumFlowable = this.themeAppProvider.getThemeFlow();
    }

    public void setErrorThemeFlow() {
        Log.d("ROBBIK", "setErrorThemeFlow from activity");
    }

}
