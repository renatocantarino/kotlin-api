package cantarino.api.ktapi.services

import cantarino.api.ktapi.domain.Account
import java.util.*

interface IAccountService {
    fun create(account: Account) : Account
    fun findById(id: Long) : Optional<Account>
    fun getAll() : List<Account>
    fun delete(id: Long)
    fun update(id: Long , account: Account) : Optional<Account>
}