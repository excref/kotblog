package com.excref.kotblog.blog.service.category.domain

import com.excref.kotblog.blog.service.common.UuidAwareDomain
import javax.persistence.Entity

/**
 * @author Ruben Vardanyan
 * @since 06/07/2017 12:27
 */
@Entity
data class Category(val name: String) : UuidAwareDomain()