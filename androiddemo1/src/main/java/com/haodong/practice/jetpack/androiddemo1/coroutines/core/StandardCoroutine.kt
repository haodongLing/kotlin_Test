package com.haodong.practice.jetpack.androiddemo1.coroutines.core

import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.coroutines.CoroutineContext

/**
 * created by linghaoDo on 2020/11/23
 * description:
 *
 * version:
 */
@RequiresApi(Build.VERSION_CODES.N)
class StandardCoroutine(override val context: CoroutineContext) : AbstractCoroutine<Unit>(context){


}