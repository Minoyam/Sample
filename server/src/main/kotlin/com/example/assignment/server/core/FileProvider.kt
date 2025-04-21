package com.example.assignment.server.core

interface FileProvider {
    fun getJsonFromAsset(filePath: String): String?
}
