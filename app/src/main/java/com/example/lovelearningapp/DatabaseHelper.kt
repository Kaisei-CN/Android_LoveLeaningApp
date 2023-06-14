package com.example.lovelearningapp

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.ContentProviderCompat.requireContext

//使用
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    @SuppressLint("Range")
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


    // 获取文章
    fun getArticle(articleId: String): Article? {
        val db = readableDatabase
        val projection = arrayOf(COLUMN_TITLE, COLUMN_CONTENT, COLUMN_IMAGE_RES_ID, COLUMN_VIDEO_RES_ID)
        val selection = "$COLUMN_ID = ?"
        val selectionArgs = arrayOf(articleId)
        val cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null)
        var article: Article? = null

        if (cursor.moveToFirst()) {
            val titleIndex = cursor.getColumnIndexOrThrow(COLUMN_TITLE)
            val contentIndex = cursor.getColumnIndexOrThrow(COLUMN_CONTENT)
            val imageResIdIndex = cursor.getColumnIndexOrThrow(COLUMN_IMAGE_RES_ID)
            val videoResIdIndex = cursor.getColumnIndexOrThrow(COLUMN_VIDEO_RES_ID)
            val title = cursor.getString(titleIndex)
            val content = cursor.getString(contentIndex)
            val imageResId = cursor.getInt(imageResIdIndex)
            val videoResId = cursor.getInt(videoResIdIndex)
            article = Article(articleId, title, content, imageResId, videoResId)
        }

        cursor.close()
        return article
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
