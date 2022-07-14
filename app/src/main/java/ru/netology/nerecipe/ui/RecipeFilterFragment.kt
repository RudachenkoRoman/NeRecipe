package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.databinding.FragmentFilterBinding
import ru.netology.nerecipe.viewModel.RecipeViewModel


class RecipeFilterFragment : Fragment() {
    private val viewModel by activityViewModels<RecipeViewModel>()
    private lateinit var binding: FragmentFilterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFilterBinding.inflate(layoutInflater, container, false).also { binding = it

        with(binding) {
            checkBoxEuropean.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_EUROPEAN)
            checkBoxAsian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_ASIAN)
            checkBoxPanasian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_PAN_ASIAN)
            checkBoxEastern.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_EASTERN)
            checkBoxAmerican.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_AMERICAN)
            checkBoxRussian.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_RUSSIAN)
            checkBoxMediterranean.isChecked = viewModel.getStateSwitch(KEY_STATE_SWITCH_MEDITERANEAN)
        }

        with(binding) {

            checkBoxEuropean.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_EUROPEAN,
                    checkBoxEuropean.isChecked
                )
                switchesIsChecked(binding)
            }
            checkBoxAsian.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_ASIAN,
                    checkBoxAsian.isChecked
                )
                switchesIsChecked(binding)
            }
            checkBoxPanasian.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_PAN_ASIAN,
                    checkBoxPanasian.isChecked
                )
                switchesIsChecked(binding)
            }
            checkBoxEastern.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_EASTERN,
                    checkBoxEastern.isChecked
                )
                switchesIsChecked(binding)
            }
            checkBoxAmerican.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_AMERICAN,
                    checkBoxAmerican.isChecked
                )
                switchesIsChecked(binding)
            }
            checkBoxRussian.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_RUSSIAN,
                    checkBoxRussian.isChecked
                )
                switchesIsChecked(binding)
            }
            checkBoxMediterranean.setOnClickListener {
                viewModel.saveStateSwitch(
                    KEY_STATE_SWITCH_MEDITERANEAN,
                    checkBoxMediterranean.isChecked
                )
                switchesIsChecked(binding)
            }
        }
        binding.buttonApply.setOnClickListener {
            onApplyButtonClicked(binding)
        }
    }.root

    private fun switchesIsChecked(binding: FragmentFilterBinding) {
        if (viewModel.filterIsActive) {
            with(binding) {
//                if (checkBoxEuropean.isChecked) checkBoxEuropean.isChecked= false
//                if (checkBoxAsian.isChecked) checkBoxAsian.isChecked = false
//                if (checkBoxPanasian.isChecked) checkBoxPanasian.isChecked = false
//                if (checkBoxEastern.isChecked) checkBoxEastern.isChecked = false
//                if (checkBoxAmerican.isChecked) checkBoxAmerican.isChecked = false
//                if (checkBoxRussian.isChecked) checkBoxRussian.isChecked = false
//                if (checkBoxMediterranean.isChecked) checkBoxMediterranean.isChecked = false
            }
        } else {
            with(binding) {
//                if (checkBoxEuropean.isChecked) checkBoxEuropean.isChecked  = true
//                if (checkBoxAsian.isChecked) checkBoxAsian.isEnabled = true
//                if (checkBoxPanasian.isChecked) checkBoxPanasian.isEnabled = true
//                if (checkBoxEastern.isChecked) checkBoxEastern.isEnabled = true
//                if (checkBoxAmerican.isChecked) checkBoxAmerican.isEnabled = true
//                if (checkBoxRussian.isChecked) checkBoxRussian.isEnabled = true
//                if (checkBoxMediterranean.isChecked) checkBoxMediterranean.isEnabled = true
            }
        }
    }

    companion object {
        const val KEY_STATE_SWITCH_EUROPEAN = "european"
        const val KEY_STATE_SWITCH_ASIAN = "asian"
        const val KEY_STATE_SWITCH_PAN_ASIAN = "pan_asian"
        const val KEY_STATE_SWITCH_EASTERN = "eastern"
        const val KEY_STATE_SWITCH_AMERICAN = "american"
        const val KEY_STATE_SWITCH_RUSSIAN = "russian"
        const val KEY_STATE_SWITCH_MEDITERANEAN = "mediterranean"
    }

    private fun onApplyButtonClicked(binding: FragmentFilterBinding) {
        var checkedCount = 0
        val numberOfFilters = 7

        if (!binding.checkBoxEuropean.isChecked) {
            ++checkedCount
            viewModel.showEuropean("Европейская кухня")
        }
        if (!binding.checkBoxAsian.isChecked) {
            ++checkedCount
            viewModel.showAsian("Азиатская кухня")
        }
        if (!binding.checkBoxPanasian.isChecked) {
            ++checkedCount
            viewModel.showPanasian("Паназиатская кухня")
        }
        if (!binding.checkBoxEastern.isChecked) {
            ++checkedCount
            viewModel.showEastern("Восточная кухня")
        }
        if (!binding.checkBoxAmerican.isChecked) {
            ++checkedCount
            viewModel.showAmerican("Американская кухня")
        }
        if (!binding.checkBoxRussian.isChecked) {
            ++checkedCount
            viewModel.showRussian("Русская кухня")
        }
        if (!binding.checkBoxMediterranean.isChecked) {
            ++checkedCount
            viewModel.showMediterranean( "Средиземноморская кухня")
        }
        if (checkedCount == numberOfFilters) {
            Toast.makeText(activity, "Выберите хотя бы один вариант", Toast.LENGTH_LONG).show()
            return
        } else findNavController().popBackStack()
    }
}


