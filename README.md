# 🚀 Mini MAD Scripts — Android (Java) Practice Projects

Welcome! This repo is for learning Mobile Application Development (MAD) with Java.  
Inside you'll find focused Android examples and tiny apps that demonstrate core concepts.

Quick highlights:
- Clear, minimal Java + XML examples
- Small projects you can open and understand in minutes
- Great for learning Activity lifecycle, layouts, intents, forms, and basic UI.

---

## ✨ What this repo is for
This repository contains educational practice projects — intentionally simple so you can read, modify, and learn quickly. These are not production apps; they are my collage lab exercises and concept demos.

---
## ▶️ Quick run — try the sample APK (Android Mobile)

If you want to quickly see an app output without opening the project in Android Studio, you can download and install the sample APK provided here.

- Download the sample APK: [Download sample APK](https://github.com/Shankar-105/Mini-MAD-Scripts/blob/main/apk_download/app-debug.apk)

(Tap this link on your Android device — it will direct you to where the apk is and to the right on the status bar you will see a donwload button click it and the APK file will be downloaded on your android device and click the file and install it.)

Note: Android will often warn that this app is "from an unknown source" or "may be harmful" when installing APKs that weren't obtained from an official store (like Google Play) and that's obvious nothing to worry about just turn on install apps from this source and install the apk and the app will be downloaded.

---

## 🧭 Key files & where to look (simple, beginner-friendly)

I’ve kept the explanation short and focused on what matters most when you open an Android project.

- Java source code (app/src/main/java/)
    - What it is: The app’s behavior lives here. Look for Activity classes (screens), Adapters (list logic), and small helper/util classes.
    - Where to start: Find `MainActivity` — that’s the app entry point. Open it to see which layout it loads and how user actions are handled.
    - Why : Java files show your logic, structure, and coding.

- Resource XML (app/src/main/res/)
    - What it is: All UI definitions and static resources.
    - Important folders:
        - `res/layout/` — screen and component layouts (XML you can edit visually in Android Studio).
        - `res/values/` — strings, colors, styles (keeps text and theme separate from code).
        - `res/drawable/` & `res/mipmap/` — icons and images.
    - Why: XML shows how UI is built and separated from code. It’s the quickest way to change visuals without touching Java.

- AndroidManifest.xml
    - What it is: Declares app components (Activities) and permissions.
    - Quick use: Use it to find the launcher Activity or to check if special permissions are required.

That’s it — focus on Java + XML + Manifest to understand each sample quickly.

---

## ▶️ How to run (super quick)
1. Clone: https://github.com/Shankar-105/Mini-MAD-Scripts.git
2. Open Android Studio → Open the cloned folder → Let Gradle sync.
3. Run on an emulator or device (enable USB debugging forrunning on any other physical devices).

Tip: If a sample uses external libraries, Android Studio will tell you to download them during sync.

---

## 🤝 Contributing & Improvements

Fork, make small improvements, and open a PR — I love feedback and contributions!

---

## 📫 Contact
GitHub: [Shankar-105](https://github.com/Shankar-105)

Thanks for visiting — I keep these samples minimal and focused so learners can jump in and experiment fast.