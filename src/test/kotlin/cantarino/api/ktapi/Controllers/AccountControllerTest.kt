package cantarino.api.ktapi.Controllers

import cantarino.api.ktapi.Repositories.IAccountRepository
import cantarino.api.ktapi.domain.Account
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var repository: IAccountRepository

    @Test
    fun `Test find All`() {
        with(repository) { save(Account(name = "test" , document = "011T788" , phone = "61977844541")) }
        mockMvc.perform(MockMvcRequestBuilders.get("/account"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].id").isNumber)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].name").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].document").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].phone").isString)
            .andDo(MockMvcResultHandlers.print())

    }
    @Test
    fun `Test find byId`() {
        val accountSaved = with(repository) { save(Account(name = "test" , document = "011T788" , phone = "61977844541")) }

        mockMvc.perform(MockMvcRequestBuilders.get("/account/${accountSaved.id}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.id").value(accountSaved.id))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.name").value(accountSaved.name))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.document").value(accountSaved.document))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.phone").value(accountSaved.phone))
            .andDo(MockMvcResultHandlers.print())

    }



}