# Building and Publishing the THETA Client SDK

This guide explains how to publish the THETA Client SDK for Kotlin Multiplatform.

### Publish to GitHub Packages

#### Local Publishing

1. Create a `local.properties` file in the project root (this file is gitignored):

```properties
gpr.user=your-github-username
gpr.key=your-github-personal-access-token
```

2. Generate a GitHub Personal Access Token (PAT) with these scopes:
   - `read:packages`
   - `write:packages`

3. Run the publish command:

```bash
./gradlew :kotlin-multiplatform:publishAllPublicationsToGitHubPackagesRepository
```

#### CI/CD Publishing (GitHub Actions)

The SDK is automatically published to GitHub Packages when changes are pushed to the `main` branch.

The workflow is defined in `.github/workflows/publish-github-packages.yaml`:

- **Trigger**: Push to `main` branch or manual dispatch
- **Runner**: `macos-latest` (required for iOS targets)
- **Authentication**: Uses `GITHUB_TOKEN` automatically provided by GitHub Actions

To manually trigger a publish:
1. Go to Actions tab in GitHub
2. Select "Publish to GitHub Packages" workflow
3. Click "Run workflow"

### Published Artifacts

The following artifacts are published:

| Artifact ID | Description |
|-------------|-------------|
| `theta-client` | Kotlin Multiplatform metadata |
| `theta-client-android` | Android AAR |
| `theta-client-jvm` | JVM JAR |
| `theta-client-iosx64` | iOS x64 klib |
| `theta-client-iosarm64` | iOS ARM64 klib |
| `theta-client-iossimulatorarm64` | iOS Simulator ARM64 klib |



## Versioning

The SDK version is defined in `kotlin-multiplatform/build.gradle.kts`:

```kotlin
val thetaClientVersion = "1.13.4"
```

Update this value before publishing a new version.

### Authentication errors

- **Local**: Verify `local.properties` has correct username and PAT
- **CI/CD**: The `GITHUB_TOKEN` is automatically provided; no configuration needed

## Using the Published SDK

### From GitHub Packages

Add the GitHub Packages repository to your project:

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/danielclipnow/theta-client")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("com.ricoh360.thetaclient:theta-client:1.13.4")
}
```