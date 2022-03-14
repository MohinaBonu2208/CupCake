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
import com.example.cupcake.databinding.FragmentPickupBinding

class PickupFragment : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentPickupBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
        _binding = fragmentBinding

        setupDate()

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding.dateOptions.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.option0 -> viewModel.setDate(viewModel.dateOptions[0])
                R.id.option1 -> viewModel.setDate(viewModel.dateOptions[1])
                R.id.option2 -> viewModel.setDate(viewModel.dateOptions[2])
                R.id.option3 -> viewModel.setDate(viewModel.dateOptions[3])
            }
        }
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
        }

        binding.cancelButton.setOnClickListener { cancelOrder() }

        viewModel.price.observe(viewLifecycleOwner) { newPrice ->
            binding.subtotal.text = getString(R.string.subtotal, newPrice.toString())
        }
    }

    private fun setupDate() {
        binding.option0.text = viewModel.dateOptions[0]
        binding.option1.text = viewModel.dateOptions[1]
        binding.option2.text = viewModel.dateOptions[2]
        binding.option3.text = viewModel.dateOptions[3]
    }

    fun cancelOrder() {
        viewModel.resetOrder()
        findNavController().navigate(R.id.action_pickupFragment_to_startFragment)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}