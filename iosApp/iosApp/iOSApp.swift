import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        Koin.shared.startKoin(modules: [ApplicationModule().module])
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
