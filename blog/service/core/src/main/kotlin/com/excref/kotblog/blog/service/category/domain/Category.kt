package com.excref.kotblog.blog.service.category.domain

import com.excref.kotblog.blog.service.common.UuidAwareDomain
import com.excref.kotblog.blog.service.user.domain.User
import javax.persistence.*

/**
 * @author Ruben Vardanyan
 * @since 06/07/2017 12:27
 */
@Entity
data class Category(
        @Column(name = "name", nullable = false)
        val name: String,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User
) : UuidAwareDomain()