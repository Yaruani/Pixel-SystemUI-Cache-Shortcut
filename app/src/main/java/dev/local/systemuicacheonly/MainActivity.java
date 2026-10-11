package dev.local.systemuicacheonly;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

/**
 * One-tap shortcut to System UI's storage screen.
 * No accessibility services, automatic cache clearing, or background work.
 */
public final class MainActivity extends Activity {
    private static final String TARGET_PACKAGE = "com.android.systemui";
    private static final String SETTINGS_PACKAGE = "com.android.settings";
    private static final String APP_STORAGE_ACTION = "com.android.settings.APP_STORAGE_SETTINGS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Uri target = Uri.fromParts("package", TARGET_PACKAGE, null);
        Intent storage = new Intent(APP_STORAGE_ACTION);
        storage.setData(target);
        storage.setPackage(SETTINGS_PACKAGE);

        try {
            startActivity(storage);
        } catch (ActivityNotFoundException | SecurityException e) {
            // If a future Android version removes the direct page, open
            // the System UI app-info page instead, without UI automation.
            Intent appInfo = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            appInfo.setData(target);
            appInfo.setPackage(SETTINGS_PACKAGE);
            try {
                startActivity(appInfo);
                Toast.makeText(this,
                        getString(R.string.fallback_notice),
                        Toast.LENGTH_LONG).show();
            } catch (ActivityNotFoundException | SecurityException fallbackError) {
                Toast.makeText(this,
                        getString(R.string.open_failed),
                        Toast.LENGTH_LONG).show();
            }
        } finally {
            finish();
        }
    }
}
