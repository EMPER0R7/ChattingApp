package com.example.bolbhai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigation=findViewById<CurvedBottomNavigation>(R.id.bottomNavigation)
        bottomNavigation.add(
            CurvedBottomNavigation.Model(1,"Chats",R.drawable.baseline_format_bold_24)
        )
        bottomNavigation.add(
            CurvedBottomNavigation.Model(2,"Search",R.drawable.baseline_group_add_24)
        )
        bottomNavigation.add(
            CurvedBottomNavigation.Model(3,"Profile",R.drawable.baseline_child_care_24)
        )

        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                1->{
                    replaceFragment(HomeFragment())
                }
                2->{
                    replaceFragment(SearchFragment())
                }
                3->{
                    replaceFragment(ProfileFragment())
                }
            }
        }
        replaceFragment(HomeFragment())
        bottomNavigation.show(1)

    }

    private fun replaceFragment(fragment: Fragment) {
 supportFragmentManager
     .beginTransaction()
     .replace(R.id.FragmentContainer,fragment)
     .commit()
    }
}