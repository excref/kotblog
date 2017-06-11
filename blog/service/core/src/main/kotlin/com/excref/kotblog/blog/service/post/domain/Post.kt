package com.excref.kotblog.blog.service.post.domain

import com.excref.kotblog.blog.service.blog.domain.Blog
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.common.UuidAwareDomain
import com.excref.kotblog.blog.service.tag.domain.Tag
import javax.persistence.Column
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/**
 * @author Gugo dzya
 * @since 6/10/17 6:59 PM
 */
class Post(
        @Column(name = "name", nullable = false)
        val name: String,

        @Column(name = "title", nullable = false)
        val title: String,

        @Column(name = "content", nullable = false)
        val content: String,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "blog_id", nullable = false)
        val blog: Blog,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "tag_id", nullable = false)
        val tags: List<Tag>,

        @ManyToOne(optional = false, fetch = FetchType.LAZY)
        @JoinColumn(name = "tag_id", nullable = false)
        val categories: List<Category>
) : UuidAwareDomain()