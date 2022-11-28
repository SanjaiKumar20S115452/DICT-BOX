package com.sanjai.dictbox.data.utils

sealed class UiEvent {
    data class ShowToast(val message: String,val actionLabel: String? = null): UiEvent()
}
