package com.excref.kotblog.blog.api.facade.greeting

import com.excref.kotblog.blog.api.model.common.ResultResponse
import com.excref.kotblog.blog.api.model.greeting.request.GreetingRequest
import com.excref.kotblog.blog.api.model.greeting.response.GreetingResponse

/**
 * @author Arthur Asatryan
 * @since 6/15/17 12:23 AM
 */
interface GreetingFacade {
    fun greet(request: GreetingRequest): ResultResponse<GreetingResponse>
}