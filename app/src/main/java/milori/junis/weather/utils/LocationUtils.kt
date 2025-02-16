package milori.junis.weather.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener

lateinit var fusedLocationProviderClient: FusedLocationProviderClient

fun hasPermissions(context: Context, permissions: Array<String>): Boolean {
    return permissions.find {
        ContextCompat.checkSelfPermission(
            context,
            it
        ) == PackageManager.PERMISSION_DENIED
    } == null
}

data class LatAndLong(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

@Composable
fun RequestLocationPermission(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = LocalContext.current
    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    val requestPermissionsLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissions ->
        val isGranted = !permissions.containsValue(false)

        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    LaunchedEffect(key1 = requestPermissionsLauncher) {
        if (!hasPermissions(context, locationPermissions)) {
            requestPermissionsLauncher.launch(locationPermissions)
        } else {
            onPermissionGranted()
        }
    }
}

@SuppressLint("MissingPermission")
fun getLastUserLocation(
    context: Context,
    onGetLocationSuccess: (Pair<Double, Double>) -> Unit,
    onGetLocationFailed: (Exception) -> Unit
) {
    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    if (areLocationPermissionsGranted(context)) {
        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
            override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token

            override fun isCancellationRequested() = false
        }).addOnSuccessListener { location ->
                if (location != null) {
                    onGetLocationSuccess(Pair(location.latitude, location.longitude))
                } else {
                    onGetLocationFailed(Exception("It's null"))
                }
            }
            .addOnFailureListener { exception ->
                // If an error occurs, invoke the failure callback with the exception
                onGetLocationFailed(exception)
            }
    }
}

fun areLocationPermissionsGranted(context: Context): Boolean {
    return (ActivityCompat.checkSelfPermission(
        context, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
}