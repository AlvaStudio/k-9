package com.fsck.k9.ui.settings

import com.fsck.k9.helper.FileBrowserHelper
import com.fsck.k9.helper.NamedThreadFactory
import com.fsck.k9.ui.account.AccountsLiveData
import com.fsck.k9.ui.settings.general.GeneralSettingsDataStore
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import java.util.concurrent.Executors

val settingsUiModule = applicationContext {
    bean { AccountsLiveData(get()) }
    viewModel { SettingsViewModel(get()) }
    bean { FileBrowserHelper.getInstance() }
    bean { GeneralSettingsDataStore(get(), get(), get("GeneralSettingsExecutor")) }
    bean("GeneralSettingsExecutor") {
        Executors.newSingleThreadExecutor(NamedThreadFactory("GeneralSettingsDataStore"))
    }
}
