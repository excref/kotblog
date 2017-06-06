package com.excref.kotblog.blog.service.user.impl

import com.excref.kotblog.blog.service.common.UuidAwareDomain
import com.excref.kotblog.blog.service.test.AbstractServiceImplTest
import com.excref.kotblog.blog.service.test.AbstractServiceIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock.expect
import org.easymock.Mock
import org.easymock.TestSubject
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

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

    //region existsForEmail
    /**
     * When exists
     */
    @Test
    fun testExistsForEmail1() {
        resetAll()
        // test data
        val email = "biacoder@gmail.com"
        val user = buildUser(email = email)
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

@EntityScan(basePackages = arrayOf("com.excref.kotblog.blog.service.*"))
@EnableJpaRepositories(basePackageClasses = arrayOf(UserRepository::class))
@Component
class UserServiceIntegrationTest : AbstractServiceIntegrationTest() {

    //region Dependencies
    @Autowired
    private lateinit var userService: UserService
    //endregion

    //region Test methods
    @Test
    fun testExistsForEmail() {
        // given
        val email = "biacoder@gmail.com"
        persistUser(email = email)
        // when
        val existsForEmail = userService.existsForEmail(email)
        assertThat(existsForEmail).isTrue()
    }
    //endregion

    // todo: move the code below to the integration tests helper class
    @Autowired
    private lateinit var userRepository: UserRepository

    // todo: replace userRepository.save to the userService.create
    fun persistUser(
            email: String = "biacoder@gmail.com",
            password: String = "you can't even guess me! :P",
            role: UserRole = UserRole.GUEST
    ): User = userRepository.save(User(email, password, role))
}

// todo: move this method to the helper class
fun buildUser(
        email: String = "biacoder@gmail.com",
        password: String = "you can't even guess me! :P",
        role: UserRole = UserRole.GUEST
): User = User(email, password, role)

// todo: move to the domain
@Entity
data class User(
        val email: String,
        val password: String,
        @Enumerated(EnumType.STRING)
        val role: UserRole = UserRole.GUEST
) : UuidAwareDomain()

// todo: move to the appropriate domain
enum class UserRole {
    GUEST, BLOGGER, SYS_ADMIN
}

// todo: move to the repository
@Repository
interface UserRepository : CrudRepository<User, Long> {
    /**
     * Finds user with the given email
     *
     * @param   email The user email
     * @return  User if found, null otherwise
     */
    fun findByEmail(email: String): User?
}

// todo: move to the service core
interface UserService {
    /**
     * Checks if there is user with the given email
     *
     * @param   email The user email
     * @return  true if found, false otherwise
     */
    fun existsForEmail(email: String): Boolean
}

// todo: move to the service impl
@Service
class UserServiceImpl : UserService {

    //region Dependencies
    @Autowired
    private lateinit var userRepository: UserRepository
    //endregion

    //region Public methods
    override fun existsForEmail(email: String): Boolean {
        logger.debug("Getting user for email - $email")
        return userRepository.findByEmail(email) != null
    }
    //endregion

    //region Companion
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }
    //endregion

}