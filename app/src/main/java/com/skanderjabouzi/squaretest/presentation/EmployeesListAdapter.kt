package com.skanderjabouzi.squaretest.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.imageLoader
import com.skanderjabouzi.squaretest.R
import com.skanderjabouzi.squaretest.data.model.Employee
import javax.inject.Inject

class EmployeesListAdapter @Inject constructor(context: Context) :

    RecyclerView.Adapter<EmployeesListAdapter.EmployeesViewHolder>(){

    private var employees = mutableListOf<Employee>()
    val imageLoader = context.imageLoader

    inner class EmployeesViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(employee: Employee, position: Int) = with(view) {

//            itemView
//            itemView.image.load(employee.photoUrlSmall) {
//                crossfade(true)
//                placeholder(R.drawable.placeholder)
//                error(R.drawable.placeholder)
//                transformations(CircleCropTransformation())
//            }
        }
    }

    fun setEmployees(employeesList: List<Employee>) {
        this.employees.clear()
        this.employees.addAll(employeesList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int = R.layout.employees_list_item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employees_list_item, parent, false)
        Coil.setImageLoader(imageLoader)
        return EmployeesViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
       holder.bind(employees[position], position)
    }

    override fun getItemCount(): Int = employees.size
}