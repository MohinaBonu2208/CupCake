package order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cupcake.databinding.FragmentDateBinding

class DateFragment : Fragment() {
    private var _binding: FragmentDateBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentDateBinding.inflate(inflater, container, false)
       _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}