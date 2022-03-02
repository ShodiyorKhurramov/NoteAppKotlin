package com.example.database.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kotlin.random.Random

open class Post : RealmObject{

    @PrimaryKey


    var id : Int ? = null

    var caption : String ? = null

    constructor(){}
    constructor(id : Int,caption : String ?){
        this.id=id
        this.caption=caption
    }
}