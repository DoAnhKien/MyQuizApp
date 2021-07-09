package com.example.quizapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp.di.ApplicationScope
import com.example.quizapp.model.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Question::class],version = 3,exportSchema = false)
abstract class QuestionDatabase: RoomDatabase(){

    abstract fun questionDao(): QuestionDao

    class CallBack @Inject constructor(
        private val questionDatabase: Provider<QuestionDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            val questionDao = questionDatabase.get().questionDao()
            applicationScope.launch {
                questionDao.insertAQuestion(Question(1,"Việt nam có mấy mùa", "1", "2", "3", "4", 4, "Việt nam có 4 mùa xuân, hạ, thu, đông"))
                questionDao.insertAQuestion( Question(1,"Một ngày có bao nhiêu tiếng", "24 giờ", "20 giờ", "21 giờ", "22 giờ", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion(Question(1,"Con sông nào dài nhất bán đảo Đông Dương", "Sông Hồng", "Sông Thầy", "Mê Kông", "Sông Ấn", 1, "Bạn điền chú thích"));
                questionDao.insertAQuestion( Question(1,"Tứ diện có bao nhiêu đường chéo", "8", "4", "2", "0", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion( Question(1,"Rắn có mấy lá phổi", "2", "4", "1", "3", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion( Question(2,"Hai điểm duy nhất của địa cầu không quay gọi là gì", "Địa cực", "Trục", "Không có ", "Chỉ có 1 điểm", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion( Question(2,"Loài trăn thường ngủ ở đâu", "Trên nệm", "Trên cây", "Trên đá", "Trong hang", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion( Question(2,"Nguyên nhân nào gây ra hiện tượng sóng thần ở biển", "Gió lớn", "Bão", "Mưa", "Động đất", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion( Question(2,"Loài lưỡng cư nào thường xuất hiện và kêu to sau cơn mưa", "Ếch", "Cóc", "Nhái", "Ễnh ương", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion( Question(2,"Công thức tính diện tích hình chữ nhật", "a+b", "(a+b)x2", "a x b", "a-b", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion( Question(3,"Truyện Kiều có bao nhiêu câu thơ", "3425", "3542", "3323", "3524", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion( Question(3,"Ai là trưởng nhóm 10", "Huynh", "Hưng", "Đức", "Hoàng quân", 1, "Một ngày có 24 tiếng"));
                questionDao.insertAQuestion( Question(4,"Kiên có đẹp trai không", "Huynh", "Hưng", "Đức", "Hoàng quân", 1, "Một ngày có 24 tiếng"));
            }
        }

    }


}