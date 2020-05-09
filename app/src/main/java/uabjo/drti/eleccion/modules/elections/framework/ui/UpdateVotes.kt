package uabjo.drti.eleccion.modules.elections.framework.ui

import android.text.Editable
import android.widget.EditText
import androidx.databinding.BindingAdapter
import uabjo.drti.eleccion.modules.common.framework.GenericDataBindingAdapter
import uabjo.drti.eleccion.modules.elections.domain.model.Candidate
import java.lang.Exception


class UpdateVotes(val adapter: GenericDataBindingAdapter<Candidate>,
                  val data : MutableList<Candidate>) {
    fun updateVotes(candidate : Candidate, votes : Editable?) {
        try {
            candidate.votes = ("0"+ votes).toInt()
            val candidatePosition = data.indexOf(candidate)
            adapter.updateItemWithoutNotify(candidate, candidatePosition)
        } catch (e : Exception){
            e.printStackTrace()
        }
    }
}//--- End update votes

@BindingAdapter("setSelection")
fun setSelection(editText: EditText, candidate: Candidate) {
    editText.setText(candidate.votes.toString())
    editText.setSelection(0, editText.text.length)
}
