package cantarino.api.ktapi.repositories

import cantarino.api.ktapi.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface IAccountRepository : JpaRepository<Account , Long>{}