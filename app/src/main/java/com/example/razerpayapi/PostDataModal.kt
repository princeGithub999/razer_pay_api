package com.example.razerpayapi

data class PostDataModal(
    val contact: String?=null,
    val email: String?=null,
    val name: String?=null,
    val gstin: String?=null,
    val fail_existing: String?=null,
    val notes: Notes?=null
)