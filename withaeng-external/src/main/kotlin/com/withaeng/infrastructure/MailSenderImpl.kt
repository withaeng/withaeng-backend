package com.withaeng.infrastructure

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService
import com.amazonaws.services.simpleemail.model.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.nio.charset.StandardCharsets

@Service
class MailSenderImpl(
    private val emailService: AmazonSimpleEmailService,
    private val templateEngine: TemplateEngine
) : MailSender {

    @Value("\${cloud.aws.ses.from}")
    private lateinit var from: String

    override fun send(redirectUrl: String, to: String, type: MailType) {
        send(
            template = type.templateName,
            variables = mapOf(
                VARIABLE_THYMELEAF_REDIRECT_URL to redirectUrl,
                VARIABLE_THYMELEAF_EMAIL to to
            ),
            to = to
        )
    }

    private fun send(template: String, variables: Map<String, Any>, to: String) {
        val content = templateEngine.process(template, createContext(variables))
        val request = createSendEmailRequest(content, to)
        emailService.sendEmail(request)
    }

    private fun createContext(variables: Map<String, Any>): Context {
        return Context().apply { setVariables(variables) }
    }

    private fun createSendEmailRequest(content: String, to: String): SendEmailRequest {
        return SendEmailRequest()
            .withDestination(Destination().withToAddresses(to))
            .withSource(from)
            .withMessage(
                Message()
                    .withSubject(
                        Content().withCharset(StandardCharsets.UTF_8.name()).withData(VALIDATING_EMAIL_SUBJECT)
                    )
                    .withBody(Body().withHtml(Content().withCharset(StandardCharsets.UTF_8.name()).withData(content)))
            )
    }

    companion object {
        private const val VALIDATING_EMAIL_SUBJECT = "같이행 서비스 이메일 인증을 부탁드립니다."

        private const val VARIABLE_THYMELEAF_REDIRECT_URL = "redirectUrl"
        private const val VARIABLE_THYMELEAF_EMAIL = "email"
    }
}
