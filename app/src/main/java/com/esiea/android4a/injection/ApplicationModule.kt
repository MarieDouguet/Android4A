package com.esiea.android4a.injection

import com.esiea.android4a.MainViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { MainViewModel() }


}