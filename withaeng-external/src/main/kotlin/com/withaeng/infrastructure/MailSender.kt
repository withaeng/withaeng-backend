package com.withaeng.infrastructure

interface MailSender {
    fun send(redirectUrl: String, to: String, type: MailType)
}
