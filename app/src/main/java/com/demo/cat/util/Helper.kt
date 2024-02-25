package com.demo.cat.util

import android.content.Context
import android.text.Editable
import com.demo.cat.model.User
import com.demo.cat.model.UserDetails
import com.squareup.moshi.Moshi

object Helper {
    val moshi = Moshi.Builder().build()

    val adapter = moshi.adapter(UserDetails::class.java)


    fun saveUserData(context: Context, userName: String, password: String) {

        val sharedPreferences = context.getSharedPreferences("userDetail", Context.MODE_PRIVATE)
        val userDetailJson = sharedPreferences.getString("userDetail", null)


        userDetailJson?.let {

            val userDetail = adapter.fromJson(it)

            userDetail?.userList?.add(User(userName, password))

            sharedPreferences.edit().putString("userDetail", adapter.toJson(userDetail)).commit()
        } ?: kotlin.run {

            val userDetail = UserDetails(
                mutableListOf<User>(User(userName, password))
            )

            sharedPreferences.edit().putString("userDetail", adapter.toJson(userDetail)).commit()


        }

        //mutableList.userList.add( User(userName,password))


    }

    fun verifyCred(context: Context, userName: String, password: String): Boolean {
        val sharedPreferences = context.getSharedPreferences("userDetail", Context.MODE_PRIVATE)
        val userDetailJson = sharedPreferences.getString("userDetail", null)


        userDetailJson?.let {

            val userDetail = adapter.fromJson(it)

            userDetail?.userList?.forEach() {

                return it.password.equals(password, ignoreCase = false) && it.userName.equals(
                    userName
                )


            }

            sharedPreferences.edit().putString("userDetail", adapter.toJson(userDetail)).commit()
        }

        return false
    }

}