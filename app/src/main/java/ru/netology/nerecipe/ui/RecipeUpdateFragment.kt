package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.nerecipe.R
import ru.netology.nerecipe.databinding.FragmentUpdateBinding
import ru.netology.nerecipe.viewModel.RecipeViewModel

class RecipeUpdateFragment : Fragment() {

    private val viewModel by activityViewModels<RecipeViewModel>()
    private val args by navArgs<RecipeUpdateFragmentArgs>()
    private var categoryRecipeNumber = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUpdateBinding.inflate(layoutInflater, container, false).also { binding ->
        incomingArg(binding)
        binding.categoryRecipeCheckBoxOne.setOnCheckedChangeListener { _, i ->
            when (i) {
                R.id.checkBoxEuropean -> categoryRecipeNumber = getString(R.string.european_type)
                R.id.checkBoxAsian -> categoryRecipeNumber = getString(R.string.asian_type)
                R.id.checkBoxPanasian -> categoryRecipeNumber = getString(R.string.panasian_type)
                R.id.checkBoxEastern -> categoryRecipeNumber = getString(R.string.eastern_type)
                R.id.checkBoxAmerican -> categoryRecipeNumber = getString(R.string.american_type)
                R.id.checkBoxRussian -> categoryRecipeNumber = getString(R.string.russian_type)
                R.id.checkBoxMediterranean -> categoryRecipeNumber = getString(R.string.mediterranean_type)
            }
        }
        binding.buttonSave.setOnClickListener {
            onSaveButtonClicked(binding)
        }
    }.root

    private fun incomingArg(binding: FragmentUpdateBinding){
        binding.title.setText(args.idRecipe?.title)
        binding.authorName.setText(args.idRecipe?.authorName)
        binding.textRecipe.setText(args.idRecipe?.textRecipe)
        binding.categoryRecipeCheckBoxOne.check(when (args.idRecipe?.categoryRecipe) {
            getString(R.string.european_type) -> R.id.checkBoxEuropean
            getString(R.string.asian_type) -> R.id.checkBoxAsian
            getString(R.string.panasian_type) -> R.id.checkBoxPanasian
            getString(R.string.eastern_type) -> R.id.checkBoxEastern
            getString(R.string.american_type) -> R.id.checkBoxAmerican
            getString(R.string.russian_type) -> R.id.checkBoxRussian
            getString(R.string.mediterranean_type) -> R.id.checkBoxMediterranean
            else -> -1
        })
    }

    private fun onSaveButtonClicked(binding: FragmentUpdateBinding) {

        val id = args.idRecipe!!.id
        val title = binding.title.text.toString()
        val authorName = binding.authorName.text.toString()
        val textRecipe = binding.textRecipe.text.toString()

        if (!emptyCheckUpdateWarning(title = title, authorName = authorName, textRecipe = textRecipe, categoryRecipe = categoryRecipeNumber)) return

        viewModel.updateContent(
            id = id,
            title = title,
            authorNam = authorName,
            textRecipe = textRecipe,
            categoryRecipe = categoryRecipeNumber
        )
        findNavController().popBackStack()
    }

    private fun emptyCheckUpdateWarning(
        title: String,
        authorName: String,
        textRecipe: String,
        categoryRecipe: String
    ): Boolean {
        return if (title.isBlank() || authorName.isBlank() || textRecipe.isBlank() || categoryRecipe.isBlank()){
            Toast.makeText(activity, "All fields must be filled in", Toast.LENGTH_LONG).show()
            false
        } else true
    }
}