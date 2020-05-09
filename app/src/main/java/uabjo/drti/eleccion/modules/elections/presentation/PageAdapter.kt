package uabjo.drti.eleccion.modules.elections.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerAdapter(fragmentActivity: FragmentActivity,
                       private val fragments : List<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}

/*
class PageAdapter(private val context: Context, fm: FragmentManager,
                  private val fragments: List<Fragment>):
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
{
    companion object {
        private val NUM_ITEMS=2
        private val TAB_TITLES = arrayOf(
            R.string.tab_title_1,
            R.string.tab_title_2
        )
        private val TAB_ICONS = arrayOf(
            android.R.drawable.ic_menu_edit,
            android.R.drawable.ic_menu_camera
        )
    }

    override fun getItem(position: Int): Fragment{
            return fragments[position]
    }//--- End getItem

    override fun getCount(): Int {
        return NUM_ITEMS
    }
    override fun getPageTitle(position: Int):
            CharSequence? {
        val image = ContextCompat.getDrawable(
            context,
            TAB_ICONS[position]
        )

        image!!.setBounds(
            0, 0, image.intrinsicWidth,
            image.intrinsicHeight
        )
        val sb = SpannableString(
            " " +
                    context.resources
                        .getString(TAB_TITLES[position])
        )
        val imageSpan = ImageSpan(image)
        sb.setSpan(
            imageSpan, 0, 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return sb
    }//--- End  getPageTitle


}//--- End class
*/