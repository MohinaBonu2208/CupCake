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
import com.example.cupcake.databinding.FragmentFlavourBinding


class FlavourFragment : Fragment() {

    private val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentFlavourBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentFlavourBinding.inflate(inflater, container, false)
        _binding = fragmentBinding

        return fragmentBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.flavourOptions.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.vanilla -> viewModel.setFlavour(getString(R.string.vanilla))
                R.id.chocolate -> viewModel.setFlavour(getString(R.string.chocolate))
                R.id.coffee -> viewModel.setFlavour(getString(R.string.coffee))
                R.id.redVelvet -> viewModel.setFlavour(getString(R.string.red_velvet))
            }
        }

        binding.subtotal.text = getString(R.string.subtotal, viewModel.price.value.toString())

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_flavourFragment_to_pickupFragment)
        }

        binding.cancelButton.setOnClickListener { cancelOrder() }

    }

    fun cancelOrder() {
        viewModel.resetOrder()
        findNavController().navigate(R.id.action_flavourFragment_to_startFragment)
    }

}