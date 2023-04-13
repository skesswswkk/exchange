package com.example.exchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.example.exchange.api.ServiceApi
import com.example.exchange.databinding.ActivityMainBinding
import com.example.exchange.model.ExchangeDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import android.widget.EditText
import android.R
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat
import java.util.Arrays.toString
import kotlin.Unit.toString
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.apilayer.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val exchangeService = retrofit.create(ServiceApi::class.java)

        //체크박스
        binding!!.checkbox1.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked) {
                checkTime()
                checkbox2.isChecked = false
                checkbox3.isChecked = false

                binding!!.tv4.text = "한국(KRW)"
                binding!!.tv7.text = "KRW/USD"
                binding!!.tv14.text = "KRW"

                exchangeService.getServiceAPI("p8ota46BT1rzEichGnVKHa4T66yFpamQ")
                    .enqueue(object: Callback<ExchangeDto>{
                        override fun onResponse(call: Call<ExchangeDto>, response: Response<ExchangeDto>) {
                            // TODO 성공처리
                            if(response.isSuccessful.not()){
                                return
                            }

                            response.body()?.let{
                                binding!!.tv6.text = String.format("%.2f", it.exchangeList.krw)
                            }
                        }
                        override fun onFailure(call: Call<ExchangeDto>, t: Throwable) {
                            // TODO 실패처리
                            Log.d(TAG, t.toString())
                        }
                    })

            }
        }

        binding!!.checkbox2.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked) {
                checkTime()
                checkbox1.isChecked = false
                checkbox3.isChecked = false

                binding!!.tv4.text = "일본(JPY)"
                binding!!.tv7.text = "JPY/USD"
                binding!!.tv14.text = "JPY"

                exchangeService.getServiceAPI("p8ota46BT1rzEichGnVKHa4T66yFpamQ")
                    .enqueue(object: Callback<ExchangeDto>{
                        override fun onResponse(call: Call<ExchangeDto>, response: Response<ExchangeDto>) {
                            // TODO 성공처리
                            if(response.isSuccessful.not()){
                                return
                            }

                            response.body()?.let{
                                binding!!.tv6.text = String.format("%.2f", it.exchangeList.jpy)
                            }
                        }
                        override fun onFailure(call: Call<ExchangeDto>, t: Throwable) {
                            // TODO 실패처리
                            Log.d(TAG, t.toString())
                        }
                    })
            }
        }

        binding!!.checkbox3.setOnCheckedChangeListener{ _, isChecked ->
            if(isChecked) {
                checkTime()
                checkbox1.isChecked = false
                checkbox2.isChecked = false

                binding!!.tv4.text = "필리핀(PHP)"
                binding!!.tv7.text = "PHP/USD"
                binding!!.tv14.text = "PHP"

                exchangeService.getServiceAPI("p8ota46BT1rzEichGnVKHa4T66yFpamQ")
                    .enqueue(object: Callback<ExchangeDto>{
                        override fun onResponse(call: Call<ExchangeDto>, response: Response<ExchangeDto>) {
                            // TODO 성공처리
                            if(response.isSuccessful.not()){
                                return
                            }

                            response.body()?.let{
                                binding!!.tv6.text = String.format("%.2f", it.exchangeList.php)
                            }
                        }
                        override fun onFailure(call: Call<ExchangeDto>, t: Throwable) {
                            // TODO 실패처리
                            Log.d(TAG, t.toString())
                        }
                    })
            }
        }

        //수취금액 계산
        binding!!.et1.setOnClickListener{

            //송금액 잘못 입력한 경우
            val editText1 = et1.text.toString().toDouble()
            if(0 > editText1 || editText1 > 10000){
                Toast.makeText(this@MainActivity, "송금액이 바르지 않습니다", Toast.LENGTH_SHORT).show()
            }

            //수취금액 계산
            val res = et1.text.toString().toInt() * tv6.text.toString().toDouble()
            val decimal = DecimalFormat("#,###.##")
            binding!!.tv13.text = decimal.format(res.toFloat())//12,345
        }
    }

    //입력금액 가져오기
//    val editText: EditText = findViewById(R.id.et1)

    companion object {
        private const val TAG = "KJH"
    }

    fun checkTime() {
        //조회시간
        val now: Long = System.currentTimeMillis()
        val date = Date(now)
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd HH:mm",
            Locale("ko", "KR")
        )
        val stringTime = dateFormat.format(date)
        Log.d(TAG, stringTime.toString())
        binding!!.tv9.text = stringTime
    }
//    fun onCheckboxClicked(view: View) {
//        if (view is CheckBox) {
//            val checked: Boolean = view.isChecked
//
//            when (view.id) {
//                R.id.checkbox1 -> {
//                    if (checked) {
//                        R.id.checkbox2.setChecked(false);
//                    } else {
//
//                    }
//                }
//                R.id.checkbox2 -> {
//                    if (checked) {
//
//                    } else {
//
//                    }
//                }
//                R.id.checkbox3 -> {
//                    if (checked) {
//
//                    } else {
//
//                    }
//                }
//            }
//        }
//    }
}