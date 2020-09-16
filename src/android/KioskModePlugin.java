package de.amicaldo.cordova.plugin;

import android.content.Intent;
import org.apache.cordova.*;
import android.widget.*;
import org.json.JSONArray;
import de.amicaldo.cordova.plugin.KioskModeActivity;


public class KioskModePlugin extends CordovaPlugin {
    @Override
    public boolean execute(
        String action,
        JSONArray args,
        CallbackContext callbackContext
    ) {
        switch (action) {
            case "enable":
                this.setKioskModeEnabled(true);

                callbackContext.success();

                return true;

            case "disable":
                this.setKioskModeEnabled(false);

                callbackContext.success();

                return true;

            case "isEnabled":
                boolean isEnabled = this.isKioskModeEnabled();

                callbackContext.success(Boolean.toString(isEnabled));

                return true;

            case "isLauncher":
                boolean isLauncher = this.isKioskModeLauncher();

                callbackContext.success(Boolean.toString(isLauncher));

                return true;

            case "switchLauncher":
                this.switchLauncher();

                callbackContext.success();

                return true;
        }

        callbackContext.error("Action not found");

        return false;
    }

    /**
     * Custom methods
     */
    private void setKioskModeEnabled(boolean enabled) {
        KioskModeActivity instance = KioskModeActivity.getInstance();

        if (instance != null) {
            this.cordova.getActivity().runOnUiThread(() -> {
                instance.setKioskModeEnabled(enabled);
                instance.updateDeviceProvisioning();
                instance.updateSystemUiVisibility();
            });
        }
    }

    private boolean isKioskModeEnabled() {
        KioskModeActivity instance = KioskModeActivity.getInstance();

        return (instance != null && instance.getKioskModeEnabled());
    }

    private String getLauncherPackageName() {
        Intent intent = new Intent(Intent.ACTION_MAIN);

        intent.addCategory(Intent.CATEGORY_HOME);

        return this.cordova
            .getActivity()
            .getPackageManager()
            .resolveActivity(intent, 0)
            .activityInfo
            .packageName;
    }

    private boolean isKioskModeLauncher() {
        return this.cordova
            .getActivity()
            .getApplicationContext()
            .getPackageName()
            .equals(getLauncherPackageName());
    }

    private void switchLauncher() {
        KioskModeActivity instance = KioskModeActivity.getInstance();

        if (instance != null) {
            instance.setKioskModeEnabled(false);

            Intent intent = new Intent(Intent.ACTION_MAIN);

            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            Intent chooser = Intent.createChooser(intent, "Select destination...");

            if (intent.resolveActivity(cordova.getActivity().getPackageManager()) != null) {
                cordova.getActivity().startActivity(chooser);
            }
        }
    }
}
