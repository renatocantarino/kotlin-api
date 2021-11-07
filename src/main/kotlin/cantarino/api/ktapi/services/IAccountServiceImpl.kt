package cantarino.api.ktapi.services

import cantarino.api.ktapi.domain.Account
import cantarino.api.ktapi.repositories.IAccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.lang.RuntimeException
import java.util.*

@Service
class IAccountServiceImpl(private val repository: IAccountRepository) : IAccountService {
    override fun create(account: Account): Account {

        Assert.hasLength(account.name)
        return repository.save(account)
    }

    override fun findById(id: Long): Optional<Account> {
       return repository.findById(id)
    }

    override fun getAll(): List<Account> {
        return repository.findAll()
    }

    override fun delete(id: Long) {
        repository.findById(id)
            .map { repository.delete(it) }
            .orElseThrow { throw RuntimeException("Id not found")}
    }

    override fun update(id: Long, account: Account): Optional<Account> {
        val _account = findById(id)
        if(_account.isEmpty) Optional.empty<Account>()

       return _account.map {
           val _toUpdate = it.copy(name = it.name, document = it.document, phone = it.phone)
           create(_toUpdate)
       }
    }
}