package com.excref.kotblog

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
 * @author Arthur Asatryan
 * @since 5/4/17 1:17 PM
 */
@SpringBootApplication
class KotblogApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotblogApplication::class.java, *args)
}
