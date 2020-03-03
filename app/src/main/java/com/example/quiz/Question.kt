package com.example.quiz

data class Question(
    var questionText: String
) {
    companion object {
        val QUESTIONS = arrayOf(
            "- A \"val\" and \"var\" are the same",
            "- Mobile Application Development grants 12 ETCs",
            "- A Unit in Kotlin corresponds to a void in Java",
            "- In Kotlin \"when\" replaces the \"switch\" in Java"
        )}}