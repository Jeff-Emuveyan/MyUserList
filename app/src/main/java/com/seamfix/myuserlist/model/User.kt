package com.seamfix.myuserlist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(@PrimaryKey var id: String,
           var title: String? = null,
           var firstName: String? = null,
           var lastName: String? = null,
           var gender: String? = null,
           var email: String? = null,
           var dateOfBirth: String? = null,
           var registerDate: String? = null,
           var picture: String? = null,
           var location: Location? = null)

class Location( var street: String? = null,
                var city: String? = null,
                var state: String? = null,
                var country: String? = null,
                var timezone: String? = null){

    override fun toString(): String {
        return "Street: $street, city: $city, state: $state, country: $country, timezone: $timezone."
    }
}