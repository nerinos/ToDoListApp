package com.nerinos.todolistapp.ui.deleteallcompleted

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // because it has viewmodel
class DeleteAllCompletedDialogFragment : DialogFragment() {

    private val viewModel: DeleteAllCompletedViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle("Confirm deletion")
            .setMessage("Do you really want to delete all completed tasks?")
            .setNegativeButton("Cancel", null)
            .setPositiveButton("Yes") { _, _ ->
                // call viewModel to delete
                viewModel.onConfirmClick()
            }
            .create()
}