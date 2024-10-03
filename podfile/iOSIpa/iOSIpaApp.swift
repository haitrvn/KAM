import SwiftUI
import presentation

@main
struct iOSApp: App {

    init() {
        MainViewControllerKt.InitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
