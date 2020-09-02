package com.cloud.sleuthclient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SleuthClientApplication

fun main(args: Array<String>) {
	runApplication<SleuthClientApplication>(*args)
}
