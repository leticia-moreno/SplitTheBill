package com.leticia.splitthebill.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.leticia.splitthebill.databinding.ActivityPersonBinding
import com.leticia.splitthebill.model.Constant.EXTRA_PERSON
import com.leticia.splitthebill.model.Constant.VIEW_PERSON
import com.leticia.splitthebill.model.Person
import kotlin.random.Random

class PersonActivity : AppCompatActivity() {
    private val apb: ActivityPersonBinding by lazy {
        ActivityPersonBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        val receivedPerson = intent.getParcelableExtra<Person>(EXTRA_PERSON)
        receivedPerson?.let{ _receivePerson ->
            with(apb) {
                with(_receivePerson) {
                    nameEt.setText(name)
                    spentEt.setText(spent)
                    debtEt.setText(debt)
                    descEt.setText(description)
                }
            }
        }
        val viewPerson = intent.getBooleanExtra(VIEW_PERSON, false)
        if (viewPerson) {
            apb.nameEt.isEnabled = false
            apb.spentEt.isEnabled = false
            apb.debtEt.isEnabled = false
            apb.descEt.isEnabled = false
            apb.saveBt.visibility = View.GONE
        }

        apb.saveBt.setOnClickListener {
            val person = Person(
                id = receivedPerson?.id?: Random(System.currentTimeMillis()).nextInt(),
                name = apb.nameEt.text.toString(),
                spent = apb.spentEt.text.toString(),
                debt = apb.debtEt.text.toString(),
                description = apb.descEt.text.toString(),
            )
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_PERSON, person)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}