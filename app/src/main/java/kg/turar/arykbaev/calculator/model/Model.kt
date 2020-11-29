package kg.turar.arykbaev.calculator.model

import kg.turar.arykbaev.calculator.viewer.Viewer
import java.util.*
import kotlin.collections.ArrayList

class Model {

    private val viewer: Viewer
    private var expressionString: String
    private var expressionValue: String

    constructor(viewer: Viewer) {
        this.viewer = viewer
        expressionValue = ""
        expressionString = ""
    }

    private fun writeExpression(value: String) {
        viewer.writeExpression(value)
    }

    private fun writeAnswer(result: String) {
        viewer.writeAnswer(result)
    }

    private fun getExpression(): String {
        return viewer.getExpression()
    }

    private fun getAnswer(): String {
        return viewer.getAnswer()
    }

    fun clearTextView() {
        expressionString = ""
        writeAnswer("")
        writeExpression("0")
    }

    fun solveExpression() {
        writeAnswer(expressionString.getResult())
        sendToServer()
    }

    private fun sendToServer() {
        ConnectToServer("${getExpression()} = ${getAnswer()}").go()
    }

    fun appendSymbol(value: String) {
        expressionValue = getExpression()
        if (expressionValue == "0" && value != ".") {
            expressionValue = ""
        }
        if (lastIsNotOperator(value, expressionValue)) {
            expressionString = expressionValue + value
        } else {
            expressionValue = expressionValue.substring(0, expressionValue.length - 1)
            expressionString = expressionValue + value
        }
        writeExpression(expressionString)
    }

    private fun lastIsNotOperator(value: String, expression: String): Boolean {
        if (expression.isNotEmpty()) {
            if (value in "+-÷×." && expression.last() in "+-÷×.") return false
        }
        return true
    }


    // RNP

    private fun String.getResult(): String {
        return try {
            val expressionList = this.convertToList()
            val expressionRPN = expressionList.convertToRNP()

            expressionRPN.calculate()
        } catch (e: EmptyStackException) {
            "Error"
        }
    }

    private fun List<String>.convertToRNP(): List<String> {
        val result = ArrayList<String>()
        val stack = Stack<String>()

        for (token in this) {
            when {
                token.isNumber() -> result.add(token)
                token == "(" -> stack.push(token)
                token == ")" -> {
                    while (stack.peek() != "(") {
                        result.add(stack.pop())
                    }
                    stack.pop()
                }
                else -> {
                    while (!stack.isEmpty() && stack.peek().getPriority() >= token.getPriority()) {
                        result.add(stack.pop())
                    }
                    stack.push(token)
                }
            }
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop())
        }

        return result
    }

    private fun String.convertToList(): List<String> {
        val stringNumber = StringBuilder()
        val list = ArrayList<String>()

        for (c in this) {
            if (c.isDigit() || c == '.') {
                stringNumber.append(c)
            } else {
                if (stringNumber.isNotEmpty()) {
                    list.add(stringNumber.toString())
                    stringNumber.clear()
                }
                list.add(c.toString())
            }
        }

        if (stringNumber.isNotEmpty()) {
            list.add(stringNumber.toString())
        }

        return list
    }

    private fun List<String>.calculate(): String {
        val stack = Stack<Double>()

        for (i in this) {
            if (i.isNumber()) {
                stack.add(i.toDouble())
            } else {
                val a = stack.pop()
                val b = stack.pop()

                when (i) {
                    "+" -> stack.add(a + b)
                    "-" -> stack.add(b - a)
                    "×" -> stack.add(a * b)
                    "÷" -> stack.add(b / a)
                }
            }
        }

        return if (stack.isNotEmpty()) {
            stack.pop().toString()
        } else {
            "Error"
        }
    }

    private fun String.isNumber(): Boolean {
        return this[0].isDigit()
    }

    private fun String.getPriority(): Int {
        return if (this == "(") {
            0
        } else if (this == "+" || this == "-") {
            1
        } else 2
    }
}

