package com.example.quizapp.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.viewmodel.QuestionViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val questionViewModel: QuestionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        insertQuestion()
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController)
    }

    private fun insertQuestion() {
        questionViewModel.getMQuestion().observe(this@MainActivity,{
            if (it.isEmpty()){
                questionViewModel.insertTheQuestion(
                    Question(
                        1,
                        "Thuộc tính đặc trưng của vật chất theo quan niệm MácLênin là gì ?",
                        "Là một phạm trù triết học",
                        "Là thực tại khách quan tồn tại bên ngoài, không lệ thuộc vào cảm giác ",
                        "Là toàn bộ thế giới hiện thực",
                        "Là tất cả những gì tác động vào giác quan ta gây lên cảm giác ",
                        2,
                        "Điền chú thích"
                    )
                )
                questionViewModel.insertAQuestion(
                    Question(
                        1,
                        "Một ngày có bao nhiêu tiếng ?",
                        "24 giờ",
                        "20 giờ",
                        "21 giờ",
                        "22 giờ",
                        1,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        1,
                        "Sai lầm của các quan niệm quy vật trước Mác về vật chất là gì ?",
                        "Đồng nhất vật chất với tồn tại",
                        "Quy vật chất về một dạng vật thể",
                        "Đồng nhất vật chất với hiện thực",
                        "Coi ý thức cũng là một dạng vật chất",
                        2,
                        "Sông mekong có diện tích 795,000 km2"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        1,
                        "Quan điểm: “vật chất và ý thức là hai nguyên thể đầu tiên cùng song song tồn tại” là quan điểm của trường phái triết học nào?",
                        "Duy vật biện chứng ",
                        "Duy vật siêu hình ",
                        "Duy tâm khách quan",
                        "Nhị nguyên ",
                        4,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        1,
                        "Quan điểm: \"Bản chất của thế giới là ý thức\" là quan điểm của trường phái triết học nào?",
                        "Duy vật ",
                        "Duy tâm ",
                        "Nhị nguyên ",
                        "Tất cả đều sai ",
                        2,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        2,
                        "Theo Ăngghen, hình thức vận động đặc trưng của con người và xã hội loài người là hình thức nào?",
                        "Vận động sinh học ",
                        "Trục",
                        "Không có ",
                        "Chỉ có 1 điểm",
                        1,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        2,
                        "Lựa chọn câu đúng nhất theo quan điểm CNDVBC",
                        "Nguồn gốc của sự vận động là ở bên ngoài sự vật hiện tượng do sự tương tác hay do sự tác động.",
                        "Nguồn gốc của sự vận động là do ý thức tinh thần tư tưởng quyết định",
                        "Nguồn gốc của sự vận động là ở trong bản thân sự vật hiện tượng do sự tác động của các mặt, các yếu tố trong sự vật hiện tượng gây ra",
                        "Nguồn gốc của sự vận động là do “Cú hích của thượng đế",
                        3,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        2,
                        "Lựa chọn câu đúng nhất theo quan điểm của CNDVBC.",
                        "Vận động, không gian, thời gian là sản phẩm do ý chí con người tạo ra, do đó nó không phải là vật chất. ",
                        "Vận động, không gian, thời gian không có tính vật chất.",
                        "Vận động, không gian, thời gian là hình thức tồn tại của vật chất. ",
                        "Cả a, b, c đều sai. ",
                        3,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        2,
                        "Chọn câu trả lời đúng với quan điểm của triết học MácLênin",
                        "Ý thức có nguồn gốc từ mọi dạng vật chất giống như gan tiết ra mật ",
                        "Ý thức của con người là hiện tượng bẩm sinh ",
                        "Ý thức con người trực tiếp hình thành từ lao động sản xuất vật chất của xã hội ",
                        "Cả a, b, c đều đúng",
                        3,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        2,
                        " Chọn câu trả lời đúng",
                        "Ý thức là thuộc tính của mọi dạng vật chất ",
                        "Ý thức là sự phản ánh nguyên xi hiện thực khách quan",
                        "Ý thức là sự phản ánh năng động, sang tạo hiện thực khách quan",
                        "Cả a, b, c đều sai ",
                        3,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        3,
                        "Ý thức có trước, vật chất có sau, ý thức quyết định vật chất, đây là quan điểm:",
                        "Duy vật ",
                        "Duy tâm",
                        "Nhị nguyên",
                        "Duy tâm chủ quan",
                        2,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        3,
                        "Đêmôcrít nhà triết học cổ Hy Lạp quan niệm vật chất là gì?",
                        "Nước",
                        "Lửa",
                        "Không khí",
                        "Nguyên tử",
                        4,
                        "Điền chú thích"
                    )
                );
                questionViewModel.insertAQuestion(
                    Question(
                        4,
                        "Theo Ph.Ăngghen, một trong những phương thức tồn tại cơ bản của vật chất là:",
                        "Phát triển",
                        "Chuyển hóa từ dạng này sang dạng khác",
                        "Phủ định",
                        "Vận động",
                        3,
                        "Điền chú thích"
                    )
                );
            }
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}