package com.excref.kotblog.blog.service.tag

import com.excref.kotblog.blog.service.test.AbstractServiceIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author Arthur Asatryan
 * @since 6/4/17 4:12 PM
 */
class TagServiceIntegrationTest : AbstractServiceIntegrationTest() {

    //region Dependencies
    @Autowired
    private lateinit var tagService: TagService
    //endregion

    //region Test methods
    @Test
    fun testCreate() {
        // given
        val name = "C++"
        // when
        val result = tagService.create(name)
        // then
        assertThat(result).isNotNull().extracting("name").containsOnly(name)
    }

    @Test
    fun testGetByUuid() {
        // given
        val tag = helper.persistTag()
        // when
        val result = tagService.getByUuid(tag.uuid)
        // then
        assertThat(result).isNotNull().isEqualTo(tag)
    }

    @Test
    fun testGetByUuids() {
        // given
        val tag = helper.persistTag()
        val tag2 = helper.persistTag()
        val tags = listOf(tag, tag2)
        val tagsUuids = tags.map { it-> it.uuid }.toList()
        // when
        val result = tagService.getByUuids(tagsUuids)
        // then
        assertThat(result).isNotNull().containsAll(tags)
    }

    @Test
    fun testExistsForName() {
        // given
        val name = "C++"
        helper.persistTag(name)
        // when
        val existsForName = tagService.existsForName(name)
        // then
        assertThat(existsForName).isTrue()
    }
    //endregion

}