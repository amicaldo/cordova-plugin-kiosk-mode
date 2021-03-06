const exec = require('cordova/exec');


class KioskMode {
  /**
   * @return {string} command
   * @return {Array<any>} [params]
   *
   * @return {Promise<any>}
   */
  static _exec(command, params = []) {
    return new Promise((resolve, reject) => {
      exec(resolve, reject, 'KioskModePlugin', command, params);
    });
  }

  /**
   * @return {Promise<any>}
   */
  static enable() {
    return KioskMode._exec('enable');
  }

  /**
   * @return {Promise<any>}
   */
  static disable() {
    return KioskMode._exec('disable');
  }

  /**
   * @return {Promise<boolean>}
   */
  static isEnabled() {
    return KioskMode._exec('isEnabled')
      .then((out) => out == 'true')
      .catch((err) => false);
  }

  /**
   * @return {Promise<boolean>}
   */
  static isLauncher() {
    return KioskMode._exec('isLauncher')
      .then((out) => out == 'true')
      .catch((err) => false);
  }

  /**
   * @return {Promise<any>}
   */
  static switchLauncher() {
    return KioskMode._exec('switchLauncher');
  }

  /**
   * @return {Promise<any>}
   */
  static enableAdb() {
    return KioskMode._exec('enableAdb');
  }

  /**
   * @return {Promise<any>}
   */
  static disableAdb() {
    return KioskMode._exec('disableAdb');
  }

  /**
   * @return {Promise<boolean>}
   */
  static isAdbEnabled() {
    return KioskMode._exec('isAdbEnabled')
      .then((out) => out == 'true')
      .catch((err) => false);
  }
}


module.exports = KioskMode;
