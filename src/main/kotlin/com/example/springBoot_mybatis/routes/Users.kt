package com.example.springBoot_mybatis.routes

import com.example.springBoot_mybatis.database.*
import org.mybatis.dynamic.sql.SqlBuilder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@RequestMapping("/api/v1/users")
class Users (val userMapper: UserMapper){
    @GetMapping("/")
    fun selectAll():List<UserRecord?> {
        return userMapper.select {
            allRows()
        }
    }

    @GetMapping("/{id}")
    fun select(@PathVariable("id") id:Int): UserRecord? {
        return userMapper.selectByPrimaryKey(id)
    }

    @PostMapping("/")
    fun insert(@RequestBody request: InsertRequest):InsertResponse {
        val record = UserRecord(request.id, request.name, request.age, request.profile)
        return InsertResponse(userMapper.insert(record))
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable("id") id:Int, @RequestBody request:UpdateRequest):UpdateResponse {
        println("Patchメソッド届いてます")
        val record = UserRecord(name = request.name, age = request.age, profile = request.profile)
        //一度すべてのプロパティに代入。ないものはNullになる。
        println("record : ${record}")
        val count = userMapper.update {
            updateSelectiveColumns(record)
            //SelectiveColumnsとなっているので、Nullのカラムは無視されて、UPdataしたいものだけされる
            where(UserDynamicSqlSupport.User.id, SqlBuilder.isEqualTo(id))
        }
        return UpdateResponse(count)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id:Int):DeleteResponse {
        return DeleteResponse(userMapper.deleteByPrimaryKey(id))
    }
}