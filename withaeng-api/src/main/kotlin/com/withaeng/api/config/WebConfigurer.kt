package com.withaeng.api.config

import com.withaeng.api.security.resolver.UserInfoArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.ByteArrayHttpMessageConverter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebMvc
@Configuration
class WebConfigurer(
    private val jackson2ObjectMapperBuilder: Jackson2ObjectMapperBuilder,
) : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(UserInfoArgumentResolver())
    }

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.addAll(
            listOf(
                ByteArrayHttpMessageConverter(),
                MappingJackson2HttpMessageConverter(jackson2ObjectMapperBuilder.build()),
            )
        )
    }

}