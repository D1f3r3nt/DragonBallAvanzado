package com.keepcoding.dragonballavanzado.models.commons

sealed class State

class Idle: State()
class ErrorState(val msg: String): State()
class ResponseOK(): State()