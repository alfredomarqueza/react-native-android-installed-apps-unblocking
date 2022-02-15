
# react-native-android-installed-apps-unblocking

## Getting started

`$ npm install react-native-android-installed-apps-unblocking`

### Mostly automatic installation

`$ react-native link react-native-android-installed-apps-unblocking`

### Manual installation



#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.androidinstalledapps.RNAndroidInstalledAppsPackage;` to the imports at the top of the file
  - Add `new RNAndroidInstalledAppsPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-android-installed-apps-unblocking'
  	project(':react-native-android-installed-apps-unblocking').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-android-installed-apps/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-android-installed-apps-unblocking')
  	```


## Methods

#### 1 - getApps()
#### 2 - getNonSystemApps()
#### 3 - getSystemApps()
#### 4 - getNonSystemAppNames()

## Return Result 

- packageName
- versionName
- versionCode
- firstInstallTime
- lastUpdateTime
- appName
- icon // Base64
- apkDir
- size // Bytes


## Usage
```javascript
import RNAndroidInstalledApps from 'react-native-android-installed-apps-unblocking';


RNAndroidInstalledApps.getApps()
      .then(apps => {
        this.setState({apps})
      })
      .catch(error => {
        alert(error);
      });

```
