package com.excref.kotblog.blog.service.post.domain

import com.excref.kotblog.blog.service.blog.domain.Blog
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.common.UuidAwareDomain
import com.excref.kotblog.blog.service.tag.domain.Tag
import javax.persistence.*

/**
 * @author Rafayel Mirzoyan
 * @since 6/10/17 6:59 PM
 */
@Entity
class Post(
        @Column(name = "title", nullable = false)
        val title: String,

        @Column(name = "content", nullable = false)
        val content: String,

        @OneToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "blog_id", nullable = false)
        val blog: Blog,

        @OneToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "tag_id")
        val tags: List<Tag> = listOf(),

        @OneToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinColumn(name = "category_id")
        var categories: List<Category> = listOf()
) : UuidAwareDomain()