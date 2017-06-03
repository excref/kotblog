package com.excref.kotblog

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotblogApplication

fun main(args: Array<String>) {
    SpringApplication.run(KotblogApplication::class.java, *args)
}
