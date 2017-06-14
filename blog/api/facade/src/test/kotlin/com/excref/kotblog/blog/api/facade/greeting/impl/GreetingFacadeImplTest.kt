package com.excref.kotblog.blog.api.facade.greeting.impl

import com.excref.kotblog.blog.api.facade.greeting.GreetingFacade
import com.excref.kotblog.blog.api.facade.test.AbstractFacadeImplTest
import com.excref.kotblog.blog.api.model.greeting.request.GreetingRequest
import org.assertj.core.api.Assertions.assertThat
import org.easymock.TestSubject
import org.junit.Test

/**
 * @author Arthur Asatryan
 * @since 6/15/17 12:51 AM
 */
class GreetingFacadeImplTest : AbstractFacadeImplTest() {

    //region Test subject and mocks
    @TestSubject
    private val greetingFacade: GreetingFacade = GreetingFacadeImpl()
    //endregion

    //region Test methods

    //region Initial
    @Test
    fun testGreetingFacade() {
        resetAll()
        // test data
        // expectations
        replayAll()
        // test scenario
        assertThat(greetingFacade).isNotNull()
        verifyAll()
    }
    //endregion

    //region greet
    @Test
    fun testGreet() {
        resetAll()
        // test data
        val name = "Arthur"
        val request = GreetingRequest(name)
        // expectations
        replayAll()
        // test scenario
        val result = greetingFacade.greet(request)
        helper.assertNoErrors(result)
//        helper.assertHasErrors(result, ErrorResponseModel("1"), ErrorResponseModel("2"))
        assertThat(result).isNotNull().extracting("response").extracting("greetings").containsOnly("Hello, $name!")
        verifyAll()
    }
    //endregion

    //endregion

}