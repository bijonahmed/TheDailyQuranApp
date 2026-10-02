package com.example.util

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CompassSensor(context: Context) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager
    private val rotationSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
    private val accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private val magnetometer = sensorManager?.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

    private val _azimuth = MutableStateFlow(0f)
    val azimuth: StateFlow<Float> = _azimuth.asStateFlow()

    private val _hasSensor = MutableStateFlow(rotationSensor != null || (accelerometer != null && magnetometer != null))
    val hasSensor: StateFlow<Boolean> = _hasSensor.asStateFlow()

    private var gravity = FloatArray(3)
    private var geomagnetic = FloatArray(3)

    fun start() {
        if (sensorManager == null) return
        if (rotationSensor != null) {
            sensorManager.registerListener(this, rotationSensor, SensorManager.SENSOR_DELAY_UI)
        } else {
            accelerometer?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
            magnetometer?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI) }
        }
    }

    fun stop() {
        sensorManager?.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {
            val rotationMatrix = FloatArray(9)
            SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)
            val orientation = FloatArray(3)
            SensorManager.getOrientation(rotationMatrix, orientation)
            var azimuthDeg = Math.toDegrees(orientation[0].toDouble()).toFloat()
            azimuthDeg = (azimuthDeg + 360) % 360
            _azimuth.value = azimuthDeg
        } else {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                gravity = event.values.clone()
            }
            if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                geomagnetic = event.values.clone()
            }
            if (gravity.isNotEmpty() && geomagnetic.isNotEmpty()) {
                val r = FloatArray(9)
                val i = FloatArray(9)
                if (SensorManager.getRotationMatrix(r, i, gravity, geomagnetic)) {
                    val orientation = FloatArray(3)
                    SensorManager.getOrientation(r, orientation)
                    var azimuthDeg = Math.toDegrees(orientation[0].toDouble()).toFloat()
                    azimuthDeg = (azimuthDeg + 360) % 360
                    _azimuth.value = azimuthDeg
                }
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
