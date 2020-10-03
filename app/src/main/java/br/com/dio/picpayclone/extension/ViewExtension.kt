package br.com.dio.picpayclone.extension

import android.view.View
import com.google.android.material.textfield.TextInputLayout

fun View.mostrar() {
    this.visibility = View.VISIBLE
}

fun View.esconder() {
    this.visibility = View.INVISIBLE
}

fun View.desaparecer() {
    this.visibility = View.INVISIBLE
}

fun TextInputLayout.getString() = this.editText?.text.toString()