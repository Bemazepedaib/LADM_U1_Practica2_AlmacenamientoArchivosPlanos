package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.slideshow

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos.arregloNombre
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos.arregloSem
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos.arregloNC
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentSlideshowBinding
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var indice = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.buscar.setOnClickListener {
            for (i in 0..arregloNC.size-1){
                if (arregloNC[i].equals(binding.nc.text.toString())){
                    binding.nombre.setText(arregloNombre[i])
                    binding.sem.setText(arregloSem[i])
                    indice = i
                    return@setOnClickListener
                }
            }
            AlertDialog.Builder(requireContext())
                .setMessage("NO SE ENCONTRÓ NINGÚN REGISTRO").show()
        }
        binding.actualizar.setOnClickListener {
            if (indice == -1) {
                AlertDialog.Builder(requireContext())
                    .setMessage("BUSQUE ALGÚN REGISTRO PRIMERO").show()
                return@setOnClickListener
            }
            arregloNC[indice] = binding.nc.text.toString()
            arregloNombre[indice] = binding.nombre.text.toString()
            arregloSem[indice] = binding.sem.text.toString()
            try {
                val archivoLec = InputStreamReader(requireActivity().openFileInput("archivo.txt"))
                val lista = archivoLec.readLines()
                val archivoEsc = OutputStreamWriter(requireActivity().openFileOutput("archivo.txt", 0))
                for (i in 0..lista.size - 1){
                    var cad = arregloNombre[i] + "," + arregloNC[i] + "," + arregloSem[i] + ",\n"
                    archivoEsc.write(cad)
                }
                archivoEsc.flush()
                archivoEsc.close()
                binding.nombre.setText("")
                binding.nc.setText("")
                binding.sem.setText("")
                AlertDialog.Builder(requireContext())
                    .setMessage("SE ACTUALIZÓ").show()
                indice = -1
            } catch (e: Exception) {
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message).show()
            }
        }
        binding.eliminar.setOnClickListener {
            if (indice == -1) {
                AlertDialog.Builder(requireContext())
                    .setMessage("BUSQUE ALGÚN REGISTRO PRIMERO").show()
                return@setOnClickListener
            }
            arregloNC[indice] = ""
            arregloNombre[indice] = ""
            arregloSem[indice] = ""
            try {
                val archivoLec = InputStreamReader(requireActivity().openFileInput("archivo.txt"))
                val lista = archivoLec.readLines()
                val archivoEsc = OutputStreamWriter(requireActivity().openFileOutput("archivo.txt", 0))
                for (i in 0..lista.size - 2) {
                    if (arregloNC[i] == "") {
                        if (i+1 > arregloNC.size){
                            continue
                        } else {
                            arregloNC[i] = arregloNC[i+1]
                            arregloNombre[i] = arregloNombre[i+1]
                            arregloSem[i] = arregloSem[i+1]
                            arregloNC[i+1] = ""
                            arregloNombre[i+1] = ""
                            arregloSem[i+1] = ""
                            var cad = arregloNombre[i] + "," + arregloNC[i] + "," + arregloSem[i] + ",\n"
                            archivoEsc.write(cad)
                        }
                    } else {
                        var cad = arregloNombre[i] + "," + arregloNC[i] + "," + arregloSem[i] + ",\n"
                        archivoEsc.write(cad)
                    }
                }
                archivoEsc.flush()
                archivoEsc.close()
                binding.nombre.setText("")
                binding.nc.setText("")
                binding.sem.setText("")
                AlertDialog.Builder(requireContext())
                    .setMessage("SE ELIMINÓ").show()
                indice = -1
            } catch (e: Exception) {
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message).show()
            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}