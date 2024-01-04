package com.example.springBoot_mybatis.routes


data class InsertRequest(val id:Int,val name:String,val age:Int, val profile:String)
data class InsertResponse(val count:Int)

data class UpdateRequest (val name:String?,val age:Int?, val profile:String?)
data class UpdateResponse(val count:Int)

data class DeleteResponse(val count:Int)