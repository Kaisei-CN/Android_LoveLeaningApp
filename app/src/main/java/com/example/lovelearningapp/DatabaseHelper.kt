package com.example.lovelearningapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_ARTICLES_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_TITLE + " TEXT,"
                + COLUMN_CONTENT + " TEXT," + COLUMN_IMAGE_RES_ID + " INTEGER," + COLUMN_VIDEO_RES_ID + " INTEGER" + ")")
        db.execSQL(CREATE_ARTICLES_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        // Create tables again
        onCreate(db)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "articlesDB"
        const val TABLE_NAME = "articles"
        const val COLUMN_ID = "id"
        const val COLUMN_TOPIC = "topic"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_IMAGE_RES_ID = "imageResId"
        const val COLUMN_VIDEO_RES_ID = "videoResId"
    }
}
