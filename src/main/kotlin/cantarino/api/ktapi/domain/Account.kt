package cantarino.api.ktapi.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "account")
data class Account (
    @Id
    @GeneratedValue
    var id: Long? = null,
    val name: String,
    val document: String,
    val phone: String)