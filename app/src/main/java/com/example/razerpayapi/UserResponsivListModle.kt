package com.example.razerpayapi

data class UserResponsivListModle (
    val entity:String?=null,
    val count:Int?=null,
    val items: List<Item>?
)

data class Item(
    val id:String?=null,
    val entity:String?=null,
    val name:String?=null,
    val email:String?=null,
    val contact:String?=null,
    val gstin:String?=null
)
