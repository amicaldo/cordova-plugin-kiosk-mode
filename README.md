# Cordova Plugin Kiosk Mode

## Installation

```bash
$ cordova plugin add https://github.com/amicaldo/cordova-plugin-kiosk-mode
$ npm i https://github.com/amicaldo/cordova-plugin-kiosk-mode
```

### Permissions

In order to properly use this plugin, your give your app the `android.permission.WRITE_SECURE_SETTINGS` permission:

```bash
$ adb shell pm grant <package> android.permission.WRITE_SECURE_SETTINGS
```

This is needed to update the `Settings.Global.DEVICE_PROVISIONED` setting.



Another approach is to install your application with the `-g` flag:

```bash
$ adb install -g <apk>
```

This will grant your application all permissions listed in the app manifest.

### Enable Kiosk Mode

* Manually set your application as launcher in your android settings*

* Run `KioskMode.enable()` in your application

### Disable Kiosk Mode

* Run `KioskMode.disable()` in your application
* Run `KioskMode.switchLauncher()` and select your default launcher*

\* Only if you set up your application as launcher.

## Usage

```typescript
/**
 * @return {Promise<any>}
 */
KioskMode.enable();

/**
 * @return {Promise<any>}
 */
KioskMode.disable();

/**
 * @return {Promise<boolean>}
 */
KioskMode.isEnabled();

/**
 * @return {Promise<boolean>}
 */
KioskMode.isLauncher();

/**
 * @return {Promise<any>}
 */
KioskMode.switchLauncher();
```

## Notes

Use this plugin with caution. Users may not get out of the Kiosk Mode by theirselves.

Installing apps with this plugin may automatically trigger – depending on the android version and device – a chooser to select a launcher.

## Author

Jannik Hauptvogel <hauptvogel@amicaldo.de>

## Contact

Support <support@amicaldo.de>
