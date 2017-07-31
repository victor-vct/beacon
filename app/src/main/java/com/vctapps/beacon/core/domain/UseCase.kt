package com.vctapps.beacon.core.domain

interface UseCase<T>{

    fun run(): T

}