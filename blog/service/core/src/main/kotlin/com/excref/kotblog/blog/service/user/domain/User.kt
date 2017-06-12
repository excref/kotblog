package com.excref.kotblog.blog.service.user.domain

import com.excref.kotblog.blog.service.common.UuidAwareDomain
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

/**
 * @author Arthur Asatryan
 * @since 6/8/17 12:38 AM
 */
@Entity
data class User(
        @Column(name = "email", unique = true, nullable = false)
        val email: String,

        @Column(name = "password", nullable = false)
        val password: String,

        @Enumerated(EnumType.STRING)
        @Column(name = "role", nullable = false)
        val role: UserRole = UserRole.USER
) : UuidAwareDomain()