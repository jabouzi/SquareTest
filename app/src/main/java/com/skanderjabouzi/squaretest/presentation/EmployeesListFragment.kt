package com.skanderjabouzi.squaretest.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.skanderjabouzi.squaretest.R
import com.skanderjabouzi.squaretest.core.App
import com.skanderjabouzi.squaretest.databinding.EmployeesListFragmentBinding
import com.skanderjabouzi.squaretest.utils.ViewModelFactory
import javax.inject.Inject

class EmployeesListFragment : Fragment() {

    @Inject
    lateinit var adapter: EmployeesListAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var employeesListFragmentBinding: EmployeesListFragmentBinding
    private lateinit var viewModel: EmployeesListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        employeesListFragmentBinding = EmployeesListFragmentBinding.inflate(inflater, container, false)
        return employeesListFragmentBinding.root
    }

    override fun onAttach(context: Context) {
        (context as App).appComponent
            .getEmployeesLIstFragmentComponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[EmployeesListViewModel::class.java]

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        view?.findViewById<Toolbar>(R.id.toolbar)
            ?.setupWithNavController(navController, appBarConfiguration)

        employeesListFragmentBinding.toolbarView.toolbarTitle.text = getString(R.string.employees_list)

        observeEmployees()
        observeErrors()
        getEmployees()
    }

    private fun getEmployees() {
        viewModel.getEmployeesList()
    }

    private fun observeErrors() {
        viewModel.employees.observe(viewLifecycleOwner, Observer { employees ->
            hideLoading()
            adapter.setEmployees(employees)
        })
    }

    private fun hideLoading() {
        TODO("Not yet implemented")
    }

    private fun observeEmployees() {
        TODO("Not yet implemented")
    }

}