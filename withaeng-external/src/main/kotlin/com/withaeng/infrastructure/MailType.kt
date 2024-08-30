package com.withaeng.infrastructure

enum class MailType(val templateName: String) {
    VALIDATE_EMAIL("validate-email-template"),
    CHANGE_PASSWORD("change-password-template")
}
