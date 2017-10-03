package smartquiz.splanet.com.permissionsnew;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ = 1123;
    private static final String TAG = "Contacts";
    private String[] permission = {};
    private Button Btn_Ok = null;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestMultiplePermission();
    }


    private void RequestMultiplePermission() {
        try {
            permission = getPermissions(MainActivity.this);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        // Creating String Array with Permissions.
        ActivityCompat.requestPermissions(MainActivity.this, permission, MY_PERMISSIONS_REQUEST_READ);

    }


    public static String[] getPermissions(Context context)
            throws PackageManager.NameNotFoundException {
        PackageInfo info = context.getPackageManager().getPackageInfo(
                context.getPackageName(), PackageManager.GET_PERMISSIONS);

        return info.requestedPermissions;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ) {
            if (grantResults.length > 0) {

                ArrayList<Integer> allGrant = new ArrayList<>();
                for (int i = 0; i < grantResults.length; i++) {
                    allGrant.add(grantResults[i]);
                }

                if (allGrant.contains(PackageManager.PERMISSION_GRANTED) && !allGrant.contains(PackageManager.PERMISSION_DENIED)) {

                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
