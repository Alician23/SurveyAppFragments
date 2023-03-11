package com.bignerdranch.android.surveyappfragments

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

const val RANDOM_RESULT_DEVELOPED = "com.bignerdranch.android.surveyappfragments.RANDOM_RESULT_DEVELOPED"
/**
 * A simple [Fragment] subclass.
 * Use the [resultsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class resultsFragment : Fragment() {

    private lateinit var yesTotalVotesTextView: TextView
    private lateinit var noTotalVotesTextView: TextView
    private lateinit var resetButton: Button

    private val randomResultViewModel: RandomResultViewModel by lazy {
        ViewModelProvider(requireActivity()).get(RandomResultViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_results, container, false)
        yesTotalVotesTextView = view.findViewById(R.id.tv_Yes_Total_Votes)

        yesTotalVotesTextView.setOnClickListener {
            handleSurveyResult()
        }

        noTotalVotesTextView = view.findViewById(R.id.tv_No_Total_Votes)

        noTotalVotesTextView.setOnClickListener {
            handleSurveyResult()
        }

        resetButton = view.findViewById(R.id.btn_Reset)

        resetButton.setOnClickListener() {
            sumYes()
            sumNo()
            updateCounts()
            showResults()
            clearCount()
        }

        return view
    }

}

    private fun handleSurveyResult(result: Fragment?) {
        if (result?.resultCode == RESULT_OK) {
            val intent = result.data
            val shouldReset = intent?.getBooleanExtra(RANDOM_RESULT_DEVELOPED, false)?: false
            if (shouldReset) {
                randomResultViewModel.clearCount()
                yesTotalVotesTextView.setText(String.format("%d",randomResultViewModel.yesRespones))
                noTotalVotesTextView.setText(String.format("%d", randomResultViewModel.noResponses))
            }
            Toast.makeText(this, "Result screen", Toast.LENGTH_LONG).show()
        } else if (result?.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Back to main", Toast.LENGTH_LONG).show()
        }
    }


    //sumYes method calls to add all yes answers and save in SurveyViewModel class.
    private fun sumYes() {
        var yesRespones = 0

    }

    private fun sumNo() {
        var noResponses = 0
        noResponses++
    }

    // clearCount method resets yesTotalVote and noTotalVote TextViews to zeros.
    private fun clearCount() {
        yesRespones = 0
        noResponses = 0
    }


    private fun updateCounts() {
        yesTotalVotesTextView .text = "$yesCount + votes"
        noTotalVotesTextView .text = "$noCount + votes"
    }

    private fun showResults() {
        val showVoteResults = randomResultViewModel.showVoteResults


        parentFragmentManager.setFragment(RANDOM_RESULT_DEVELOPED, Bundle.EMPTY)
        parentFragmentManager.setFragment(RANDOM_RESULT_DEVELOPED, randomResultViewModel.yesRespones)
        parentFragmentManager.setFragment(RANDOM_RESULT_DEVELOPED, randomResultViewModel.noRespones)
        surveyResultLauncher.launch(randomResultViewModel)
    }

    companion object {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters
     */
    @JvmStatic
    fun newInstance() = resultsFragment

     }

