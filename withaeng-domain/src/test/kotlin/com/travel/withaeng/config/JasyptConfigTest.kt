package com.travel.withaeng.config

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.test.context.junit.jupiter.SpringExtension

@Disabled
@SpringBootTest
@ExtendWith(SpringExtension::class)
class JasyptConfigTest {
    //@Value("\${jasypt.encryptor.password}")
    //private lateinit var jasyptEncryptorPassword: String

    @Autowired
    private lateinit var configurableEnvironment: ConfigurableEnvironment
    private lateinit var encryptor: DefaultLazyEncryptor

    /*@BeforeEach
    internal fun setUp() {
        check(jasyptEncryptorPassword.isNotBlank()) {
            "jasypt.encryptor.password must not be null, empty or blank. "
        }
        encryptor = DefaultLazyEncryptor(configurableEnvironment)
    }*/

    /*@Test
    fun testForEncryption() {
        val source = "test"
        println("source: $source")
        println("encrypted: ${encryptor.encrypt(source)}")
    }*/

    /*@Test
    fun testForDecryption() {
        val source = "test"
        println("source: $source")
        println("decrypted: ${encryptor.decrypt(source)}")
    }*/
}