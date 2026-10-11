# Signed releases / 署名付きAPKの公開

The regular CI workflow produces ephemeral-signature **debug APKs**. Each GitHub Actions runner creates its own debug signing certificate, so users may have to uninstall before replacing a debug APK.

A stable release must use the **same private signing key** for every published version. The key must NOT be committed to this public repository.

## 初回設定（リポジトリ管理者）

PC等の信頼できる環境でJDKの keytool を使い、PKCS12署名鍵を生成します。

~~~powershell
keytool -genkeypair -v -keystore pixel-systemui-release.p12 -storetype PKCS12 -alias pixel-systemui -keyalg RSA -keysize 3072 -validity 10000 -dname "CN=Pixel SystemUI Cache Shortcut, O=Yaruani, C=JP"
~~~

パスワードは自分で設定し、鍵ファイルとパスワードをオフラインへバックアップしてください。

GitHubリポジトリの **Settings → Secrets and variables → Actions → New repository secret** から以下の2つを登録します。

| Secret | 値 |
|---|---|
| APK_KEYSTORE_BASE64 | 作成した PKCS12（.p12）ファイル全体の Base64 |
| APK_KEYSTORE_PASSWORD | keytoolで設定したパスワード |

Windows PowerShellでBase64をクリップボードへコピーできます。

~~~powershell
[Convert]::ToBase64String([IO.File]::ReadAllBytes((Resolve-Path .\pixel-systemui-release.p12))) | Set-Clipboard
~~~

**秘密鍵、Base64、パスワードをGitHubのコミット、Issue、チャットへ貼らないでください。** GitHub Actions Secretsの入力欄に直接登録してください。秘密鍵を失うと同じ署名のAPK更新ができなくなります。

## GitHub Releaseの作成

1. [Actions → Publish signed APK release](../.github/workflows/release.yml) を開きます。
2. **Run workflow** を選択し、初回は **v0.3.0** を入力します。
3. Actionsが署名付きAPKを作成し、署名・追加権限なしを検証します。
4. 成功すると [Releases](../../releases) にAPKとSHA-256が公開されます。

Releaseワークフローは**手動実行のみ**です。Secretsを設定して実行するまで、正式なGitHub Releaseと固定署名のAPKは生成されません。

## English

Use a private PKCS12 keystore with alias **pixel-systemui**, created using the keytool command above. Back up the keystore and password. Set repository Actions secrets named **APK_KEYSTORE_BASE64** (Base64-encoded .p12) and **APK_KEYSTORE_PASSWORD**. Never commit them.

Run **Actions → Publish signed APK release** with a tag matching the Gradle version (initially **v0.3.0**). The workflow compiles an unsigned release APK, aligns and signs it with apksigner, verifies the certificate and zero declared Android permissions, and then publishes an APK and SHA-256 file to GitHub Releases.

Future releases must reuse the same keystore and increment Android's versionCode.

## 固定署名証明書の検証 / Pinned signing certificate

リリース用の公開証明書: [certs/release-signing.pem](../certs/release-signing.pem)

Certificate SHA-256 (lowercase, without colons):
`bf5cbab9bb18ccb2f84c807835edbf38e9fa78917bba0ab05667c5f58d5b2bf8`

GitHub Actionsは署名後のAPKに含まれる証明書をこの値と比較します。異なる秘密鍵を設定するとReleaseを中止します。

For each signed GitHub Release, the workflow verifies that the APK signer certificate matches this pinned fingerprint. If you choose to generate your own release key instead of using the provided signing-key bundle, update the **public** certificate and pinned fingerprint in the workflow before publishing.

**Private keys, passwords, and Base64 secret values must never be committed to this public repository.**
