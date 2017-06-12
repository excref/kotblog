package com.excref.kotblog.blog.service.post.domain

import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.common.UuidAwareDomain
import com.excref.kotblog.blog.service.tag.domain.Tag
import com.excref.kotblog.blog.service.user.domain.User
import javax.persistence.*


/**
 * Created by Android Studio.
 * User: Gurgen Arustamyan
 * Date: 6/8/17
 * Time: 3:11 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
data class Post(
        @Column(name = "title")
        val title: String,

        @Column(name = "content", nullable = false)
        val content: String,

        @ManyToMany()
        @JoinColumn(name = "category_id")
        val categories: List<Category>,

        @ManyToMany()
        @JoinColumn(name = "tag_id")
        val tags: List<Tag>,

        @ManyToOne()
        @JoinColumn(name = "user_id")
        val user: User
) : UuidAwareDomain()