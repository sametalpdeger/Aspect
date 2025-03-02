package com.example.aspectchat.core.data.repositories

import com.apollographql.apollo.ApolloClient
import com.example.SignInQuery
import com.example.SignUpMutation
import javax.inject.Inject

class MainServerRepository @Inject constructor(
    private val apolloClient: ApolloClient
) {
    suspend fun getSignInResponse(secretID: String, userID: String): Boolean? {
        return apolloClient.query(SignInQuery(secretID, userID)).execute().data?.signIn?.success
    }

    suspend fun getSignUpResponse(): SignUpMutation.SignUp? {
        return apolloClient.mutation(SignUpMutation()).execute().data?.signUp
    }
}