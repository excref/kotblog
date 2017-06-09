package com.excref.kotblog.blog.service.category.impl

import com.excref.kotblog.blog.persistence.category.CategoryRepository
import com.excref.kotblog.blog.service.category.CategoryService
import com.excref.kotblog.blog.service.category.domain.Category
import com.excref.kotblog.blog.service.category.exception.CategoryAlreadyExistsForNameAndUserException
import com.excref.kotblog.blog.service.category.exception.CategoryNotExistsForUuidException
import com.excref.kotblog.blog.service.test.AbstractServiceImplTest
import com.excref.kotblog.blog.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.easymock.EasyMock
import org.easymock.EasyMock.expect
import org.easymock.EasyMock.isA
import org.easymock.Mock
import org.easymock.TestSubject
import org.junit.Test
import java.util.*

/**
 * @author Ruben Vardanyan
 * @since 06/07/2017 12:42
 */
class CategoryServiceImplTest : AbstractServiceImplTest() {

    //region Test subject and mocks
    @TestSubject
    private val categoryService: CategoryService = CategoryServiceImpl()

    @Mock
    private lateinit var categoryRepository: CategoryRepository

    @Mock
    private lateinit var userService: UserService
    //endregion

    //region initial
    @Test
    fun testCategoryService() {
        assertThat(categoryService).isNotNull()
    }

    @Test
    fun testCategoryRepository() {
        assertThat(categoryRepository).isNotNull()
    }

    @Test
    fun testUserService() {
        assertThat(userService).isNotNull()
    }
    //endregion

    //region create

    /**
     *  When category with name already exists for user
     */
    @Test
    fun create1() {
        resetAll()
        // test data
        val name = "Software development"
        val user = helper.buildUser()
        val category = helper.buildCategory(name, user)
        //expectations
        expect(categoryRepository.findByNameAndUserUuid(name, user.uuid)).andReturn(category)
        replayAll()
        // test scenario
        try {
            categoryService.create(name, user.uuid)
        } catch (ex: CategoryAlreadyExistsForNameAndUserException) {
            assertThat(ex).isNotNull().extracting("name").containsOnly(name)
        }
        verifyAll()
    }

    @Test
    fun create2() {
        resetAll()
        // test data
        val name = "Software development"
        val user = helper.buildUser()
        //expectations
        expect(categoryRepository.findByNameAndUserUuid(name, user.uuid)).andReturn(null)
        expect(userService.getByUuid(user.uuid)).andReturn(user)
        expect(categoryRepository.save(isA(Category::class.java))).andAnswer({ EasyMock.getCurrentArguments()[0] as Category? })
        replayAll()
        // test scenario
        val category = categoryService.create(name, user.uuid)
        assertThat(category).isNotNull().extracting("name").containsOnly(name)
        verifyAll()
    }
    //endregion

    //region getByUuid
    /**
     * When does not exists for uuid
     */
    @Test
    fun getByUuid1() {
        resetAll()
        // test data
        val uuid = UUID.randomUUID().toString()
        // expectations
        expect(categoryRepository.findByUuid(uuid)).andReturn(null)
        replayAll()
        // test scenario
        try {
            categoryService.getByUuid(uuid)
        } catch (ex: CategoryNotExistsForUuidException) {
            assertThat(ex).isNotNull().extracting("uuid").containsOnly(uuid)
        }
        verifyAll()
    }

    @Test
    fun getByUuid2() {
        resetAll()
        // test data
        val category = helper.buildCategory()
        val uuid = category.uuid
        // expectations
        expect(categoryRepository.findByUuid(uuid)).andReturn(category)
        replayAll()
        // test scenario
        val result = categoryService.getByUuid(uuid)
        assertThat(result).isNotNull().isEqualTo(category)
        verifyAll()
    }
    //endregion

    //region existsForName
    /**
     * When does not exist
     */
    @Test
    fun existsForNameAdnUser1() {
        resetAll()
        // test data
        val name = "Art"
        val user = helper.buildUser()
        // expectations
        expect(categoryRepository.findByNameAndUserUuid(name, user.uuid)).andReturn(null)
        replayAll()
        // test scenario
        assertThat(categoryService.existsForNameAndUser(name, user.uuid)).isFalse()
    }

    /**
     * When exists
     */
    @Test
    fun existsForName2() {
        resetAll()
        // test data
        val name = "Sports"
        val user = helper.buildUser()
        val category = helper.buildCategory(name, user)
        // expectations
        expect(categoryRepository.findByNameAndUserUuid(name, user.uuid)).andReturn(category)
        replayAll()
        // test scenario
        assertThat(categoryService.existsForNameAndUser(name, user.uuid)).isTrue()
    }
    //endregion

}