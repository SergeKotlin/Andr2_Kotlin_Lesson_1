package com.example.andr2_kot_lesson_1

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField

// Прелести Parcelable в Kotlin (вычещен весь бойлерплейт-код)
// класс нужно создать с ключевым словом data. Также вместе с тем
// подгружаются все возможные доступные расширения (методы) по умолчанию

//@ParcelField
data class Note(val title:String, val note:String, val color:Int)//:Parcelable