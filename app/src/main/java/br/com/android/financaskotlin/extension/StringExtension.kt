package br.com.android.financaskotlin.extension

fun String.maxLength(length: Int): String{
    if (this.length > length){
        val firstCaracter = 0
        return "${this.substring(firstCaracter, length)}..."
    }
    return this
}