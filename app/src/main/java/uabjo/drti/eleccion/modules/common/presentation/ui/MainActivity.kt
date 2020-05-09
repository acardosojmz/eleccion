package uabjo.drti.eleccion.modules.common.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.main_activity.*
import uabjo.drti.eleccion.R
import uabjo.drti.eleccion.modules.elections.framework.ui.CandidatesFragment
import uabjo.drti.eleccion.modules.elections.framework.ui.EvidencesFragment
import uabjo.drti.eleccion.modules.elections.presentation.ViewPagerAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        val fragments: MutableList<Fragment> = arrayListOf()
        fragments.add(CandidatesFragment())
        fragments.add(EvidencesFragment())

        view_pager.setAdapter(createCardAdapter(fragments))
        TabLayoutMediator(tabs, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text =  "Tab " + (position + 1)
            }).attach()
    }

    private fun createCardAdapter(fragments:List<Fragment>): ViewPagerAdapter {
        return ViewPagerAdapter(this,  fragments)
    }
}