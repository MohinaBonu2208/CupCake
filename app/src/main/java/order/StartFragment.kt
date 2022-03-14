package order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.OrderViewModel
import com.example.cupcake.R
import com.example.cupcake.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()

   private var _binding: FragmentStartBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            button.setOnClickListener { orderCupcake(1) }
            button2.setOnClickListener { orderCupcake(6) }
            button3.setOnClickListener { orderCupcake(12) }
        }
    }

    private fun orderCupcake(quantity: Int) {
        viewModel.setQuantity(quantity)
       findNavController().navigate(R.id.action_startFragment_to_flavourFragment)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}