package com.vctapps.beacon.core.presentation

import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.vctapps.beacon.R

class RecyclerViewState : RelativeLayout {

    enum class State {
        LOADING, EMPTY, FILLED
    }

    lateinit var errorMessage: ViewGroup
    lateinit var loadingMessage: ViewGroup
    lateinit var recycleView: RecyclerView
    lateinit var layoutManager: RecyclerView.LayoutManager

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context){
        View.inflate(context, R.layout.recyclerviewstate, this)

        errorMessage = findViewById(R.id.error_message) as ViewGroup
        loadingMessage = findViewById(R.id.loading_message) as ViewGroup
        recycleView = findViewById(R.id.recyclerview_base) as RecyclerView
        layoutManager = LinearLayoutManager(context)

        recycleView.layoutManager = layoutManager

        recycleView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    fun setState(state: State){
        when(state){
            State.LOADING -> {
                errorMessage.visibility = View.GONE
                loadingMessage.visibility = View.VISIBLE
                recycleView.visibility = View.GONE
            }
            State.FILLED -> {
                errorMessage.visibility = View.GONE
                loadingMessage.visibility = View.GONE
                recycleView.visibility = View.VISIBLE
            }
            State.EMPTY -> {
                errorMessage.visibility = View.VISIBLE
                loadingMessage.visibility = View.GONE
                recycleView.visibility = View.GONE
            }
        }
    }

    fun setNewLayoutManager(layoutManager: RecyclerView.LayoutManager){
        recycleView.layoutManager = layoutManager
    }

    fun getRecyclerView(): RecyclerView {
        return recycleView
    }
}