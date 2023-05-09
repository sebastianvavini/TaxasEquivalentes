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

        binding.radioAoAno.setOnClickListener(this)
        binding.radioAoMes.setOnClickListener(this)
        binding.radioAoDia.setOnClickListener(this)

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
    private fun convertMensalToAnual(aoMes: Double):Double{

      var anual= Math.pow( (1+aoMes),12.00) - 1
      return anual

    }
    private fun convertDiariaToMensal(aoDia:Double):Double{
        var mensal= Math.pow((1+aoDia),30.00) - 1
        return mensal
    }
    private fun converte(){
        var taxaAoAno:Double
        var taxaAoMes:Double
        var taxaAoDia:Double

        if(binding.radioAoAno.isChecked){

             taxaAoAno= binding.editTextTaxaDeJurosAoAno.text.toString().toDouble()
             taxaAoMes=convertAnualToMensal(taxaAoAno)
             taxaAoDia= convertMensalToDiaria(convertAnualToMensal(taxaAoAno))

            print(taxaAoAno.toString(),taxaAoMes.toString(),taxaAoDia.toString())
        }
        if(binding.radioAoMes.isChecked){

             taxaAoMes = binding.editTextTaxaDeJurosAoMes.text.toString().toDouble()
             taxaAoAno = convertMensalToAnual(taxaAoMes)
             taxaAoDia= convertMensalToDiaria(taxaAoMes)

            print(taxaAoAno.toString(),taxaAoMes.toString(),taxaAoDia.toString())


        }
        if (binding.radioAoDia.isChecked){

            taxaAoDia= binding.editTextTaxaDeJurosAoDia.text.toString().toDouble()
            taxaAoMes = convertDiariaToMensal(taxaAoDia)
            taxaAoAno = convertMensalToAnual(taxaAoMes)

            print(taxaAoAno.toString(),taxaAoMes.toString(),taxaAoDia.toString())

        }




    }
     private fun print(aoAno:String,aoMes:String,aoDia:String){
         binding.textViewResultadoEquivalenteAoAno.text=aoAno
         binding.textViewResultadoEquivalenteAoDia.text=aoDia
         binding.textViewResultadoEquivalenteAoMes.text= aoMes
     }

    override fun onClick(v: View) {

        if(v.id==R.id.radio_aoAno){
            binding.editTextTaxaDeJurosAoDia.isEnabled=false
            binding.editTextTaxaDeJurosAoMes.isEnabled=false
            binding.editTextTaxaDeJurosAoAno.isEnabled=true
        }
        if (v.id==R.id.radio_aoMes){
            binding.editTextTaxaDeJurosAoDia.isEnabled=false
            binding.editTextTaxaDeJurosAoAno.isEnabled=false
            binding.editTextTaxaDeJurosAoMes.isEnabled=true
        }
        if(v.id==R.id.radio_aoDia){
            binding.editTextTaxaDeJurosAoAno.isEnabled=false
            binding.editTextTaxaDeJurosAoMes.isEnabled=false
            binding.editTextTaxaDeJurosAoDia.isEnabled=true
        }

        if(v.id==R.id.button_calculate) {
            if (isValid()) {
                converte()

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