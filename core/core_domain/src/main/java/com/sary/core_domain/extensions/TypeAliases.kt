package com.sary.core_domain.extensions

typealias Action = () -> Unit
typealias Consumer<T> = (T) -> Unit
typealias Function<T, R> = (T) -> R
typealias Provider<T> = () -> T