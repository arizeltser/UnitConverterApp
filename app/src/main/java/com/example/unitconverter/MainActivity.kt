package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt
import androidx.compose.ui.text.TextStyle as TextStyle1
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val iConversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }

    val customTextStyle = TextStyle1(
        fontFamily = FontFamily.SansSerif,
        fontSize = 32.sp,
        color = Color.Black

    )

    fun convertUnits() {
        // if null return 0.0
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * iConversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // UI elements stacked below each other
        // Title text
        Text("Unit Converter",style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        // text box
        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()
                            },
            label = { Text(text = "Enter Value")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // UI elements stacked next to each other
            // Input
            Box {
                Button(onClick = {iExpanded = true}) {
                    Text(text = inputUnit)

                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = {iExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        inputUnit = "Centimeters"
                        iExpanded = false
                        iConversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        inputUnit = "Meters"
                        iExpanded = false
                        iConversionFactor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        inputUnit = "Feet"
                        iExpanded = false
                        iConversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        inputUnit = "Millimeters"
                        iExpanded = false
                        iConversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            // Output
            Box {
                Button(onClick = {oExpanded = true}) {
                    Text(text = outputUnit)

                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = {oExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        outputUnit = "Centimeters"
                        oExpanded = false
                        oConversionFactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        outputUnit = "Meters"
                        oExpanded = false
                        oConversionFactor.value = 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        outputUnit = "Feet"
                        oExpanded = false
                        oConversionFactor.value = 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = {
                        outputUnit = "Millimeters"
                        oExpanded = false
                        oConversionFactor.value = 0.001
                        convertUnits()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Result text
        Text(text = "Result: $outputValue $outputUnit", style = MaterialTheme.typography.headlineMedium)
    }
}




@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}