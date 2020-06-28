package com.xiaoma.hencoder.kotlin

interface EntityCallback<T> {
    fun onSuccess(entity: T)
    fun onFailure(message: String?)
}