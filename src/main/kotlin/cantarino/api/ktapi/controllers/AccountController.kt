package cantarino.api.ktapi.controllers

import cantarino.api.ktapi.domain.Account
import cantarino.api.ktapi.services.IAccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/account")
class AccountController(private val accountService: IAccountService)
{

    @PostMapping
    fun create(@Valid @RequestBody account: Account) : Account = accountService.create(account)

    @GetMapping
    fun all() : List<Account> = accountService.getAll()

    @GetMapping("/{id}")
    fun byId(@PathVariable id:Long ) : ResponseEntity<Account>
     = accountService.findById(id)
                 .map { ResponseEntity.ok(it)}
                 .orElse( ResponseEntity.notFound().build())


    @PutMapping("/{id}")
    fun update(@PathVariable id:Long , @RequestBody account: Account ) : ResponseEntity<Account>
            = accountService.update(id,account)
                .map { ResponseEntity.ok(it)}
                .orElse( ResponseEntity.notFound().build())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id:Long  ) : ResponseEntity<Void>
    {
        accountService.delete(id)
        return ResponseEntity<Void>(HttpStatus.ACCEPTED)
    }

}