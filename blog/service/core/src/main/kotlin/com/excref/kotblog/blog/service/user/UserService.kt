package com.excref.kotblog.blog.service.user

import com.excref.kotblog.blog.service.user.domain.User
import com.excref.kotblog.blog.service.user.domain.UserRole

/**
 * @author Arthur Asatryan
 * @since 6/8/17 12:40 AM
 */
interface UserService {
    /**
     * Creates a new user
     *
     * @param   email The user email
     * @param   password The user password
     * @param   role The user role
     * @return  Persistent user
     * @throws  com.excref.kotblog.blog.service.user.exception.UserAlreadyExistsForEmailException If the user already exists for the given email
     */
    fun create(email: String, password: String, role: UserRole): User

    /**
     * Checks if there is user with the given email
     *
     * @param   email The user email
     * @return  true if found, false otherwise
     */
    fun existsForEmail(email: String): Boolean

    /**
     * Gets user with the given uuid
     *
     * @param   uuid The user uuid
     * @throws  com.excref.kotblog.blog.service.user.exception.UserNotFoundForUuidException If user does not exists
     */
    fun getByUuid(uuid: String): User
}