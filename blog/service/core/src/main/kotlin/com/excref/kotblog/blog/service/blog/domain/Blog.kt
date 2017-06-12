package com.excref.kotblog.blog.service.blog.domain

import com.excref.kotblog.blog.service.common.UuidAwareDomain
import com.excref.kotblog.blog.service.user.domain.User
import javax.persistence.*

/**
 * @author Arthur Asatryan
 * @since 6/10/17 7:17 PM
 */
@Entity
data class Blog constructor(
        @Column(name = "name", nullable = false, unique = true)
        val name: String,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User
) : UuidAwareDomain()