package com.example.kotlinproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity() {

    lateinit var tv: TextView

    val FirstFragment = Fragment1();
    val SecondFragment = Fragment2();
    val ThirdFragment = Fragment3();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        sendNotification();

        findViewById<BottomNavigationView>(R.id.MenuBottom).setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setTabStateFragment(TabState.HOME)
        supportFragmentManager.beginTransaction()
            .add(R.id.containerFragment, FirstFragment)
            .add(R.id.containerFragment, SecondFragment)
            .add(R.id.containerFragment, ThirdFragment)
            .commit()

        tv = findViewById(R.id.second_title)
    }

    fun sendNotification (){

        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "YOUR_CHANNEL_ID",
                "YOUR_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "YOUR_NOTIFICATION_CHANNEL_DESCRIPTION"
            mNotificationManager.createNotificationChannel(channel)
        }

        val builder =  NotificationCompat.Builder(this, "YOUR_CHANNEL_ID")
            .setSmallIcon(R.drawable.dofus)
            .setContentTitle("KAMAS")
            .setContentText("Tu as gagné 10.000 kamas petit veinard, c'est pas dans une poubelle que tu les aurais trouvés... Ne les dépense pas d'un coup !")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Tu as gagné 10.000 kamas petit veinard, c'est pas dans une poubelle que tu les aurais trouvés... Ne les dépense pas d'un coup !"))

        with(NotificationManagerCompat.from(this)){
            notify(1, builder.build())
        }
    }

    internal enum class TabState {
        HOME,
        ALARM,
        BREAD
    }

    private fun setTabStateFragment(state: TabState) {
        var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        when (state) {
            TabState.HOME -> {
                run {
                    transaction.show(FirstFragment)
                    transaction.hide(SecondFragment)
                    transaction.hide(ThirdFragment)
                }

            }
            TabState.ALARM -> {
                run {
                    transaction.show(SecondFragment)
                    transaction.hide(FirstFragment)
                    transaction.hide(ThirdFragment)
                }

            }
            TabState.BREAD -> {
                run {
                    transaction.show(ThirdFragment)
                    transaction.hide(SecondFragment)
                    transaction.hide(FirstFragment)
                }

            }
        }
        transaction.commit()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.tab_home -> {
                setTabStateFragment(TabState.HOME)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_camera -> {
                setTabStateFragment(TabState.ALARM)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_bread -> {
                setTabStateFragment(TabState.BREAD)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}