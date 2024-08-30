package com.withaeng.infrastructure.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.TemplateEngine
import org.thymeleaf.spring6.SpringTemplateEngine
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.templatemode.TemplateMode

@Configuration
class ThymeleafConfig {

    @Bean
    fun htmlTemplateEngine(springResourceTemplateResolver: SpringResourceTemplateResolver): TemplateEngine {
        return SpringTemplateEngine().apply {
            addTemplateResolver(springResourceTemplateResolver)
        }
    }

    @Bean
    fun springResourceTemplateResolver(): SpringResourceTemplateResolver {
        return SpringResourceTemplateResolver().apply {
            prefix = TEMPLATE_PREFIX
            characterEncoding = TEMPLATE_CHARACTER_ENCODING
            suffix = TEMPLATE_SUFFIX
            templateMode = TemplateMode.HTML
            isCacheable = false
        }
    }

    companion object {
        private const val TEMPLATE_PREFIX = "classpath:templates/"
        private const val TEMPLATE_CHARACTER_ENCODING = "UTF-8"
        private const val TEMPLATE_SUFFIX = ".html"
    }
}