package com.excref.kotblog.blog.service.test.helper

import com.excref.kotblog.blog.api.model.common.ErrorResponseModel
import com.excref.kotblog.blog.api.model.common.ResponseModel
import com.excref.kotblog.blog.api.model.common.ResultResponse
import org.assertj.core.api.Assertions.assertThat

/**
 * @author Arthur Asatryan
 * @since 6/15/17 12:46 AM
 */
class FacadeImplTestHelper {

    //region Public methods

    //region Common
    fun <T : ResponseModel> assertNoErrors(resultResponse: ResultResponse<T>) {
        assertThat(resultResponse.hasErrors()).isFalse()
        assertThat(resultResponse.errors).isEmpty()
    }

    fun <T : ResponseModel> assertHasErrors(resultResponse: ResultResponse<T>, vararg errors: ErrorResponseModel) {
        assertThat(resultResponse.errors).containsOnly(*errors)
    }
    //endregion

    //endregion

}