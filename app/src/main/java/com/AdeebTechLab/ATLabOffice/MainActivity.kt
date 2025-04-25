package com.AdeebTechLab.ATLabOffice


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var bluetoothHelper: BluetoothHelper
    private lateinit var statusTextView: TextView
    private lateinit var lightOn: Button
    private lateinit var lightOff: Button
    private lateinit var fanOn: Button
    private lateinit var fanOff: Button
    private lateinit var hardwareOn: Button
    private lateinit var hardwareOff: Button
    private lateinit var studioOn: Button
    private lateinit var studioOff: Button
    private lateinit var classRoomOn: Button
    private lateinit var classRoomOff: Button
    private lateinit var ledOn: Button
    private lateinit var ledOff: Button
    private lateinit var printerOn: Button
    private lateinit var printerOff: Button
    private lateinit var ledNameOn: Button
    private lateinit var ledNameOff: Button

    private lateinit var allOn: Button
    private lateinit var allOff: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Insets handling for Edge-to-Edge layouts
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI components
        val timeTextView: TextView = findViewById(R.id.timeTextView)
        val dateTextView: TextView = findViewById(R.id.dateTextView)
        val temperatureTextView: TextView = findViewById(R.id.temperatureTextView)

        // Set Current Time
        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
        timeTextView.text = "||   $currentTime"

        // Set Current Date
        val currentDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(Date())
        dateTextView.text = "   ||   $currentDate"

        // Dummy Temperature (replace with actual API integration)
        val temperature = "25°C"
        temperatureTextView.text = "   ||   $temperature   ||"

        bluetoothHelper = BluetoothHelper()

        val connectBtn: Button = findViewById(R.id.connect_bt)
        val exitBtn: Button = findViewById(R.id.exe_bt)
        statusTextView = findViewById(R.id.Disconnected)

        lightOn = findViewById(R.id.light_on)
        lightOff = findViewById(R.id.light_off)
        fanOn = findViewById(R.id.fan_on)
        fanOff = findViewById(R.id.fan_off)
        hardwareOn = findViewById(R.id.hardwear_on)
        hardwareOff = findViewById(R.id.hardwear_off)
        studioOn = findViewById(R.id.studio_on)
        studioOff = findViewById(R.id.studio_off)
        classRoomOn = findViewById(R.id.classRoom_on)
        classRoomOff = findViewById(R.id.classRoom_off)
        ledOn = findViewById(R.id.led_on)
        ledOff = findViewById(R.id.led_off)
        printerOn = findViewById(R.id.printer_on)
        printerOff = findViewById(R.id.printer_off)
        ledNameOn = findViewById(R.id.ledName_on)
        ledNameOff = findViewById(R.id.ledName_off)
        allOn = findViewById(R.id.all_on)
        allOff = findViewById(R.id.all_off)

        // Bluetooth Connection Status
        connectBtn.setOnClickListener {
            if (bluetoothHelper.connect()) {
                statusTextView.text = "Connected"
                statusTextView.setTextColor(Color.GREEN)
                Toast.makeText(this, "Connected to HC-05", Toast.LENGTH_SHORT).show()
            } else {
                statusTextView.text = "Disconnected"
                statusTextView.setTextColor(Color.RED)
                Toast.makeText(this, "Connection Failed!", Toast.LENGTH_SHORT).show()
            }
        }

        exitBtn.setOnClickListener { finishAffinity() }

        // Toggle Relay Buttons
        setButtonToggle(lightOn, lightOff, "a", "1")
        setButtonToggle(fanOn, fanOff, "b", "2")
        setButtonToggle(hardwareOn, hardwareOff, "c", "3")
        setButtonToggle(studioOn, studioOff, "d", "4")
        setButtonToggle(classRoomOn, classRoomOff, "e", "5")
        setButtonToggle(ledOn, ledOff, "f", "6")
        setButtonToggle(printerOn, printerOff, "g", "7")
        setButtonToggle(ledNameOn, ledNameOff, "h", "8")
        setButtonToggle(allOn, allOff, "i", "9")
    }

    private fun setButtonToggle(buttonOn: Button, buttonOff: Button, commandOn: String, commandOff: String) {
        buttonOn.setOnClickListener {
            bluetoothHelper.sendCommand(commandOn)
            buttonOn.visibility = View.GONE
            buttonOff.visibility = View.VISIBLE
        }

        buttonOff.setOnClickListener {
            bluetoothHelper.sendCommand(commandOff)
            buttonOff.visibility = View.GONE
            buttonOn.visibility = View.VISIBLE
        }
    }
}