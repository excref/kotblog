package com.excref.kotblog.blog.api.model.common

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Arthur Asatryan
 * @since 6/15/17 12:04 AM
 */
data class ResultResponse<out T : ResponseModel>(
        @JsonProperty("response")
        val response: T? = null,
        @JsonProperty("success")
        val success: Boolean = true,
        @JsonProperty("errors")
        val errors: Set<ErrorResponseModel> = setOf()
) {
    fun hasErrors(): Boolean = !errors.isEmpty()
}