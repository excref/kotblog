package com.excref.kotblog.blog.api.facade.greeting.impl

import com.excref.kotblog.blog.api.facade.greeting.GreetingFacade
import com.excref.kotblog.blog.api.model.common.ResultResponse
import com.excref.kotblog.blog.api.model.greeting.request.GreetingRequest
import com.excref.kotblog.blog.api.model.greeting.response.GreetingResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/**
 * @author Arthur Asatryan
 * @since 6/15/17 12:23 AM
 */
@Component
class GreetingFacadeImpl : GreetingFacade {

    //region Public methods
    override fun greet(request: GreetingRequest): ResultResponse<GreetingResponse> {
        logger.debug("Got greeting request - $request")
        val name = request.name
        logger.debug("Greeting to - $name")
        return ResultResponse(GreetingResponse("Hello, $name!"))
    }
    //endregion

    //region Companion object
    companion object {
        private val logger = LoggerFactory.getLogger(GreetingFacadeImpl::class.java)
    }
    //endregion
}