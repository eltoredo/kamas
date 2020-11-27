package com.example.kotlinproject

interface IViewHolderAdapter<T> {
    fun LoadView(item: T)
}