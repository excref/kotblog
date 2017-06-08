package com.excref.kotblog.blog.persistence.user

import com.excref.kotblog.blog.service.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Arthur Asatryan
 * @since 6/8/17 12:40 AM
 */
@Repository
interface UserRepository : CrudRepository<User, Long> {
    /**
     * Finds user with the given email
     *
     * @param   email The user email
     * @return  User if found, null otherwise
     */
    fun findByEmail(email: String): User?

    /**
     * Finds user with the given uuid
     *
     * @param   uuid The user uuid
     * @return  User if found, null otherwise
     */
    fun findByUuid(uuid: String): User?
}