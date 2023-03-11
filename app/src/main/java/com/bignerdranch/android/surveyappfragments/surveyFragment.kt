package com.bignerdranch.android.surveyappfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlin.random.Random

const val RANDOM_RESULTS = "com.bignerdranch.android.surveyappfragments.RANDOM_RESULTS"

/**
 * A simple [Fragment] subclass.
 * Use the [surveyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class surveyFragment : Fragment() {

    private lateinit var displaySurveyQuestionTextView: TextView
    private lateinit var yesRandomButton: Button
    private lateinit var noRandomButton: Button

    private val randomResultViewModel : RandomResultViewModel by lazy {
        ViewModelProvider(requireActivity()).get(RandomResultViewModel :: class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_survey, container, false)
        displaySurveyQuestionTextView = view.findViewById(R.id.tv_survey_question)
        showSurveyQuestion()

        yesRandomButton = view.findViewById(R.id.btn_yes_fragment)
        noRandomButton = view.findViewById(R.id.btn_no_fragment)

         // Yes button is pressed by users. Yes responses are tally by sumYesCount method.
        yesRandomButton.setOnClickListener {
            yesRandom()
        }

        noRandomButton.setOnClickListener {
            noRandom()
        }
        return view
    }

    private fun showSurveyQuestion() {
        val surveyQuestion = randomResultViewModel.surveyQuestion
        displaySurveyQuestionTextView.text = surveyQuestion.toString()
    }

    private fun yesRandom() {
        val yesRandomResult = Random.nextInt(50)
        randomResultViewModel.randomResult = yesRandomResult
        parentFragmentManager.setFragmentResult(RANDOM_RESULTS, Bundle.EMPTY)
    }

    private fun noRandom() {
        val noRandomResult = Random.nextInt(50)
        randomResultViewModel.randomResult = noRandomResult
        parentFragmentManager.setFragmentResult(RANDOM_RESULTS, Bundle.EMPTY)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters
         */
        @JvmStatic
        fun newInstance() = surveyFragment
    }
}