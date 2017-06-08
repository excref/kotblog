package com.excref.kotblog.blog.service.user.impl

import com.excref.kotblog.blog.persistence.user.UserRepository
import com.excref.kotblog.blog.service.test.AbstractServiceImplTest
import com.excref.kotblog.blog.service.user.UserService
import com.excref.kotblog.blog.service.user.domain.User
import com.excref.kotblog.blog.service.user.domain.UserRole
import com.excref.kotblog.blog.service.user.exception.UserAlreadyExistsForEmailException
import com.excref.kotblog.blog.service.user.exception.UserNotFoundForUuidException
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.*
import org.easymock.Mock
import org.easymock.TestSubject
import org.junit.Assert.fail
import org.junit.Test
import java.util.*

/**
 * @author Arthur Asatryan
 * @since 6/7/17 12:16 AM
 */
class UserServiceImplTest : AbstractServiceImplTest() {

    //region Test subject and mocks
    @TestSubject
    private val userService: UserService = UserServiceImpl()

    @Mock
    private lateinit var userRepository: UserRepository
    //endregion

    //region Test methods

    //region Initial
    @Test
    fun testUserService() {
        resetAll()
        // test data
        // expectations
        replayAll()
        // test scenario
        assertThat(userService).isNotNull()
        verifyAll()
    }

    @Test
    fun testUserRepository() {
        resetAll()
        // test data
        // expectations
        replayAll()
        // test scenario
        assertThat(userRepository).isNotNull()
        verifyAll()
    }
    //endregion

    //region create
    /**
     * When user already exists for email
     */
    @Test
    fun testCreate1() {
        resetAll()
        // test data
        val user = helper.buildUser()
        // expectations
        expect(userRepository.findByEmail(user.email)).andReturn(user)
        replayAll()
        // test scenario
        try {
            userService.create(user.email, UUID.randomUUID().toString(), UserRole.GUEST)
            fail()
        } catch(ex: UserAlreadyExistsForEmailException) {
            assertThat(ex).isNotNull().extracting("email").containsOnly(user.email)
        }
        verifyAll()
    }

    /**
     * When user does not exists
     */
    @Test
    fun testCreate2() {
        resetAll()
        // test data
        val email = "biacoder@gmail.com"
        val password = "you can't even guess me! :P"
        val role = UserRole.BLOGGER
        // expectations
        expect(userRepository.findByEmail(email)).andReturn(null)
        expect(userRepository.save(isA(User::class.java))).andAnswer({ getCurrentArguments()[0] as User })
        replayAll()
        // test scenario
        val result = userService.create(email, password, role)
        assertThat(result).isNotNull().extracting("email", "password", "role").containsOnly(email, password, role)
        verifyAll()
    }
    //endregion

    //region getByUuid
    /**
     * When user does not exists
     */
    @Test
    fun testGetByUuid1() {
        resetAll()
        // test data
        // expectations
        val uuid = UUID.randomUUID().toString()
        expect(userRepository.findByUuid(uuid)).andReturn(null)
        replayAll()
        // test scenario
        try {
            userService.getByUuid(uuid)
            fail()
        } catch(ex: UserNotFoundForUuidException) {
            assertThat(ex).isNotNull().extracting("uuid").containsOnly(uuid)
        }
        verifyAll()
    }

    /**
     * When user exists
     */
    @Test
    fun testGetByUuid2() {
        resetAll()
        // test data
        // expectations
        val user = helper.buildUser()
        val uuid = user.uuid
        expect(userRepository.findByUuid(uuid)).andReturn(user)
        replayAll()
        // test scenario
        val result = userService.getByUuid(uuid)
        assertThat(result).isNotNull().isEqualTo(user)
        verifyAll()
    }
    //endregion

    //region existsForEmail
    /**
     * When exists
     */
    @Test
    fun testExistsForEmail1() {
        resetAll()
        // test data
        val email = "biacoder@gmail.com"
        val user = helper.buildUser(email = email)
        // expectations
        expect(userRepository.findByEmail(email)).andReturn(user)
        replayAll()
        // test scenario
        assertThat(userService.existsForEmail(email)).isNotNull().isTrue()
        verifyAll()
    }

    /**
     * When does not exists
     */
    @Test
    fun testExistsForEmail2() {
        resetAll()
        // test data
        val email = "biacoder@gmail.com"
        // expectations
        expect(userRepository.findByEmail(email)).andReturn(null)
        replayAll()
        // test scenario
        assertThat(userService.existsForEmail(email)).isFalse()
        verifyAll()
    }
    //endregion

    //endregion

}