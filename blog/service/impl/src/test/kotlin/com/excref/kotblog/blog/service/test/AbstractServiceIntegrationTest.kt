package com.excref.kotblog.blog.service.test

import com.excref.kotblog.blog.service.test.helper.ServiceIntegrationTestHelper
import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Arthur Asatryan
 * @since 6/4/17 4:09 PM
 */
// todo: see issue #20
@RunWith(SpringRunner::class)
@SpringBootTest
@EnableJpaRepositories(basePackages = arrayOf("com.excref.kotblog.blog.persistence.*"))
@EntityScan(basePackages = arrayOf("com.excref.kotblog.blog.service.*"))
@ContextConfiguration("classpath:applicationContext-service.xml")
@Ignore
abstract class AbstractServiceIntegrationTest : AbstractTransactionalJUnit4SpringContextTests() {

    //region Dependencies
    @Autowired
    protected lateinit var helper: ServiceIntegrationTestHelper
    //endregion
}