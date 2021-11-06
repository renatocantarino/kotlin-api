package cantarino.api.ktapi.Controllers

import cantarino.api.ktapi.Repositories.IAccountRepository
import cantarino.api.ktapi.domain.Account
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/account")
class AccountController(private val repository: IAccountRepository) {

    @PostMapping
    fun create(@RequestBody account: Account) : Account = repository.save(account)

    @GetMapping
    fun all() : List<Account> = repository.findAll()

    @GetMapping("/{id}")
    fun byId(@PathVariable id:Long ) : ResponseEntity<Account>
     = repository.findById(id)
                 .map { ResponseEntity.ok(it)}
                 .orElse( ResponseEntity.notFound().build())


    @PutMapping("/{id}")
    fun update(@PathVariable id:Long , @RequestBody account: Account ) : ResponseEntity<Account>
            = repository.findById(id)
                .map {
                    val toUpdate = it.copy(
                        name = it.name,
                        document = it.document,
                        phone = it.phone)
                    ResponseEntity.ok(repository.save(toUpdate))
                }
                .orElse( ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long  ) : ResponseEntity<Void>
            = repository.findById(id)
        .map {
            repository.delete(it)
            ResponseEntity<Void>(HttpStatus.ACCEPTED)
        }
        .orElse( ResponseEntity.notFound().build())
}