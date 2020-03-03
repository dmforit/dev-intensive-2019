package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = null,
    var isOnline: Boolean = false
) {

    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id:String) : this(id, "John", "Doe")

    init {
        println("It's Alive!!! \n${if(lastName === "Doe") "His name id $firstName $lastName" else "And his name is $firstName $lastName!!!"}\n")
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName:String?) : User {
            lastId++

            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    } 

    class Builder {
        private var id = ""
        private var firstName: String? = null
        private var lastName: String? = null
        private var avatar: String? = null
        private var rating = 0
        private var respect = 0
        private var lastVisit: Date? = null
        private var isOnline = false

        fun id(_id: String): Builder {
            id = _id
            return this
        }

        fun firstName(_firstName: String?): Builder {
            firstName = _firstName
            return this
        }

        fun lastName(_lastName: String?): Builder{
            lastName = _lastName
            return this
        }

        fun avatar(_avatar: String?): Builder{
            avatar = _avatar
            return this
        }

        fun rating(_rating: Int): Builder{
            rating = _rating
            return this
        }

        fun respect(_respect: Int): Builder{
            respect = _respect
            return this
        }

        fun lastVisit(_lastVisit: Date?): Builder{
            lastVisit = _lastVisit
            return this
        }

        fun isOnline(_isOnline: Boolean): Builder{
            isOnline = _isOnline
            return this
        }
        fun build(): User = User(id = id, firstName = firstName, lastName = lastName, avatar = avatar, rating = rating, respect = respect, lastVisit = lastVisit, isOnline = isOnline)
    }
}