package com.skanderjabouzi.squaretest.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var binding: EmployeesListFragmentBinding
    private lateinit var viewModel:EmployeesListViewModel

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EmployeesListFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory)[EmployeesListViewModel::class.java]
        binding.employeesRecyclerView.adapter = adapter
        binding.employeesRetryButton.setOnClickListener {
            getEmployees()
        }
        observeEmployees()
        observeErrors()
        return binding.root
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as App).appComponent
            .getEmployeesLIstFragmentComponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.employees_list)
    }

    private fun getEmployees() {
        showLoading()
        viewModel.reload()
    }

    private fun observeEmployees() {
        viewModel.employees.observe(viewLifecycleOwner, { employees ->
            hideLoading()
            if (employees.isEmpty()) {
                showMessage(getString(R.string.list_empty))
            } else {
                adapter.setEmployees(employees)
            }
        })
    }

    private fun observeErrors() {
        viewModel.error.observe(viewLifecycleOwner, { errorMessage ->
            hideLoading()
            showMessage(errorMessage)
        })
    }

    private fun showLoading() {
        binding.employeesRecyclerView.visibility = View.GONE
        binding.employeesProgressBar.visibility = View.VISIBLE
        binding.employeesRetryButton.visibility = View.GONE
        binding.employeesErroMessage.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.employeesRecyclerView.visibility = View.VISIBLE
        binding.employeesProgressBar.visibility = View.GONE
        binding.employeesRetryButton.visibility = View.GONE
        binding.employeesErroMessage.visibility = View.GONE
    }

    private fun showMessage(errorMessage: String) {
        if (errorMessage.isNotEmpty()) {
            binding.employeesRecyclerView.visibility = View.GONE
            binding.employeesProgressBar.visibility = View.GONE
            binding.employeesRetryButton.visibility = View.VISIBLE
            binding.employeesErroMessage.apply {
                isVisible = true
                text = errorMessage
            }
        }
    }
}