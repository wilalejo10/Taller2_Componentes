package com.dominoconquista.ui.model

object Avatars {
    val all = listOf("🦊", "🐼", "🐯", "🐸", "🐙", "🦄", "🐵", "🐧")

    fun emoji(index: Int): String = all[index.mod(all.size)]
}