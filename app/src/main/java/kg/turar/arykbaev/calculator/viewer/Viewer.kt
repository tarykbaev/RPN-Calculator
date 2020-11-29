package kg.turar.arykbaev.calculator.viewer

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kg.turar.arykbaev.calculator.R
import kg.turar.arykbaev.calculator.controller.Controller

class Viewer() : AppCompatActivity() {

    private val controller: Controller = Controller(this)

    private lateinit var textViewExpression: TextView
    private lateinit var textViewAnswer: TextView

    private lateinit var buttonOpenParenthesis: Button
    private lateinit var buttonCloseParenthesis: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonEqual: Button
    private lateinit var buttonDot: Button
    private lateinit var buttonDivision: Button
    private lateinit var buttonMultiplication: Button
    private lateinit var buttonMinus: Button
    private lateinit var buttonAdd: Button
    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button
    private lateinit var buttonFour: Button
    private lateinit var buttonFive: Button
    private lateinit var buttonSix: Button
    private lateinit var buttonSeven: Button
    private lateinit var buttonEight: Button
    private lateinit var buttonNine: Button
    private lateinit var buttonZero: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTextView()
        initButtons()
        setOnClickListener()
    }

    private fun initTextView() {
        textViewExpression = findViewById(R.id.text_view_expression)
        textViewAnswer = findViewById(R.id.text_view_expression_answer)
    }

    private fun initButtons() {
        buttonOpenParenthesis = findViewById(R.id.button_open_parenthesis)
        buttonCloseParenthesis = findViewById(R.id.button_close_parenthesis)
        buttonClear = findViewById(R.id.button_clear)
        buttonEqual = findViewById(R.id.button_equal)
        buttonDot = findViewById(R.id.button_dot)

        buttonDivision = findViewById(R.id.button_division)
        buttonMultiplication = findViewById(R.id.button_multiplication)
        buttonMinus = findViewById(R.id.button_minus)
        buttonAdd = findViewById(R.id.button_add)

        buttonOne = findViewById(R.id.button_one)
        buttonTwo = findViewById(R.id.button_two)
        buttonThree = findViewById(R.id.button_three)
        buttonFour = findViewById(R.id.button_four)
        buttonFive = findViewById(R.id.button_five)
        buttonSix = findViewById(R.id.button_six)
        buttonSeven = findViewById(R.id.button_seven)
        buttonEight = findViewById(R.id.button_eight)
        buttonNine = findViewById(R.id.button_nine)
        buttonZero = findViewById(R.id.button_zero)
    }

    private fun setOnClickListener() {
        buttonOpenParenthesis.setOnClickListener(controller)
        buttonCloseParenthesis.setOnClickListener(controller)
        buttonClear.setOnClickListener(controller)
        buttonEqual.setOnClickListener(controller)
        buttonDot.setOnClickListener(controller)

        buttonDivision.setOnClickListener(controller)
        buttonMultiplication.setOnClickListener(controller)
        buttonMinus.setOnClickListener(controller)
        buttonAdd.setOnClickListener(controller)

        buttonOne.setOnClickListener(controller)
        buttonTwo.setOnClickListener(controller)
        buttonThree.setOnClickListener(controller)
        buttonFour.setOnClickListener(controller)
        buttonFive.setOnClickListener(controller)
        buttonSix.setOnClickListener(controller)
        buttonSeven.setOnClickListener(controller)
        buttonEight.setOnClickListener(controller)
        buttonNine.setOnClickListener(controller)
        buttonZero.setOnClickListener(controller)
    }

    fun getExpression(): String {
        return textViewExpression.text.toString()
    }

    fun writeExpression(value: String) {
        textViewExpression.text = value
    }

    fun getAnswer(): String {
        return textViewAnswer.text.toString()
    }

    fun writeAnswer(answer: String) {
        textViewAnswer.text = answer
    }
}