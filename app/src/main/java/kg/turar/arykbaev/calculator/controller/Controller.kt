package kg.turar.arykbaev.calculator.controller

import android.view.View
import android.widget.Button
import kg.turar.arykbaev.calculator.R
import kg.turar.arykbaev.calculator.model.Model
import kg.turar.arykbaev.calculator.viewer.Viewer

class Controller(viewer: Viewer) : View.OnClickListener {

    private val model: Model = Model(viewer)
    private var buttonText: String

    init {
        buttonText = ""
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_clear -> model.clearTextView()
            R.id.button_equal -> model.solveExpression()
            else -> {
                buttonText = (view as Button).text.toString() // get button's text
                model.appendSymbol(buttonText)
            }
        }
    }
}