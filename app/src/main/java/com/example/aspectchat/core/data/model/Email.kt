package com.example.aspectchat.core.data.model

@JvmInline
value class Email private constructor(val value: String) {
    companion object {
        private val EMAIL_REGEX = Regex(
            "[a-zA-Z0-9+._%\\-]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        fun create(email: String): Result<Email> {
            return if (isValid(email)) {
                Result.success(Email(email.lowercase()))
            } else {
                Result.failure(IllegalArgumentException("Invalid email format"))
            }
        }

        private fun isValid(email: String): Boolean {
            return email.matches(EMAIL_REGEX)
        }
    }

    override fun toString(): String = value
}