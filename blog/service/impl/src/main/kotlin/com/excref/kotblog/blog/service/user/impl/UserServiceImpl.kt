package com.excref.kotblog.blog.service.user.impl

import com.excref.kotblog.blog.persistence.user.UserRepository
import com.excref.kotblog.blog.service.user.UserService
import com.excref.kotblog.blog.service.user.domain.User
import com.excref.kotblog.blog.service.user.domain.UserRole
import com.excref.kotblog.blog.service.user.exception.UserAlreadyExistsForEmailException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Arthur Asatryan
 * @since 6/8/17 12:44 AM
 */
@Service
internal class UserServiceImpl : UserService {

    //region Dependencies
    @Autowired
    private lateinit var userRepository: UserRepository
    //endregion

    //region Public methods
    override fun create(email: String, password: String, role: UserRole): User {
        logger.debug("Creating user for email - $email, password - $password and role - $role")
        assertUserNotExistsForEmail(email)
        return userRepository.save(User(email, password, role))
    }

    override fun existsForEmail(email: String): Boolean {
        logger.debug("Getting user for email - $email")
        return userRepository.findByEmail(email) != null
    }
    //endregion

    //region Utility methods
    private fun assertUserNotExistsForEmail(email: String) {
        if (existsForEmail(email)) {
            logger.error("The user with email - $email already exists")
            throw UserAlreadyExistsForEmailException(email, "The user with email - $email already exists")
        }
    }
    //endregion

    //region Companion
    companion object {
        private val logger: Logger = LoggerFactory.getLogger(UserServiceImpl::class.java)
    }
    //endregion

}