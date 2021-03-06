package com.skanderjabouzi.squaretest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.Coil
import coil.ImageLoader
import coil.load
import coil.transform.CircleCropTransformation
import com.skanderjabouzi.squaretest.R
import com.skanderjabouzi.squaretest.data.model.Employee
import com.skanderjabouzi.squaretest.databinding.EmployeesListItemBinding
import okhttp3.internal.format
import javax.inject.Inject

class EmployeesListAdapter @Inject constructor() :
    RecyclerView.Adapter<EmployeesListAdapter.EmployeesViewHolder>(){

    private var employees = mutableListOf<Employee>()
    private lateinit var imageLoader: ImageLoader
    lateinit var itemBinding: EmployeesListItemBinding
        private set

    inner class EmployeesViewHolder(val itemBinding: EmployeesListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(employee: Employee) {
            val context = itemBinding.root.context
            itemBinding.fullName.text = employee.fullName
            itemBinding.biography.text = employee.biography
            itemBinding.contact.text = format(context.getString(R.string.contact_text),
                employee.phoneNumber, employee.emailAddress
            )
            itemBinding.team.text = employee.team
            itemBinding.type.text = employee.employeeType
            itemBinding.image.load(employee.photoUrlSmall) {
                crossfade(true)
                placeholder(R.drawable.placeholder)
                error(R.drawable.placeholder)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        itemBinding = EmployeesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        imageLoader = ImageLoader(parent.context)
        Coil.setImageLoader(imageLoader)
        return EmployeesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        holder.bind(employees[position])
    }

    override fun getItemCount(): Int {
        return employees.size
    }

    fun setEmployees(employeesList: List<Employee>) {
        employees.clear()
        employees.addAll(employeesList)
        notifyDataSetChanged()
    }

}