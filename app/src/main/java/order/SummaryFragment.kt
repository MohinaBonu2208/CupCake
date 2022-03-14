package order

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.OrderViewModel
import com.example.cupcake.R
import com.example.cupcake.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment() {
    private val viewModel: OrderViewModel by activityViewModels()

    private var _binding: FragmentSummaryBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        _binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.quantity1.text = viewModel.quantity.value.toString()
        binding.flavour2.text = viewModel.flavour.value.toString()
        binding.date2.text = viewModel.date.value.toString()
        binding.subtotal.text = getString(R.string.subtotal, viewModel.price.value.toString())

        binding.send.setOnClickListener { sendOrder() }
        binding.cancelButton.setOnClickListener { cancelOrder() }
    }

    private fun cancelOrder() {
        viewModel.resetOrder()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }


    private fun sendOrder() {
        val numCakes = viewModel.quantity.value ?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numCakes, numCakes),
            viewModel.flavour.value.toString(),
            viewModel.date.value.toString(),
            viewModel.price.value.toString()
        )

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT, orderSummary)

        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}