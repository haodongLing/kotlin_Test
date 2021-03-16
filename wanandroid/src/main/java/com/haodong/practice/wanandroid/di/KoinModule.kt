package com.haodong.practice.wanandroid.di


import com.haodong.practice.wanandroid.CoroutinesDispatcherProvider
import com.haodong.practice.wanandroid.model.api.WanRetrofitClient
import com.haodong.practice.wanandroid.model.api.WanService
import com.haodong.practice.wanandroid.model.repository.LoginRepository
import com.haodong.practice.wanandroid.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by luyao
 * on 2019/11/15 15:44
 */

val viewModelModule = module {
    viewModel { LoginViewModel(get(),get()) }
//    viewModel { ArticleViewModel(get(), get(), get(), get(), get()) }
//    viewModel { SystemViewModel(get(), get()) }
//    viewModel { NavigationViewModel(get()) }
//    viewModel { ProjectViewModel(get()) }
//    viewModel { SearchViewModel(get(), get()) }
//    viewModel { ShareViewModel(get()) }
}

val repositoryModule = module {
    single { WanRetrofitClient.getService(WanService::class.java, WanService.BASE_URL) }
    single { CoroutinesDispatcherProvider() }
    single { LoginRepository(get()) }
//    single { SquareRepository() }
//    single { HomeRepository() }
//    single { ProjectRepository() }
//    single { CollectRepository() }
//    single { SystemRepository() }
//    single { NavigationRepository() }
//    single { SearchRepository() }
//    single { ShareRepository() }
}

val appModule = listOf(viewModelModule, repositoryModule)