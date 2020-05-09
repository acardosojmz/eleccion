package uabjo.drti.eleccion.modules.elections.framework.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_candidatos.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import uabjo.drti.eleccion.BR
import uabjo.drti.eleccion.R
import uabjo.drti.eleccion.modules.common.domain.ScreenState
import uabjo.drti.eleccion.modules.common.framework.DataBindingVariables
import uabjo.drti.eleccion.modules.common.framework.GenericDataBindingAdapter
import uabjo.drti.eleccion.modules.common.framework.utils.MarginItemDecoration
import uabjo.drti.eleccion.modules.elections.domain.model.Candidate
import uabjo.drti.eleccion.modules.elections.domain.model.CandidatesVoted
import uabjo.drti.eleccion.modules.elections.domain.model.Vote
import uabjo.drti.eleccion.modules.elections.domain.model.Votes
import uabjo.drti.eleccion.modules.elections.presentation.CandidatesScreenState
import uabjo.drti.eleccion.modules.elections.presentation.CandidatesViewModel
import uabjo.drti.eleccion.modules.elections.presentation.CandidatesVotedScreenState



class CandidatesFragment : Fragment() {
    private lateinit var candidatesAdapter: GenericDataBindingAdapter<Candidate>
    private val candidatesViewModel: CandidatesViewModel by viewModel()
    private var candidates : MutableList<Candidate> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_candidatos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        candidatesAdapter = GenericDataBindingAdapter(BR.candidate, R.layout.candidato)
        rvCandidatos?.apply {
            adapter = candidatesAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.default_padding).toInt()
                )
            )
            setHasFixedSize(true)
        }

        fabSave?.setOnClickListener {
            val votes = candidatesAdapter.getItems().map { candidate ->
                Vote(candidateId = candidate.id, numberOfVotes = candidate.votes)
            }
            candidatesViewModel.saveCandidatesVotes(
                Votes(
                    imei = "213128937218931283",
                    candidates = votes
                )
            )
        } //--- End fabSave

        candidatesViewModel.candidates.observe(viewLifecycleOwner, Observer { screenState ->
            renderCandidatesUI(screenState)
        })

        candidatesViewModel.candidatesVoted.observe(viewLifecycleOwner, Observer { screenState ->
            renderCandidatesVoted(screenState)
        })
    }

    private fun renderCandidatesVoted(screenState: ScreenState<CandidatesVotedScreenState>) {
        when (screenState) {
            ScreenState.Loading -> sendingCandidatesVotes()
            is ScreenState.Render -> candidatesVotedSent(screenState.data)
        }
    }

    private fun candidatesVotedSent(candidatesVotedScreenState: CandidatesVotedScreenState) {
        when (candidatesVotedScreenState) {
            is CandidatesVotedScreenState.Success -> candidatesVotedSuccessful(
                candidatesVotedScreenState.candidatesVoted
            )
            is CandidatesVotedScreenState.Error -> showError(candidatesVotedScreenState.message)
        }
    }

    private fun candidatesVotedSuccessful(candidatesVoted: CandidatesVoted) {

        Toast.makeText(requireContext(), candidatesVoted.message, Toast.LENGTH_LONG).show()
        candidates.forEach { candidateVoted ->
            candidateVoted.votes = 0
        }
        candidatesAdapter.setItems(candidates)
    }

    private fun sendingCandidatesVotes() {
        Toast.makeText(
            requireContext(),
            getString(R.string.sending_candidates_votes),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun renderCandidatesUI(screenState: ScreenState<CandidatesScreenState>) {
        when (screenState) {
            ScreenState.Loading -> showLoader()
            is ScreenState.Render -> showCandidates(screenState.data)
        }
    }

    private fun showCandidates(candidatesScreenState: CandidatesScreenState) {

        hideLoader()
        when (candidatesScreenState) {
            is CandidatesScreenState.Candidates -> {
                val itemVariables: MutableList<DataBindingVariables> = mutableListOf()

                val updateVotes = UpdateVotes(
                    candidatesAdapter,
                    candidatesScreenState.list.toMutableList()
                )
                itemVariables.add(
                    DataBindingVariables(
                        BR.updateVotes,
                        updateVotes
                    )
                )
                candidatesAdapter.setItemVariables(itemVariables)

                candidates.clear()
                candidates.addAll(candidatesScreenState.list.toMutableList())

                candidatesAdapter.setItems(
                    candidates
                )
               // Toast.makeText(context,"Se recupero un total de " + candidates.size.toString() +  " elementos", Toast.LENGTH_LONG).show()
            }
            is CandidatesScreenState.Error -> showError(candidatesScreenState.message)
        }
    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun showLoader() {
        pgrCandidates?.visibility = View.VISIBLE
    }

    private fun hideLoader() {
        pgrCandidates?.visibility = View.GONE
    }



} //--- end class
