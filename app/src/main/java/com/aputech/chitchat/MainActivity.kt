package com.aputech.chitchat

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.aputech.chitchat.Adapters.MyAdapter
import com.aputech.chitchat.FragmentsUI.Calls
import com.aputech.chitchat.FragmentsUI.Camera
import com.aputech.chitchat.FragmentsUI.Inbox
import com.aputech.chitchat.FragmentsUI.Status
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager : ViewPager2
    private lateinit var tabLayout : TabLayout
    private lateinit var fab: FloatingActionButton
    private val tabTitles= arrayOf("","CHATS","STATUS","CALLS","WORLD")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar:Toolbar= findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.view_pager)
        tabLayout=findViewById(R.id.tabs)
        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = MyAdapter(this, this, 5)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if (position==0){
                tab.setIcon(R.drawable.ic_camera)
            }else{
                tabLayout.tabMode=TabLayout.MODE_FIXED
                tab.text = tabTitles[position]
            }
            viewPager.setCurrentItem(tab.position, true)
        }.attach()
        setTabWidthAsWrapContent(tabLayout,0)
        viewPager.setCurrentItem(1,false)

        fab =findViewById(R.id.fab)
        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                UpdateFabIcon(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //pass
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                UpdateFabIcon(tab)
            }

        })

    }

    fun UpdateFabIcon(tab: TabLayout.Tab?){
        when (tab?.position) {
            0->{
                fab.hide()
            }
            1->{
                fab.show()
                fab.setImageResource(R.drawable.ic_baseline_message)
                fab.setOnClickListener { view ->
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            2->{
                fab.show()
                fab.setImageResource(R.drawable.ic_camera)
                fab.setOnClickListener { view ->
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            3->{
                fab.show()
                fab.setImageResource(R.drawable.ic_baseline_add_ic_call)
                fab.setOnClickListener { view ->
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            4->{
                fab.hide()
                fab.setOnClickListener { view ->
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }
    fun setTabWidthAsWrapContent(tablayout:TabLayout,tabPosition: Int) {
        val layout = (tablayout.getChildAt(tabPosition) as LinearLayout).getChildAt(tabPosition) as LinearLayout
        val layoutParams = layout.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 0f
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        layout.layoutParams = layoutParams
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.searchMsg ->{
                Toast.makeText(this,"This is my message",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.Settings ->{
                val intent = Intent(this,SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }

}