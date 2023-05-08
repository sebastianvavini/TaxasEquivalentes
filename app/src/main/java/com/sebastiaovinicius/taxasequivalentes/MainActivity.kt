package com.sebastiaovinicius.taxasequivalentes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.sebastiaovinicius.taxasequivalentes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),  View.OnClickListener  {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalculate.setOnClickListener(this)

    }

    private fun convertAnualToMensal( aoAno: Double): Double {

        // mensal= (1+anual)^¹/¹² -1

        var mensal= Math.pow(  (1+aoAno), 1/12.toDouble()   ) - 1

        return mensal

    }

    private fun convertMensalToDiaria( aoMes: Double): Double {



        var diaria= Math.pow(  (1+aoMes), 1/30.toDouble()   ) - 1

        return diaria

    }

    override fun onClick(v: View) {
        if(v.id==R.id.button_calculate) {
            if (isValid()) {

                var taxaAoAno= binding.editTextTaxaDeJurosAoAno.text.toString().toDouble()
                var taxaAoMes=convertAnualToMensal(taxaAoAno)
                var taxaAoDia= convertMensalToDiaria(convertAnualToMensal(taxaAoAno))

                //var taxaAoDia= convertMensalToDiaria(0.06)

                binding.textViewResultadoEquivalenteAoAno.text=binding.editTextTaxaDeJurosAoAno.text.toString()
                binding.textViewResultadoEquivalenteAoMes.text=taxaAoMes.toString()
                binding.textViewResultadoEquivalenteAoDia.text=taxaAoDia.toString()

            } else {
                //R.string.fill_all_fields
                Toast.makeText(this, "algo deu errado", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValid(): Boolean {

        return binding.editTextTaxaDeJurosAoAno.text.toString()!=""  ||
                binding.editTextTaxaDeJurosAoMes.text.toString()!="" ||
                binding.editTextTaxaDeJurosAoDia.text.toString()!=""


    }
}