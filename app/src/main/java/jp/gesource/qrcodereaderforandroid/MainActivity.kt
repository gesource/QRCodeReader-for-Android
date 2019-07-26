package jp.gesource.qrcodereaderforandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DefaultDecoderFactory
import com.journeyapps.barcodescanner.camera.CameraSettings
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var lastText: String = ""

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            if (result?.text == null || result.text == lastText) {
                return
            }
            lastText = result.text
            barcodeView.setStatusText(result.text)
        }
        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val formats = listOf(BarcodeFormat.QR_CODE)
        barcodeView.barcodeView.decoderFactory = DefaultDecoderFactory(formats)
        barcodeView.initializeFromIntent(intent)
        barcodeView.decodeContinuous(callback)
        barcodeView.barcodeView.cameraSettings = CameraSettings()
    }

    override fun onResume() {
        super.onResume()
        barcodeView.resume()
    }

    override fun onPause() {
        super.onPause()
        barcodeView.pause()
    }
}
