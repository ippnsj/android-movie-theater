package woowacourse.movie

import android.app.Application
import woowacourse.movie.ui.storage.SettingsStorage

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SettingsStorage.init(this)
    }
}