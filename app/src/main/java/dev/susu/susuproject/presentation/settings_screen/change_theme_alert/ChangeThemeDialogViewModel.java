package dev.susu.susuproject.presentation.settings_screen.change_theme_alert;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.Lazy;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dev.susu.susuproject.app.theme_app.ThemeAppProvider;
import dev.susu.susuproject.app.theme_app.ThemeEnum;
import io.reactivex.rxjava3.core.Flowable;

@HiltViewModel
public class ChangeThemeDialogViewModel extends ViewModel {

    private ThemeAppProvider themeAppProvider;
    public ThemeEnum themeEnum;

    @Inject
    public ChangeThemeDialogViewModel(
            Lazy<ThemeAppProvider> themeAppProvider
    ) {
        this.themeAppProvider = themeAppProvider.get();
        this.themeEnum = this.themeAppProvider.getTheme();
    }

    public void changeTheme(ThemeEnum themeEnum) {
        themeAppProvider.setTheme(themeEnum);
    }

    public void setErrorThemeFlow() {
        Log.d("ROBBIK", "setErrorThemeFlow from changeThemeDialog");
        themeAppProvider.setTheme(ThemeEnum.SYSTEM);
    }
}
