package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.gallery

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos.arregloNC
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos.arregloNombre
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos.arregloSem
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentGalleryBinding
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    var arregloDatos = Array(10,{i->""})
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(ActualizarViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.guardar.setOnClickListener {
            try {
                val archivo = InputStreamReader(requireActivity().openFileInput("archivo.txt"))
            } catch (e:Exception){
                val archivo = OutputStreamWriter(requireActivity().openFileOutput("archivo.txt", 0))
                archivo.flush()
                archivo.close()
            }
            try {
                val archivoLec = InputStreamReader(requireActivity().openFileInput("archivo.txt"))
                val lista = archivoLec.readLines()
                if (lista.size == 10){
                    AlertDialog.Builder(requireContext())
                        .setMessage("NO SE PUEDEN GUARDAR MAS DATOS").show()
                    return@setOnClickListener
                }
                for (i in 0..lista.size-1){
                    val elementos = lista[i].split(",")
                    arregloNombre[i] = elementos[0]
                    arregloNC[i] = elementos[1]
                    arregloSem[i] = elementos[2]
                }
                var cadena = binding.nombre.text.toString()+","+binding.nc.text.toString()+","+binding.sem.text.toString()+",\n"
                val archivoEsc = OutputStreamWriter(requireActivity().openFileOutput("archivo.txt", 0))
                for (i in 0..lista.size-1){
                    var cad = arregloNombre[i]+","+arregloNC[i]+","+arregloSem[i]+",\n"
                    archivoEsc.write(cad)
                }
                archivoEsc.write(cadena)
                archivoEsc.flush()
                archivoEsc.close()
                binding.nombre.setText("")
                binding.nc.setText("")
                binding.sem.setText("")
                AlertDialog.Builder(requireContext())
                    .setMessage("SE GUARDO").show()
            } catch (e:Exception) {
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