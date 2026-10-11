# Pixel System UI Cache Shortcut

English below / English follows after the Japanese instructions.

**No ads · No requested Android permissions · Open source · MIT License**

## 日本語

### このアプリについて

Pixelの「システム UI → ストレージ」設定画面を**ワンタップで開く**Androidアプリです。Androidのアップデート後などにシステムUIのカクつきが発生し、キャッシュ削除で一時的に改善する場合に、設定画面までの操作を短縮します。

**自動でキャッシュを削除するアプリではありません。** 画面を開いた後、「キャッシュを削除」は利用者が手動でタップします。カクつきの原因やAndroid自体の不具合は修正しません。

### 使い方

1. ホーム画面で「System UI キャッシュ画面」をタップします。
2. 表示された「システム UI」のストレージ画面で「キャッシュを削除」をタップします。
3. 直接開けない端末では「システム UI」のアプリ情報画面が開きます。その場合はストレージ項目を手動で開いてください。

### 安全性・プライバシー

- **広告なし**：広告表示・広告SDKなし。
- **要求するAndroid権限は0件**：通信権限・ユーザー補助権限・ストレージ権限を要求しません。root、ADB、Shizukuも不要です。
- **通信・個人データの収集・解析機能なし**：アプリにネットワーク処理、分析SDK、常駐サービスはありません。
- キャッシュ削除の自動操作、アプリデータ消去、強制停止は行いません。
- 操作する対象は **com.android.systemui** に固定されています。

**安全性は各自で公開ソースコードを確認してください。AIを利用したソースコード検査も可能です。ただし、AIによる検査は安全性の保証ではありません。** インストールするAPKについても配布元、署名、SHA-256を確認してください。

### 対応状況

- **動作確認済み：Google Pixel 10 Pro XL / Android 17**（2026年10月、システムUIのストレージ画面への遷移）
- その他の端末・OSバージョン：**未検証**
- Androidの設定画面の構成に依存するため、今後のアップデートで動作が変わる場合があります。

### APKの入手

- **[GitHub Actionsのビルド](../../actions/workflows/build-apk.yml)** → 成功した実行 → Artifacts → Pixel-SystemUI-Cache-Shortcut-debug-APK → ZIPを展開してAPKをインストール。
- ActionsのデバッグAPKは実行ごとに署名が変わるため、同一アプリの更新時に旧版のアンインストールが必要になる場合があります。
- [GitHub Releases](../../releases)に安定した署名のAPKを公開するためのワークフローも用意しています。**初回の署名鍵設定が完了するまで安定版Releaseはありません。**

### ソースコード

Java 17 / Android SDK 36。AndroidManifestに追加権限はなく、アクティビティ1個のみです。外部ランタイムライブラリはありません。使用する画面遷移は、Android設定アプリの APP_STORAGE_SETTINGS アクションおよび標準のアプリ情報設定アクションです。

[ライセンス：MIT](LICENSE) · [署名付きリリース手順](docs/RELEASING.md)

---

## English

### What it does

A **one-tap shortcut to the System UI storage screen** on Google Pixel phones. If clearing the System UI cache temporarily alleviates lag or stuttering, this shortcut saves navigation through Android Settings.

**This app does not clear cache automatically.** After opening the screen, tap **Clear cache** yourself. It does not fix the underlying Android performance issue.

### How to use it

1. Tap **System UI Cache Shortcut**.
2. On the **System UI → Storage** screen, tap **Clear cache** manually.
3. If direct navigation isn't supported, the app falls back to **System UI → App info**. Navigate to **Storage & cache** from there.

### Security and privacy

- **No ads**, advertising SDKs, or tracking libraries.
- **Zero requested Android permissions**. No network, Accessibility, storage, root, ADB, or Shizuku access needed.
- No analytics, data collection, background processes, or automatic screen tapping.
- Never clears app data or force-stops System UI. Target package is fixed to **com.android.systemui**.

**Please verify the source code's safety yourself, including with an AI code review if desired. AI analysis is not a security guarantee.** Verify the source, origin, signature, and SHA-256 of any APK you install.

### Compatibility

- **Tested:** Google Pixel 10 Pro XL running Android 17 (October 2026), direct navigation to the System UI storage screen.
- **Not tested:** other Android devices or versions. Behavior may change with OS updates and OEM Settings implementations.

### Downloads

- **[GitHub Actions build](../../actions/workflows/build-apk.yml)** → successful run → Artifacts → Pixel-SystemUI-Cache-Shortcut-debug-APK → unzip and install.
- Debug APKs are signed with ephemeral build keys and may not install over an older APK.
- A stable, repeatably signed [release workflow](docs/RELEASING.md) is included; **no stable GitHub Release is available until the maintainer configures the signing key**.

### Development

Java 17 / Android SDK 36. One Activity; no additional manifest permissions or third-party runtime libraries. The app launches Android Settings using the APP_STORAGE_SETTINGS action and a fixed package URI, and falls back to the normal app-info settings action.

[MIT License](LICENSE) · Unofficial project, not affiliated with or endorsed by Google.
