package com.projetosrafaelfemina.viacep

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.projetosrafaelfemina.viacep.databinding.ActivityMainBinding
import com.projetosrafaelfemina.viacep.model.Address
import com.projetosrafaelfemina.viacep.repository.CepRepository
import com.projetosrafaelfemina.viacep.ui.ActivityUtils

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ActivityUtils.initActionBar(this)

        Log.i(TAG, "Recurso carregado com sucesso!, OnCreate!")
        binding.btBuscarCep.setOnClickListener {
            val cep = binding.editCep.text.toString()
            searchCep(cep)
        }
    }

    private fun searchCep(cep: String) {
        if (cep.isNotEmpty()) {
            val repository = CepRepository()
            repository.getAddress(cep, object : (Result<Address>) -> Unit {
                override fun invoke(address: Result<Address>) {
                    try {
                        updateForm(address.getOrThrow())
                    } catch (e: Throwable) {
                        Log.e(TAG, e.message!!)
                        showToast("CEP Incorreto!")
                    }
                }
            })
        } else {
            showToast("Preencha o cep!")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateForm(address: Address) {
        binding.editLogradouro.setText(address.logradouro)
        binding.editBairro.setText(address.bairro)
        binding.editCidade.setText(address.localidade)
        binding.editEstado.setText(address.uf)
    }

}