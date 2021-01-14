package com.seamfix.myuserlist.model

class User(var id: String,
           var title: String = "",
           var firstName: String = "",
           var lastName: String = "",
           var gender: String = "",
           var email: String = "",
           var dateOfBirth: String = "",
           var registerDate: String = "",
           var picture: String = "",
           var location: Location? = null)

class Location( var street: String,
                var city: String,
                var state: String,
                var country: String,
                var timezone: String)