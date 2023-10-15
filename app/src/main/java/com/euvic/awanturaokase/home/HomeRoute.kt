package com.euvic.awanturaokase.home

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

class HomeRoute {
    companion object {
        const val HOME_ROUTE_KEY: String = "HOME"
        val preparedRoute: String
            get() = navArguments.map { navArgument ->
                "/{${navArgument.name}}"
            }.joinToString { it }.let { preparedNames ->
                "$HOME_ROUTE_KEY$preparedNames"
            }

        const val ARGUMENT_NAME_EMAIL: String = "email"
        val navArguments: List<NamedNavArgument> = listOf(
            navArgument(name = ARGUMENT_NAME_EMAIL) {
                type = NavType.StringType
                nullable = false
            }
        )

        fun prepareRouteWithArgs(
            values: Map<String, Any>
        ): String {
            var preparedRouteCopy = preparedRoute

            values.filter { (name, _) ->
                navArguments.any { it.name == name }
            }.map { (name, value) ->
                preparedRouteCopy = preparedRouteCopy.replace("{$name}", "$value")
            }

            return preparedRouteCopy
        }
    }
}