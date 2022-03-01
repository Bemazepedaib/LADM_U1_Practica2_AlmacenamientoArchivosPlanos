package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.actualizar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentActualizarBinding
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.gallery.ActualizarViewModel

class ActualizarFragment : Fragment() {

    private var _binding: FragmentActualizarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val actualizarViewModel =
            ViewModelProvider(this).get(ActualizarViewModel::class.java)

        _binding = FragmentActualizarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}