package com.excref.kotblog.blog.api.model.greeting.response

import com.excref.kotblog.blog.api.model.common.ResponseModel

/**
 * @author Arthur Asatryan
 * @since 6/15/17 12:18 AM
 */
data class GreetingResponse(val greetings: String = "Hello, World!") : ResponseModel