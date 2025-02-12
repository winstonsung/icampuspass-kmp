package app.icampuspass.viewmodels

import app.icampuspass.AppRepository
import app.icampuspass.Greeting
import com.rickclephas.kmp.observableviewmodel.ViewModel

class MainViewModel(
    private val appRepository: AppRepository
): ViewModel() {
    fun getGreeting(): Greeting {
        return appRepository.getGreeting()
    }
}
