package com.honar.walletcash

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.category_dialog_layout.*
import java.lang.Exception
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class MainMenuActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    var database = MyDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        //database.clearData()
        setUpMainRV()
        getItemData()
        addIemIB.setOnClickListener {
            onAddItemClick()
        }
        title = "جزدانکا من"
    }

    @SuppressLint("SetTextI18n")
    private fun onAddItemClick() {
        var dialog = Dialog(this)
        dialog.setContentView(R.layout.insert_data_dialog)
        val metrics = resources.displayMetrics
        val screenWidth = metrics.widthPixels
        val screenHeight = metrics.heightPixels

        var cCategory = 18
        var c: DatePicker
        dialog.findViewById<Space>(R.id.spcaee).layoutParams = ConstraintLayout.LayoutParams(
            (screenWidth * 0.8).toInt(),
            0
        )

        dialog.findViewById<TextView>(R.id.costDate).text =
            android.text.format.DateFormat.format("yyyy-MM-dd", Date())

        dialog.show()
        dialog.findViewById<Button>(R.id.addCostBtn).setOnClickListener {
            val myCash = Cash(
                name = dialog.findViewById<EditText>(R.id.costName).text.toString(),
                description = dialog.findViewById<EditText>(R.id.costDescrition).text.toString(),
                tag = cCategory,
                cash = dialog.findViewById<EditText>(R.id.costCost).text.toString().toInt(),
                date = SimpleDateFormat("yyyy-MM-dd").parse(dialog.findViewById<TextView>(R.id.costDate).text.toString())
            )
            database.addNewCost(
                name = dialog.findViewById<EditText>(R.id.costName).text.toString(),
                description = dialog.findViewById<EditText>(R.id.costDescrition).text.toString(),
                tag = cCategory,
                cost = dialog.findViewById<EditText>(R.id.costCost).text.toString().toInt() * if (dialog.findViewById<ToggleButton>(
                        R.id.costToggleButton
                    ).isChecked
                ) {
                    1
                } else {
                    -1
                },
                datee = SimpleDateFormat("yyyy-MM-dd").parse(dialog.findViewById<TextView>(R.id.costDate).text.toString())
            )
            getItemData()
            dialog.dismiss()

        }

        dialog.findViewById<LinearLayout>(R.id.dateContainer).setOnClickListener {
            val dialog1 = Dialog(this)
            dialog1.setContentView(R.layout.date_picker_dialog_alert)
            dialog1.show()
            c = dialog1.findViewById(R.id.datepickerDialog)

            c.maxDate = Date().time
            dialog1.findViewById<Button>(R.id.en).setOnClickListener {

                var month = (c.month + 1).toString()
                if (month.length == 1) {
                    val day = month
                    month = "0$day"
                }


                dialog.findViewById<TextView>(R.id.costDate).text =
                    "${c.year}-$month-${c.dayOfMonth}"
                dialog1.dismiss()
            }
        }

        dialog.findViewById<LinearLayout>(R.id.selectCategory).setOnClickListener {
            val dialog2 = Dialog(this)
            dialog2.setContentView(R.layout.category_dialog_layout)
            /*dialog2.findViewById<Space>(R.id.spaceCCC).layoutParams = TableLayout.LayoutParams(
                (screenWidth * 0.9).toInt(),
                0
            )*/
            dialog2.show()
            dialog2.findViewById<ImageView>(R.id.tag_1_food).setOnClickListener {
                cCategory = 1; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_food);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_2_shopping).setOnClickListener {
                cCategory = 2; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_shopping);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_3_taxi).setOnClickListener {
                cCategory = 3; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_taxi);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_4_home).setOnClickListener {
                cCategory = 4; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_home);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_5_bill_and_fee).setOnClickListener {
                cCategory = 5; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_bill_and_fee);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_6_entertainment).setOnClickListener {
                cCategory = 6; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_entertainment);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_7_car).setOnClickListener {
                cCategory = 7; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_car);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_8_oil).setOnClickListener {
                cCategory = 8; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_oil);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_9_education).setOnClickListener {
                cCategory = 9; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_education);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_10_travl).setOnClickListener {
                cCategory = 10; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_travl);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_11_family).setOnClickListener {
                cCategory = 11; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_family);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_12_health).setOnClickListener {
                cCategory = 12; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_health);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_13_groceries).setOnClickListener {
                cCategory = 13; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_groceries);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_14_gift).setOnClickListener {
                cCategory = 14; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_gift);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_15_sport).setOnClickListener {
                cCategory = 15; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_sport);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_16_beaty).setOnClickListener {
                cCategory = 16; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_beaty);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_17_work).setOnClickListener {
                cCategory = 17; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_work);dialog2.dismiss()
            }
            dialog2.findViewById<ImageView>(R.id.tag_18_other).setOnClickListener {
                cCategory = 18; dialog.findViewById<ImageView>(R.id.categoryImage)
                .setImageResource(R.drawable.tag_other);dialog2.dismiss()
            }

        }
    }


    fun idToNumber(id: Int): Int {
        return when (id) {
            R.id.tag_1_food -> 1
            R.id.tag_2_shopping -> 2
            R.id.tag_3_taxi -> 3
            R.id.tag_4_home -> 4
            R.id.tag_5_bill_and_fee -> 5
            R.id.tag_6_entertainment -> 6
            R.id.tag_7_car -> 7
            R.id.tag_8_oil -> 8
            R.id.tag_9_education -> 9
            R.id.tag_10_travl -> 10
            R.id.tag_11_family -> 11
            R.id.tag_12_health -> 12
            R.id.tag_13_groceries -> 13
            R.id.tag_14_gift -> 14
            R.id.tag_15_sport -> 15
            R.id.tag_16_beaty -> 16
            R.id.tag_17_work -> 17
            R.id.tag_18_other -> 18
            else -> 18
        }
    }


    @SuppressLint("SetTextI18n")
    private fun getItemData() {

        var mezaxtn = 0
        var qazanc = 0
        val berdest: Int

        database.getAllData()
        showItemData.clear()
        for (item in itemData) {
            showItemData.add(item)
            if (item.cash >= 0) {
                qazanc += item.cash
            } else {
                mezaxtn += item.cash
            }
        }

        berdest = qazanc - mezaxtn * -1

        val dcf = DecimalFormat("#,###.#")
        berdestTV.text = "${dcf.format(berdest)} IQD"
        mezaxtnTV.text = "${dcf.format(mezaxtn)} IQD"
        qazancTV.text = "${dcf.format(qazanc)} IQD"

        mainAdapter.notifyDataSetChanged()
    }

    val mainAdapter = ItemRVA(this)

    private fun setUpMainRV() {
        mainAdapter.onImageClickListener(OnItemClickListner { cash, position ->
            itemClicked(cash, position)
        })
        mainRV.layoutManager = GridLayoutManager(this, 1/*number of columns*/)
        mainRV.addItemDecoration(RecyclerViewPaiding(10))

        mainRV.adapter = mainAdapter
    }

    private fun itemClicked(cash: Cash, position: Int) {
        if (position == 123456) {
            AlertDialog.Builder(this)
                .setTitle("تەدڤێت ژێببەی")
                .setPositiveButton("بەلێ"){_,_ ->
                    database.deleteItem(cash.id)
                    getItemData()
                }.setNegativeButton("نەخێر"){_,_ ->

                }.show()
        } else {

        }
    }


    var searchItem: MenuItem? = null
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)

        searchItem = menu!!.findItem(R.id.app_bar_search)
        val searchView = searchItem!!.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search"
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.mSort -> {

                val dialog = Dialog(this)
                dialog.setContentView(R.layout.sort_dialog)

                val metrics = resources.displayMetrics
                val screenWidth = metrics.widthPixels

                dialog.findViewById<Space>(R.id.mainSpace).layoutParams = LinearLayout.LayoutParams(
                    (screenWidth * 0.9).toInt(),
                    0
                )
                dialog.show()

                dialog.findViewById<Button>(R.id.newedstBtn).setOnClickListener {
                    sortDataLike(NUMnewedstBtn)
                    dialog.dismiss()
                }
                dialog.findViewById<Button>(R.id.oldestBtn).setOnClickListener {
                    sortDataLike(NUMoldestBtn)
                    dialog.dismiss()
                }
                dialog.findViewById<Button>(R.id.highestPrice).setOnClickListener {
                    sortDataLike(NUMhighestPrice)
                    dialog.dismiss()
                }
                dialog.findViewById<Button>(R.id.lowestPrice).setOnClickListener {
                    sortDataLike(NUMlowestPrice)
                    dialog.dismiss()
                }
                dialog.findViewById<Button>(R.id.sortByType).setOnClickListener {
                    sortDataLike(NUMType)
                    dialog.dismiss()
                }
                dialog.findViewById<Button>(R.id.sortDefult).setOnClickListener {
                    sortDataLike(NUMDefult)
                    dialog.dismiss()
                }

            }
//            R.id.mSetting -> {
//                ttt(this, "Settings")
//
//            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sortDataLike(sort: Int) {
        showItemData.clear()
        when (sort) {
            NUMnewedstBtn -> {
                itemData.sortWith(compareBy { it.date })
                itemData.reverse()
            }
            NUMoldestBtn -> {

                itemData.sortWith(compareBy { it.date })
            }
            NUMhighestPrice -> {
                itemData.sortWith(compareBy { abs(it.cash) })
                itemData.reverse()
            }
            NUMlowestPrice -> {
                itemData.sortWith(compareBy { abs(it.cash) })
            }
            NUMType -> {
                itemData.sortWith(compareBy { it.tag })
            }
            NUMDefult -> {
                itemData.sortWith(compareBy { it.id })

                itemData.reverse()
            }
        }
        for (item in itemData) {
            showItemData.add(item)
        }
        mainAdapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return true
    }

    var searchMode = false

    @SuppressLint("DefaultLocale")
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!!.isNotEmpty()) {
            showItemData.clear()
            for (cash in itemData) {
                if (cash.name.toUpperCase().contains(newText.toUpperCase()) ||
                    cash.description.toUpperCase().contains(newText.toUpperCase()) ||
                    cash.cash.toString().contains(newText)
                ) {
                    showItemData.add(cash)
                }
            }
            searchMode = true
            mainAdapter.notifyDataSetChanged()
        } else {
            searchMode = false
            showItemData.clear()
            for (item in itemData)
                showItemData.add(item)
            mainAdapter.notifyDataSetChanged()
        }
        Log.d("searchS", newText + "")
        return true
    }


}
