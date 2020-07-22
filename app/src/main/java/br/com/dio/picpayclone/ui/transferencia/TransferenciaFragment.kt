package br.com.dio.picpayclone.ui.transferencia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.dio.picpayclone.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_transferencia.*

class TransferenciaFragment : Fragment() {

    private val argumentos by navArgs<TransferenciaFragmentArgs>()
    private val usuario by lazy { argumentos.usuario }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transferencia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navView = activity?.findViewById<BottomNavigationView>(R.id.nav_view)
        navView?.visibility = GONE
        textViewNome.text = usuario.login
        textViewNomeCompleto.text = usuario.nomeCompleto
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonCartaoCredito -> {
                    constraintLayoutCartaoCredito.visibility = VISIBLE
                }
                else -> {
                    constraintLayoutCartaoCredito.visibility = GONE
                }
            }
        }
    }
}