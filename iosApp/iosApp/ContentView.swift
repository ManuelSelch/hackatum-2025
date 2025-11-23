import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        // MainViewControllerKt.MainViewController()
        
        MainKt.ComposeEntryPointWithUIViewController { callback in
            let root = ScannerView(onScanned: { qr in
                callback(qr)
            })
            return UIHostingController(rootView: root)
        }
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            .ignoresSafeArea()
    }
}
